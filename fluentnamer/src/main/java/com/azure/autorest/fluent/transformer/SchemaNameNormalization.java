/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.transformer;

import com.azure.autorest.extension.base.model.codemodel.ChoiceSchema;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.OperationGroup;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.model.codemodel.Property;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.model.codemodel.SealedChoiceSchema;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.preprocessor.namer.CodeNamer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Normalize the names of some unnamed schemas.
 */
public class SchemaNameNormalization {

    private static final Logger logger = LoggerFactory.getLogger(SchemaNameNormalization.class);

    public CodeModel process(CodeModel codeModel) {
        codeModel = normalizeAdditionalPropertiesSchemaName(codeModel);
        codeModel = normalizeUnnamedChoiceSchema(codeModel);
        return codeModel;
    }

    protected CodeModel normalizeUnnamedChoiceSchema(CodeModel codeModel) {
        List<ChoiceSchema> unnamedChoiceSchemas = codeModel.getSchemas().getChoices().stream()
                .filter(s -> isUnnamed(Utils.getDefaultName(s)))
                .collect(Collectors.toList());
        if (!unnamedChoiceSchemas.isEmpty()) {
            unnamedChoiceSchemas.forEach(s -> renameSchema(codeModel, s));
        }

        List<SealedChoiceSchema> unnamedSealedChoiceSchemas = codeModel.getSchemas().getSealedChoices().stream()
                .filter(s -> isUnnamed(Utils.getDefaultName(s)))
                .collect(Collectors.toList());
        if (!unnamedSealedChoiceSchemas.isEmpty()) {
            unnamedSealedChoiceSchemas.forEach(s -> renameSchema(codeModel, s));
        }

        return codeModel;
    }

    private static boolean isUnnamed(String name) {
        final String prefix = "Enum";

        boolean unnamed = false;
        if (name.startsWith(prefix)) {
            String restName = name.substring(prefix.length());
            try {
                Integer.parseInt(restName);
                unnamed = true;
            } catch (NumberFormatException e) {
                // ignore
            }
        }
        return unnamed;
    }

    private static void renameSchema(CodeModel codeModel, Schema schema) {
        for (ObjectSchema compositeType : codeModel.getSchemas().getObjects()) {
            Optional<Property> property = compositeType.getProperties().stream()
                    .filter(p -> p.getSchema() == schema)
                    .findFirst();
            if (property.isPresent()) {
                String newName = Utils.getDefaultName(compositeType) + CodeNamer.toPascalCase(property.get().getSerializedName());
                logger.info("Rename schema from {} to {}, based on parent schema {}", Utils.getDefaultName(schema), newName, Utils.getDefaultName(compositeType));
                schema.getLanguage().getDefault().setName(newName);
                break;
            }
        }

        for (OperationGroup operationGroup : codeModel.getOperationGroups()) {
            boolean done = false;
            for (Operation operation : operationGroup.getOperations()) {
                Optional<Parameter> parameter = Stream.concat(operation.getParameters().stream(), operation.getRequests().stream().flatMap(r -> r.getParameters().stream()))
                        .filter(p -> p.getSchema() == schema)
                        .findFirst();
                if (parameter.isPresent()) {
                    String newName = Utils.getDefaultName(operationGroup) + CodeNamer.toPascalCase(Utils.getDefaultName(parameter.get()));
                    logger.info("Rename schema from {} to {}, based on operation group {}", Utils.getDefaultName(schema), newName, Utils.getDefaultName(operationGroup));
                    schema.getLanguage().getDefault().setName(newName);
                    done = true;
                    break;
                }
            }
            if (done) {
                break;
            }
        }
    }

    protected CodeModel normalizeAdditionalPropertiesSchemaName(CodeModel codeModel) {
        final String prefix = "Components";
        final String postfix = "Additionalproperties";

        codeModel.getSchemas().getDictionaries().stream()
                .filter(s -> s.getElementType() instanceof ObjectSchema)
                .filter(s -> Utils.getDefaultName(s.getElementType()).startsWith(prefix) && Utils.getDefaultName(s.getElementType()).endsWith(postfix))
                .forEach(dict -> {
                    Schema schema = dict.getElementType();
                    String name = Utils.getDefaultName(schema);
                    String newName = Utils.getDefaultName(dict);
                    schema.getLanguage().getDefault().setName(newName);
                    logger.info("Rename schema default name, from {} to {}", name, newName);
                });

        return codeModel;
    }
}
