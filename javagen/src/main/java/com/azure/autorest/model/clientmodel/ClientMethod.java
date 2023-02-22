// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

//====================================================================================================
//The Free Edition of C# to Java Converter limits conversion output to 100 lines per file.

//To subscribe to the Premium Edition, visit our website:
//https://www.tangiblesoftwaresolutions.com/order/order-csharp-to-java.html
//====================================================================================================

package com.azure.autorest.model.clientmodel;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.util.CodeNamer;
import com.azure.autorest.util.MethodUtil;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.DateTimeRfc1123;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * A ClientMethod that exists on a ServiceClient or MethodGroupClient that eventually will call a ProxyMethod.
 */
public class ClientMethod {
    private static final List<String> KNOWN_POLLING_STRATEGIES = Arrays.asList(
            "DefaultPollingStrategy",
            "ChainedPollingStrategy",
            "OperationResourcePollingStrategy",
            "LocationPollingStrategy",
            "StatusCheckPollingStrategy",
            "SyncDefaultPollingStrategy",
            "SyncChainedPollingStrategy",
            "SyncOperationResourcePollingStrategy",
            "SyncLocationPollingStrategy",
            "SyncStatusCheckPollingStrategy");
    /**
     * The description of this ClientMethod.
     */
    private String description;
    /**
     * The return value of this ClientMethod.
     */
    private ReturnValue returnValue;
    /**
     * The name of this ClientMethod.
     */
    private String name;
    /**
     * The parameters of this ClientMethod.
     */
    private List<ClientMethodParameter> parameters;
    /**
     * Whether or not this ClientMethod has omitted optional parameters.
     */
    private boolean onlyRequiredParameters;
    /**
     * The type of this ClientMethod.
     */
    private ClientMethodType type = ClientMethodType.values()[0];
    /**
     * The RestAPIMethod that this ClientMethod eventually calls.
     */
    private ProxyMethod proxyMethod;
    /**
     * The expressions (parameters and service client properties) that need to be validated in this ClientMethod.
     */
    private Map<String, String> validateExpressions;
    /**
     * The reference to the service client.
     */
    private String clientReference;
    /**
     * The parameter expressions which are required.
     */
    private List<String> requiredNullableParameterExpressions;
    /**
     * The parameter that needs to transformed before pagination.
     */
    private boolean isGroupedParameterRequired;
    /**
     * The type name of groupedParameter.
     */
    private String groupedParameterTypeName;
    /**
     * The pagination information if this is a paged method.
     */
    private MethodPageDetails methodPageDetails;
    /**
     * The parameter transformations before calling ProxyMethod.
     */
    private List<MethodTransformationDetail> methodTransformationDetails;

    private JavaVisibility methodVisibility;

    private JavaVisibility methodVisibilityInWrapperClient;

    private final ImplementationDetails implementationDetails;

    private MethodPollingDetails methodPollingDetails;

    private ExternalDocumentation externalDocumentation;

