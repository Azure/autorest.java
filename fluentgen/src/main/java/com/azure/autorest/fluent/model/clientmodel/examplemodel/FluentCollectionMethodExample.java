/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.examplemodel;

import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.MethodParameter;

import java.util.ArrayList;
import java.util.List;

public class FluentCollectionMethodExample {

    private final String name;
    private final FluentResourceCollection collection;
    private final FluentCollectionMethod collectionMethod;
    private final List<ParameterExample> parameters = new ArrayList<>();

    public static class ParameterExample {
        private final MethodParameter parameter;
        private final ExampleNode exampleNode;

        public ParameterExample(MethodParameter parameter, ExampleNode exampleNode) {
            this.parameter = parameter;
            this.exampleNode = exampleNode;
        }

        public MethodParameter getParameter() {
            return parameter;
        }

        public ExampleNode getExampleNode() {
            return exampleNode;
        }
    }

    public FluentCollectionMethodExample(String name, FluentResourceCollection collection, FluentCollectionMethod collectionMethod) {
        this.name = name;
        this.collection = collection;
        this.collectionMethod = collectionMethod;
    }

    public String getName() {
        return name;
    }

    public List<ParameterExample> getParameters() {
        return parameters;
    }

    public FluentResourceCollection getResourceCollection() {
        return collection;
    }

    public FluentCollectionMethod getCollectionMethod() {
        return collectionMethod;
    }
}
