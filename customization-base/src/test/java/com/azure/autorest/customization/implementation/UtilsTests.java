// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization.implementation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Modifier;

import static com.azure.autorest.customization.implementation.Utils.validateModifiers;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests {@link Utils}.
 */
public class UtilsTests {
    @ParameterizedTest
    @ValueSource(ints = { Modifier.PUBLIC, Modifier.PROTECTED, Modifier.PRIVATE, Modifier.ABSTRACT, Modifier.STATIC,
        Modifier.FINAL, 0 })
    public void validClassModifiers(int modifier) {
        assertDoesNotThrow(() -> validateModifiers(Modifier.classModifiers(), modifier));
    }

    @ParameterizedTest
    @ValueSource(ints = { Modifier.INTERFACE, Modifier.SYNCHRONIZED, Modifier.TRANSIENT, Modifier.VOLATILE, -1 })
    public void invalidClassModifiers(int modifier) {
        assertThrows(IllegalArgumentException.class, () -> validateModifiers(Modifier.classModifiers(), modifier));
    }

    @ParameterizedTest
    @ValueSource(ints = { Modifier.PUBLIC, Modifier.PROTECTED, Modifier.PRIVATE, Modifier.ABSTRACT, Modifier.STATIC,
        Modifier.FINAL, Modifier.SYNCHRONIZED, 0 })
    public void validMethodModifiers(int modifier) {
        assertDoesNotThrow(() -> validateModifiers(Modifier.methodModifiers(), modifier));
    }

    @ParameterizedTest
    @ValueSource(ints = { Modifier.INTERFACE, Modifier.VOLATILE, Modifier.TRANSIENT, -1 })
    public void invalidMethodModifiers(int modifier) {
        assertThrows(IllegalArgumentException.class, () -> validateModifiers(Modifier.methodModifiers(), modifier));
    }
}
