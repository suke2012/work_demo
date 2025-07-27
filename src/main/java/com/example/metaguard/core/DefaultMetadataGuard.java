
package com.example.metaguard.core;

import com.example.metaguard.exception.MetaViolationException;
import com.example.metaguard.exception.Violation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DefaultMetadataGuard implements MetadataGuard {

    private final KvCollector collector;
    private final MetadataRegistry registry;
    private final List<RuleChecker> checkers;
    private final MetaLogger logger;

    @Autowired
    public DefaultMetadataGuard(KvCollector collector,
                                MetadataRegistry registry,
                                List<RuleChecker> checkers,
                                MetaLogger logger) {
        this.collector = collector;
        this.registry = registry;
        this.checkers = new ArrayList<RuleChecker>(checkers);
        Collections.sort(this.checkers);
        this.logger = logger;
    }

    @Override
    public <T> void validate(T dto, Mode mode, String source) throws MetaViolationException {
        Map<String,String> kvs = collector.collect(dto);
        List<Violation> violations = new ArrayList<Violation>();

        for (Map.Entry<String,String> e : kvs.entrySet()) {
            MetaRule rule = registry.lookup(e.getKey());
            if (rule == null) continue;
            for (RuleChecker c : checkers) {
                if (c.supports(rule)) {
                    Optional<Violation> v = c.check(e.getKey(), e.getValue(), rule, source);
                    if (v.isPresent()) violations.add(v.get());
                }
            }
        }

        if (!violations.isEmpty()) {
            if (mode == Mode.MONITOR) {
                logger.publish(source, violations);
            } else {
                throw new MetaViolationException("Metadata violation", violations);
            }
        }
    }
}
