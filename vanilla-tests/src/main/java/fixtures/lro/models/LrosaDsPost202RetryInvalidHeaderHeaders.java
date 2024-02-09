// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.lro.models;

import com.azure.core.annotation.Fluent;

import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.HttpHeaders;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The LrosaDsPost202RetryInvalidHeaderHeaders model.
 */
@Fluent
public final class LrosaDsPost202RetryInvalidHeaderHeaders {
    /*
     * The Retry-After property.
     */
    @JsonProperty(value = "Retry-After")
    private Integer retryAfter;

    /*
     * The Location property.
     */
    @JsonProperty(value = "Location")
    private String location;

    // HttpHeaders containing the raw property values.
    /**
     * Creates an instance of LrosaDsPost202RetryInvalidHeaderHeaders class.
     * 
     * @param rawHeaders The raw HttpHeaders that will be used to create the property values.
     */
    public LrosaDsPost202RetryInvalidHeaderHeaders(HttpHeaders rawHeaders) {
        String retryAfter = rawHeaders.getValue(HttpHeaderName.RETRY_AFTER);
        if (retryAfter != null) {
            this.retryAfter = Integer.parseInt(retryAfter);
        }
        this.location = rawHeaders.getValue(HttpHeaderName.LOCATION);
    }

    /**
     * Get the retryAfter property: The Retry-After property.
     * 
     * @return the retryAfter value.
     */
    public Integer getRetryAfter() {
        return this.retryAfter;
    }

    /**
     * Set the retryAfter property: The Retry-After property.
     * 
     * @param retryAfter the retryAfter value to set.
     * @return the LrosaDsPost202RetryInvalidHeaderHeaders object itself.
     */
    public LrosaDsPost202RetryInvalidHeaderHeaders setRetryAfter(Integer retryAfter) {
        this.retryAfter = retryAfter;
        return this;
    }

    /**
     * Get the location property: The Location property.
     * 
     * @return the location value.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Set the location property: The Location property.
     * 
     * @param location the location value to set.
     * @return the LrosaDsPost202RetryInvalidHeaderHeaders object itself.
     */
    public LrosaDsPost202RetryInvalidHeaderHeaders setLocation(String location) {
        this.location = location;
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
