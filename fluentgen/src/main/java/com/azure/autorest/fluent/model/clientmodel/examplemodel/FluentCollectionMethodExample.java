/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.examplemodel;

import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentManager;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.util.CodeNamer;

/**
 * Model of example for FluentCollectionMethod.
 */
public class FluentCollectionMethodExample extends FluentBaseExample implements FluentMethodExample {

    private final FluentCollectionMethod collectionMethod;

    public FluentCollectionMethodExample(String name, FluentManager manager, FluentResourceCollection collection,
                                         FluentCollectionMethod collectionMethod) {
        super(name, manager, collection);
        this.collectionMethod = collectionMethod;
    }

    public FluentCollectionMethod getCollectionMethod() {
        return collectionMethod;
    }

    @Override
    public String getMethodReference() {
        return CodeNamer.toCamelCase(this.getResourceCollection().getInterfaceType().getName()) + "()";
    }

    @Override
    public String getMethodName() {
        return collectionMethod.getMethodName();
    }
}
