// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.azurespecials.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.HttpHeaders;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The HeadersCustomNamedRequestIdHeaders model.
 */
@Fluent
public final class HeadersCustomNamedRequestIdHeaders {
    /*
     * The foo-request-id property.
     */
    @JsonProperty(value = "foo-request-id")
    private String fooRequestId;

    private static final HttpHeaderName FOO_REQUEST_ID = HttpHeaderName.fromString("foo-request-id");

    // HttpHeaders containing the raw property values.
    /**
     * Creates an instance of HeadersCustomNamedRequestIdHeaders class.
     * 
     * @param rawHeaders The raw HttpHeaders that will be used to create the property values.
     */
    public HeadersCustomNamedRequestIdHeaders(HttpHeaders rawHeaders) {
        this.fooRequestId = rawHeaders.getValue(FOO_REQUEST_ID);
    }

    /**
     * Get the fooRequestId property: The foo-request-id property.
     * 
     * @return the fooRequestId value.
     */
    public String getFooRequestId() {
        return this.fooRequestId;
    }

    /**
     * Set the fooRequestId property: The foo-request-id property.
     * 
     * @param fooRequestId the fooRequestId value to set.
     * @return the HeadersCustomNamedRequestIdHeaders object itself.
     */
    public HeadersCustomNamedRequestIdHeaders setFooRequestId(String fooRequestId) {
        this.fooRequestId = fooRequestId;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
