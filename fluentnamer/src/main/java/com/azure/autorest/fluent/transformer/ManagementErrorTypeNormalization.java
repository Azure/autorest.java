/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.transformer;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.Language;
import com.azure.autorest.extension.base.model.codemodel.Languages;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Property;
import com.azure.autorest.extension.base.model.codemodel.Relations;
import com.azure.autorest.extension.base.model.codemodel.Response;
import com.azure.autorest.extension.base.model.codemodel.Value;
import com.azure.autorest.fluent.model.FluentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ManagementErrorTypeNormalization {

    private static final Logger logger = LoggerFactory.getLogger(ManagementErrorTypeNormalization.class);

    public CodeModel process(CodeModel codeModel) {
        codeModel.getOperationGroups().stream()
                .flatMap(og -> og.getOperations().stream())
                .flatMap(o -> o.getExceptions().stream())
                .map(Response::getSchema)
                .distinct()
                .forEach(s -> process((ObjectSchema) s));

        return codeModel;
    }

    private static final Set<String> MANAGEMENT_ERROR_FIELDS = new HashSet<>(Arrays.asList("code", "message", "target", "details", "additionalInfo"));
    private static final Set<String> MANAGEMENT_ERROR_FIELDS_MIN_REQUIRED = new HashSet<>(Arrays.asList("code", "message"));

    private static final ObjectSchema DUMMY_ERROR = dummyManagementError();

    private static ObjectSchema dummyManagementError() {
        ObjectSchema schema = new ObjectSchema();
        schema.setLanguage(new Languages());
        schema.getLanguage().setJava(new Language());
        schema.getLanguage().getJava().setName(FluentType.ManagementError.getName());
        return schema;
    }

    private void process(ObjectSchema error) {
        ObjectSchema errorSchema = error;

        Optional<ObjectSchema> errorSchemaOpt = error.getProperties().stream()
                .filter(p -> p.getSerializedName().equalsIgnoreCase("error"))
                .map(Value::getSchema)
                .filter(s -> s instanceof ObjectSchema)
                .map(s -> (ObjectSchema) s)
                .findFirst();

        if (errorSchemaOpt.isPresent()) {
            errorSchema = errorSchemaOpt.get();
        }

        switch (getErrorType(errorSchema)) {
            case MANAGEMENT_ERROR:
                error.getLanguage().getJava().setName(FluentType.ManagementError.getName());
                break;

            case SUBCLASS_MANAGEMENT_ERROR:
                Relations parents = new Relations();
                parents.setAll(Collections.singletonList(DUMMY_ERROR));
                parents.setImmediate(Collections.singletonList(DUMMY_ERROR));
                error.setParents(parents);

                List<Property> properties = new ArrayList<>();
                errorSchema.getProperties().stream().forEach(p -> {
                    if (!MANAGEMENT_ERROR_FIELDS.contains(p.getSerializedName())) {
                        properties.add(p);
                    } else if (p.getSerializedName().equals("details")) {
                        properties.add(p);
                    }
                });
                error.setProperties(properties);
                break;

            case GENERIC:
                break;
        }
    }

    private ErrorType getErrorType(ObjectSchema error) {
        Set<String> propertyNames = error.getProperties().stream()
                .map(Property::getSerializedName)
                .collect(Collectors.toSet());

        ErrorType type;
        if (MANAGEMENT_ERROR_FIELDS.containsAll(propertyNames)) {
            type = ErrorType.MANAGEMENT_ERROR;
        } else if (propertyNames.containsAll(MANAGEMENT_ERROR_FIELDS_MIN_REQUIRED)) {
            type = ErrorType.SUBCLASS_MANAGEMENT_ERROR;
        } else {
            type = ErrorType.GENERIC;
        }
        return type;
    }

    private static enum ErrorType {
        MANAGEMENT_ERROR, SUBCLASS_MANAGEMENT_ERROR, GENERIC
    }
}
