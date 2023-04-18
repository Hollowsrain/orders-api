package lt.bite.orders.repository;

import lt.bite.orders.model.entity.MSISDN;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface MSISDNRepository extends CrudRepository<MSISDN, Long> {

    Set<MSISDN> findAllByAccountId(Long id);
}