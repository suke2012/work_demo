
package com.example.metaguard.core;

import java.util.Map;

public interface KvCollector {
    Map<String, String> collect(Object root);
}
