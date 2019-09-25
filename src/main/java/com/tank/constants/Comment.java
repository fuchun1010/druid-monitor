package com.tank.constants;

import java.lang.annotation.*;

/**
 * @author tank198435163.com
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface Comment {

  String desc() default "";
}
