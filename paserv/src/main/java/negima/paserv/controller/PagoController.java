package negima.paserv.controller;

import negima.paserv.entity.Pago;
import negima.paserv.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pago")
public class PagoController {

    @Autowired
    PagoService serv;

    @GetMapping
    public ResponseEntity<List<Pago>> listarPagos(){
        List<Pago> data = serv.getAll();
        if(data.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(data);
    }

    @PostMapping("/calcular/{anno}/{mes}/{quincena}")
    public ResponseEntity<String> calcularPagos(@PathVariable("anno") int anno, @PathVariable("mes") int mes, @PathVariable("quincena") int quincena){
        Integer[] fecha = new Integer[]{anno, mes, quincena};
        serv.calcularPagos(fecha);
        return ResponseEntity.ok("Datos calculados correctamente");
    }
}
