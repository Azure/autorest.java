// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.android.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ClientModelPropertyReference;
import com.azure.autorest.model.clientmodel.IType;

import java.util.List;
import java.util.Set;

public class AndroidClientModel extends ClientModel {
    protected AndroidClientModel(String packageKeyword, String name, List<String> imports, String description,
        boolean isPolymorphic, ClientModelProperty polymorphicDiscriminator, String polymorphicDiscriminatorName,
        String serializedName, boolean needsFlatten, String parentModelName, List<ClientModel> derivedModels,
        String xmlName, String xmlNamespace, List<ClientModelProperty> properties,
        List<ClientModelPropertyReference> propertyReferences, IType modelType, boolean stronglyTypedHeader,
        boolean usedInXml, Set<String> serializationFormats) {
        super(packageKeyword, name, imports, description, isPolymorphic, polymorphicDiscriminator,
            polymorphicDiscriminatorName, serializedName, needsFlatten, parentModelName, derivedModels, xmlName,
            xmlNamespace, properties, propertyReferences, modelType, stronglyTypedHeader, null, usedInXml,
            serializationFormats, null);
    }

    @Override
    protected void addFluentAnnotationImport(Set<String> imports) {
        imports.add("com.azure.android.core.rest.annotation.Fluent");
    }

    @Override
    protected void addImmutableAnnotationImport(Set<String> imports) {
        imports.add("com.azure.android.core.rest.annotation.Immutable");
    }

    @Override
    protected void addJsonFlattenAnnotationImport(Set<String> imports) {
        imports.add("com.azure.android.core.serde.jackson.JsonFlatten");
    }

    @Override
    public void addImportsTo(Set<String> imports, JavaSettings settings) {
        super.addImportsTo(imports, settings);

        if (imports.contains(ClassType.UNIX_TIME_DATE_TIME.getFullName())) {
            imports.remove(ClassType.UNIX_TIME_DATE_TIME.getFullName());
            imports.add(ClassType.ANDROID_DATE_TIME.getFullName());
        }
    }

    public static class Builder extends ClientModel.Builder {
        @Override
        public ClientModel build() {
            return new AndroidClientModel(packageName, name, imports, description, isPolymorphic,
                polymorphicDiscriminator, polymorphicDiscriminatorName, serializedName, needsFlatten, parentModelName,
                derivedModels, xmlName, xmlNamespace, properties, propertyReferences, modelType, stronglyTypedHeader,
                usedInXml, serializationFormats);
        }
    }
}
