/*
 * // Copyright (c) Microsoft Corporation. All rights reserved.
 * // Licensed under the MIT License.
 */

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.FluentGenAccessor;
import com.azure.autorest.fluent.TestUtils;
import com.azure.autorest.fluent.model.clientmodel.FluentClient;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.model.javamodel.FluentJavaPackage;
import com.azure.autorest.fluent.util.FluentConsts;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.util.CodeNamer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class FluentNamerTests {
    private static FluentGenAccessor fluentgenAccessor;

    @BeforeAll
    public static void ensurePlugin() {
        FluentGen fluentgen = new TestUtils.MockFluentGen();
        fluentgenAccessor = new FluentGenAccessor(fluentgen);
    }

    @Test
    @Disabled
    public void testLiveTestGen(){
        CodeModel codeModel = TestUtils.loadCodeModel(fluentgenAccessor, "code-model-fluentnamer-nulloperationgroup.yaml");
        Client client = FluentStatic.getClient();
        FluentJavaPackage javaPackage = fluentgenAccessor.handleTemplate(client);
        FluentClient fluentClient = fluentgenAccessor.handleFluentLite(codeModel, client, javaPackage);
        // exist operation without operation group and also operation group named "ResourceProvider", this case, null operation group is named "ResourceProviderOperation" to avoid confliction
        Assertions.assertTrue(fluentClient.getResourceCollections().stream().anyMatch(collection -> collection.getInterfaceType().getName().equals(CodeNamer.getPlural(FluentConsts.DEFAULT_NAME_FOR_UNGROUPED_OPERATIONS))));
        // exist operation without operation group
        Assertions.assertTrue(fluentClient.getResourceCollections().stream().anyMatch(collection -> collection.getInterfaceType().getName().equals(CodeNamer.getPlural(FluentConsts.DEFAULT_NAME_FOR_UNGROUPED_OPERATIONS + FluentConsts.DEFAULT_SUFFIX_FOR_UNGROUPED_OPERATIONS))));
    }

}
