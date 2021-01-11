/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.method;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.FluentGenAccessor;
import com.azure.autorest.fluent.TestUtils;
import com.azure.autorest.fluent.mapper.ResourceParserAccessor;
import com.azure.autorest.fluent.model.clientmodel.FluentClient;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.ResourceCreate;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.delete.ResourceDelete;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.update.ResourceUpdate;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.template.prototype.MethodTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FluentMethodTests {

    private static FluentGenAccessor fluentgenAccessor;

    @BeforeAll
    public static void ensurePlugin() {
        FluentGen fluentgen = new TestUtils.MockFluentGen();
        fluentgenAccessor = new FluentGenAccessor(fluentgen);
    }

    @Test
    public void testUpdateConstructorAndApply() {
        TestUtils.ContentLocks content = TestUtils.initContentLocks(fluentgenAccessor);
        Client client = content.getClient();
        FluentResourceModel lockModel = content.getLockModel();
        FluentResourceCollection lockCollection = content.getLockCollection();

        List<ResourceCreate> resourceCreates = ResourceParserAccessor.resolveResourceCreate(lockCollection, content.getFluentModels(), client.getModels());
        ResourceCreate lockCreate = resourceCreates.iterator().next();
        ResourceUpdate lockUpdate = ResourceParserAccessor.resolveResourceUpdate(lockCollection, lockCreate, client.getModels()).get();

        FluentMethod constructor = new FluentConstructorByInner(lockModel, FluentMethodType.CONSTRUCTOR,
                lockUpdate.getPathParameters(), lockUpdate.getResourceLocalVariables(),
                FluentStatic.getFluentManager().getType(), lockUpdate.getUrlPathSegments());

        Assertions.assertEquals("ManagementLockObjectImpl(ManagementLockObjectInner innerObject, ManagementLockManager serviceManager)", constructor.getImplementationMethodSignature());

        JavaFile javaFile = new JavaFile("dummy");
        constructor.getMethodTemplate().writeMethod(new JavaClass(javaFile.getContents()));
        Assertions.assertTrue(javaFile.getContents().toString().contains("this.innerObject = innerObject;"));
        Assertions.assertTrue(javaFile.getContents().toString().contains("this.serviceManager = serviceManager;"));
        Assertions.assertTrue(javaFile.getContents().toString().contains("this.lockName = Utils.getValueFromIdByName(innerObject.id(), \"locks\")"));

        List<FluentMethod> applyMethods = lockUpdate.getApplyMethods();
        Assertions.assertEquals(2, applyMethods.size());

        FluentMethod applyMethod = applyMethods.iterator().next();
        Assertions.assertEquals("ManagementLockObject apply()", applyMethod.getInterfaceMethodSignature());

        javaFile = new JavaFile("dummy");
        applyMethod.getMethodTemplate().writeMethod(new JavaClass(javaFile.getContents()));
        Assertions.assertTrue(javaFile.getContents().toString().contains("createOrUpdateAtResourceGroupLevelWithResponse(resourceGroupName, lockName, this.innerModel(), Context.NONE)"));
    }

    @Test
    public void testOperationById() {
        TestUtils.ContentLocks content = TestUtils.initContentLocks(fluentgenAccessor);
        Client client = content.getClient();
        FluentResourceCollection lockCollection = content.getLockCollection();

        List<ResourceCreate> resourceCreates = ResourceParserAccessor.resolveResourceCreate(lockCollection, content.getFluentModels(), client.getModels());
        ResourceCreate lockCreate = resourceCreates.iterator().next();
        ResourceDelete lockDelete = ResourceParserAccessor.resolveResourceDelete(lockCollection, lockCreate).get();

        List<MethodTemplate> deleteByIdMethods = lockDelete.getDeleteByIdCollectionMethods();
        Assertions.assertEquals(2, deleteByIdMethods.size());

        MethodTemplate deleteByIdMethod = deleteByIdMethods.iterator().next();
        JavaFile javaFile = new JavaFile("dummy");
        deleteByIdMethod.writeMethod(new JavaClass(javaFile.getContents()));

        Assertions.assertTrue(javaFile.getContents().toString().contains("void deleteById(String id)"));
        Assertions.assertTrue(javaFile.getContents().toString().contains("this.deleteWithResponse(resourceGroupName, lockName, Context.NONE)"));
    }
}
