/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.transformer;

import com.azure.autorest.extension.base.model.codemodel.ApiVersion;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.ConstantSchema;
import com.azure.autorest.extension.base.model.codemodel.ConstantValue;
import com.azure.autorest.extension.base.model.codemodel.Language;
import com.azure.autorest.extension.base.model.codemodel.Languages;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.model.codemodel.Protocol;
import com.azure.autorest.extension.base.model.codemodel.Protocols;
import com.azure.autorest.extension.base.model.codemodel.Request;
import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.model.codemodel.Schema;
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

    private static final Logger logger = LoggerFactory.getLogger(FluentTransformer.class);

    public FluentTransformer(FluentJavaSettings fluentJavaSettings) {
        this.fluentJavaSettings = fluentJavaSettings;
    }

    public CodeModel preTransform(CodeModel codeModel) {
        codeModel = removePagingLRO(codeModel);
        codeModel = normalizeAdditionalPropertiesSchemaName(codeModel);
        codeModel = addApiVersionParameter(codeModel);
        codeModel = addStartOperationForLROs(codeModel);
        return codeModel;
    }

    public CodeModel postTransform(CodeModel codeModel) {
        codeModel = new FlattenedTypeCleanup().process(codeModel);
        codeModel = new OperationNameNormalization().process(codeModel);
        codeModel = new ResourceTypeNormalization().process(codeModel);
        if (fluentJavaSettings.isResourcePropertyAsSubResource()) {
            codeModel = new ResourcePropertyNormalization().process(codeModel);
        }
        return codeModel;
    }

    public CodeModel removePagingLRO(CodeModel codeModel) {
        codeModel.getOperationGroups().stream().flatMap(og -> og.getOperations().stream())
                .filter(o -> hasLongRunningOperationExtension(o) && hasPaging(o))
                .forEach(o -> o.getExtensions().setXmsPageable(null));
        return codeModel;
    }

    /**
     * Provides better naming for unnamed additionalProperties type.
     *
     * @param codeModel Code model.
     * @return Processed code model.
     */
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

    /**
     * Adds API version parameter for all operations.
     *
     * Parameter is modeled differently based on numbers of API versions included in service.
     * If single API version, models it as a shared client parameter.
     * If multiple API versions, models it as constant method parameter.
     *
     * @param codeModel Code model.
     * @return Processed code model.
     */
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

            Parameter apiVersionParameter = parameter;
            o.getRequests().forEach(r -> r.getParameters().add(apiVersionParameter));
        });

        return codeModel;
    }

    /**
     * Adds start operation for LROs (e.g. BeginCreateFoo for CreateFoo LRO).
     *
     * @param codeModel Code model.
     * @return Processed code model.
     */
    protected CodeModel addStartOperationForLROs(CodeModel codeModel) {
        codeModel.getOperationGroups().forEach(operationGroup -> {
            if (operationGroup.getOperations() != null && operationGroup.getOperations().stream().anyMatch(FluentTransformer::hasLongRunningOperationExtension)) {
                List<Operation> operations = new ArrayList<>(operationGroup.getOperations());

                for (Operation operation : operationGroup.getOperations()) {
                    if (hasLongRunningOperationExtension(operation)) {
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

                        List<Request> newRequests = new ArrayList<>();
                        for (Request request : operation.getRequests()) {
                            Request newRequest = new Request();
                            Utils.shallowCopy(request, newRequest, Request.class, logger);

                            // Transformer will change request.parameters
                            newRequest.setParameters(new ArrayList<>(request.getParameters()));

                            newRequests.add(newRequest);
                        }
                        newOperation.setRequests(newRequests);

                        operations.add(newOperation);
                    }
                }

                operationGroup.setOperations(operations);
            }
        });
        return codeModel;
    }

    private static boolean hasLongRunningOperationExtension(Operation operation) {
        return operation.getExtensions() != null && operation.getExtensions().isXmsLongRunningOperation();
    }

    private static boolean hasPaging(Operation operation) {
        return operation.getExtensions() != null && operation.getExtensions().getXmsPageable() != null;
    }
}
