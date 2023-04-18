package lt.bite.orders.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.bite.orders.exception.NotFoundException;
import lt.bite.orders.model.dto.MSISDNDTO;
import lt.bite.orders.model.dto.OrderedServiceDTO;
import lt.bite.orders.model.dto.converter.MSISDNConverter;
import lt.bite.orders.model.dto.converter.OrderedServiceConverter;
import lt.bite.orders.model.entity.MSISDN;
import lt.bite.orders.model.entity.OrderedService;
import lt.bite.orders.repository.MSISDNRepository;
import lt.bite.orders.repository.OrderedServiceRepository;
import lt.bite.orders.service.OrderedServiceService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderedServiceImpl implements OrderedServiceService {

    private final OrderedServiceRepository orderedServiceRepository;
    private final MSISDNRepository msisdnRepository;
    private final OrderedServiceConverter orderedServiceConverter;
    private final MSISDNConverter msisdnConverter;

    @Override
    public Set<OrderedServiceDTO> getOrderedServiceByMSISDNId(Long msisdnId) {
        Optional<MSISDN> msisdn = msisdnRepository.findById(msisdnId);
        if (msisdn.isPresent()) {
            MSISDNDTO msisdndTO = msisdnConverter.convertToDTO(msisdn.get());
            return msisdndTO.getOrderedServices();
        } else {
            log.error("MSISDN with id: " + msisdnId + " not found.");
            throw new NotFoundException("MSISDN not found.");
        }
    }

    @Override
    public OrderedServiceDTO getOrderedServiceById(Long id) {
        Optional<OrderedService> orderedService = orderedServiceRepository.findById(id);
        if (orderedService.isPresent()) {
            return orderedServiceConverter.convertToDTO(orderedService.get());
        } else {
            log.error("Ordered service with id: " + id + " not found.");
            throw new NotFoundException("Ordered service not found.");
        }
    }

    @Override
    public OrderedServiceDTO createOrderedService(Long msisdnId, OrderedServiceDTO orderedServiceDTO) {
        Optional<MSISDN> msisdn = msisdnRepository.findById(msisdnId);
        if (msisdn.isPresent()) {
            OrderedService orderedService = orderedServiceConverter.convertToEntity(orderedServiceDTO);

            orderedService.setMsisdn(msisdn.get());

            OrderedService savedOrderService = orderedServiceRepository.save(orderedService);
            return orderedServiceConverter.convertToDTO(savedOrderService);
        } else {
            log.error("Failed ordering a service for msisdn with Id: " + msisdnId);
            throw new NotFoundException("MSISDN not found.");
        }
    }

    @Override
    public OrderedServiceDTO updateOrderedService(Long id, OrderedServiceDTO orderedServiceDTO) {
        Optional<OrderedService> existingOrderedService = orderedServiceRepository.findById(id);

        if (existingOrderedService.isPresent()) {
            existingOrderedService.get().setActiveTo(orderedServiceDTO.getActiveTo());

            orderedServiceRepository.save(existingOrderedService.get());
            return orderedServiceConverter.convertToDTO(existingOrderedService.get());
        } else {
            log.error("Ordered service with id: " + id + " not found.");
            throw new NotFoundException("Ordered service not found.");
        }
    }

    @Override
    public void deleteOrderedServiceById(Long id) {
        orderedServiceRepository.deleteById(id);
    }
}