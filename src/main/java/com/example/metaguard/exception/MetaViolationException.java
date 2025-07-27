
package com.example.metaguard.exception;

import java.util.*;

public final class MetaViolationException extends RuntimeException {
    private final List<Violation> violations;
    public MetaViolationException(String message, Collection<Violation> vio) {
        super(message);
        this.violations = Collections.unmodifiableList(new ArrayList<Violation>(vio));
    }
    public List<Violation> getViolations() { return violations; }
}
