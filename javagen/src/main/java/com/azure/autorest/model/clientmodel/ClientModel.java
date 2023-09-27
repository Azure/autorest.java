// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * A model that is defined by the client.
 */
public class ClientModel {
    /**
     * The package that this model class belongs to.
     */
    private final String packageName;
    /**
     * Get the name of this model.
     */
    private final String name;
    /**
     * Get the imports for this model.
     */
    private final List<String> imports;
    /**
     * Get the description of this model.
     */
    private final String description;
    /**
     * Get whether this model has model types that derive from it.
     */
    private final boolean isPolymorphic;
    /**
     * Get the name of the property that determines which polymorphic model type to create.
     */
    private final String polymorphicDiscriminator;
    /**
     * Get the name that is used for this model when it is serialized.
     */
    private final String serializedName;
    /**
     * Get whether this model needs serialization flattening.
     */
    private final boolean needsFlatten;
    /**
     * Get the parent model of this model.
     */
    private final String parentModelName;
    /**
     * Get the models that derive from this model.
     */
    private final List<ClientModel> derivedModels;
    /**
     * Get the name that will be used for this model's XML element representation.
     */
    private final String xmlName;

    /**
     * The xml namesapce for a model.
     */
    private final String xmlNamespace;

    /**
     * Get the properties for this model.
     */
    private final List<ClientModelProperty> properties;

    private final List<ClientModelPropertyReference> propertyReferences;

    private final IType modelType;

    /*
     * Whether this model is a strongly-typed HTTP headers class.
     */
    private final boolean stronglyTypedHeader;

    private final ImplementationDetails implementationDetails;
    private final boolean usedInXml;

    private final String crossLanguageDefinitionId;

    /**
     * Create a new ServiceModel with the provided properties.
     *
     * @param name The name of this model.
     * @param imports The imports for this model.
     * @param description The description of this model.
     * @param isPolymorphic Whether this model has model types that derive from it.
     * @param polymorphicDiscriminator The name of the property that determines which polymorphic model type to create.
     * @param serializedName The name that is used for this model when it is serialized.
     * @param needsFlatten Whether this model needs serialization flattening.
     * @param parentModelName The parent model of this model.
     * @param derivedModels The models that derive from this model.
     * @param xmlName The name that will be used for this model's XML element representation.
     * @param properties The properties for this model.
     * @param propertyReferences The property references for this model.
     * @param modelType the type of the model.
     * @param stronglyTypedHeader Whether this model is a strongly-typed HTTP headers class.
     * @param usedInXml Whether the model is used in XML serialization.
     */
    protected ClientModel(String packageKeyword, String name, List<String> imports, String description,
        boolean isPolymorphic, String polymorphicDiscriminator, String serializedName, boolean needsFlatten,
        String parentModelName, List<ClientModel> derivedModels, String xmlName, String xmlNamespace,
        List<ClientModelProperty> properties, List<ClientModelPropertyReference> propertyReferences,
        IType modelType, boolean stronglyTypedHeader, ImplementationDetails implementationDetails, boolean usedInXml, String crossLanguageDefinitionId) {
        packageName = packageKeyword;
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
        this.propertyReferences = propertyReferences;
        this.modelType = modelType;
        this.stronglyTypedHeader = stronglyTypedHeader;
        this.implementationDetails = implementationDetails;
        this.usedInXml = usedInXml;
        this.crossLanguageDefinitionId = crossLanguageDefinitionId;
    }

