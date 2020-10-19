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

import java.util.ArrayList;
import java.util.List;

public class FluentManager {

    private final Client client;

    private final ClassType type;

    private final String serviceName;

    private final List<FluentManagerProperty> properties = new ArrayList<>();

    public FluentManager(Client client, String clientName) {
        JavaSettings settings = JavaSettings.getInstance();

        this.client = client;

        this.serviceName = CodeNamer.toPascalCase(FluentUtils.getServiceName(clientName));

        this.type = new ClassType.Builder()
                .packageName(settings.getPackage())
                .name(this.serviceName + "Manager")
                .build();
    }

    public Client getClient() {
        return client;
    }

    public ClassType getType() {
        return type;
    }

    public String getDescription() {
        return String.format("Entry point to %1$s.\n%2$s", this.getType().getName(), client.getClientDescription());
    }

    public String getServiceName() {
        return serviceName;
    }

    public List<FluentManagerProperty> getProperties() {
        return properties;
    }
}
