// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.typespec.http.client.generator.mgmt.model.clientmodel.immutablemethod;

import com.microsoft.typespec.http.client.generator.mgmt.FluentGen;
import com.microsoft.typespec.http.client.generator.mgmt.FluentGenAccessor;
import com.microsoft.typespec.http.client.generator.mgmt.TestUtils;
import com.microsoft.typespec.http.client.generator.mgmt.model.clientmodel.FluentCollectionMethod;
import com.microsoft.typespec.http.client.generator.mgmt.model.clientmodel.FluentResourceCollection;
import com.microsoft.typespec.http.client.generator.mgmt.model.clientmodel.FluentResourceModel;
import com.microsoft.typespec.http.client.generator.mgmt.model.clientmodel.immutablemodel.CollectionMethodTemplate;
import com.microsoft.typespec.http.client.generator.mgmt.model.clientmodel.immutablemodel.CollectionMethodTypeConversionTemplate;
import com.microsoft.typespec.http.client.generator.mgmt.model.clientmodel.immutablemodel.PropertyTemplate;
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
        PropertyTemplate propertyTemplate = new PropertyTemplate(lockModel.getProperty("owners"), lockModel.getProperty("owners").getModelProperty());
        String methodContent = TestUtils.getMethodTemplateContent(propertyTemplate.getMethodTemplate());
        Assertions.assertTrue(methodContent.contains("List<ManagementLockOwner> inner = this.innerModel().owners()"));

        propertyTemplate = new PropertyTemplate(lockModel.getProperty("notes"), lockModel.getProperty("notes").getModelProperty());
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
