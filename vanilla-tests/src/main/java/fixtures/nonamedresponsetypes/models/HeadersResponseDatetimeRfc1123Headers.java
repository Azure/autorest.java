// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.nonamedresponsetypes.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.HttpHeaders;
import com.azure.core.util.DateTimeRfc1123;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

/** The HeadersResponseDatetimeRfc1123Headers model. */
@Fluent
public final class HeadersResponseDatetimeRfc1123Headers {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private DateTimeRfc1123 value;

    private static final HttpHeaderName VALUE = HttpHeaderName.fromString("value");

    // HttpHeaders containing the raw property values.
    /**
     * Creates an instance of HeadersResponseDatetimeRfc1123Headers class.
     *
     * @param rawHeaders The raw HttpHeaders that will be used to create the property values.
     */
    public HeadersResponseDatetimeRfc1123Headers(HttpHeaders rawHeaders) {
        String value = rawHeaders.getValue(VALUE);
        if (value != null) {
            this.value = new DateTimeRfc1123(value);
        }
    }

    /**
     * Get the value property: The value property.
     *
     * @return the value value.
     */
    public OffsetDateTime getValue() {
        if (this.value == null) {
            return null;
        }
        return this.value.getDateTime();
    }

    /**
     * Set the value property: The value property.
     *
     * @param value the value value to set.
     * @return the HeadersResponseDatetimeRfc1123Headers object itself.
     */
    public HeadersResponseDatetimeRfc1123Headers setValue(OffsetDateTime value) {
        if (value == null) {
            this.value = null;
        } else {
            this.value = new DateTimeRfc1123(value);
        }
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
