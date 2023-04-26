// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.model.FluentType;
import com.azure.autorest.fluent.model.arm.ResourceClientModel;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ClientModelPropertyReference;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaContext;
import com.azure.autorest.template.ModelTemplate;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.autorest.util.ModelNamer;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class FluentModelTemplate extends ModelTemplate {

    private static final FluentModelTemplate INSTANCE = new FluentModelTemplate();

    private static ModelNamer modelNamer;

    protected FluentModelTemplate() {
    }

    public static FluentModelTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    protected void addSerializationImports(Set<String> imports, ClientModel model, JavaSettings settings) {
        super.addSerializationImports(imports, model, settings);

        imports.add(JsonInclude.class.getName());
    }

    @Override
    protected void addFieldAnnotations(ClientModel model, ClientModelProperty property, JavaClass classBlock, JavaSettings settings) {
        super.addFieldAnnotations(model, property, classBlock, settings);

        // JsonInclude
        if (!property.isAdditionalProperties()) {
            String propertyName = model.getName() + "." + property.getName();
            Set<String> propertiesAllowNull = FluentStatic.getFluentJavaSettings().getJavaNamesForPropertyIncludeAlways();
            final boolean propertyAllowNull = propertiesAllowNull.contains(propertyName);

            if (property.getClientType() instanceof MapType) {
                String value = propertyAllowNull ? "JsonInclude.Include.ALWAYS" : "JsonInclude.Include.NON_NULL";
                classBlock.annotation(String.format("JsonInclude(value = %1$s, content = JsonInclude.Include.ALWAYS)", value));
            } else {
                if (propertyAllowNull) {
                    classBlock.annotation("JsonInclude(value = JsonInclude.Include.ALWAYS)");
                }
            }
        }
    }

    @Override
    protected boolean validateOnParentModel(String parentModelName) {
        return parentModelName != null
            && FluentType.nonResourceType(parentModelName)
            && FluentType.nonManagementError(parentModelName);
    }

    @Override
    protected String getGetterName(ClientModel model, ClientModelProperty property) {
        if (FluentType.ManagementError.getName().equals(model.getParentModelName())) {
            // subclass of ManagementError

            if (modelNamer == null) {
                modelNamer = new ModelNamer();
            }
            return modelNamer.modelPropertyGetterName(property);
        } else {
            return super.getGetterName(model, property);
        }
    }

    @Override
    protected List<ClientModelPropertyReference> getClientModelPropertyReferences(ClientModel model) {
        List<ClientModelPropertyReference> propertyReferences = new ArrayList<>();

        String lastParentName = model.getName();
        String parentModelName = model.getParentModelName();
        while (parentModelName != null && !lastParentName.equals(parentModelName)) {
            ClientModel parentModel = ClientModelUtil.getClientModel(parentModelName);
            if (parentModel == null) {
                parentModel = getPredefinedModel(parentModelName).orElse(null);
            }
            if (parentModel != null) {
                if (parentModel.getProperties() != null) {
                    propertyReferences.addAll(parentModel.getProperties().stream()
                        .filter(p -> !p.getClientFlatten() && !p.isAdditionalProperties())
                        .map(ClientModelPropertyReference::ofParentProperty)
                        .collect(Collectors.toList()));
                }

                if (parentModel.getPropertyReferences() != null) {
                    propertyReferences.addAll(parentModel.getPropertyReferences().stream()
                        .filter(ClientModelPropertyReference::isFromFlattenedProperty)
                        .map(ClientModelPropertyReference::ofParentProperty)
                        .collect(Collectors.toList()));
                }
            }

            lastParentName = parentModelName;
            parentModelName = parentModel == null ? null : parentModel.getParentModelName();
        }

        return propertyReferences;
    }

    @Override
    protected void addGeneratedImport(Set<String> imports) {
    }

    @Override
    protected void addGeneratedAnnotation(JavaContext classBlock) {
    }

    private Optional<ClientModel> getPredefinedModel(String modelName) {
        return ResourceClientModel.getResourceClientModel(modelName);
    }
}
