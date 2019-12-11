// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.model.clientmodel;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.util.CodeNamer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * A ClientMethod that exists on a ServiceClient or MethodGroupClient that eventually will call a ProxyMethod.
 */
public class PagingClientMethod {
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
    private List<String> expressionsToValidate;
    private ArrayList<String> requiredNullableParameterExpressions;
    private ClientMethodParameter groupedParameter;
    private String groupedParameterTypeName;
    private MethodPageDetails methodPageDetails;
    private ArrayList<MethodTransformationDetail> methodTransformationDetails;

    /**
     * Create a new ClientMethod with the provided properties.
     * @param description The description of this ClientMethod.
     * @param returnValue The return value of this ClientMethod.
     * @param name The name of this ClientMethod.
     * @param parameters The parameters of this ClientMethod.
     * @param onlyRequiredParameters Whether or not this ClientMethod has omitted optional parameters.
     * @param type The type of this ClientMethod.
     * @param proxyMethod The ProxyMethod that this ClientMethod eventually calls.
     * @param expressionsToValidate The expressions (parameters and service client properties) that need to be validated in this ClientMethod.
     * @param requiredNullableParameterExpressions The parameter expressions which are required.
     * @param groupedParameter The parameter that needs to transformed before pagination.
     * @param groupedParameterTypeName The type name of groupedParameter.
     * @param methodPageDetails The pagination information if this is a paged method.
     * @param methodTransformationDetails The parameter transformations before calling ProxyMethod.
     */
    public PagingClientMethod(String description, com.azure.autorest.model.clientmodel.ReturnValue returnValue, String name, List<com.azure.autorest.model.clientmodel.ClientMethodParameter> parameters, boolean onlyRequiredParameters, ClientMethodType type, ProxyMethod proxyMethod, List<String> expressionsToValidate, ArrayList<String> requiredNullableParameterExpressions, ClientMethodParameter groupedParameter, String groupedParameterTypeName, MethodPageDetails methodPageDetails, ArrayList<MethodTransformationDetail> methodTransformationDetails) {
        this.description = description;
        this.returnValue = returnValue;
        this.name = name;
        this.parameters = parameters;
        this.onlyRequiredParameters = onlyRequiredParameters;
        this.type = type;
        this.proxyMethod = proxyMethod;
        this.expressionsToValidate = expressionsToValidate;
        this.requiredNullableParameterExpressions = requiredNullableParameterExpressions;
        this.groupedParameter = groupedParameter;
        this.groupedParameterTypeName = groupedParameterTypeName;
        this.methodPageDetails = methodPageDetails;
        this.methodTransformationDetails = methodTransformationDetails;
    }

    public final String getDescription() {
        return description;
    }

    public final com.azure.autorest.model.clientmodel.ReturnValue getReturnValue() {
        return returnValue;
    }

    public final String getName() {
        return name;
    }

//    public final String getClientReference()
//    {
//        return getProxyMethod().getAutoRestMethod().Group.IsNullOrEmpty() ? "this" : "this.client";
//    }

