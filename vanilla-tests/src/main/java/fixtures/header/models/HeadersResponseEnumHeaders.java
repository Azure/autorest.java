// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.header.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.http.HttpHeaders;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The HeadersResponseEnumHeaders model. */
@Fluent
public final class HeadersResponseEnumHeaders {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private GreyscaleColors value;

    // HttpHeaders containing the raw property values.
    /**
     * Creates an instance of HeadersResponseEnumHeaders class.
     *
     * @param rawHeaders The raw HttpHeaders that will be used to create the property values.
     */
    public HeadersResponseEnumHeaders(HttpHeaders rawHeaders) {
        this.value = GreyscaleColors.fromString(rawHeaders.getValue("value"));
    }

    /**
     * Get the value property: The value property.
     *
     * @return the value value.
     */
    public GreyscaleColors getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     *
     * @param value the value value to set.
     * @return the HeadersResponseEnumHeaders object itself.
     */
    public HeadersResponseEnumHeaders setValue(GreyscaleColors value) {
        this.value = value;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
