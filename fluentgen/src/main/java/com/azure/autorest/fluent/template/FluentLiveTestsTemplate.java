/*
 * // Copyright (c) Microsoft Corporation. All rights reserved.
 * // Licensed under the MIT License.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.mapper.ExampleParser;
import com.azure.autorest.fluent.model.clientmodel.FluentClient;
import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentExample;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentCollectionMethodExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentResourceCreateExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentResourceUpdateExample;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.ResourceCreate;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.update.ResourceUpdate;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ExampleLiveTestStep;
import com.azure.autorest.model.clientmodel.LiveTestCase;
import com.azure.autorest.model.clientmodel.LiveTestStep;
import com.azure.autorest.model.clientmodel.LiveTests;
import com.azure.autorest.model.clientmodel.ProxyMethodExample;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.util.CodeNamer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class FluentLiveTestsTemplate {

    private static final FluentLiveTestsTemplate INSTANCE = new FluentLiveTestsTemplate();

    public void write(LiveTests liveTests, FluentClient fluentClient, JavaFile javaFile) {

        addImports(javaFile);
        FluentExampleTemplate fluentExampleTemplate = FluentExampleTemplate.getInstance();

        // write manager
        javaFile.publicClass(new ArrayList<>(), liveTests.getTestClassName(), new Consumer<JavaClass>() {
            @Override
            public void accept(JavaClass javaClass) {
                for (LiveTestCase testCase : liveTests.getTestCases()) {
                    javaClass.annotation("Test");
                    javaClass.publicMethod(testCase.getName(), new Consumer<JavaBlock>() {
                        @Override
                        public void accept(JavaBlock javaBlock) {

                        }
                    });
                }
            }
        });


    }

    private FluentResourceCollection findResourceCollection(FluentClient fluentClient, String operationGroup) {
        return fluentClient.getResourceCollections().stream().filter(collection -> collection.getInterfaceType().getName().equalsIgnoreCase(CodeNamer.getPlural(operationGroup))).findFirst().get();
    }

    private void addImports(JavaFile javaFile) {
        // @Test
        javaFile.declareImport("org.junit.jupiter.api.test");
    }

}
