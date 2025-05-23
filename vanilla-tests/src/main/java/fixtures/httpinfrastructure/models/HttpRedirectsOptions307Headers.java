// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.httpinfrastructure.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.HttpHeaders;

/**
 * The HttpRedirectsOptions307Headers model.
 */
@Fluent
public final class HttpRedirectsOptions307Headers {
    /*
     * The Location property.
     */
    @Generated
    private String location = "/http/success/options/200";

    // HttpHeaders containing the raw property values.
    /**
     * Creates an instance of HttpRedirectsOptions307Headers class.
     * 
     * @param rawHeaders The raw HttpHeaders that will be used to create the property values.
     */
    public HttpRedirectsOptions307Headers(HttpHeaders rawHeaders) {
        this.location = rawHeaders.getValue(HttpHeaderName.LOCATION);
    }

    /**
     * Get the location property: The Location property.
     * 
     * @return the location value.
     */
    @Generated
    public String getLocation() {
        return this.location;
    }

    /**
     * Set the location property: The Location property.
     * 
     * @param location the location value to set.
     * @return the HttpRedirectsOptions307Headers object itself.
     */
    @Generated
    public HttpRedirectsOptions307Headers setLocation(String location) {
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
