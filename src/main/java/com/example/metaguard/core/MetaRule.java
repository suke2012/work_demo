
package com.example.metaguard.core;

import java.util.Set;

public class MetaRule {
    private final String key;
    private final Set<String> allowedSources;
    private final String regex;

    public MetaRule(String key, Set<String> allowedSources, String regex) {
        this.key = key;
        this.allowedSources = allowedSources;
        this.regex = regex;
    }

    public String getKey() { return key; }
    public Set<String> getAllowedSources() { return allowedSources; }
    public String getRegex() { return regex; }
}
