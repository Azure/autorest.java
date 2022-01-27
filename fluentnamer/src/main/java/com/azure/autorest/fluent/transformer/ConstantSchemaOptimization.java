// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.transformer;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluentnamer.FluentNamer;
import com.azure.autorest.preprocessor.Preprocessor;
import org.slf4j.Logger;

public class ConstantSchemaOptimization {

    private static final Logger logger = new PluginLogger(FluentNamer.getPluginInstance(), SchemaNameNormalization.class);

    public CodeModel process(CodeModel codeModel) {
        return Preprocessor.convertOptionalConstantsToEnum(codeModel);
    }
}
