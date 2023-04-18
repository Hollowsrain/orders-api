package lt.bite.orders.controller;

import lombok.RequiredArgsConstructor;
import lt.bite.orders.model.dto.AccountDTO;
import lt.bite.orders.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping(value = "/customers/{id}/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<AccountDTO>> getCustomerAccounts(@PathVariable Long id) {
        Set<AccountDTO> accountDTOS = accountService.getCustomerAccountsByCustomerId(id);
        return new ResponseEntity<>(accountDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/accounts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDTO> getAccount(@PathVariable Long id) {
        AccountDTO accountDTO = accountService.getAccountById(id);
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/customers/{customerId}/accounts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDTO> createAccount(@PathVariable Long customerId, @RequestBody AccountDTO accountDTO) {
        AccountDTO createdAccount = accountService.createAccount(customerId, accountDTO);
        return new ResponseEntity<>(createdAccount, HttpStatus.OK);
    }

    @PutMapping(value = "/accounts/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable Long id, @RequestBody AccountDTO accountDTO) {
        AccountDTO updatedAccountDTO = accountService.updateAccount(id, accountDTO);
        return new ResponseEntity<>(updatedAccountDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/accounts/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccountById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}