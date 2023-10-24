// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.multipleinheritance.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Kitten model.
 */
@Fluent
public final class Kitten extends Cat {
    /*
     * The eatsMiceYet property.
     */
    @JsonProperty(value = "eatsMiceYet")
    private Boolean eatsMiceYet;

    /**
     * Creates an instance of Kitten class.
     */
    public Kitten() {}

    /**
     * Get the eatsMiceYet property: The eatsMiceYet property.
     * 
     * @return the eatsMiceYet value.
     */
    public Boolean isEatsMiceYet() {
        return this.eatsMiceYet;
    }

    /**
     * Set the eatsMiceYet property: The eatsMiceYet property.
     * 
     * @param eatsMiceYet the eatsMiceYet value to set.
     * @return the Kitten object itself.
     */
    public Kitten setEatsMiceYet(Boolean eatsMiceYet) {
        this.eatsMiceYet = eatsMiceYet;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Kitten setLikesMilk(Boolean likesMilk) {
        super.setLikesMilk(likesMilk);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Kitten setMeows(Boolean meows) {
        super.setMeows(meows);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Kitten setHisses(Boolean hisses) {
        super.setHisses(hisses);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Kitten setName(String name) {
        super.setName(name);
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
