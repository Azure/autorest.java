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
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.model.codemodel.Property;
import com.azure.autorest.extension.base.model.codemodel.Response;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.fluent.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Cleans up unused flattened types.
 */
public class FlattenedTypeCleanup {

    private final static Logger logger = LoggerFactory.getLogger(FlattenedTypeCleanup.class);

    public CodeModel process(CodeModel codeModel) {
        Set<ObjectSchema> schemasNotInUse = codeModel.getSchemas().getObjects().stream()
                .filter(FlattenedTypeCleanup::hasFlattenedExtension)
                .filter(schema -> schema.getChildren() == null || schema.getChildren().getAll().stream().allMatch(FlattenedTypeCleanup::hasFlattenedExtension)) // no children
                .filter(schema -> schema.getParents() == null || schema.getParents().getAll().stream().allMatch(FlattenedTypeCleanup::hasFlattenedExtension))   // no parent
                .collect(Collectors.toSet());

        Set<Schema> schemasInUse;
        if (!schemasNotInUse.isEmpty()) {
            // properties of non-flattened object
            schemasInUse = codeModel.getSchemas().getObjects().stream()
                    .flatMap(s -> s.getProperties().stream())
                    .filter(p -> p.getFlattenedNames() == null || p.getFlattenedNames().isEmpty())
                    .map(Property::getSchema)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            schemasNotInUse.removeAll(schemasInUse);
        }
        if (!schemasNotInUse.isEmpty()) {
            // elements of array or dictionary
            schemasInUse = Stream.concat(
                    codeModel.getSchemas().getArrays().stream().map(ArraySchema::getElementType),
                    codeModel.getSchemas().getDictionaries().stream().map(DictionarySchema::getElementType))
                    .collect(Collectors.toSet());
            schemasNotInUse.removeAll(schemasInUse);
        }
        if (!schemasNotInUse.isEmpty()) {
            // operation requests
            schemasInUse = codeModel.getOperationGroups().stream()
                    .flatMap(og -> og.getOperations().stream())
                    .map(Operation::getRequest)
                    .flatMap(r -> r.getParameters().stream())
                    .map(Parameter::getSchema)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            schemasNotInUse.removeAll(schemasInUse);
        }
        if (!schemasNotInUse.isEmpty()) {
            // operation responses
            schemasInUse = codeModel.getOperationGroups().stream()
                    .flatMap(og -> og.getOperations().stream())
                    .flatMap(o -> o.getResponses().stream())
                    .map(Response::getSchema)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            schemasNotInUse.removeAll(schemasInUse);
        }

        codeModel.getSchemas().getObjects().removeIf(s -> {
            boolean unused = schemasNotInUse.contains(s);
            if (unused) {
                logger.info("Remove unused flattened schema {}", Utils.getJavaName(s));
            }
            return unused;
        });

        return codeModel;
    }

    private static boolean hasFlattenedExtension(Schema schema) {
        return schema.getExtensions() != null && schema.getExtensions().isXmsFlattened();
    }
}
