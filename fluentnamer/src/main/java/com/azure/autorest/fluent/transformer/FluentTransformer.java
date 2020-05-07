/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.transformer;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.ConstantSchema;
import com.azure.autorest.extension.base.model.codemodel.Language;
import com.azure.autorest.extension.base.model.codemodel.Languages;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.Request;
import com.azure.autorest.extension.base.model.extensionmodel.XmsExtensions;
import com.azure.autorest.fluent.util.FluentJavaSettings;
import com.azure.autorest.fluent.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class FluentTransformer {

    private final FluentJavaSettings fluentJavaSettings;

    private static final Logger logger = LoggerFactory.getLogger(FluentTransformer.class);

    public FluentTransformer(FluentJavaSettings fluentJavaSettings) {
        this.fluentJavaSettings = fluentJavaSettings;
    }

    public CodeModel preTransform(CodeModel codeModel) {
        codeModel = removePagingLRO(codeModel);
        codeModel = new SchemaNameNormalization(fluentJavaSettings.getNamingOverride()).process(codeModel);
        codeModel = new ConstantSchemaOptimization().process(codeModel);
        codeModel = new NamingConflictResolver().process(codeModel);
        codeModel = normalizeApiVersionParameter(codeModel);
        codeModel = addStartOperationForLROs(codeModel);
        return codeModel;
    }

    public CodeModel postTransform(CodeModel codeModel) {
        codeModel = new OperationNameNormalization().process(codeModel);
        codeModel = new ResourceTypeNormalization().process(codeModel);
        codeModel = new ManagementErrorTypeNormalization().process(codeModel);
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
     * Sets proper ClientDefaultValue to api-version parameters.
     *
     * @param codeModel Code model.
     * @return Processed code model.
     */
    protected CodeModel normalizeApiVersionParameter(CodeModel codeModel) {
        codeModel.getGlobalParameters().stream()
                .filter(p -> "api-version".equals(p.getLanguage().getDefault().getSerializedName()))
                .forEach(p -> {
                    if (p.getSchema() instanceof ConstantSchema) {
                        p.setClientDefaultValue(((ConstantSchema) p.getSchema()).getValue().getValue().toString());
                    }
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
