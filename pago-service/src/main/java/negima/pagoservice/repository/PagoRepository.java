package negima.pagoservice.repository;

import negima.pagoservice.entity.Pago;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends CrudRepository<Pago, Integer> {
}
