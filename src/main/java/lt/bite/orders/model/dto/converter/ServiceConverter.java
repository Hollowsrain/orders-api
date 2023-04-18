package lt.bite.orders.model.dto.converter;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import lt.bite.orders.model.dto.ServiceDTO;
import lt.bite.orders.model.entity.Service;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceConverter {

    @Synchronized
    public ServiceDTO convertToDTO(Service service) {
        return ServiceDTO.builder()
                .id(service.getId())
                .name(service.getName())
                .description(service.getDescription())
                .build();
    }

    @Synchronized
    public Service convertToEntity(ServiceDTO serviceDTO) {
        return Service.builder()
                .id(serviceDTO.getId())
                .name(serviceDTO.getName())
                .description(serviceDTO.getDescription())
                .build();
    }
}