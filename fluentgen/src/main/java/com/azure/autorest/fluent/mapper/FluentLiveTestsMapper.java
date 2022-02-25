// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.model.clientmodel.FluentClient;
import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentExampleLiveTestStep;
import com.azure.autorest.fluent.model.clientmodel.FluentLiveTestCase;
import com.azure.autorest.fluent.model.clientmodel.FluentLiveTestStep;
import com.azure.autorest.fluent.model.clientmodel.FluentLiveTests;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentCollectionMethodExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentResourceCreateExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentResourceUpdateExample;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.ResourceCreate;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.update.ResourceUpdate;
import com.azure.autorest.fluent.template.FluentExampleTemplate;
import com.azure.autorest.fluent.util.FluentJavaSettings;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.model.clientmodel.ExampleLiveTestStep;
import com.azure.autorest.model.clientmodel.LiveTestStep;
import com.azure.autorest.model.clientmodel.LiveTests;
import com.azure.autorest.model.clientmodel.ProxyMethodExample;
import com.azure.autorest.util.CodeNamer;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A mapper to map vanilla live tests to fluent live tests.
 */
public class FluentLiveTestsMapper {
    private final PluginLogger logger = new PluginLogger(FluentGen.getPluginInstance(), FluentLiveTestsMapper.class);

    private final FluentExampleTemplate fluentExampleTemplate = FluentExampleTemplate.getInstance();

    private static final FluentLiveTestsMapper INSTANCE = new FluentLiveTestsMapper();

    public static FluentLiveTestsMapper getInstance(){
        return INSTANCE;
    }

    public FluentLiveTests map(LiveTests liveTests, FluentClient fluentClient, CodeModel codeModel, FluentJavaSettings fluentJavaSettings) {

        FluentLiveTests.Builder resultBuilder = FluentLiveTests.newBuilder();

        resultBuilder.className(liveTests.getFilename() + "Tests");

        resultBuilder.addTestCases(liveTests.getTestCases().stream().map(liveTestCase -> {
            FluentLiveTestCase.Builder testCaseBuilder = FluentLiveTestCase.newBuilder().methodName(CodeNamer.toCamelCase(liveTestCase.getName()));
            testCaseBuilder.addSteps(
                liveTestCase.getTestSteps()
                    .stream()
                    // future work: support other step types
                    .filter(testStep -> testStep instanceof ExampleLiveTestStep)
                    .map((Function<LiveTestStep, Optional<FluentLiveTestStep>>) step -> {
                        ExampleLiveTestStep exampleStep = (ExampleLiveTestStep) step;
                        String operationId = exampleStep.getOperationId();
                        String[] oprs = getOperationGroupPair(operationId, codeModel, fluentJavaSettings);
                        String operationGroup = oprs[0];
                        String operation = oprs[1];
                        ProxyMethodExample example = exampleStep.getExample();
                        FluentResourceCollection resourceCollection = findResourceCollection(fluentClient, operationGroup);

                        FluentExampleTemplate.ExampleMethod exampleMethod = null;
                        // find collectionMethod
                        Optional<FluentCollectionMethod> collectionMethodOptional = findCollectionMethod(resourceCollection, operation);
                        if (collectionMethodOptional.isPresent()) {
                            FluentCollectionMethodExample collectionMethodExample = ExampleParser.parseMethodExample(
                                resourceCollection
                                , resourceCollection.getMethodsForTemplate()
                                    .stream()
                                    .filter(m -> m.getMethodName().contains(CodeNamer.toCamelCase(operation))) // getXxWithResponse
                                    .collect(Collectors.toList())
                                , example
                            );
                            exampleMethod = fluentExampleTemplate.generateExampleMethod(collectionMethodExample);
                            setExampleStepFeatures(resultBuilder, testCaseBuilder, collectionMethodExample, exampleMethod);
                        } else {
                            // find resourceCreate
                            Optional<ResourceCreate> createMethod = findResourceCreate(resourceCollection, operation);
                            if (createMethod.isPresent()) {
                                ResourceCreate create = createMethod.get();
                                FluentResourceCreateExample createExample = ExampleParser.parseResourceCreate(resourceCollection, create, example);
                                exampleMethod = fluentExampleTemplate.generateExampleMethod(createExample);
                                setExampleStepFeatures(resultBuilder, testCaseBuilder, createExample, exampleMethod);
                            } else {
                                // find resourceUpdate
                                Optional<ResourceUpdate> updateMethod = resourceCollection.getResourceUpdates().stream().filter(rc -> FluentUtils.exampleIsUpdate(rc.getMethodName()) && rc.getMethodName().equalsIgnoreCase(operation)).findFirst();
                                if (updateMethod.isPresent()) {
                                    ResourceUpdate update = updateMethod.get();
                                    FluentResourceUpdateExample updateExample = ExampleParser.parseResourceUpdate(resourceCollection, update, example);
                                    if (updateExample == null) {
                                        return Optional.empty();
                                    }
                                    exampleMethod = fluentExampleTemplate.generateExampleMethod(updateExample);
                                    setExampleStepFeatures(resultBuilder, testCaseBuilder, updateExample, exampleMethod);
                                }
                            }
                        }
                        if (exampleMethod != null) {
                            resultBuilder.addHelperFeatures(testCaseBuilder.getHelperFeatures());
                            return Optional.of(FluentExampleLiveTestStep.newBuilder().description(step.getDescription()).exampleMethod(exampleMethod).build());
                        } else {
                            // can't find method, ignore the whole test case altogether
                            logger.warn(String.format("Operation : %s not found, ignore this test case.", operationId));
                            return Optional.empty();
                        }
                    })
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList()));
            return testCaseBuilder.build();
        }).collect(Collectors.toList()));

        return resultBuilder.build();
    }

    private String[] getOperationGroupPair(String operationId, CodeModel codeModel, FluentJavaSettings fluentJavaSettings) {
        if (!operationId.contains("_")){
            return new String[]{Utils.getNameForUngroupedOperations(codeModel, fluentJavaSettings), operationId};
        }
        return operationId.split("_");
    }

    private void setExampleStepFeatures(FluentLiveTests.Builder resultBuilder, FluentLiveTestCase.Builder testCaseBuilder, com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentExample fluentExample, FluentExampleTemplate.ExampleMethod exampleMethod) {
        testCaseBuilder.addHelperFeatures(exampleMethod.getHelperFeatures());
        resultBuilder.addImports(exampleMethod.getImports())
            .managerName(fluentExample.getEntryName())
            .managerType(fluentExample.getEntryType());
    }

    private FluentResourceCollection findResourceCollection(FluentClient fluentClient, String operationGroup) {
        return fluentClient.getResourceCollections().stream().filter(collection -> collection.getInterfaceType().getName().equalsIgnoreCase(CodeNamer.getPlural(operationGroup))).findFirst().get();
    }

    private Optional<FluentCollectionMethod> findCollectionMethod(FluentResourceCollection resourceCollection, String operation) {
        return resourceCollection.getMethodsForTemplate().stream().filter(m -> m.getMethodName().contains(CodeNamer.toCamelCase(operation))).findFirst();
    }

    private Optional<ResourceCreate> findResourceCreate(FluentResourceCollection resourceCollection, String operation) {
        return resourceCollection.getResourceCreates().stream().filter(rc ->
            !FluentUtils.exampleIsUpdate(rc.getMethodName()) &&
                rc.getMethodName().equalsIgnoreCase(operation)).findFirst();
    }


}
