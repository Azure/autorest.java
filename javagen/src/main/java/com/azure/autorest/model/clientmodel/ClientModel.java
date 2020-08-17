// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import java.util.List;
import java.util.Set;

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
     * The xml namesapce for a model.
     */
    private final String xmlNamespace;

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
    protected ClientModel(String package_Keyword, String name, List<String> imports, String description,
            boolean isPolymorphic, String polymorphicDiscriminator, String serializedName, boolean needsFlatten,
            String parentModelName, List<ClientModel> derivedModels, String xmlName, String xmlNamespace,
            List<ClientModelProperty> properties) {
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
        this.xmlNamespace = xmlNamespace;
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

    public String getXmlNamespace() {
        return xmlNamespace;
    }

    public final List<ClientModelProperty> getProperties() {
        return properties;
    }

    protected void addReadWriteAnnotationImport(List<ClientModelProperty> properties, Set<String> imports) {
        if (properties.stream().anyMatch(p -> !p.getIsReadOnly())) {
            imports.add("com.azure.core.annotation.Fluent");
        } else {
            imports.add("com.azure.core.annotation.Immutable");
        }
    }

    /**
     * Add this ServiceModel's imports to the provided ISet of imports.
     * @param imports The set of imports to add to.
     * @param settings The settings for this Java generator session.
     */
    public final void addImportsTo(Set<String> imports, JavaSettings settings) {
        addReadWriteAnnotationImport(properties, imports);        

        if (needsFlatten) {
            imports.add("com.azure.core.annotation.JsonFlatten");
        }

        for (String import_Keyword : getImports()) {
            imports.add(import_Keyword);
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

    public static class Builder {
        protected String packageName;
        protected String name;
        protected List<String> imports;
        protected String description;
        protected boolean isPolymorphic;
        protected String polymorphicDiscriminator;
        protected String serializedName;
        protected boolean needsFlatten = false;
        protected String parentModelName;
        protected List<ClientModel> derivedModels;
        protected String xmlName;
        protected List<ClientModelProperty> properties;
        protected String xmlNamespace;

        /**
         * Sets the package that this model class belongs to.
         * @param packageName the package that this model class belongs to
         * @return the Builder itself
         */
        public Builder packageName(String packageName) {
            this.packageName = packageName;
            return this;
        }

        /**
         * Sets the name of this model.
         * @param name the name of this model
         * @return the Builder itself
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the imports for this model.
         * @param imports the imports for this model
         * @return the Builder itself
         */
        public Builder imports(List<String> imports) {
            this.imports = imports;
            return this;
        }

        /**
         * Sets the description of this model.
         * @param description the description of this model
         * @return the Builder itself
         */
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets whether or not this model has model types that derive from it.
         * @param isPolymorphic whether or not this model has model types that derive from it
         * @return the Builder itself
         */
        public Builder isPolymorphic(boolean isPolymorphic) {
            this.isPolymorphic = isPolymorphic;
            return this;
        }

        /**
         * Sets the name of the property that determines which polymorphic model type to create.
         * @param polymorphicDiscriminator the name of the property that determines which polymorphic model type to create
         * @return the Builder itself
         */
        public Builder polymorphicDiscriminator(String polymorphicDiscriminator) {
            this.polymorphicDiscriminator = polymorphicDiscriminator;
            return this;
        }

        /**
         * Sets the name that is used for this model when it is serialized.
         * @param serializedName the name that is used for this model when it is serialized
         * @return the Builder itself
         */
        public Builder serializedName(String serializedName) {
            this.serializedName = serializedName;
            return this;
        }

        /**
         * Sets whether or not this model needs serialization flattening.
         * @param needsFlatten whether or not this model needs serialization flattening
         * @return the Builder itself
         */
        public Builder needsFlatten(boolean needsFlatten) {
            this.needsFlatten = needsFlatten;
            return this;
        }

        /**
         * Sets the parent model of this model.
         * @param parentModelName the parent model of this model
         * @return the Builder itself
         */
        public Builder parentModelName(String parentModelName) {
            this.parentModelName = parentModelName;
            return this;
        }

        /**
         * Sets the models that derive from this model.
         * @param derivedModels the models that derive from this model
         * @return the Builder itself
         */
        public Builder derivedModels(List<ClientModel> derivedModels) {
            this.derivedModels = derivedModels;
            return this;
        }

        /**
         * Sets the name that will be used for this model's XML element representation.
         * @param xmlName the name that will be used for this model's XML element representation
         * @return the Builder itself
         */
        public Builder xmlName(String xmlName) {
            this.xmlName = xmlName;
            return this;
        }

        /**
         * Sets the XML namespace that will be used for this model's XML element representation.
         * @param xmlNamespace the XML namespace that will be used for this model's XML element representation
         * @return the Builder itself
         */
        public Builder xmlNamespace(String xmlNamespace) {
            this.xmlNamespace = xmlNamespace;
            return this;
        }

        /**
         * Sets the properties for this model.
         * @param properties the properties for this model
         * @return the Builder itself
         */
        public Builder properties(List<ClientModelProperty> properties) {
            this.properties = properties;
            return this;
        }

        public ClientModel build() {
            return new ClientModel(packageName,
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
