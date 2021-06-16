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
import io.netty.handler.codec.http.HttpResponseStatus;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A method within a Proxy.
 */
public class ProxyMethod {
    /**
     * Get the Content-Type of the request.
     */
    private String requestContentType;
    /**
     * The value that is returned from this method.
     */
    protected IType returnType;
    /**
     * Get the HTTP method that will be used for this method.
     */
    private HttpMethod httpMethod;
    /**
     * Get the path of this method's request URL.
     */
    private String urlPath;
    /**
     * Get the status codes that are expected in the response.
     */
    private List<HttpResponseStatus> responseExpectedStatusCodes;

    private Map<ClassType, List<HttpResponseStatus>> unexpectedResponseExceptionTypes;
    /**
     * Get the exception type to throw if this method receives and unexpected response status code.
     */
    private ClassType unexpectedResponseExceptionType;
    /**
     * Get the name of this Rest API method.
     */
    private String name;
    /**
     * Get the parameters that are provided to this method.
     */
    protected List<ProxyMethodParameter> parameters;
    /**
     * Get the description of this method.
     */
    private String description;
    /**
     * The value of the ReturnValueWireType annotation for this method.
     */
    protected IType returnValueWireType;
    /**
     * Get whether or not this method resumes polling of an LRO.
     */
    private boolean isResumable;
    /**
     * The media-types in response.
     */
    private Set<String> responseContentTypes;

