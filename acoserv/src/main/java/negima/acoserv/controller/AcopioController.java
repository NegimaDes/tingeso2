package negima.acoserv.controller;

import negima.acoserv.entity.Acopio;
import negima.acoserv.service.AcopioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<String> docRead(@RequestParam("doc1")MultipartFile doc1, @RequestParam("doc2")MultipartFile doc2){
        serv.readDoc(doc1, doc2);
        return ResponseEntity.ok("Archivos correctamente guardados");
    }

    @GetMapping("/{anno}/{mes}/{quin}")
    public ResponseEntity<List<Acopio>> findPorFecha(@PathVariable("anno") int anno, @PathVariable("mes") int mes, @PathVariable("quin")int quincena){
        List<Acopio> acopios = serv.getByDate(anno, mes, quincena);
        if(acopios.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(acopios);
    }
}
