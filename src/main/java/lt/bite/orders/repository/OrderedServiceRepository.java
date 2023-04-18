package lt.bite.orders.repository;

import lt.bite.orders.model.entity.OrderedService;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface OrderedServiceRepository extends CrudRepository<OrderedService, Long> {

    Set<OrderedService> findAllByMsisdnId(Long id);
}