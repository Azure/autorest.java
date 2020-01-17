package com.azure.security.keyvault.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/**
 * Defines values for JsonWebKeySignatureAlgorithm.
 */
public final class JsonWebKeySignatureAlgorithm extends ExpandableStringEnum<JsonWebKeySignatureAlgorithm> {
    /**
     * Static value PS256 for JsonWebKeySignatureAlgorithm.
     */
    public static final JsonWebKeySignatureAlgorithm PS256 = fromString("PS256");

    /**
     * Static value PS384 for JsonWebKeySignatureAlgorithm.
     */
    public static final JsonWebKeySignatureAlgorithm PS384 = fromString("PS384");

    /**
     * Static value PS512 for JsonWebKeySignatureAlgorithm.
     */
    public static final JsonWebKeySignatureAlgorithm PS512 = fromString("PS512");

    /**
     * Static value RS256 for JsonWebKeySignatureAlgorithm.
     */
    public static final JsonWebKeySignatureAlgorithm RS256 = fromString("RS256");

    /**
     * Static value RS384 for JsonWebKeySignatureAlgorithm.
     */
    public static final JsonWebKeySignatureAlgorithm RS384 = fromString("RS384");

    /**
     * Static value RS512 for JsonWebKeySignatureAlgorithm.
     */
    public static final JsonWebKeySignatureAlgorithm RS512 = fromString("RS512");

    /**
     * Static value RSNULL for JsonWebKeySignatureAlgorithm.
     */
    public static final JsonWebKeySignatureAlgorithm RSNULL = fromString("RSNULL");

    /**
     * Static value ES256 for JsonWebKeySignatureAlgorithm.
     */
    public static final JsonWebKeySignatureAlgorithm ES256 = fromString("ES256");

    /**
     * Static value ES384 for JsonWebKeySignatureAlgorithm.
     */
    public static final JsonWebKeySignatureAlgorithm ES384 = fromString("ES384");

    /**
     * Static value ES512 for JsonWebKeySignatureAlgorithm.
     */
    public static final JsonWebKeySignatureAlgorithm ES512 = fromString("ES512");

    /**
     * Static value ES256K for JsonWebKeySignatureAlgorithm.
     */
    public static final JsonWebKeySignatureAlgorithm ES256K = fromString("ES256K");

    /**
     * Creates or finds a JsonWebKeySignatureAlgorithm from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding JsonWebKeySignatureAlgorithm.
     */
    @JsonCreator
    public static JsonWebKeySignatureAlgorithm fromString(String name) {
        return fromString(name, JsonWebKeySignatureAlgorithm.class);
    }

    /**
     * @return known JsonWebKeySignatureAlgorithm values.
     */
    public static Collection<JsonWebKeySignatureAlgorithm> values() {
        return values(JsonWebKeySignatureAlgorithm.class);
    }
}
