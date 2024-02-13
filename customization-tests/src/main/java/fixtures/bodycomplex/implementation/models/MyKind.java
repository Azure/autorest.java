// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.implementation.models;

import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

/**
 * Defines values for MyKind.
 */
public final class MyKind extends ExpandableStringEnum<MyKind> {
    /**
     * Static value Kind1 for MyKind.
     */
    public static final MyKind KIND1 = fromString("Kind1");

    /**
     * Creates a new instance of MyKind value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Deprecated
    public MyKind() {
    }

    /**
     * Creates or finds a MyKind from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding MyKind.
     */
    public static MyKind fromString(String name) {
        return fromString(name, MyKind.class);
    }

    /**
     * Gets known MyKind values.
     * 
     * @return known MyKind values.
     */
    public static Collection<MyKind> values() {
        return values(MyKind.class);
    }
}
