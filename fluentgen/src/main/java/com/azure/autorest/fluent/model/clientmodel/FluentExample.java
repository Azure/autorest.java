/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentCollectionMethodExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentResourceCreateExample;

import java.util.ArrayList;
import java.util.List;

public class FluentExample {

    private final String name;

    private final List<FluentCollectionMethodExample> collectionMethodExamples = new ArrayList<>();
    private final List<FluentResourceCreateExample> resourceCreateExamples = new ArrayList<>();

    public FluentExample(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<FluentCollectionMethodExample> getCollectionMethodExamples() {
        return collectionMethodExamples;
    }

    public List<FluentResourceCreateExample> getResourceCreateExamples() {
        return resourceCreateExamples;
    }

    public String getClassName() {
        return name + "Samples";
    }
}
