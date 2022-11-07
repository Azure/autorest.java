// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.httpinfrastructure.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.http.HttpHeaders;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The HttpRedirectsPut301Headers model. */
@Fluent
public final class HttpRedirectsPut301Headers {
    /*
     * The Location property.
     */
    @JsonProperty(value = "Location")
    private String location = "/http/failure/500";

    // HttpHeaders containing the raw property values.
    /**
     * Creates an instance of HttpRedirectsPut301Headers class.
     *
     * @param rawHeaders The raw HttpHeaders that will be used to create the property values.
     */
    public HttpRedirectsPut301Headers(HttpHeaders rawHeaders) {
        this.location = rawHeaders.getValue(LOCATION);
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
     * @return the HttpRedirectsPut301Headers object itself.
     */
    public HttpRedirectsPut301Headers setLocation(String location) {
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
