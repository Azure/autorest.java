/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.model.FluentType;
import com.azure.autorest.fluent.model.arm.ResourceClientModel;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ClientModelPropertyReference;
import com.azure.autorest.model.clientmodel.ClientModels;
import com.azure.autorest.template.ModelTemplate;
import com.azure.autorest.util.ModelNamer;
import com.azure.core.util.CoreUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        if (JavaSettings.getInstance().isOverrideSetterFromSuperclass()) {
            String lastParentName = model.getName();
            String parentModelName = model.getParentModelName();
            while (parentModelName != null && !lastParentName.equals(parentModelName)) {
                ClientModel parentModel = ClientModels.Instance.getModel(parentModelName);
                if (parentModel == null) {
                    parentModel = getPredefinedModel(parentModelName).orElse(null);
                }
                if (parentModel != null) {
                    if (parentModel.getProperties() != null) {
                        propertyReferences.addAll(parentModel.getProperties().stream()
                                .filter(p -> !("additionalProperties".equals(p.getName()) && CoreUtils.isNullOrEmpty(p.getSerializedName())))   // exclude `additionalProperties`
                                .map(ClientModelPropertyReference::new)
                                .collect(Collectors.toList()));
                    }
                }

                lastParentName = parentModelName;
                parentModelName = parentModel == null ? null : parentModel.getParentModelName();
            }
        }
        return propertyReferences;
    }

    private Optional<ClientModel> getPredefinedModel(String modelName) {
        return ResourceClientModel.getResourceClientModel(modelName);
    }
}
