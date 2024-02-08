// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package fixtures.nonamedresponsetypes.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.HttpHeaders;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The HeadersResponseBoolHeaders model.
 */
@Fluent
public final class HeadersResponseBoolHeaders {

    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private Boolean value;

    private static final HttpHeaderName VALUE = HttpHeaderName.fromString("value");

    // HttpHeaders containing the raw property values.
    /**
     * Creates an instance of HeadersResponseBoolHeaders class.
     *
     * @param rawHeaders The raw HttpHeaders that will be used to create the property values.
     */
    public HeadersResponseBoolHeaders(HttpHeaders rawHeaders) {
        String value = rawHeaders.getValue(VALUE);
        if (value != null) {
            this.value = Boolean.parseBoolean(value);
        }
    }

    /**
     * Get the value property: The value property.
     *
     * @return the value value.
     */
    public Boolean isValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     *
     * @param value the value value to set.
     * @return the HeadersResponseBoolHeaders object itself.
     */
    public HeadersResponseBoolHeaders setValue(Boolean value) {
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
