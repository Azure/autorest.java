/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.examplemodel;

import com.azure.autorest.fluent.model.clientmodel.FluentManager;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.model.clientmodel.ClassType;

import java.util.ArrayList;
import java.util.List;

public abstract class FluentBaseExample implements FluentExample {

    private final String name;
    private final FluentManager manager;
    private final FluentResourceCollection collection;
    private final List<ParameterExample> parameters = new ArrayList<>();

    public FluentBaseExample(String name, FluentManager manager, FluentResourceCollection collection) {
        this.name = name;
        this.manager = manager;
        this.collection = collection;
    }

    @Override
    public String getName() {
        return name;
    }

    public FluentManager getManager() {
        return manager;
    }


    @Override
    public ClassType getEntryType() {
        return manager.getType();
    }

    @Override
    public String getEntryName() {
        return "manager";
    }

    @Override
    public String getEntryDescription() {
        return String.format("Entry point to %1$s.", manager.getType().getName());
    }

    public FluentResourceCollection getResourceCollection() {
        return collection;
    }

    @Override
    public List<ParameterExample> getParameters() {
        return parameters;
    }
}
