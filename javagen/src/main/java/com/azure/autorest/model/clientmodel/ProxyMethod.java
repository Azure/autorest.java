// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

//====================================================================================================
//The Free Edition of C# to Java Converter limits conversion output to 100 lines per file.

//To subscribe to the Premium Edition, visit our website:
//https://www.tangiblesoftwaresolutions.com/order/order-csharp-to-java.html
//====================================================================================================

package com.azure.autorest.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.util.CodeNamer;
import com.azure.core.http.ContentType;
import com.azure.core.http.HttpMethod;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A method within a Proxy.
 */
public class ProxyMethod {
    /**
     * Get the Content-Type of the request.
     */
    private final String requestContentType;
    /**
     * The value that is returned from this method.
     */
    protected IType returnType;
    /**
     * Get the HTTP method that will be used for this method.
     */
    private final HttpMethod httpMethod;
    /**
     * Get the base URL that will be used for each REST API method.
     */
    private final String baseUrl;
    /**
     * Get the path of this method's request URL.
     */
    private final String urlPath;
    /**
     * Get the status codes that are expected in the response.
     */
    private final List<Integer> responseExpectedStatusCodes;

    private final Map<ClassType, List<Integer>> unexpectedResponseExceptionTypes;
    /**
     * Get the exception type to throw if this method receives and unexpected response status code.
     */
    private final ClassType unexpectedResponseExceptionType;
    /**
     * Get the name of this Rest API method.
     */
    private final String name;
    /**
     * Get the parameters that are provided to this method.
     */
    protected List<ProxyMethodParameter> parameters;
    /**
     * Get all parameters defined in swagger to this method.
     */
    protected List<ProxyMethodParameter> allParameters;
    /**
     * Get the description of this method.
     */
    private final String description;
    /**
     * The value of the ReturnValueWireType annotation for this method.
     */
    protected IType returnValueWireType;
    /**
     * The response body type.
     */
    private final IType responseBodyType;
    /**
     * The raw response body type. responseBodyType is set to BinaryData in low-level mode. We need raw type.
     */
    private final IType rawResponseBodyType;
    /**
     * Get whether this method resumes polling of an LRO.
     */
    private final boolean isResumable;
    /**
     * The media-types in response.
     */
    private final Set<String> responseContentTypes;

    private final Map<String, ProxyMethodExample> examples;

    private final List<String> specialHeaders;

    private final String operationId;

    private final boolean isSync;
    private ProxyMethod syncProxy;

    protected ProxyMethod(String requestContentType, IType returnType, HttpMethod httpMethod, String baseUrl,
        String urlPath, List<Integer> responseExpectedStatusCodes,
        ClassType unexpectedResponseExceptionType,
        Map<ClassType, List<Integer>> unexpectedResponseExceptionTypes,
        String name, List<ProxyMethodParameter> parameters,
        List<ProxyMethodParameter> allParameters, String description,
        IType returnValueWireType, IType responseBodyType, IType rawResponseBodyType,
        boolean isResumable, Set<String> responseContentTypes,
        String operationId, Map<String, ProxyMethodExample> examples,
        List<String> specialHeaders) {
        this(requestContentType, returnType, httpMethod, baseUrl, urlPath, responseExpectedStatusCodes,
            unexpectedResponseExceptionType, unexpectedResponseExceptionTypes, name, parameters, allParameters,
            description, returnValueWireType, responseBodyType, rawResponseBodyType, isResumable,
            responseContentTypes, operationId, examples, specialHeaders, false);
    }

