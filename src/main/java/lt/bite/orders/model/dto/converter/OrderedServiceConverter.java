package lt.bite.orders.model.dto.converter;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import lt.bite.orders.model.dto.OrderedServiceDTO;
import lt.bite.orders.model.entity.OrderedService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderedServiceConverter {

    private final ServiceConverter serviceConverter;

    @Synchronized
    public OrderedServiceDTO convertToDTO(OrderedService orderedService) {
        return OrderedServiceDTO.builder()
                .id(orderedService.getId())
                .name(orderedService.getName())
                .activeFrom(orderedService.getActiveFrom())
                .activeTo(orderedService.getActiveTo())
                .service(serviceConverter.convertToDTO(orderedService.getService()))
                .msisdn(null)
                .build();
    }

    @Synchronized
    public OrderedService convertToEntity(OrderedServiceDTO orderedServiceDTO) {
        return OrderedService.builder()
                .id(orderedServiceDTO.getId())
                .name(orderedServiceDTO.getName())
                .activeFrom(orderedServiceDTO.getActiveFrom())
                .activeTo(orderedServiceDTO.getActiveTo())
                .service(serviceConverter.convertToEntity(orderedServiceDTO.getService()))
                .msisdn(null)
                .build();
    }
}