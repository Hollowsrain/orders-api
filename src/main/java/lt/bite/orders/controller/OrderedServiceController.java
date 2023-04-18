package lt.bite.orders.controller;

import lombok.RequiredArgsConstructor;
import lt.bite.orders.model.dto.OrderedServiceDTO;
import lt.bite.orders.model.entity.OrderedService;
import lt.bite.orders.service.OrderedServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class OrderedServiceController {

    private final OrderedServiceService orderedServiceService;

    @GetMapping(value = "/msissdn/{msisdnId}/ordered-services", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<OrderedServiceDTO>> getOrderedServicesByMSISDNId(@PathVariable Long msisdnId) {
        Set<OrderedServiceDTO> orderedServiceDTOs = orderedServiceService.getOrderedServiceByMSISDNId(msisdnId);
        return new ResponseEntity<>(orderedServiceDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/ordered-services/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderedServiceDTO> getOrderedServiceById(@PathVariable Long id) {
        OrderedServiceDTO orderedServiceDTO = orderedServiceService.getOrderedServiceById(id);
        return new ResponseEntity<>(orderedServiceDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/msisdns/{msisdnId}/ordered-services", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderedServiceDTO> createOrderedService(@PathVariable Long msisdnId, @RequestBody OrderedServiceDTO orderedServiceDTO) {
        OrderedServiceDTO createdOrderedServiceDTO = orderedServiceService.createOrderedService(msisdnId, orderedServiceDTO);
        return new ResponseEntity<>(createdOrderedServiceDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/ordered-services/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderedServiceDTO> updateOrderedService(@PathVariable Long id, @RequestBody OrderedServiceDTO orderedServiceDTO) {
        OrderedServiceDTO updatedOrderedServiceDTO = orderedServiceService.updateOrderedService(id, orderedServiceDTO);
        return new ResponseEntity<>(updatedOrderedServiceDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/ordered-services/{id}")
    public ResponseEntity<Boolean> deleteOrderedServiceById(@PathVariable Long id) {
        orderedServiceService.deleteOrderedServiceById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}