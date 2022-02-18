// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.ArraySchema;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.DictionarySchema;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.Response;
import com.azure.autorest.extension.base.model.codemodel.Value;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.model.FluentType;
import com.azure.autorest.fluent.model.clientmodel.FluentClient;
import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentExample;
import com.azure.autorest.fluent.model.clientmodel.FluentExampleLiveTestStep;
import com.azure.autorest.fluent.model.clientmodel.FluentLiveTestCase;
import com.azure.autorest.fluent.model.clientmodel.FluentLiveTestStep;
import com.azure.autorest.fluent.model.clientmodel.FluentLiveTests;
import com.azure.autorest.fluent.model.clientmodel.FluentManager;
import com.azure.autorest.fluent.model.clientmodel.FluentManagerProperty;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentCollectionMethodExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentResourceCreateExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentResourceUpdateExample;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.ResourceCreate;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.update.ResourceUpdate;
import com.azure.autorest.fluent.template.FluentExampleTemplate;
import com.azure.autorest.fluent.util.FluentJavaSettings;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.model.clientmodel.ExampleLiveTestStep;
import com.azure.autorest.model.clientmodel.LiveTestCase;
import com.azure.autorest.model.clientmodel.LiveTestStep;
import com.azure.autorest.model.clientmodel.LiveTests;
import com.azure.autorest.model.clientmodel.ModuleInfo;
import com.azure.autorest.model.clientmodel.ProxyMethodExample;
import com.azure.autorest.util.CodeNamer;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FluentMapper {

    private static final Logger logger = new PluginLogger(FluentGen.getPluginInstance(), FluentMapper.class);

    private final FluentJavaSettings fluentJavaSettings;
    private final FluentExampleTemplate fluentExampleTemplate = FluentExampleTemplate.getInstance();

    public FluentMapper(FluentJavaSettings fluentJavaSettings) {
        this.fluentJavaSettings = fluentJavaSettings;
    }

    public void preModelMap(CodeModel codeModel) {
        processInnerModel(codeModel);
        FluentModelMapper.getInstance().addRemovedModels(fluentJavaSettings.getJavaNamesForRemoveModel());
    }

    public FluentClient map(CodeModel codeModel, Client client) {
        FluentClient fluentClient = basicMap(codeModel, client);

        // parse resource collections to identify create/update/refresh flow on resource instance
        fluentClient.getResourceCollections()
                .forEach(c -> ResourceParser.parseResourcesCategory(c, fluentClient.getResourceModels(), FluentStatic.getClient().getModels()));
        ResourceParser.processAdditionalMethods(fluentClient);

        // samples
        if (fluentJavaSettings.isGenerateSamples()) {
            ExampleParser exampleParser = new ExampleParser();
            List<FluentExample> examples = fluentClient.getResourceCollections().stream()
                    .flatMap(rc -> exampleParser.parseResourceCollection(rc).stream())
                    .sorted()
                    .collect(Collectors.toList());
            fluentClient.getExamples().addAll(examples);
        }

        // live tests
        fluentClient.getLiveTests().addAll(client.getLiveTests().stream().map(liveTests -> mapLiveTests(liveTests, fluentClient)).collect(Collectors.toList()));

        return fluentClient;
    }

    private FluentLiveTests mapLiveTests(LiveTests liveTests, FluentClient fluentClient) {

        FluentLiveTests result = new FluentLiveTests();
        result.setClassName(liveTests.getTestClassName());

        parseLiveTests: for (LiveTestCase liveTestCase : liveTests.getTestCases()) {
            FluentLiveTestCase testCase = new FluentLiveTestCase();
            for (LiveTestStep liveTestStep : liveTestCase.getTestSteps()) {
                if (liveTestStep instanceof ExampleLiveTestStep) {
                    FluentExampleLiveTestStep fluentStep = new FluentExampleLiveTestStep();
                    ExampleLiveTestStep exampleStep = (ExampleLiveTestStep) liveTestStep;
                    String operationId = exampleStep.getOperationId();
                    String[] oprs = operationId.split("_");
                    String operationGroup = oprs[0];
                    String operation = oprs[1];
                    ProxyMethodExample example = exampleStep.getExample();
                    FluentResourceCollection resourceCollection = findResourceCollection(fluentClient, operationGroup);

                    // find collectionMethod
                    Optional<FluentCollectionMethod> collectionMethodOptional = findCollectionMethod(resourceCollection, operation);
                    if (collectionMethodOptional.isPresent()) {
                        FluentCollectionMethod collectionMethod = collectionMethodOptional.get();
                        FluentCollectionMethodExample collectionMethodExample = ExampleParser.parseMethodExample(resourceCollection, collectionMethod, example);
                        FluentExampleTemplate.ExampleMethod exampleMethod = fluentExampleTemplate.generateExampleMethod(collectionMethodExample);
                        testCase.getHelperFeatures().addAll(exampleMethod.getHelperFeatures());
                        fluentStep.setExampleMethod(exampleMethod);
                    } else {
                        // find resourceCreate
                        Optional<ResourceCreate> createMethod = findResourceCreate(resourceCollection, operation);
                        if (createMethod.isPresent()) {
                            ResourceCreate create = createMethod.get();
                            FluentResourceCreateExample createExample = ExampleParser.parseResourceCreate(resourceCollection, create, example);
                            FluentExampleTemplate.ExampleMethod exampleMethod = fluentExampleTemplate.generateExampleMethod(createExample);
                            testCase.getHelperFeatures().addAll(exampleMethod.getHelperFeatures());
                            fluentStep.setExampleMethod(exampleMethod);
                        } else {
                            // find resourceUpdate
                            Optional<ResourceUpdate> updateMethod = resourceCollection.getResourceUpdates().stream().filter(rc -> FluentUtils.exampleIsUpdate(rc.getMethodName()) && rc.getMethodName().equalsIgnoreCase(operation)).findFirst();
                            if (updateMethod.isPresent()) {
                                ResourceUpdate update = updateMethod.get();
                                FluentResourceUpdateExample updateExample = ExampleParser.parseResourceUpdate(resourceCollection, update, example);
                                if (updateExample == null) {
                                    continue parseLiveTests;
                                }
                                FluentExampleTemplate.ExampleMethod exampleMethod = fluentExampleTemplate.generateExampleMethod(updateExample);
                                testCase.getHelperFeatures().addAll(exampleMethod.getHelperFeatures());
                                fluentStep.setExampleMethod(exampleMethod);
                            }
                        }
                    }
                    testCase.getSteps().add(fluentStep);
                } else {
                    continue parseLiveTests;
                }
            }

            result.getTestCases().add(testCase);

        }

        return result;
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

    FluentClient basicMap(CodeModel codeModel, Client client) {
        FluentClient fluentClient = new FluentClient(client);

        fluentClient.setModuleInfo(moduleInfo());

        FluentStatic.setFluentClient(fluentClient);

        // manager, service API
        fluentClient.setManager(new FluentManager(client, Utils.getJavaName(codeModel)));

        // wrapper for response objects, potentially as resource instance
        fluentClient.getResourceModels().addAll(
                codeModel.getSchemas().getObjects().stream()
                        .map(o -> FluentResourceModelMapper.getInstance().map(o))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()));

        // resource collection APIs
        fluentClient.getResourceCollections().addAll(
                codeModel.getOperationGroups().stream()
                        .map(og -> FluentResourceCollectionMapper.getInstance().map(og))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()));

        // set resource collection APIs to service API
        fluentClient.getManager().getProperties().addAll(
                fluentClient.getResourceCollections().stream()
                        .map(FluentManagerProperty::new)
                        .collect(Collectors.toList()));

        return fluentClient;
    }

    private static ModuleInfo moduleInfo() {
        JavaSettings settings = JavaSettings.getInstance();
        ModuleInfo moduleInfo = new ModuleInfo(settings.getPackage());

        List<ModuleInfo.RequireModule> requireModules = moduleInfo.getRequireModules();
        requireModules.add(new ModuleInfo.RequireModule("com.azure.core.management", true));

        List<ModuleInfo.ExportModule> exportModules = moduleInfo.getExportModules();
        exportModules.add(new ModuleInfo.ExportModule(settings.getPackage()));
        exportModules.add(new ModuleInfo.ExportModule(settings.getPackage(settings.getFluentSubpackage())));
        exportModules.add(new ModuleInfo.ExportModule(settings.getPackage(settings.getFluentModelsSubpackage())));
        exportModules.add(new ModuleInfo.ExportModule(settings.getPackage(settings.getModelsSubpackage())));

        List<String> openToModules = Arrays.asList("com.azure.core", "com.fasterxml.jackson.databind");
        List<ModuleInfo.OpenModule> openModules = moduleInfo.getOpenModules();
        openModules.add(new ModuleInfo.OpenModule(settings.getPackage(settings.getFluentModelsSubpackage()), openToModules));
        openModules.add(new ModuleInfo.OpenModule(settings.getPackage(settings.getModelsSubpackage()), openToModules));

        return moduleInfo;
    }

    private void processInnerModel(CodeModel codeModel) {
        // Add "Inner" to all method response types, and recursively to types having it as property.

        final FluentObjectMapper objectMapper = (FluentObjectMapper) Mappers.getObjectMapper();

        Set<ObjectSchema> compositeTypes = Stream.concat(Stream.concat(Stream.concat(
                // ObjectSchema
                codeModel.getOperationGroups().stream()
                        .flatMap(og -> og.getOperations().stream())
                        .filter(o -> !isPossiblePagedList(o))
                        .flatMap(o -> o.getResponses().stream())
                        .map(Response::getSchema),
                // Paged list
                codeModel.getOperationGroups().stream()
                        .flatMap(og -> og.getOperations().stream())
                        .filter(FluentMapper::isPossiblePagedList)
                        .flatMap(o ->  o.getResponses().stream())
                        .filter(r -> r.getSchema() instanceof ObjectSchema)
                        .map(r -> (ObjectSchema) r.getSchema())
                        .flatMap(s -> s.getProperties().stream())
                        .filter(p -> p.getSerializedName().equals("value") && p.getSchema() instanceof ArraySchema)
                        .map(p -> ((ArraySchema) p.getSchema()).getElementType())),
                // ArraySchema
                codeModel.getOperationGroups().stream()
                        .flatMap(og -> og.getOperations().stream())
                        .flatMap(o -> o.getResponses().stream())
                        .map(Response::getSchema)
                        .filter(s -> s instanceof ArraySchema)
                        .map(s -> ((ArraySchema) s).getElementType())),
                // DictionarySchema
                codeModel.getOperationGroups().stream()
                        .flatMap(og -> og.getOperations().stream())
                        .flatMap(o -> o.getResponses().stream())
                        .map(Response::getSchema)
                        .filter(s -> s instanceof DictionarySchema)
                        .map(s -> ((DictionarySchema) s).getElementType()))
                .filter(s -> s instanceof ObjectSchema)
                .map(s -> (ObjectSchema) s)
                .filter(FluentType::nonResourceType)
                .collect(Collectors.toSet());

        Set<ObjectSchema> errorTypes = codeModel.getOperationGroups().stream()
                .flatMap(og -> og.getOperations().stream())
                .flatMap(o -> o.getExceptions().stream())
                .map(Response::getSchema)
                .filter(s -> s instanceof ObjectSchema)
                .map(s -> (ObjectSchema) s)
                .filter(o -> FluentType.nonManagementError(Utils.getJavaName(o)))
                .collect(Collectors.toSet());

        compositeTypes.removeAll(errorTypes);

        compositeTypes = objectMapper.addInnerModels(compositeTypes);
        if (logger.isInfoEnabled()) {
            logger.info("Add Inner to response types: {}",
                    compositeTypes.stream().map(Utils::getJavaName).collect(Collectors.toList()));
        }
        recursiveAddInnerModel(objectMapper, codeModel, compositeTypes);

        final Set<String> javaNamesForAddInner = fluentJavaSettings.getJavaNamesForAddInner();
        if (!javaNamesForAddInner.isEmpty()) {
            compositeTypes = codeModel.getSchemas().getObjects().stream()
                    .filter(s -> javaNamesForAddInner.contains(Utils.getJavaName(s)))
                    .collect(Collectors.toSet());

            compositeTypes = objectMapper.addInnerModels(compositeTypes);
            if (logger.isInfoEnabled()) {
                logger.info("Add Inner as requested: {}",
                        compositeTypes.stream().map(Utils::getJavaName).collect(Collectors.toList()));
            }
            recursiveAddInnerModel(objectMapper, codeModel, compositeTypes);
        }

        final Set<String> javaNamesForRemoveInner = fluentJavaSettings.getJavaNamesForRemoveInner();
        if (!javaNamesForRemoveInner.isEmpty()) {
            objectMapper.removeInnerModels(javaNamesForRemoveInner);
        }
    }

    private static boolean isPossiblePagedList(Operation operation) {
        return (operation.getExtensions() != null && operation.getExtensions().getXmsPageable() != null);
//                || (Utils.getJavaName(operation).equals(WellKnownMethodName.LIST) || Utils.getJavaName(operation).equals(WellKnownMethodName.LIST_BY_RESOURCE_GROUP));
    }

    private static void recursiveAddInnerModel(FluentObjectMapper objectMapper, CodeModel codeModel, Collection<ObjectSchema> compositeTypes) {
        compositeTypes.forEach(s -> recursiveAddInnerModel(objectMapper, codeModel, Utils.getJavaName(s)));
    }

    private static void recursiveAddInnerModel(FluentObjectMapper objectMapper, CodeModel codeModel, String typeName) {
        if (typeName == null) return;

        Set<ObjectSchema> compositeTypesInProperties = Stream.concat(Stream.concat(
                // ObjectSchema
                codeModel.getSchemas().getObjects().stream(),
                // ArraySchema
                codeModel.getSchemas().getArrays().stream()
                        .map(ArraySchema::getElementType)
                        .filter(s -> s instanceof ObjectSchema)
                        .map(s -> (ObjectSchema) s)),
                // DictionarySchema
                codeModel.getSchemas().getDictionaries().stream()
                        .map(DictionarySchema::getElementType)
                        .filter(s -> s instanceof ObjectSchema)
                        .map(s -> (ObjectSchema) s))
                .filter(s -> {
                    if (s.getProperties() == null) return false;
                    return s.getProperties().stream()
                            .map(Value::getSchema)
                            .anyMatch(t -> t instanceof ObjectSchema && typeName.equals(Utils.getJavaName(t)));
                })
                .collect(Collectors.toSet());

        if (!compositeTypesInProperties.isEmpty()) {
            compositeTypesInProperties = objectMapper.addInnerModels(compositeTypesInProperties);
            if (logger.isInfoEnabled()) {
                logger.info("Add Inner for type '{}': {}", typeName,
                        compositeTypesInProperties.stream().map(Utils::getJavaName).collect(Collectors.toList()));
            }
            recursiveAddInnerModel(objectMapper, codeModel, compositeTypesInProperties);
        }
    }
}
