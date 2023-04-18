package lt.bite.orders.model.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Customer name is required.")
    private String name;

    @NotBlank(message = "Customer surname is required.")
    private String surname;

    private String companyName;

    private String companyCode;

    @NotNull(message = "Customer personal code is required.")
    @Pattern(regexp = "\\d{11}", message = "Personal code must be a 11-digit number")
    private String personalCode;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonManagedReference("Customer")
    private Set<AccountDTO> accounts = new HashSet<>();
}