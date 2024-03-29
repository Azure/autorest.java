// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.armresourceprovider.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.HttpHeaders;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The CustomTemplateResourceInterfacesUpdateHeaders model.
 */
@Fluent
public final class CustomTemplateResourceInterfacesUpdateHeaders {
    /*
     * The Retry-After property.
     */
    @JsonProperty(value = "Retry-After")
    private Integer retryAfter;

    // HttpHeaders containing the raw property values.
    /**
     * Creates an instance of CustomTemplateResourceInterfacesUpdateHeaders class.
     * 
     * @param rawHeaders The raw HttpHeaders that will be used to create the property values.
     */
    public CustomTemplateResourceInterfacesUpdateHeaders(HttpHeaders rawHeaders) {
        String retryAfter = rawHeaders.getValue(HttpHeaderName.RETRY_AFTER);
        if (retryAfter != null) {
            this.retryAfter = Integer.parseInt(retryAfter);
        }
    }

    /**
     * Get the retryAfter property: The Retry-After property.
     * 
     * @return the retryAfter value.
     */
    public Integer retryAfter() {
        return this.retryAfter;
    }

    /**
     * Set the retryAfter property: The Retry-After property.
     * 
     * @param retryAfter the retryAfter value to set.
     * @return the CustomTemplateResourceInterfacesUpdateHeaders object itself.
     */
    public CustomTemplateResourceInterfacesUpdateHeaders withRetryAfter(Integer retryAfter) {
        this.retryAfter = retryAfter;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }
}
