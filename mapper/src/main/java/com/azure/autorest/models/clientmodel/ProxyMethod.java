//====================================================================================================
//The Free Edition of C# to Java Converter limits conversion output to 100 lines per file.

//To subscribe to the Premium Edition, visit our website:
//https://www.tangiblesoftwaresolutions.com/order/order-csharp-to-java.html
//====================================================================================================

package com.azure.autorest.models.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.models.codemodel.HttpMethod;
import com.azure.autorest.models.codemodel.HttpOperation;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.List;
import java.util.Set;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.
//C# TO JAVA CONVERTER NOTE: There is no Java equivalent to C# namespace aliases:
//using AutoRestMethod = AutoRest.Core.Model.Method;

/** 
 A method within a Proxy.
*/
public class ProxyMethod
{
	/** 
	 Create a new RestAPIMethod with the provided properties.
	 
	 @param requestContentType The Content-Type of the request.
	 @param returnType The type of value that is returned from this method.
	 @param isPagingNextOperation Whether or not this method is a request to get the next page of a sequence of pages.
	 @param httpMethod The HTTP method that will be used for this method.
	 @param urlPath The path of this method's request URL.
	 @param responseExpectedStatusCodes The status codes that are expected in the response.
	 @param returnValueWireType The return value's type as it is received from the network (across the wire).
	 @param unexpectedResponseExceptionType The exception type to throw if this method receives and unexpected response status code.
	 @param name The name of this REST API method.
	 @param asyncReturnType The return type of this method with its asynchronous container.
	 @param parameters The parameters that are provided to this method.
	 @param description The description of this method.
	 @param returnValueClientType The return value's type as it is returned from the client.
	 @param autoRestMethod The AutoRestMethod that this RestAPIMethod was created from.
	 @param isResumable Whether or not this method is resumable.
	*/
	public ProxyMethod(String requestContentType, IType returnType, boolean isPagingNextOperation, HttpMethod httpMethod, String urlPath, List<HttpStatusCode> responseExpectedStatusCodes, ClassType unexpectedResponseExceptionType, String name, List<ProxyMethodParameter> parameters, String description, IType returnValueWireType, HttpOperation autoRestMethod, boolean isResumable)
	{
		RequestContentType = requestContentType;
		ReturnType = returnType;
		IsPagingNextOperation = isPagingNextOperation;
		HttpMethod = httpMethod;
		UrlPath = urlPath;
		ResponseExpectedStatusCodes = responseExpectedStatusCodes;
		UnexpectedResponseExceptionType = unexpectedResponseExceptionType;
		Name = name;
		Parameters = parameters;
		Description = description;
		ReturnValueWireType = returnValueWireType;
		AutoRestMethod = autoRestMethod;
		IsResumable = isResumable;
	}

	/** 
	 Get the Content-Type of the request.
	*/
	private String RequestContentType;
	public final String getRequestContentType()
	{
		return RequestContentType;
	}

	/** 
	 The value that is returned from this method.
	*/
	private IType ReturnType;
	public final IType getReturnType()
	{
		return ReturnType;
	}

	/** 
	 Get whether or not this method is a request to get the next page of a sequence of pages.
	*/
	private boolean IsPagingNextOperation;
	public final boolean getIsPagingNextOperation()
	{
		return IsPagingNextOperation;
	}

	/** 
	 Get the HTTP method that will be used for this method.
	*/
	private HttpMethod HttpMethod;
	public final HttpMethod getHttpMethod()
	{
		return HttpMethod;
	}

	/** 
	 Get the path of this method's request URL.
	*/
	private String UrlPath;
	public final String getUrlPath()
	{
		return UrlPath;
	}

	/** 
	 Get the status codes that are expected in the response.
	*/
	private List<HttpStatusCode> ResponseExpectedStatusCodes;
	public final List<HttpStatusCode> getResponseExpectedStatusCodes()
	{
		return ResponseExpectedStatusCodes;
	}

	/** 
	 Get the exception type to throw if this method receives and unexpected response status code.
	*/
	private ClassType UnexpectedResponseExceptionType;
	public final ClassType getUnexpectedResponseExceptionType()
	{
		return UnexpectedResponseExceptionType;
	}

	/** 
	 Get the name of this Rest API method.
	*/
	private String Name;
	public final String getName()
	{
		return Name;
	}

	/** 
	 Get the parameters that are provided to this method.
	*/
	private List<ProxyMethodParameter> Parameters;
	public final List<ProxyMethodParameter> getParameters()
	{
		return Parameters;
	}

