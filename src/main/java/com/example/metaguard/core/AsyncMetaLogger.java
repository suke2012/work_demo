
package com.example.metaguard.core;

import com.example.metaguard.exception.Violation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class AsyncMetaLogger implements MetaLogger {

    private static final Logger log = LoggerFactory.getLogger(AsyncMetaLogger.class);
    private final ExecutorService pool = Executors.newSingleThreadExecutor();

    @Override
    public void publish(final String source, final List<Violation> violations) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                log.warn("MONITOR: source={}, violations={}", source, violations);
            }
        });
    }

    @PreDestroy
    public void shutdown() {
        pool.shutdown();
    }
}