    private Map<String, ProxyMethodExample> examples;

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
     * @param examples the examples for the method.
     */
    protected ProxyMethod(String requestContentType, IType returnType, HttpMethod httpMethod, String urlPath,
                          List<HttpResponseStatus> responseExpectedStatusCodes,
                          ClassType unexpectedResponseExceptionType,
                          Map<ClassType, List<HttpResponseStatus>> unexpectedResponseExceptionTypes,
                          String name, List<ProxyMethodParameter> parameters, String description,
                          IType returnValueWireType, boolean isResumable, Set<String> responseContentTypes,
                          Map<String, ProxyMethodExample> examples) {
        this.requestContentType = requestContentType;
        this.returnType = returnType;
        this.httpMethod = httpMethod;
        this.urlPath = urlPath;
        this.responseExpectedStatusCodes = responseExpectedStatusCodes;
        this.unexpectedResponseExceptionType = unexpectedResponseExceptionType;
        this.unexpectedResponseExceptionTypes = unexpectedResponseExceptionTypes;
        this.name = name;
        this.parameters = parameters;
        this.description = description;
        this.returnValueWireType = returnValueWireType;
        this.isResumable = isResumable;
        this.responseContentTypes = responseContentTypes;
        this.examples = examples;
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

    public final String getUrlPath() {
        return urlPath;
    }

    public final List<HttpResponseStatus> getResponseExpectedStatusCodes() {
        return responseExpectedStatusCodes;
    }

    public final ClassType getUnexpectedResponseExceptionType() {
        return unexpectedResponseExceptionType;
    }

    public final Map<ClassType, List<HttpResponseStatus>> getUnexpectedResponseExceptionTypes() {
        return unexpectedResponseExceptionTypes;
    }

    public final String getName() {
        return name;
    }

    public final List<ProxyMethodParameter> getParameters() {
        return parameters;
    }

    public final String getDescription() {
        return description;
    }

    public final IType getReturnValueWireType() {
        return returnValueWireType;
    }

    public final boolean getIsResumable() {
        return isResumable;
    }

    public final String getPagingAsyncSinglePageMethodName() {
        return getName() + "SinglePageAsync";
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

    public Map<String, ProxyMethodExample> getExamples() {
        return examples;
    }

//    private MethodType _methodType = null;
//    public final MethodType getMethodType()
//    {
//        if (_methodType != null)
//        {
//            return _methodType.getValue();
//        }
//        _methodType = getMethodType().Other;
//        if (!tangible.StringHelper.isNullOrEmpty(getAutoRestMethod().MethodGroup == null ? null : (getAutoRestMethod().MethodGroup.Name == null ? null : getAutoRestMethod().MethodGroup.Name.toString())))
//        {
//            String autoRestMethodUrl = (new Regex("/+$")).Replace((new Regex("^/+")).Replace(getUrlPath(), ""), "");
//            String[] autoRestMethodUrlSplits = autoRestMethodUrl.split("[/]", -1);
//            switch (getAutoRestMethod().HttpMethod)
//            {
//                case getHttpMethod().Get:
//                    if ((autoRestMethodUrlSplits.length == 5 || autoRestMethodUrlSplits.length == 7) && autoRestMethodUrlSplits[0].EqualsIgnoreCase("subscriptions") && getAutoRestMethod().ReturnType.Body.MethodHasSequenceType())
//                    {
//                        if (autoRestMethodUrlSplits.length == 5)
//                        {
//                            if (autoRestMethodUrlSplits[2].EqualsIgnoreCase("providers"))
//                            {
//                                _methodType = getMethodType().ListBySubscription;
//                            }
//                            else
//                            {
//                                _methodType = getMethodType().ListByResourceGroup;
//                            }
//                        }
//                        else if (autoRestMethodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
//                        {
//                            _methodType = getMethodType().ListByResourceGroup;
//                        }
//                    }
//                    else if (autoRestMethodUrlSplits.IsTopLevelResourceUrl())
//                    {
//                        _methodType = getMethodType().Get;
//                    }
//                    break;
//
//                case getHttpMethod().Delete:
//                    if (autoRestMethodUrlSplits.IsTopLevelResourceUrl())
//                    {
//                        _methodType = getMethodType().Delete;
//                    }
//                    break;
//            }
//        }
//        return _methodType.getValue();
//    }

    /**
     * Add this property's imports to the provided ISet of imports.
     * @param imports The set of imports to add to.
     * @param includeImplementationImports Whether or not to include imports that are only necessary for method implementations.
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
            if (getIsResumable()) {
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
        protected String urlPath;
        protected List<HttpResponseStatus> responseExpectedStatusCodes;
        protected ClassType unexpectedResponseExceptionType;
        protected Map<ClassType, List<HttpResponseStatus>> unexpectedResponseExceptionTypes;
        protected String name;
        protected List<ProxyMethodParameter> parameters;
        protected String description;
        protected IType returnValueWireType;
        protected boolean isResumable;
        protected Set<String> responseContentTypes;
        protected Map<String, ProxyMethodExample> examples;

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
         * @param returnType the value that is returned from this method
         * @return the Builder itself
         */
        public Builder returnType(IType returnType) {
            this.returnType = returnType;
            return this;
        }

        /**
         * Sets the HTTP method that will be used for this method.
         * @param httpMethod the HTTP method that will be used for this method
         * @return the Builder itself
         */
        public Builder httpMethod(HttpMethod httpMethod) {
            this.httpMethod = httpMethod;
            return this;
        }

        /**
         * Sets the path of this method's request URL.
         * @param urlPath the path of this method's request URL
         * @return the Builder itself
         */
        public Builder urlPath(String urlPath) {
            this.urlPath = urlPath;
            return this;
        }

        /**
         * Sets the status codes that are expected in the response.
         * @param responseExpectedStatusCodes the status codes that are expected in the response
         * @return the Builder itself
         */
        public Builder responseExpectedStatusCodes(List<HttpResponseStatus> responseExpectedStatusCodes) {
            this.responseExpectedStatusCodes = responseExpectedStatusCodes;
            return this;
        }

        /**
         * Sets the exception type to throw if this method receives any unexpected response status code.
         * @param unexpectedResponseExceptionType the exception type to throw if this method receives any unexpected response status code
         * @return the Builder itself
         */
        public Builder unexpectedResponseExceptionType(ClassType unexpectedResponseExceptionType) {
            this.unexpectedResponseExceptionType = unexpectedResponseExceptionType;
            return this;
        }

        /**
         * Sets the exception type to throw if this method receives certain unexpected response status code.
         * @param unexpectedResponseExceptionTypes the exception type to throw if this method receives certain unexpected response status code
         * @return the Builder itself
         */
        public Builder unexpectedResponseExceptionTypes(Map<ClassType, List<HttpResponseStatus>> unexpectedResponseExceptionTypes) {
            this.unexpectedResponseExceptionTypes = unexpectedResponseExceptionTypes;
            return this;
        }

        /**
         * Sets the name of this Rest API method.
         * @param name the name of this Rest API method
         * @return the Builder itself
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the parameters that are provided to this method.
         * @param parameters the parameters that are provided to this method
         * @return the Builder itself
         */
        public Builder parameters(List<ProxyMethodParameter> parameters) {
            this.parameters = parameters;
            return this;
        }

        /**
         * Sets the description of this method.
         * @param description the description of this method
         * @return the Builder itself
         */
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets the value of the ReturnValueWireType annotation for this method.
         * @param returnValueWireType the value of the ReturnValueWireType annotation for this method
         * @return the Builder itself
         */
        public Builder returnValueWireType(IType returnValueWireType) {
            this.returnValueWireType = returnValueWireType;
            return this;
        }

        /**
         * Sets whether or not this method resumes polling of an LRO.
         * @param isResumable whether or not this method resumes polling of an LRO
         * @return the Builder itself
         */
        public Builder isResumable(boolean isResumable) {
            this.isResumable = isResumable;
            return this;
        }

        /**
         * Sets the metia-types in response.
         * @param responseContentTypes the metia-types in response
         * @return the Builder itself
         */
        public Builder responseContentTypes(Set<String> responseContentTypes) {
            this.responseContentTypes = responseContentTypes;
            return this;
        }

        /**
         * Sets the examples for the method.
         * @param examples the examples
         * @return the Builder itself
         */
        public Builder examples(Map<String, ProxyMethodExample> examples) {
            this.examples = examples;
            return this;
        }

        /**
         * @return an immutable ProxyMethod instance with the configurations on this builder.
         */
        public ProxyMethod build() {
            return new ProxyMethod(requestContentType,
                    returnType,
                    httpMethod,
                    urlPath,
                    responseExpectedStatusCodes,
                    unexpectedResponseExceptionType,
                    unexpectedResponseExceptionTypes,
                    name,
                    parameters,
                    description,
                    returnValueWireType,
                    isResumable,
                    responseContentTypes,
                    examples);
        }
    }
}
