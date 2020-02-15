package com.azure.autorest.model.clientmodel;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


import java.util.List;

/**
 * A container for the types associated for accessing a specific service.
 */
public class Client {
    /**
     * The name of this service client.
     */
    private String clientName;
    /**
     * The description of this service.
     */
    private String clientDescription;
    /**
     * Get the enum types that are used by this service.
     */
    private List<EnumType> enums;
    /**
     * Get the exception types that are used by this service.
     */
    private List<ClientException> exceptions;
    /**
     * Get the XML sequence wrappers that are used by this service.
     */
    private List<XmlSequenceWrapper> xmlSequenceWrappers;
    /**
     * Get the response models which contain the response status code, headers and body for each service method.
     */
    private List<ClientResponse> responseModels;
    /**
     * Get the model types that are used by this service.
     */
    private List<ClientModel> models;
    private List<PackageInfo> packageInfos;
    /**
     * Get the manager for this service.
     */
    private Manager manager;
    /**
     * The serviceClient for this service.
     */
    private ServiceClient serviceClient;

    /**
     * Create a new Client with the provided values.
     * @param clientName The name of the service client.
     * @param clientDescription The description of the service client.
     * @param enums The enum types that are used by the client.
     * @param exceptions The exception types that are used by the client.
     * @param xmlSequenceWrappers the xml wrapper types that are used by the client.
     * @param models the client models that are used by the client.
     * @param packageInfos the package-info classes that are used by the client.
     * @param manager the manager class that is used by the client.
     * @param serviceClient the service client that is used by the client.
     */
    public Client(String clientName, String clientDescription, List<EnumType> enums, List<ClientException> exceptions, List<XmlSequenceWrapper> xmlSequenceWrappers, List<ClientResponse> responseModels, List<ClientModel> models, List<PackageInfo> packageInfos, Manager manager, ServiceClient serviceClient) {
        this.clientName = clientName;
        this.clientDescription = clientDescription;
        this.enums = enums;
        this.exceptions = exceptions;
        this.xmlSequenceWrappers = xmlSequenceWrappers;
        this.responseModels = responseModels;
        this.models = models;
        this.packageInfos = packageInfos;
        this.manager = manager;
        this.serviceClient = serviceClient;
    }

    public final String getClientName() {
        return clientName;
    }

    public final String getClientDescription() {
        return clientDescription;
    }

    public final List<EnumType> getEnums() {
        return enums;
    }

    public final List<ClientException> getExceptions() {
        return exceptions;
    }

    public final List<XmlSequenceWrapper> getXmlSequenceWrappers() {
        return xmlSequenceWrappers;
    }

    public final List<ClientResponse> getResponseModels() {
        return responseModels;
    }

    public final List<ClientModel> getModels() {
        return models;
    }

    public final List<PackageInfo> getPackageInfos() {
        return packageInfos;
    }

    public final Manager getManager() {
        return manager;
    }

    public final ServiceClient getServiceClient() {
        return serviceClient;
    }
}
