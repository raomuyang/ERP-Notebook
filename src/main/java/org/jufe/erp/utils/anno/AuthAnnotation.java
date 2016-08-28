package org.jufe.erp.utils.anno;

/**
 * Created by Raomengnan on 2016/5/30.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthAnnotation {
        boolean auth() default false;
}
