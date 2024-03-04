// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a client.
 */
public class Client extends Metadata {
    private String summary;
    private List<OperationGroup> operationGroups = new ArrayList<>();
    private List<Parameter> globalParameters = new ArrayList<>();
    private Security security;
    private List<ApiVersion> apiVersions = new ArrayList<>();
    private ServiceVersion serviceVersion;
    private String crossLanguageDefinitionId;

    /**
     * Creates a new instance of the Client class.
     */
    public Client() {
        super();
    }

    /**
     * Gets the summary of the client.
     *
     * @return The summary of the client.
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets the summary of the client.
     *
     * @param summary The summary of the client.
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Gets the operation groups of the client.
     *
     * @return The operation groups of the client.
     */
    public List<OperationGroup> getOperationGroups() {
        return operationGroups;
    }

    /**
     * Sets the operation groups of the client.
     *
     * @param operationGroups The operation groups of the client.
     */
    public void setOperationGroups(List<OperationGroup> operationGroups) {
        this.operationGroups = operationGroups;
    }

    /**
     * Gets the global parameters of the client.
     *
     * @return The global parameters of the client.
     */
    public List<Parameter> getGlobalParameters() {
        return globalParameters;
    }

    /**
     * Sets the global parameters of the client.
     *
     * @param globalParameters The global parameters of the client.
     */
    public void setGlobalParameters(List<Parameter> globalParameters) {
        this.globalParameters = globalParameters;
    }

    /**
     * Gets the security of the client.
     *
     * @return The security of the client.
     */
    public Security getSecurity() {
        return security;
    }

    /**
     * Sets the security of the client.
     *
     * @param security The security of the client.
     */
    public void setSecurity(Security security) {
        this.security = security;
    }

    /**
     * Gets the API versions of the client.
     *
     * @return The API versions of the client.
     */
    public List<ApiVersion> getApiVersions() {
        return apiVersions;
    }

    /**
     * Sets the API versions of the client.
     *
     * @param apiVersions The API versions of the client.
     */
    public void setApiVersions(List<ApiVersion> apiVersions) {
        this.apiVersions = apiVersions;
    }

    /**
     * Gets the service version of the client.
     *
     * @return The service version of the client.
     */
    public ServiceVersion getServiceVersion() {
        return serviceVersion;
    }

    /**
     * Sets the service version of the client.
     *
     * @param serviceVersion The service version of the client.
     */
    public void setServiceVersion(ServiceVersion serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    /**
     * Gets the cross-language definition id of the client.
     *
     * @return The cross-language definition id of the client.
     */
    public String getCrossLanguageDefinitionId() {
        return crossLanguageDefinitionId;
    }

    /**
     * Sets the cross-language definition id of the client.
     *
     * @param crossLanguageDefinitionId The cross-language definition id of the client.
     */
    public void setCrossLanguageDefinitionId(String crossLanguageDefinitionId) {
        this.crossLanguageDefinitionId = crossLanguageDefinitionId;
    }
}
