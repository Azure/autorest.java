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
    private final ClassType fluentImplementType;

    private final String buildMethodInvocation;

    public FluentManagerProperty(FluentResourceCollection collection) {
        this.fluentType = collection.getInterfaceType();
        this.fluentImplementType = collection.getImplementationType();

        String interfaceName = fluentType.getName();
        this.name = CodeNamer.toCamelCase(interfaceName);

        String innerClientName = collection.getInnerGroupClient().getClassBaseName().endsWith("Client")
                ? collection.getInnerGroupClient().getClassBaseName()
                : collection.getInnerGroupClient().getClassBaseName() + "Client";
        this.buildMethodInvocation = String.format("build%1$s()", innerClientName);
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

    public String getBuildMethodInvocation() {
        return buildMethodInvocation;
    }
}
