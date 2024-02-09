// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.header.models;

import com.azure.core.annotation.Fluent;

import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.HttpHeaders;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The HeadersResponseProtectedKeyHeaders model.
 */
@Fluent
public final class HeadersResponseProtectedKeyHeaders {
    /*
     * The Content-Type property.
     */
    @JsonProperty(value = "Content-Type")
    private String contentType;

    // HttpHeaders containing the raw property values.
    /**
     * Creates an instance of HeadersResponseProtectedKeyHeaders class.
     * 
     * @param rawHeaders The raw HttpHeaders that will be used to create the property values.
     */
    public HeadersResponseProtectedKeyHeaders(HttpHeaders rawHeaders) {
        this.contentType = rawHeaders.getValue(HttpHeaderName.CONTENT_TYPE);
    }

    /**
     * Get the contentType property: The Content-Type property.
     * 
     * @return the contentType value.
     */
    public String getContentType() {
        return this.contentType;
    }

    /**
     * Set the contentType property: The Content-Type property.
     * 
     * @param contentType the contentType value to set.
     * @return the HeadersResponseProtectedKeyHeaders object itself.
     */
    public HeadersResponseProtectedKeyHeaders setContentType(String contentType) {
        this.contentType = contentType;
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
