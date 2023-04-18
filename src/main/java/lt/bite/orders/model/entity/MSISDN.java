package lt.bite.orders.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "msisdn")
public class MSISDN {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "active_from", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private ZonedDateTime activeFrom;

    @Column(name = "active_to", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private ZonedDateTime activeTo;

    @OneToMany(mappedBy = "msisdn", cascade = CascadeType.ALL)
    private Set<OrderedService> orderedServices;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}