    public String getCrossLanguageDefinitionId() {
        return crossLanguageDefinitionId;
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

    public final boolean isPolymorphic() {
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

    public IType getType() {
        return modelType;
    }

    public List<ClientModelPropertyReference> getPropertyReferences() {
        return propertyReferences == null ? Collections.emptyList() : propertyReferences;
    }

    /**
     * Whether this model is a strongly-typed HTTP headers class.
     *
     * @return Whether this model is a strongly-typed HTTP headers class.
     */
    public boolean isStronglyTypedHeader() {
        return stronglyTypedHeader;
    }

    public ImplementationDetails getImplementationDetails() {
        return implementationDetails;
    }

    /**
     * List the properties that have access (getter or setter) methods.
     * <p>
     * It does not include properties from superclass (even though they can be accessed via inheritance). It does not
     * include properties that only have private access (e.g. property of a flattened model). It includes properties
     * that can be accessed from the model but not declared in this model (e.g. properties from a flattened model).
     *
     * @return The properties that have access (getter or setter) methods.
     */
    public List<ClientModelPropertyAccess> getAccessibleProperties() {
        List<ClientModelPropertyAccess> propertyAccesses = new ArrayList<>();
        if (properties != null) {
            for (ClientModelProperty property : properties) {
                if (!property.getClientFlatten()) {
                    propertyAccesses.add(property);
                }
            }
        }

        for (ClientModelPropertyReference clientModelPropertyReference : getPropertyReferences()) {
            if (clientModelPropertyReference.isFromFlattenedProperty()) {
                propertyAccesses.add(clientModelPropertyReference);
            }
        }

        return propertyAccesses;
    }

    /**
     * Add this ServiceModel's imports to the provided ISet of imports.
     *
     * @param imports The set of imports to add to.
     * @param settings The settings for this Java generator session.
     */
    public void addImportsTo(Set<String> imports, JavaSettings settings) {
        // whether annotated as Immutable or Fluent is also determined by its superclass
        imports.add(this.getFullName());
        addFluentAnnotationImport(imports);
        addImmutableAnnotationImport(imports);

        if (settings.getClientFlattenAnnotationTarget() == JavaSettings.ClientFlattenAnnotationTarget.TYPE && needsFlatten) {
            addJsonFlattenAnnotationImport(imports);
        }

        imports.addAll(getImports());

        if (isPolymorphic()) {
            imports.add("com.fasterxml.jackson.annotation.JsonTypeInfo");
            imports.add("com.fasterxml.jackson.annotation.JsonTypeName");

            if (getDerivedModels() != null && getDerivedModels().size() > 0) {
                imports.add("com.fasterxml.jackson.annotation.JsonSubTypes");
                getDerivedModels().forEach(m -> imports.add(m.getFullName()));
            }
        }

        for (ClientModelProperty property : getProperties()) {
            property.addImportsTo(imports, usedInXml);
        }
    }

    protected void addJsonFlattenAnnotationImport(Set<String> imports) {
        imports.add("com.azure.core.annotation.JsonFlatten");
    }

    protected void addImmutableAnnotationImport(Set<String> imports) {
        imports.add("com.azure.core.annotation.Immutable");
    }

    protected void addFluentAnnotationImport(Set<String> imports) {
        imports.add("com.azure.core.annotation.Fluent");
    }

    /**
     * Whether the model is used in XML serialization.
     *
     * @return Whether the model is used in XML serialization.
     */
    public final boolean isUsedInXml() {
        return usedInXml;
    }

    public static class Builder {
        protected String packageName;
        protected String name;
        protected List<String> imports = Collections.emptyList();
        protected String description;
        protected boolean isPolymorphic;
        protected String polymorphicDiscriminator;
        protected String serializedName;
        protected boolean needsFlatten = false;
        protected String parentModelName;
        protected List<ClientModel> derivedModels = Collections.emptyList();
        protected String xmlName;
        protected List<ClientModelProperty> properties;
        protected String xmlNamespace;
        protected List<ClientModelPropertyReference> propertyReferences;
        protected IType modelType;
        protected boolean stronglyTypedHeader;
        protected ImplementationDetails implementationDetails;
        protected boolean usedInXml;
        protected String crossLanguageDefinitionId;

        /**
         * Sets the package that this model class belongs to.
         *
         * @param packageName the package that this model class belongs to
         * @return the Builder itself
         */
        public Builder packageName(String packageName) {
            this.packageName = packageName;
            return this;
        }

        /**
         * Sets the name of this model.
         *
         * @param name the name of this model
         * @return the Builder itself
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the imports for this model.
         *
         * @param imports the imports for this model
         * @return the Builder itself
         */
        public Builder imports(List<String> imports) {
            this.imports = imports;
            return this;
        }

        /**
         * Sets the description of this model.
         *
         * @param description the description of this model
         * @return the Builder itself
         */
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets whether this model has model types that derive from it.
         *
         * @param isPolymorphic whether this model has model types that derive from it
         * @return the Builder itself
         */
        public Builder polymorphic(boolean isPolymorphic) {
            this.isPolymorphic = isPolymorphic;
            return this;
        }

        /**
         * Sets the name of the property that determines which polymorphic model type to create.
         *
         * @param polymorphicDiscriminator the name of the property that determines which polymorphic model type to
         * create
         * @return the Builder itself
         */
        public Builder polymorphicDiscriminator(String polymorphicDiscriminator) {
            this.polymorphicDiscriminator = polymorphicDiscriminator;
            return this;
        }

        /**
         * Sets the name that is used for this model when it is serialized.
         *
         * @param serializedName the name that is used for this model when it is serialized
         * @return the Builder itself
         */
        public Builder serializedName(String serializedName) {
            this.serializedName = serializedName;
            return this;
        }

        /**
         * Sets whether this model needs serialization flattening.
         *
         * @param needsFlatten whether this model needs serialization flattening
         * @return the Builder itself
         */
        public Builder needsFlatten(boolean needsFlatten) {
            this.needsFlatten = needsFlatten;
            return this;
        }

        /**
         * Sets the parent model of this model.
         *
         * @param parentModelName the parent model of this model
         * @return the Builder itself
         */
        public Builder parentModelName(String parentModelName) {
            this.parentModelName = parentModelName;
            return this;
        }

        /**
         * Sets the models that derive from this model.
         *
         * @param derivedModels the models that derive from this model
         * @return the Builder itself
         */
        public Builder derivedModels(List<ClientModel> derivedModels) {
            this.derivedModels = derivedModels;
            return this;
        }

        /**
         * Sets the name that will be used for this model's XML element representation.
         *
         * @param xmlName the name that will be used for this model's XML element representation
         * @return the Builder itself
         */
        public Builder xmlName(String xmlName) {
            this.xmlName = xmlName;
            return this;
        }

        /**
         * Sets the XML namespace that will be used for this model's XML element representation.
         *
         * @param xmlNamespace the XML namespace that will be used for this model's XML element representation
         * @return the Builder itself
         */
        public Builder xmlNamespace(String xmlNamespace) {
            this.xmlNamespace = xmlNamespace;
            return this;
        }

        /**
         * Sets the properties for this model.
         *
         * @param properties the properties for this model
         * @return the Builder itself
         */
        public Builder properties(List<ClientModelProperty> properties) {
            this.properties = properties;
            return this;
        }

        /**
         * Sets the property references for this model. They are used to call property method from i.e. a parent model.
         *
         * @param propertyReferences the property references.
         * @return the Builder itself
         */
        public Builder propertyReferences(List<ClientModelPropertyReference> propertyReferences) {
            this.propertyReferences = propertyReferences;
            return this;
        }

        /**
         * Sets the model type.
         *
         * @param modelType the model type.
         * @return the Builder itself
         */
        public Builder type(IType modelType) {
            this.modelType = modelType;
            return this;
        }

        /**
         * Sets whether the model is a strongly-typed HTTP headers class.
         *
         * @param stronglyTypedHeader Whether the model is a strongly-typed HTTP headers class.
         * @return the Builder itself
         */
        public Builder stronglyTypedHeader(boolean stronglyTypedHeader) {
            this.stronglyTypedHeader = stronglyTypedHeader;
            return this;
        }

        /**
         * Sets the implementation details for the model.
         *
         * @param implementationDetails the implementation details.
         * @return the Builder itself
         */
        public Builder implementationDetails(ImplementationDetails implementationDetails) {
            this.implementationDetails = implementationDetails;
            return this;
        }

        /**
         * Sets whether the model is used in XML serialization.
         *
         * @param usedInXml Whether the model is used in XML serialization.
         * @return the Builder itself
         */
        public Builder usedInXml(boolean usedInXml) {
            this.usedInXml = usedInXml;
            return this;
        }

        public Builder crossLanguageDefinitionId(String crossLanguageDefinitionId) {
            this.crossLanguageDefinitionId = crossLanguageDefinitionId;
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
                properties,
                propertyReferences,
                modelType,
                stronglyTypedHeader,
                implementationDetails,
                usedInXml,
                crossLanguageDefinitionId);
        }
    }
}
