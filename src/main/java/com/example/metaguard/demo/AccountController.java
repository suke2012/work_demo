
package com.example.metaguard.demo;

import com.example.metaguard.exception.MetaViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService service;
    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AccountDto dto) {
        try {
            service.createAccount(dto);
            return ResponseEntity.ok("OK");
        } catch (MetaViolationException e) {
            return ResponseEntity.badRequest().body(e.getViolations());
        }
    }
}
