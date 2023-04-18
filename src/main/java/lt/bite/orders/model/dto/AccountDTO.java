package lt.bite.orders.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Account name is required.")
    private String name;

    @NotBlank(message = "Account address is required.")
    private String address;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonManagedReference("Account")
    private Set<MSISDNDTO> msisdns = new HashSet<>();

    @JsonBackReference("Customer")
    private CustomerDTO customer;
}