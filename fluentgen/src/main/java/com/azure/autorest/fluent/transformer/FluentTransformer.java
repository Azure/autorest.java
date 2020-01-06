/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.transformer;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.Language;
import com.azure.autorest.extension.base.model.codemodel.Languages;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.model.codemodel.Protocol;
import com.azure.autorest.extension.base.model.codemodel.Protocols;
import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.model.codemodel.StringSchema;
import com.azure.autorest.extension.base.model.extensionmodel.XmsExtensions;
import com.azure.autorest.fluent.FluentJavaSettings;
import com.azure.autorest.fluent.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class FluentTransformer {

    private final FluentJavaSettings fluentJavaSettings;

    private final static Logger logger = LoggerFactory.getLogger(FluentTransformer.class);

    public FluentTransformer(FluentJavaSettings fluentJavaSettings) {
        this.fluentJavaSettings = fluentJavaSettings;
    }

    public CodeModel preTransform(CodeModel codeModel) {
        codeModel = addApiVersionParameter(codeModel);
        //codeModel = modifySubscriptionIdParameter(codeModel);
        codeModel = addLongRunningOperations(codeModel);
        return codeModel;
    }

    public CodeModel postTransform(CodeModel codeModel) {
        codeModel = new ClientFlatten(fluentJavaSettings).process(codeModel);
        return codeModel;
    }

    protected CodeModel addApiVersionParameter(CodeModel codeModel) {
        final Language language = new Language();
        language.setName("api-version");
        language.setDescription("The API version to use for this operation.");

        final StringSchema schema = new StringSchema();
        schema.setLanguage(new Languages());
        schema.getLanguage().setDefault(language);

        codeModel.getOperationGroups().stream().flatMap(og -> og.getOperations().stream()).forEach(o -> {
            final Parameter parameter = new Parameter();
            parameter.setSchema(schema);
            parameter.setImplementation(Parameter.ImplementationLocation.CLIENT);
            parameter.setRequired(true);
            parameter.setLanguage(new Languages());
            parameter.getLanguage().setDefault(language);
            parameter.setProtocol(new Protocols());
            parameter.getProtocol().setHttp(new Protocol());
            parameter.getProtocol().getHttp().setIn(RequestParameterLocation.Query);

            if (o.getApiVersions() != null && !o.getApiVersions().isEmpty()) {
                parameter.setClientDefaultValue(o.getApiVersions().get(0).getVersion());
            }

            final List<Parameter> parameters = new ArrayList<>(o.getRequest().getParameters());
            parameters.add(parameter);
            o.getRequest().setParameters(parameters);
        });

        return codeModel;
    }

    protected CodeModel modifySubscriptionIdParameter(CodeModel codeModel) {
        codeModel.getOperationGroups().forEach(g -> g.getOperations().forEach(operation -> {
            if (operation.getRequest().getParameters() != null) {
                operation.getRequest().getParameters().forEach(p -> {
                    if ("subscriptionId".equals(p.getLanguage().getDefault().getName())) {
                        p.setImplementation(Parameter.ImplementationLocation.CLIENT);
                    }
                });
            };
        }));

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
