
package com.example.metaguard.aop;

import com.example.metaguard.core.MetadataGuard;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MetaWatch {
    MetadataGuard.Mode mode() default MetadataGuard.Mode.MONITOR;
    String source() default "";
}
