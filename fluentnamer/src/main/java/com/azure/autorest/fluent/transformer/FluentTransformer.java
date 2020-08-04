/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.transformer;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
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
        if (fluentJavaSettings.getNameForUngroupedOperations().isPresent()) {
            codeModel = renameUngroupedOperationGroup(codeModel, fluentJavaSettings.getNameForUngroupedOperations().get());
        }
        codeModel = new SchemaNameNormalization(fluentJavaSettings.getNamingOverride()).process(codeModel);
        codeModel = new ConstantSchemaOptimization().process(codeModel);
        codeModel = new NamingConflictResolver().process(codeModel);
        codeModel = renameHostParameter(codeModel);
        //codeModel = addStartOperationForLROs(codeModel);
        return codeModel;
    }

    public CodeModel postTransform(CodeModel codeModel) {
        codeModel = new OperationNameNormalization().process(codeModel);
        codeModel = new ResourceTypeNormalization().process(codeModel);
        codeModel = new ErrorTypeNormalization().process(codeModel);
        codeModel = new ResponseStatusCodeNormalization().process(codeModel);
        if (fluentJavaSettings.isResourcePropertyAsSubResource()) {
            codeModel = new ResourcePropertyNormalization().process(codeModel);
        }
        codeModel = new SchemaCleanup().process(codeModel);
        return codeModel;
    }

    public CodeModel renameUngroupedOperationGroup(CodeModel codeModel, String nameForUngroupOperations) {
        codeModel.getOperationGroups().stream()
                .filter(og -> Utils.getDefaultName(og) == null || Utils.getDefaultName(og).isEmpty())
                .forEach(og -> {
                    logger.info("Rename ungrouped operation group to {}", nameForUngroupOperations);
                    og.set$key(nameForUngroupOperations);
                    og.getLanguage().getDefault().setName(nameForUngroupOperations);
                });
        return codeModel;
    }

    /**
     * Renames $host to endpoint.
     *
     * @param codeModel Code model.
     * @return Processed code model.
     */
    protected CodeModel renameHostParameter(CodeModel codeModel) {
        codeModel.getGlobalParameters().stream()
                .filter(p -> "$host".equals(p.getLanguage().getDefault().getSerializedName()))
                .forEach(p -> {
                    p.getLanguage().getDefault().setName("endpoint");
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
                        updatedDefault.setName("Begin" + operation.getLanguage().getDefault().getName() + "WithoutPolling");

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
