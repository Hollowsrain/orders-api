package lt.bite.orders.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderedServiceDTO {

    private Long id;

    @NotBlank(message = "Ordered service name is required.")
    private String name;

    @PastOrPresent(message = "Incorrect date, date set in future.")
    private ZonedDateTime activeFrom;

    @FutureOrPresent(message = "Incorrect date, date set in past.")
    private ZonedDateTime activeTo;

    private ServiceDTO service;

    @JsonBackReference("Msisdn")
    private MSISDNDTO msisdn;
}