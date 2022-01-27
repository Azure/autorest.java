// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;



/**
 * the bare-minimum fields for per-language metadata on a given aspect
 * 
 */
public class Language {

    /**
     * name used in actual implementation
     * (Required)
     *
     */
    private String name;

    /**
     * name used in serialization
     * (Optional)
     *
     */
    private String serializedName;

    /**
     * description text - describes this node.
     * (Required)
     * 
     */
    private String description;

    /**
     * name used in actual implementation
     * (Required)
     * 
     */
    public String getName() {
        return name;
    }

    /**
     * name used in actual implementation
     * (Required)
     * 
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getSerializedName() {
        return serializedName;
    }

    public void setSerializedName(String serializedName) {
        this.serializedName = serializedName;
    }

    /**
     * description text - describes this node.
     * (Required)
     * 
     */
    public String getDescription() {
        return description;
    }

    /**
     * description text - describes this node.
     * (Required)
     * 
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
