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
import com.azure.autorest.fluent.model.FluentType;
import com.azure.autorest.fluent.model.clientmodel.FluentClient;
import com.azure.autorest.fluent.model.clientmodel.FluentManager;
import com.azure.autorest.fluent.util.FluentJavaSettings;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.model.clientmodel.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FluentMapper {

    private static final Logger logger = LoggerFactory.getLogger(FluentMapper.class);

    private final FluentJavaSettings fluentJavaSettings;

    public FluentMapper(FluentJavaSettings fluentJavaSettings) {
        this.fluentJavaSettings = fluentJavaSettings;
    }

    public void preModelMap(CodeModel codeModel) {
        processInnerModel(codeModel);
    }

    public FluentClient map(CodeModel codeModel, Client client) {
        FluentClient fluentClient = new FluentClient(client);

        fluentClient.setManager(new FluentManager(client, Utils.getJavaName(codeModel)));

        fluentClient.getResourceModels().addAll(codeModel.getSchemas().getObjects().stream()
                .map(o -> FluentResourceModelMapper.getInstance().map(o))
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));

        return fluentClient;
    }

    private void processInnerModel(CodeModel codeModel) {
        // Add "Inner" to all method response types, and recursively to types having it as property.

        final FluentObjectMapper objectMapper = (FluentObjectMapper) Mappers.getObjectMapper();

        Set<ObjectSchema> compositeTypes = Stream.concat(Stream.concat(Stream.concat(
                // ObjectSchema
                codeModel.getOperationGroups().stream()
                        .flatMap(og -> og.getOperations().stream())
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

        compositeTypes = objectMapper.addInnerModels(compositeTypes);
        if (logger.isInfoEnabled()) {
            logger.info("Add Inner to response types: " +
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
                logger.info("Add Inner as requested: " +
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
                logger.info("Add Inner for type " + typeName + ": " +
                        compositeTypesInProperties.stream().map(Utils::getJavaName).collect(Collectors.toList()));
            }
            recursiveAddInnerModel(objectMapper, codeModel, compositeTypesInProperties);
        }
    }
}
