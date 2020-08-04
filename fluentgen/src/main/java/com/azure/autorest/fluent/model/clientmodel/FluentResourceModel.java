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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FluentResourceModel {

    private final ClientModel model;

    private final ModelType modelType = ModelType.WRAPPER;

    private final ClassType resourceInterfaceClassType;
    private final ClassType resourceImplementationClassType;

    private final List<FluentModelProperty> properties = new ArrayList<>();

    public FluentResourceModel(ClientModel model) {
        JavaSettings settings = JavaSettings.getInstance();

        this.model = model;

        resourceInterfaceClassType = FluentUtils.resourceModelInterfaceClassType(model.getName());
        resourceImplementationClassType = new ClassType.Builder()
                .packageName(settings.getPackage(settings.getImplementationSubpackage()))
                .name(resourceInterfaceClassType.getName() + ModelNaming.MODEL_IMPL_SUFFIX)
                .build();

        properties.addAll(this.model.getProperties().stream()
                .map(FluentModelProperty::new)
                .collect(Collectors.toList()));
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
