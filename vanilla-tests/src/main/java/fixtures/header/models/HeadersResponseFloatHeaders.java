// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.header.models;

import com.azure.core.annotation.Fluent;

import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.HttpHeaders;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The HeadersResponseFloatHeaders model.
 */
@Fluent
public final class HeadersResponseFloatHeaders {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private Float value;

    private static final HttpHeaderName VALUE = HttpHeaderName.fromString("value");

    // HttpHeaders containing the raw property values.
    /**
     * Creates an instance of HeadersResponseFloatHeaders class.
     * 
     * @param rawHeaders The raw HttpHeaders that will be used to create the property values.
     */
    public HeadersResponseFloatHeaders(HttpHeaders rawHeaders) {
        String value = rawHeaders.getValue(VALUE);
        if (value != null) {
            this.value = Float.parseFloat(value);
        }
    }

    /**
     * Get the value property: The value property.
     * 
     * @return the value value.
     */
    public Float getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     * 
     * @param value the value value to set.
     * @return the HeadersResponseFloatHeaders object itself.
     */
    public HeadersResponseFloatHeaders setValue(Float value) {
        this.value = value;
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
