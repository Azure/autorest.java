/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.FluentGenAccessor;
import com.azure.autorest.fluent.TestUtils;
import com.azure.autorest.fluent.model.arm.ModelCategory;
import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.ResourceCreate;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.delete.ResourceDelete;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.get.ResourceRefresh;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.update.ResourceUpdate;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.core.http.HttpMethod;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ResourceParserTests {

    private static FluentGenAccessor fluentgenAccessor;

    @BeforeAll
    public static void ensurePlugin() {
        FluentGen fluentgen = new TestUtils.MockFluentGen();
        fluentgenAccessor = new FluentGenAccessor(fluentgen);
    }

    @Test
    public void testResourceCreate() {
        CodeModel codeModel = loadCodeModel();
        Client client = FluentStatic.getClient();

        List<FluentResourceModel> fluentModels =
                codeModel.getSchemas().getObjects().stream()
                        .map(o -> FluentResourceModelMapper.getInstance().map(o))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());

        List<FluentResourceCollection> fluentCollections =
                codeModel.getOperationGroups().stream()
                        .map(og -> FluentResourceCollectionMapper.getInstance().map(og))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());

        FluentResourceModel lockModel = fluentModels.stream()
                .filter(m -> m.getName().equals("ManagementLockObject"))
                .findFirst().get();

        FluentResourceCollection lockCollection = fluentCollections.stream()
                .filter(c -> c.getInnerGroupClient().getClassBaseName().startsWith("ManagementLocks"))
                .findFirst().get();

        // test findResourceCreateForCategory
        {
            Map<String, FluentResourceModel> fluentModelMapByName = fluentModels.stream()
                    .collect(Collectors.toMap(m -> m.getInterfaceType().toString(), Function.identity()));

            // RESOURCE_GROUP_AS_PARENT
            Map<FluentResourceModel, ResourceCreate> resourceCreateMap =
                    ResourceParser.findResourceCreateForCategory(lockCollection, fluentModelMapByName, client.getModels(), Collections.emptySet(), ModelCategory.RESOURCE_GROUP_AS_PARENT);

            Assertions.assertEquals(1, resourceCreateMap.size());
            ResourceCreate resourceCreate = resourceCreateMap.values().iterator().next();

            Assertions.assertEquals("createOrUpdateAtResourceGroupLevel", resourceCreate.getMethodName());
            Assertions.assertTrue(resourceCreate.getUrlPathSegments().hasSubscription());
            Assertions.assertTrue(resourceCreate.getUrlPathSegments().hasResourceGroup());
            Assertions.assertFalse(resourceCreate.getUrlPathSegments().isNested());

            // SCOPE_AS_PARENT
            resourceCreateMap =
                    ResourceParser.findResourceCreateForCategory(lockCollection, fluentModelMapByName, client.getModels(), Collections.emptySet(), ModelCategory.SCOPE_AS_PARENT);

            Assertions.assertEquals(1, resourceCreateMap.size());
            resourceCreate = resourceCreateMap.values().iterator().next();

            Assertions.assertEquals("createOrUpdateByScope", resourceCreate.getMethodName());
            Assertions.assertTrue(resourceCreate.getUrlPathSegments().hasScope());
            Assertions.assertFalse(resourceCreate.getUrlPathSegments().isNested());

            // SUBSCRIPTION_AS_PARENT
            resourceCreateMap =
                    ResourceParser.findResourceCreateForCategory(lockCollection, fluentModelMapByName, client.getModels(), Collections.emptySet(), ModelCategory.SUBSCRIPTION_AS_PARENT);

            Assertions.assertEquals(1, resourceCreateMap.size());
            resourceCreate = resourceCreateMap.values().iterator().next();

            Assertions.assertEquals("createOrUpdateAtSubscriptionLevel", resourceCreate.getMethodName());

            // NESTED_CHILD, not available in locks
            resourceCreateMap =
                    ResourceParser.findResourceCreateForCategory(lockCollection, fluentModelMapByName, client.getModels(), Collections.emptySet(), ModelCategory.NESTED_CHILD);

            Assertions.assertTrue(resourceCreateMap.isEmpty());
        }

        // test resolveResourceCreate
        List<ResourceCreate> resourceCreates = ResourceParser.resolveResourceCreate(lockCollection, fluentModels, client.getModels());
        Assertions.assertEquals(1, resourceCreates.size());

        ResourceCreate lockCreate = resourceCreates.iterator().next();

        Assertions.assertEquals(lockCollection, lockCreate.getResourceCollection());
        Assertions.assertEquals(lockModel, lockCreate.getResourceModel());
        Assertions.assertEquals(ModelCategory.RESOURCE_GROUP_AS_PARENT, lockCreate.getResourceModel().getCategory());

        Assertions.assertEquals(1, lockCollection.getResourceCreates().size());
        Assertions.assertEquals(lockCreate, lockCollection.getResourceCreates().iterator().next());

        Assertions.assertEquals(lockCreate, lockModel.getResourceCreate());

        List<FluentCollectionMethod> methodReferences = lockCreate.getMethodReferences();
        Assertions.assertEquals(2, methodReferences.size());
        Assertions.assertTrue(methodReferences.iterator().next().getInnerClientMethod().getName().startsWith("createOrUpdateAtResourceGroupLevel"));
        Assertions.assertEquals(lockCreate.getUrlPathSegments().getPath(), methodReferences.iterator().next().getInnerProxyMethod().getUrlPath());
        Assertions.assertEquals(HttpMethod.PUT, methodReferences.iterator().next().getInnerProxyMethod().getHttpMethod());

        // test resolveResourceUpdate
        ResourceUpdate lockUpdate = ResourceParser.resolveResourceUpdate(lockCollection, lockCreate, client.getModels()).get();
        methodReferences = lockUpdate.getMethodReferences();
        Assertions.assertEquals(2, methodReferences.size());
        Assertions.assertTrue(methodReferences.iterator().next().getInnerClientMethod().getName().startsWith("createOrUpdateAtResourceGroupLevel"));
        Assertions.assertEquals(lockCreate.getUrlPathSegments().getPath(), methodReferences.iterator().next().getInnerProxyMethod().getUrlPath());
        Assertions.assertEquals(HttpMethod.PUT, methodReferences.iterator().next().getInnerProxyMethod().getHttpMethod());

        // test resolveResourceRefresh
        ResourceRefresh lockRefresh = ResourceParser.resolveResourceRefresh(lockCollection, lockCreate).get();
        methodReferences = lockRefresh.getMethodReferences();
        Assertions.assertEquals(2, methodReferences.size());
        Assertions.assertTrue(methodReferences.iterator().next().getInnerClientMethod().getName().startsWith("getByResourceGroup"));
        Assertions.assertEquals(lockCreate.getUrlPathSegments().getPath(), methodReferences.iterator().next().getInnerProxyMethod().getUrlPath());
        Assertions.assertEquals(HttpMethod.GET, methodReferences.iterator().next().getInnerProxyMethod().getHttpMethod());

        // test resolveResourceRefresh
        ResourceDelete lockDelete = ResourceParser.resolveResourceDelete(lockCollection, lockCreate).get();
        methodReferences = lockDelete.getMethodReferences();
        Assertions.assertEquals(2, methodReferences.size());
        Assertions.assertTrue(methodReferences.iterator().next().getInnerClientMethod().getName().startsWith("delete"));
        Assertions.assertEquals(lockCreate.getUrlPathSegments().getPath(), methodReferences.iterator().next().getInnerProxyMethod().getUrlPath());
        Assertions.assertEquals(HttpMethod.DELETE, methodReferences.iterator().next().getInnerProxyMethod().getHttpMethod());
    }

    private static CodeModel loadCodeModel() {
        String searchYamlContent = loadYaml("code-model-fluentnamer-locks.yaml");   // the YAML is produced by fluentnamer on locks.json

        CodeModel codeModel = fluentgenAccessor.handleYaml(searchYamlContent);
        Client client = fluentgenAccessor.handleMap(codeModel);

        FluentStatic.setClient(client);

        return codeModel;
    }

    private static String loadYaml(String filename) {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        try (InputStream inputStream = ResourceParserTests.class.getClassLoader().getResourceAsStream(filename);
                Reader in = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            int charsRead;
            while ((charsRead = in.read(buffer, 0, buffer.length)) > 0) {
                out.append(buffer, 0, charsRead);
            }
            return out.toString();
        } catch (IOException e) {
            Assertions.fail(e);
            return null;
        }
    }
}
