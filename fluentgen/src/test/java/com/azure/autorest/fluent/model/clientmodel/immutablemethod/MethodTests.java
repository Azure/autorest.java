/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.immutablemethod;

import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.FluentGenAccessor;
import com.azure.autorest.fluent.TestUtils;
import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.immutablemodel.CollectionMethodTemplate;
import com.azure.autorest.fluent.model.clientmodel.immutablemodel.CollectionMethodTypeConversionTemplate;
import com.azure.autorest.fluent.model.clientmodel.immutablemodel.PropertyTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MethodTests {
    private static FluentGenAccessor fluentgenAccessor;

    @BeforeAll
    public static void ensurePlugin() {
        FluentGen fluentgen = new TestUtils.MockFluentGen();
        fluentgenAccessor = new FluentGenAccessor(fluentgen);
    }

    @Test
    public void testMethods() {
        TestUtils.ContentLocks content = TestUtils.initContentLocks(fluentgenAccessor);
        FluentResourceModel lockModel = content.getLockModel();
        FluentResourceCollection lockCollection = content.getLockCollection();

        // model property, without type conversion
        PropertyTemplate propertyTemplate = new PropertyTemplate(lockModel.getProperty("owners"), lockModel.getProperty("owners").getInnerProperty());
        String methodContent = TestUtils.getMethodTemplateContent(propertyTemplate.getMethodTemplate());
        Assertions.assertTrue(methodContent.contains("List<ManagementLockOwner> inner = this.innerModel().owners()"));

        propertyTemplate = new PropertyTemplate(lockModel.getProperty("notes"), lockModel.getProperty("notes").getInnerProperty());
        methodContent = TestUtils.getMethodTemplateContent(propertyTemplate.getMethodTemplate());
        Assertions.assertTrue(methodContent.contains("return this.innerModel().notes()"));

        // collection method, with type conversion
        FluentCollectionMethod method = lockCollection.getMethods().stream().filter(m -> m.getMethodName().equals("createOrUpdateAtResourceGroupLevel")).findFirst().get();
        CollectionMethodTypeConversionTemplate collectionMethodTypeConversionTemplate = new CollectionMethodTypeConversionTemplate(method, method.getInnerClientMethod().getReturnValue().getType());

        methodContent = TestUtils.getMethodTemplateContent(collectionMethodTypeConversionTemplate.getMethodTemplate());
        Assertions.assertTrue(methodContent.contains("ManagementLockObjectInner inner = this.serviceClient().createOrUpdateAtResourceGroupLevel(resourceGroupName, lockName, parameters)"));
        Assertions.assertTrue(methodContent.contains("return new ManagementLockObjectImpl(inner, this.manager())"));

        // collection method, without type conversion
        method = content.getFluentClient().getResourceCollections().stream().filter(c -> c.getInnerGroupClient().getClassBaseName().equals("AuthorizationOperations")).findFirst().get()
                .getMethods().stream().filter(m -> m.getMethodName().equals("list")).findFirst().get();
        CollectionMethodTemplate collectionMethodTemplate = new CollectionMethodTemplate(method, method.getInnerClientMethod().getReturnValue().getType());

        methodContent = TestUtils.getMethodTemplateContent(collectionMethodTemplate.getMethodTemplate());
        Assertions.assertTrue(methodContent.contains("return this.serviceClient().list()"));
    }
}
