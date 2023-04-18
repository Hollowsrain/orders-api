package lt.bite.orders.controller;

import lombok.RequiredArgsConstructor;
import lt.bite.orders.model.dto.ServiceDTO;
import lt.bite.orders.model.entity.Service;
import lt.bite.orders.service.ServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceService serviceService;

    @GetMapping(value = "/services", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ServiceDTO>> getServices() {
        Set<ServiceDTO> serviceDTOs = serviceService.getServices();
        return new ResponseEntity<>(serviceDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/services/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceDTO> getService(@PathVariable Long id) {
        ServiceDTO serviceDTO = serviceService.getServiceById(id);
        return new ResponseEntity<>(serviceDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/services", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceDTO> createService(@RequestBody ServiceDTO serviceDTO) {
        ServiceDTO createdServiceDTO = serviceService.createService(serviceDTO);
        return new ResponseEntity<>(createdServiceDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/services/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceDTO> updateService(@PathVariable Long id, @RequestBody ServiceDTO serviceDTO) {
        ServiceDTO updatedServiceDTO = serviceService.updateService(id, serviceDTO);
        return new ResponseEntity<>(updatedServiceDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/services/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        serviceService.deleteServiceById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