    public final List<com.azure.autorest.model.clientmodel.ClientMethodParameter> getParameters() {
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

    public final List<String> getExpressionsToValidate() {
        return expressionsToValidate;
    }

    /**
     * Get the comma-separated list of parameter declarations for this ClientMethod.
     */
    public final String getParametersDeclaration() {
        return getParameters().stream()
                .map(ClientMethodParameter::getDeclaration)
                .collect(Collectors.joining(", "));
    }

    /**
     * Get the comma-separated list of parameter names for this ClientMethod.
     */
    public final String getArgumentList() {
        return getParameters().stream()
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

    public final List<com.azure.autorest.model.clientmodel.ClientMethodParameter> getMethodParameters() {
        return getParameters().stream()
                .filter(parameter -> parameter != null && !parameter.getFromClient() && parameter.getName() != null && !parameter.getName().isEmpty())
                .sorted((p1, p2) -> Boolean.compare(p2.getIsRequired(), p1.getIsRequired()))
                .collect(Collectors.toList());
    }

    public final List<com.azure.autorest.model.clientmodel.ClientMethodParameter> getMethodNonConstantParameters() {
        return getMethodParameters().stream()
                .filter(parameter -> !parameter.getIsConstant())
                .sorted((p1, p2) -> Boolean.compare(p2.getIsRequired(), p1.getIsRequired()))
                .collect(Collectors.toList());
    }

    public final List<com.azure.autorest.model.clientmodel.ClientMethodParameter> getMethodRequiredParameters() {
        return getMethodNonConstantParameters().stream()
                .filter(parameter -> parameter.getIsRequired())
                .collect(Collectors.toList());
    }

    public final ArrayList<String> getRequiredNullableParameterExpressions() {
        return requiredNullableParameterExpressions;
    }

    public final ClientMethodParameter getGroupedParameter() {
        return groupedParameter;
    }

    public final String getGroupedParameterTypeName() {
        return groupedParameterTypeName;
    }

    public final MethodPageDetails getMethodPageDetails() {
        return methodPageDetails;
    }

    public final ArrayList<MethodTransformationDetail> getMethodTransformationDetails() {
        return methodTransformationDetails;
    }

    public final List<String> getProxyMethodArguments(JavaSettings settings) {
        List<String> restAPIMethodArguments = getProxyMethod().getParameters().stream()
                .map(parameter ->
                {
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
                    if (getMethodTransformationDetails().stream().anyMatch(d -> (parameterName + "1").equals(d.getOutParameter().getName()))) {
                        result = getMethodTransformationDetails().stream().filter(d -> (parameterName + "1").equals(d.getOutParameter().getName())).findFirst().get().getOutParameter().getName();
                    } else {
                        result = parameterWireName;
                    }
                    return result;
                }).collect(Collectors.toList());
        if (settings.getAddContextParameter()) {
            restAPIMethodArguments.add(0, "context");
        }
        return restAPIMethodArguments;
    }

    /**
     * Add this ClientMethod's imports to the provided ISet of imports.
     * @param imports The set of imports to add to.
     * @param includeImplementationImports Whether or not to include imports that are only necessary for method implementations.
     */
    public void addImportsTo(Set<String> imports, boolean includeImplementationImports, JavaSettings settings) {
        getReturnValue().addImportsTo(imports, includeImplementationImports);

        for (com.azure.autorest.model.clientmodel.ClientMethodParameter parameter : getParameters()) {
            parameter.addImportsTo(imports, includeImplementationImports);
        }

        if (includeImplementationImports) {
            if (expressionsToValidate.size() > 0) {
                imports.add(ClassType.Validator.getFullName());
            }

            // TODO: Yuck!
//            List<AutoRestParameter> methodRetrofitParameters = ProxyMethod.AutoRestMethod.LogicalParameters.Where(p => p.Location != AutoRestParameterLocation.None).ToList();
//            if (settings.IsAzureOrFluent && ProxyMethod.AutoRestMethod.Extensions.Get<bool>("nextLinkMethod") == true)
//            {
//                methodRetrofitParameters.RemoveAll(p => p.Location == AutoRestParameterLocation.Path);
//            }
//            foreach (AutoRestParameter parameter in methodRetrofitParameters)
//            {
//                AutoRestParameterLocation location = parameter.Location;
//                AutoRestIModelType parameterModelType = parameter.ModelType;
//
//                if (location != AutoRestParameterLocation.Body)
//                {
//                    if (parameterModelType.IsPrimaryType(AutoRestKnownPrimaryType.ByteArray))
//                    {
//                        imports.Add("com.azure.core.util.Base64Util");
//                    }
//                    else if (parameterModelType is AutoRestSequenceType)
//                    {
//                        imports.Add("com.azure.core.util.serializer.CollectionFormat");
//                    }
//                }
//            }
        }
    }
}