/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ClientModelPropertyBase;
import com.azure.autorest.model.clientmodel.ClientModelPropertyReference;
import com.azure.autorest.model.clientmodel.IType;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ModelProperty {

    private final ClientModelPropertyBase property;

    private ModelProperty(ClientModelPropertyBase property) {
        this.property = property;
    }

    public static ModelProperty ofClientModelProperty(ClientModelPropertyBase property) {
        return new ModelProperty(property);
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
        return property.getName();
    }

    public String getDescription() {
        return property.getDescription();
    }

    public IType getClientType() {
        return property.getClientType();
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
        if (property instanceof ClientModelProperty) {
            return ((ClientModelProperty) property).getSerializedName();
        } else if (property instanceof ClientModelPropertyReference) {
            return ((ClientModelPropertyReference) property).getAllProperties().stream()
                    .map(ClientModelProperty::getSerializedName)
                    .map(s -> s.replace(".", "\\\\."))
                    .collect(Collectors.joining("."));
        } else {
            throw new IllegalStateException("Unknown subclass of ClientModelPropertyBase: " + property.getClass().getName());
        }
    }

    public List<String> getSerializedNames() {
        if (property instanceof ClientModelProperty) {
            ClientModelProperty clientModelProperty = (ClientModelProperty) property;
            if (!clientModelProperty.getNeedsFlatten()) {
                return FluentUtils.splitFlattenedSerializedName(clientModelProperty.getSerializedName());
            } else {
                return Collections.singletonList(clientModelProperty.getSerializedName());
            }
        } else if (property instanceof ClientModelPropertyReference) {
            return ((ClientModelPropertyReference) property).getAllProperties().stream()
                    .map(ClientModelProperty::getSerializedName)
                    .collect(Collectors.toList());
        } else {
            throw new IllegalStateException("Unknown subclass of ClientModelPropertyBase: " + property.getClass().getName());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelProperty that = (ModelProperty) o;
        return Objects.equals(property, that.property);
    }

    @Override
    public int hashCode() {
        return Objects.hash(property);
    }
}
