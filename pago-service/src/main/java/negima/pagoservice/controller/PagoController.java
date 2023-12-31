package negima.pagoservice.controller;

import negima.pagoservice.entity.Pago;
import negima.pagoservice.service.PagoService;
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

    @PostMapping("/calcular/{anno}/{mes}/{quin}")
    public ResponseEntity<String> calcularPagos(@PathVariable("anno")Integer anno,@PathVariable("mes")Integer mes,@PathVariable("quin")Integer quin){
        Integer[] fecha = new Integer[]{anno, mes, quin};
        serv.calcularPagos(fecha);
        return ResponseEntity.ok("Datos calculados correctamente");
    }
}
