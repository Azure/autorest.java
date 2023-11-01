// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstylexmlserialization.models;

import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

/**
 * Defines values for PublicAccessType.
 */
public final class PublicAccessType extends ExpandableStringEnum<PublicAccessType> {
    /**
     * Static value container for PublicAccessType.
     */
    public static final PublicAccessType CONTAINER = fromString("container");

    /**
     * Static value blob for PublicAccessType.
     */
    public static final PublicAccessType BLOB = fromString("blob");

    /**
     * Creates a new instance of PublicAccessType value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Deprecated
    public PublicAccessType() {
    }

    /**
     * Creates or finds a PublicAccessType from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding PublicAccessType.
     */
    public static PublicAccessType fromString(String name) {
        return fromString(name, PublicAccessType.class);
    }

    /**
     * Gets known PublicAccessType values.
     * 
     * @return known PublicAccessType values.
     */
    public static Collection<PublicAccessType> values() {
        return values(PublicAccessType.class);
    }
}
