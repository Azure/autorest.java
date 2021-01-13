/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel;

import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.FluentGenAccessor;
import com.azure.autorest.fluent.TestUtils;
import com.azure.autorest.fluent.mapper.ResourceParserAccessor;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.ResourceCreate;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.update.ResourceUpdate;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.update.UpdateStage;
import com.azure.autorest.model.clientmodel.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ResourceUpdateTests {

    private static FluentGenAccessor fluentgenAccessor;

    @BeforeAll
    public static void ensurePlugin() {
        FluentGen fluentgen = new TestUtils.MockFluentGen();
        fluentgenAccessor = new FluentGenAccessor(fluentgen);
    }

    @Test
    public void testUpdateStages() {
        TestUtils.ContentLocks content = TestUtils.initContentLocks(fluentgenAccessor);
        Client client = content.getClient();
        FluentResourceCollection lockCollection = content.getLockCollection();

        List<ResourceCreate> resourceCreates = ResourceParserAccessor.resolveResourceCreate(lockCollection, content.getFluentModels(), client.getModels());
        ResourceCreate lockCreate = resourceCreates.iterator().next();
        ResourceUpdate resourceUpdate = ResourceParserAccessor.resolveResourceUpdate(lockCollection, lockCreate, client.getModels()).get();

        List<UpdateStage> updateStages = resourceUpdate.getUpdateStages();
        Assertions.assertEquals(3, updateStages.size());

        updateStages.stream().forEach(stage -> {
            Assertions.assertNotNull(stage.getNextStage());
            Assertions.assertEquals("Update", stage.getNextStage().getName());
            Assertions.assertEquals(1, stage.getMethods().size());
        });
    }
}
