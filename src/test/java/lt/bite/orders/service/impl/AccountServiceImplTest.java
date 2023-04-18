package lt.bite.orders.service.impl;

import lt.bite.orders.exception.NotFoundException;
import lt.bite.orders.model.dto.AccountDTO;
import lt.bite.orders.model.dto.CustomerDTO;
import lt.bite.orders.model.dto.converter.AccountConverter;
import lt.bite.orders.model.dto.converter.CustomerConverter;
import lt.bite.orders.model.entity.Account;
import lt.bite.orders.model.entity.Customer;
import lt.bite.orders.repository.AccountRepository;
import lt.bite.orders.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerConverter customerConverter;

    @Mock
    private AccountConverter accountConverter;

    private Customer customer;
    private Account account;
    private AccountDTO accountDTO;
    private CustomerDTO customerDTO;

    @BeforeEach
    public void setUp() {
        customer = Customer.builder()
                .id(1L)
                .name("John")
                .surname("Snow")
                .companyName("HBO")
                .companyCode("11111")
                .personalCode("98765452111")
                .accounts(new HashSet<>())
                .build();

        customerDTO = CustomerDTO.builder()
                .id(1L)
                .name("John")
                .surname("Snow")
                .companyName("HBO")
                .companyCode("11111")
                .personalCode("98765452111")
                .accounts(new HashSet<>())
                .build();

        account = Account.builder()
                .id(1L)
                .name("John Doe")
                .address("123 Main St")
                .msisdns(new HashSet<>())
                .customer(customer)
                .build();

        accountDTO = AccountDTO.builder()
                .id(1L)
                .name("John Doe")
                .address("123 Main St")
                .msisdns(new HashSet<>())
                .customer(customerDTO)
                .build();
    }

    @Test
    public void testGetCustomerAccountsByCustomerId() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerConverter.convertToDTO(customer)).thenReturn(customerDTO);

        customer.getAccounts().add(account);
        customerDTO.getAccounts().add(accountDTO);

        Set<AccountDTO> result = accountService.getCustomerAccountsByCustomerId(1L);

        assertEquals(customerDTO.getAccounts(), result);
    }

    @Test
    public void testGetCustomerAccountsByCustomerId_CustomerNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> accountService.getCustomerAccountsByCustomerId(1L));
        assertEquals("Customer not found.", exception.getMessage());
    }

    @Test
    public void testGetAccountById() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountConverter.convertToDTO(account)).thenReturn(accountDTO);

        AccountDTO result = accountService.getAccountById(1L);

        assertEquals(accountDTO, result);
    }

    @Test
    public void testGetAccountById_AccountNotFound() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> accountService.getAccountById(1L));
        assertEquals("Account not found.", exception.getMessage());
    }

    @Test
    public void testCreateAccount() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(accountConverter.convertToEntity(accountDTO)).thenReturn(account);
        when(accountRepository.save(account)).thenReturn(account);
        when(accountConverter.convertToDTO(account)).thenReturn(accountDTO);

        AccountDTO result = accountService.createAccount(1L, accountDTO);

        assertEquals(accountDTO, result);
    }

    @Test
    public void testCreateAccount_CustomerNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> accountService.createAccount(1L, accountDTO));
        assertEquals("Customer not found.", exception.getMessage());
    }

    @Test
    public void testUpdateAccount() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(account)).thenReturn(account);
        when(accountConverter.convertToDTO(account)).thenReturn(accountDTO);

        AccountDTO updatedAccountDTO = accountService.updateAccount(1L, accountDTO);

        assertNotNull(updatedAccountDTO);
        assertEquals(accountDTO.getName(), updatedAccountDTO.getName());
        assertEquals(accountDTO.getAddress(), updatedAccountDTO.getAddress());
    }

    @Test
    public void testUpdateAccount_AccountNotFound() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> accountService.updateAccount(1L, accountDTO));
        assertEquals("Account not found.", exception.getMessage());
    }

    @Test
    public void testDeleteAccountById() {
        accountService.deleteAccountById(1L);

        verify(accountRepository, times(1)).deleteById(1L);
    }
}