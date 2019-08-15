package com.azure.autorest.remodeler;

import com.azure.autorest.extension.base.jsonrpc.Connection;

import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        Connection connection = new Connection(System.out, System.in);
        connection.dispatch("GetPluginNames", () -> Collections.singletonList("remodeler"));
        connection.dispatch("Process", (plugin, sessionId) -> new Remodeler(connection, plugin, sessionId).process(), String.class, String.class);
        connection.dispatchNotification("Shutdown", connection::stop);

        // wait for something to do.
        connection.waitForAll();
    }
}
