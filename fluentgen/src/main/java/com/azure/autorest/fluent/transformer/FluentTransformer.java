/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.transformer;

import com.azure.autorest.extension.base.model.codemodel.ApiVersion;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.ConstantSchema;
import com.azure.autorest.extension.base.model.codemodel.ConstantValue;
import com.azure.autorest.extension.base.model.codemodel.DictionarySchema;
import com.azure.autorest.extension.base.model.codemodel.Language;
import com.azure.autorest.extension.base.model.codemodel.Languages;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.model.codemodel.Protocol;
import com.azure.autorest.extension.base.model.codemodel.Protocols;
import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.model.codemodel.StringSchema;
import com.azure.autorest.extension.base.model.extensionmodel.XmsExtensions;
import com.azure.autorest.fluent.util.FluentJavaSettings;
import com.azure.autorest.fluent.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FluentTransformer {

    private final FluentJavaSettings fluentJavaSettings;

    private final static Logger logger = LoggerFactory.getLogger(FluentTransformer.class);

    public FluentTransformer(FluentJavaSettings fluentJavaSettings) {
        this.fluentJavaSettings = fluentJavaSettings;
    }

    public CodeModel preTransform(CodeModel codeModel) {
        codeModel = normalizeAdditionalPropertiesSchemaName(codeModel);
        codeModel = addApiVersionParameter(codeModel);
        codeModel = addLongRunningOperations(codeModel);
        return codeModel;
    }

    public CodeModel postTransform(CodeModel codeModel) {
        codeModel = new OperationNameNormalization().process(codeModel);
        codeModel = new ResourceTypeNormalization().process(codeModel);
        return codeModel;
    }

    protected CodeModel normalizeAdditionalPropertiesSchemaName(CodeModel codeModel) {
        final String prefix = "components·schemas·";
        final String postfix = "·additionalproperties";

        codeModel.getSchemas().getDictionaries().stream()
                .map(DictionarySchema::getElementType)
                .filter(s -> s instanceof ObjectSchema)
                .filter(s -> Utils.getDefaultName(s).startsWith(prefix) && Utils.getDefaultName(s).endsWith(postfix))
                .forEach(s -> {
                    String name = Utils.getDefaultName(s);
                    String newName = name.substring(prefix.length(), name.length() - postfix.length());
                    s.getLanguage().getDefault().setName(newName);
                    logger.info("Rename schema default name, from {} to {}", name, newName);
                });

        return codeModel;
    }

    protected CodeModel addApiVersionParameter(CodeModel codeModel) {
        final Language language = new Language();
        language.setSerializedName("api-version");
        language.setName(language.getSerializedName());
        language.setDescription("The API version to use for this operation.");

        final StringSchema schema = new StringSchema();
        schema.setLanguage(new Languages());
        schema.getLanguage().setDefault(language);

        Set<String> apiVersions = codeModel.getOperationGroups().stream()
                .flatMap(og -> og.getOperations().stream())
                .flatMap(o -> o.getApiVersions().stream())
                .map(ApiVersion::getVersion)
                .collect(Collectors.toSet());

        logger.info("Api Version {}", apiVersions);

        final boolean singleApiVersion = apiVersions.size() == 1;
        final Map<String, Parameter> apiVersionParameters = new HashMap<>();

        codeModel.getOperationGroups().stream().flatMap(og -> og.getOperations().stream()).forEach(o -> {
            final String apiVersion = o.getApiVersions().get(0).getVersion();

            Parameter parameter = apiVersionParameters.get(apiVersion);
            if (parameter == null) {
                parameter = new Parameter();
                if (singleApiVersion) {
                    parameter.setSchema(schema);
                    parameter.setImplementation(Parameter.ImplementationLocation.CLIENT);
                } else {
                    final ConstantSchema constantSchema = new ConstantSchema();
                    constantSchema.setLanguage(new Languages());
                    constantSchema.getLanguage().setDefault(language);
                    constantSchema.setValueType(schema);
                    constantSchema.setValue(new ConstantValue());
                    constantSchema.getValue().setValue(apiVersion);

                    parameter.setSchema(constantSchema);
                    parameter.setImplementation(Parameter.ImplementationLocation.METHOD);
                }
                parameter.setRequired(false);
                parameter.setLanguage(new Languages());
                parameter.getLanguage().setDefault(language);
                parameter.setProtocol(new Protocols());
                parameter.getProtocol().setHttp(new Protocol());
                parameter.getProtocol().getHttp().setIn(RequestParameterLocation.Query);

                parameter.setClientDefaultValue(apiVersion);

                apiVersionParameters.put(apiVersion, parameter);
            }

            o.getRequest().getParameters().add(parameter);
        });

        return codeModel;
    }

    protected CodeModel addLongRunningOperations(CodeModel codeModel) {
        codeModel.getOperationGroups().forEach(operationGroup -> {
            if (operationGroup.getOperations() != null && operationGroup.getOperations().stream().anyMatch(FluentTransformer::isLongRunningOperationExtension)) {
                List<Operation> operations = new ArrayList<>(operationGroup.getOperations());

                for (Operation operation : operationGroup.getOperations()) {
                    if (isLongRunningOperationExtension(operation)) {
                        Operation newOperation = new Operation();
                        Utils.shallowCopy(operation, newOperation, Operation.class, logger);

                        Language updatedDefault = new Language();
                        Utils.shallowCopy(operation.getLanguage().getDefault(), updatedDefault, Language.class, logger);
                        updatedDefault.setName("Begin" + operation.getLanguage().getDefault().getName());

                        Languages updatedLanguages = new Languages();
                        Utils.shallowCopy(operation.getLanguage(), updatedLanguages, Languages.class, logger);
                        updatedLanguages.setDefault(updatedDefault);
                        newOperation.setLanguage(updatedLanguages);

                        XmsExtensions updatedExtensions = new XmsExtensions();
                        Utils.shallowCopy(operation.getExtensions(), updatedExtensions, XmsExtensions.class, logger);
                        updatedExtensions.setXmsLongRunningOperation(false);
                        newOperation.setExtensions(updatedExtensions);

                        operations.add(newOperation);
                    }
                }

                operationGroup.setOperations(operations);
            }
        });
        return codeModel;
    }

    private static boolean isLongRunningOperationExtension(Operation compositeType) {
        return compositeType.getExtensions() != null && compositeType.getExtensions().isXmsLongRunningOperation();
    }
}
