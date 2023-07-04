package negima.proveedorservice.controller;

import negima.proveedorservice.service.ProveedorService;
import negima.proveedorservice.entity.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    ProveedorService serv;

    @GetMapping
    public ResponseEntity<List<Proveedor>> getAll(){
        List<Proveedor> proveedores = serv.getAll();
        if(proveedores.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Proveedor> getById(@PathVariable("codigo") int codigo) {
        Proveedor proveedor = serv.getProveedorById(codigo);
        if(proveedor == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(proveedor);
    }

    @PostMapping()
    public ResponseEntity<Proveedor> save(@RequestBody Proveedor newproveedor){
        newproveedor = serv.resolveID(newproveedor);
        Proveedor nuevo = serv.save(newproveedor);
        return ResponseEntity.ok(nuevo);
    }

    @PostMapping("/swap/{codigo}")
    public ResponseEntity<Proveedor> swap(@PathVariable("codigo") int codigo){
        if(serv.getProveedorById(codigo) != null)
            return ResponseEntity.ok(serv.changeRetencion(codigo));
        return ResponseEntity.notFound().build();
    }
}
