/**
 * FileName: MyController
 * Author:   Ren Xiaotian
 * Date:     2018/7/21 8:14
 */

package com.rxt.common.mySpring.framework.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyController {
    String value() default "";
}
