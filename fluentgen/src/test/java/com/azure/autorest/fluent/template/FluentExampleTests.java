/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.FluentGenAccessor;
import com.azure.autorest.fluent.TestUtils;
import com.azure.autorest.fluent.model.clientmodel.FluentClient;
import com.azure.autorest.fluent.model.clientmodel.FluentExample;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.model.javamodel.FluentJavaPackage;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.model.javamodel.JavaFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FluentExampleTests {

    private static FluentGenAccessor fluentgenAccessor;

    @BeforeAll
    public static void ensurePlugin() {
        FluentGen fluentgen = new TestUtils.MockFluentGen();
        fluentgenAccessor = new FluentGenAccessor(fluentgen);
    }

    @Test
    @Disabled
    public void testLocks() {
        CodeModel codeModel = TestUtils.loadCodeModel(fluentgenAccessor, "code-model-fluentnamer-locks.yaml");
        Client client = FluentStatic.getClient();
        FluentJavaPackage javaPackage = fluentgenAccessor.handleTemplate(client);
        FluentClient fluentClient = fluentgenAccessor.handleFluentLite(codeModel, client, javaPackage);

        List<FluentExample> examples = fluentClient.getExamples();
        Assertions.assertTrue(!examples.isEmpty());
        // TODO verification
    }

    @Test
    @Disabled
    public void testStorage() {
        CodeModel codeModel = TestUtils.loadCodeModel(fluentgenAccessor, "code-model-fluentnamer-storage.yaml");
        Client client = FluentStatic.getClient();
        FluentJavaPackage javaPackage = fluentgenAccessor.handleTemplate(client);
        FluentClient fluentClient = fluentgenAccessor.handleFluentLite(codeModel, client, javaPackage);

        List<FluentExample> examples = fluentClient.getExamples();
        Assertions.assertTrue(!examples.isEmpty());
        // TODO verification
        for (FluentExample example : examples) {
            JavaFile javaFile = new JavaFile("dummy");
            FluentExampleTemplate.getInstance().write(example, javaFile);
            String content = javaFile.getContents().toString();
        }
    }
}
