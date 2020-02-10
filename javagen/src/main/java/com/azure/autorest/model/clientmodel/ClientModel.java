package com.azure.autorest.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import java.util.List;
import java.util.Set;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/**
 * A model that is defined by the client.
 */
public class ClientModel {
    /**
     * The package that this model class belongs to.
     */
    private String packageName;
    /**
     * Get the name of this model.
     */
    private String name;
    /**
     * Get the imports for this model.
     */
    private List<String> imports;
    /**
     * Get the description of this model.
     */
    private String description;
    /**
     * Get whether or not this model has model types that derive from it.
     */
    private boolean isPolymorphic;
    /**
     * Get the name of the property that determines which polymorphic model type to create.
     */
    private String polymorphicDiscriminator;
    /**
     * Get the name that is used for this model when it is serialized.
     */
    private String serializedName;
    /**
     * Get whether or not this model needs serialization flattening.
     */
    private boolean needsFlatten;
    /**
     * Get the parent model of this model.
     */
    private String parentModelName;
    /**
     * Get the models that derive from this model.
     */
    private List<ClientModel> derivedModels;
    /**
     * Get the name that will be used for this model's XML element representation.
     */
    private String xmlName;
    /**
     * Get the properties for this model.
     */
    private List<ClientModelProperty> properties;

    /**
     * Create a new ServiceModel with the provided properties.
     * @param name The name of this model.
     * @param imports The imports for this model.
     * @param description The description of this model.
     * @param isPolymorphic Whether or not this model has model types that derive from it.
     * @param polymorphicDiscriminator The name of the property that determines which polymorphic model type to create.
     * @param serializedName The name that is used for this model when it is serialized.
     * @param needsFlatten Whether or not this model needs serialization flattening.
     * @param parentModelName The parent model of this model.
     * @param derivedModels The models that derive from this model.
     * @param xmlName The name that will be used for this model's XML element representation.
     * @param properties The properties for this model.
     */
    public ClientModel(String package_Keyword, String name, List<String> imports, String description, boolean isPolymorphic, String polymorphicDiscriminator, String serializedName, boolean needsFlatten, String parentModelName, List<ClientModel> derivedModels, String xmlName, List<ClientModelProperty> properties) {
        packageName = package_Keyword;
        this.name = name;
        this.imports = imports;
        this.description = description;
        this.isPolymorphic = isPolymorphic;
        this.polymorphicDiscriminator = polymorphicDiscriminator;
        this.serializedName = serializedName;
        this.needsFlatten = needsFlatten;
        this.parentModelName = parentModelName;
        this.derivedModels = derivedModels;
        this.xmlName = xmlName;
        this.properties = properties;
    }

    public final String getPackage() {
        return packageName;
    }

    public final String getName() {
        return name;
    }

    /**
     * The full name of this model class (package and name).
     */
    public final String getFullName() {
        return String.format("%1$s.%2$s", getPackage(), getName());
    }

    public final List<String> getImports() {
        return imports;
    }

    public final String getDescription() {
        return description;
    }

    public final boolean getIsPolymorphic() {
        return isPolymorphic;
    }

    public final String getPolymorphicDiscriminator() {
        return polymorphicDiscriminator;
    }

    public final String getSerializedName() {
        return serializedName;
    }

    public final boolean getNeedsFlatten() {
        return needsFlatten;
    }

    public final String getParentModelName() {
        return parentModelName;
    }

    public final List<ClientModel> getDerivedModels() {
        return derivedModels;
    }

    public final String getXmlName() {
        return xmlName;
    }

    public final List<ClientModelProperty> getProperties() {
        return properties;
    }

    /**
     * Add this ServiceModel's imports to the provided ISet of imports.
     * @param imports The set of imports to add to.
     * @param settings The settings for this Java generator session.
     */
    public final void addImportsTo(Set<String> imports, JavaSettings settings) {
        if (properties.stream().anyMatch(p -> !p.getIsReadOnly())) {
            imports.add("com.azure.core.annotation.Fluent");
        } else {
            imports.add("com.azure.core.annotation.Immutable");
        }
        for (String import_Keyword : getImports()) {
            imports.add(import_Keyword);
        }

        if (getParentModelName() != null && settings.isAzureOrFluent()) {
            if (getParentModelName().equals(ClassType.Resource.getName())) {
                ClassType.Resource.addImportsTo(imports, false);
            } else if (getParentModelName().equals(ClassType.SubResource.getName())) {
                ClassType.SubResource.addImportsTo(imports, false);
            } else {
//                imports.add(getParentModel().getFullName());
            }
        }

        if (getIsPolymorphic()) {
            imports.add("com.fasterxml.jackson.annotation.JsonTypeInfo");
            imports.add("com.fasterxml.jackson.annotation.JsonTypeName");

            if (getDerivedModels() != null && getDerivedModels().size() > 0) {
                imports.add("com.fasterxml.jackson.annotation.JsonSubTypes");
            }
        }

        for (ClientModelProperty property : getProperties()) {
            property.addImportsTo(imports, settings.shouldGenerateXmlSerialization());
        }
    }
}
