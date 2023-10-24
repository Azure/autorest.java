// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodyformdataurlencoded.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/**
 * Can take a value of meat, or fish, or plant.
 */
public final class PetFood extends ExpandableStringEnum<PetFood> {
    /**
     * Static value meat for PetFood.
     */
    public static final PetFood MEAT = fromString("meat");

    /**
     * Static value fish for PetFood.
     */
    public static final PetFood FISH = fromString("fish");

    /**
     * Static value plant for PetFood.
     */
    public static final PetFood PLANT = fromString("plant");

    /**
     * Creates a new instance of PetFood value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Deprecated
    public PetFood() {}

    /**
     * Creates or finds a PetFood from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding PetFood.
     */
    @JsonCreator
    public static PetFood fromString(String name) {
        return fromString(name, PetFood.class);
    }

    /**
     * Gets known PetFood values.
     * 
     * @return known PetFood values.
     */
    public static Collection<PetFood> values() {
        return values(PetFood.class);
    }
}
