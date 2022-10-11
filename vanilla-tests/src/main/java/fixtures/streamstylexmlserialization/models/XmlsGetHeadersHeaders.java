// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstylexmlserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.http.HttpHeaders;

/** The XmlsGetHeadersHeaders model. */
@Fluent
public final class XmlsGetHeadersHeaders {
    /*
     * The Custom-Header property.
     */
    private String customHeader;

    // HttpHeaders containing the raw property values.
    /**
     * Creates an instance of XmlsGetHeadersHeaders class.
     *
     * @param rawHeaders The raw HttpHeaders that will be used to create the property values.
     */
    public XmlsGetHeadersHeaders(HttpHeaders rawHeaders) {
        this.customHeader = rawHeaders.getValue("Custom-Header");
    }

    /**
     * Get the customHeader property: The Custom-Header property.
     *
     * @return the customHeader value.
     */
    public String getCustomHeader() {
        return this.customHeader;
    }

    /**
     * Set the customHeader property: The Custom-Header property.
     *
     * @param customHeader the customHeader value to set.
     * @return the XmlsGetHeadersHeaders object itself.
     */
    public XmlsGetHeadersHeaders setCustomHeader(String customHeader) {
        this.customHeader = customHeader;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
