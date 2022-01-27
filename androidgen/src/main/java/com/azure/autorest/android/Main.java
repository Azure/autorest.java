// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.android;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Connection connection = new Connection(System.out, System.in);
        connection.dispatch("GetPluginNames", () -> Arrays.asList("androidgen"));
        connection.dispatch("Process", (plugin, sessionId) -> new Androidgen(connection, plugin, sessionId).process(),
                String.class, String.class);
        connection.dispatchNotification("Shutdown", connection::stop);
        connection.waitForAll();
    }
}
