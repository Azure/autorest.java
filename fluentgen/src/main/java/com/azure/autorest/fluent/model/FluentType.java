/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 *
 */

package com.azure.autorest.fluent.model;

import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;

public class FluentType {

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
}
