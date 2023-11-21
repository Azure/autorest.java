// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.dpgcustomization.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonProperty;
import fixtures.dpgcustomization.models.Product;
import java.util.List;

/**
 * The ProductResult model.
 */
@Fluent
public final class ProductResult {
    /*
     * The values property.
     */
    @Generated
    @JsonProperty(value = "values")
    private List<Product> values;

    /*
     * The nextLink property.
     */
    @Generated
    @JsonProperty(value = "nextLink")
    private String nextLink;

    /**
     * Creates an instance of ProductResult class.
     */
    @Generated
    public ProductResult() {
    }

    /**
     * Get the values property: The values property.
     * 
     * @return the values value.
     */
    @Generated
    public List<Product> getValues() {
        return this.values;
    }

    /**
     * Set the values property: The values property.
     * 
     * @param values the values value to set.
     * @return the ProductResult object itself.
     */
    @Generated
    public ProductResult setValues(List<Product> values) {
        this.values = values;
        return this;
    }

    /**
     * Get the nextLink property: The nextLink property.
     * 
     * @return the nextLink value.
     */
    @Generated
    public String getNextLink() {
        return this.nextLink;
    }

    /**
     * Set the nextLink property: The nextLink property.
     * 
     * @param nextLink the nextLink value to set.
     * @return the ProductResult object itself.
     */
    @Generated
    public ProductResult setNextLink(String nextLink) {
        this.nextLink = nextLink;
        return this;
    }
}
