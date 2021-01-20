package com.azure.autorest.android.template;

import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.model.clientmodel.*;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.util.CodeNamer;

import java.util.Optional;

public class PageResponseRetrieverTemplate {
    private final ClientMethod getFirstPageMethod;
    private final String serviceClientClassName;
    private final ClientMethod getNextPageMethod;
    private final String retrieverClassName;
    private final GenericType methodReturnType;
    private final IType elementType;
    private final GenericType pageType;

    public PageResponseRetrieverTemplate(ClientMethod getFirstPageMethod,
                                      ClientMethod getNextPageMethod,
                                      String serviceClientClassName) {
        this.getFirstPageMethod = getFirstPageMethod;
        this.getNextPageMethod = getNextPageMethod;
        this.serviceClientClassName = serviceClientClassName;
        this.methodReturnType=(GenericType)getFirstPageMethod.getReturnValue().getType();
        this.elementType=methodReturnType.getTypeArguments()[0];
        this.pageType=GenericType.AndroidPage(elementType);
        this.retrieverClassName=CodeNamer.toPascalCase(getFirstPageMethod.getName())+"Retriever";

    }

    public void write(JavaClass clientClass) {

        String classSignature = retrieverClassName + String.format(" extends PagedDataResponseRetriever<%1$s, Page<%1$s>>", elementType);

        clientClass.privateStaticFinalClass(classSignature, javaClass -> {
            StringBuilder ctorSignatureBuilder = new StringBuilder();
            ctorSignatureBuilder.append(retrieverClassName).append("(");
            boolean hasPrevious = false;
            for (ClientMethodParameter clientMethodParameter : getFirstPageMethod.getMethodParameters()) {
                if (hasPrevious) {
                    ctorSignatureBuilder.append(", ");
                }
                javaClass.privateFinalMemberVariable(clientMethodParameter.getClientType().toString(), clientMethodParameter.getName());
                ctorSignatureBuilder.append(String.format("%1$s %2$s", clientMethodParameter.getClientType(), clientMethodParameter.getName()));
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

            javaClass.publicMethod(String.format("%s getFirstPage()", GenericType.AndroidHttpResponse(pageType)),  getPageMethod -> {
                StringBuilder getPageBuilder = new StringBuilder();
                getPageBuilder.append(String.format(" return serviceClient.%sWithRestResponse(", getFirstPageMethod.getProxyMethod().getName()));
                boolean hasPreviousParam = false;
                for (ClientMethodParameter clientMethodParameter : getFirstPageMethod.getMethodParameters()) {
                    if (hasPreviousParam) {
                        getPageBuilder.append(", ");
                    }
                    getPageBuilder.append(clientMethodParameter.getName());
                    hasPreviousParam = true;
                }
                getPageBuilder.append(");");
                getPageMethod.line(getPageBuilder.toString());
            });

            String nextPageParamName = "dummy";
            String methodSignature = String.format("%1$s getPage(String %2$s)",
                    GenericType.AndroidHttpResponse(pageType),
                    nextPageParamName);
            if (getNextPageMethod == null) {
                javaClass.publicMethod(methodSignature, getNextPageMethod -> {
                    getNextPageMethod.line("throw new RuntimeException(\"No more pages.\");");
                });
            } else {
                Optional<ProxyMethodParameter> pageIdParam = getNextPageMethod.getProxyMethod().getParameters().stream()
                        .filter(p -> p.getRequestParameterLocation() == RequestParameterLocation.Path)
                        .reduce((current, next) -> next);
                if (pageIdParam.isPresent()) {
                    nextPageParamName = pageIdParam.get().getName();
                    methodSignature = String.format("%1$s getPage(String %2$s)",
                            GenericType.AndroidHttpResponse(pageType),
                            nextPageParamName);
                }

                javaClass.publicMethod(methodSignature, getPageMethod -> {
                    StringBuilder getPageBuilder = new StringBuilder();
                    getPageBuilder.append(String.format(" return serviceClient.%sWithRestResponse(", getNextPageMethod.getProxyMethod().getName()));
                    boolean hasPreviousParam = false;
                    for (ClientMethodParameter clientMethodParameter : getNextPageMethod.getMethodParameters()) {
                        if (hasPreviousParam) {
                            getPageBuilder.append(", ");
                        }
                        getPageBuilder.append(clientMethodParameter.getName());
                        hasPreviousParam = true;
                    }
                    getPageBuilder.append(");");
                    getPageMethod.line(getPageBuilder.toString());
                });
            }
        });
    }

    public String getRetrieverClassName() {
        return retrieverClassName;
    }
}
