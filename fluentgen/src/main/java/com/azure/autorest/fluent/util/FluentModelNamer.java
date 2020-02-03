/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 *
 */

package com.azure.autorest.fluent.util;

import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.util.CodeNamer;
import com.azure.autorest.util.ModelNamer;

public class FluentModelNamer extends ModelNamer {

    @Override
    public String modelPropertyGetterName(ClientModelProperty property) {
        return CodeNamer.toCamelCase(property.getName());
    }

    @Override
    public String modelPropertyGetterName(String propertyName) {
        return CodeNamer.toCamelCase(propertyName);
    }

    @Override
    public String modelPropertySetterName(ClientModelProperty property) {
        return "with" + CodeNamer.toPascalCase(property.getName());
    }
}
