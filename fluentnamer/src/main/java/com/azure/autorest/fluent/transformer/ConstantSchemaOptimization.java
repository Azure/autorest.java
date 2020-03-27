/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.transformer;

import com.azure.autorest.extension.base.model.codemodel.ChoiceValue;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.ConstantSchema;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.model.codemodel.SealedChoiceSchema;
import com.azure.autorest.fluent.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConstantSchemaOptimization {

    private static final Logger logger = LoggerFactory.getLogger(SchemaNameNormalization.class);

    public CodeModel process(CodeModel codeModel) {
        Set<ConstantSchema> constantSchemas = new HashSet<>(codeModel.getSchemas().getConstants());
        if (!constantSchemas.isEmpty()) {
            Map<ConstantSchema, SealedChoiceSchema> convertedChoiceSchemas = new HashMap<>();

            codeModel.getOperationGroups().stream()
                    .flatMap(og -> og.getOperations().stream())
                    .forEach(o -> {
                        o.getParameters().stream()
                                .filter(p -> !p.isRequired() && p.getSchema() instanceof ConstantSchema)
                                .forEach(p -> {
                                    ConstantSchema constantSchema = (ConstantSchema) p.getSchema();
                                    SealedChoiceSchema sealedChoiceSchema = convertedChoiceSchemas.computeIfAbsent(constantSchema,
                                            ConstantSchemaOptimization::convertToChoiceSchema);
                                    p.setSchema(sealedChoiceSchema);

                                    o.getSignatureParameters().add(p);
                                });

                        o.getRequests().forEach(r -> {
                            r.getParameters().stream()
                                    .filter(p -> !p.isRequired() && p.getSchema() instanceof ConstantSchema)
                                    .forEach(p -> {
                                        ConstantSchema constantSchema = (ConstantSchema) p.getSchema();
                                        SealedChoiceSchema sealedChoiceSchema = convertedChoiceSchemas.computeIfAbsent(constantSchema,
                                                ConstantSchemaOptimization::convertToChoiceSchema);
                                        p.setSchema(sealedChoiceSchema);

                                        r.getSignatureParameters().add(p);
                                    });
                        });
                    });

            codeModel.getSchemas().getObjects().stream()
                    .flatMap(s -> s.getProperties().stream())
                    .filter(p -> !p.isRequired() && p.getSchema() instanceof ConstantSchema)
                    .forEach(p -> {
                        ConstantSchema constantSchema = (ConstantSchema) p.getSchema();
                        SealedChoiceSchema sealedChoiceSchema = convertedChoiceSchemas.computeIfAbsent(constantSchema,
                                ConstantSchemaOptimization::convertToChoiceSchema);
                        p.setSchema(sealedChoiceSchema);
                    });

            codeModel.getSchemas().getSealedChoices().addAll(convertedChoiceSchemas.values());
        }
        return codeModel;
    }

    private static SealedChoiceSchema convertToChoiceSchema(ConstantSchema constantSchema) {
        SealedChoiceSchema sealedChoiceSchema = new SealedChoiceSchema();
        sealedChoiceSchema.setType(Schema.AllSchemaTypes.SEALED_CHOICE);
        sealedChoiceSchema.setChoiceType(constantSchema.getValueType());
        sealedChoiceSchema.setDefaultValue(constantSchema.getDefaultValue());
        sealedChoiceSchema.setLanguage(constantSchema.getLanguage());

        ChoiceValue choice = new ChoiceValue();
        choice.setValue(constantSchema.getValue().getValue().toString());
        choice.setLanguage(constantSchema.getValue().getLanguage());
        sealedChoiceSchema.setChoices(Collections.singletonList(choice));

        logger.info("Create sealed choice {}", Utils.getDefaultName(sealedChoiceSchema));

        return sealedChoiceSchema;
    }
}
