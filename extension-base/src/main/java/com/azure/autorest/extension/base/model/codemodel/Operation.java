// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * represents a single callable endpoint with a discrete set of inputs, and any number of output possibilities (responses or exceptions)
 * 
 */
public class Operation extends Metadata {

    private String operationId;

    private List<Parameter> parameters = new ArrayList<Parameter>();

    private List<Parameter> signatureParameters = new ArrayList<>();
    /**
     * 
     * (Required)
     * 
     */
    private List<Request> requests;
    /**
     * responses that indicate a successful call
     * 
     */
    private List<Response> responses = new ArrayList<Response>();
    /**
     * responses that indicate a failed call
     * 
     */
    private List<Response> exceptions = new ArrayList<Response>();
    private DictionaryApiVersion profile;
    /**
     * 
     * (Required)
     * 
     */
    private String $key;
    /**
     * 
     * (Required)
     * 
     */
    private String description;
    /**
     * 
     * (Required)
     * 
     */
    private String uid;
    /**
     * a short description
     * 
     */
    private String summary;
    /**
     * API versions that this applies to. Undefined means all versions
     * 
     */
    private List<ApiVersion> apiVersions = new ArrayList<ApiVersion>();
    /**
     * represents  deprecation information for a given aspect
     * 
     */
    private Deprecation deprecated;
    /**
     * a reference to external documentation
     * 
     */
    private ExternalDocumentation externalDocs;

    private List<String> specialHeaders;

    private Map<String, OperationLink> operationLinks;

    private ConvenienceApi convenienceApi;

    /**
     * a boolean to represent should generate protocol api or not, default is true
     */
    private boolean generateProtocolApi = true;

    // internal
    private OperationGroup operationGroup;

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    /**
     * 
     * (Required)
     * 
     */
    public List<Request> getRequests() {
        return requests;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    /**
     * responses that indicate a successful call
     * 
     */
    public List<Response> getResponses() {
        return responses;
    }

    /**
     * responses that indicate a successful call
     * 
     */
    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

    /**
     * responses that indicate a failed call
     * 
     */
    public List<Response> getExceptions() {
        return exceptions;
    }

    /**
     * responses that indicate a failed call
     * 
     */
    public void setExceptions(List<Response> exceptions) {
        this.exceptions = exceptions;
    }

    public DictionaryApiVersion getProfile() {
        return profile;
    }

    public void setProfile(DictionaryApiVersion profile) {
        this.profile = profile;
    }

    /**
     * 
     * (Required)
     * 
     */
    public String get$key() {
        return $key;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void set$key(String $key) {
        this.$key = $key;
    }

    /**
     * 
     * (Required)
     * 
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * (Required)
     * 
     */
    public String getUid() {
        return uid;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * a short description
     * 
     */
    public String getSummary() {
        return summary;
    }

    /**
     * a short description
     * 
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * API versions that this applies to. Undefined means all versions
     * 
     */
    public List<ApiVersion> getApiVersions() {
        return apiVersions;
    }

    /**
     * API versions that this applies to. Undefined means all versions
     * 
     */
    public void setApiVersions(List<ApiVersion> apiVersions) {
        this.apiVersions = apiVersions;
    }

    /**
     * represents  deprecation information for a given aspect
     * 
     */
    public Deprecation getDeprecated() {
        return deprecated;
    }

    /**
     * represents  deprecation information for a given aspect
     * 
     */
    public void setDeprecated(Deprecation deprecated) {
        this.deprecated = deprecated;
    }

    /**
     * a reference to external documentation
     * 
     */
    public ExternalDocumentation getExternalDocs() {
        return externalDocs;
    }

    /**
     * a reference to external documentation
     * 
     */
    public void setExternalDocs(ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
    }

    public OperationGroup getOperationGroup() {
        return operationGroup;
    }

    public void setOperationGroup(OperationGroup operationGroup) {
        this.operationGroup = operationGroup;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public List<Parameter> getSignatureParameters() {
        return signatureParameters;
    }

    public void setSignatureParameters(List<Parameter> signatureParameters) {
        this.signatureParameters = signatureParameters;
    }

    /**
     * @return headers that require special processing, e.g. Repeatability-Request-ID
     */
    public List<String> getSpecialHeaders() {
        return specialHeaders;
    }

    public void setSpecialHeaders(List<String> specialHeaders) {
        this.specialHeaders = specialHeaders;
    }

    /**
     * @return the operation links, e.g. "polling" for the status monitor operation.
     */
    public Map<String, OperationLink> getOperationLinks() {
        return operationLinks;
    }

    public void setOperationLinks(Map<String, OperationLink> operationLinks) {
        this.operationLinks = operationLinks;
    }

    /**
     * @return the configuration of convenience API
     */
    public ConvenienceApi getConvenienceApi() {
        return convenienceApi;
    }

    public void setConvenienceApi(ConvenienceApi convenienceApi) {
        this.convenienceApi = convenienceApi;
    }

    public boolean isGenerateProtocolApi() {
        return generateProtocolApi;
    }

    public void setGenerateProtocolApi(boolean generateProtocolApi) {
        this.generateProtocolApi = generateProtocolApi;
    }


}
