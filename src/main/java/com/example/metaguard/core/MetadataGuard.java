
package com.example.metaguard.core;

import com.example.metaguard.exception.MetaViolationException;

public interface MetadataGuard {

    enum Mode { MONITOR, INTERCEPT }

    <T> void validate(T dto, Mode mode, String source) throws MetaViolationException;
}
