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
import java.util.List;
import java.util.Objects;
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
        String searchYamlContent = loadYaml("code-model-fluentgen-search.yaml");

        CodeModel codeModel = fluentgenAccessor.handleYaml(searchYamlContent);
        Client client = fluentgenAccessor.handleMap(codeModel);

        FluentStatic.setClient(client);

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
                .filter(m -> m.getName().equals("SearchService"))
                .findFirst().get();

        FluentResourceCollection serviceCollection = fluentCollections.stream()
                .filter(c -> c.getInnerGroupClient().getClassBaseName().startsWith("Services"))
                .findFirst().get();

        List<ResourceCreate> resourceCreates = ResourceParser.resolveResourceCreate(serviceCollection, fluentModels, client.getModels());

        Assertions.assertEquals(1, resourceCreates.size());

        ResourceCreate serviceCreate = resourceCreates.iterator().next();

        Assertions.assertEquals(serviceCollection, serviceCreate.getResourceCollection());
        Assertions.assertEquals(serviceModel, serviceCreate.getResourceModel());
        Assertions.assertEquals(ModelCategory.RESOURCE_GROUP_AS_PARENT, serviceCreate.getResourceModel().getCategory());

        Assertions.assertEquals(1, serviceCollection.getResourceCreates().size());
        Assertions.assertEquals(serviceCreate, serviceCollection.getResourceCreates().iterator().next());

        Assertions.assertEquals(serviceCreate, serviceModel.getResourceCreate());

        int i = 1;
    }

    public static String loadYaml(String filename) {
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
