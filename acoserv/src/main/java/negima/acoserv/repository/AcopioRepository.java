package negima.acoserv.repository;

import negima.acoserv.entity.Acopio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcopioRepository extends CrudRepository<Acopio, Integer> {

}
