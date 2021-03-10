package com.azure.autorest.android.template;

import com.azure.autorest.template.ClientMethodTemplate;

public class AndroidClientMethodTemplate extends ClientMethodTemplate {

    private static ClientMethodTemplate _instance = new AndroidClientMethodTemplate();

    protected AndroidClientMethodTemplate() {
    }

    public static ClientMethodTemplate getInstance() {
        return _instance;
    }

}
