//====================================================================================================
//The Free Edition of C# to Java Converter limits conversion output to 100 lines per file.

//To subscribe to the Premium Edition, visit our website:
//https://www.tangiblesoftwaresolutions.com/order/order-csharp-to-java.html
//====================================================================================================

package com.azure.autorest.models.clientmodel;

import java.util.*;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

import AutoRest.Core.Model.*;
import AutoRest.Core.Utilities.*;
//C# TO JAVA CONVERTER NOTE: There is no Java equivalent to C# namespace aliases:
//using AutoRestIModelType = AutoRest.Core.Model.IModelType;
//C# TO JAVA CONVERTER NOTE: There is no Java equivalent to C# namespace aliases:
//using AutoRestKnownPrimaryType = AutoRest.Core.Model.KnownPrimaryType;
//C# TO JAVA CONVERTER NOTE: There is no Java equivalent to C# namespace aliases:
//using AutoRestMethod = AutoRest.Core.Model.Method;
//C# TO JAVA CONVERTER NOTE: There is no Java equivalent to C# namespace aliases:
//using AutoRestParameter = AutoRest.Core.Model.Parameter;
//C# TO JAVA CONVERTER NOTE: There is no Java equivalent to C# namespace aliases:
//using AutoRestParameterLocation = AutoRest.Core.Model.ParameterLocation;
//C# TO JAVA CONVERTER NOTE: There is no Java equivalent to C# namespace aliases:
//using AutoRestSequenceType = AutoRest.Core.Model.SequenceType;

/** 
 A ClientMethod that exists on a ServiceClient or MethodGroupClient that eventually will call a ProxyMethod.
*/
public class PagingClientMethod
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
	 @param groupedParameter The parameter that needs to transformed before pagination.
	 @param groupedParameterTypeName The type name of groupedParameter.
	 @param methodPageDetails The pagination information if this is a paged method.
	 @param methodTransformationDetails The parameter transformations before calling ProxyMethod.
	*/
	public PagingClientMethod(String description, ReturnValue returnValue, String name, List<ClientMethodParameter> parameters, boolean onlyRequiredParameters, ClientMethodType type, ProxyMethod proxyMethod, List<String> expressionsToValidate, ArrayList<String> requiredNullableParameterExpressions, Parameter groupedParameter, String groupedParameterTypeName, MethodPageDetails methodPageDetails, ArrayList<MethodTransformationDetail> methodTransformationDetails)
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
		GroupedParameter = groupedParameter;
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

	public final String getClientReference()
	{
		return getProxyMethod().getAutoRestMethod().Group.IsNullOrEmpty() ? "this" : "this.client";
	}

	/** 
	 Get the comma-separated list of parameter declarations for this ClientMethod.
	*/
	public final String getParametersDeclaration()
	{
		return tangible.StringHelper.join(", ", getParameters().Select((ClientMethodParameter parameter) -> parameter.getDeclaration()));
	}

	/** 
	 Get the comma-separated list of parameter names for this ClientMethod.
	*/
	public final String getArgumentList()
	{
		return tangible.StringHelper.join(", ", getParameters().Select((ClientMethodParameter parameter) -> parameter.getName()));
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
		return getParameters().stream().filter(parameter -> parameter != null && !parameter.FromClient && !tangible.StringHelper.isNullOrWhiteSpace(parameter.Name)).sorted(item -> !item.IsRequired);
	}
	public final List<ClientMethodParameter> getMethodNonConstantParameters()
	{
		return getMethodParameters().stream().filter(parameter -> !parameter.IsConstant).sorted(parameter -> !parameter.IsRequired);
	}

	public final List<ClientMethodParameter> getMethodRequiredParameters()
	{
		return getMethodNonConstantParameters().Where(parameter -> parameter.IsRequired);
	}

	private ArrayList<String> RequiredNullableParameterExpressions;
	public final ArrayList<String> getRequiredNullableParameterExpressions()
	{
		return RequiredNullableParameterExpressions;
	}

	private Parameter GroupedParameter;
	public final Parameter getGroupedParameter()
	{
		return GroupedParameter;
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
		List<String> restAPIMethodArguments = getProxyMethod().getParameters().Select(parameter ->
		{
					String parameterName = parameter.ParameterReference;
					IType parameterWireType = parameter.WireType;
					if (parameter.IsNullable)
					{
						parameterWireType = parameterWireType.AsNullable();
					}
					IType parameterClientType = parameter.ClientType;

					if (parameterClientType != ClassType.Base64Url && parameter.RequestParameterLocation != RequestParameterLocation.Body && parameter.RequestParameterLocation != RequestParameterLocation.FormData && (parameterClientType instanceof ArrayType || parameterClientType instanceof ListType))
					{
						parameterWireType = ClassType.String;
					}

					String parameterWireName = parameterClientType != parameterWireType ? String.format("%1$sConverted", parameterName.ToCamelCase()) : parameterName;

					String result;
					/*if (settings.ShouldGenerateXmlSerialization && parameterWireType is ListType)
					{
					    // used to be $"new {parameterWireType.XmlName.ToPascalCase()}Wrapper({parameterWireName})"
					    result = $"new {parameterWireType.ToString().ToPascalCase()}Wrapper({parameterWireName})";
					}
					else */
					if (getMethodTransformationDetails().Any(d -> d.OutParameterName == parameterName + "1"))
					{
						result = getMethodTransformationDetails().First(d -> d.OutParameterName == parameterName + "1").OutParameterName;
					}
					else
					{
						result = parameterWireName;
					}
					return result;
		});
		if (settings.AddContextParameter)
		{
			restAPIMethodArguments = new String[] {"context"}.Concat(restAPIMethodArguments);
		}
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

		for (ClientMethodParameter parameter : getParameters())
		{
			parameter.AddImportsTo(imports, includeImplementationImports);
		}


//====================================================================================================
//End of the allowed output for the Free Edition of C# to Java Converter.

//To subscribe to the Premium Edition, visit our website:
//https://www.tangiblesoftwaresolutions.com/order/order-csharp-to-java.html
//====================================================================================================
