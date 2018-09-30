/**
 * FileName: MyRequestParam
 * Author:   Ren Xiaotian
 * Date:     2018/7/21 8:12
 */

package com.rxt.common.mySpring.framework.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestParam {
    String value() default "";
}
