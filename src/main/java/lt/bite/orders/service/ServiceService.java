package lt.bite.orders.service;

import lt.bite.orders.model.dto.ServiceDTO;
import lt.bite.orders.model.entity.Service;

import java.util.Set;

public interface ServiceService {

    Set<ServiceDTO> getServices();

    ServiceDTO getServiceById(Long id);

    ServiceDTO createService(ServiceDTO serviceDTO);

    ServiceDTO updateService(Long id, ServiceDTO serviceDTO);

    void deleteServiceById(Long id);
}
