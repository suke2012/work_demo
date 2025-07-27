
package com.example.metaguard.core;

import com.example.metaguard.exception.Violation;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.Set;

@Component
public class AllowedSourceChecker implements RuleChecker {

    @Override
    public boolean supports(MetaRule rule) {
        return rule.getAllowedSources() != null && !rule.getAllowedSources().isEmpty();
    }

    @Override
    public Optional<Violation> check(String key, String value, MetaRule rule, String source) {
        Set<String> allow = rule.getAllowedSources();
        if (allow != null && !allow.isEmpty() && !allow.contains(source)) {
            return Optional.of(new Violation(key, "source not allowed: " + source, source));
        }
        return Optional.empty();
    }
}
