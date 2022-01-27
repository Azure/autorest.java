// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.android.template;

import com.azure.autorest.template.EnumTemplate;

public class AndroidEnumTemplate extends EnumTemplate {

    private static EnumTemplate _instance = new AndroidEnumTemplate();

    private AndroidEnumTemplate() {
    }

    public static EnumTemplate getInstance() {
        return _instance;
    }

    @Override
    protected String getStringEnumImport() {
        return "com.azure.android.core.util.ExpandableStringEnum";
    }
}
