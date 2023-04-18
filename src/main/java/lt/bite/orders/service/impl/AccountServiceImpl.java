package lt.bite.orders.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.bite.orders.exception.NotFoundException;
import lt.bite.orders.model.dto.AccountDTO;
import lt.bite.orders.model.dto.CustomerDTO;
import lt.bite.orders.model.dto.converter.AccountConverter;
import lt.bite.orders.model.dto.converter.CustomerConverter;
import lt.bite.orders.model.entity.Account;
import lt.bite.orders.model.entity.Customer;
import lt.bite.orders.repository.AccountRepository;
import lt.bite.orders.repository.CustomerRepository;
import lt.bite.orders.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;
    private final AccountConverter accountConverter;

    @Override
    public Set<AccountDTO> getCustomerAccountsByCustomerId(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            CustomerDTO customerDTO = customerConverter.convertToDTO(customer.get());

            return customerDTO.getAccounts();
        } else {
            log.error("Customer with id: " + id + " not found.");
            throw new NotFoundException("Customer not found.");
        }
    }

    @Override
    public AccountDTO getAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);

        if (account.isPresent()) {
            return accountConverter.convertToDTO(account.get());
        } else {
            log.error("Account with id: " + id + " not found.");
            throw new NotFoundException("Account not found.");
        }

    }

    @Override
    public AccountDTO createAccount(Long customerId, AccountDTO accountDTO) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            Account account = accountConverter.convertToEntity(accountDTO);

            account.setCustomer(customer.get());
            Account savedAccount = accountRepository.save(account);

            return accountConverter.convertToDTO(savedAccount);
        } else {
            log.error("Failed creating account for customer with Id: " + customerId);
            throw new NotFoundException("Customer not found.");
        }
    }

    @Override
    public AccountDTO updateAccount(Long id, AccountDTO accountDTO) {
        Optional<Account> existingAccount = accountRepository.findById(id);

        if (existingAccount.isPresent()) {
            existingAccount.get().setName(accountDTO.getName());
            existingAccount.get().setAddress(accountDTO.getAddress());

            accountRepository.save(existingAccount.get());
            return accountConverter.convertToDTO(existingAccount.get());
        } else {
            log.error("Account with id: " + id + " not found.");
            throw new NotFoundException("Account not found.");
        }
    }

    @Override
    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }
}