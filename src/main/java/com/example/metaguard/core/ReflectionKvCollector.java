
package com.example.metaguard.core;

import com.example.metaguard.aop.MetaField;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;
import java.util.*;

@Component
public class ReflectionKvCollector implements KvCollector {

    private static final Set<Class<?>> LEAF = Collections.unmodifiableSet(
            new HashSet<Class<?>>(Arrays.asList(String.class, Boolean.class)));

    private static boolean isLeaf(Object obj) {
        if (obj == null) return true;
        Class<?> clazz = obj.getClass();
        return LEAF.contains(clazz)
                || clazz.isPrimitive()
                || Number.class.isAssignableFrom(clazz)
                || clazz.equals(Character.class);
    }

    @Override
    public Map<String, String> collect(Object root) {
        Map<String, String> sink = new HashMap<String, String>();
        if (root == null) {
            return sink;
        }
        Deque<Object> stack = new ArrayDeque<Object>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Object obj = stack.pop();
            if (obj == null) {
                continue;
            }
            if (isLeaf(obj)) {
                continue;
            }

            if (obj instanceof Map) {
                Map map = (Map) obj;
                for (Object k : map.keySet()) {
                    Object v = map.get(k);
                    if (v != null) {
                        sink.put(String.valueOf(k), String.valueOf(v));
                    }
                }
                continue;
            }

            for (Field f : obj.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                try {
                    Object val = f.get(obj);
                    if (f.isAnnotationPresent(MetaField.class)) {
                        MetaField mf = f.getAnnotation(MetaField.class);
                        String key = mf.key().isEmpty() ? f.getName() : mf.key();
                        if (val != null) {
                            sink.put(key, String.valueOf(val));
                        }
                    } else {
                        if (val != null) {
                            stack.push(val);
                        }
                    }
                } catch (IllegalAccessException e) {
                    // ignore
                }
            }
        }
        return sink;
    }
}
