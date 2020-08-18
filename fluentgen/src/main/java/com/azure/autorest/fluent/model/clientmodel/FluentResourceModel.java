/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.model.arm.ModelCategory;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.ResourceCreate;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

// Fluent resource instance. E.g. StorageAccount.
// Also include some simple wrapper class.
public class FluentResourceModel {

    // inner model. E.g. StorageAccountInner.
    private final ClientModel innerModel;
    // all parent models of the inner model (property of which need to be put to resource class as well)
    private final List<ClientModel> parentModels;

    // class type for interface and implementation
    private final ClassType interfaceType;
    private final ClassType implementationType;

    // resource properties
    private final Map<String, FluentModelProperty> properties = new HashMap<>();

    // category of the resource
    private ModelCategory category = ModelCategory.WRAPPER;
    private ResourceCreate resourceCreate;

    public FluentResourceModel(ClientModel innerModel, List<ClientModel> parentModels) {
        JavaSettings settings = JavaSettings.getInstance();

        this.innerModel = innerModel;
        this.parentModels = parentModels;

        interfaceType = FluentUtils.resourceModelInterfaceClassType(innerModel.getName());
        implementationType = new ClassType.Builder()
                .packageName(settings.getPackage(settings.getImplementationSubpackage()))
                .name(interfaceType.getName() + ModelNaming.MODEL_IMPL_SUFFIX)
                .build();

        properties.putAll(this.innerModel.getProperties().stream()
                .map(FluentModelProperty::new)
                .collect(Collectors.toMap(FluentModelProperty::getName, Function.identity())));

        for (ClientModel parent : parentModels) {
            parent.getProperties().stream()
                    .map(FluentModelProperty::new)
                    .forEach(p -> properties.putIfAbsent(p.getName(), p));
        }
    }

    public ClientModel getInnerModel() {
        return innerModel;
    }

    public ClassType getInterfaceType() {
        return interfaceType;
    }

    public ClassType getImplementationType() {
        return implementationType;
    }

    public boolean hasProperty(String name) {
        return properties.containsKey(name);
    }

    public FluentModelProperty getProperty(String name) {
        return properties.get(name);
    }

    public Collection<FluentModelProperty> getProperties() {
        return properties.values();
    }

    public String getDescription() {
        return String.format("An immutable client-side representation of %s.", interfaceType.getName());
    }

    // method signature for inner model
    public String getInnerMethodSignature() {
        return String.format("%1$s %2$s()", this.getInnerModel().getName(), FluentUtils.getGetterName(ModelNaming.METHOD_INNER));
    }

    public ModelCategory getCategory() {
        return category;
    }

    public void setCategory(ModelCategory category) {
        this.category = category;
    }

    public ResourceCreate getResourceCreate() {
        return resourceCreate;
    }

    public void setResourceCreate(ResourceCreate resourceCreate) {
        this.resourceCreate = resourceCreate;
    }

    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        imports.add(this.getInnerModel().getFullName());

        this.getProperties().forEach(p -> p.addImportsTo(imports, includeImplementationImports));

        if (includeImplementationImports) {
            interfaceType.addImportsTo(imports, false);
        }
    }
}
