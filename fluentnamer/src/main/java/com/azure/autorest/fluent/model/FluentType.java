/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 *
 */

package com.azure.autorest.fluent.model;

import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;

public class FluentType {

    public static final ClassType Resource = new ClassType.Builder().packageName("com.azure.core.management").name("Resource").build();
    public static final ClassType ProxyResource = new ClassType.Builder().packageName("com.azure.core.management").name("ProxyResource").build();
    public static final ClassType SubResource = new ClassType.Builder().packageName("com.azure.core.management").name("SubResource").build();

    public static final ClassType CloudError = new ClassType.Builder().packageName("com.azure.core.management").name("CloudError").build();

    private FluentType() {
    }

    public static GenericType InnerSupportsGet(IType typeArgument) {
        return new GenericType("com.azure.management.resources.fluentcore.collection", "InnerSupportsGet", typeArgument);
    }

    public static GenericType InnerSupportsList(IType typeArgument) {
        return new GenericType("com.azure.management.resources.fluentcore.collection", "InnerSupportsListing", typeArgument);
    }

    public static GenericType InnerSupportsDelete(IType typeArgument) {
        return new GenericType("com.azure.management.resources.fluentcore.collection", "InnerSupportsDelete", typeArgument);
    }

    public static boolean nonResourceType(ObjectSchema compositeType) {
        return !(Resource.getName().equals(compositeType.getLanguage().getJava().getName())
                || ProxyResource.getName().equals(compositeType.getLanguage().getJava().getName())
                || SubResource.getName().equals(compositeType.getLanguage().getJava().getName()));
    }

    public static boolean nonResourceType(ClassType modelType) {
        return !(Resource.equals(modelType)
                || ProxyResource.equals(modelType)
                || SubResource.equals(modelType));
    }

    public static boolean nonCloudError(ClassType modelType) {
        return !(CloudError.getName().equals(modelType.getName())
                || "CloudErrorBody".equals(modelType.getName()));
    }
}
