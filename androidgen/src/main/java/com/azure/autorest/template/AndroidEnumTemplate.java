// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.template;

import com.azure.autorest.template.EnumTemplate;
import com.azure.autorest.model.javamodel.JavaFile;

public class AndroidEnumTemplate extends EnumTemplate {
    private static AndroidEnumTemplate _instance = new AndroidEnumTemplate();

    private AndroidEnumTemplate() {
    }

    public static AndroidEnumTemplate getInstance() {
        return _instance;
    }

    @Override
    protected void addDeclareImports(JavaFile javaFile) {
        javaFile.declareImport("java.util.Collection", "com.fasterxml.jackson.annotation.JsonCreator", "com.azure.android.core.util.ExpandableStringEnum");
    }
}