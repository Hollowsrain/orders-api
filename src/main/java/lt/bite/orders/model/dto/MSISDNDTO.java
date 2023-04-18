package lt.bite.orders.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MSISDNDTO {

    private Long id;

    @PastOrPresent(message = "Incorrect date, date set in future.")
    private ZonedDateTime activeFrom;

    @FutureOrPresent(message = "Incorrect date, date set in past.")
    private ZonedDateTime activeTo;

    @JsonManagedReference("Msisdn")
    private Set<OrderedServiceDTO> orderedServices;

    @JsonBackReference("Account")
    private AccountDTO account;
}
