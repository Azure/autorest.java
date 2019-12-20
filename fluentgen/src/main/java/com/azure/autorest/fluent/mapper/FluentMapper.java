package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Response;
import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.mapper.ObjectMapper;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FluentMapper {

    private final Set<String> javaNamesForAddInner;

    public FluentMapper(NewPlugin host) {
        String addInnerSetting = host.getStringValue("add-inner");
        if (addInnerSetting != null && !addInnerSetting.isEmpty()) {
            javaNamesForAddInner = Arrays.stream(addInnerSetting.split(Pattern.quote(",")))
                    .filter(s -> !s.isEmpty())
                    .map(String::trim)
                    .collect(Collectors.toSet());
        } else {
            javaNamesForAddInner = Collections.emptySet();
        }
    }

    public void preModelMap(CodeModel codeModel) {
        processInnerModel(codeModel);
    }

    private void processInnerModel(CodeModel codeModel) {
        // Add "inner" to all method response types, and recursively to its properties.

        final ObjectMapper objectMapper = Mappers.getObjectMapper();

        Set<ObjectSchema> compositeTypes = codeModel.getOperationGroups().stream()
                .flatMap(og -> og.getOperations().stream())
                .flatMap(o -> o.getResponses().stream())
                .map(Response::getSchema)
                .filter(s -> s instanceof ObjectSchema)
                .map(s -> (ObjectSchema) s)
                .filter(ObjectMapper::nonResourceType)
                .collect(Collectors.toSet());

        compositeTypes = objectMapper.addInnerModels(compositeTypes);
        recursiveAddInnerModel(objectMapper, compositeTypes);

        if (!javaNamesForAddInner.isEmpty()) {
            compositeTypes = codeModel.getSchemas().getObjects().stream()
                    .filter(s -> s.getLanguage() != null && s.getLanguage().getJava() != null && s.getLanguage().getJava().getName() != null && javaNamesForAddInner.contains(s.getLanguage().getJava().getName()))
                    .collect(Collectors.toSet());

            compositeTypes = objectMapper.addInnerModels(compositeTypes);
            recursiveAddInnerModel(objectMapper, compositeTypes);
        }
    }

    private static void recursiveAddInnerModel(ObjectMapper objectMapper, Collection<ObjectSchema> compositeTypes) {
        compositeTypes.forEach(s -> recursiveAddInnerModel(objectMapper, s));
    }

    private static void recursiveAddInnerModel(ObjectMapper objectMapper, ObjectSchema compositeType) {
//        if (compositeType.getProperties() != null) {
//            Set<ObjectSchema> compositeTypesInProperties = compositeType.getProperties().stream()
//                    .map(Value::getSchema)
//                    .filter(s -> s instanceof ObjectSchema)
//                    .map(s -> (ObjectSchema) s)
//                    .filter(ObjectMapper::nonResourceType)
//                    .collect(Collectors.toSet());
//
//            compositeTypesInProperties = objectMapper.addInnerModels(compositeTypesInProperties);
//            recursiveAddInnerModel(objectMapper, compositeTypesInProperties);
//        }
    }
}
