// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.paged.implementation.models;

import com.azure.core.annotation.Immutable;
import com.cadl.paged.models.Resource;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** Paged collection of Resource items. */
@Immutable
public final class CustomPageResource {
    /*
     * The Resource items on this page
     */
    @JsonProperty(value = "value", required = true)
    private List<Resource> value;

    /*
     * The link to the next page of items
     */
    @JsonProperty(value = "nextLink")
    private String nextLink;

    /**
     * Creates an instance of CustomPageResource class.
     *
     * @param value the value value to set.
     */
    @JsonCreator
    private CustomPageResource(@JsonProperty(value = "value", required = true) List<Resource> value) {
        this.value = value;
    }

    /**
     * Get the value property: The Resource items on this page.
     *
     * @return the value value.
     */
    public List<Resource> getValue() {
        return this.value;
    }

    /**
     * Get the nextLink property: The link to the next page of items.
     *
     * @return the nextLink value.
     */
    public String getNextLink() {
        return this.nextLink;
    }
}
