/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

// Fluent resource instance. E.g. StorageAccount.
// Also include some simple wrapper class.
public class FluentResourceModel {

    // inner model. E.g. StorageAccountInner.
    private final ClientModel model;
    // all parent models of the inner model (property of which need to be put to resource class as well)
    private final List<ClientModel> parentModels;

    private final ModelType modelType = ModelType.WRAPPER;

    // class type for interface and implementation
    private final ClassType resourceInterfaceClassType;
    private final ClassType resourceImplementationClassType;

    // resource properties
    private final List<FluentModelProperty> properties = new ArrayList<>();

    public FluentResourceModel(ClientModel model, List<ClientModel> parentModels) {
        JavaSettings settings = JavaSettings.getInstance();

        this.model = model;
        this.parentModels = parentModels;

        resourceInterfaceClassType = FluentUtils.resourceModelInterfaceClassType(model.getName());
        resourceImplementationClassType = new ClassType.Builder()
                .packageName(settings.getPackage(settings.getImplementationSubpackage()))
                .name(resourceInterfaceClassType.getName() + ModelNaming.MODEL_IMPL_SUFFIX)
                .build();

        properties.addAll(this.model.getProperties().stream()
                .map(FluentModelProperty::new)
                .collect(Collectors.toList()));

        Set<String> propertyNames = properties.stream().map(FluentModelProperty::getName).collect(Collectors.toSet());
        for (ClientModel parent : parentModels) {
            List<FluentModelProperty> parentProperties = parent.getProperties().stream()
                    .filter(p -> !propertyNames.contains(p.getName()))
                    .map(FluentModelProperty::new)
                    .collect(Collectors.toList());
            properties.addAll(parentProperties);

            propertyNames.addAll(parentProperties.stream().map(FluentModelProperty::getName).collect(Collectors.toSet()));
        }
    }

//    public ModelType getModelType() {
//        return modelType;
//    }

    public ClientModel getInnerModel() {
        return model;
    }

    public ClassType getResourceInterfaceClassType() {
        return resourceInterfaceClassType;
    }

    public ClassType getResourceImplementationClassType() {
        return resourceImplementationClassType;
    }

    public List<FluentModelProperty> getProperties() {
        return properties;
    }

    public String getDescription() {
        return String.format("An immutable client-side representation of %s.", resourceInterfaceClassType.getName());
    }

    // method signature for inner model
    public String getInnerMethodSignature() {
        return String.format("%1$s %2$s()", this.getInnerModel().getName(), FluentUtils.getGetterName(ModelNaming.PROPERTY_INNER));
    }

    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        imports.add(this.getInnerModel().getFullName());

        this.getProperties().forEach(p -> p.addImportsTo(imports, includeImplementationImports));

        if (includeImplementationImports) {
            resourceInterfaceClassType.addImportsTo(imports, false);
        }
    }
}
