//====================================================================================================
//The Free Edition of C# to Java Converter limits conversion output to 100 lines per file.

//To subscribe to the Premium Edition, visit our website:
//https://www.tangiblesoftwaresolutions.com/order/order-csharp-to-java.html
//====================================================================================================

package com.azure.autorest.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.util.CodeNamer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

/** 
 A ClientMethod that exists on a ServiceClient or MethodGroupClient that eventually will call a ProxyMethod.
*/
public class ClientMethod
{
    /**
     Create a new ClientMethod with the provided properties.

     @param description The description of this ClientMethod.
     @param returnValue The return value of this ClientMethod.
     @param name The name of this ClientMethod.
     @param parameters The parameters of this ClientMethod.
     @param onlyRequiredParameters Whether or not this ClientMethod has omitted optional parameters.
     @param type The type of this ClientMethod.
     @param proxyMethod The ProxyMethod that this ClientMethod eventually calls.
     @param expressionsToValidate The expressions (parameters and service client properties) that need to be validated in this ClientMethod.
     @param requiredNullableParameterExpressions The parameter expressions which are required.
     @param isGroupedParameterRequired The parameter that needs to transformed before pagination.
     @param groupedParameterTypeName The type name of groupedParameter.
     @param methodPageDetails The pagination information if this is a paged method.
     @param methodTransformationDetails The parameter transformations before calling ProxyMethod.
    */
    public ClientMethod(String description, ReturnValue returnValue, String name, List<ClientMethodParameter> parameters, boolean onlyRequiredParameters, ClientMethodType type, ProxyMethod proxyMethod, List<String> expressionsToValidate, ArrayList<String> requiredNullableParameterExpressions, boolean isGroupedParameterRequired, String groupedParameterTypeName, MethodPageDetails methodPageDetails, ArrayList<MethodTransformationDetail> methodTransformationDetails)
    {
        Description = description;
        ReturnValue = returnValue;
        Name = name;
        Parameters = parameters;
        OnlyRequiredParameters = onlyRequiredParameters;
        Type = type;
        ProxyMethod = proxyMethod;
        ExpressionsToValidate = expressionsToValidate;
        RequiredNullableParameterExpressions = requiredNullableParameterExpressions;
        IsGroupedParameterRequired = isGroupedParameterRequired;
        GroupedParameterTypeName = groupedParameterTypeName;
        MethodPageDetails = methodPageDetails;
        MethodTransformationDetails = methodTransformationDetails;
    }

    /**
     The description of this ClientMethod.
    */
    private String Description;
    public final String getDescription()
    {
        return Description;
    }

    /**
     The return value of this ClientMethod.
    */
    private ReturnValue ReturnValue;
    public final ReturnValue getReturnValue()
    {
        return ReturnValue;
    }

    /**
     The name of this ClientMethod.
    */
    private String Name;
    public final String getName()
    {
        return Name;
    }

    /**
     The parameters of this ClientMethod.
    */
    private List<ClientMethodParameter> Parameters;
    public final List<ClientMethodParameter> getParameters()
    {
        return Parameters;
    }

    /**
     Whether or not this ClientMethod has omitted optional parameters.
    */
    private boolean OnlyRequiredParameters;
    public final boolean getOnlyRequiredParameters()
    {
        return OnlyRequiredParameters;
    }

    /**
     The type of this ClientMethod.
    */
    private ClientMethodType Type = ClientMethodType.values()[0];
    public final ClientMethodType getType()
    {
        return Type;
    }

    /**
     The RestAPIMethod that this ClientMethod eventually calls.
    */
    private ProxyMethod ProxyMethod;
    public final ProxyMethod getProxyMethod()
    {
        return ProxyMethod;
    }

    /**
     The expressions (parameters and service client properties) that need to be validated in this ClientMethod.
    */
    private List<String> ExpressionsToValidate;
    public final List<String> getExpressionsToValidate()
    {
        return ExpressionsToValidate;
    }

//    public final String getClientReference()
//    {
//        return getProxyMethod().getAutoRestMethod().Group.IsNullOrEmpty() ? "this" : "this.client";
//    }

    /**
     Get the comma-separated list of parameter declarations for this ClientMethod.
    */
    public final String getParametersDeclaration()
    {
        return getParameters().stream().map(ClientMethodParameter::getDeclaration).collect(Collectors.joining(", "));
    }

    /**
     Get the comma-separated list of parameter names for this ClientMethod.
    */
    public final String getArgumentList()
    {
        return getParameters().stream().map(ClientMethodParameter::getName).collect(Collectors.joining(", "));
    }

