/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

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
}