    /**
     * Create a new ClientMethod with the provided properties.
     * @param description The description of this ClientMethod.
     * @param returnValue The return value of this ClientMethod.
     * @param name The name of this ClientMethod.
     * @param parameters The parameters of this ClientMethod.
     * @param onlyRequiredParameters Whether or not this ClientMethod has omitted optional parameters.
     * @param type The type of this ClientMethod.
     * @param proxyMethod The ProxyMethod that this ClientMethod eventually calls.
     * @param validateExpressions The expressions (parameters and service client properties) that need to be validated in this ClientMethod.
     * @param clientReference The reference to the service client.
     * @param requiredNullableParameterExpressions The parameter expressions which are required.
     * @param isGroupedParameterRequired The parameter that needs to transformed before pagination.
     * @param groupedParameterTypeName The type name of groupedParameter.
     * @param methodPageDetails The pagination information if this is a paged method.
     * @param methodTransformationDetails The parameter transformations before calling ProxyMethod.
     * @param externalDocumentation The external documentation.
     */
    protected ClientMethod(String description, ReturnValue returnValue, String name,
                           List<ClientMethodParameter> parameters, boolean onlyRequiredParameters,
                           ClientMethodType type, ProxyMethod proxyMethod, Map<String, String> validateExpressions,
                           String clientReference, List<String> requiredNullableParameterExpressions,
                           boolean isGroupedParameterRequired, String groupedParameterTypeName,
                           MethodPageDetails methodPageDetails,
                           List<MethodTransformationDetail> methodTransformationDetails,
                           JavaVisibility methodVisibility, JavaVisibility methodVisibilityInWrapperClient, ImplementationDetails implementationDetails,
                           MethodPollingDetails methodPollingDetails, ExternalDocumentation externalDocumentation) {
        this.description = description;
        this.returnValue = returnValue;
        this.name = name;
        this.parameters = parameters;
        this.onlyRequiredParameters = onlyRequiredParameters;
        this.type = type;
        this.proxyMethod = proxyMethod;
        this.validateExpressions = validateExpressions;
        this.clientReference = clientReference;
        this.requiredNullableParameterExpressions = requiredNullableParameterExpressions;
        this.isGroupedParameterRequired = isGroupedParameterRequired;
        this.groupedParameterTypeName = groupedParameterTypeName;
        this.methodPageDetails = methodPageDetails;
        this.methodTransformationDetails = methodTransformationDetails;
        this.methodVisibility = methodVisibility;
        this.implementationDetails = implementationDetails;
        this.methodPollingDetails = methodPollingDetails;
        this.externalDocumentation = externalDocumentation;
        this.methodVisibilityInWrapperClient = methodVisibilityInWrapperClient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientMethod that = (ClientMethod) o;
        return onlyRequiredParameters == that.onlyRequiredParameters
                && isGroupedParameterRequired == that.isGroupedParameterRequired
                && Objects.equals(returnValue.getType(), that.returnValue.getType())
                && Objects.equals(name, that.name)
                && Objects.equals(getParametersDeclaration(), that.getParametersDeclaration())
                && type == that.type
                && Objects.equals(requiredNullableParameterExpressions, that.requiredNullableParameterExpressions)
                && Objects.equals(groupedParameterTypeName, that.groupedParameterTypeName)
                && Objects.equals(methodTransformationDetails, that.methodTransformationDetails)
                && methodVisibility == that.methodVisibility;
    }

    @Override
    public int hashCode() {
        return Objects.hash(returnValue.getType(), name, getParametersDeclaration(), onlyRequiredParameters, type,
                requiredNullableParameterExpressions, isGroupedParameterRequired, groupedParameterTypeName,
                methodTransformationDetails, methodVisibility);
    }

    public final String getDescription() {
        return description;
    }

    public final ReturnValue getReturnValue() {
        return returnValue;
    }

    public final String getName() {
        return name;
    }

    public final List<ClientMethodParameter> getParameters() {
        return parameters;
    }

    public final boolean getOnlyRequiredParameters() {
        return onlyRequiredParameters;
    }

    public final ClientMethodType getType() {
        return type;
    }

    public final ProxyMethod getProxyMethod() {
        return proxyMethod;
    }

    public final Map<String, String> getValidateExpressions() {
        return validateExpressions;
    }

    public final String getClientReference() {
        return clientReference;
    }

    /**
     * Get the comma-separated list of parameter declarations for this ClientMethod.
     */
    public final String getParametersDeclaration() {
        List<ClientMethodParameter> methodParameters = onlyRequiredParameters ? getMethodRequiredParameters() : getMethodParameters();
        return methodParameters.stream().map(ClientMethodParameter::getDeclaration).collect(Collectors.joining(", "));
    }

    /**
     * Get the comma-separated list of parameter names for this ClientMethod.
     */
    public final String getArgumentList() {
        return getMethodParameters().stream().map(ClientMethodParameter::getName).collect(Collectors.joining(", "));
    }

    public final String getArgumentListWithoutRequestOptions() {
        return getMethodParameters().stream().map(ClientMethodParameter::getName)
                .map(name -> name.equals("requestOptions") ? "null" : name).collect(Collectors.joining(", "));
    }

    /**
     * The full declaration of this ClientMethod.
     */
    public final String getDeclaration() {
        return String.format("%1$s %2$s(%3$s)", getReturnValue().getType(), getName(), getParametersDeclaration());
    }

