package com.user.annotation;

import java.lang.annotation.*;

/**
 * 幂等性注解
 *
 * @author dingzi
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoIdempotent {

}
