// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.model.clientmodel.TestContext;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.template.example.ProtocolTestWriter;

public class ProtocolTestBaseTemplate implements IJavaTemplate<TestContext, JavaFile> {

    private static final ProtocolTestBaseTemplate INSTANCE = new ProtocolTestBaseTemplate();

    protected ProtocolTestBaseTemplate() {
    }

    public static ProtocolTestBaseTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    public void write(TestContext testContext, JavaFile context) {

        ProtocolTestWriter writer = new ProtocolTestWriter(testContext);

        context.declareImport(writer.getImports());

        context.classBlock(JavaVisibility.PackagePrivate, null, String.format("%s extends TestBase", testContext.getTestBaseClassName()), classBlock -> {

            writer.writeClientVariables(classBlock);

            classBlock.annotation("Override");
            classBlock.method(JavaVisibility.Protected, null, "void beforeTest()", writer::writeClientInitialization);
        });
    }
}
