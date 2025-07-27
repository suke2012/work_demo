
package com.example.metaguard.core;

import com.example.metaguard.exception.Violation;
import java.util.List;

public interface MetaLogger {
    void publish(String source, List<Violation> violations);
}
