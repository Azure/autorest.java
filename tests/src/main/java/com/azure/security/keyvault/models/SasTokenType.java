package com.azure.security.keyvault.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/**
 * Defines values for SasTokenType.
 */
public final class SasTokenType extends ExpandableStringEnum<SasTokenType> {
    /**
     * Static value account for SasTokenType.
     */
    public static final SasTokenType ACCOUNT = fromString("account");

    /**
     * Static value service for SasTokenType.
     */
    public static final SasTokenType SERVICE = fromString("service");

    /**
     * Creates or finds a SasTokenType from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding SasTokenType.
     */
    @JsonCreator
    public static SasTokenType fromString(String name) {
        return fromString(name, SasTokenType.class);
    }

    /**
     * @return known SasTokenType values.
     */
    public static Collection<SasTokenType> values() {
        return values(SasTokenType.class);
    }
}
