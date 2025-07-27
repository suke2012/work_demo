
package com.example.metaguard;

import com.example.metaguard.core.MetadataGuard;
import com.example.metaguard.demo.AccountDto;
import com.example.metaguard.demo.CreditAccount;
import com.example.metaguard.exception.MetaViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MetadataGuardTest {

    @Autowired
    private MetadataGuard guard;

    @Test
    void monitor_ok() {
        AccountDto dto = goodDto();
        assertThatCode(() -> guard.validate(dto, MetadataGuard.Mode.MONITOR, "ACCOUNT_API"))
                .doesNotThrowAnyException();
    }

    @Test
    void intercept_violate() {
        AccountDto dto = badDto();
        assertThatThrownBy(() -> guard.validate(dto, MetadataGuard.Mode.INTERCEPT, "BAD_API"))
                .isInstanceOf(MetaViolationException.class);
    }

    private AccountDto goodDto() {
        AccountDto dto = new AccountDto();
        dto.setSource("ACCOUNT_API");
        dto.setKvId("ID123");
        dto.setSysFieldId("FIELD1");
        Map<String,String> ext = new HashMap<String,String>();
        ext.put("amount","123");
        dto.setExtInfo(ext);
        CreditAccount ca = new CreditAccount();
        ca.setExtInfo(Collections.<String,String>emptyMap());
        dto.setCreditAccount(ca);
        return dto;
    }

    private AccountDto badDto() {
        AccountDto dto = new AccountDto();
        dto.setSource("BAD_API");
        dto.setKvId("ID 123");
        Map<String,String> ext = new HashMap<String,String>();
        ext.put("amount","abc");
        dto.setExtInfo(ext);
        return dto;
    }
}
