package com.azure.security.keyvault.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/**
 * Defines values for JsonWebKeyOperation.
 */
public final class JsonWebKeyOperation extends ExpandableStringEnum<JsonWebKeyOperation> {
    /**
     * Static value encrypt for JsonWebKeyOperation.
     */
    public static final JsonWebKeyOperation ENCRYPT = fromString("encrypt");

    /**
     * Static value decrypt for JsonWebKeyOperation.
     */
    public static final JsonWebKeyOperation DECRYPT = fromString("decrypt");

    /**
     * Static value sign for JsonWebKeyOperation.
     */
    public static final JsonWebKeyOperation SIGN = fromString("sign");

    /**
     * Static value verify for JsonWebKeyOperation.
     */
    public static final JsonWebKeyOperation VERIFY = fromString("verify");

    /**
     * Static value wrapKey for JsonWebKeyOperation.
     */
    public static final JsonWebKeyOperation WRAP_KEY = fromString("wrapKey");

    /**
     * Static value unwrapKey for JsonWebKeyOperation.
     */
    public static final JsonWebKeyOperation UNWRAP_KEY = fromString("unwrapKey");

    /**
     * Creates or finds a JsonWebKeyOperation from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding JsonWebKeyOperation.
     */
    @JsonCreator
    public static JsonWebKeyOperation fromString(String name) {
        return fromString(name, JsonWebKeyOperation.class);
    }

    /**
     * @return known JsonWebKeyOperation values.
     */
    public static Collection<JsonWebKeyOperation> values() {
        return values(JsonWebKeyOperation.class);
    }
}
