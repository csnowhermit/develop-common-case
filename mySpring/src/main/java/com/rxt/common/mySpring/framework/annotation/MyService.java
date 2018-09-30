/**
 * FileName: MyService
 * Author:   Ren Xiaotian
 * Date:     2018/7/21 7:41
 */

package com.rxt.common.mySpring.framework.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyService {
    String value() default "";
}
