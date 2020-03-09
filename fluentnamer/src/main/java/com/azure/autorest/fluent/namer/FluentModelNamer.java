/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 *
 */

package com.azure.autorest.fluent.namer;

import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.util.CodeNamer;
import com.azure.autorest.util.ModelNamer;

public class FluentModelNamer extends ModelNamer {

    @Override
    public String modelPropertyGetterName(ClientModelProperty property) {
        String propertyName = property.getName();
        return this.modelPropertyGetterName(propertyName);
    }

    @Override
    public String modelPropertyGetterName(String propertyName) {
        if (propertyName.equalsIgnoreCase("id")) {
            // temporary fix for ProxyResource.getId
            return "get" + CodeNamer.toPascalCase(propertyName);
        }
        return CodeNamer.toCamelCase(propertyName);
    }

    @Override
    public String modelPropertySetterName(ClientModelProperty property) {
        String propertyName = property.getName();
        return this.modelPropertySetterName(propertyName);
    }

    public String modelPropertySetterName(String propertyName) {
        return "with" + CodeNamer.toPascalCase(propertyName);
    }
}
