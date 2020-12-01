package com.azure.autorest.android.template;

import com.azure.autorest.model.clientmodel.*;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.util.CodeNamer;

public class PageRetrieverTemplate {
    private final ClientMethod getFirstPageMethod;
    private final String serviceClientClassName;
    private final ClientMethod getNextPageMethod;
    private final String retrieverClassName;
    private final IType elementType;
    private final GenericType pageType;
    private final GenericType returnType;

    public PageRetrieverTemplate(ClientMethod getFirstPageMethod, ClientMethod getNextPageMethod,
            String serviceClientClassName) {
        this.getFirstPageMethod = getFirstPageMethod;
        this.getNextPageMethod = getNextPageMethod;
        this.serviceClientClassName = serviceClientClassName;
    }

    public String getRetrieverClassName() {
        return retrieverClassName;
    }

    public void write(JavaClass clientClass) {
        String classSignature = retrieverClassName
                + String.format(" extends PagedDataRetriever<%1$s, Page<%1$s>>", elementType);

        clientClass.privateStaticFinalClass(classSignature, javaClass -> {
            StringBuilder ctorSignatureBuilder = new StringBuilder();
            ctorSignatureBuilder.append(retrieverClassName).append("(");
            boolean hasPrevious = false;
            for (ClientMethodParameter clientMethodParameter : getFirstPageMethod.getMethodParameters()) {
                if (hasPrevious) {
                    ctorSignatureBuilder.append(", ");
                }
                javaClass.privateFinalMemberVariable(clientMethodParameter.getClientType().toString(),
                        clientMethodParameter.getName());
                ctorSignatureBuilder.append(String.format("%1$s %2$s", clientMethodParameter.getClientType(),
                        clientMethodParameter.getName()));
                hasPrevious = true;
            }
            if (hasPrevious) {
                ctorSignatureBuilder.append(", ");
            }
            javaClass.privateFinalMemberVariable(serviceClientClassName, "serviceClient");
            ctorSignatureBuilder.append(String.format("%s serviceClient", serviceClientClassName));
            ctorSignatureBuilder.append(")");

            javaClass.constructor(JavaVisibility.Public, ctorSignatureBuilder.toString(), constructor -> {
                getFirstPageMethod.getMethodParameters().stream().forEach(parameter -> {
                    constructor.line(String.format("this.%1$s = %1$s;", parameter.getName()));
                });
                constructor.line("this.serviceClient = serviceClient;");
            });

            javaClass.publicMethod(String.format("%s getFirstPage()", pageType), getPageMethod -> {
                StringBuilder getPageBuilder = new StringBuilder();
                getPageBuilder.append(String.format(" return serviceClient.%sWithRestResponse(",
                        getFirstPageMethod.getProxyMethod().getName()));
                boolean hasPreviousParam = false;
                for (ClientMethodParameter clientMethodParameter : getFirstPageMethod.getMethodParameters()) {
                    if (hasPreviousParam) {
                        getPageBuilder.append(", ");
                    }
                    getPageBuilder.append(clientMethodParameter.getName());
                    hasPreviousParam = true;
                }
                getPageBuilder.append(").getValue();");
                getPageMethod.line(getPageBuilder.toString());
            });

            javaClass.publicMethod(String.format("%1$s getPage(String pageId)", pageType, elementType),
                    getPageMethod -> {
                        getPageMethod.line(String.format("return serviceClient.%sWithRestResponse(pageId).getValue();",
                                getNextPageMethod.getProxyMethod().getName()));
                    });
        });
    }
}
