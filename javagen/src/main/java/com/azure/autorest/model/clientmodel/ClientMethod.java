//====================================================================================================
//The Free Edition of C# to Java Converter limits conversion output to 100 lines per file.

//To subscribe to the Premium Edition, visit our website:
//https://www.tangiblesoftwaresolutions.com/order/order-csharp-to-java.html
//====================================================================================================

package com.azure.autorest.model.clientmodel;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.util.CodeNamer;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

/**
 * A ClientMethod that exists on a ServiceClient or MethodGroupClient that eventually will call a ProxyMethod.
 */
public class ClientMethod {
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
    private String clientReference;
    private List<String> requiredNullableParameterExpressions;
    private boolean isGroupedParameterRequired;
    private String groupedParameterTypeName;
    private MethodPageDetails methodPageDetails;
    private List<MethodTransformationDetail> methodTransformationDetails;

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
     * @param requiredNullableParameterExpressions The parameter expressions which are required.
     * @param isGroupedParameterRequired The parameter that needs to transformed before pagination.
     * @param groupedParameterTypeName The type name of groupedParameter.
     * @param methodPageDetails The pagination information if this is a paged method.
     * @param methodTransformationDetails The parameter transformations before calling ProxyMethod.
     */
    public ClientMethod(String description, ReturnValue returnValue, String name, List<ClientMethodParameter> parameters, boolean onlyRequiredParameters, ClientMethodType type, ProxyMethod proxyMethod, Map<String, String> validateExpressions, List<String> requiredNullableParameterExpressions, boolean isGroupedParameterRequired, String groupedParameterTypeName, MethodPageDetails methodPageDetails, List<MethodTransformationDetail> methodTransformationDetails) {
        this.description = description;
        this.returnValue = returnValue;
        this.name = name;
        this.parameters = parameters;
        this.onlyRequiredParameters = onlyRequiredParameters;
        this.type = type;
        this.proxyMethod = proxyMethod;
        this.validateExpressions = validateExpressions;
        this.requiredNullableParameterExpressions = requiredNullableParameterExpressions;
        this.isGroupedParameterRequired = isGroupedParameterRequired;
        this.groupedParameterTypeName = groupedParameterTypeName;
        this.methodPageDetails = methodPageDetails;
        this.methodTransformationDetails = methodTransformationDetails;
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
        return getParameters().stream().map(ClientMethodParameter::getDeclaration).collect(Collectors.joining(", "));
    }

    /**
     * Get the comma-separated list of parameter names for this ClientMethod.
     */
    public final String getArgumentList() {
        return getParameters().stream()
                .filter(p -> p.getClientType() != ClassType.OutputStream)
                .map(ClientMethodParameter::getName)
                .collect(Collectors.joining(", "));
    }

    /**
     * The full declaration of this ClientMethod.
     */
    public final String getDeclaration() {
        return String.format("%1$s %2$s(%3$s)", getReturnValue().getType(), getName(), getParametersDeclaration());
    }

    public final String getPagingAsyncSinglePageMethodName() {
        return getProxyMethod().getName() + "SinglePageAsync";
    }

    public final String getSimpleAsyncMethodName() {
        return getProxyMethod().getName() + "Async";
    }

    public final List<ClientMethodParameter> getMethodParameters() {
        return getParameters().stream().filter(parameter -> parameter != null && !parameter.getFromClient() &&
                parameter.getName() != null && parameter.getName().trim().isEmpty())
                .sorted((p1, p2) -> Boolean.compare(!p1.getIsRequired(), !p2.getIsRequired()))
                .collect(Collectors.toList());
    }

    public final List<ClientMethodParameter> getMethodNonConstantParameters() {
        return getMethodParameters().stream().filter(parameter -> !parameter.getIsConstant())
                .sorted((p1, p2) -> Boolean.compare(!p1.getIsRequired(), !p2.getIsRequired()))
                .collect(Collectors.toList());
    }

    public final List<ClientMethodParameter> getMethodRequiredParameters() {
        return getMethodNonConstantParameters().stream().filter(ClientMethodParameter::getIsRequired).collect(Collectors.toList());
    }

    public final List<String> getRequiredNullableParameterExpressions() {
        return requiredNullableParameterExpressions;
    }

    public final boolean getIsGroupedParameterRequired() {
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

    public final List<String> getProxyMethodArguments(JavaSettings settings) {
        List<String> restAPIMethodArguments = getProxyMethod().getParameters().stream().map(parameter -> {
            String parameterName = parameter.getParameterReference();
            IType parameterWireType = parameter.getWireType();
            if (parameter.getIsNullable()) {
                parameterWireType = parameterWireType.asNullable();
            }
            IType parameterClientType = parameter.getClientType();

            if (parameterClientType != ClassType.Base64Url && parameter.getRequestParameterLocation() != RequestParameterLocation.Body /*&& parameter.getRequestParameterLocation() != RequestParameterLocation.FormData*/ && (parameterClientType instanceof ArrayType || parameterClientType instanceof ListType)) {
                parameterWireType = ClassType.String;
            }

            String parameterWireName = parameterClientType != parameterWireType ? String.format("%1$sConverted", CodeNamer.toCamelCase(parameterName)) : parameterName;

            String result;
                /*if (settings.ShouldGenerateXmlSerialization && parameterWireType is ListType)
                {
                    // used to be $"new {parameterWireType.XmlName.ToPascalCase()}Wrapper({parameterWireName})"
                    result = $"new {parameterWireType.ToString().ToPascalCase()}Wrapper({parameterWireName})";
                }
                else */
            if (getMethodTransformationDetails().stream().anyMatch(d -> d.getOutParameter().getName().equals(parameterName + "1"))) {
                result = getMethodTransformationDetails().stream().filter(d -> d.getOutParameter().getName().equals(parameterName + "1")).findFirst().get().getOutParameter().getName();
            } else {
                result = parameterWireName;
            }
            return result;
        }).collect(Collectors.toList());
        return restAPIMethodArguments;
    }

    /**
     * Add this ClientMethod's imports to the provided ISet of imports.
     * @param imports The set of imports to add to.
     * @param includeImplementationImports Whether or not to include imports that are only necessary for method implementations.
     */
    public void addImportsTo(Set<String> imports, boolean includeImplementationImports, JavaSettings settings) {
        getReturnValue().addImportsTo(imports, includeImplementationImports);

        imports.add("com.azure.core.annotation.ServiceMethod");
        imports.add("com.azure.core.annotation.ReturnType");

        for (ClientMethodParameter parameter : getParameters()) {
            parameter.addImportsTo(imports, includeImplementationImports);
        }

        if (getMethodPageDetails() != null) {
            imports.add("com.azure.core.http.rest.PagedResponseBase");
        }

        if (includeImplementationImports) {
            proxyMethod.addImportsTo(imports, includeImplementationImports, settings);
            for (ProxyMethodParameter parameter : proxyMethod.getParameters()) {
                parameter.getClientType().addImportsTo(imports, true);
            }

            if (getParameters().stream().anyMatch(p -> ClassType.OutputStream == p.getClientType())) {
                imports.add("com.azure.core.util.FluxUtil");
                imports.add("java.io.IOException");
            }
        }
    }
}