/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.action;

import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentModelProperty;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentActionMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethodType;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Collection of resource actions.
 */
public class ResourceActions {

    private static final Logger logger = new PluginLogger(FluentGen.getPluginInstance(), ResourceActions.class);

    private final FluentResourceModel resourceModel;
    private final FluentResourceCollection resourceCollection;
    private final List<FluentCollectionMethod> actionMethods;

    private List<FluentMethod> resourceActionMethods;

    public ResourceActions(FluentResourceModel resourceModel, FluentResourceCollection resourceCollection,
                           List<FluentCollectionMethod> actionMethods) {

        this.resourceModel = resourceModel;
        this.resourceCollection = resourceCollection;
        this.actionMethods = actionMethods;

        if (logger.isInfoEnabled()) {
            Set<String> methodNames = actionMethods.stream().map(m -> m.getInnerProxyMethod().getName()).collect(Collectors.toSet());
            logger.info("ResourceActions: Fluent model '{}', action methods: {}", resourceModel.getName(), methodNames);
        }
    }

    public List<FluentMethod> getFluentMethods() {
        Set<String> unavailableMethodNames = this.getUnavailableMethodNames();
        if (resourceActionMethods == null) {
            resourceActionMethods = new ArrayList<>();
            for (FluentCollectionMethod method : actionMethods) {
                if (!unavailableMethodNames.contains(method.getMethodName())) {
                    resourceActionMethods.add(new FluentActionMethod(resourceModel, FluentMethodType.OTHER,
                            resourceCollection, method,
                            resourceModel.getResourceCreate().getResourceLocalVariables()));
                }
            }
        }
        return resourceActionMethods;
    }

    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        this.getFluentMethods().forEach(m -> m.addImportsTo(imports, includeImplementationImports));
    }

    private Set<String> getUnavailableMethodNames() {
        Set<String> unavailableMethodNames = resourceModel.getProperties().stream()
                .map(FluentModelProperty::getMethodName)
                .collect(Collectors.toSet());
        if (resourceModel.getResourceCreate() != null) {
            unavailableMethodNames.add("create");
        }
        if (resourceModel.getResourceUpdate() != null) {
            unavailableMethodNames.add("update");
            unavailableMethodNames.add("apply");
        }
        if (resourceModel.getResourceUpdate() != null) {
            unavailableMethodNames.add("refresh");
        }
        return unavailableMethodNames;
    }
}
