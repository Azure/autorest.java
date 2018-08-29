/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.fluent.gencode.groupable_resource_inheritance;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.rest.serializer.JsonFlatten;

/**
 * Cat update resource.
 */
@JsonFlatten
public class CatUpdate {
    /**
     * the animal color type. Possible values include: 'Black', 'White'.
     */
    @JsonProperty(value = "properties.osType")
    private ColorTypes osType;

    /**
     * If creationData.createOption is Empty, this field is mandatory and it
     * indicates the size of the VHD to create. If this field is present for
     * updates or creation with other options, it indicates a resize. Resizes
     * are only allowed if the dog is not attached to a running owner, and can
     * only increase the dog's size.
     */
    @JsonProperty(value = "properties.animalSizeGB")
    private Integer animalSizeGB;

    /**
     * Resource tags.
     */
    @JsonProperty(value = "tags")
    private Map<String, String> tags;

    /**
     * The sku property.
     */
    @JsonProperty(value = "sku")
    private CatSku sku;

    /**
     * Get the animal color type. Possible values include: 'Black', 'White'.
     *
     * @return the osType value
     */
    public ColorTypes osType() {
        return this.osType;
    }

    /**
     * Set the animal color type. Possible values include: 'Black', 'White'.
     *
     * @param osType the osType value to set
     * @return the CatUpdate object itself.
     */
    public CatUpdate withOsType(ColorTypes osType) {
        this.osType = osType;
        return this;
    }

    /**
     * Get if creationData.createOption is Empty, this field is mandatory and it indicates the size of the VHD to create. If this field is present for updates or creation with other options, it indicates a resize. Resizes are only allowed if the dog is not attached to a running owner, and can only increase the dog's size.
     *
     * @return the animalSizeGB value
     */
    public Integer animalSizeGB() {
        return this.animalSizeGB;
    }

    /**
     * Set if creationData.createOption is Empty, this field is mandatory and it indicates the size of the VHD to create. If this field is present for updates or creation with other options, it indicates a resize. Resizes are only allowed if the dog is not attached to a running owner, and can only increase the dog's size.
     *
     * @param animalSizeGB the animalSizeGB value to set
     * @return the CatUpdate object itself.
     */
    public CatUpdate withAnimalSizeGB(Integer animalSizeGB) {
        this.animalSizeGB = animalSizeGB;
        return this;
    }

    /**
     * Get resource tags.
     *
     * @return the tags value
     */
    public Map<String, String> tags() {
        return this.tags;
    }

    /**
     * Set resource tags.
     *
     * @param tags the tags value to set
     * @return the CatUpdate object itself.
     */
    public CatUpdate withTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Get the sku value.
     *
     * @return the sku value
     */
    public CatSku sku() {
        return this.sku;
    }

    /**
     * Set the sku value.
     *
     * @param sku the sku value to set
     * @return the CatUpdate object itself.
     */
    public CatUpdate withSku(CatSku sku) {
        this.sku = sku;
        return this;
    }

}
