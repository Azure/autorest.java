/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ClientModelPropertyReference;
import com.azure.autorest.model.clientmodel.IType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ModelProperty {

    private final String description;
    private final IType clientType;

    private final ClientModelProperty property;
    private final ClientModelPropertyReference propertyReference;

    private ModelProperty(ClientModelProperty property, ClientModelPropertyReference propertyReference) {
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
        return propertyReference == null ? property.getGetterName() : propertyReference.getGetterName();
    }

    public String getSetterName() {
        return propertyReference == null ? property.getSetterName() : propertyReference.getSetterName();
    }

    public void addImportsTo(Set<String> imports) {
        property.addImportsTo(imports, false);
    }

    public String getName() {
        return propertyReference == null ? property.getName() : propertyReference.getName();
    }

    public String getDescription() {
        return description;
    }

    public IType getClientType() {
        return clientType;
    }

    public boolean isRequired() {
        if (propertyReference != null) {
            return propertyReference.getTargetProperty().isRequired() && propertyReference.getReferenceProperty().isRequired();
        } else {
            return property.isRequired();
        }
    }

    public boolean isConstant() {
        return property.getIsConstant();
    }

    public boolean isReadOnly() {
        if (propertyReference != null) {
            return propertyReference.getTargetProperty().getIsReadOnly() || propertyReference.getReferenceProperty().getIsReadOnly();
        } else {
            return property.getIsReadOnly();
        }
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
            return FluentUtils.splitFlattenedSerializedName(property.getSerializedName());
        } else {
            return Collections.singletonList(property.getSerializedName());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelProperty that = (ModelProperty) o;
        return Objects.equals(property, that.property) && Objects.equals(propertyReference, that.propertyReference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(property, propertyReference);
    }
}
