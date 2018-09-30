/**
 * FileName: MyRequestMapping
 * Author:   Ren Xiaotian
 * Date:     2018/7/21 8:15
 */

package com.rxt.common.mySpring.framework.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestMapping {
    String value() default "";
}
