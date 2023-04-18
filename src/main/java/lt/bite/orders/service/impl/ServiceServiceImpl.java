package lt.bite.orders.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.bite.orders.exception.NotFoundException;
import lt.bite.orders.model.dto.ServiceDTO;
import lt.bite.orders.model.dto.converter.ServiceConverter;
import lt.bite.orders.model.entity.Service;
import lt.bite.orders.repository.ServiceRepository;
import lt.bite.orders.service.ServiceService;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@org.springframework.stereotype.Service
@Slf4j
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final ServiceConverter serviceConverter;

    @Override
    public Set<ServiceDTO> getServices() {
        Set<ServiceDTO> services = new HashSet<>();
        serviceRepository.findAll().forEach(service -> services.add(serviceConverter.convertToDTO(service)));
        return services;
    }

    @Override
    public ServiceDTO getServiceById(Long id) {
        Optional<Service> service = serviceRepository.findById(id);
        if (service.isPresent()) {
            return serviceConverter.convertToDTO(service.get());
        } else {
            log.error("Service with id: " + id + " not found.");
            throw new NotFoundException("Service not found.");
        }
    }

    @Override
    public ServiceDTO createService(ServiceDTO serviceDTO) {
        Service savedService = serviceRepository.save(serviceConverter.convertToEntity(serviceDTO));
        return serviceConverter.convertToDTO(savedService);
    }

    @Override
    public ServiceDTO updateService(Long id, ServiceDTO serviceDTO) {
        Optional<Service> existingService = serviceRepository.findById(id);
        if (existingService.isPresent()) {

            existingService.get().setName(serviceDTO.getName());
            existingService.get().setDescription(serviceDTO.getDescription());
            existingService.get().setType(serviceDTO.getType());
            Service updatedService = serviceRepository.save(existingService.get());
            return serviceConverter.convertToDTO(updatedService);
        } else {
            log.error("Service with id: " + id + " not found.");
            throw new NotFoundException("Service not found.");
        }
    }

    @Override
    public void deleteServiceById(Long id) {
        serviceRepository.deleteById(id);
    }
}