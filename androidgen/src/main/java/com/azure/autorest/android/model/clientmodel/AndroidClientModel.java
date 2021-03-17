package com.azure.autorest.android.model.clientmodel;

import com.azure.autorest.model.clientmodel.ClientModel;

public class AndroidClientModel extends ClientModel {
    protected AndroidClientModel(String package_Keyword, String name, java.util.List<String> imports, String description,
                          boolean isPolymorphic, String polymorphicDiscriminator, String serializedName, boolean needsFlatten,
                          String parentModelName, java.util.List<com.azure.autorest.model.clientmodel.ClientModel> derivedModels, String xmlName, String xmlNamespace,
                          java.util.List<com.azure.autorest.model.clientmodel.ClientModelProperty> properties, java.util.List<com.azure.autorest.model.clientmodel.ClientModelPropertyReference> propertyReferences) {
        super(package_Keyword,
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
                properties,
                propertyReferences);
    }

    @Override
    protected void addFluentAnnotationImport(java.util.Set<String> imports) {
        imports.add("com.azure.android.core.rest.annotation.Fluent");
    }

    @Override
    protected void addImmutableAnnotationImport(java.util.Set<String> imports) {
        imports.add("com.azure.android.core.rest.annotation.Immutable");
    }

    @Override
    protected void addJsonFlattenAnnotationImport(java.util.Set<String> imports) {
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
                    properties,
                    propertyReferences);
        }
    }
}
