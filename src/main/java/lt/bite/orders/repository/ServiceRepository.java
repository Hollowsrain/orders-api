package lt.bite.orders.repository;

import lt.bite.orders.model.entity.Service;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<Service, Long> {
}
