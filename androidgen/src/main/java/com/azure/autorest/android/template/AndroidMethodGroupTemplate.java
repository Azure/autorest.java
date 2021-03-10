package com.azure.autorest.android.template;

import com.azure.autorest.template.MethodGroupTemplate;

public class AndroidMethodGroupTemplate extends MethodGroupTemplate {
    private static MethodGroupTemplate _instance = new AndroidMethodGroupTemplate();

    protected AndroidMethodGroupTemplate() {
    }

    public static MethodGroupTemplate getInstance() {
        return _instance;
    }
}

