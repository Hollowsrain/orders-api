package lt.bite.orders.controller;

import lt.bite.orders.model.dto.AccountDTO;
import lt.bite.orders.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @Test
    public void testGetCustomerAccounts() {
        Long customerId = 1L;
        Set<AccountDTO> accountDTOS = new HashSet<>();
        when(accountService.getCustomerAccountsByCustomerId(customerId)).thenReturn(accountDTOS);

        ResponseEntity<Set<AccountDTO>> responseEntity = accountController.getCustomerAccounts(customerId);

        verify(accountService, times(1)).getCustomerAccountsByCustomerId(customerId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(accountDTOS, responseEntity.getBody());
    }

    @Test
    public void testGetAccount() {
        Long accountId = 1L;
        AccountDTO accountDTO = new AccountDTO();
        when(accountService.getAccountById(accountId)).thenReturn(accountDTO);

        ResponseEntity<AccountDTO> responseEntity = accountController.getAccount(accountId);

        verify(accountService, times(1)).getAccountById(accountId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(accountDTO, responseEntity.getBody());
    }

    @Test
    public void testCreateAccount() {
        Long customerId = 1L;
        AccountDTO accountDTO = new AccountDTO();
        AccountDTO createdAccountDTO = new AccountDTO();
        when(accountService.createAccount(customerId, accountDTO)).thenReturn(createdAccountDTO);

        ResponseEntity<AccountDTO> responseEntity = accountController.createAccount(customerId, accountDTO);

        verify(accountService, times(1)).createAccount(customerId, accountDTO);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(createdAccountDTO, responseEntity.getBody());
    }

    @Test
    public void testUpdateAccount() {
        Long accountId = 1L;
        AccountDTO accountDTO = new AccountDTO();
        AccountDTO updatedAccountDTO = new AccountDTO();
        when(accountService.updateAccount(accountId, accountDTO)).thenReturn(updatedAccountDTO);

        ResponseEntity<AccountDTO> responseEntity = accountController.updateAccount(accountId, accountDTO);

        verify(accountService, times(1)).updateAccount(accountId, accountDTO);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedAccountDTO, responseEntity.getBody());
    }

    @Test
    public void testDeleteAccount() {
        Long accountId = 1L;
        ResponseEntity<Void> responseEntity = accountController.deleteAccount(accountId);

        verify(accountService, times(1)).deleteAccountById(accountId);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

}