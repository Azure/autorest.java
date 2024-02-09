// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.nonamedresponsetypes.models;

import com.azure.core.annotation.Fluent;

import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.HttpHeaders;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The HeadersResponseIntegerHeaders model.
 */
@Fluent
public final class HeadersResponseIntegerHeaders {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private Integer value;

    private static final HttpHeaderName VALUE = HttpHeaderName.fromString("value");

    // HttpHeaders containing the raw property values.
    /**
     * Creates an instance of HeadersResponseIntegerHeaders class.
     * 
     * @param rawHeaders The raw HttpHeaders that will be used to create the property values.
     */
    public HeadersResponseIntegerHeaders(HttpHeaders rawHeaders) {
        String value = rawHeaders.getValue(VALUE);
        if (value != null) {
            this.value = Integer.parseInt(value);
        }
    }

    /**
     * Get the value property: The value property.
     * 
     * @return the value value.
     */
    public Integer getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     * 
     * @param value the value value to set.
     * @return the HeadersResponseIntegerHeaders object itself.
     */
    public HeadersResponseIntegerHeaders setValue(Integer value) {
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
