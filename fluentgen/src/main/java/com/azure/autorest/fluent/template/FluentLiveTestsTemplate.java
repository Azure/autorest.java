/*
 * // Copyright (c) Microsoft Corporation. All rights reserved.
 * // Licensed under the MIT License.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.clientmodel.FluentClient;
import com.azure.autorest.fluent.model.clientmodel.FluentLiveTests;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.util.CodeNamer;

public class FluentLiveTestsTemplate {

    private static final FluentLiveTestsTemplate INSTANCE = new FluentLiveTestsTemplate();

    public static FluentLiveTestsTemplate getInstance(){
        return INSTANCE;
    }

    public void write(FluentLiveTests liveTests, JavaFile javaFile) {
        //TODO
    }

    private FluentResourceCollection findResourceCollection(FluentClient fluentClient, String operationGroup) {
        return fluentClient.getResourceCollections().stream().filter(collection -> collection.getInterfaceType().getName().equalsIgnoreCase(CodeNamer.getPlural(operationGroup))).findFirst().get();
    }

    private void addImports(JavaFile javaFile) {
        // @Test
        javaFile.declareImport("org.junit.jupiter.api.test");
    }

}
