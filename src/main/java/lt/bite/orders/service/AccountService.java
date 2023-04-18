package lt.bite.orders.service;

import lt.bite.orders.model.dto.AccountDTO;

import java.util.Set;

public interface AccountService {

    Set<AccountDTO> getCustomerAccountsByCustomerId(Long id);

    AccountDTO getAccountById(Long id);

    AccountDTO createAccount(Long customerId, AccountDTO accountDTO);

    AccountDTO updateAccount(Long id, AccountDTO accountDTO);

    void deleteAccountById(Long id);
}