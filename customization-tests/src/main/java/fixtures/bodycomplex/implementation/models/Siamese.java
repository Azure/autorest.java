// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.implementation.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The Siamese model.
 */
@Fluent
public final class Siamese extends Cat {
    /*
     * The breed property.
     */
    @JsonProperty(value = "breed")
    private String breed;

    /**
     * Creates an instance of Siamese class.
     */
    public Siamese() {}

    /**
     * Get the breed property: The breed property.
     * 
     * @return the breed value.
     */
    public String getBreed() {
        return this.breed;
    }

    /**
     * Set the breed property: The breed property.
     * 
     * @param breed the breed value to set.
     * @return the Siamese object itself.
     */
    public Siamese setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Siamese setColor(String color) {
        super.setColor(color);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Siamese setHates(List<Dog> hates) {
        super.setHates(hates);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Siamese setId(Integer id) {
        super.setId(id);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Siamese setName(String name) {
        super.setName(name);
        return this;
    }
}
