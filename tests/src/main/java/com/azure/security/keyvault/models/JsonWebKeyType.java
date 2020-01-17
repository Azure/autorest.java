package com.azure.security.keyvault.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/**
 * Defines values for JsonWebKeyType.
 */
public final class JsonWebKeyType extends ExpandableStringEnum<JsonWebKeyType> {
    /**
     * Static value EC for JsonWebKeyType.
     */
    public static final JsonWebKeyType EC = fromString("EC");

    /**
     * Static value EC-HSM for JsonWebKeyType.
     */
    public static final JsonWebKeyType EC_HSM = fromString("EC-HSM");

    /**
     * Static value RSA for JsonWebKeyType.
     */
    public static final JsonWebKeyType RSA = fromString("RSA");

    /**
     * Static value RSA-HSM for JsonWebKeyType.
     */
    public static final JsonWebKeyType RSA_HSM = fromString("RSA-HSM");

    /**
     * Static value oct for JsonWebKeyType.
     */
    public static final JsonWebKeyType OCT = fromString("oct");

    /**
     * Creates or finds a JsonWebKeyType from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding JsonWebKeyType.
     */
    @JsonCreator
    public static JsonWebKeyType fromString(String name) {
        return fromString(name, JsonWebKeyType.class);
    }

    /**
     * @return known JsonWebKeyType values.
     */
    public static Collection<JsonWebKeyType> values() {
        return values(JsonWebKeyType.class);
    }
}