    /**
     * Create a new RestAPIMethod with the provided properties.
     *
     * @param requestContentType The Content-Type of the request.
     * @param returnType The type of value that is returned from this method.
     * @param httpMethod The HTTP method that will be used for this method.
     * @param baseUrl The base URL that will be used for each REST API method.
     * @param urlPath The path of this method's request URL.
     * @param responseExpectedStatusCodes The status codes that are expected in the response.
     * @param returnValueWireType The return value's type as it is received from the network (across the wire).
     * @param unexpectedResponseExceptionType The exception type to throw if this method receives and unexpected
     * response status code.
     * @param name The name of this REST API method.
     * @param parameters The parameters that are provided to this method.
     * @param description The description of this method.
     * @param isResumable Whether this method is resumable.
     * @param responseContentTypes The media-types in response.
     * @param operationId the operation ID
     * @param examples the examples for the method.
     */
    protected ProxyMethod(String requestContentType, IType returnType, HttpMethod httpMethod, String baseUrl,
        String urlPath, List<Integer> responseExpectedStatusCodes,
        ClassType unexpectedResponseExceptionType,
        Map<ClassType, List<Integer>> unexpectedResponseExceptionTypes,
        String name, List<ProxyMethodParameter> parameters,
        List<ProxyMethodParameter> allParameters, String description,
        IType returnValueWireType, IType responseBodyType, IType rawResponseBodyType,
        boolean isResumable, Set<String> responseContentTypes,
        String operationId, Map<String, ProxyMethodExample> examples,
        List<String> specialHeaders, boolean isSync) {
        this.requestContentType = requestContentType;
        this.returnType = returnType;
        this.httpMethod = httpMethod;
        this.baseUrl = baseUrl;
        this.urlPath = urlPath;
        this.responseExpectedStatusCodes = responseExpectedStatusCodes;
        this.unexpectedResponseExceptionType = unexpectedResponseExceptionType;
        this.unexpectedResponseExceptionTypes = unexpectedResponseExceptionTypes;
        this.name = name;
        this.parameters = parameters;
        this.allParameters = allParameters;
        this.description = description;
        this.returnValueWireType = returnValueWireType;
        this.responseBodyType = responseBodyType;
        this.rawResponseBodyType = rawResponseBodyType;
        this.isResumable = isResumable;
        this.responseContentTypes = responseContentTypes;
        this.operationId = operationId;
        this.examples = examples;
        this.specialHeaders = specialHeaders;
        this.isSync = isSync;
    }

    public final String getRequestContentType() {
        return requestContentType;
    }

    public final IType getReturnType() {
        return returnType;
    }

    public final HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public final String getBaseUrl() {
        return baseUrl;
    }

    public final String getUrlPath() {
        return urlPath;
    }

    public final List<Integer> getResponseExpectedStatusCodes() {
        return responseExpectedStatusCodes;
    }

    public final ClassType getUnexpectedResponseExceptionType() {
        return unexpectedResponseExceptionType;
    }

    public final Map<ClassType, List<Integer>> getUnexpectedResponseExceptionTypes() {
        return unexpectedResponseExceptionTypes;
    }

    public final String getName() {
        return name;
    }

    public final List<ProxyMethodParameter> getParameters() {
        return parameters;
    }

    public final List<ProxyMethodParameter> getAllParameters() {
        return allParameters;
    }

    public final String getDescription() {
        return description;
    }

    public final IType getReturnValueWireType() {
        return returnValueWireType;
    }

    public IType getResponseBodyType() {
        return responseBodyType;
    }

    public IType getRawResponseBodyType() {
        return rawResponseBodyType;
    }

    public final boolean isResumable() {
        return isResumable;
    }

    public final String getPagingAsyncSinglePageMethodName() {
        return getName() + "SinglePageAsync";
    }

    public final String getPagingSinglePageMethodName() {
        return getName() + "SinglePage";
    }

    public final String getSimpleAsyncMethodName() {
        return getName() + "Async";
    }

    public final String getSimpleAsyncRestResponseMethodName() {
        return getName() + "WithResponseAsync";
    }

    public final String getSimpleRestResponseMethodName() {
        return getName() + "WithResponse";
    }

    public final Set<String> getResponseContentTypes() {
        return responseContentTypes;
    }

    public String getOperationId() {
        return operationId;
    }

    public Map<String, ProxyMethodExample> getExamples() {
        return examples;
    }

