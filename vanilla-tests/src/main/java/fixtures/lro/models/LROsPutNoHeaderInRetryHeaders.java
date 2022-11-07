// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.lro.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.http.HttpHeaders;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The LROsPutNoHeaderInRetryHeaders model. */
@Fluent
public final class LROsPutNoHeaderInRetryHeaders {
    /*
     * The location property.
     */
    @JsonProperty(value = "location")
    private String location;

    // HttpHeaders containing the raw property values.
    /**
     * Creates an instance of LROsPutNoHeaderInRetryHeaders class.
     *
     * @param rawHeaders The raw HttpHeaders that will be used to create the property values.
     */
    public LROsPutNoHeaderInRetryHeaders(HttpHeaders rawHeaders) {
        this.location = rawHeaders.getValue(HttpHeaderName.LOCATION);
    }

    /**
     * Get the location property: The location property.
     *
     * @return the location value.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Set the location property: The location property.
     *
     * @param location the location value to set.
     * @return the LROsPutNoHeaderInRetryHeaders object itself.
     */
    public LROsPutNoHeaderInRetryHeaders setLocation(String location) {
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
