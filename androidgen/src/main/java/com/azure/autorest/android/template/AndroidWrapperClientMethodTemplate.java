// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.android.template;

import com.azure.autorest.model.javamodel.JavaType;
import com.azure.autorest.template.WrapperClientMethodTemplate;

public class AndroidWrapperClientMethodTemplate extends WrapperClientMethodTemplate {

    private static final WrapperClientMethodTemplate INSTANCE = new AndroidWrapperClientMethodTemplate();

    public static WrapperClientMethodTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    protected void addGeneratedAnnotation(JavaType typeBlock) {
    }

    @Override
    protected boolean isPartialUpdateSupported() {
        return false;
    }
}
