/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.transformer;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.ConstantSchema;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.fluent.util.FluentJavaSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        return codeModel;
    }

    public CodeModel postTransform(CodeModel codeModel) {
        codeModel = new OperationNameNormalization().process(codeModel);
        codeModel = new ResourceTypeNormalization().process(codeModel);
        codeModel = new ErrorTypeNormalization().process(codeModel);
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

    private static boolean hasLongRunningOperationExtension(Operation operation) {
        return operation.getExtensions() != null && operation.getExtensions().isXmsLongRunningOperation();
    }

    private static boolean hasPaging(Operation operation) {
        return operation.getExtensions() != null && operation.getExtensions().getXmsPageable() != null;
    }
}
