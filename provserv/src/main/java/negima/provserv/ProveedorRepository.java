package negima.provserv;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProveedorRepository extends CrudRepository<Proveedor, Integer> {

    Optional<Proveedor> findByNombre(String name);
}
