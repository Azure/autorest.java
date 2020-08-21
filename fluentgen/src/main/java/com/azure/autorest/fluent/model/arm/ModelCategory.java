/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.arm;

public enum ModelCategory {

    // e.g. /subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Storage/storageAccounts/{accountName}
    RESOURCE_GROUP_AS_PARENT,

    SUBSCRIPTION_AS_PARENT,

    // e.g. /subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Storage/storageAccounts/{accountName}/blobServices/default/containers/{containerName}
    NESTED_CHILD,

    // not an Azure resource, merely a wrapper
    WRAPPER
}
