/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ClientModelPropertyReference;
import com.azure.autorest.model.clientmodel.IType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class ModelProperty {

    private final String name;
    private final String description;
    private final IType clientType;

    private final ClientModelProperty property;
    private final ClientModelPropertyReference propertyReference;

    private ModelProperty(ClientModelProperty property, ClientModelPropertyReference propertyReference) {
        this.name = property.getName();
        this.description = property.getDescription();
        this.clientType = property.getClientType();

        this.property = property;
        this.propertyReference = propertyReference;
    }

    public static ModelProperty ofClientModelProperty(ClientModelProperty property) {
        return new ModelProperty(property, null);
    }

    public static ModelProperty ofClientModelPropertyReference(ClientModelPropertyReference propertyReference) {
        return new ModelProperty(propertyReference.getReferenceProperty(), propertyReference);
    }

    public String getGetterName() {
        return property.getGetterName();
    }

    public String getSetterName() {
        return property.getSetterName();
    }

    public void addImportsTo(Set<String> imports) {
        property.addImportsTo(imports, false);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public IType getClientType() {
        return clientType;
    }

    public boolean isRequired() {
        return property.isRequired();
    }

    public boolean isConstant() {
        return property.getIsConstant();
    }

    public boolean isReadOnly() {
        return property.getIsReadOnly();
    }

    public boolean isReadOnlyForCreate() {
        return property.getIsReadOnlyForCreate();
    }

    public boolean isReadOnlyForUpdate() {
        return property.getIsReadOnlyForUpdate();
    }

    public String getSerializedName() {
        if (propertyReference == null) {
            return property.getSerializedName();
        } else {
            return String.format("%1$s.%2$s",
                    propertyReference.getTargetProperty().getSerializedName().replace(".", "\\\\."),
                    propertyReference.getReferenceProperty().getSerializedName()).replace(".", "\\\\.");
        }
    }

    public List<String> getSerializedNames() {
        if (propertyReference != null) {
            return Arrays.asList(propertyReference.getTargetProperty().getSerializedName(), propertyReference.getReferenceProperty().getSerializedName());
        } else if (!property.getNeedsFlatten()) {
            // TODO
            return Arrays.asList(property.getSerializedName().split(Pattern.quote(".")));
        } else {
            return Collections.singletonList(property.getSerializedName());
        }
    }
}
