package com.azure.autorest.android.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.core.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class AndroidProxyMethod extends ProxyMethod {

    /**
     * Create a new RestAPIMethod with the provided properties.
     * @param requestContentType The Content-Type of the request.
     * @param returnType The type of value that is returned from this method.
     * @param httpMethod The HTTP method that will be used for this method.
     * @param urlPath The path of this method's request URL.
     * @param responseExpectedStatusCodes The status codes that are expected in the response.
     * @param returnValueWireType The return value's type as it is received from the network (across the wire).
     * @param unexpectedResponseExceptionType The exception type to throw if this method receives and unexpected response status code.
     * @param name The name of this REST API method.
     * @param parameters The parameters that are provided to this method.
     * @param description The description of this method.
     * @param isResumable Whether or not this method is resumable.
     * @param responseContentTypes The metia-types in response.
     */
    protected AndroidProxyMethod(String requestContentType, IType returnType, HttpMethod httpMethod, String baseUrl,
                                 String urlPath, List<HttpResponseStatus> responseExpectedStatusCodes,
                                 ClassType unexpectedResponseExceptionType,
                                 Map<ClassType, List<HttpResponseStatus>> unexpectedResponseExceptionTypes,
                                 String name, List<ProxyMethodParameter> parameters, String description,
                                 IType returnValueWireType, IType responseBodyType, boolean isResumable,
                                 Set<String> responseContentTypes) {
        super(requestContentType,
                returnType,
                httpMethod,
                baseUrl,
                urlPath,
                responseExpectedStatusCodes,
                unexpectedResponseExceptionType,
                unexpectedResponseExceptionTypes,
                name,
                parameters,
                null,
                description,
                returnValueWireType,
                responseBodyType,
                null,
                isResumable,
                responseContentTypes);
    }

    @Override
    public void addImportsTo(Set<String> imports, boolean includeImplementationImports, JavaSettings settings) {

        if (includeImplementationImports) {
            if (getUnexpectedResponseExceptionType() != null) {
                imports.add("com.azure.android.core.rest.annotation.UnexpectedResponseExceptionTypes");
                imports.add("com.azure.android.core.rest.annotation.UnexpectedResponseExceptionType");
                getUnexpectedResponseExceptionType().addImportsTo(imports, includeImplementationImports);
            }
            if (getUnexpectedResponseExceptionTypes() != null) {
                imports.add("com.azure.android.core.rest.annotation.UnexpectedResponseExceptionTypes");
                getUnexpectedResponseExceptionTypes().keySet().forEach(e -> e.addImportsTo(imports, includeImplementationImports));
            }
            if (isResumable()) {
                imports.add("com.azure.android.core.rest.annotation.ResumeOperation");
            }
            imports.add(String.format("com.azure.android.core.rest.annotation.%1$s", com.azure.autorest.util.CodeNamer
                    .toPascalCase(getHttpMethod().toString().toLowerCase())));

            if (settings.isFluent()) {
                imports.add("com.azure.android.core.rest.annotation.Headers");
            }

            if (getReturnValueWireType() != null) {
                imports.add("com.azure.android.core.rest.annotation.ReturnValueWireType");
                imports.add("com.azure.android.core.rest.annotation.UnexpectedResponseExceptionTypes");
                imports.add("com.azure.android.core.rest.annotation.UnexpectedResponseExceptionType");
                getUnexpectedResponseExceptionType().addImportsTo(imports, includeImplementationImports);
            }
            if (getUnexpectedResponseExceptionTypes() != null) {
                imports.add("com.azure.android.core.rest.annotation.UnexpectedResponseExceptionTypes");
                imports.add("com.azure.android.core.rest.annotation.UnexpectedResponseExceptionType");
                getUnexpectedResponseExceptionTypes().keySet().forEach(e -> e.addImportsTo(imports, includeImplementationImports));
            }
            if (isResumable()) {
                imports.add("com.azure.android.core.rest.annotation.ResumeOperation");
            }
            imports.add(String.format("com.azure.android.core.rest.annotation.%1$s", com.azure.autorest.util.CodeNamer
                    .toPascalCase(getHttpMethod().toString().toLowerCase())));

            if (settings.isFluent()) {
                imports.add("com.azure.android.core.rest.annotation.Headers");
            }
            imports.add("com.azure.android.core.rest.annotation.ExpectedResponses");
            imports.add("com.azure.android.core.rest.PagedResponse");

            if (getReturnValueWireType() != null) {
                imports.add("com.azure.android.core.rest.annotation.ReturnValueWireType");
                returnValueWireType.addImportsTo(imports, includeImplementationImports);
            }

            returnType.addImportsTo(imports, includeImplementationImports);

            for (ProxyMethodParameter parameter : parameters) {
                parameter.addImportsTo(imports, includeImplementationImports, settings);
            }

            if (imports.contains(ClassType.UnixTimeDateTime.getFullName())) {
                imports.remove(ClassType.UnixTimeDateTime.getFullName());
                imports.add(ClassType.AndroidDateTime.getFullName());
            }

            if (imports.contains(ClassType.Base64Url.getFullName())) {
                imports.remove(ClassType.Base64Url.getFullName());
                imports.add(ClassType.AndroidBase64Url.getFullName());
            }

            if (imports.contains(ClassType.DateTimeRfc1123.getFullName())) {
                imports.remove(ClassType.DateTimeRfc1123.getFullName());
                imports.add(ClassType.AndroidDateTimeRfc1123.getFullName());
            }
        }
    }

    public static class Builder extends ProxyMethod.Builder {

        @Override
        public ProxyMethod build() {
            if (unexpectedResponseExceptionTypes != null
                && unexpectedResponseExceptionTypes.containsKey(unexpectedResponseExceptionType)) {
                unexpectedResponseExceptionType = null;
            }
            return new AndroidProxyMethod(requestContentType,
                    returnType,
                    httpMethod,
                    baseUrl,
                    urlPath,
                    responseExpectedStatusCodes,
                    unexpectedResponseExceptionType,
                    unexpectedResponseExceptionTypes,
                    name,
                    parameters,
                    description,
                    returnValueWireType,
                    responseBodyType,
                    isResumable,
                    responseContentTypes);
        }
    }
}
