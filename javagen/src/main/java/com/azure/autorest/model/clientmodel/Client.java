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
    /**
     * Get the package infos.
     */
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
    private Client(String clientName, String clientDescription, List<EnumType> enums, List<ClientException> exceptions, List<XmlSequenceWrapper> xmlSequenceWrappers, List<ClientResponse> responseModels, List<ClientModel> models, List<PackageInfo> packageInfos, Manager manager, ServiceClient serviceClient) {
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

    public static class Builder {
        private String clientName;
        private String clientDescription;
        private List<EnumType> enums;
        private List<ClientException> exceptions;
        private List<XmlSequenceWrapper> xmlSequenceWrappers;
        private List<ClientResponse> responseModels;
        private List<ClientModel> models;
        private List<PackageInfo> packageInfos;
        private Manager manager;
        private ServiceClient serviceClient;

        /**
         * Sets the name of this service client.
         * @param clientName the name of this service client
         * @return the Builder itself
         */
        public Builder clientName(String clientName) {
            this.clientName = clientName;
            return this;
        }

        /**
         * Sets the description of this service.
         * @param clientDescription the description of this service
         * @return the Builder itself
         */
        public Builder clientDescription(String clientDescription) {
            this.clientDescription = clientDescription;
            return this;
        }

        /**
         * Sets the enum types that are used by this service.
         * @param enums the enum types that are used by this service
         * @return the Builder itself
         */
        public Builder enums(List<EnumType> enums) {
            this.enums = enums;
            return this;
        }

        /**
         * Sets the exception types that are used by this service.
         * @param exceptions the exception types that are used by this service
         * @return the Builder itself
         */
        public Builder exceptions(List<ClientException> exceptions) {
            this.exceptions = exceptions;
            return this;
        }

        /**
         * Sets the XML sequence wrappers that are used by this service.
         * @param xmlSequenceWrappers the XML sequence wrappers that are used by this service
         * @return the Builder itself
         */
        public Builder xmlSequenceWrappers(List<XmlSequenceWrapper> xmlSequenceWrappers) {
            this.xmlSequenceWrappers = xmlSequenceWrappers;
            return this;
        }

        /**
         * Sets the response models which contain the response status code, headers and body for each service method.
         * @param responseModels the response models which contain the response status code, headers and body for each service method
         * @return the Builder itself
         */
        public Builder responseModels(List<ClientResponse> responseModels) {
            this.responseModels = responseModels;
            return this;
        }

        /**
         * Sets the model types that are used by this service.
         * @param models the model types that are used by this service
         * @return the Builder itself
         */
        public Builder models(List<ClientModel> models) {
            this.models = models;
            return this;
        }

        /**
         * Sets the package infos.
         * @param packageInfos the package infos
         * @return the Builder itself
         */
        public Builder packageInfos(List<PackageInfo> packageInfos) {
            this.packageInfos = packageInfos;
            return this;
        }

        /**
         * Sets the manager for this service.
         * @param manager the manager for this service
         * @return the Builder itself
         */
        public Builder manager(Manager manager) {
            this.manager = manager;
            return this;
        }

        /**
         * Sets the serviceClient for this service.
         * @param serviceClient the serviceClient for this service
         * @return the Builder itself
         */
        public Builder serviceClient(ServiceClient serviceClient) {
            this.serviceClient = serviceClient;
            return this;
        }

        public Client build() {
            return new Client(clientName,
                    clientDescription,
                    enums,
                    exceptions,
                    xmlSequenceWrappers,
                    responseModels,
                    models,
                    packageInfos,
                    manager,
                    serviceClient);
        }
    }
}
