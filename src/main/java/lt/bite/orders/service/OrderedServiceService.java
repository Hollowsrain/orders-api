package lt.bite.orders.service;

import lt.bite.orders.model.dto.OrderedServiceDTO;

import java.util.Set;

public interface OrderedServiceService {

    Set<OrderedServiceDTO> getOrderedServiceByMSISDNId(Long msisdnId);

    OrderedServiceDTO getOrderedServiceById(Long id);

    OrderedServiceDTO createOrderedService(Long msisdnId, OrderedServiceDTO orderedServiceDTO);

    OrderedServiceDTO updateOrderedService(Long id, OrderedServiceDTO orderedServiceDTO);

    void deleteOrderedServiceById(Long id);
}