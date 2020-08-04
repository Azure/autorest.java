/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.util.CodeNamer;

public class FluentManager {

    private final Client client;

    private final ClassType classType;

    public FluentManager(Client client, String clientName) {
        JavaSettings settings = JavaSettings.getInstance();

        this.client = client;

        this.classType = new ClassType.Builder()
                .packageName(settings.getPackage())
                .name(CodeNamer.toPascalCase(FluentUtils.getServiceName(clientName)) + "Manager")
                .build();
    }

    public ClassType getClassType() {
        return classType;
    }

    public String getDescription() {
        return String.format("Entry point to %1$s.\n%2$s", this.getClassType().getName(), client.getClientDescription());
    }
}
