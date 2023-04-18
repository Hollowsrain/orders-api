package lt.bite.orders.service;

import lt.bite.orders.model.dto.MSISDNDTO;

import java.util.Set;

public interface MSISDNService {

    Set<MSISDNDTO> getMSISDNByAccountId(Long accountId);

    Set<MSISDNDTO> getActiveMsisdnByAccountId(Long accountId);

    MSISDNDTO getMSISDNById(Long id);

    MSISDNDTO createMSISDN(Long accountId, MSISDNDTO msisdnDTO);

    MSISDNDTO updateMSISDN(Long id, MSISDNDTO msisdnDTO);

    void deleteMSISDNById(Long id);
}
