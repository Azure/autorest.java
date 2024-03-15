// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.util;

import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;

import java.util.Arrays;

public class TypeUtil {
    private TypeUtil() {
    }

    /**
     * Whether the given type is GenericType and is subclass of either of the given classes.
     * @param type the type to check
     * @param parentClasses classes to match either one
     * @return whether the given type is GenericType and is subclass of either of the given classes
     */
    public static boolean isGenericTypeClassSubclassOf(IType type, String... parentClasses) {
        if (!(type instanceof GenericType) || parentClasses == null || parentClasses.length == 0) return false;
        String genericClass = ((GenericType) type).getPackage() + "." + ((GenericType) type).getName();

        return Arrays.asList(parentClasses).contains(genericClass);
    }
}
