/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

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
import com.azure.autorest.fluent.model.clientmodel.FluentManager;
import com.azure.autorest.fluent.model.clientmodel.FluentManagerProperty;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentCollectionMethodExample;
import com.azure.autorest.fluent.util.FluentJavaSettings;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.model.clientmodel.ModuleInfo;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FluentMapper {

    private static final Logger logger = new PluginLogger(FluentGen.getPluginInstance(), FluentMapper.class);

    private final FluentJavaSettings fluentJavaSettings;

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
            final Set<String> exampleClassNames = new HashSet<>();
            List<FluentCollectionMethodExample> methodExamples = new ArrayList<>();

            for (FluentResourceCollection resourceCollection : fluentClient.getResourceCollections()) {
                resourceCollection.getMethodsForTemplate().forEach(m -> {
                    List<FluentCollectionMethodExample> examples = ExampleParser.parseMethod(resourceCollection, m);
                    if (examples != null) {
                        for (FluentCollectionMethodExample example : examples) {
                            String className = example.getClassName();
                            int count = 0;
                            while (exampleClassNames.contains(example.getClassName())) {
                                example.setClassName(className + (++count));
                            }
                            exampleClassNames.add(example.getClassName());
                            methodExamples.add(example);
                        }
                    }
                });

                resourceCollection.getResourceCreates().forEach(rc -> ExampleParser.parseResourceCreate(resourceCollection, rc));
            }
            fluentClient.getResourceCollectionMethodExamples().addAll(methodExamples);
        }

        return fluentClient;
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
