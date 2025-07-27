
package com.example.metaguard.aop;

import com.example.metaguard.core.MetadataGuard;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MetaWatchAspect {

    private final MetadataGuard guard;

    @Autowired
    public MetaWatchAspect(MetadataGuard guard) {
        this.guard = guard;
    }

    @Around("@annotation(watch)")
    public Object around(ProceedingJoinPoint pjp, MetaWatch watch) throws Throwable {
        Object dto = pjp.getArgs()[0];
        guard.validate(dto, watch.mode(), watch.source());
        return pjp.proceed();
    }
}
