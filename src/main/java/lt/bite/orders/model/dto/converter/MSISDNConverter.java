package lt.bite.orders.model.dto.converter;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import lt.bite.orders.model.dto.MSISDNDTO;
import lt.bite.orders.model.dto.OrderedServiceDTO;
import lt.bite.orders.model.entity.MSISDN;
import lt.bite.orders.model.entity.OrderedService;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MSISDNConverter {

    private final OrderedServiceConverter orderedServiceConverter;

    @Synchronized
    public MSISDNDTO convertToDTO(MSISDN msisdn) {
        Set<OrderedServiceDTO> orderedServiceDTOs = new HashSet<>();
        for (OrderedService orderedService : msisdn.getOrderedServices()) {
            orderedServiceDTOs.add(orderedServiceConverter.convertToDTO(orderedService));
        }
        return MSISDNDTO.builder()
                .id(msisdn.getId())
                .activeFrom(msisdn.getActiveFrom())
                .activeTo(msisdn.getActiveTo())
                .orderedServices(orderedServiceDTOs)
                .account(null)
                .build();
    }

    @Synchronized
    public MSISDN convertToEntity(MSISDNDTO msisdnDTO) {
        return MSISDN.builder()
                .id(msisdnDTO.getId())
                .activeFrom(msisdnDTO.getActiveFrom())
                .activeTo(msisdnDTO.getActiveTo())
                .orderedServices(new HashSet<>())
                .account(null)
                .build();
    }
}