    /**
     * Get the input parameters of the client method, taking configure of onlyRequiredParameters.
     */
    public final List<ClientMethodParameter> getMethodInputParameters() {
        return getOnlyRequiredParameters() ? getMethodRequiredParameters() : getMethodParameters();
    }

    public final List<ClientMethodParameter> getMethodParameters() {
        return getParameters().stream().filter(parameter -> parameter != null && !parameter.isFromClient() &&
                parameter.getName() != null && !parameter.getName().trim().isEmpty())
                .sorted((p1, p2) -> Boolean.compare(!p1.isRequired(), !p2.isRequired()))
                .collect(Collectors.toList());
    }

    private final List<ClientMethodParameter> getMethodNonConstantParameters() {
        return getMethodParameters().stream().filter(parameter -> !parameter.isConstant())
                .sorted((p1, p2) -> Boolean.compare(!p1.isRequired(), !p2.isRequired()))
                .collect(Collectors.toList());
    }

    public final List<ClientMethodParameter> getMethodRequiredParameters() {
        return getMethodNonConstantParameters().stream().filter(ClientMethodParameter::isRequired).collect(Collectors.toList());
    }

    public final List<String> getRequiredNullableParameterExpressions() {
        return requiredNullableParameterExpressions;
    }

    public final boolean isGroupedParameterRequired() {
        return isGroupedParameterRequired;
    }

    public final String getGroupedParameterTypeName() {
        return groupedParameterTypeName;
    }

    public final MethodPageDetails getMethodPageDetails() {
        return methodPageDetails;
    }

    public final List<MethodTransformationDetail> getMethodTransformationDetails() {
        return methodTransformationDetails;
    }

    public ExternalDocumentation getMethodDocumentation() {
        return externalDocumentation;
    }

    public final List<String> getProxyMethodArguments(JavaSettings settings) {
        List<String> restAPIMethodArguments = getProxyMethod().getParameters().stream().map(parameter -> {
            String parameterName = parameter.getParameterReference();
            IType parameterWireType = parameter.getWireType();
            if (parameter.isNullable()) {
                parameterWireType = parameterWireType.asNullable();
            }
            IType parameterClientType = parameter.getClientType();

            if (parameterClientType != ClassType.Base64Url && parameter.getRequestParameterLocation() != RequestParameterLocation.BODY /*&& parameter.getRequestParameterLocation() != RequestParameterLocation.FormData*/ && (parameterClientType instanceof ArrayType || parameterClientType instanceof ListType)) {
                parameterWireType = ClassType.String;
            }

            String parameterWireName = parameterClientType != parameterWireType ? String.format("%1$sConverted", CodeNamer
                .toCamelCase(CodeNamer.removeInvalidCharacters(parameterName))) : parameterName;

            String result;
            if (getMethodTransformationDetails().stream().anyMatch(d -> d.getOutParameter().getName().equals(parameterName + "1"))) {
                result = getMethodTransformationDetails().stream().filter(d -> d.getOutParameter().getName().equals(parameterName + "1")).findFirst().get().getOutParameter().getName();
            } else {
                result = parameterWireName;
            }
            return result;
        }).collect(Collectors.toList());
        return restAPIMethodArguments;
    }

    public JavaVisibility getMethodVisibility() {
        return methodVisibility;
    }

    public JavaVisibility getMethodVisibilityInWrapperClient() {
        return methodVisibilityInWrapperClient;
    }

    public ImplementationDetails getImplementationDetails() {
        return implementationDetails;
    }

    public boolean isImplementationOnly() {
        return implementationDetails != null && implementationDetails.isImplementationOnly();
    }

    public MethodPollingDetails getMethodPollingDetails() {
        return methodPollingDetails;
    }

