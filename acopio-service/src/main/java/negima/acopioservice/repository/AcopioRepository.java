package negima.acopioservice.repository;

import negima.acopioservice.entity.Acopio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcopioRepository extends CrudRepository<Acopio, Integer> {

}
