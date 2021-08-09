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
import com.azure.autorest.extension.base.model.codemodel.Request;
import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.model.extensionmodel.XmsExtensions;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.util.FluentJavaSettings;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.fluentnamer.FluentNamer;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FluentTransformer {

    private final FluentJavaSettings fluentJavaSettings;

    private static final Logger logger = new PluginLogger(FluentNamer.getPluginInstance(), FluentTransformer.class);

    public FluentTransformer(FluentJavaSettings fluentJavaSettings) {
        this.fluentJavaSettings = fluentJavaSettings;
    }

    public CodeModel preTransform(CodeModel codeModel) {
        codeModel = normalizeParameterLocation(codeModel);
        codeModel = renameUngroupedOperationGroup(codeModel, fluentJavaSettings);
        codeModel = new SchemaNameNormalization(fluentJavaSettings.getNamingOverride()).process(codeModel);
        codeModel = new ConstantSchemaOptimization().process(codeModel);
        codeModel = new NamingConflictResolver().process(codeModel);
        codeModel = renameHostParameter(codeModel);
        //codeModel = addStartOperationForLROs(codeModel);
        return codeModel;
    }

    public CodeModel postTransform(CodeModel codeModel) {
        codeModel = new SchemaRenamer(fluentJavaSettings.getJavaNamesForRenameModel()).process(codeModel);
        codeModel = new OperationNameNormalization().process(codeModel);
        codeModel = new ResourceTypeNormalization().process(codeModel);
        codeModel = new ErrorTypeNormalization().process(codeModel);
        codeModel = new ResponseStatusCodeNormalization().process(codeModel);
        if (fluentJavaSettings.isResourcePropertyAsSubResource()) {
            codeModel = new ResourcePropertyNormalization().process(codeModel);
        }
        codeModel = new SchemaCleanup(fluentJavaSettings.getJavaNamesForPreserveModel()).process(codeModel);
        return codeModel;
    }

    protected CodeModel normalizeParameterLocation(CodeModel codeModel) {
        List<Parameter> modifiedGlobalParameters = new ArrayList<>();
        codeModel.getGlobalParameters().stream().filter(p -> p.getImplementation() == Parameter.ImplementationLocation.CLIENT
                && p.getProtocol() != null && p.getProtocol().getHttp() != null).forEach(p -> {
                    String serializedName = p.getLanguage().getDefault().getSerializedName();
                    if ((p.getProtocol().getHttp().getIn() == RequestParameterLocation.Path && !"subscriptionId".equalsIgnoreCase(serializedName))
                            || (p.getProtocol().getHttp().getIn() == RequestParameterLocation.Query && !"api-version".equalsIgnoreCase(serializedName))) {
                        logger.warn("Modify parameter '{}' implementation from CLIENT to METHOD", serializedName);
                        p.setImplementation(Parameter.ImplementationLocation.METHOD);
                        modifiedGlobalParameters.add(p);
                    }
                });
        if (!modifiedGlobalParameters.isEmpty()) {
            // add now METHOD parameter to signature parameters
            codeModel.getOperationGroups().stream().flatMap(og -> og.getOperations().stream()).forEach(o -> {
                List<Parameter> parameters = o.getParameters();
                List<Parameter> signatureParameters = o.getSignatureParameters();
                for (Parameter parameter : modifiedGlobalParameters) {
                    if (!signatureParameters.contains(parameter) && parameters.contains(parameter)) {
                        signatureParameters.add(parameter);
                    }
                }
            });
        }
        return codeModel;
    }

    protected CodeModel renameUngroupedOperationGroup(CodeModel codeModel, FluentJavaSettings settings) {
        final String nameForUngroupedOperations = getNameForUngroupedOperations(codeModel, settings);
        if (nameForUngroupedOperations == null) {
            return codeModel;
        }

        codeModel.getOperationGroups().stream()
                .filter(og -> Utils.getDefaultName(og) == null || Utils.getDefaultName(og).isEmpty())
                .forEach(og -> {
                    logger.info("Rename ungrouped operation group to '{}'", nameForUngroupedOperations);
                    og.set$key(nameForUngroupedOperations);
                    og.getLanguage().getDefault().setName(nameForUngroupedOperations);
                });
        return codeModel;
    }

    private static String getNameForUngroupedOperations(CodeModel codeModel, FluentJavaSettings settings) {
        String nameForUngroupOperations = null;
        if (settings.getNameForUngroupedOperations().isPresent()) {
            nameForUngroupOperations = settings.getNameForUngroupedOperations().get();
        } else if (JavaSettings.getInstance().isFluentLite()) {
            nameForUngroupOperations = "ResourceProvider";

            Set<String> operationGroupNames = codeModel.getOperationGroups().stream()
                    .map(Utils::getDefaultName)
                    .collect(Collectors.toSet());
            if (operationGroupNames.contains(nameForUngroupOperations)) {
                nameForUngroupOperations += "Operation";
            }
        }
        return nameForUngroupOperations;
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