    /**
     * Add this ClientMethod's imports to the provided ISet of imports.
     * @param imports The set of imports to add to.
     * @param includeImplementationImports Whether or not to include imports that are only necessary for method implementations.
     */
    public void addImportsTo(Set<String> imports, boolean includeImplementationImports, JavaSettings settings) {

        imports.add("com.azure.core.annotation.ServiceMethod");
        imports.add("com.azure.core.annotation.ReturnType");

        imports.add("java.util.Objects");
        imports.add("java.util.stream.Collectors");
        imports.add(Response.class.getName());
        imports.add(SimpleResponse.class.getName());

        getReturnValue().addImportsTo(imports, includeImplementationImports);

        for (ClientMethodParameter parameter : getParameters()) {
            parameter.addImportsTo(imports, includeImplementationImports);
        }

        if (includeImplementationImports) {
            ClassType.Context.addImportsTo(imports, false);

            if (proxyMethod != null) {
                proxyMethod.addImportsTo(imports, includeImplementationImports, settings);
                for (ProxyMethodParameter parameter : proxyMethod.getParameters()) {
                    parameter.getClientType().addImportsTo(imports, true);

                    if (parameter.getExplode()) {
                        imports.add("java.util.Optional");
                        imports.add("java.util.stream.Stream");
                        imports.add(ArrayList.class.getName());
                        imports.add("java.util.Collection");
                    }
                }
            }

            if (getReturnValue().getType() == ClassType.InputStream) {
                imports.add("com.fasterxml.jackson.databind.util.ByteBufferBackedInputStream");
                imports.add("java.io.SequenceInputStream");
                imports.add("java.util.Enumeration");
                imports.add("java.util.Iterator");
            }

            // Add FluxUtil as an import if this is an asynchronous method and the last parameter isn't the Context
            // parameter.
            if (proxyMethod != null && !proxyMethod.isSync()
                && (CoreUtils.isNullOrEmpty(parameters)
                    || parameters.get(parameters.size() - 1) != ClientMethodParameter.CONTEXT_PARAMETER)) {
                imports.add("com.azure.core.util.FluxUtil");
            }

            if (getMethodPageDetails() != null) {
                imports.add("com.azure.core.http.rest.PagedResponseBase");

                if (settings.isDataPlaneClient()) {
                    imports.add("java.util.List");
                    imports.add("java.util.Map");
                    ClassType.BinaryData.addImportsTo(imports, includeImplementationImports);
                }
            }

            if (type == ClientMethodType.LongRunningBeginAsync || type == ClientMethodType.LongRunningBeginSync) {
                if (settings.isFluent()) {
                    if (((GenericType) this.getReturnValue().getType().getClientType()).getTypeArguments()[0] instanceof GenericType) {
                        imports.add("com.fasterxml.jackson.core.type.TypeReference");
                    }
                } else {
                    imports.add("com.azure.core.util.serializer.TypeReference");
                    imports.add("java.time.Duration");

                    if (getMethodPollingDetails() != null) {
                        for (String pollingStrategy : KNOWN_POLLING_STRATEGIES) {
                            if (getMethodPollingDetails().getPollingStrategy().contains(pollingStrategy)
                                    || getMethodPollingDetails().getSyncPollingStrategy().contains(pollingStrategy)) {
                                imports.add("com.azure.core.util.polling." + pollingStrategy);
                            }
                        }
                    }
                }
            }

            if (type == ClientMethodType.PagingAsyncSinglePage || type == ClientMethodType.PagingSyncSinglePage && this.getMethodPageDetails() != null) {
                if (this.getMethodPageDetails() != null && this.getMethodPageDetails().getLroIntermediateType() != null) {
                    // pageable + LRO
                    this.getMethodPageDetails().getLroIntermediateType().addImportsTo(imports, includeImplementationImports);
                }
            }

            if (MethodUtil.isMethodIncludeRepeatableRequestHeaders(this.proxyMethod)) {
                // Repeatable Requests
                imports.add(UUID.class.getName());
                imports.add(OffsetDateTime.class.getName());
                imports.add(DateTimeRfc1123.class.getName());
            }

            if (type == ClientMethodType.SendRequestAsync || type == ClientMethodType.SendRequestSync) {
                imports.add(SimpleResponse.class.getName());
                ClassType.BinaryData.addImportsTo(imports, false);
                ClassType.HttpRequest.addImportsTo(imports, false);
            }
        }
    }

