/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.fluent.gencode.parent_child_insame_opgroup.implementation;

import com.fluent.gencode.parent_child_insame_opgroup.PuppySku;
import org.joda.time.DateTime;
import com.fluent.gencode.parent_child_insame_opgroup.ColorTypes;
import com.fluent.gencode.parent_child_insame_opgroup.CreationData;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.rest.serializer.JsonFlatten;
import com.microsoft.azure.Resource;

/**
 * Puppy resource.
 */
@JsonFlatten
public class PuppyInner extends Resource {
    /**
     * Unused. Always Null.
     */
    @JsonProperty(value = "managedBy", access = JsonProperty.Access.WRITE_ONLY)
    private String managedBy;

    /**
     * The sku property.
     */
    @JsonProperty(value = "sku")
    private PuppySku sku;

    /**
     * The time when the dog was created.
     */
    @JsonProperty(value = "properties.timeCreated", access = JsonProperty.Access.WRITE_ONLY)
    private DateTime timeCreated;

    /**
     * The Operating System type. Possible values include: 'Black', 'White'.
     */
    @JsonProperty(value = "properties.osType")
    private ColorTypes osType;

    /**
     * Dog source information. CreationData information cannot be changed after
     * the animal has been created.
     */
    @JsonProperty(value = "properties.creationData", required = true)
    private CreationData creationData;

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
     * The dog provisioning state.
     */
    @JsonProperty(value = "properties.provisioningState", access = JsonProperty.Access.WRITE_ONLY)
    private String provisioningState;

    /**
     * Get unused. Always Null.
     *
     * @return the managedBy value
     */
    public String managedBy() {
        return this.managedBy;
    }

    /**
     * Get the sku value.
     *
     * @return the sku value
     */
    public PuppySku sku() {
        return this.sku;
    }

    /**
     * Set the sku value.
     *
     * @param sku the sku value to set
     * @return the PuppyInner object itself.
     */
    public PuppyInner withSku(PuppySku sku) {
        this.sku = sku;
        return this;
    }

    /**
     * Get the time when the dog was created.
     *
     * @return the timeCreated value
     */
    public DateTime timeCreated() {
        return this.timeCreated;
    }

    /**
     * Get the Operating System type. Possible values include: 'Black', 'White'.
     *
     * @return the osType value
     */
    public ColorTypes osType() {
        return this.osType;
    }

    /**
     * Set the Operating System type. Possible values include: 'Black', 'White'.
     *
     * @param osType the osType value to set
     * @return the PuppyInner object itself.
     */
    public PuppyInner withOsType(ColorTypes osType) {
        this.osType = osType;
        return this;
    }

    /**
     * Get dog source information. CreationData information cannot be changed after the animal has been created.
     *
     * @return the creationData value
     */
    public CreationData creationData() {
        return this.creationData;
    }

    /**
     * Set dog source information. CreationData information cannot be changed after the animal has been created.
     *
     * @param creationData the creationData value to set
     * @return the PuppyInner object itself.
     */
    public PuppyInner withCreationData(CreationData creationData) {
        this.creationData = creationData;
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
     * @return the PuppyInner object itself.
     */
    public PuppyInner withAnimalSizeGB(Integer animalSizeGB) {
        this.animalSizeGB = animalSizeGB;
        return this;
    }

    /**
     * Get the dog provisioning state.
     *
     * @return the provisioningState value
     */
    public String provisioningState() {
        return this.provisioningState;
    }

}
