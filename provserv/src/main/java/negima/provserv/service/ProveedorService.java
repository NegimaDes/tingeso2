package negima.provserv.service;

import negima.provserv.entity.Proveedor;
import negima.provserv.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Math.max;

@Service
public class ProveedorService {

    @Autowired
    ProveedorRepository repo;

    public List<Proveedor> getAll(){
        return (List<Proveedor>) repo.findAll();
    }

    public Proveedor getProveedorById(int codigo){
        return repo.findById(codigo).orElse(null);
    }

    public Proveedor save(Proveedor newproveedor){
        return repo.save(newproveedor);
    }

    public Proveedor resolveID(Proveedor corregir){
        corregir.setCodigo(nextPosible(corregir));
        return corregir;
    }

    public int nextPosible(Proveedor proveedor){
        Iterable<Proveedor> all = repo.findAll();
        int codigo = proveedor.getCodigo();
        int code = 0;
        for(Proveedor ac: all){
            if(ac.getCodigo() - (ac.getCodigo()%1000) == codigo - (codigo%1000)){
                code = max(ac.getCodigo(), code);
            }
        }
        if(code != 0)
            return code + 1;
        return codigo - (codigo%1000) +1;
    }
    public Proveedor changeRetencion(int codigo){
        Proveedor editar = getProveedorById(codigo);
        editar.setRetencion(!editar.getRetencion());
        return save(editar);
    }
}