package com.azure.autorest.android.template;

import com.azure.autorest.template.ModelTemplate;

public class AndroidModelTemplate extends ModelTemplate {
    private static AndroidModelTemplate _instance = new AndroidModelTemplate();

    protected AndroidModelTemplate() {
    }

    public static AndroidModelTemplate getInstance() {
        return _instance;
    }

    @Override
    protected String generateByteArrayCloneExpression(String expression) {
        return String.format("CoreUtil.clone(%s)", expression);
    }
}
