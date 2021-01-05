/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.transformer;

import com.azure.autorest.extension.base.model.codemodel.ArraySchema;
import com.azure.autorest.extension.base.model.codemodel.ChoiceSchema;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.Metadata;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.OperationGroup;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.model.codemodel.Property;
import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.model.codemodel.SealedChoiceSchema;
import com.azure.autorest.extension.base.model.codemodel.Value;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.fluentnamer.FluentNamer;
import com.azure.autorest.preprocessor.namer.CodeNamer;
import com.azure.core.util.CoreUtils;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Normalize the names of some unnamed schemas.
 */
public class SchemaNameNormalization {

    private static final Logger logger = new PluginLogger(FluentNamer.getPluginInstance(), SchemaNameNormalization.class);

    private final Map<String, String> nameOverridePlan = new HashMap<>();

    public SchemaNameNormalization(Map<String, String> nameOverridePlan) {
        nameOverridePlan.forEach((k, v) -> {
            char[] kCharArray = k.toCharArray();
            char[] vCharArray = v.toCharArray();

            kCharArray[0] = Character.toLowerCase(kCharArray[0]);
            vCharArray[0] = Character.toLowerCase(vCharArray[0]);
            this.nameOverridePlan.put(new String(kCharArray), new String(vCharArray));

            kCharArray[0] = Character.toUpperCase(kCharArray[0]);
            vCharArray[0] = Character.toUpperCase(vCharArray[0]);
            this.nameOverridePlan.put(new String(kCharArray), new String(vCharArray));
        });
    }

