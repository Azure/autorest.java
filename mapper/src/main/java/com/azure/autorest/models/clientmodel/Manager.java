package com.azure.autorest.models.clientmodel;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/** 
 The details needed to create a Manager class for the client.
*/
public class Manager
{
	/** 
	 Create a new Manager with the provided properties.
	 
	 @param package The package of this manager class.
	 @param serviceClientName The name of the service client.
	 @param serviceName The name of the service.
	 @param azureTokenCredentialsParameter The credentials parameter.
	 @param httpPipelineParameter The HttpPipeline parameter.
	*/
	public Manager(String package_Keyword, String serviceClientName, String serviceName, Lazy<ClientMethodParameter> azureTokenCredentialsParameter, Lazy<ClientMethodParameter> httpPipelineParameter)
	{
		Package = package_Keyword;
		ServiceClientName = serviceClientName;
		ServiceName = serviceName;
		AzureTokenCredentialsParameter = azureTokenCredentialsParameter;
		HttpPipelineParameter = httpPipelineParameter;
	}

	private String Package;
	public final String getPackage()
	{
		return Package;
	}

	/** 
	 The name of the service client.
	*/
	private String ServiceClientName;
	public final String getServiceClientName()
	{
		return ServiceClientName;
	}

	/** 
	 The name of the service.
	*/
	private String ServiceName;
	public final String getServiceName()
	{
		return ServiceName;
	}

	private Lazy<ClientMethodParameter> AzureTokenCredentialsParameter;
	public final Lazy<ClientMethodParameter> getAzureTokenCredentialsParameter()
	{
		return AzureTokenCredentialsParameter;
	}

	private Lazy<ClientMethodParameter> HttpPipelineParameter;
	public final Lazy<ClientMethodParameter> getHttpPipelineParameter()
	{
		return HttpPipelineParameter;
	}
}