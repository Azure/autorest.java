package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.ArraySchema;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.DictionarySchema;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Response;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.model.codemodel.Value;
import com.azure.autorest.fluent.FluentJavaSettings;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.mapper.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FluentMapper {

    private final static Logger logger = LoggerFactory.getLogger(FluentMapper.class);

    private final FluentJavaSettings fluentJavaSettings;

    public FluentMapper(FluentJavaSettings fluentJavaSettings) {
        this.fluentJavaSettings = fluentJavaSettings;
    }

    public void preModelMap(CodeModel codeModel) {
        processInnerModel(codeModel);
    }

    private void processInnerModel(CodeModel codeModel) {
        // Add "Inner" to all method response types, and recursively to types having it as property.

        final FluentObjectMapper objectMapper = (FluentObjectMapper) Mappers.getObjectMapper();

        Set<ObjectSchema> compositeTypes = Stream.concat(Stream.concat(
                // ObjectSchema
                codeModel.getOperationGroups().stream()
                        .flatMap(og -> og.getOperations().stream())
                        .flatMap(o -> o.getResponses().stream())
                        .map(Response::getSchema),
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
                .filter(ObjectMapper::nonResourceType)
                .collect(Collectors.toSet());

        compositeTypes = objectMapper.addInnerModels(compositeTypes);
        if (logger.isInfoEnabled()) {
            logger.info("Add Inner to response types: " +
                    compositeTypes.stream().map(FluentMapper::getJavaName).collect(Collectors.toList()));
        }
        recursiveAddInnerModel(objectMapper, codeModel, compositeTypes);

        final Set<String> javaNamesForAddInner = fluentJavaSettings.getJavaNamesForAddInner();
        if (!javaNamesForAddInner.isEmpty()) {
            compositeTypes = codeModel.getSchemas().getObjects().stream()
                    .filter(s -> javaNamesForAddInner.contains(getJavaName(s)))
                    .collect(Collectors.toSet());

            compositeTypes = objectMapper.addInnerModels(compositeTypes);
            if (logger.isInfoEnabled()) {
                logger.info("Add Inner as requested: " +
                        compositeTypes.stream().map(FluentMapper::getJavaName).collect(Collectors.toList()));
            }
            recursiveAddInnerModel(objectMapper, codeModel, compositeTypes);
        }
    }

    private static void recursiveAddInnerModel(FluentObjectMapper objectMapper, CodeModel codeModel, Collection<ObjectSchema> compositeTypes) {
        compositeTypes.forEach(s -> recursiveAddInnerModel(objectMapper, codeModel, getJavaName(s)));
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
                            .anyMatch(t -> t instanceof ObjectSchema && typeName.equals(getJavaName(t)));
                })
                .collect(Collectors.toSet());

        if (!compositeTypesInProperties.isEmpty()) {
            compositeTypesInProperties = objectMapper.addInnerModels(compositeTypesInProperties);
            if (logger.isInfoEnabled()) {
                logger.info("Add Inner for type " + typeName + ": " +
                        compositeTypesInProperties.stream().map(FluentMapper::getJavaName).collect(Collectors.toList()));
            }
            recursiveAddInnerModel(objectMapper, codeModel, compositeTypesInProperties);
        }
    }

    private static String getJavaName(Schema s) {
        return s.getLanguage() != null && s.getLanguage().getJava() != null && s.getLanguage().getJava().getName() != null
                ? s.getLanguage().getJava().getName()
                : null;
    }
}
