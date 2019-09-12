package com.azure.autorest.models.clientmodel;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


import java.util.List;

/**
 A container for the types associated for accessing a specific service.
*/
public class Client
{
	/** 
	 Create a new Client with the provided values.
	 
	 @param clientName The name of the service client.
	 @param clientDescription The description of the service client.
	 @param enums The enum types that are used by the client.
	 @param exceptions The exception types that are used by the client.
	 @param xmlSequenceWrappers the xml wrapper types that are used by the client.
	 @param models the client models that are used by the client.
	 @param packageInfos the package-info classes that are used by the client.
	 @param manager the manager class that is used by the client.
	 @param serviceClient the service client that is used by the client.
	*/
	public Client(String clientName, String clientDescription, List<EnumType> enums, List<ClientException> exceptions, List<XmlSequenceWrapper> xmlSequenceWrappers, List<ClientResponse> responseModels, List<ClientModel> models, List<PackageInfo> packageInfos, Manager manager, ServiceClient serviceClient)
	{
		ClientName = clientName;
		ClientDescription = clientDescription;
		Enums = enums;
		Exceptions = exceptions;
		XmlSequenceWrappers = xmlSequenceWrappers;
		ResponseModels = responseModels;
		Models = models;
		PackageInfos = packageInfos;
		Manager = manager;
		ServiceClient = serviceClient;
	}

	/** 
	 The name of this service client.
	*/
	private String ClientName;
	public final String getClientName()
	{
		return ClientName;
	}

	/** 
	 The description of this service.
	*/
	private String ClientDescription;
	public final String getClientDescription()
	{
		return ClientDescription;
	}

	/** 
	 Get the enum types that are used by this service.
	*/
	private List<EnumType> Enums;
	public final List<EnumType> getEnums()
	{
		return Enums;
	}

	/** 
	 Get the exception types that are used by this service.
	*/
	private List<ClientException> Exceptions;
	public final List<ClientException> getExceptions()
	{
		return Exceptions;
	}

	/** 
	 Get the XML sequence wrappers that are used by this service.
	*/
	private List<XmlSequenceWrapper> XmlSequenceWrappers;
	public final List<XmlSequenceWrapper> getXmlSequenceWrappers()
	{
		return XmlSequenceWrappers;
	}

	/** 
	 Get the response models which contain the response status code, headers and body for each service method.
	*/
	private List<ClientResponse> ResponseModels;
	public final List<ClientResponse> getResponseModels()
	{
		return ResponseModels;
	}

	/** 
	 Get the model types that are used by this service.
	*/
	private List<ClientModel> Models;
	public final List<ClientModel> getModels()
	{
		return Models;
	}

	private List<PackageInfo> PackageInfos;
	public final List<PackageInfo> getPackageInfos()
	{
		return PackageInfos;
	}

	/** 
	 Get the Manager for this service.
	*/
	private Manager Manager;
	public final Manager getManager()
	{
		return Manager;
	}

	/** 
	 The ServiceClient for this service.
	*/
	private ServiceClient ServiceClient;
	public final ServiceClient getServiceClient()
	{
		return ServiceClient;
	}
}