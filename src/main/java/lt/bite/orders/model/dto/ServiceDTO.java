package lt.bite.orders.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ServiceDTO {

    private Long id;

    @NotBlank(message = "Service name is required.")
    private String name;

    @NotBlank(message = "Service type is required.")
    private String type;

    private String description;
}