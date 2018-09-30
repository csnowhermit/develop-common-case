/**
 * FileName: RPCRequest
 * Author:   Ren Xiaotian
 * Date:     2018/9/30 16:29
 */

package com.rxt.common.commonUnit;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 传输对象
 */
public class RPCRequest implements Serializable {

    private static final long serialVersionUID = 5239098691535848407L;
    private String className;
    private String methodName;
    private Object[] parameters;

    public RPCRequest() {
    }

    public RPCRequest(String className, String methodName, Object[] parameters) {
        this.className = className;
        this.methodName = methodName;
        this.parameters = parameters;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "RPCRequest{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parameters=" + Arrays.toString(parameters) +
                '}';
    }
}
