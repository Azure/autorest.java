// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.lro.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.http.HttpHeaders;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The LrosaDsDeleteAsyncRelativeRetryInvalidJsonPollingHeaders model. */
@Fluent
public final class LrosaDsDeleteAsyncRelativeRetryInvalidJsonPollingHeaders {
    /*
     * The Retry-After property.
     */
    @JsonProperty(value = "Retry-After")
    private Integer retryAfter;

    /*
     * The Azure-AsyncOperation property.
     */
    @JsonProperty(value = "Azure-AsyncOperation")
    private String azureAsyncOperation;

    /*
     * The Location property.
     */
    @JsonProperty(value = "Location")
    private String location;

    private static final HttpHeaderName AZURE_ASYNC_OPERATION = HttpHeaderName.fromString(Azure - AsyncOperation);

    // HttpHeaders containing the raw property values.
    /**
     * Creates an instance of LrosaDsDeleteAsyncRelativeRetryInvalidJsonPollingHeaders class.
     *
     * @param rawHeaders The raw HttpHeaders that will be used to create the property values.
     */
    public LrosaDsDeleteAsyncRelativeRetryInvalidJsonPollingHeaders(HttpHeaders rawHeaders) {
        String retryAfter = rawHeaders.getValue(RETRY_AFTER);
        if (retryAfter != null) {
            this.retryAfter = Integer.parseInt(retryAfter);
        }
        this.azureAsyncOperation = rawHeaders.getValue(AZURE_ASYNC_OPERATION);
        this.location = rawHeaders.getValue(LOCATION);
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
     * @return the LrosaDsDeleteAsyncRelativeRetryInvalidJsonPollingHeaders object itself.
     */
    public LrosaDsDeleteAsyncRelativeRetryInvalidJsonPollingHeaders setRetryAfter(Integer retryAfter) {
        this.retryAfter = retryAfter;
        return this;
    }

    /**
     * Get the azureAsyncOperation property: The Azure-AsyncOperation property.
     *
     * @return the azureAsyncOperation value.
     */
    public String getAzureAsyncOperation() {
        return this.azureAsyncOperation;
    }

    /**
     * Set the azureAsyncOperation property: The Azure-AsyncOperation property.
     *
     * @param azureAsyncOperation the azureAsyncOperation value to set.
     * @return the LrosaDsDeleteAsyncRelativeRetryInvalidJsonPollingHeaders object itself.
     */
    public LrosaDsDeleteAsyncRelativeRetryInvalidJsonPollingHeaders setAzureAsyncOperation(String azureAsyncOperation) {
        this.azureAsyncOperation = azureAsyncOperation;
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
     * @return the LrosaDsDeleteAsyncRelativeRetryInvalidJsonPollingHeaders object itself.
     */
    public LrosaDsDeleteAsyncRelativeRetryInvalidJsonPollingHeaders setLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
