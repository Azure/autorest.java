/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.util;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.AsyncSyncClient;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.ServiceClient;

import java.util.List;

/**
 * Utilities for client model.
 */
public class ClientModelUtil {

    /**
     * Prepare async/sync clients for service client.
     * @param serviceClient the service client.
     * @param asyncClients output, the async clients.
     * @param syncClients output, the sync client.
     */
    public static void getAsyncSyncClients(ServiceClient serviceClient,
                                           List<AsyncSyncClient> asyncClients, List<AsyncSyncClient> syncClients) {
        if (serviceClient.getProxy() != null) {
            AsyncSyncClient.Builder builder = new AsyncSyncClient.Builder()
                    .serviceClient(serviceClient);

            String asyncClassName =
                    serviceClient.getClientBaseName().endsWith("Client") ? serviceClient
                            .getClientBaseName().replace("Client", "AsyncClient")
                            : serviceClient.getClientBaseName() + "AsyncClient";
            asyncClients.add(builder.name(asyncClassName).build());

            if (JavaSettings.SyncMethodsGeneration.ALL.equals(JavaSettings.getInstance().getSyncMethods())) {
                String syncClassName =
                        serviceClient.getClientBaseName().endsWith("Client") ? serviceClient
                                .getClientBaseName() : serviceClient.getClientBaseName() + "Client";
                syncClients.add(builder.name(syncClassName).build());
            }
        }

        final int count = serviceClient.getMethodGroupClients().size() + asyncClients.size();
        for (MethodGroupClient methodGroupClient : serviceClient.getMethodGroupClients()) {
            AsyncSyncClient.Builder builder = new AsyncSyncClient.Builder()
                    .serviceClient(serviceClient)
                    .methodGroupClient(methodGroupClient);

            if (count == 1) {
                // if it is the only method group, use service client name as base.

                String asyncClassName =
                        serviceClient.getClientBaseName().endsWith("Client") ? serviceClient
                                .getClientBaseName().replace("Client", "AsyncClient")
                                : serviceClient.getClientBaseName() + "AsyncClient";
                asyncClients.add(builder.name(asyncClassName).build());

                if (JavaSettings.SyncMethodsGeneration.ALL.equals(JavaSettings.getInstance().getSyncMethods())) {
                    String syncClassName =
                            serviceClient.getClientBaseName().endsWith("Client") ? serviceClient
                                    .getClientBaseName() : serviceClient.getClientBaseName() + "Client";
                    syncClients.add(builder.name(syncClassName).build());
                }
            } else {
                String asyncClassName =
                        methodGroupClient.getClassBaseName().endsWith("Client")
                                ? methodGroupClient.getClassBaseName().replace("Client", "AsyncClient")
                                : methodGroupClient.getClassBaseName() + "AsyncClient";
                asyncClients.add(builder.name(asyncClassName).build());

                if (JavaSettings.SyncMethodsGeneration.ALL.equals(JavaSettings.getInstance().getSyncMethods())) {
                    String syncClassName =
                            methodGroupClient.getClassBaseName().endsWith("Client")
                                    ? methodGroupClient.getClassBaseName()
                                    : methodGroupClient.getClassBaseName() + "Client";
                    syncClients.add(builder.name(syncClassName).build());
                }
            }
        }
    }
}
