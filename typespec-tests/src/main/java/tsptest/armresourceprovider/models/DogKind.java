// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package tsptest.armresourceprovider.models;

import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

/**
 * extensible enum type for discriminator.
 */
public final class DogKind extends ExpandableStringEnum<DogKind> {
    /**
     * Species golden.
     */
    public static final DogKind GOLDEN = fromString("golden_dog");

    /**
     * Creates a new instance of DogKind value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Deprecated
    public DogKind() {
    }

    /**
     * Creates or finds a DogKind from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding DogKind.
     */
    public static DogKind fromString(String name) {
        return fromString(name, DogKind.class);
    }

    /**
     * Gets known DogKind values.
     * 
     * @return known DogKind values.
     */
    public static Collection<DogKind> values() {
        return values(DogKind.class);
    }
}
