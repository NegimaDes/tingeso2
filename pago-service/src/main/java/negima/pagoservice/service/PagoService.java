package negima.pagoservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import negima.pagoservice.entity.Pago;
import negima.pagoservice.models.Acopio;
import negima.pagoservice.models.Calidad;
import negima.pagoservice.models.Proveedor;
import negima.pagoservice.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class PagoService {
    static final int PAGO_A = 700;
    static final int PAGO_B = 550;
    static final int PAGO_C = 400;
    static final int PAGO_D = 250;
    static final int GRASA_BAJA = 30;
    static final int GRASA_MEDIA = 80;
    static final int GRASA_ALTA = 120;
    static final int SOL_BAJO = -130;
    static final int SOL_MEDIO_BAJO = -90;
    static final int SOL_MEDIO_ALTO = 95;
    static final int SOL_ALTO = 150;
    static final int BON_DOBLE = 20;
    static final int BON_MAN = 12;
    static final int BON_TAR = 8;
    static final int VAR_KLS_BAJA = 7;
    static final int VAR_KLS_MEDIA = 15;
    static final int VAR_KLS_ALTA = 30;
    static final int VAR_GRASA_BAJA = 12;
    static final int VAR_GRASA_MEDIA = 20;
    static final int VAR_GRASA_ALTA = 30;
    static final int VAR_SOL_BAJA = 18;
    static final int VAR_SOL_MEDIA = 27;
    static final int VAR_SOL_ALTA = 45;
    static final float RETENCION = 0.13F;

    @Autowired
    PagoRepository repo;

    @Autowired
    RestTemplate restTemplate;

    public Pago save(Pago pago){
        return repo.save(pago);
    }

    public List<Pago> getAll(){ return (List<Pago>) repo.findAll();}

    @SneakyThrows
    public List<Acopio> getAcopios(int anno, int mes, int quincena){
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("anno", anno);
        map.put("mes", mes);
        map.put("quin", quincena);
        System.out.println(mes);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "http://acopio-service/acopio/{anno}/{mes}/{quin}",
                String.class, map);
        if(!Objects.equals(responseEntity.getBody(), "[]")) {
            String json = responseEntity.getBody();
            ObjectMapper mapper = new ObjectMapper();
            List<Acopio> datos = null;
            try {
                datos = mapper.readValue(json, new TypeReference<List<Acopio>>(){});
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return datos;
        }
        return null;
    }

    public Calidad getCalidad(int codigo, int anno, int mes, int quincena){
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("code",codigo);
        map.put("anno", anno);
        map.put("mes", mes);
        map.put("quin", quincena);
        ResponseEntity<Calidad> responseEntity = restTemplate.getForEntity(
                "http://calidad-service/calidad/{code}/{anno}/{mes}/{quin}",
                Calidad.class, map);
        if(responseEntity.getStatusCode() == HttpStatus.OK) {
            System.out.println(responseEntity.getBody().getGrasas());
            return responseEntity.getBody();
        }
        return null;
    }

    public Proveedor getProveedor(int codigo){
        ResponseEntity<Proveedor> responseEntity = restTemplate.getForEntity(
                "http://proveedor-service/proveedor/{code}",
                Proveedor.class, codigo);
        if(responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        }
        return null;
    }

    public void calcularPagos(Integer[] fecha){
        List<Acopio> porpagar = getAcopios(fecha[0], fecha[1], fecha[2]);
        Integer[] fecha2 = getAnterior(fecha);
        List<Acopio> anteriores = getAcopios(fecha2[0], fecha2[1], fecha2[2]);
        if(anteriores != null)
            procesos(porpagar, anteriores,fecha,fecha2);
        else
            procesos(porpagar, fecha);
    }

    public Integer[] getAnterior(Integer[] fecha){
        int quincena = fecha[2];
        int mes = fecha[1];
        int anno = fecha[0];
        if(quincena == 1) {
            if (mes == 1)
                return new Integer[]{anno - 1, 12, 2};
            else
                return new Integer[]{anno, mes - 1, 2};
        } else
            return new Integer[]{anno,mes,1};
    }

    public Pago procesoComun(Proveedor provcal, Calidad calcal, Acopio calculando, Pago pago, Integer[] fecha){
        pago.setCodigo(calculando.getCodigo());
        pago.setPleche(getPagoCat(provcal) * calculando.getTotalkls());
        pago.setPgrasa(getPagoGrasa(calcal) * calculando.getTotalkls());
        pago.setPsolidos(getPagoSolidos(calcal) * calculando.getTotalkls());
        pago.setBonificacion(getBonificacionFrecuencia(calculando));
        pago.setFecha(fecha[0],fecha[1],fecha[2]);
        pago.setCodigo(calculando.getCodigo());
        pago.setNombre(provcal.getNombre());
        pago.setKls(calculando.getTotalkls());
        pago.setDiasenvio(calculando.getEntregas());
        pago.setPromdiario((float) pago.getKls()/pago.getDiasenvio());
        pago.setGrasa(calcal.getGrasas());
        pago.setSolidos(calcal.getSolidos());
        return pago;
    }

    public void procesos(List<Acopio> porpagar, Integer[] fecha){
        for(Acopio calculando:porpagar){
            Pago pago = new Pago();
            Calidad calcal = getCalidad(calculando.getCodigo(), fecha[0], fecha[1], fecha[2]);
            Proveedor provcal = getProveedor(calculando.getCodigo());

            pago = procesoComun(provcal, calcal, calculando, pago, fecha);

            pago.setVarl(0);
            pago.setVarg(0);
            pago.setVars(0);

            pago = calculosInternos(pago);
            pago = definirTotal(pago, provcal);
            save(pago);
        }
    }

    public void procesos(List<Acopio> porpagar, List<Acopio> anteriores, Integer[] fecha, Integer[] fecha2){
        for(Acopio calculando:porpagar){
            Pago pago = new Pago();
            Calidad calcal = getCalidad(calculando.getCodigo(), fecha[0], fecha[1], fecha[2]);
            Calidad calcal2 = getCalidad(calculando.getCodigo(), fecha2[0], fecha2[1], fecha2[2]);
            Acopio previo = anterior(calculando, anteriores);
            Proveedor provcal = getProveedor(calculando.getCodigo());

            pago = procesoComun(provcal, calcal, calculando, pago, fecha);

            pago.setVarl(diferenciaKLS(calculando, previo));
            pago.setVarg(diferenciaGrasa(calcal, calcal2));
            pago.setVars(diferenciaSolidos(calcal, calcal2));

            pago = calculosInternos(pago);
            pago = definirTotal(pago, provcal);
            save(pago);
        }
    }

    public Pago calculosInternos(Pago pago){
        Integer pagoacopio = pago.getPleche() + pago.getPgrasa() + pago.getPsolidos() + pago.getPleche()*pago.getBonificacion();
        pago.setDvarl(pagoacopio * getVariacionKLS(pago.getVarl()));
        pago.setDvarg(pagoacopio * getVariacionGrasa(pago.getVarg()));
        pago.setDvars(pagoacopio * getVariacionSolidos(pago.getVars()));
        Integer descuentototal = pago.getDvarl() + pago.getDvarg() + pago.getDvars();
        pago.setTotal(pagoacopio - descuentototal);
        return pago;
    }

    public Pago definirTotal(Pago pago, Proveedor proveedor){
        if(pago.getTotal() > 950000){
            pago.setVfinal((int) (pago.getTotal() *(1-RETENCION)));
            pago.setRetencion((int) (pago.getTotal() * RETENCION));
            if(!proveedor.getRetencion())
                restTemplate.postForEntity("http://proveedor-service/proveedor/swap/"+proveedor.getCodigo(),null,null);
        }else{
            pago.setVfinal(pago.getTotal());
            pago.setRetencion(0);
            if(proveedor.getRetencion())
                restTemplate.postForEntity("http://proveedor-service/proveedor/swap/"+proveedor.getCodigo(),null,null);
        }
        return pago;
    }

    public Integer getPagoCat(Proveedor prov){
        String categoria = prov.getCategoria();
        if(Objects.equals(categoria, "A")){
            return PAGO_A;
        }else if(Objects.equals(categoria, "B")){
            return PAGO_B;
        }else if(Objects.equals(categoria, "C")){
            return PAGO_C;
        }else{
            return PAGO_D;
        }
    }

    public Integer getPagoGrasa(Calidad calidad){
        System.out.println(calidad == null);
        if(calidad.getGrasas() <= 20){
            return GRASA_BAJA;
        }else if(calidad.getGrasas() <= 45){
            return GRASA_MEDIA;
        }else{
            return GRASA_ALTA;
        }
    }

    public Integer getPagoSolidos(Calidad calidad){
        if(calidad.getSolidos() <= 7){
            return SOL_BAJO;
        }else if(calidad.getSolidos() <= 18){
            return SOL_MEDIO_BAJO;
        }else if(calidad.getSolidos() <= 35){
            return SOL_MEDIO_ALTO;
        }else{
            return SOL_ALTO;
        }
    }

    public Integer getBonificacionFrecuencia(Acopio acopio){
        if(acopio.getTarde() >= 10){
            if(acopio.getManana() >= 10){
                return BON_DOBLE;
            }
            return BON_TAR;
        }else if(acopio.getManana() >= 10){
            return BON_MAN;
        }
        return 0;
    }

    public Acopio anterior(Acopio actual, List<Acopio> previos){
        for(Acopio temp:previos)
            if(actual.getCodigo() == temp.getCodigo())
                return temp;
        return null;
    }

    public Integer diferenciaKLS(Acopio actual, Acopio previo){
        if(previo == null){
            return 0;
        }
        float relacion = ((float) (actual.getTotalkls())/(float) (previo.getTotalkls()))*100;
        return (int) (100 - relacion);
    }

    public Integer diferenciaGrasa(Calidad calactual, Calidad calprevia){
        if(calprevia == null){
            return 0;
        }
        float relacion = ((float) (calactual.getGrasas())/(float) (calprevia.getGrasas()))*100;
        return (int) (100 - relacion);
    }

    public Integer diferenciaSolidos(Calidad calactual, Calidad calprevia){
        if(calprevia == null){
            return 0;
        }
        float relacion = ((float) (calactual.getSolidos())/(float) (calprevia.getSolidos()))*100;
        return (int) (100 - relacion);
    }

    public Integer getVariacionKLS(Integer diferencia){
        if(diferencia <= 8){
            return 0;
        }else if(diferencia <= 25){
            return VAR_KLS_BAJA;
        }else if(diferencia <= 45){
            return VAR_KLS_MEDIA;
        }else{
            return VAR_KLS_ALTA;
        }
    }

    public Integer getVariacionGrasa(Integer diferencia){
        if(diferencia <= 8){
            return 0;
        }else if(diferencia <= 25){
            return VAR_GRASA_BAJA;
        }else if(diferencia <= 45){
            return VAR_GRASA_MEDIA;
        }else{
            return VAR_GRASA_ALTA;
        }
    }

    public Integer getVariacionSolidos(Integer diferencia){
        if(diferencia <= 8){
            return 0;
        }else if(diferencia <= 25){
            return VAR_SOL_BAJA;
        }else if(diferencia <= 45){
            return VAR_SOL_MEDIA;
        }else{
            return VAR_SOL_ALTA;
        }
    }

}
