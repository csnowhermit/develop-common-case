/**
 * FileName: RPCAnnotation
 * Author:   Ren Xiaotian
 * Date:     2018/6/24 11:41
 */

package com.rxt.common.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RPCAnnotation {

    /**
     * 对外发布服务的接口地址
     * @return
     */
    Class<?> value();

    String version() default "";
}
