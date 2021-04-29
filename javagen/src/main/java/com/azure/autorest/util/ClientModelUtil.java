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
     *
     * @param serviceClient the service client.
     * @param asyncClients output, the async clients.
     * @param syncClients output, the sync client.
     */
    public static void getAsyncSyncClients(ServiceClient serviceClient,
                                           List<AsyncSyncClient> asyncClients, List<AsyncSyncClient> syncClients) {
        String packageName = getAsyncSyncClientPackageName(serviceClient);

        if (serviceClient.getProxy() != null) {
            AsyncSyncClient.Builder builder = new AsyncSyncClient.Builder()
                    .packageName(packageName)
                    .serviceClient(serviceClient);

            String asyncClassName =
                    serviceClient.getClientBaseName().endsWith("Client")
                            ? serviceClient.getClientBaseName().replace("Client", "AsyncClient")
                            : serviceClient.getClientBaseName() + "AsyncClient";
            asyncClients.add(builder.className(asyncClassName).build());

            if (JavaSettings.SyncMethodsGeneration.ALL.equals(JavaSettings.getInstance().getSyncMethods())) {
                String syncClassName =
                        serviceClient.getClientBaseName().endsWith("Client")
                                ? serviceClient.getClientBaseName()
                                : serviceClient.getClientBaseName() + "Client";
                syncClients.add(builder.className(syncClassName).build());
            }
        }

        final int count = serviceClient.getMethodGroupClients().size() + asyncClients.size();
        for (MethodGroupClient methodGroupClient : serviceClient.getMethodGroupClients()) {
            AsyncSyncClient.Builder builder = new AsyncSyncClient.Builder()
                    .packageName(packageName)
                    .serviceClient(serviceClient)
                    .methodGroupClient(methodGroupClient);

            if (count == 1) {
                // if it is the only method group, use service client name as base.

                String asyncClassName =
                        serviceClient.getClientBaseName().endsWith("Client")
                                ? serviceClient.getClientBaseName().replace("Client", "AsyncClient")
                                : serviceClient.getClientBaseName() + "AsyncClient";
                asyncClients.add(builder.className(asyncClassName).build());

                if (JavaSettings.SyncMethodsGeneration.ALL.equals(JavaSettings.getInstance().getSyncMethods())) {
                    String syncClassName =
                            serviceClient.getClientBaseName().endsWith("Client")
                                    ? serviceClient.getClientBaseName()
                                    : serviceClient.getClientBaseName() + "Client";
                    syncClients.add(builder.className(syncClassName).build());
                }
            } else {
                String asyncClassName =
                        methodGroupClient.getClassBaseName().endsWith("Client")
                                ? methodGroupClient.getClassBaseName().replace("Client", "AsyncClient")
                                : methodGroupClient.getClassBaseName() + "AsyncClient";
                asyncClients.add(builder.className(asyncClassName).build());

                if (JavaSettings.SyncMethodsGeneration.ALL.equals(JavaSettings.getInstance().getSyncMethods())) {
                    String syncClassName =
                            methodGroupClient.getClassBaseName().endsWith("Client")
                                    ? methodGroupClient.getClassBaseName()
                                    : methodGroupClient.getClassBaseName() + "Client";
                    syncClients.add(builder.className(syncClassName).build());
                }
            }
        }
    }

    public static void getLowLevelClients(ServiceClient serviceClient, List<AsyncSyncClient> lowLevelClients) {
        String packageName = getAsyncSyncClientPackageName(serviceClient);

        if (serviceClient.getProxy() != null) {
            AsyncSyncClient.Builder builder = new AsyncSyncClient.Builder()
                    .packageName(packageName)
                    .serviceClient(serviceClient);

            String lowLevelClassName;
            if (serviceClient.getClientBaseName().endsWith("RestClient")) {
                lowLevelClassName = serviceClient.getClientBaseName();
            } else if (serviceClient.getClientBaseName().endsWith("Client")) {
                lowLevelClassName = serviceClient.getClientBaseName().replaceAll("Client$", "RestClient");
            } else {
                lowLevelClassName = serviceClient.getClientBaseName() + "RestClient";
            }
            lowLevelClients.add(builder.className(lowLevelClassName).build());
        }

        final int count = serviceClient.getMethodGroupClients().size() + lowLevelClients.size();
        for (MethodGroupClient methodGroupClient : serviceClient.getMethodGroupClients()) {
            AsyncSyncClient.Builder builder = new AsyncSyncClient.Builder()
                    .packageName(packageName)
                    .serviceClient(serviceClient)
                    .methodGroupClient(methodGroupClient);

            if (count == 1) {
                // if it is the only method group, use service client name as base.

                String lowLevelClassName =
                        serviceClient.getClientBaseName().endsWith("Client")
                                ? serviceClient.getClientBaseName()
                                : serviceClient.getClientBaseName() + "Client";
                lowLevelClients.add(builder.className(lowLevelClassName).build());
            } else {
                String lowLevelClassName =
                        methodGroupClient.getClassBaseName().endsWith("Client")
                                ? methodGroupClient.getClassBaseName()
                                : methodGroupClient.getClassBaseName() + "Client";
                lowLevelClients.add(builder.className(lowLevelClassName).build());
            }
        }
    }

    public static String getBuilderSuffix() {
        JavaSettings settings = JavaSettings.getInstance();
        StringBuilder builderSuffix = new StringBuilder();
        if (!settings.isFluent()
                && settings.shouldGenerateClientAsImpl()
                && !settings.shouldGenerateSyncAsyncClients()
                && !settings.isLowLevelClient()) {
            builderSuffix.append("Impl");
        }
        builderSuffix.append("Builder");
        return builderSuffix.toString();
    }

    public static String getServiceClientBuilderPackageName(ServiceClient serviceClient) {
        JavaSettings settings = JavaSettings.getInstance();
        String builderPackage = serviceClient.getPackage();
        if ((settings.shouldGenerateSyncAsyncClients() || settings.isLowLevelClient()) && !settings.isFluent()) {
            builderPackage = settings.getPackage();
        } else if (settings.isFluent()) {
            builderPackage = settings.getPackage(settings.getImplementationSubpackage());
        }
        return builderPackage;
    }

    public static String getServiceClientPackageName(String serviceClientClassName) {
        JavaSettings settings = JavaSettings.getInstance();
        String subpackage = settings.shouldGenerateClientAsImpl() ? settings.getImplementationSubpackage() : null;
        if (settings.isFluent()) {
            if (settings.shouldGenerateSyncAsyncClients() || settings.shouldGenerateClientInterfaces()) {
                subpackage = settings.getImplementationSubpackage();
            } else {
                subpackage = settings.getFluentSubpackage();
            }
        }
        if (settings.isCustomType(serviceClientClassName)) {
            subpackage = settings.getCustomTypesSubpackage();
        }
        return settings.getPackage(subpackage);
    }

    public static String getAsyncSyncClientPackageName(ServiceClient serviceClient) {
        JavaSettings settings = JavaSettings.getInstance();
        if (settings.isFluent()) {
            return settings.getPackage(settings.getFluentSubpackage());
        } else {
            return getServiceClientBuilderPackageName(serviceClient);
        }
    }

    public static String getServiceClientInterfacePackageName() {
        JavaSettings settings = JavaSettings.getInstance();
        if (settings.isFluent()) {
            return settings.getPackage(settings.getFluentSubpackage());
        } else {
            return settings.getPackage();
        }
    }
}
