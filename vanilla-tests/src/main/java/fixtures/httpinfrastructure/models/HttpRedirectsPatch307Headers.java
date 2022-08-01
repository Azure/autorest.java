// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.httpinfrastructure.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.http.HttpHeaders;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The HttpRedirectsPatch307Headers model. */
@Fluent
public final class HttpRedirectsPatch307Headers {
    /*
     * The Location property.
     */
    @JsonProperty(value = "Location")
    private String location = "/http/success/patch/200";

    // HttpHeaders containing the raw property values.
    /**
     * Creates an instance of HttpRedirectsPatch307Headers class.
     *
     * @param rawHeaders The raw HttpHeaders that will be used to create the property values.
     */
    public HttpRedirectsPatch307Headers(HttpHeaders rawHeaders) {
        this.location = rawHeaders.getValue("Location");
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
     * @return the HttpRedirectsPatch307Headers object itself.
     */
    public HttpRedirectsPatch307Headers setLocation(String location) {
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
