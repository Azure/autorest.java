/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.FluentGenAccessor;
import com.azure.autorest.fluent.TestUtils;
import com.azure.autorest.fluent.mapper.ResourceParserAccessor;
import com.azure.autorest.fluent.model.arm.ModelCategory;
import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.action.ResourceActions;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.DefinitionStageCreate;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.DefinitionStageMisc;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.ResourceCreate;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.delete.ResourceDelete;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentConstructorByInner;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentConstructorByName;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentDefineMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethodParameterMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethodType;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.update.ResourceUpdate;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.template.prototype.MethodTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FluentMethodTests {

    private static FluentGenAccessor fluentgenAccessor;

    @BeforeAll
    public static void ensurePlugin() {
        FluentGen fluentgen = new TestUtils.MockFluentGen();
        fluentgenAccessor = new FluentGenAccessor(fluentgen);
    }

    @Test
    public void testConstructorAndMethods() {
        TestUtils.ContentLocks content = TestUtils.initContentLocks(fluentgenAccessor);
        Client client = content.getClient();
        FluentResourceModel lockModel = content.getLockModel();
        FluentResourceCollection lockCollection = content.getLockCollection();

        List<ResourceCreate> resourceCreates = ResourceParserAccessor.resolveResourceCreate(lockCollection, content.getFluentModels(), client.getModels());
        ResourceCreate lockCreate = resourceCreates.iterator().next();
        ResourceUpdate lockUpdate = ResourceParserAccessor.resolveResourceUpdate(lockCollection, lockCreate, client.getModels()).get();

        // constructor by inner
        FluentMethod constructor = new FluentConstructorByInner(lockModel, FluentMethodType.CONSTRUCTOR,
                lockUpdate.getPathParameters(), lockUpdate.getResourceLocalVariables(),
                FluentStatic.getFluentManager().getType(), lockUpdate.getUrlPathSegments());

        Assertions.assertEquals("ManagementLockObjectImpl(ManagementLockObjectInner innerObject, com.azure.resourcemanager.mock.ManagementLockManager serviceManager)", constructor.getImplementationMethodSignature());

        String methodContent = TestUtils.getMethodTemplateContent(constructor.getMethodTemplate());
        Assertions.assertTrue(methodContent.contains("this.innerObject = innerObject;"));
        Assertions.assertTrue(methodContent.contains("this.serviceManager = serviceManager;"));
        Assertions.assertTrue(methodContent.contains("this.lockName = Utils.getValueFromIdByName(innerObject.id(), \"locks\")"));

        // constructor by name
        constructor = new FluentConstructorByName(lockModel, FluentMethodType.CONSTRUCTOR,
                ClassType.String, "lockName",
                FluentStatic.getFluentManager().getType(), lockCreate.getResourceLocalVariables());

        Assertions.assertEquals("ManagementLockObjectImpl(String name, com.azure.resourcemanager.mock.ManagementLockManager serviceManager)", constructor.getImplementationMethodSignature());

        methodContent = TestUtils.getMethodTemplateContent(constructor.getMethodTemplate());
        Assertions.assertTrue(methodContent.contains("new ManagementLockObjectInner();"));
        Assertions.assertTrue(methodContent.contains("this.serviceManager = serviceManager;"));
        Assertions.assertTrue(methodContent.contains("this.lockName = name;"));

        // this constructor without name (when name is constant) is not valid for Locks
        constructor = FluentConstructorByName.constructorMethodWithConstantResourceName(lockModel, FluentMethodType.CONSTRUCTOR,
                FluentStatic.getFluentManager().getType(), lockCreate.getResourceLocalVariables());

        Assertions.assertEquals("ManagementLockObjectImpl(com.azure.resourcemanager.mock.ManagementLockManager serviceManager)", constructor.getImplementationMethodSignature());

        methodContent = TestUtils.getMethodTemplateContent(constructor.getMethodTemplate());
        Assertions.assertTrue(methodContent.contains("new ManagementLockObjectInner();"));
        Assertions.assertTrue(methodContent.contains("this.serviceManager = serviceManager;"));
        Assertions.assertFalse(methodContent.contains("this.lockName = name;"));

        // update()
        FluentMethod updateMethod = lockUpdate.getUpdateMethod();
        Assertions.assertEquals("ManagementLockObject.Update update()", updateMethod.getInterfaceMethodSignature());

        methodContent = TestUtils.getMethodTemplateContent(updateMethod.getMethodTemplate());
        Assertions.assertTrue(methodContent.contains("return this"));

        // apply()
        List<FluentMethod> applyMethods = lockUpdate.getApplyMethods();
        Assertions.assertEquals(2, applyMethods.size());

        FluentMethod applyMethod = applyMethods.iterator().next();
        Assertions.assertEquals("ManagementLockObject apply()", applyMethod.getInterfaceMethodSignature());

        methodContent = TestUtils.getMethodTemplateContent(applyMethod.getMethodTemplate());
        Assertions.assertTrue(methodContent.contains("createOrUpdateAtResourceGroupLevelWithResponse(resourceGroupName, lockName, this.innerModel(), Context.NONE)"));

        // define(..)
        FluentDefineMethod defineMethod = lockCreate.getDefineMethod();

        Assertions.assertEquals("ManagementLockObject.DefinitionStages.Blank defineAtResourceGroupLevel(String name)", defineMethod.getInterfaceMethodSignature());

        methodContent = TestUtils.getMethodTemplateContent(defineMethod.getMethodTemplate());
        Assertions.assertTrue(methodContent.contains("new ManagementLockObjectImpl(name, this.manager())"));
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
        String methodContent = TestUtils.getMethodTemplateContent(deleteByIdMethod);

        Assertions.assertTrue(methodContent.contains("void deleteById(String id)"));
        Assertions.assertTrue(methodContent.contains("this.deleteWithResponse(resourceGroupName, lockName, Context.NONE)"));
    }

    @Test
    public void testOperationByIdForScope() {
        TestUtils.ContentLocks content = TestUtils.initContentLocks(fluentgenAccessor);
        Client client = content.getClient();
        FluentResourceCollection lockCollection = content.getLockCollection();

        Map<String, FluentResourceModel> fluentModelMapByName = content.getFluentModels().stream()
                .collect(Collectors.toMap(m -> m.getInterfaceType().toString(), Function.identity()));

        // SCOPE_AS_PARENT
        List<ResourceCreate> resourceCreates = ResourceParserAccessor.resolveResourceCreate(lockCollection, content.getFluentModels(), client.getModels(), Collections.singletonList(ModelCategory.SCOPE_AS_PARENT));
        ResourceCreate lockCreate = resourceCreates.iterator().next();
        ResourceDelete lockDelete = ResourceParserAccessor.resolveResourceDelete(lockCollection, lockCreate).get();

        List<MethodTemplate> deleteByIdMethods = lockDelete.getDeleteByIdCollectionMethods();
        Assertions.assertEquals(2, deleteByIdMethods.size());

        MethodTemplate deleteByIdMethod = deleteByIdMethods.iterator().next();
        String methodContent = TestUtils.getMethodTemplateContent(deleteByIdMethod);

        Assertions.assertTrue(methodContent.contains("void deleteById(String id)"));
        Assertions.assertTrue(methodContent.contains("this.deleteByScopeWithResponse(scope, lockName, Context.NONE)"));
    }

    @Test
    @Disabled("locks.json does not have action method")
    public void testActionMethod() {
        TestUtils.ContentLocks content = TestUtils.initContentLocks(fluentgenAccessor);
        Client client = content.getClient();
        FluentResourceCollection lockCollection = content.getLockCollection();

        List<ResourceCreate> resourceCreates = ResourceParserAccessor.resolveResourceCreate(lockCollection, content.getFluentModels(), client.getModels());
        ResourceCreate lockCreate = resourceCreates.iterator().next();
        ResourceActions lockActions = ResourceParserAccessor.resourceResourceActions(lockCollection, lockCreate).get();

        List<FluentMethod> refreshMethods = lockActions.getFluentMethods();
        Assertions.assertEquals(2, refreshMethods.size());

        FluentMethod refreshMethod = refreshMethods.iterator().next();
        String methodContent = TestUtils.getMethodTemplateContent(refreshMethod.getMethodTemplate());

        Assertions.assertTrue(methodContent.contains("void refreshAtResourceGroupLevel()"));
        Assertions.assertTrue(methodContent.contains("serviceManager.managementLocks().refreshAtResourceGroupLevel(resourceGroupName, lockName)"));
    }

    @Test
    public void testParameterMethod() {
        // the "lockName" parameter is not valid as FluentMethodParameterMethod for Locks

        TestUtils.ContentLocks content = TestUtils.initContentLocks(fluentgenAccessor);
        FluentResourceModel lockModel = content.getLockModel();
        FluentResourceCollection lockCollection = content.getLockCollection();

        FluentCollectionMethod method = lockCollection.getMethods().stream()
                .filter(m -> m.getMethodName().equals("createOrUpdateAtResourceGroupLevel"))
                .findFirst().get();
        ClientMethodParameter lockParameter = method.getInnerClientMethod().getParameters().stream()
                .filter(p -> p.getName().equals("lockName"))
                .findFirst().get();

        DefinitionStageMisc stage = new DefinitionStageMisc("WithLockName", lockParameter);
        DefinitionStageCreate nextStage = new DefinitionStageCreate();
        stage.setNextStage(nextStage);

        LocalVariable variable = new LocalVariable("lockName", ClassType.String, RequestParameterLocation.Path, lockParameter);

        FluentMethodParameterMethod parameterMethod = new FluentMethodParameterMethod(lockModel, FluentMethodType.CREATE_WITH, stage, lockParameter, variable);
        Assertions.assertEquals("WithCreate withLockName(String lockName)", parameterMethod.getInterfaceMethodSignature());

        parameterMethod = new FluentMethodParameterMethod(lockModel, FluentMethodType.CREATE_WITH, stage, lockParameter, variable, "withLockNameParameter");
        Assertions.assertEquals("WithCreate withLockNameParameter(String lockName)", parameterMethod.getInterfaceMethodSignature());
    }
}
