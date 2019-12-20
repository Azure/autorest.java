package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Response;
import com.azure.autorest.extension.base.model.codemodel.Value;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.mapper.ObjectMapper;

import java.util.Set;
import java.util.stream.Collectors;

public class FluentMapper {

    public void preModelMap(CodeModel codeModel) {
        // Add "inner" to all method response types (and type of elements of simple list), and its properties.

        ObjectMapper objectMapper = Mappers.getObjectMapper();

        Set<ObjectSchema> complexTypes = codeModel.getOperationGroups().stream()
                .flatMap(og -> og.getOperations().stream())
                .flatMap(o -> o.getResponses().stream())
                .map(Response::getSchema)
                .filter(s -> s instanceof ObjectSchema)
                .map(s -> (ObjectSchema) s)
                .filter(ObjectMapper::nonResourceType)
                .collect(Collectors.toSet());

//        Set<ObjectSchema> complexTypes = Stream.concat(
//                codeModel.getOperationGroups().stream()
//                        .flatMap(og -> og.getOperations().stream())
//                        .flatMap(o -> o.getResponses().stream())
//                        .map(Response::getSchema)
//                        .filter(s -> s instanceof ObjectSchema)
//                        .map(s -> (ObjectSchema) s)
//                        .filter(ObjectMapper::nonResourceType),
//                codeModel.getOperationGroups().stream()
//                        .flatMap(og -> og.getOperations().stream())
//                        .flatMap(o -> o.getResponses().stream())
//                        .map(Response::getSchema)
//                        .filter(s -> s instanceof ArraySchema)
//                        .map(s -> ((ArraySchema) s).getElementType())
//                        .filter(s -> s instanceof ObjectSchema)
//                        .map(s -> (ObjectSchema) s)
//                        .filter(ObjectMapper::nonResourceType))
//                .collect(Collectors.toSet());

        objectMapper.addInnerModels(complexTypes);

        complexTypes.forEach(s -> recursiveAddInnerModel(objectMapper, s));
    }

    private static void recursiveAddInnerModel(ObjectMapper objectMapper, ObjectSchema complexSchema) {
        if (complexSchema.getProperties() != null) {
            Set<ObjectSchema> complexTypes = complexSchema.getProperties().stream()
                    .map(Value::getSchema)
                    .filter(s -> s instanceof ObjectSchema)
                    .map(s -> (ObjectSchema) s)
                    .filter(ObjectMapper::nonResourceType)
                    .collect(Collectors.toSet());

            objectMapper.addInnerModels(complexTypes);

            complexTypes.forEach(s -> recursiveAddInnerModel(objectMapper, s));
        }
    }
}
