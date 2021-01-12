/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.create;

import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.FluentGenAccessor;
import com.azure.autorest.fluent.TestUtils;
import com.azure.autorest.fluent.mapper.ResourceParserAccessor;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethod;
import com.azure.autorest.model.clientmodel.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ResourceCreateTests {

    private static FluentGenAccessor fluentgenAccessor;

    @BeforeAll
    public static void ensurePlugin() {
        FluentGen fluentgen = new TestUtils.MockFluentGen();
        fluentgenAccessor = new FluentGenAccessor(fluentgen);
    }

    @Test
    public void testDefinitionStages() {
        TestUtils.ContentLocks content = TestUtils.initContentLocks(fluentgenAccessor);
        Client client = content.getClient();
        FluentResourceModel lockModel = content.getLockModel();
        FluentResourceCollection lockCollection = content.getLockCollection();

        List<ResourceCreate> resourceCreates = ResourceParserAccessor.resolveResourceCreate(lockCollection, content.getFluentModels(), client.getModels());
        ResourceCreate lockCreate = resourceCreates.iterator().next();

        List<DefinitionStage> definitionStages = lockCreate.getDefinitionStages();
        Assertions.assertEquals(6, definitionStages.size());

        DefinitionStageBlank blankStage = (DefinitionStageBlank) definitionStages.get(0);
        DefinitionStageParent parentStage = (DefinitionStageParent) definitionStages.get(1);
        DefinitionStage levelStage = definitionStages.get(2);
        DefinitionStageCreate createStage = (DefinitionStageCreate) definitionStages.get(3);
        DefinitionStage optionalNotesStage = definitionStages.get(4);
        DefinitionStage optionalOwnersStage = definitionStages.get(5);

        Assertions.assertEquals("Blank", blankStage.getName());
        Assertions.assertEquals("WithResourceGroup", blankStage.getExtendStages());
        Assertions.assertEquals(parentStage, blankStage.getNextStage());
        Assertions.assertEquals(0, blankStage.getMethods().size());

        Assertions.assertEquals("WithResourceGroup", parentStage.getName());
        Assertions.assertNull(parentStage.getExtendStages());
        Assertions.assertEquals(levelStage, parentStage.getNextStage());
        Assertions.assertEquals(1, parentStage.getMethods().size());

        Assertions.assertEquals("WithLevel", levelStage.getName());
        Assertions.assertNull(levelStage.getExtendStages());
        Assertions.assertEquals(createStage, levelStage.getNextStage());
        Assertions.assertNotNull(levelStage.getModelProperty());
        Assertions.assertEquals(1, levelStage.getMethods().size());
        Assertions.assertEquals("level", levelStage.getModelProperty().getName());

        Assertions.assertEquals("WithCreate", createStage.getName());
        Assertions.assertEquals("DefinitionStages.WithNotes, DefinitionStages.WithOwners", createStage.getExtendStages());
        Assertions.assertNull(createStage.getNextStage());
        Assertions.assertEquals(2, createStage.getMethods().size());

        Assertions.assertEquals("WithNotes", optionalNotesStage.getName());
        Assertions.assertNull(optionalNotesStage.getExtendStages());
        Assertions.assertEquals(createStage, optionalNotesStage.getNextStage());

        Assertions.assertEquals("WithOwners", optionalOwnersStage.getName());
        Assertions.assertNull(optionalOwnersStage.getExtendStages());
        Assertions.assertEquals(createStage, optionalOwnersStage.getNextStage());

        FluentMethod createMethod = createStage.getMethods().iterator().next();
        String methodContent = TestUtils.getMethodTemplateContent(createMethod.getMethodTemplate());

        Assertions.assertTrue(methodContent.contains("ManagementLockObject create()"));
        Assertions.assertTrue(methodContent.contains("serviceManager.serviceClient().getManagementLocks().createOrUpdateAtResourceGroupLevelWithResponse(resourceGroupName, lockName, this.innerModel(), Context.NONE)"));

        FluentMethod parentMethod = parentStage.getMethods().iterator().next();
        methodContent = TestUtils.getMethodTemplateContent(parentMethod.getMethodTemplate());

        Assertions.assertTrue(methodContent.contains("ManagementLockObjectImpl withExistingResourceGroup(String resourceGroupName)"));
        Assertions.assertTrue(methodContent.contains("this.resourceGroupName = resourceGroupName"));

        FluentMethod propertyMethod = levelStage.getMethods().iterator().next();
        methodContent = TestUtils.getMethodTemplateContent(propertyMethod.getMethodTemplate());

        Assertions.assertTrue(methodContent.contains("ManagementLockObjectImpl withLevel(LockLevel level)"));
        Assertions.assertTrue(methodContent.contains("this.innerModel().withLevel(level)"));
    }
}
