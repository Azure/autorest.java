/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.model.arm.ModelCategory;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.ResourceCreate;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.ResourceImplementation;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethod;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    private final Map<String, FluentModelProperty> propertiesMap = new HashMap<>();
    private final List<FluentModelProperty> properties = new ArrayList<>();

    // category of the resource
    private ModelCategory category = ModelCategory.IMMUTABLE;
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

        List<List<FluentModelProperty>> propertiesFromTypeAndParents = new ArrayList<>();
        propertiesFromTypeAndParents.add(new ArrayList<>());
        this.innerModel.getProperties().stream()
                .map(FluentModelProperty::new)
                .forEach(p -> {
                    propertiesMap.putIfAbsent(p.getName(), p);
                    propertiesFromTypeAndParents.get(propertiesFromTypeAndParents.size() - 1).add(p);
                });

        for (ClientModel parent : parentModels) {
            propertiesFromTypeAndParents.add(new ArrayList<>());

            parent.getProperties().stream()
                    .map(FluentModelProperty::new)
                    .forEach(p -> {
                        if (propertiesMap.putIfAbsent(p.getName(), p) == null) {
                            propertiesFromTypeAndParents.get(propertiesFromTypeAndParents.size() - 1).add(p);
                        }
                    });
        }

        Collections.reverse(propertiesFromTypeAndParents);
        for (List<FluentModelProperty> properties1 : propertiesFromTypeAndParents) {
            properties.addAll(properties1);
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
        return propertiesMap.containsKey(name);
    }

    public FluentModelProperty getProperty(String name) {
        return propertiesMap.get(name);
    }

    public Collection<FluentModelProperty> getProperties() {
        return properties;
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

    public ResourceImplementation getResourceImplementation() {
        List<FluentMethod> methods = new ArrayList<>();
        if (resourceCreate != null) {
            methods.addAll(resourceCreate.getFluentMethods());
        }
        return new ResourceImplementation(methods);
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

        if (resourceCreate != null) {
            resourceCreate.addImportsTo(imports, includeImplementationImports);
        }
    }
}