    public static ClientMethod getAsyncSendRequestClientMethod(boolean isInMethodGroup) {
        return new Builder()
                .name("sendRequestAsync")
                .description("Sends the {@code httpRequest}.")
                .clientReference(isInMethodGroup ? "this.client" : "this")
                .methodVisibility(JavaVisibility.Public)
                .onlyRequiredParameters(false)
                .type(ClientMethodType.SendRequestAsync)
                .parameters(Collections.singletonList(ClientMethodParameter.HTTP_REQUEST_PARAMETER))
                .returnValue(new ReturnValue("the response body on successful completion of {@link Mono}",
                        GenericType.Mono(GenericType.Response(ClassType.BinaryData))))
                .build();
    }

    public static ClientMethod getSyncSendRequestClientMethod(boolean isInMethodGroup) {
        return new Builder()
                .name("sendRequest")
                .description("Sends the {@code httpRequest}.")
                .clientReference(isInMethodGroup ? "this.client" : "this")
                .methodVisibility(JavaVisibility.Public)
                .onlyRequiredParameters(false)
                .type(ClientMethodType.SendRequestSync)
                .parameters(Arrays.asList(ClientMethodParameter.HTTP_REQUEST_PARAMETER, ClientMethodParameter.CONTEXT_PARAMETER))
                .returnValue(new ReturnValue("the response body along with {@link Response}",
                        GenericType.Response(ClassType.BinaryData)))
                .build();
    }

    public static class Builder {
        protected String description;
        protected ReturnValue returnValue;
        protected String name;
        protected List<ClientMethodParameter> parameters;
        protected boolean onlyRequiredParameters;
        protected ClientMethodType type = ClientMethodType.values()[0];
        protected ProxyMethod proxyMethod;
        protected Map<String, String> validateExpressions;
        protected String clientReference;
        protected List<String> requiredNullableParameterExpressions;
        protected boolean isGroupedParameterRequired;
        protected String groupedParameterTypeName;
        protected MethodPageDetails methodPageDetails;
        protected List<MethodTransformationDetail> methodTransformationDetails;
        protected JavaVisibility methodVisibility = JavaVisibility.Public;
        protected JavaVisibility methodVisibilityInWrapperClient = JavaVisibility.Public;
        protected ImplementationDetails implementationDetails;
        protected MethodPollingDetails methodPollingDetails;
        protected ExternalDocumentation externalDocumentation;

        /**
         * Sets the description of this ClientMethod.
         * @param description the description of this ClientMethod
         * @return the Builder itself
         */
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets the return value of this ClientMethod.
         * @param returnValue the return value of this ClientMethod
         * @return the Builder itself
         */
        public Builder returnValue(ReturnValue returnValue) {
            this.returnValue = returnValue;
            return this;
        }

        /**
         * Sets the name of this ClientMethod.
         * @param name the name of this ClientMethod
         * @return the Builder itself
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the parameters of this ClientMethod.
         * @param parameters the parameters of this ClientMethod
         * @return the Builder itself
         */
        public Builder parameters(List<ClientMethodParameter> parameters) {
            this.parameters = parameters;
            return this;
        }

        /**
         * Sets whether or not this ClientMethod has omitted optional parameters.
         * @param onlyRequiredParameters whether or not this ClientMethod has omitted optional parameters
         * @return the Builder itself
         */
        public Builder onlyRequiredParameters(boolean onlyRequiredParameters) {
            this.onlyRequiredParameters = onlyRequiredParameters;
            return this;
        }

        /**
         * Sets the type of this ClientMethod.
         * @param type the type of this ClientMethod
         * @return the Builder itself
         */
        public Builder type(ClientMethodType type) {
            this.type = type;
            return this;
        }

        /**
         * Sets the RestAPIMethod that this ClientMethod eventually calls.
         * @param proxyMethod the RestAPIMethod that this ClientMethod eventually calls
         * @return the Builder itself
         */
        public Builder proxyMethod(ProxyMethod proxyMethod) {
            this.proxyMethod = proxyMethod;
            return this;
        }

        /**
         * Sets the expressions ( (parameters and service client properties) that need to be validated in this ClientMethod.
         * @param validateExpressions the expressions (parameters and service client properties) that need to be validated in this ClientMethod
         * @return the Builder itself
         */
        public Builder validateExpressions(Map<String, String> validateExpressions) {
            this.validateExpressions = validateExpressions;
            return this;
        }

