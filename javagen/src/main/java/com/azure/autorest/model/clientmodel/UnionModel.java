// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class UnionModel {

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
     * Get the properties for this model.
     */
    private final List<ClientModelProperty> properties;
    private final ImplementationDetails implementationDetails;

    protected UnionModel(
            String packageKeyword, String name, List<String> imports, String description,
            List<ClientModelProperty> properties, ImplementationDetails implementationDetails) {
        this.packageName = packageKeyword;
        this.name = name;
        this.imports = imports;
        this.description = description;
        this.properties = properties;
        this.implementationDetails = implementationDetails;
    }

    public final String getFullName() {
        return String.format("%1$s.%2$s", getPackage(), getName());
    }

    public void addImportsTo(Set<String> imports) {
        imports.add(this.getFullName());

        imports.addAll(getImports());

        for (ClientModelProperty property : getProperties()) {
            property.addImportsTo(imports, false);
        }
    }

    public String getPackage() {
        return packageName;
    }

    public String getName() {
        return name;
    }

    public List<String> getImports() {
        return imports;
    }

    public String getDescription() {
        return description;
    }

    public List<ClientModelProperty> getProperties() {
        return properties;
    }

    public ImplementationDetails getImplementationDetails() {
        return implementationDetails;
    }

    public static class Builder {
        protected String packageName;
        protected String name;
        protected List<String> imports = Collections.emptyList();
        protected String description;
        protected List<ClientModel> derivedModels = Collections.emptyList();
        protected List<ClientModelProperty> properties;
        protected ImplementationDetails implementationDetails;

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
         * Sets the properties for this model.
         * @param properties the properties for this model
         * @return the Builder itself
         */
        public Builder properties(List<ClientModelProperty> properties) {
            this.properties = properties;
            return this;
        }

        /**
         * Sets the implementation details for the model.
         * @param implementationDetails the implementation details.
         * @return the Builder itself
         */
        public Builder implementationDetails(ImplementationDetails implementationDetails) {
            this.implementationDetails = implementationDetails;
            return this;
        }

        public UnionModel build() {
            return new UnionModel(packageName,
                    name,
                    imports,
                    description,
                    properties,
                    implementationDetails);
        }
    }
}
