// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.customheaderdeserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.http.HttpHeaders;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The HeadersResponseStringHeaders model. */
@Fluent
public final class HeadersResponseStringHeaders {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private String value;

    // HttpHeaders containing the raw property values.
    /**
     * Creates an instance of HeadersResponseStringHeaders class.
     *
     * @param rawHeaders The raw HttpHeaders that will be used to create the property values.
     */
    public HeadersResponseStringHeaders(HttpHeaders rawHeaders) {
        this.value = rawHeaders.getValue("value");
    }

    /**
     * Get the value property: The value property.
     *
     * @return the value value.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     *
     * @param value the value value to set.
     * @return the HeadersResponseStringHeaders object itself.
     */
    public HeadersResponseStringHeaders setValue(String value) {
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