    public List<String> getSpecialHeaders() {
        return specialHeaders;
    }

    public boolean isSync() {
        return isSync;
    }

    public ProxyMethod toSync() {
        if (isSync) {
            return this;
        }

        if (this.syncProxy != null) {
            return syncProxy;
        }

        List<ProxyMethodParameter> syncParams = this.getParameters()
            .stream()
            .map(this::mapToSyncParam)
            .collect(Collectors.toList());

        List<ProxyMethodParameter> allSyncParams = this.getAllParameters()
            .stream()
            .map(this::mapToSyncParam)
            .collect(Collectors.toList());

        this.syncProxy = new ProxyMethod.Builder()
            .parameters(syncParams)
            .httpMethod(this.getHttpMethod())
            .name(this.getName() + "Sync")
            .description(this.getDescription())
            .baseURL(this.getBaseUrl())
            .operationId(this.getOperationId())
            .isResumable(this.isResumable())
            .examples(this.getExamples())
            .rawResponseBodyType(mapToSyncType(this.getRawResponseBodyType()))
            .requestContentType(this.getRequestContentType())
            .responseBodyType(mapToSyncType(this.getResponseBodyType()))
            .returnType(mapToSyncType(this.getReturnType()))
            .returnValueWireType(mapToSyncType(this.getReturnValueWireType()))
            .urlPath(this.getUrlPath())
            .specialHeaders(this.getSpecialHeaders())
            .unexpectedResponseExceptionType(this.getUnexpectedResponseExceptionType())
            .unexpectedResponseExceptionTypes(this.getUnexpectedResponseExceptionTypes())
            .allParameters(allSyncParams)
            .responseContentTypes(this.getResponseContentTypes())
            .responseExpectedStatusCodes(this.getResponseExpectedStatusCodes())
            .isSync(true)
            .build();
        return this.syncProxy;
    }

    private ProxyMethodParameter mapToSyncParam(ProxyMethodParameter param) {
        return param.toNewBuilder()
            .clientType(mapToSyncType(param.getClientType()))
            .rawType(mapToSyncType(param.getRawType()))
            .wireType(mapToSyncType(param.getWireType()))
            .build();
    }

    private IType mapToSyncType(IType type) {
        if (type == GenericType.FluxByteBuffer) {
            return ClassType.BinaryData;
        }


        if (type instanceof GenericType) {
            GenericType genericType = (GenericType) type;
            if (genericType.getName().equals("Mono")) {
                return (genericType.getTypeArguments()[0] == ClassType.StreamResponse)
                    ? GenericType.Response(ClassType.BinaryData)
                    : genericType.getTypeArguments()[0];
            }
            if (genericType.getName().equals("PagedFlux")) {
                IType pageType = genericType.getTypeArguments()[0];
                return GenericType.PagedIterable(pageType);
            }
            if (genericType.getName().equals("PollerFlux")) {
                IType[] typeArguments = genericType.getTypeArguments();
                IType pollType = typeArguments[0];
                IType resultType = typeArguments[1];
                return GenericType.SyncPoller(pollType, resultType);
            }
        }
        return type;
    }


