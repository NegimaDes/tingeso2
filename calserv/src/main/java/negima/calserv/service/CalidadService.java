package negima.calserv.service;

import negima.calserv.entity.Calidad;
import negima.calserv.repository.CalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class CalidadService {

    @Autowired
    CalidadRepository repo;

    public Calidad save(Calidad nuevo){return repo.save(nuevo);}

    public List<Calidad> getAll(){return (List<Calidad>) repo.findAll();}

    public Calidad getCalidadById(int id){return repo.findById(id).orElse(null);}

    public void readDoc(MultipartFile doc, Integer[] fecha){
        String line;
        BufferedReader br;
        try{
            InputStream is = doc.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            line = br.readLine();
            while ((line = br.readLine()) != null){
                readLine(line, fecha);
            }
            is.close();
        } catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    public void readLine(String line, Integer[] fecha){
        String[] arr = line.split(",");
        Calidad nuevo = new Calidad();
        nuevo.setCodigo(Integer.parseInt(arr[0]));
        nuevo.setGrasa(Integer.parseInt(arr[1]));
        nuevo.setSolido(Integer.parseInt(arr[2]));
        nuevo.setAnno(fecha[0]);
        nuevo.setMes(fecha[1]);
        nuevo.setQuincena(fecha[2]);
    }

    public Calidad getByInfo(int codigo, int anno, int mes, int quincena){
        Iterable<Calidad> all = repo.findAll();
        for(Calidad temp:all)
            if(temp.getCodigo() == codigo && temp.getAnno()==anno &&temp.getMes()==mes && temp.getQuincena()==quincena)
                return temp;
        return null;
    }

}
