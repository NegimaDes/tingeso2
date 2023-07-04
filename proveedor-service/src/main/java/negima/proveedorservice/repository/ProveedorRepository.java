package negima.proveedorservice.repository;


import negima.proveedorservice.entity.Proveedor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProveedorRepository extends CrudRepository<Proveedor, Integer> {
}
