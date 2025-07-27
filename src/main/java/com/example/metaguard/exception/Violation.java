
package com.example.metaguard.exception;

public final class Violation {
    private final String key;
    private final String reason;
    private final String source;

    public Violation(String key, String reason, String source) {
        this.key = key;
        this.reason = reason;
        this.source = source;
    }

    public String getKey() { return key; }
    public String getReason() { return reason; }
    public String getSource() { return source; }

    @Override
    public String toString() {
        return "Violation{" +
                "key='" + key + '\'' +
                ", reason='" + reason + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
