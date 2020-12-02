/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.arm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UrlPathSegmentsTests {

    @Test
    public void testUrlPathSegments() {
        UrlPathSegments segments = new UrlPathSegments("/subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Storage/storageAccounts/{accountName}");

        Assertions.assertTrue(segments.hasSubscription());
        Assertions.assertTrue(segments.hasResourceGroup());
        Assertions.assertFalse(segments.hasScope());
        Assertions.assertFalse(segments.isNested());

        segments = new UrlPathSegments("/subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Storage/storageAccounts/{accountName}/blobServices/default/containers/{containerName}");

        Assertions.assertTrue(segments.hasSubscription());
        Assertions.assertTrue(segments.hasResourceGroup());
        Assertions.assertFalse(segments.hasScope());
        Assertions.assertTrue(segments.isNested());

        segments = new UrlPathSegments("/subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}");

        Assertions.assertTrue(segments.hasSubscription());
        Assertions.assertFalse(segments.hasResourceGroup());
        Assertions.assertFalse(segments.hasScope());
        Assertions.assertFalse(segments.isNested());

        segments = new UrlPathSegments("/{scope}/providers/Microsoft.Authorization/roleAssignments/{roleAssignmentName}");

        Assertions.assertFalse(segments.hasSubscription());
        Assertions.assertFalse(segments.hasResourceGroup());
        Assertions.assertTrue(segments.hasScope());
        Assertions.assertFalse(segments.isNested());
    }
}
