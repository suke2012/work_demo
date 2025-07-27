
package com.example.metaguard.core;

import com.example.metaguard.exception.Violation;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class RegexChecker implements RuleChecker {

    @Override
    public boolean supports(MetaRule rule) { return rule.getRegex() != null; }

    @Override
    public Optional<Violation> check(String key, String value, MetaRule rule, String source) {
        if (!value.matches(rule.getRegex())) {
            return Optional.of(new Violation(key, "regex mismatch: " + rule.getRegex(), source));
        }
        return Optional.empty();
    }

    @Override
    public int order() { return 10; }
}
