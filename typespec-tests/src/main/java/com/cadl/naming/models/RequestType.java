// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.naming.models;

import com.azure.core.annotation.Generated;
import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

/**
 * Defines values for RequestType.
 */
public final class RequestType extends ExpandableStringEnum<RequestType> {
    /**
     * Static value Type1 for RequestType.
     */
    @Generated
    public static final RequestType TYPE1 = fromString("Type1");

    /**
     * Static value Type2 for RequestType.
     */
    @Generated
    public static final RequestType TYPE2 = fromString("Type2");

    /**
     * Creates a new instance of RequestType value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public RequestType() {
    }

    /**
     * Creates or finds a RequestType from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding RequestType.
     */
    @Generated
    public static RequestType fromString(String name) {
        return fromString(name, RequestType.class);
    }

    /**
     * Gets known RequestType values.
     * 
     * @return known RequestType values.
     */
    @Generated
    public static Collection<RequestType> values() {
        return values(RequestType.class);
    }
}
