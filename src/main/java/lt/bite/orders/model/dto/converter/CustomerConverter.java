package lt.bite.orders.model.dto.converter;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import lt.bite.orders.model.dto.AccountDTO;
import lt.bite.orders.model.dto.CustomerDTO;
import lt.bite.orders.model.entity.Account;
import lt.bite.orders.model.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CustomerConverter {

    private final AccountConverter accountConverter;

    @Synchronized
    public CustomerDTO convertToDTO(Customer customer) {
        Set<AccountDTO> accountDTOs = new HashSet<>();
        for (Account account : customer.getAccounts()) {
            accountDTOs.add(accountConverter.convertToDTO(account));
        }
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .companyName(customer.getCompanyName())
                .companyCode(customer.getCompanyCode())
                .personalCode(customer.getPersonalCode())
                .accounts(accountDTOs)
                .build();
    }

    @Synchronized
    public Customer convertToEntity(CustomerDTO customerDTO) {
        return Customer.builder()
                .id(customerDTO.getId())
                .name(customerDTO.getName())
                .surname(customerDTO.getSurname())
                .companyName(customerDTO.getCompanyName())
                .companyCode(customerDTO.getCompanyCode())
                .personalCode(customerDTO.getPersonalCode())
                .accounts(new HashSet<>())
                .build();
    }
}