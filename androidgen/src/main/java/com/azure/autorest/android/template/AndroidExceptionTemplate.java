// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.android.template;

import com.azure.autorest.template.ExceptionTemplate;

public class AndroidExceptionTemplate extends ExceptionTemplate {
    private static ExceptionTemplate _instance = new AndroidExceptionTemplate();

    protected AndroidExceptionTemplate() {
    }

    public static ExceptionTemplate getInstance() {
        return _instance;
    }

    @Override
    protected String getHttpResponseImport() {
        return "com.azure.android.core.http.HttpResponse";
    }
}