    /**
     The full declaration of this ClientMethod.
    */
    public final String getDeclaration()
    {
        return String.format("%1$s %2$s(%3$s)", getReturnValue().getType(), getName(), getParametersDeclaration());
    }

    public final String getPagingAsyncSinglePageMethodName()
    {
        return getProxyMethod().getName() + "SinglePageAsync";
    }

    public final String getSimpleAsyncMethodName()
    {
        return getProxyMethod().getName() + "Async";
    }

    public final List<ClientMethodParameter> getMethodParameters()
    {
        return getParameters().stream().filter(parameter -> parameter != null && !parameter.getFromClient() &&
                parameter.getName() != null && parameter.getName().trim().isEmpty())
                .sorted((p1, p2) -> Boolean.compare(!p1.getIsRequired(), !p2.getIsRequired()))
                .collect(Collectors.toList());
    }

    public final List<ClientMethodParameter> getMethodNonConstantParameters()
    {
        return getMethodParameters().stream().filter(parameter -> !parameter.getIsConstant())
                .sorted((p1, p2) -> Boolean.compare(!p1.getIsRequired(), !p2.getIsRequired()))
                .collect(Collectors.toList());
    }

    public final List<ClientMethodParameter> getMethodRequiredParameters()
    {
        return getMethodNonConstantParameters().stream().filter(ClientMethodParameter::getIsRequired).collect(Collectors.toList());
    }

    private ArrayList<String> RequiredNullableParameterExpressions;
    public final ArrayList<String> getRequiredNullableParameterExpressions()
    {
        return RequiredNullableParameterExpressions;
    }

    private boolean IsGroupedParameterRequired;
    public final boolean getIsGroupedParameterRequired()
    {
        return IsGroupedParameterRequired;
    }

    private String GroupedParameterTypeName;
    public final String getGroupedParameterTypeName()
    {
        return GroupedParameterTypeName;
    }

    private MethodPageDetails MethodPageDetails;
    public final MethodPageDetails getMethodPageDetails()
    {
        return MethodPageDetails;
    }

    private ArrayList<MethodTransformationDetail> MethodTransformationDetails;
    public final ArrayList<MethodTransformationDetail> getMethodTransformationDetails()
    {
        return MethodTransformationDetails;
    }

    public final List<String> GetProxyMethodArguments(JavaSettings settings)
    {
        List<String> restAPIMethodArguments = getProxyMethod().getParameters().stream().map(parameter ->
        {
                    String parameterName = parameter.getParameterReference();
                    IType parameterWireType = parameter.getWireType();
                    if (parameter.getIsNullable())
                    {
                        parameterWireType = parameterWireType.AsNullable();
                    }
                    IType parameterClientType = parameter.getClientType();

                    if (parameterClientType != ClassType.Base64Url && parameter.getRequestParameterLocation() != RequestParameterLocation.Body && parameter.getRequestParameterLocation() != RequestParameterLocation.FormData && (parameterClientType instanceof ArrayType || parameterClientType instanceof ListType))
                    {
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
                    if (getMethodTransformationDetails().stream().anyMatch(d -> d.getOutParameterName().equals(parameterName + "1")))
                    {
                        result = getMethodTransformationDetails().stream().filter(d -> d.getOutParameterName().equals(parameterName + "1")).findFirst().get().getOutParameterName();
                    }
                    else
                    {
                        result = parameterWireName;
                    }
                    return result;
        }).collect(Collectors.toList());
        return restAPIMethodArguments;
    }

    /**
     Add this ClientMethod's imports to the provided ISet of imports.

     @param imports The set of imports to add to.
     @param includeImplementationImports Whether or not to include imports that are only necessary for method implementations.
    */
    public void AddImportsTo(Set<String> imports, boolean includeImplementationImports, JavaSettings settings)
    {
        getReturnValue().AddImportsTo(imports, includeImplementationImports);

        imports.add("com.azure.core.implementation.annotation.ServiceMethod");
        imports.add("com.azure.core.implementation.annotation.ReturnType");

        for (ClientMethodParameter parameter : getParameters())
        {
            parameter.AddImportsTo(imports, includeImplementationImports);
        }

        if (includeImplementationImports)
        {
            if (!ExpressionsToValidate.isEmpty() && settings.getClientSideValidations())
            {
                imports.add(ClassType.Validator.getFullName());
            }

            ProxyMethod.AddImportsTo(imports, includeImplementationImports, settings);
            for (ProxyMethodParameter parameter : ProxyMethod.getParameters()) {
                parameter.getClientType().AddImportsTo(imports, true);
            }
        }
    }
}