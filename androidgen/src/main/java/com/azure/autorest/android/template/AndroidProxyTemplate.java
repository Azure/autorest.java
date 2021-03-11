package com.azure.autorest.android.template;

import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.javamodel.JavaInterface;
import com.azure.autorest.template.ProxyTemplate;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.ArrayList;
import java.util.Arrays;
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
        annotationBuilder.append("UnexpectedResponseExceptionTypes({\n");
        for (java.util.Map.Entry<ClassType, List<HttpResponseStatus>> exception : restAPIMethod.getUnexpectedResponseExceptionTypes().entrySet()) {
            annotationBuilder.append(String.format("\t@UnexpectedResponseExceptionType(value = %1$s.class, code = {%2$s})\n",
                    exception.getKey(), exception.getValue().stream().map(status -> String.valueOf(status.code())).collect(java.util.stream.Collectors.joining(", "))));
        }
        annotationBuilder.append("})\n");
        interfaceBlock.annotation(annotationBuilder.toString());
    }

    @Override
    protected void writeSingleUnexpectedException(ProxyMethod restAPIMethod, JavaInterface interfaceBlock) {
        StringBuilder annotationBuilder = new StringBuilder();
        annotationBuilder.append("UnexpectedResponseExceptionTypes({\n");
        annotationBuilder.append(String.format("\t@UnexpectedResponseExceptionType(%1$s.class)\n", restAPIMethod.getUnexpectedResponseExceptionType()));
        annotationBuilder.append("})\n");
        interfaceBlock.annotation(annotationBuilder.toString());
    }
/*
    @Override
    protected void writeProxyMethodSignature(ArrayList<String> parameterDeclarationList, ProxyMethod restAPIMethod, JavaInterface interfaceBlock) {
        String parameterDeclarations = String.join(", ", parameterDeclarationList);
        IType restAPIMethodReturnValueClientType = restAPIMethod.getReturnType().getClientType();
        if(restAPIMethodReturnValueClientType instanceof GenericType) {
            GenericType genericReturnType = (GenericType) restAPIMethodReturnValueClientType;
            if (genericReturnType.getName().startsWith("Mono")) {
                restAPIMethodReturnValueClientType = Arrays.stream(genericReturnType.getTypeArguments()).findFirst().get();
            }
        }
        parameterDeclarations += String.format(", Callback<%1$s> callback", restAPIMethodReturnValueClientType.toString());
        interfaceBlock.publicMethod(String.format("void %1$s(%2$s)", restAPIMethod.getName(), parameterDeclarations));
        interfaceBlock.publicMethod(String.format("%1$s %2$s(%3$s)", restAPIMethodReturnValueClientType, restAPIMethod.getName(), parameterDeclarations));
    }

 */
}
