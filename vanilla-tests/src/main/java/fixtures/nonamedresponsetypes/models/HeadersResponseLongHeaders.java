// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.nonamedresponsetypes.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.HttpHeaders;

/**
 * The HeadersResponseLongHeaders model.
 */
@Fluent
public final class HeadersResponseLongHeaders {
    /*
     * The value property.
     */
    @Generated
    private Long value;

    private static final HttpHeaderName VALUE = HttpHeaderName.fromString("value");

    // HttpHeaders containing the raw property values.
    /**
     * Creates an instance of HeadersResponseLongHeaders class.
     * 
     * @param rawHeaders The raw HttpHeaders that will be used to create the property values.
     */
    public HeadersResponseLongHeaders(HttpHeaders rawHeaders) {
        String value = rawHeaders.getValue(VALUE);
        if (value != null) {
            this.value = Long.parseLong(value);
        } else {
            this.value = null;
        }
    }

    /**
     * Get the value property: The value property.
     * 
     * @return the value value.
     */
    @Generated
    public Long getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     * 
     * @param value the value value to set.
     * @return the HeadersResponseLongHeaders object itself.
     */
    @Generated
    public HeadersResponseLongHeaders setValue(Long value) {
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
