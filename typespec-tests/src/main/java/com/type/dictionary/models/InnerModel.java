// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.dictionary.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Dictionary inner model.
 */
@Fluent
public final class InnerModel {
    /*
     * Required string property
     */
    @Generated
    @JsonProperty(value = "property")
    private String property;

    /*
     * The children property.
     */
    @Generated
    @JsonProperty(value = "children")
    private Map<String, InnerModel> children;

    /**
     * Creates an instance of InnerModel class.
     * 
     * @param property the property value to set.
     */
    @Generated
    @JsonCreator
    public InnerModel(@JsonProperty(value = "property") String property) {
        this.property = property;
    }

    /**
     * Get the property property: Required string property.
     * 
     * @return the property value.
     */
    @Generated
    public String getProperty() {
        return this.property;
    }

    /**
     * Get the children property: The children property.
     * 
     * @return the children value.
     */
    @Generated
    public Map<String, InnerModel> getChildren() {
        return this.children;
    }

    /**
     * Set the children property: The children property.
     * 
     * @param children the children value to set.
     * @return the InnerModel object itself.
     */
    @Generated
    public InnerModel setChildren(Map<String, InnerModel> children) {
        this.children = children;
        return this;
    }
}
