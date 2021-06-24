/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.examplemodel;

import com.azure.autorest.fluent.model.clientmodel.FluentManager;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethod;
import com.azure.autorest.util.CodeNamer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FluentResourceCreateExample {

    private final String name;
    private final FluentManager manager;
    private final FluentResourceCollection collection;
    private final List<ParameterExample> parameters = new ArrayList<>();
    private String className;

    public FluentResourceCreateExample(String name, FluentManager manager, FluentResourceCollection collection) {
        this.name = name;
        this.manager = manager;
        this.collection = collection;

        this.className = CodeNamer.toPascalCase(name) + "Samples";
    }

    public static class ParameterExample {
        private final FluentMethod fluentMethod;
        private final List<ExampleNode> exampleNodes = new ArrayList<>();

        public ParameterExample(FluentMethod fluentMethod, Collection<ExampleNode> exampleNodeIterator) {
            this.fluentMethod = fluentMethod;
            exampleNodes.addAll(exampleNodeIterator);
        }

        public ParameterExample(FluentMethod fluentMethod, ExampleNode exampleNode) {
            this.fluentMethod = fluentMethod;
            if (exampleNode != null) {
                this.exampleNodes.add(exampleNode);
            }
        }

        public FluentMethod getFluentMethod() {
            return fluentMethod;
        }

        public List<ExampleNode> getExampleNodes() {
            return exampleNodes;
        }
    }

    public String getName() {
        return name;
    }

    public FluentManager getManager() {
        return manager;
    }

    public FluentResourceCollection getResourceCollection() {
        return collection;
    }

    public List<ParameterExample> getParameters() {
        return parameters;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
