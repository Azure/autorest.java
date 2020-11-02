package com.azure.autorest.android.template;

import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaVisibility;

public class AsyncPageRetrieverTemplate {
    private final ClientMethod getFirstPageMethod;
    private final ServiceClient serviceClient;
    private final ClientMethod getNextPageMethod;

    public AsyncPageRetrieverTemplate(ClientMethod getFirstPageMethod,
                                      ClientMethod getNextPageMethod,
                                      ServiceClient serviceClient) {
        this.getFirstPageMethod = getFirstPageMethod;
        this.getNextPageMethod = getNextPageMethod;
        this.serviceClient = serviceClient;
    }

    public static String getRetrieverClassName(IType elementType) {
        return elementType.toString() + "PageAsyncRetriever";
    }

    public void write(JavaClass clientClass) {
        final ClientMethodParameter callbackParameter = AndroidClientMethodTemplate.getCallbackParameter(getFirstPageMethod);
        final GenericType callbackParameterType = (GenericType) callbackParameter.getWireType();
        final GenericType pageType = (GenericType) callbackParameterType.getTypeArguments()[0];
        final IType elementType = pageType.getTypeArguments()[0];
        final String retrieverClassName = getRetrieverClassName(elementType);
        String classSignature = retrieverClassName + String.format(" extends AsyncPagedDataRetriever<%1$s, Page<%1$s>>", elementType);

        clientClass.privateStaticFinalClass(classSignature, javaClass -> {
            StringBuilder ctorSignatureBuilder = new StringBuilder();
            ctorSignatureBuilder.append(retrieverClassName).append("(");
            boolean hasPrevious = false;
            for (ClientMethodParameter clientMethodParameter : getFirstPageMethod.getMethodParameters()) {
                if (clientMethodParameter.equals(callbackParameter)) {
                    continue;
                }
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
                    if (!parameter.equals(callbackParameter)) {
                        constructor.line(String.format("this.%1$s = %1$s;", parameter.getName()));
                    }
                });
                constructor.line("this.serviceClient = serviceClient;");
            });

            javaClass.publicMethod(String.format("void getFirstPage(%s callback)", GenericType.AndroidCallback(GenericType.AndroidPage(elementType))), getPageMethod -> {
                StringBuilder getPageBuilder = new StringBuilder();
                getPageBuilder.append(String.format("serviceClient.%s(", getFirstPageMethod.getName()));
                boolean hasPreviousParam = false;
                for(ClientMethodParameter clientMethodParameter : getFirstPageMethod.getMethodParameters()) {
                    if (clientMethodParameter.equals(callbackParameter)) {
                        continue;
                    }
                    if (hasPreviousParam) {
                        getPageBuilder.append(", ");
                    }
                    getPageBuilder.append(clientMethodParameter.getName());
                    hasPreviousParam = true;
                }

                if (hasPreviousParam) {
                    getPageBuilder.append(", ");
                }
                getPageBuilder.append("callback);");
                getPageMethod.line(getPageBuilder.toString());
            });

            javaClass.publicMethod(String.format("void getPage(String pageId, %s callback)", GenericType.AndroidCallback(GenericType.AndroidPage(elementType))), getPageMethod -> {
                getPageMethod.line(String.format("serviceClient.%s(pageId, callback);", getNextPageMethod.getName()));
            });
        });
    }
}
