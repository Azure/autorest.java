package com.azure.autorest.models.codemodel;

import java.util.Map;

public class CodeModel {
    private Map<String, Schema> schemas;
    private Info info;
    private HttpComponents http;

    public Info info() {
        return info;
    }
}
