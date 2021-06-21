/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.fluent.mapper.ExampleParser;
import com.azure.autorest.fluent.model.clientmodel.FluentClient;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.model.javamodel.FluentJavaPackage;
import com.azure.autorest.model.clientmodel.Client;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class FluentGenTests {

    private static FluentGenAccessor fluentgenAccessor;

    @BeforeAll
    public static void ensurePlugin() {
        FluentGen fluentgen = new TestUtils.MockFluentGen();
        fluentgenAccessor = new FluentGenAccessor(fluentgen);
    }

    @Test
    @Disabled("no validation")
    public void testProcess() {
        CodeModel codeModel = TestUtils.loadCodeModel(fluentgenAccessor, "code-model-fluentnamer-locks.yaml");
        Client client = FluentStatic.getClient();
        FluentJavaPackage javaPackage = fluentgenAccessor.handleTemplate(client);
        FluentClient fluentClient = fluentgenAccessor.handleFluentLite(codeModel, client, javaPackage);
        fluentClient.getResourceCollections().stream().forEach(rc -> rc.getMethods().stream().forEach(m -> ExampleParser.parseMethod(rc, m)));
    }
}
