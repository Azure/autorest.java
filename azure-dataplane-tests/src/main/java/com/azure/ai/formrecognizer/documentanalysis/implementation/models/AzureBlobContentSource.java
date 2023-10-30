// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.formrecognizer.documentanalysis.implementation.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Azure Blob Storage content.
 */
@Fluent
public final class AzureBlobContentSource {
    /*
     * Azure Blob Storage container URL.
     */
    @JsonProperty(value = "containerUrl", required = true)
    private String containerUrl;

    /*
     * Blob name prefix.
     */
    @JsonProperty(value = "prefix")
    private String prefix;

    /**
     * Creates an instance of AzureBlobContentSource class.
     */
    public AzureBlobContentSource() {
    }

    /**
     * Get the containerUrl property: Azure Blob Storage container URL.
     * 
     * @return the containerUrl value.
     */
    public String getContainerUrl() {
        return this.containerUrl;
    }

    /**
     * Set the containerUrl property: Azure Blob Storage container URL.
     * 
     * @param containerUrl the containerUrl value to set.
     * @return the AzureBlobContentSource object itself.
     */
    public AzureBlobContentSource setContainerUrl(String containerUrl) {
        this.containerUrl = containerUrl;
        return this;
    }

    /**
     * Get the prefix property: Blob name prefix.
     * 
     * @return the prefix value.
     */
    public String getPrefix() {
        return this.prefix;
    }

    /**
     * Set the prefix property: Blob name prefix.
     * 
     * @param prefix the prefix value to set.
     * @return the AzureBlobContentSource object itself.
     */
    public AzureBlobContentSource setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }
}
