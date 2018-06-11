/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.fluent.gencode.child_in_parent_opgroup;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The puppies sku name.
 */
public class PuppySku {
    /**
     * The sku name. Possible values include: 'Small', 'Medium', 'Large'.
     */
    @JsonProperty(value = "name")
    private SkuNames name;

    /**
     * The sku tier.
     */
    @JsonProperty(value = "tier", access = JsonProperty.Access.WRITE_ONLY)
    private String tier;

    /**
     * Get the name value.
     *
     * @return the name value
     */
    public SkuNames name() {
        return this.name;
    }

    /**
     * Set the name value.
     *
     * @param name the name value to set
     * @return the PuppySku object itself.
     */
    public PuppySku withName(SkuNames name) {
        this.name = name;
        return this;
    }

    /**
     * Get the tier value.
     *
     * @return the tier value
     */
    public String tier() {
        return this.tier;
    }

}
