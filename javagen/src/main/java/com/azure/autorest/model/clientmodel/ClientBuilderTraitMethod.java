package com.azure.autorest.model.clientmodel;

import com.azure.autorest.model.javamodel.JavaBlock;

import java.util.function.Consumer;

public class ClientBuilderTraitMethod {
    private String methodName;
    private ClassType methodParamType;
    private String methodParamName;
    private String documentation;
    private ServiceClientProperty property;
    private Consumer<JavaBlock> methodImpl;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public ClassType getMethodParamType() {
        return methodParamType;
    }

    public void setMethodParamType(ClassType methodParamType) {
        this.methodParamType = methodParamType;
    }

    public String getMethodParamName() {
        return methodParamName;
    }

    public void setMethodParamName(String methodParamName) {
        this.methodParamName = methodParamName;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public ServiceClientProperty getProperty() {
        return property;
    }

    public void setProperty(ServiceClientProperty property) {
        this.property = property;
    }

    public Consumer<JavaBlock> getMethodImpl() {
        return methodImpl;
    }

    public void setMethodImpl(Consumer<JavaBlock> methodImpl) {
        this.methodImpl = methodImpl;
    }
}
