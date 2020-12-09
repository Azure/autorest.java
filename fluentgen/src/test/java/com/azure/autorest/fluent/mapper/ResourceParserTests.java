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
import com.azure.autorest.model.clientmodel.Client;
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

        FluentResourceModel serviceModel = fluentModels.stream()
                .filter(m -> m.getName().equals("ManagementLockObject"))
                .findFirst().get();

        FluentResourceCollection serviceCollection = fluentCollections.stream()
                .filter(c -> c.getInnerGroupClient().getClassBaseName().startsWith("ManagementLocks"))
                .findFirst().get();

        // test findResourceCreateForCategory
        {
            Map<String, FluentResourceModel> fluentModelMapByName = fluentModels.stream()
                    .collect(Collectors.toMap(m -> m.getInterfaceType().toString(), Function.identity()));

            // RESOURCE_GROUP_AS_PARENT
            Map<FluentResourceModel, ResourceCreate> resourceCreateMap =
                    ResourceParser.findResourceCreateForCategory(serviceCollection, fluentModelMapByName, client.getModels(), Collections.emptySet(), ModelCategory.RESOURCE_GROUP_AS_PARENT);

            Assertions.assertEquals(1, resourceCreateMap.size());
            ResourceCreate resourceCreate = resourceCreateMap.values().iterator().next();

            Assertions.assertEquals("createOrUpdateAtResourceGroupLevel", resourceCreate.getMethodName());

            // SCOPE_AS_PARENT
            resourceCreateMap =
                    ResourceParser.findResourceCreateForCategory(serviceCollection, fluentModelMapByName, client.getModels(), Collections.emptySet(), ModelCategory.SCOPE_AS_PARENT);

            Assertions.assertEquals(1, resourceCreateMap.size());
            resourceCreate = resourceCreateMap.values().iterator().next();

            Assertions.assertEquals("createOrUpdateByScope", resourceCreate.getMethodName());

            // SUBSCRIPTION_AS_PARENT
            resourceCreateMap =
                    ResourceParser.findResourceCreateForCategory(serviceCollection, fluentModelMapByName, client.getModels(), Collections.emptySet(), ModelCategory.SUBSCRIPTION_AS_PARENT);

            Assertions.assertEquals(1, resourceCreateMap.size());
            resourceCreate = resourceCreateMap.values().iterator().next();

            Assertions.assertEquals("createOrUpdateAtSubscriptionLevel", resourceCreate.getMethodName());

            // NESTED_CHILD, not available in locks
            resourceCreateMap =
                    ResourceParser.findResourceCreateForCategory(serviceCollection, fluentModelMapByName, client.getModels(), Collections.emptySet(), ModelCategory.NESTED_CHILD);

            Assertions.assertTrue(resourceCreateMap.isEmpty());
        }

        // test resolveResourceCreate
        {
            List<ResourceCreate> resourceCreates = ResourceParser.resolveResourceCreate(serviceCollection, fluentModels, client.getModels());

            Assertions.assertEquals(1, resourceCreates.size());

            ResourceCreate serviceCreate = resourceCreates.iterator().next();

            Assertions.assertEquals(serviceCollection, serviceCreate.getResourceCollection());
            Assertions.assertEquals(serviceModel, serviceCreate.getResourceModel());
            Assertions.assertEquals(ModelCategory.RESOURCE_GROUP_AS_PARENT, serviceCreate.getResourceModel().getCategory());

            Assertions.assertEquals(1, serviceCollection.getResourceCreates().size());
            Assertions.assertEquals(serviceCreate, serviceCollection.getResourceCreates().iterator().next());

            Assertions.assertEquals(serviceCreate, serviceModel.getResourceCreate());

            List<FluentCollectionMethod> methodReferences = serviceCreate.getMethodReferences();
            Assertions.assertEquals(2, methodReferences.size());
            Assertions.assertTrue(methodReferences.iterator().next().getInnerClientMethod().getName().startsWith("createOrUpdateAtResourceGroupLevel"));
        }
    }

    private static CodeModel loadCodeModel() {
        String searchYamlContent = loadYaml("code-model-fluentnamer-locks.yaml");

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
