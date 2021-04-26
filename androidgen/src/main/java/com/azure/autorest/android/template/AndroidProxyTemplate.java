package com.azure.autorest.android.template;

import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.javamodel.JavaInterface;
import com.azure.autorest.template.ProxyTemplate;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.List;

public class AndroidProxyTemplate extends ProxyTemplate {
    private static ProxyTemplate _instance = new AndroidProxyTemplate();

    protected AndroidProxyTemplate() {
    }

    public static ProxyTemplate getInstance() {
        return _instance;
    }

    @Override
    protected void writeUnexpectedExceptions(ProxyMethod restAPIMethod, JavaInterface interfaceBlock) {
        StringBuilder annotationBuilder = new StringBuilder();
        annotationBuilder.append(String.format("UnexpectedResponseExceptionTypes({%n"));
        for (java.util.Map.Entry<ClassType, List<HttpResponseStatus>> exception : restAPIMethod.getUnexpectedResponseExceptionTypes().entrySet()) {
            annotationBuilder.append(String.format("\t@UnexpectedResponseExceptionType(value = %1$s.class, code = {%2$s})%n",
                    exception.getKey(), exception.getValue().stream().map(status -> String.valueOf(status.code())).collect(java.util.stream.Collectors.joining(", "))));
        }
        annotationBuilder.append(String.format("})%n"));
        interfaceBlock.annotation(annotationBuilder.toString());
    }

    @Override
    protected void writeSingleUnexpectedException(ProxyMethod restAPIMethod, JavaInterface interfaceBlock) {
        // REVISIT: For some reason this can be called even when writeUnexpectedExceptions is called already
    }
}
