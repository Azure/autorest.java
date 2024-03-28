// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.armresourceprovider.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.logging.ClientLogger;
import com.cadl.armresourceprovider.fluent.models.OperationInner;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * A list of REST API operations supported by an Azure Resource Provider. It contains an URL link to get the next set of
 * results.
 */
@Fluent
public final class PagedOperation {
    /*
     * The Operation items on this page
     */
    @JsonProperty(value = "value", required = true)
    private List<OperationInner> value;

    /*
     * The link to the next page of items
     */
    @JsonProperty(value = "nextLink", access = JsonProperty.Access.WRITE_ONLY)
    private String nextLink;

    /**
     * Creates an instance of PagedOperation class.
     */
    public PagedOperation() {
    }

    /**
     * Get the value property: The Operation items on this page.
     * 
     * @return the value value.
     */
    public List<OperationInner> value() {
        return this.value;
    }

    /**
     * Set the value property: The Operation items on this page.
     * 
     * @param value the value value to set.
     * @return the PagedOperation object itself.
     */
    public PagedOperation withValue(List<OperationInner> value) {
        this.value = value;
        return this;
    }

    /**
     * Get the nextLink property: The link to the next page of items.
     * 
     * @return the nextLink value.
     */
    public String nextLink() {
        return this.nextLink;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (value() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Missing required property value in model PagedOperation"));
        } else {
            value().forEach(e -> e.validate());
        }
    }

    private static final ClientLogger LOGGER = new ClientLogger(PagedOperation.class);
}
