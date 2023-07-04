package negima.calidadservice.repository;

import negima.calidadservice.entity.Calidad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalidadRepository extends CrudRepository<Calidad, Integer> {
}
