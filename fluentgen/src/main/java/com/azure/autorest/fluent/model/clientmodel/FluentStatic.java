/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.model.clientmodel.Client;

public class FluentStatic {

    private static Client client;

    private static FluentClient fluentClient;

    private FluentStatic() {

    }

    /**
     * @return the client on service client and method groups.
     */
    public static Client getClient() {
        return client;
    }

    public static void setClient(Client client) {
        FluentStatic.client = client;
    }

    /**
     * @return the client on Fluent manager, resource collections and instances (models)
     */
    public static FluentManager getFluentManager() {
        return fluentClient.getManager();
    }

    public static void setFluentClient(FluentClient fluentClient) {
        FluentStatic.fluentClient = fluentClient;
    }
}
