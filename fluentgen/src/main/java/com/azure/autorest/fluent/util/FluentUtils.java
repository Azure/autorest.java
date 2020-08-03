/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.util;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.util.CodeNamer;
import com.azure.core.util.CoreUtils;

import java.util.Locale;

public class FluentUtils {

    private FluentUtils() {
    }

    public static boolean isInnerClassType(ClassType classType) {
        return isInnerClassType(classType.getPackage(), classType.getName());
    }

    public static boolean isInnerClassType(String packageName, String name) {
        JavaSettings settings = JavaSettings.getInstance();
        String innerPackageName = settings.getPackage(settings.getModelsSubpackage(), "inner");
        return packageName.equals(innerPackageName) && name.endsWith("Inner");
    }

    public static ClassType resourceModelInterfaceClassType(ClassType innerModelClassType) {
        return resourceModelInterfaceClassType(innerModelClassType.getName());
    }

    public static ClassType resourceModelInterfaceClassType(String innerModelClassName) {
        JavaSettings settings = JavaSettings.getInstance();
        return new ClassType.Builder()
                .packageName(settings.getPackage(settings.getModelsSubpackage()))
                .name(innerModelClassName.substring(0, innerModelClassName.length() - "Inner".length()))
                .build();
    }

    public static String getGetterName(String propertyName) {
        return CodeNamer.getModelNamer().modelPropertyGetterName(propertyName);
    }

    public static String getServiceName(String clientName) {
        JavaSettings settings = JavaSettings.getInstance();
        String serviceName = settings.getServiceName();
        if (CoreUtils.isNullOrEmpty(serviceName)) {
            String packageLastName = settings.getPackage();
            int pos = packageLastName.lastIndexOf(".");
            if (pos != -1 && pos != packageLastName.length() - 1) {
                packageLastName = packageLastName.substring(pos + 1);
            }

            if (clientName != null) {
                if (clientName.toLowerCase(Locale.ROOT).startsWith(packageLastName.toLowerCase(Locale.ROOT))) {
                    serviceName = clientName.substring(0, packageLastName.length());
                } else {
                    final String keywordManagementClient = "ManagementClient";
                    if (clientName.endsWith(keywordManagementClient)) {
                        serviceName = clientName.substring(0, clientName.length() - keywordManagementClient.length());
                    }
                }
            }

            if (CoreUtils.isNullOrEmpty(serviceName)) {
                serviceName = packageLastName;
            }
        }
        return serviceName;
    }
}
