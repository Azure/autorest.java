package com.azure.autorest.android.model;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.util.CodeNamer;
import com.azure.core.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class AndroidProxyMethod extends ProxyMethod {
    private final List<ProxyMethodParameter> parameters;

    private AndroidProxyMethod(String requestContentType,
                               IType returnType,
                               HttpMethod httpMethod,
                               String urlPath,
                               List<HttpResponseStatus> responseExpectedStatusCodes,
                               ClassType unexpectedResponseExceptionType,
                               Map<ClassType, List<HttpResponseStatus>> unexpectedResponseExceptionTypes,
                               String name, List<ProxyMethodParameter> parameters,
                               String description,
                               IType returnValueWireType,
                               boolean isResumable,
                               Set<String> responseContentTypes) {
        super(requestContentType, returnType,
                httpMethod, urlPath,
                responseExpectedStatusCodes, unexpectedResponseExceptionType,
                unexpectedResponseExceptionTypes, name,
                parameters, description,
                returnValueWireType, isResumable,
                responseContentTypes);

        this.parameters = parameters;
    }

    @Override
    public void addImportsTo(Set<String> imports, boolean includeImplementationImports, JavaSettings settings) {
        imports.add("retrofit2.Call");
        imports.add("okhttp3.ResponseBody");
        imports.add("com.azure.android.core.http.exception.HttpResponseException");
        imports.add(String.format("retrofit2.http.%1$s", CodeNamer
                .toPascalCase(getHttpMethod().toString().toUpperCase())));
        for (ProxyMethodParameter parameter : parameters) {
            if (parameter.getRequestParameterLocation() != RequestParameterLocation.None
                    && parameter.getRequestParameterLocation() != RequestParameterLocation.Uri) {
                imports.add(String.format("retrofit2.http.%1$s",
                        CodeNamer.toPascalCase(parameter.getRequestParameterLocation().toString())));
                parameter.getWireType().addImportsTo(imports, false);
            }
            if (parameter.getRequestParameterLocation() == RequestParameterLocation.Body) {
                imports.add("okhttp3.RequestBody");
            }
        }

        ClassType unexpectedExceptionType = this.getUnexpectedResponseExceptionType();
        if (unexpectedExceptionType == ClassType.HttpResponseException) {
            imports.add("com.azure.android.core.http.exception.HttpResponseException");
        } else {
            unexpectedExceptionType.addImportsTo(imports, includeImplementationImports);
        }
        if (getUnexpectedResponseExceptionTypes() != null) {
            getUnexpectedResponseExceptionTypes()
                    .keySet()
                    .forEach(e -> e.addImportsTo(imports, includeImplementationImports));
        }
    }

    public static class Builder extends ProxyMethod.Builder {
        public AndroidProxyMethod build() {
            return new AndroidProxyMethod(super.requestContentType,
                    super.returnType,
                    super.httpMethod,
                    super.urlPath,
                    super.responseExpectedStatusCodes,
                    super.unexpectedResponseExceptionType,
                    super.unexpectedResponseExceptionTypes,
                    super.name,
                    super.parameters,
                    super.description,
                    super.returnValueWireType,
                    super.isResumable, super.responseContentTypes);
        }
    }
}
