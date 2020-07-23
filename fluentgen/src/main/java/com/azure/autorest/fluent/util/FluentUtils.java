/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.util;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;

public class FluentUtils {

    private FluentUtils() {
    }

    public static boolean isInnerClassType(ClassType type) {
        return isInnerClassType(type.getPackage(), type.getName());
    }

    public static boolean isInnerClassType(String packageName, String name) {
        JavaSettings settings = JavaSettings.getInstance();
        String innerPackageName = settings.getPackage(settings.getModelsSubpackage(), "inner");
        return packageName.equals(innerPackageName) && name.endsWith("Inner");
    }

    public static ClassType resourceModelInterfaceClassType(ClassType clientType) {
        return resourceModelInterfaceClassType(clientType.getName());
    }

    public static ClassType resourceModelInterfaceClassType(String name) {
        JavaSettings settings = JavaSettings.getInstance();
        return new ClassType.Builder()
                .packageName(settings.getPackage(settings.getModelsSubpackage()))
                .name(name.substring(0, name.length() - "Inner".length()))
                .build();
    }
}