    public CodeModel process(CodeModel codeModel) {
        codeModel = namingOverride(codeModel);
        codeModel = normalizeUnnamedAdditionalProperties(codeModel);
        codeModel = normalizeUnnamedBaseType(codeModel);
        codeModel = normalizeUnnamedChoiceSchema(codeModel);
        codeModel = normalizeUnnamedRequestBody(codeModel);
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
                logger.warn("Rename schema from '{}' to '{}', based on parent schema '{}'", Utils.getDefaultName(schema), newName, Utils.getDefaultName(compositeType));
                schema.getLanguage().getDefault().setName(newName);
                break;
            }
        }

        boolean done = false;
        for (OperationGroup operationGroup : codeModel.getOperationGroups()) {
            for (Operation operation : operationGroup.getOperations()) {
                Optional<Parameter> parameter = Stream.concat(operation.getParameters().stream(), operation.getRequests().stream().flatMap(r -> r.getParameters().stream()))
                        .filter(p -> p.getSchema() == schema)
                        .findFirst();
                if (parameter.isPresent()) {
                    String newName = Utils.getDefaultName(operationGroup) + CodeNamer.toPascalCase(Utils.getDefaultName(parameter.get()));
                    logger.warn("Rename schema from '{}' to '{}', based on operation group '{}'", Utils.getDefaultName(schema), newName, Utils.getDefaultName(operationGroup));
                    schema.getLanguage().getDefault().setName(newName);
                    done = true;
                    break;
                }
            }
            if (done) {
                break;
            }
        }
        if (!done) {
            for (OperationGroup operationGroup : codeModel.getOperationGroups()) {
                for (Operation operation : operationGroup.getOperations()) {
                    Optional<Parameter> parameter = Stream.concat(operation.getParameters().stream(), operation.getRequests().stream().flatMap(r -> r.getParameters().stream()))
                            .filter(p -> (p.getSchema() instanceof ArraySchema) && ((ArraySchema) p.getSchema()).getElementType() == schema)
                            .findFirst();
                    if (parameter.isPresent()) {
                        String newName = Utils.getDefaultName(operationGroup) + CodeNamer.toPascalCase(Utils.getDefaultName(parameter.get()));
                        logger.warn("Rename schema from '{}' to '{}', based on operation group '{}'", Utils.getDefaultName(schema), newName, Utils.getDefaultName(operationGroup));
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
    }

    protected CodeModel normalizeUnnamedAdditionalProperties(CodeModel codeModel) {
        // unnamed type is named by modelerfour as e.g. ComponentsQit0EtSchemasManagedclusterpropertiesPropertiesIdentityprofileAdditionalproperties

        final String prefix = "Components";
        final String postfix = "Additionalproperties";

        codeModel.getSchemas().getDictionaries().stream()
                .filter(s -> s.getElementType() instanceof ObjectSchema)
                .forEach(dict -> {
                    ObjectSchema schema = (ObjectSchema) dict.getElementType();

                    List<Schema> subtypes = new ArrayList<>();
                    subtypes.add(schema);
                    if (schema.getChildren() != null && schema.getChildren().getAll() != null) {
                        subtypes.addAll(schema.getChildren().getAll());
                    }

                    for (Schema type : subtypes) {
                        String name = Utils.getDefaultName(type);
                        if (name.startsWith(prefix) && name.endsWith(postfix)) {
                            String newName = Utils.getDefaultName(dict);
                            type.getLanguage().getDefault().setName(newName);
                            logger.warn("Rename schema default name, from '{}' to '{}'", name, newName);
                        }
                    }
                });

        return codeModel;
    }

    protected CodeModel normalizeUnnamedBaseType(CodeModel codeModel) {
        // unnamed base type is named by modelerfour as e.g.Components1Q1Og48SchemasManagedclusterAllof1

        final String prefix = "Components";
        final String allOf = "Allof";

        codeModel.getSchemas().getObjects().forEach(schema -> {
            String name = Utils.getDefaultName(schema);
            if (schema.getChildren() != null && !CoreUtils.isNullOrEmpty(schema.getChildren().getImmediate())
                    && name.startsWith(prefix) && name.contains(allOf)) {
                int index = name.lastIndexOf(allOf) + allOf.length();
                boolean unnamed = false;
                String restName = name.substring(index);
                if (restName.isEmpty()) {
                    unnamed = true;
                } else {
                    try {
                        Integer.parseInt(restName);
                        unnamed = true;
                    } catch (NumberFormatException e) {
                        // ignore
                    }
                }

                if (unnamed) {
                    Schema firstChild = schema.getChildren().getImmediate().iterator().next();
                    String newName = "Base" + Utils.getDefaultName(firstChild);
                    schema.getLanguage().getDefault().setName(newName);
                    logger.warn("Rename schema default name, from '{}' to '{}'", name, newName);
                }
            }
        });

        return codeModel;
    }

    protected CodeModel normalizeUnnamedRequestBody(CodeModel codeModel) {
        // unnamed request body is named by modelerfour as e.g. Paths1Ezr0XyApplicationsApplicationIdMicrosoftGraphGetmembergroupsPostRequestbodyContentApplicationJsonSchema

        final String prefix = "Paths";
        final String postfix = "Schema";
        final String requestBody = "Requestbody";

        codeModel.getOperationGroups().forEach(og -> {
            og.getOperations().forEach(operation -> {
                operation.getRequests().forEach(request -> {
                    Optional<Schema> bodySchemaOpt = request.getParameters().stream()
                            .filter(p -> p.getSchema() != null && p.getProtocol() != null && p.getProtocol().getHttp() != null && p.getProtocol().getHttp().getIn() == RequestParameterLocation.Body)
                            .map(Value::getSchema)
                            .findFirst();
                    if (bodySchemaOpt.isPresent()) {
                        Schema schema = bodySchemaOpt.get();
                        String name = Utils.getDefaultName(schema);
                        if (name.startsWith(prefix) && name.endsWith(postfix) && name.contains(requestBody)) {
                            String newName = Utils.getDefaultName(og) + Utils.getDefaultName(operation) + "RequestBody";
                            schema.getLanguage().getDefault().setName(newName);
                            logger.warn("Rename schema default name, from '{}' to '{}'", name, newName);
                        }
                    }
                });
            });
        });

        return codeModel;
    }

    protected CodeModel namingOverride(CodeModel codeModel) {
        if (!nameOverridePlan.isEmpty()) {
            overrideName(codeModel);

            codeModel.getSchemas().getObjects().forEach(this::overrideName);
            codeModel.getSchemas().getObjects().stream()
                    .flatMap(o -> o.getProperties().stream())
                    .forEach(this::overrideName);

            codeModel.getSchemas().getAnds().forEach(this::overrideName);
            codeModel.getSchemas().getChoices().forEach(this::overrideName);
            codeModel.getSchemas().getSealedChoices().forEach(this::overrideName);
            codeModel.getSchemas().getDictionaries().forEach(this::overrideName);

            codeModel.getOperationGroups().forEach(this::overrideName);
            codeModel.getOperationGroups().stream()
                    .flatMap(og -> og.getOperations().stream())
                    .forEach(this::overrideName);
            codeModel.getOperationGroups().stream()
                    .flatMap(og -> og.getOperations().stream())
                    .flatMap(o -> o.getParameters().stream())
                    .forEach(this::overrideName);
            codeModel.getOperationGroups().stream()
                    .flatMap(og -> og.getOperations().stream())
                    .flatMap(o -> o.getRequests().stream())
                    .flatMap(r -> r.getParameters().stream())
                    .forEach(this::overrideName);

            // hack, http header is case insensitive
            codeModel.getOperationGroups().stream()
                    .flatMap(og -> og.getOperations().stream())
                    .flatMap(o -> o.getResponses().stream())
                    .filter(r -> r.getProtocol().getHttp().getHeaders() != null)
                    .flatMap(r -> r.getProtocol().getHttp().getHeaders().stream())
                    .forEach(h -> {
                        String name = h.getHeader();
                        String newName = overrideName(name);
                        if (!name.equals(newName)) {
                            if (name.equalsIgnoreCase(newName)) {
                                logger.info("Override response header, from '{}' to '{}'", name, newName);
                                h.setHeader(newName);
                            } else {
                                logger.info("Abort override response header, from '{}' to '{}'", name, newName);
                            }
                        }
                    });
        }
        return codeModel;
    }

    private void overrideName(Metadata m) {
        String name = Utils.getDefaultName(m);
        String newName = overrideName(name);
        if (!name.equals(newName)) {
            m.getLanguage().getDefault().setName(newName);
            logger.info("Override default name, from '{}' to '{}'", name, newName);
        }
    }

    private String overrideName(String name) {
        String newName = name;
        for (Map.Entry<String, String> entry : nameOverridePlan.entrySet()) {
            int index = newName.indexOf(entry.getKey());
            if (index >= 0) {
                int endIndex = index + entry.getKey().length();
                boolean replace = true;
                if (index > 0 && isSameCase(newName.charAt(index - 1), newName.charAt(index))) {
                    replace = false;
                } else if (endIndex < newName.length() && isSameCase(newName.charAt(endIndex - 1), newName.charAt(endIndex))) {
                    replace = false;
                }

                if (replace) {
                    newName = newName.replace(entry.getKey(), entry.getValue());
                }
            }
        }
        return newName;
    }

    private static boolean isSameCase(char c1, char c2) {
        return (Character.isUpperCase(c1) && Character.isUpperCase(c2))
                || (Character.isLowerCase(c1) && Character.isLowerCase(c2));
    }
}
