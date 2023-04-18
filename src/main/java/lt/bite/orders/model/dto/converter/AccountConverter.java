package lt.bite.orders.model.dto.converter;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import lt.bite.orders.model.dto.AccountDTO;
import lt.bite.orders.model.dto.MSISDNDTO;
import lt.bite.orders.model.entity.Account;
import lt.bite.orders.model.entity.MSISDN;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AccountConverter {

    private final MSISDNConverter msisdnConverter;

    @Synchronized
    public AccountDTO convertToDTO(Account account) {
        Set<MSISDNDTO> msisdnDTOs = new HashSet<>();
        for (MSISDN msisdn : account.getMsisdns()) {
            msisdnDTOs.add(msisdnConverter.convertToDTO(msisdn));
        }
        return AccountDTO.builder()
                .id(account.getId())
                .name(account.getName())
                .address(account.getAddress())
                .msisdns(msisdnDTOs)
                .customer(null)
                .build();
    }

    @Synchronized
    public Account convertToEntity(AccountDTO accountDTO) {
        return Account.builder()
                .id(accountDTO.getId())
                .name(accountDTO.getName())
                .address(accountDTO.getAddress())
                .msisdns(new HashSet<>())
                .customer(null)
                .build();
    }
}