package com.azure.autorest.extension.base.model.codemodel;

import java.util.HashMap;
import java.util.Map;

public enum ParameterLocation
{
    PATH("path"),
    QUERY("query"),
    HEADER("header"),
    BODY("body"),
    FORM_DATA("form-data");

    private final String value;
    private final static Map<String, ParameterLocation> CONSTANTS = new HashMap<>();

    static {
        for (ParameterLocation c: values()) {
            CONSTANTS.put(c.value, c);
        }
    }

    private ParameterLocation(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public String value() {
        return this.value;
    }

    public static ParameterLocation fromValue(String value) {
        ParameterLocation constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }
}