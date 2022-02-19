/*
 * // Copyright (c) Microsoft Corporation. All rights reserved.
 * // Licensed under the MIT License.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.clientmodel.*;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.util.CodeNamer;
import com.google.common.collect.Lists;

public class FluentLiveTestsTemplate {

    private static final FluentLiveTestsTemplate INSTANCE = new FluentLiveTestsTemplate();

    public static FluentLiveTestsTemplate getInstance(){
        return INSTANCE;
    }

    public void write(FluentLiveTests liveTests, JavaFile javaFile) {
        // write class
        addImports(liveTests, javaFile);
        javaFile.publicClass(Lists.newArrayList(), liveTests.getClassName(), classBlock->{
            for (FluentLiveTestCase testCase : liveTests.getTestCases()) {
                // write manager field
                classBlock.privateMemberVariable(liveTests.getEntryType().getName(), liveTests.getEntryName());
                // write @BeforeEach
                classBlock.annotation("BeforeEach");
                classBlock.publicMethod("void init()", methodBlock -> {
                    methodBlock.line(
                            String.format("%s = %s.authenticate(" +
                                    "new DefaultAzureCredentialBuilder().build(), new AzureProfile(AzureEnvironment.AZURE)" +
                                    ");", liveTests.getEntryName(), liveTests.getEntryType().getName())
                    );
                });
                // write method signature
                classBlock.annotation("Test");
                String methodSignature = String.format("%s %s()", "void", testCase.getMethodName());
                if (testCase.getHelperFeatures().contains(FluentExampleTemplate.HelperFeature.ThrowsIOException)) {
                    methodSignature += " throws IOException";
                }
                classBlock.publicMethod(methodSignature, methodBlock -> {
                    for (FluentLiveTestStep step : testCase.getSteps()) {
                        if (step instanceof FluentExampleLiveTestStep) {
                            FluentExampleLiveTestStep exampleStep = (FluentExampleLiveTestStep) step;
                            methodBlock.line(exampleStep.getExampleMethod().getMethodContent());
                        }
                        methodBlock.line();
                    }
                });
            }
            if (liveTests.getHelperFeatures().contains(FluentExampleTemplate.HelperFeature.MapOfMethod)) {
                FluentExampleTemplate.getInstance().writeMapOfMethod(classBlock);
            }
        });
    }

    private void addImports(FluentLiveTests liveTests, JavaFile javaFile) {
        javaFile.declareImport(liveTests.getImports());
        javaFile.declareImport(liveTests.getEntryType().getFullName());
        javaFile.declareImport("org.junit.jupiter.api.Test", "org.junit.jupiter.api.BeforeEach");
        javaFile.declareImport("com.azure.identity.DefaultAzureCredentialBuilder", "com.azure.core.management.profile.AzureProfile", "com.azure.core.management.AzureEnvironment");
    }

}
