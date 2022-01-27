// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.util.CodeNamer;

public class FluentManagerProperty {

    private final String name;

    private final ClassType fluentType;
    private final ClassType fluentImplementType;
    private final String innerClientGetMethod;

    public FluentManagerProperty(FluentResourceCollection collection) {
        this.fluentType = collection.getInterfaceType();
        this.fluentImplementType = collection.getImplementationType();

        String interfaceName = fluentType.getName();
        this.name = CodeNamer.toCamelCase(interfaceName);

        this.innerClientGetMethod = "get" + CodeNamer.toPascalCase(collection.getInnerGroupClient().getVariableName());
    }

    public String getName() {
        return name;
    }

    public ClassType getFluentType() {
        return fluentType;
    }

    public ClassType getFluentImplementType() {
        return fluentImplementType;
    }

    public String getMethodName() {
        return CodeNamer.getModelNamer().modelPropertyGetterName(name);
    }

    public String getInnerClientGetMethod() {
        return innerClientGetMethod;
    }
}
