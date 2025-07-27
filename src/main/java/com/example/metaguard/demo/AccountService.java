
package com.example.metaguard.demo;

import com.example.metaguard.aop.MetaWatch;
import com.example.metaguard.core.MetadataGuard;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @MetaWatch(mode = MetadataGuard.Mode.INTERCEPT, source = "ACCOUNT_API")
    public void createAccount(AccountDto dto) {
        // Business logic placeholder
    }
}
