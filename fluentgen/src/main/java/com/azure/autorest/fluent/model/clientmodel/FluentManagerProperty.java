/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.util.CodeNamer;

public class FluentManagerProperty {

    private final String name;
    private final ClassType fluentType;

    public FluentManagerProperty(FluentResourceCollection collection) {
        this.fluentType = collection.getCollectionInterfaceClassType();
        String interfaceName = fluentType.getName();
        name = CodeNamer.toCamelCase(interfaceName);
    }

    public String getName() {
        return name;
    }

    public ClassType getFluentType() {
        return fluentType;
    }

    public String getMethodName() {
        return CodeNamer.getModelNamer().modelPropertyGetterName(name);
    }
}
