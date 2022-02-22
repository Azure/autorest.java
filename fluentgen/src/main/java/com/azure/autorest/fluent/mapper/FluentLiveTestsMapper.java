/*
 * // Copyright (c) Microsoft Corporation. All rights reserved.
 * // Licensed under the MIT License.
 */

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.model.clientmodel.FluentClient;
import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentExampleLiveTestStep;
import com.azure.autorest.fluent.model.clientmodel.FluentLiveTestCase;
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
import com.azure.autorest.model.clientmodel.LiveTestCase;
import com.azure.autorest.model.clientmodel.LiveTestStep;
import com.azure.autorest.model.clientmodel.LiveTests;
import com.azure.autorest.model.clientmodel.ProxyMethodExample;
import com.azure.autorest.util.CodeNamer;

import java.util.Optional;


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

        parseLiveTests: for (LiveTestCase liveTestCase : liveTests.getTestCases()) {
            FluentLiveTestCase testCase = new FluentLiveTestCase(liveTestCase.getName());
            for (LiveTestStep liveTestStep : liveTestCase.getTestSteps()) {
                if (liveTestStep instanceof ExampleLiveTestStep) {
                    ExampleLiveTestStep exampleStep = (ExampleLiveTestStep) liveTestStep;
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
                        FluentCollectionMethod collectionMethod = collectionMethodOptional.get();
                        FluentCollectionMethodExample collectionMethodExample = ExampleParser.parseMethodExample(resourceCollection, collectionMethod, example);
                        exampleMethod = fluentExampleTemplate.generateExampleMethod(collectionMethodExample);
                        setExampleStepFeatures(resultBuilder, testCase, collectionMethodExample, exampleMethod);
                    } else {
                        // find resourceCreate
                        Optional<ResourceCreate> createMethod = findResourceCreate(resourceCollection, operation);
                        if (createMethod.isPresent()) {
                            ResourceCreate create = createMethod.get();
                            FluentResourceCreateExample createExample = ExampleParser.parseResourceCreate(resourceCollection, create, example);
                            exampleMethod = fluentExampleTemplate.generateExampleMethod(createExample);
                            setExampleStepFeatures(resultBuilder, testCase, createExample, exampleMethod);
                        } else {
                            // find resourceUpdate
                            Optional<ResourceUpdate> updateMethod = resourceCollection.getResourceUpdates().stream().filter(rc -> FluentUtils.exampleIsUpdate(rc.getMethodName()) && rc.getMethodName().equalsIgnoreCase(operation)).findFirst();
                            if (updateMethod.isPresent()) {
                                ResourceUpdate update = updateMethod.get();
                                FluentResourceUpdateExample updateExample = ExampleParser.parseResourceUpdate(resourceCollection, update, example);
                                if (updateExample == null) {
                                    continue parseLiveTests;
                                }
                                exampleMethod = fluentExampleTemplate.generateExampleMethod(updateExample);
                                setExampleStepFeatures(resultBuilder, testCase, updateExample, exampleMethod);
                            }
                        }
                    }
                    if (exampleMethod != null) {
                        testCase.getSteps().add( new FluentExampleLiveTestStep(exampleMethod));
                        resultBuilder.addHelperFeatures(testCase.getHelperFeatures());
                    } else {
                        // can't find method, ignore the whole test case altogether
                        logger.warn(String.format("Operation : %s not found, ignore this test case.", operationId));
                        continue parseLiveTests;
                    }
                } else {
                    continue parseLiveTests;
                }
            }

            resultBuilder.addTestCase(testCase);

        }

        return resultBuilder.build();
    }

    private String[] getOperationGroupPair(String operationId, CodeModel codeModel, FluentJavaSettings fluentJavaSettings) {
        if (!operationId.contains("_")){
            return new String[]{Utils.getNameForUngroupedOperations(codeModel, fluentJavaSettings), operationId};
        }
        return operationId.split("_");
    }

    private void setExampleStepFeatures(FluentLiveTests.Builder resultBuilder, FluentLiveTestCase testCase, com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentExample fluentExample, FluentExampleTemplate.ExampleMethod exampleMethod) {
        testCase.getHelperFeatures().addAll(exampleMethod.getHelperFeatures());
        resultBuilder.addImports(exampleMethod.getImports())
            .managerName(fluentExample.getEntryName())
            .managerType(fluentExample.getEntryType());
    }

    private FluentResourceCollection findResourceCollection(FluentClient fluentClient, String operationGroup) {
        return fluentClient.getResourceCollections().stream().filter(collection -> collection.getInterfaceType().getName().equalsIgnoreCase(CodeNamer.getPlural(operationGroup))).findFirst().get();
    }

    private Optional<FluentCollectionMethod> findCollectionMethod(FluentResourceCollection resourceCollection, String operation) {
        return resourceCollection.getMethodsForTemplate().stream().filter(m -> operation.equalsIgnoreCase(m.getMethodName())).findFirst();
    }

    private Optional<ResourceCreate> findResourceCreate(FluentResourceCollection resourceCollection, String operation) {
        return resourceCollection.getResourceCreates().stream().filter(rc ->
            !FluentUtils.exampleIsUpdate(rc.getMethodName()) &&
                rc.getMethodName().equalsIgnoreCase(operation)).findFirst();
    }


}
