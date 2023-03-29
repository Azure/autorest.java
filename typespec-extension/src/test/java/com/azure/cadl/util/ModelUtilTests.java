// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.cadl.util;

import com.azure.autorest.CadlPlugin;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ImplementationDetails;
import com.azure.cadl.model.EmitterOptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

public class ModelUtilTests {

    // sadly ModelUtil.isGeneratingModel queries JavaSettings
    @BeforeAll
    public static void ensurePlugin() {
        CadlPlugin plugin = new CadlPlugin(new EmitterOptions().setNamespace("com.azure.client"), false);
    }

    @Test
    public void testIsGeneratingModel() {
        ImplementationDetails.Builder implementationDetailsBuilder = new ImplementationDetails.Builder()
                .usages(new HashSet<>(Arrays.asList(ImplementationDetails.Usage.EXCEPTION)));
        ClientModel.Builder modelBuilder = new ClientModel.Builder()
                .name("Model")
                .packageName("com.azure.client")
                .type(new ClassType.Builder().name("Model").packageName("com.azure.client").build());

        // model which is only used in exception
        Assertions.assertFalse(ModelUtil.isGeneratingModel(modelBuilder.implementationDetails(implementationDetailsBuilder.build()).build()));

        // model which is used in input and output
        implementationDetailsBuilder.usages(new HashSet<>(Arrays.asList(ImplementationDetails.Usage.INPUT, ImplementationDetails.Usage.OUTPUT)));
        Assertions.assertFalse(ModelUtil.isGeneratingModel(modelBuilder.implementationDetails(implementationDetailsBuilder.build()).build()));

        // in convenience method

        // model which is only used in exception
        implementationDetailsBuilder.usages(new HashSet<>(Arrays.asList(ImplementationDetails.Usage.EXCEPTION, ImplementationDetails.Usage.CONVENIENCE_API)));
        Assertions.assertFalse(ModelUtil.isGeneratingModel(modelBuilder.implementationDetails(implementationDetailsBuilder.build()).build()));

        // model which is used in input
        implementationDetailsBuilder.usages(new HashSet<>(Arrays.asList(ImplementationDetails.Usage.INPUT, ImplementationDetails.Usage.CONVENIENCE_API)));
        Assertions.assertTrue(ModelUtil.isGeneratingModel(modelBuilder.implementationDetails(implementationDetailsBuilder.build()).build()));

        // model which is used in output and exception
        implementationDetailsBuilder.usages(new HashSet<>(Arrays.asList(ImplementationDetails.Usage.OUTPUT, ImplementationDetails.Usage.EXCEPTION, ImplementationDetails.Usage.CONVENIENCE_API)));
        Assertions.assertTrue(ModelUtil.isGeneratingModel(modelBuilder.implementationDetails(implementationDetailsBuilder.build()).build()));
    }
}
