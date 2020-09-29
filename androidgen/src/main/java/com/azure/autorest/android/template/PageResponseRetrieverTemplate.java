package com.azure.autorest.android.template;

import com.azure.autorest.model.clientmodel.*;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaVisibility;

import java.util.Optional;

public class PageResponseRetrieverTemplate {
    private final ClientMethod getFirstPageMethod;
    private final ServiceClient serviceClient;
    private final ClientMethod getNextPageMethod;
    private String retrieverClassName;

    public PageResponseRetrieverTemplate(ClientMethod getFirstPageMethod,
                                      ClientMethod getNextPageMethod,
                                      ServiceClient serviceClient) {
        this.getFirstPageMethod = getFirstPageMethod;
        this.getNextPageMethod = getNextPageMethod;
        this.serviceClient = serviceClient;
    }

    public String getClassName() {
        return retrieverClassName;
    }

    public void write(JavaClass clientClass) {
        final Optional<ClientMethodParameter> lastParamOpt = getFirstPageMethod.getMethodRequiredParameters()
                .stream()
                .reduce((current, next) -> next);
        final ClientMethodParameter lastParam = lastParamOpt.get();
        final GenericType callbackParameter = (GenericType) lastParam.getWireType();
        final GenericType pageType = (GenericType) callbackParameter.getTypeArguments()[0];
        final IType elementType = pageType.getTypeArguments()[0];
        retrieverClassName = elementType.toString() + "PageResponseRetriever";
        String classSignature = retrieverClassName + String.format(" extends PagedDataResponseRetriever<<%1$s>, Page<%1$s>>", elementType);

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
            javaClass.privateFinalMemberVariable(serviceClient.getClassName(), "serviceClient");
            ctorSignatureBuilder.append(String.format("%s serviceClient", serviceClient.getClassName()));
            ctorSignatureBuilder.append(")");

            javaClass.constructor(JavaVisibility.Public, ctorSignatureBuilder.toString(), constructor -> {
                getFirstPageMethod.getMethodParameters().stream().forEach(parameter -> {
                    constructor.line(String.format("this.%1$s = %1$s;", parameter.getName()));
                });
                constructor.line("this.serviceClient = serviceClient;");
            });

            javaClass.publicMethod(String.format("%s getFirstPage()", pageType), getPageMethod -> {
                StringBuilder getPageBuilder = new StringBuilder();
                getPageBuilder.append(String.format(" return serviceClient.%s(", getFirstPageMethod.getName()));
                boolean hasPreviousParam = false;
                for (ClientMethodParameter clientMethodParameter : getFirstPageMethod.getMethodParameters()) {
                    if (clientMethodParameter.getName().contains("callback")) {
                        continue;
                    }
                    if (hasPreviousParam) {
                        getPageBuilder.append(", ");
                    }
                    getPageBuilder.append(clientMethodParameter.getName());
                    hasPreviousParam = true;
                }
                getPageBuilder.append(");");
                getPageMethod.line(getPageBuilder.toString());
            });

            javaClass.publicMethod(String.format("%1$s getPage(String pageId, Callback<Page<%2$s>> callback)", pageType, elementType),
                    getPageMethod -> {
                        getPageMethod.line(String.format("return serviceClient.%s(pageId, callback);", getNextPageMethod.getName()));
                    });
        });
    }
}
