package lt.bite.orders.model.dto.converter;

import lt.bite.orders.model.dto.AccountDTO;
import lt.bite.orders.model.dto.MSISDNDTO;
import lt.bite.orders.model.entity.Account;
import lt.bite.orders.model.entity.MSISDN;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountConverterTest {

    @InjectMocks
    private AccountConverter accountConverter;

    @Mock
    private MSISDNConverter msisdnConverter;

    @Test
    public void testConvertToDTO() {
        ZonedDateTime time = ZonedDateTime.now();
        Account account = Account.builder()
                .id(1L)
                .name("John Doe")
                .address("123 Main St")
                .msisdns(new HashSet<>())
                .customer(null)
                .build();
        MSISDN msisdn1 = MSISDN.builder()
                .id(1L)
                .activeFrom(time)
                .activeTo(time)
                .account(account)
                .build();
        MSISDN msisdn2 = MSISDN.builder()
                .id(2L)
                .activeFrom(time)
                .activeTo(time)
                .account(account)
                .build();
        Set<MSISDN> msisdns = new HashSet<>();
        msisdns.add(msisdn1);
        msisdns.add(msisdn2);
        account.setMsisdns(msisdns);


        when(msisdnConverter.convertToDTO(msisdn1)).thenReturn(MSISDNDTO.builder()
                .id(1L)
                .activeFrom(time)
                .activeTo(time)
                .build());
        when(msisdnConverter.convertToDTO(msisdn2)).thenReturn(MSISDNDTO.builder()
                .id(2L)
                .activeFrom(time)
                .activeTo(time)
                .build());

        AccountDTO accountDTO = accountConverter.convertToDTO(account);

        assertNotNull(accountDTO);
        assertEquals(1L, accountDTO.getId().longValue());
        assertEquals("John Doe", accountDTO.getName());
        assertEquals("123 Main St", accountDTO.getAddress());
        assertEquals(2, accountDTO.getMsisdns().size());
    }

    @Test
    public void testConvertToEntity() {
        AccountDTO accountDTO = AccountDTO.builder()
                .id(1L)
                .name("John Doe")
                .address("123 Main St")
                .msisdns(new HashSet<>())
                .customer(null)
                .build();

        Account account = accountConverter.convertToEntity(accountDTO);

        assertNotNull(account);
        assertEquals(1L, account.getId().longValue());
        assertEquals("John Doe", account.getName());
        assertEquals("123 Main St", account.getAddress());
        assertNotNull(account.getMsisdns());
        assertTrue(account.getMsisdns().isEmpty());
    }
}