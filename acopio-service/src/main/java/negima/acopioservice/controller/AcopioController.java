package negima.acopioservice.controller;

import negima.acopioservice.entity.Acopio;
import negima.acopioservice.service.AcopioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/acopio")
public class AcopioController {

    @Autowired
    AcopioService serv;

    @GetMapping
    public ResponseEntity<List<Acopio>> getAll(){
        List<Acopio> acopios = serv.getAll();
        if(acopios.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(acopios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Acopio> getById(@PathVariable("id") int id) {
        Acopio acopio = serv.getAcopioById(id);
        if(acopio == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(acopio);
    }

    @PostMapping
    public ResponseEntity<Acopio> save(@RequestBody Acopio newacopio){
        Acopio nuevo = serv.save(newacopio);
        return ResponseEntity.ok(nuevo);
    }

    @PostMapping("/doc")
    public ResponseEntity<String> docRead(@RequestParam("file")MultipartFile file){
        serv.readDoc(file);
        return ResponseEntity.ok("Archivos correctamente guardados");
    }

    @GetMapping("/{anno}/{mes}/{quin}")
    public ResponseEntity<List<Acopio>> findPorFecha(@PathVariable("anno") int anno, @PathVariable("mes") int mes, @PathVariable("quin")int quincena){
        List<Acopio> acopios = serv.getByDate(anno, mes, quincena);
        if(acopios.isEmpty()) {
            System.out.println("Se devuelve sin nada");
            List<Acopio> dummy = new ArrayList<>();
            return ResponseEntity.ok(dummy);
        }
        System.out.println("Se devuelve con algo");
        return ResponseEntity.ok(acopios);
    }

    @GetMapping("/lastDate")
    public ResponseEntity<Integer[]> findLastDate(){
        Acopio ultimo = serv.getLast(serv.getAll());
        return ResponseEntity.ok(new Integer[]{ultimo.getAnno(), ultimo.getMes(), ultimo.getQuincena()});
    }
}
