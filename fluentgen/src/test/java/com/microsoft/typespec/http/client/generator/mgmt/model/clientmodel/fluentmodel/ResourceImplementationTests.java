// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.typespec.http.client.generator.mgmt.model.clientmodel.fluentmodel;

import com.microsoft.typespec.http.client.generator.mgmt.FluentGen;
import com.microsoft.typespec.http.client.generator.mgmt.FluentGenAccessor;
import com.microsoft.typespec.http.client.generator.mgmt.TestUtils;
import com.microsoft.typespec.http.client.generator.mgmt.mapper.ResourceParserAccessor;
import com.microsoft.typespec.http.client.generator.mgmt.model.clientmodel.FluentResourceCollection;
import com.microsoft.typespec.http.client.generator.mgmt.model.clientmodel.FluentResourceModel;
import com.microsoft.typespec.http.client.generator.mgmt.model.clientmodel.fluentmodel.create.ResourceCreate;
import com.microsoft.typespec.http.client.generator.mgmt.model.clientmodel.fluentmodel.method.FluentApplyMethod;
import com.microsoft.typespec.http.client.generator.mgmt.model.clientmodel.fluentmodel.method.FluentConstructorByName;
import com.microsoft.typespec.http.client.generator.mgmt.model.clientmodel.fluentmodel.method.FluentCreateMethod;
import com.microsoft.typespec.http.client.generator.mgmt.model.clientmodel.fluentmodel.method.FluentParentMethod;
import com.microsoft.typespec.http.client.generator.mgmt.model.clientmodel.fluentmodel.method.FluentUpdateMethod;
import com.microsoft.typespec.http.client.generator.mgmt.model.clientmodel.immutablemodel.ImmutableMethod;
import com.microsoft.typespec.http.client.generator.core.model.clientmodel.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ResourceImplementationTests {

    private static FluentGenAccessor fluentgenAccessor;

    @BeforeAll
    public static void ensurePlugin() {
        FluentGen fluentgen = new TestUtils.MockFluentGen();
        fluentgenAccessor = new FluentGenAccessor(fluentgen);
    }

    @Test
    public void testImplementation() {
        TestUtils.ContentLocks content = TestUtils.initContentLocks(fluentgenAccessor);
        Client client = content.getClient();
        FluentResourceModel lockModel = content.getLockModel();
        FluentResourceCollection lockCollection = content.getLockCollection();

        List<ResourceCreate> resourceCreates = ResourceParserAccessor.resolveResourceCreate(lockCollection, content.getFluentModels(), client.getModels());
        ResourceCreate lockCreate = resourceCreates.iterator().next();
        ResourceParserAccessor.resolveResourceUpdate(lockCollection, lockCreate, client.getModels());

        ResourceImplementation implementation = new ResourceImplementation(lockModel);

        List<ImmutableMethod> methods = implementation.getMethods();
        Assertions.assertEquals(11, methods.size());
        Assertions.assertTrue(methods.stream().anyMatch(m -> m instanceof FluentParentMethod));
        Assertions.assertTrue(methods.stream().anyMatch(m -> m instanceof FluentCreateMethod));
        Assertions.assertTrue(methods.stream().anyMatch(m -> m instanceof FluentConstructorByName));
        Assertions.assertTrue(methods.stream().anyMatch(m -> m instanceof FluentUpdateMethod));
        Assertions.assertTrue(methods.stream().anyMatch(m -> m instanceof FluentApplyMethod));
    }
}
