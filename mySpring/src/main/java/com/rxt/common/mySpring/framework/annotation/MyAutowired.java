/**
 * FileName: MyAutowired
 * Author:   Ren Xiaotian
 * Date:     2018/7/21 7:39
 */

package com.rxt.common.mySpring.framework.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAutowired {
    String value() default "";
}

