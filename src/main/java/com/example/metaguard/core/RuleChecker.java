
package com.example.metaguard.core;

import com.example.metaguard.exception.Violation;
import java.util.Optional;

public interface RuleChecker extends Comparable<RuleChecker> {

    boolean supports(MetaRule rule);
    Optional<Violation> check(String key, String value, MetaRule rule, String source);
    default int order() { return 0; }
    @Override
    default int compareTo(RuleChecker o) { return Integer.compare(order(), o.order()); }
}
