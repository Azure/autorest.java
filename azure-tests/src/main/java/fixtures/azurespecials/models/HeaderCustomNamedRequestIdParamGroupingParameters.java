// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.azurespecials.models;

import com.azure.core.annotation.Fluent;

/**
 * Parameter group.
 */
@Fluent
public final class HeaderCustomNamedRequestIdParamGroupingParameters {
    /*
     * The fooRequestId
     */
    private String fooClientRequestId;

    /**
     * Creates an instance of HeaderCustomNamedRequestIdParamGroupingParameters class.
     */
    public HeaderCustomNamedRequestIdParamGroupingParameters() {
    }

    /**
     * Get the fooClientRequestId property: The fooRequestId.
     * 
     * @return the fooClientRequestId value.
     */
    public String getFooClientRequestId() {
        return this.fooClientRequestId;
    }

    /**
     * Set the fooClientRequestId property: The fooRequestId.
     * 
     * @param fooClientRequestId the fooClientRequestId value to set.
     * @return the HeaderCustomNamedRequestIdParamGroupingParameters object itself.
     */
    public HeaderCustomNamedRequestIdParamGroupingParameters setFooClientRequestId(String fooClientRequestId) {
        this.fooClientRequestId = fooClientRequestId;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getFooClientRequestId() == null) {
            throw new IllegalArgumentException(
                "Missing required property fooClientRequestId in model HeaderCustomNamedRequestIdParamGroupingParameters");
        }
    }
}