    /**
     * Add this property's imports to the provided ISet of imports.
     *
     * @param imports The set of imports to add to.
     * @param includeImplementationImports Whether or not to include imports that are only necessary for method
     * implementations.
     */
    public void addImportsTo(Set<String> imports, boolean includeImplementationImports, JavaSettings settings) {
        if (includeImplementationImports) {
            if (getUnexpectedResponseExceptionType() != null) {
                imports.add("com.azure.core.annotation.UnexpectedResponseExceptionType");
                getUnexpectedResponseExceptionType().addImportsTo(imports, includeImplementationImports);
            }
            if (getUnexpectedResponseExceptionTypes() != null) {
                imports.add("com.azure.core.annotation.UnexpectedResponseExceptionType");
                getUnexpectedResponseExceptionTypes().keySet().forEach(e -> e.addImportsTo(imports, includeImplementationImports));
            }
            if (isResumable()) {
                imports.add("com.azure.core.annotation.ResumeOperation");
            }
            imports.add(String.format("com.azure.core.annotation.%1$s", CodeNamer
                .toPascalCase(getHttpMethod().toString().toLowerCase())));

            if (settings.isFluent()) {
                imports.add("com.azure.core.annotation.Headers");
            }
            imports.add("com.azure.core.annotation.ExpectedResponses");

            if (getReturnValueWireType() != null) {
                imports.add("com.azure.core.annotation.ReturnValueWireType");
                returnValueWireType.addImportsTo(imports, includeImplementationImports);
            }

            returnType.addImportsTo(imports, includeImplementationImports);

            if (ContentType.APPLICATION_X_WWW_FORM_URLENCODED.equals(this.requestContentType)) {
                imports.add("com.azure.core.annotation.FormParam");
            }

            for (ProxyMethodParameter parameter : parameters) {
                parameter.addImportsTo(imports, includeImplementationImports, settings);
            }
        }
    }

    public static class Builder {
        protected String requestContentType;
        protected IType returnType;
        protected HttpMethod httpMethod;
        protected String baseUrl;
        protected String urlPath;
        protected List<Integer> responseExpectedStatusCodes;
        protected ClassType unexpectedResponseExceptionType;
        protected Map<ClassType, List<Integer>> unexpectedResponseExceptionTypes;
        protected String name;
        protected List<ProxyMethodParameter> parameters;
        protected List<ProxyMethodParameter> allParameters;
        protected String description;
        protected IType returnValueWireType;
        protected IType responseBodyType;
        protected IType rawResponseBodyType;
        protected boolean isResumable;
        protected Set<String> responseContentTypes;
        protected Map<String, ProxyMethodExample> examples;
        protected String operationId;
        protected List<String> specialHeaders;
        protected boolean isSync;

        /*
         * Sets the Content-Type of the request.
         * @param requestContentType the Content-Type of the request
         * @return the Builder itself
         */
        public Builder requestContentType(String requestContentType) {
            this.requestContentType = requestContentType;
            return this;
        }

        /**
         * Sets the value that is returned from this method.
         *
         * @param returnType the value that is returned from this method
         * @return the Builder itself
         */
        public Builder returnType(IType returnType) {
            this.returnType = returnType;
            return this;
        }

        /**
         * Sets the HTTP method that will be used for this method.
         *
         * @param httpMethod the HTTP method that will be used for this method
         * @return the Builder itself
         */
        public Builder httpMethod(HttpMethod httpMethod) {
            this.httpMethod = httpMethod;
            return this;
        }

