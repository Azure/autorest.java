/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 *
 */

package com.azure.autorest.fluent.transformer;

import com.azure.autorest.extension.base.model.codemodel.ArraySchema;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.DictionarySchema;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.model.codemodel.Property;
import com.azure.autorest.extension.base.model.codemodel.Response;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.model.FluentType;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.fluentnamer.FluentNamer;
import org.slf4j.Logger;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Cleans up unused flattened types.
 */
public class SchemaCleanup {

    private static final Logger logger = new PluginLogger(FluentNamer.getPluginInstance(), SchemaCleanup.class);

    public CodeModel process(CodeModel codeModel) {
        Set<ObjectSchema> schemasNotInUse = codeModel.getSchemas().getObjects().stream()
//                .filter(SchemaCleanup::hasFlattenedExtension)
                .filter(schema -> schema.getChildren() == null || schema.getChildren().getImmediate() == null
                        || schema.getChildren().getImmediate().isEmpty())   // no children
                .filter(schema -> schema.getParents() == null || schema.getParents().getImmediate() == null
                        || schema.getParents().getImmediate().stream().allMatch(s -> {
                            if (s instanceof ObjectSchema) {
                                return !FluentType.nonResourceType((ObjectSchema) s);
                            } else {
                                return false;
                            }
                        }))
                .collect(Collectors.toSet());

        Set<Schema> choicesSchemasNotInUse = new HashSet<>(codeModel.getSchemas().getSealedChoices());
        choicesSchemasNotInUse.addAll(codeModel.getSchemas().getChoices());

        Set<Schema> schemasInUse;
        if (!schemasNotInUse.isEmpty()) {
            // properties of object
            schemasInUse = codeModel.getSchemas().getObjects().stream()
                    .filter(o -> {
                        String name = Utils.getJavaName(o);
                        return FluentType.nonSystemData(name) && FluentType.nonManagementError(name);
                    })
                    .flatMap(s -> s.getProperties().stream())
//                    .filter(Utils::nonFlattenedProperty)
                    .map(Property::getSchema)
                    .map(SchemaCleanup::schemaOrElementInCollection)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            schemasNotInUse.removeAll(schemasInUse);
            choicesSchemasNotInUse.removeAll(schemasInUse);
        }
        if (!schemasNotInUse.isEmpty()) {
            // operation requests
            schemasInUse = codeModel.getOperationGroups().stream()
                    .flatMap(og -> og.getOperations().stream())
                    .flatMap(o -> o.getRequests().stream())
                    .flatMap(r -> r.getParameters().stream())
                    .map(Parameter::getSchema)
                    .map(SchemaCleanup::schemaOrElementInCollection)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            schemasNotInUse.removeAll(schemasInUse);
            choicesSchemasNotInUse.removeAll(schemasInUse);
        }
        if (!schemasNotInUse.isEmpty()) {
            // operation responses
            schemasInUse = codeModel.getOperationGroups().stream()
                    .flatMap(og -> og.getOperations().stream())
                    .flatMap(o -> o.getResponses().stream())
                    .map(Response::getSchema)
                    .map(SchemaCleanup::schemaOrElementInCollection)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            schemasNotInUse.removeAll(schemasInUse);
            choicesSchemasNotInUse.removeAll(schemasInUse);
        }
        if (!schemasNotInUse.isEmpty()) {
            // operation exception
            schemasInUse = codeModel.getOperationGroups().stream()
                    .flatMap(og -> og.getOperations().stream())
                    .flatMap(o -> o.getExceptions().stream())
                    .map(Response::getSchema)
                    .map(SchemaCleanup::schemaOrElementInCollection)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            schemasNotInUse.removeAll(schemasInUse);
            choicesSchemasNotInUse.removeAll(schemasInUse);
        }

        codeModel.getSchemas().getObjects().removeIf(s -> {
            boolean unused = schemasNotInUse.contains(s);
            if (unused) {
                logger.info("Remove unused object schema '{}'", Utils.getJavaName(s));
            }
            return unused;
        });

        codeModel.getSchemas().getSealedChoices().removeIf(s -> {
            boolean unused = choicesSchemasNotInUse.contains(s);
            if (unused) {
                logger.info("Remove unused sealed choice schema '{}'", Utils.getJavaName(s));
            }
            return unused;
        });

        codeModel.getSchemas().getChoices().removeIf(s -> {
            boolean unused = choicesSchemasNotInUse.contains(s);
            if (unused) {
                logger.info("Remove unused choice schema '{}'", Utils.getJavaName(s));
            }
            return unused;
        });

        return codeModel;
    }

    private static Schema schemaOrElementInCollection(Schema schema) {
        if (schema instanceof ArraySchema) {
            return schemaOrElementInCollection(((ArraySchema) schema).getElementType());
        } else if (schema instanceof DictionarySchema) {
            return schemaOrElementInCollection(((DictionarySchema) schema).getElementType());
        } else {
            return schema;
        }
    }

//    private static boolean hasFlattenedExtension(Schema schema) {
//        return schema.getExtensions() != null && schema.getExtensions().isXmsFlattened();
//    }
}