        /**
         * Sets the reference to the service client.
         * @param clientReference the reference to the service client
         * @return the Builder itself
         */
        public Builder clientReference(String clientReference) {
            this.clientReference = clientReference;
            return this;
        }

        /**
         * Sets the parameter expressions which are required.
         * @param requiredNullableParameterExpressions the parameter expressions which are required
         * @return the Builder itself
         */
        public Builder requiredNullableParameterExpressions(List<String> requiredNullableParameterExpressions) {
            this.requiredNullableParameterExpressions = requiredNullableParameterExpressions;
            return this;
        }

        /**
         * Sets the parameter that needs to transformed before pagination.
         * @param isGroupedParameterRequired the parameter that needs to transformed before pagination
         * @return the Builder itself
         */
        public Builder groupedParameterRequired(boolean isGroupedParameterRequired) {
            this.isGroupedParameterRequired = isGroupedParameterRequired;
            return this;
        }

        /**
         * Sets the type name of groupedParameter.
         * @param groupedParameterTypeName the type name of groupedParameter
         * @return the Builder itself
         */
        public Builder groupedParameterTypeName(String groupedParameterTypeName) {
            this.groupedParameterTypeName = groupedParameterTypeName;
            return this;
        }

        /**
         * Sets the pagination information if this is a paged method.
         * @param methodPageDetails the pagination information if this is a paged method
         * @return the Builder itself
         */
        public Builder methodPageDetails(MethodPageDetails methodPageDetails) {
            this.methodPageDetails = methodPageDetails;
            return this;
        }

        /**
         * Sets the parameter transformations before calling ProxyMethod.
         * @param methodTransformationDetails the parameter transformations before calling ProxyMethod
         * @return the Builder itself
         */
        public Builder methodTransformationDetails(List<MethodTransformationDetail> methodTransformationDetails) {
            this.methodTransformationDetails = methodTransformationDetails;
            return this;
        }

        /**
         * Sets the parameter method visibility.
         * @param methodVisibility the method visibility, default is Public.
         * @return the Builder itself
         */
        public Builder methodVisibility(JavaVisibility methodVisibility) {
            this.methodVisibility = methodVisibility;
            return this;
        }

        /**
         * Sets the parameter method visibility in wrapper client.
         * @param methodVisibilityInWrapperClient the method visibility in wrapper client, default is Public.
         * @return the Builder itself
         */
        public Builder methodVisibilityInWrapperClient(JavaVisibility methodVisibilityInWrapperClient) {
            this.methodVisibilityInWrapperClient = methodVisibilityInWrapperClient;
            return this;
        }

        /**
         * Sets the polling information if this is a long running method.
         * @param methodPollingDetails the polling information
         * @return the Builder itself
         */
        public Builder methodPollingDetails(MethodPollingDetails methodPollingDetails) {
            this.methodPollingDetails = methodPollingDetails;
            return this;
        }

        /**
         * Sets the implementation details for the method.
         * @param implementationDetails the implementation details.
         * @return the Builder itself
         */
        public Builder implementationDetails(ImplementationDetails implementationDetails) {
            this.implementationDetails = implementationDetails;
            return this;
        }

        /**
         * Sets method documentation
         * @param externalDocumentation method level documentation
         * @return the Builder itself
         */
        public Builder methodDocumentation(ExternalDocumentation externalDocumentation) {
            this.externalDocumentation = externalDocumentation;
            return this;
        }

        /**
         * @return an immutable ClientMethod instance with the configurations on this builder.
         */
        public ClientMethod build() {
            return new ClientMethod(
                    description,
                    returnValue,
                    name,
                    parameters,
                    onlyRequiredParameters,
                    type,
                    proxyMethod,
                    validateExpressions,
                    clientReference,
                    requiredNullableParameterExpressions,
                    isGroupedParameterRequired,
                    groupedParameterTypeName,
                    methodPageDetails,
                    methodTransformationDetails,
                    methodVisibility,
                    methodVisibilityInWrapperClient,
                    implementationDetails,
                    methodPollingDetails,
                    externalDocumentation);
        }
    }
}
