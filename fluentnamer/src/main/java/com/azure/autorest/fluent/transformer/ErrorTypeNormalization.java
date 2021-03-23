/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.transformer;

import com.azure.autorest.extension.base.model.codemodel.ArraySchema;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.Language;
import com.azure.autorest.extension.base.model.codemodel.Languages;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Property;
import com.azure.autorest.extension.base.model.codemodel.Relations;
import com.azure.autorest.extension.base.model.codemodel.Response;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.model.codemodel.Value;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.model.FluentType;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.fluentnamer.FluentNamer;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ErrorTypeNormalization {

    private static final Logger logger = new PluginLogger(FluentNamer.getPluginInstance(), ErrorTypeNormalization.class);

    public CodeModel process(CodeModel codeModel) {
        codeModel.getOperationGroups().stream()
                .flatMap(og -> og.getOperations().stream())
                .flatMap(o -> o.getExceptions().stream())
                .map(Response::getSchema)
                .filter(Objects::nonNull)
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
        schema.setProperties(new ArrayList<>());
        schema.getProperties().add(new Property());
        schema.getProperties().get(0).setSerializedName("code");
        schema.getProperties().add(new Property());
        schema.getProperties().get(1).setSerializedName("message");
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

        normalizeErrorType(error, errorSchema);
    }

    private void normalizeErrorType(ObjectSchema error, ObjectSchema errorSchema) {
        switch (getErrorType(errorSchema)) {
            case MANAGEMENT_ERROR:
                logger.info("Rename error from '{}' to 'ManagementError'", Utils.getJavaName(error));

                error.getLanguage().getJava().setName(FluentType.ManagementError.getName());

                if (errorSchema != error) {
                    errorSchema.getLanguage().getJava().setName(FluentType.ManagementError.getName());
                }

                if (errorSchema != error) {
                    error.setChildren(errorSchema.getChildren());
                }

                normalizeSubclass(errorSchema);

                break;

            case SUBCLASS_MANAGEMENT_ERROR:
                logger.info("Modify error '{}' as subclass of 'ManagementError'", Utils.getJavaName(error));

                error.getLanguage().getJava().setName(Utils.getJavaName(errorSchema));

                // make it a subclass of ManagementError
                Relations parents = new Relations();
                parents.setAll(Collections.singletonList(DUMMY_ERROR));
                parents.setImmediate(Collections.singletonList(DUMMY_ERROR));
                errorSchema.setParents(parents);

                if (errorSchema != error) {
                    error.setParents(parents);
                    error.setChildren(errorSchema.getChildren());
                }

                filterProperties(errorSchema);

                if (errorSchema != error) {
                    error.setProperties(errorSchema.getProperties());
                }

                normalizeSubclass(errorSchema);

                break;

            case GENERIC:
                break;
        }
    }

    private void normalizeSubclass(ObjectSchema errorSchema) {
        if (errorSchema.getChildren() != null && errorSchema.getChildren().getImmediate() != null) {
            for (Schema schema : errorSchema.getChildren().getImmediate()) {
                if (schema instanceof ObjectSchema) {
                    ObjectSchema error = (ObjectSchema) schema;

                    logger.info("Modify type '{}' as subclass of '{}'", Utils.getJavaName(error), Utils.getJavaName(errorSchema));

                    filterProperties(error);
                }
            }
        }
    }

    private void filterProperties(ObjectSchema errorSchema) {
        List<Property> properties = new ArrayList<>();
        errorSchema.getProperties().forEach(p -> {
            if (!MANAGEMENT_ERROR_FIELDS.contains(p.getSerializedName())) {
                p.setReadOnly(true);
                properties.add(p);
            } else if (p.getSerializedName().equals("details")) {
                normalizeErrorDetailType(p);
                if (FluentType.nonManagementError(Utils.getJavaName(((ArraySchema) p.getSchema()).getElementType()))) {
                    p.setReadOnly(true);
                    properties.add(p);
                }
            }
        });
        errorSchema.setProperties(properties);
    }

    private void normalizeErrorDetailType(Property details) {
        Schema detailsSchema = details.getSchema();
        if (detailsSchema instanceof ArraySchema && ((ArraySchema) detailsSchema).getElementType() instanceof ObjectSchema ) {
            ObjectSchema error = (ObjectSchema) ((ArraySchema) detailsSchema).getElementType();
            if (error.getParents() == null || FluentType.nonManagementError(Utils.getJavaName(error.getParents().getImmediate().get(0)))) {
                // if not subclass of ManagementError, normalize it

                switch (getErrorType(error)) {
                    case MANAGEMENT_ERROR:
                        error.getLanguage().getJava().setName(FluentType.ManagementError.getName());
                        break;

                    case SUBCLASS_MANAGEMENT_ERROR:
                    case GENERIC:
                        Relations parents = new Relations();
                        parents.setAll(Collections.singletonList(DUMMY_ERROR));
                        parents.setImmediate(Collections.singletonList(DUMMY_ERROR));
                        error.setParents(parents);

                        filterProperties(error);
                        break;
                }
            }
        } else {
            ArraySchema arraySchema = new ArraySchema();
            arraySchema.setLanguage(new Languages());
            arraySchema.getLanguage().setJava(new Language());
            arraySchema.getLanguage().getJava().setName("ManagementErrorDetails");

            arraySchema.setElementType(DUMMY_ERROR);

            details.setSchema(arraySchema);
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

    private enum ErrorType {
        MANAGEMENT_ERROR, SUBCLASS_MANAGEMENT_ERROR, GENERIC
    }
}