	/** 
	 Get the description of this method.
	*/
	private String Description;
	public final String getDescription()
	{
		return Description;
	}

	/** 
	 The value of the ReturnValueWireType annotation for this method.
	*/
	private IType ReturnValueWireType;
	public final IType getReturnValueWireType()
	{
		return ReturnValueWireType;
	}

	/** 
	 The AutoRestMethod that this RestAPIMethod was created from.
	*/
	private HttpOperation AutoRestMethod;
	public final HttpOperation getAutoRestMethod()
	{
		return AutoRestMethod;
	}

	/** 
	 Get whether or not this method resumes polling of an LRO.
	*/
	private boolean IsResumable;
	public final boolean getIsResumable()
	{
		return IsResumable;
	}

	public final String getPagingAsyncSinglePageMethodName()
	{
		return getName() + "SinglePageAsync";
	}

	public final String getSimpleAsyncMethodName()
	{
		return getName() + "Async";
	}

	public final String getSimpleAsyncRestResponseMethodName()
	{
		return getName() + "WithRestResponseAsync";
	}

//	private MethodType _methodType = null;
//	public final MethodType getMethodType()
//	{
//		if (_methodType != null)
//		{
//			return _methodType.getValue();
//		}
//		_methodType = getMethodType().Other;
//		if (!tangible.StringHelper.isNullOrEmpty(getAutoRestMethod().MethodGroup == null ? null : (getAutoRestMethod().MethodGroup.Name == null ? null : getAutoRestMethod().MethodGroup.Name.toString())))
//		{
//			String autoRestMethodUrl = (new Regex("/+$")).Replace((new Regex("^/+")).Replace(getUrlPath(), ""), "");
//			String[] autoRestMethodUrlSplits = autoRestMethodUrl.split("[/]", -1);
//			switch (getAutoRestMethod().HttpMethod)
//			{
//				case getHttpMethod().Get:
//					if ((autoRestMethodUrlSplits.length == 5 || autoRestMethodUrlSplits.length == 7) && autoRestMethodUrlSplits[0].EqualsIgnoreCase("subscriptions") && getAutoRestMethod().ReturnType.Body.MethodHasSequenceType())
//					{
//						if (autoRestMethodUrlSplits.length == 5)
//						{
//							if (autoRestMethodUrlSplits[2].EqualsIgnoreCase("providers"))
//							{
//								_methodType = getMethodType().ListBySubscription;
//							}
//							else
//							{
//								_methodType = getMethodType().ListByResourceGroup;
//							}
//						}
//						else if (autoRestMethodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
//						{
//							_methodType = getMethodType().ListByResourceGroup;
//						}
//					}
//					else if (autoRestMethodUrlSplits.IsTopLevelResourceUrl())
//					{
//						_methodType = getMethodType().Get;
//					}
//					break;
//
//				case getHttpMethod().Delete:
//					if (autoRestMethodUrlSplits.IsTopLevelResourceUrl())
//					{
//						_methodType = getMethodType().Delete;
//					}
//					break;
//			}
//		}
//		return _methodType.getValue();
//	}

	/** 
	 Add this property's imports to the provided ISet of imports.
	 
	 @param imports The set of imports to add to.
	 @param includeImplementationImports Whether or not to include imports that are only necessary for method implementations.
	*/
	public final void AddImportsTo(Set<String> imports, boolean includeImplementationImports, JavaSettings settings)
	{

		if (includeImplementationImports)
		{
			if (getUnexpectedResponseExceptionType() != null)
			{
				imports.add("com.azure.core.implementation.annotation.UnexpectedResponseExceptionType");
				getUnexpectedResponseExceptionType().AddImportsTo(imports, includeImplementationImports);
			}
			if (getIsResumable())
			{
				imports.add("com.azure.core.implementation.annotation.ResumeOperation");
			}
			imports.add(String.format("com.azure.core.implementation.annotation.%1$s", getHttpMethod().toString().ToPascalCase()));

			imports.add("com.azure.core.implementation.annotation.ExpectedResponses");

			if (getReturnValueWireType() != null)
			{

//====================================================================================================
//End of the allowed output for the Free Edition of C# to Java Converter.

//To subscribe to the Premium Edition, visit our website:
//https://www.tangiblesoftwaresolutions.com/order/order-csharp-to-java.html
//====================================================================================================
