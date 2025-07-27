
package com.example.metaguard.aop;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MetaField {
    String key() default "";
}
