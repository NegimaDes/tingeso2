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

    @PostMapping("/calcular/")
    public ResponseEntity<String> calcularPagos(@RequestParam("fecha") Integer[] fecha){
        serv.calcularPagos(fecha);
        return ResponseEntity.ok("Datos calculados correctamente");
    }
}
