
package com.example.metaguard.core;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CachedRegistry implements MetadataRegistry {

    private static final long TTL_MS = TimeUnit.MINUTES.toMillis(5);
    private final AtomicReference<Map<String, MetaRule>> cache =
            new AtomicReference<Map<String, MetaRule>>(Collections.<String, MetaRule>emptyMap());
    private volatile long lastRefresh = 0L;

    @Override
    public MetaRule lookup(String key) {
        refreshIfNeeded();
        return cache.get().get(key);
    }

    private void refreshIfNeeded() {
        long now = System.currentTimeMillis();
        if (now - lastRefresh > TTL_MS) {
            synchronized (this) {
                if (now - lastRefresh > TTL_MS) {
                    cache.set(fetchRules());
                    lastRefresh = now;
                }
            }
        }
    }

    private Map<String, MetaRule> fetchRules() {
        Map<String, MetaRule> m = new HashMap<String, MetaRule>();
        m.put("from", new MetaRule("from",
                new HashSet<String>(Arrays.asList("ACCOUNT_API")), null));
        m.put("amount", new MetaRule("amount", null, "^[0-9]+$"));
        m.put("kvId", new MetaRule("kvId", null, "^[A-Za-z0-9_-]+$"));
        return m;
    }
}
