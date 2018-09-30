package com.rxt.common.commonUnit;

import java.io.Serializable;

/**
 * 传输对象
 */
public class RPCRequest implements Serializable {

    private static final long serialVersionUID = 2631587073672825177L;
    private String className;
    private String methodName;
    private Object[] parameters;
    private String version;    //版本号

    public RPCRequest() {
    }

    public RPCRequest(String className, String methodName, Object[] parameters, String version) {
        this.className = className;
        this.methodName = methodName;
        this.parameters = parameters;
        this.version = version;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
