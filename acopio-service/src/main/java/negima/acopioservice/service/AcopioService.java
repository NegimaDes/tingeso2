package negima.acoserv.service;

import negima.acoserv.entity.Acopio;
import negima.acoserv.repository.AcopioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AcopioService {

    @Autowired
    AcopioRepository repo;

    @Autowired
    RestTemplate restTemplate;

    public Acopio save(Acopio nuevo){return repo.save(nuevo);}

    public List<Acopio> getAll(){return (List<Acopio>) repo.findAll();}

    public List<Acopio> getByDate(int anno, int mes, int quincena){
        Iterable<Acopio> all = repo.findAll();
        List<Acopio> resultantes = new ArrayList<>();
        for(Acopio temp:all)
            if(confirmar(temp, anno, mes, quincena))
                resultantes.add(temp);
        return resultantes;
    }

    public Acopio getAcopioById(int id) {return repo.findById(id).orElse(null);}

    public Acopio getAcopioByCodigo(int codigo){
        Iterable<Acopio> lista = repo.findAll();
        List<Acopio> aceptables = new ArrayList<>();
        for(Acopio revision:lista){
            if(revision.getCodigo() == codigo)
                aceptables.add(revision);
        }
        return getLast(aceptables);
    }

    public Acopio getLast(List<Acopio> lista){
        Acopio ultimo = new Acopio();
        for(Acopio revision:lista) {
            if(ultimo.getId() == null)
                ultimo = revision;
            if (revision.getAnno() > ultimo.getAnno())
                ultimo = revision;
            if (revision.getAnno() == ultimo.getAnno() && revision.getMes() > ultimo.getMes())
                ultimo = revision;
            if (revision.getAnno() == ultimo.getAnno() && revision.getMes() == ultimo.getMes() && revision.getQuincena() > ultimo.getQuincena())
                ultimo = revision;
        }
        return ultimo;
    }

    public Acopio findAcopio(int codigo, Integer anno, Integer mes, Integer quincena){
        Acopio ultimo = getAcopioByCodigo(codigo);
    if(ultimo.getAnno() == anno && ultimo.getMes() == mes && ultimo.getQuincena() == quincena)
        return ultimo;
    return null;
    }

    public boolean confirmar(Acopio acopio, Integer anno, Integer mes, Integer quincena){
        return Objects.equals(acopio.getAnno(), anno) && Objects.equals(acopio.getMes(), mes) && Objects.equals(acopio.getQuincena(), quincena);
    }

    public void readDoc(MultipartFile doc1, MultipartFile doc2){
        String line;
        BufferedReader br;
        Integer[] fecha = null;
        try{
            InputStream is = doc1.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            line = br.readLine();
            while ((line = br.readLine()) != null){
                fecha = readLine(line);
            }
            is.close();
            restTemplate.postForEntity("http://calserv/doc/"+fecha[0]+"/"+fecha[1]+"/"+fecha[2],doc1, MultipartFile.class);
        } catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    public Integer[] readLine(String line){
        String[] arr = line.split(",");
        Integer[] fecha = getListDate(arr[0]);
        int quincena = 1;
        if(fecha[1] > 15)
            quincena = 2;
        Integer[] fecharespuesta = new Integer[]{fecha[2], fecha[0], quincena};
        Acopio acopio = findAcopio(Integer.parseInt(arr[2]), fecha[2], fecha[0], quincena);
        saveHandler(acopio, fecha[2], fecha[0], quincena, arr[1], arr[3], arr[2]);
        return fecharespuesta;
    }

    public Integer[] getListDate(String fecha){
        String[] arr = fecha.split("/");
        int anno = Integer.parseInt(arr[2]);
        int mes = Integer.parseInt(arr[0]);
        int dia = Integer.parseInt(arr[1]);
        return new Integer[]{mes, dia, anno};
    }

    public void saveHandler(Acopio acopio, Integer anno, Integer mes, Integer quincena, String turno, String kls, String proveedor) {
        if (acopio == null) {
            acopio = new Acopio();
            acopio.startCounters();
            acopio.setCodigo(Integer.parseInt(proveedor));
            acopio.setFecha(anno, mes, quincena);
        }
        acopio.addKls(Integer.parseInt(kls));
        if (Objects.equals(turno, "M")) {
            acopio.addManana();
        } else {
            acopio.addTarde();
        }
        save(acopio);
    }

}
