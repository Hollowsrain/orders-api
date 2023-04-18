package lt.bite.orders.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.bite.orders.exception.NotFoundException;
import lt.bite.orders.model.dto.AccountDTO;
import lt.bite.orders.model.dto.MSISDNDTO;
import lt.bite.orders.model.dto.converter.AccountConverter;
import lt.bite.orders.model.dto.converter.MSISDNConverter;
import lt.bite.orders.model.entity.Account;
import lt.bite.orders.model.entity.MSISDN;
import lt.bite.orders.repository.AccountRepository;
import lt.bite.orders.repository.MSISDNRepository;
import lt.bite.orders.service.MSISDNService;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MSISDNServiceImpl implements MSISDNService {

    private final MSISDNRepository msisdnRepository;
    private final AccountRepository accountRepository;
    private final AccountConverter accountConverter;
    private final MSISDNConverter msisdnConverter;

    @Override
    public Set<MSISDNDTO> getMSISDNByAccountId(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            AccountDTO accountDTO = accountConverter.convertToDTO(account.get());
            return accountDTO.getMsisdns();
        } else {
            log.error("Account with id: " + accountId + " not found.");
            throw new NotFoundException("Account not found.");
        }
    }

    @Override
    public Set<MSISDNDTO> getActiveMsisdnByAccountId(Long accountId) {
        return getMSISDNByAccountId(accountId).stream()
                .filter(msisdnDTO -> msisdnDTO.getActiveTo().isAfter(ZonedDateTime.now()))
                .collect(Collectors.toSet());
    }

    @Override
    public MSISDNDTO getMSISDNById(Long id) {
        Optional<MSISDN> msisdn = msisdnRepository.findById(id);

        if (msisdn.isPresent()) {
            return msisdnConverter.convertToDTO(msisdn.get());
        } else {
            log.error("MSISDN with id: " + id + " not found.");
            throw new NotFoundException("MSISDN not found.");
        }
    }

    @Override
    public MSISDNDTO createMSISDN(Long accountId, MSISDNDTO msisdnDTO) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            MSISDN msisdn = msisdnConverter.convertToEntity(msisdnDTO);

            msisdn.setAccount(account.get());
            MSISDN savedMsisdn = msisdnRepository.save(msisdn);

            return msisdnConverter.convertToDTO(savedMsisdn);
        } else {
            log.error("Failed creating MSISDN for account with Id: " + accountId);
            throw new NotFoundException("Account not found.");
        }
    }

    @Override
    public MSISDNDTO updateMSISDN(Long id, MSISDNDTO msisdnDTO) {
        Optional<MSISDN> existingMSISDN = msisdnRepository.findById(id);

        if (existingMSISDN.isPresent()) {
            existingMSISDN.get().setActiveFrom(msisdnDTO.getActiveFrom());
            existingMSISDN.get().setActiveTo(msisdnDTO.getActiveTo());

            MSISDN savedMsisdn = msisdnRepository.save(existingMSISDN.get());
            return msisdnConverter.convertToDTO(savedMsisdn);
        } else {
            log.error("MSISDN with id: " + id + " not found.");
            throw new NotFoundException("MSISDN not found.");
        }
    }

    @Override
    public void deleteMSISDNById(Long id) {
        msisdnRepository.deleteById(id);
    }
}