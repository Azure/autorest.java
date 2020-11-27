// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.android.model;

import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import java.util.List;
import java.util.Set;

public class AndroidClientModel extends ClientModel {

    private AndroidClientModel(String package_Keyword, String name, List<String> imports, String description,
        boolean isPolymorphic, String polymorphicDiscriminator, String serializedName, boolean needsFlatten,
        String parentModelName, List<ClientModel> derivedModels, String xmlName, String xmlNamespace,
        List<ClientModelProperty> properties) {
            super(package_Keyword, name, imports, description,
                isPolymorphic, polymorphicDiscriminator, serializedName, needsFlatten,
                parentModelName, derivedModels, xmlName, xmlNamespace,
                properties);
    }

    @Override
    protected void addReadWriteAnnotationImport(List<ClientModelProperty> properties, Set<String> imports) {
        if (properties.stream().anyMatch(p -> !p.getIsReadOnly())) {
            imports.add("com.azure.android.core.annotation.Fluent");
        } else {
            imports.add("com.azure.android.core.annotation.Immutable");
        }
    }

    @Override
    protected void addJsonFlattenImport(Set<String> imports) {
        imports.add("com.azure.android.core.annotation.JsonFlatten");
    }

    public static class Builder extends ClientModel.Builder {

        @Override
        public ClientModel build() {
            return new AndroidClientModel(packageName,
                    name,
                    imports,
                    description,
                    isPolymorphic,
                    polymorphicDiscriminator,
                    serializedName,
                    needsFlatten,
                    parentModelName,
                    derivedModels,
                    xmlName,
                    xmlNamespace,
                    properties);
        }
    }
    
}