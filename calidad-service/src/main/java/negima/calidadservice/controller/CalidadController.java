package negima.calidadservice.controller;

import negima.calidadservice.entity.Calidad;
import negima.calidadservice.service.CalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/calidad")
public class CalidadController {

    @Autowired
    CalidadService serv;

    @GetMapping
    public ResponseEntity<List<Calidad>> getAll(){
        List<Calidad> calidad = serv.getAll();
        if(calidad.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(calidad);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Calidad> getById(@PathVariable("id") int id) {
        Calidad calidad = serv.getCalidadById(id);
        if(calidad == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(calidad);
    }

    @PostMapping()
    public ResponseEntity<Calidad> save(@RequestBody Calidad newcalidad){
        Calidad nuevo = serv.save(newcalidad);
        return ResponseEntity.ok(nuevo);
    }

    @PostMapping("/doc")
    public ResponseEntity<String> docRead(@RequestParam("file") MultipartFile file){
        Integer[] fecha = serv.getFecha();
        if(fecha == null)
            return ResponseEntity.internalServerError().build();
        System.out.println(fecha[0]);
        serv.readDoc(file,fecha);
        return ResponseEntity.ok("Archivo correctamente guardado");
    }

    @GetMapping("/{code}/{anno}/{mes}/{quin}")
    public ResponseEntity<Calidad> getCalidad(@PathVariable("code") int codigo,
                                              @PathVariable("anno") int anno,
                                              @PathVariable("mes") int mes,
                                              @PathVariable("quin") int quincena){
        Calidad calidad = serv.getByInfo(codigo, anno, mes, quincena);
        if(calidad == null)
            return ResponseEntity.notFound().build();
        System.out.println(calidad.getGrasas());
        return ResponseEntity.ok(calidad);
    }
}
