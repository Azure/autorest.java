// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package fixtures.modelflattening.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The product URL.
 */
@Fluent
public final class ProductUrl extends GenericUrl {

    /*
     * URL value.
     */
    @JsonProperty(value = "@odata.value")
    private String odataValue;

    /**
     * Creates an instance of ProductUrl class.
     */
    public ProductUrl() {
    }

    /**
     * Get the odataValue property: URL value.
     *
     * @return the odataValue value.
     */
    public String getOdataValue() {
        return this.odataValue;
    }

    /**
     * Set the odataValue property: URL value.
     *
     * @param odataValue the odataValue value to set.
     * @return the ProductUrl object itself.
     */
    public ProductUrl setOdataValue(String odataValue) {
        this.odataValue = odataValue;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductUrl setGenericValue(String genericValue) {
        super.setGenericValue(genericValue);
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    @Override
    public void validate() {
        super.validate();
    }
}
