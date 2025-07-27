
package com.example.metaguard;

import com.example.metaguard.demo.AccountDto;
import com.example.metaguard.demo.AccountService;
import com.example.metaguard.exception.MetaViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MetaWatchAspectIT {

    @Autowired
    private AccountService service;

    @Test
    void aspect_blocks_when_intercept() {
        AccountDto bad = new AccountDto();
        bad.setSource("BAD_API");
        assertThatThrownBy(() -> service.createAccount(bad))
                .isInstanceOf(MetaViolationException.class);
    }
}
