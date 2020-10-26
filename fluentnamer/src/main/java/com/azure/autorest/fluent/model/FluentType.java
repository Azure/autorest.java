/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 *
 */

package com.azure.autorest.fluent.model;

import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;

public class FluentType {

    public static final ClassType Resource = new ClassType.Builder().knownClass(com.azure.core.management.Resource.class).build();
    public static final ClassType ProxyResource = new ClassType.Builder().knownClass(com.azure.core.management.ProxyResource.class).build();
    public static final ClassType SubResource = new ClassType.Builder().knownClass(com.azure.core.management.SubResource.class).build();

    public static final ClassType ManagementException = new ClassType.Builder().knownClass(com.azure.core.management.exception.ManagementException.class).build();
    public static final ClassType ManagementError = new ClassType.Builder().knownClass(com.azure.core.management.exception.ManagementError.class).build();

    public static final ClassType AzureProfile = new ClassType.Builder().knownClass(com.azure.core.management.profile.AzureProfile.class).build();

    public static final ClassType Region = new ClassType.Builder().knownClass(com.azure.core.management.Region.class).build();

    private FluentType() {
    }

    public static GenericType InnerSupportsGet(IType typeArgument) {
        return new GenericType("com.azure.resourcemanager.resources.fluentcore.collection", "InnerSupportsGet", typeArgument);
    }

    public static GenericType InnerSupportsList(IType typeArgument) {
        return new GenericType("com.azure.resourcemanager.resources.fluentcore.collection", "InnerSupportsListing", typeArgument);
    }

    public static GenericType InnerSupportsDelete(IType typeArgument) {
        return new GenericType("com.azure.resourcemanager.resources.fluentcore.collection", "InnerSupportsDelete", typeArgument);
    }

    public static boolean nonResourceType(ObjectSchema compositeType) {
        return nonResourceType(Utils.getJavaName(compositeType));
    }

    public static boolean nonResourceType(ClassType modelType) {
        return !(Resource.equals(modelType)
                || ProxyResource.equals(modelType)
                || SubResource.equals(modelType));
    }

    public static boolean nonResourceType(String modelName) {
        return !(Resource.getName().equals(modelName)
                || ProxyResource.getName().equals(modelName)
                || SubResource.getName().equals(modelName));
    }

    public static boolean nonManagementError(ClassType modelType) {
        return nonManagementError(modelType.getName());
    }

    public static boolean nonManagementError(String modelName) {
        return !ManagementError.getName().equals(modelName);
    }
}
