// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package fixtures.nonamedresponsetypes.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.HttpHeaders;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The HeadersResponseExistingKeyHeaders model.
 */
@Fluent
public final class HeadersResponseExistingKeyHeaders {

    /*
     * The User-Agent property.
     */
    @JsonProperty(value = "User-Agent")
    private String userAgent;

    // HttpHeaders containing the raw property values.
    /**
     * Creates an instance of HeadersResponseExistingKeyHeaders class.
     *
     * @param rawHeaders The raw HttpHeaders that will be used to create the property values.
     */
    public HeadersResponseExistingKeyHeaders(HttpHeaders rawHeaders) {
        this.userAgent = rawHeaders.getValue(HttpHeaderName.USER_AGENT);
    }

    /**
     * Get the userAgent property: The User-Agent property.
     *
     * @return the userAgent value.
     */
    public String getUserAgent() {
        return this.userAgent;
    }

    /**
     * Set the userAgent property: The User-Agent property.
     *
     * @param userAgent the userAgent value to set.
     * @return the HeadersResponseExistingKeyHeaders object itself.
     */
    public HeadersResponseExistingKeyHeaders setUserAgent(String userAgent) {
        this.userAgent = userAgent;
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
