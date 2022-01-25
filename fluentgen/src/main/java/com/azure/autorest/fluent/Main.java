/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent;

import com.azure.autorest.extension.base.jsonrpc.Connection;

import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        Connection connection = new Connection(System.out, System.in);
        connection.dispatch("GetPluginNames", () -> Collections.singletonList("fluentgen"));
        connection.dispatch("Process", (plugin, sessionId) -> new FluentGen(connection, plugin, sessionId).process(), String.class, String.class);
        connection.dispatchNotification("Shutdown", connection::stop);

        // wait for something to do.
        connection.waitForAll();
    }
}
