package lt.bite.orders.controller;

import lombok.RequiredArgsConstructor;
import lt.bite.orders.model.dto.MSISDNDTO;
import lt.bite.orders.model.entity.MSISDN;
import lt.bite.orders.service.MSISDNService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class MSISDNController {

    private final MSISDNService MSISDNService;

    @GetMapping(value = "/accounts/{id}/msisdns", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<MSISDNDTO>> getAccountMSISDNs(@PathVariable Long id) {
        Set<MSISDNDTO> msisdnDTOs = MSISDNService.getMSISDNByAccountId(id);
        return new ResponseEntity<>(msisdnDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/msisdns/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MSISDNDTO> getMSISDN(@PathVariable Long id) {
        MSISDNDTO msisdnDTO = MSISDNService.getMSISDNById(id);
        return new ResponseEntity<>(msisdnDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/accounts/{accountId}/msisdns", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MSISDNDTO> createMSISDN(@PathVariable Long accountId, @RequestBody MSISDNDTO msisdnDTO) {
        MSISDNDTO createdMsisdnDTO = MSISDNService.createMSISDN(accountId, msisdnDTO);
        return new ResponseEntity<>(createdMsisdnDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/msisdns/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MSISDNDTO> updateMSISDN(@PathVariable Long id, @RequestBody MSISDNDTO msisdnDTO) {
        MSISDNDTO updatedMsisdnDTO = MSISDNService.updateMSISDN(id, msisdnDTO);
        return new ResponseEntity<>(updatedMsisdnDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/msisdns/{id}")
    public ResponseEntity<Void> deleteMSISDN(@PathVariable Long id) {
        MSISDNService.deleteMSISDNById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