        /**
         * Sets the base URL that will be used for each REST API method.
         *
         * @param baseUrl the base URL that will be used for each REST API method
         * @return the Builder itself
         */
        public Builder baseURL(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        /**
         * Sets the path of this method's request URL.
         *
         * @param urlPath the path of this method's request URL
         * @return the Builder itself
         */
        public Builder urlPath(String urlPath) {
            this.urlPath = urlPath;
            return this;
        }

        /**
         * Sets the status codes that are expected in the response.
         *
         * @param responseExpectedStatusCodes the status codes that are expected in the response
         * @return the Builder itself
         */
        public Builder responseExpectedStatusCodes(List<Integer> responseExpectedStatusCodes) {
            this.responseExpectedStatusCodes = responseExpectedStatusCodes;
            return this;
        }

        /**
         * Sets the exception type to throw if this method receives any unexpected response status code.
         *
         * @param unexpectedResponseExceptionType the exception type to throw if this method receives any unexpected
         * response status code
         * @return the Builder itself
         */
        public Builder unexpectedResponseExceptionType(ClassType unexpectedResponseExceptionType) {
            this.unexpectedResponseExceptionType = unexpectedResponseExceptionType;
            return this;
        }

        /**
         * Sets the exception type to throw if this method receives certain unexpected response status code.
         *
         * @param unexpectedResponseExceptionTypes the exception type to throw if this method receives certain
         * unexpected response status code
         * @return the Builder itself
         */
        public Builder unexpectedResponseExceptionTypes(Map<ClassType, List<Integer>> unexpectedResponseExceptionTypes) {
            this.unexpectedResponseExceptionTypes = unexpectedResponseExceptionTypes;
            return this;
        }

        /**
         * Sets the name of this Rest API method.
         *
         * @param name the name of this Rest API method
         * @return the Builder itself
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the parameters that are provided to this method.
         *
         * @param parameters the parameters that are provided to this method
         * @return the Builder itself
         */
        public Builder parameters(List<ProxyMethodParameter> parameters) {
            this.parameters = parameters;
            return this;
        }

        /**
         * Sets all parameters defined in swagger to this method.
         *
         * @param allParameters the parameters that are provided to this method
         * @return the Builder itself
         */
        public Builder allParameters(List<ProxyMethodParameter> allParameters) {
            this.allParameters = allParameters;
            return this;
        }

        /**
         * Sets the description of this method.
         *
         * @param description the description of this method
         * @return the Builder itself
         */
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets the value of the ReturnValueWireType annotation for this method.
         *
         * @param returnValueWireType the value of the ReturnValueWireType annotation for this method
         * @return the Builder itself
         */
        public Builder returnValueWireType(IType returnValueWireType) {
            this.returnValueWireType = returnValueWireType;
            return this;
        }

        /**
         * Sets the response body type.
         *
         * @param responseBodyType the response body type
         * @return the Builder itself
         */
        public Builder responseBodyType(IType responseBodyType) {
            this.responseBodyType = responseBodyType;
            return this;
        }

        /**
         * Sets the raw response body type.
         *
         * @param rawResponseBodyType the response body type
         * @return the Builder itself
         */
        public Builder rawResponseBodyType(IType rawResponseBodyType) {
            this.rawResponseBodyType = rawResponseBodyType;
            return this;
        }

        /**
         * Sets whether or not this method resumes polling of an LRO.
         *
         * @param isResumable whether or not this method resumes polling of an LRO
         * @return the Builder itself
         */
        public Builder isResumable(boolean isResumable) {
            this.isResumable = isResumable;
            return this;
        }

        /**
         * Sets the metia-types in response.
         *
         * @param responseContentTypes the metia-types in response
         * @return the Builder itself
         */
        public Builder responseContentTypes(Set<String> responseContentTypes) {
            this.responseContentTypes = responseContentTypes;
            return this;
        }

        /**
         * Sets the examples for the method.
         *
         * @param examples the examples
         * @return the Builder itself
         */
        public Builder examples(Map<String, ProxyMethodExample> examples) {
            this.examples = examples;
            return this;
        }

        /**
         * Sets the operation ID for reference.
         *
         * @param operationId the operation ID
         * @return the Builder itself
         */
        public Builder operationId(String operationId) {
            this.operationId = operationId;
            return this;
        }

        /**
         * Sets the special headers
         *
         * @param specialHeaders the special headers
         * @return the Builder
         */
        public Builder specialHeaders(List<String> specialHeaders) {
            this.specialHeaders = specialHeaders;
            return this;
        }

        public Builder isSync(boolean isSync) {
            this.isSync = isSync;
            return this;
        }

        /**
         * @return an immutable ProxyMethod instance with the configurations on this builder.
         */
        public ProxyMethod build() {
            return new ProxyMethod(requestContentType,
                returnType,
                httpMethod,
                baseUrl,
                urlPath,
                responseExpectedStatusCodes,
                unexpectedResponseExceptionType,
                unexpectedResponseExceptionTypes,
                name,
                parameters,
                allParameters,
                description,
                returnValueWireType,
                responseBodyType,
                rawResponseBodyType,
                isResumable,
                responseContentTypes,
                operationId,
                examples,
                specialHeaders,
                isSync);
        }
    }
}
