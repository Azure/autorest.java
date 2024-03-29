// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.transformer;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.preprocessor.Preprocessor;

public class ConstantSchemaOptimization {

    public CodeModel process(CodeModel codeModel) {
        return Preprocessor.convertOptionalConstantsToEnum(codeModel);
    }
}
