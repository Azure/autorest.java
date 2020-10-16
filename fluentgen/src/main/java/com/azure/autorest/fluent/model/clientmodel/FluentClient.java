/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.model.clientmodel.Client;

import java.util.ArrayList;
import java.util.List;

public class FluentClient {

    private final Client client;

    private FluentManager manager;

    private final List<FluentResourceModel> resourceModels = new ArrayList<>();

    private final List<FluentResourceCollection> resourceCollections = new ArrayList<>();

    public FluentClient(Client client) {
        this.client = client;
    }

    public List<FluentResourceModel> getResourceModels() {
        return resourceModels;
    }

    public List<FluentResourceCollection> getResourceCollections() {
        return resourceCollections;
    }

    public FluentManager getManager() {
        return manager;
    }

    public void setManager(FluentManager manager) {
        this.manager = manager;
    }

    public Client getInnerClient() {
        return this.client;
    }
}
