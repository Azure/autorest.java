package com.azure.autorest.extension.base.model.codemodel;

import java.util.HashMap;
import java.util.Map;

public enum KnownMediaType {
    BINARY("binary"),
    FORM("form"),
    JSON("json"),
    MULTIPART("multipart"),
    TEXT("text"),
    UNKNOWN("unknown"),
    XML("xml");

    private final String value;
    private final static Map<String, KnownMediaType> CONSTANTS = new HashMap<>();

    static {
        for (KnownMediaType c: values()) {
            CONSTANTS.put(c.value, c);
        }
    }

    private KnownMediaType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public String value() {
        return this.value;
    }

    public static KnownMediaType fromValue(String value) {
        KnownMediaType constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }

    public String getContentType() {
        switch (this) {
            case BINARY: return "application/octet-stream";
            case FORM: return "application/x-www-form-urlencoded";
            case JSON: return "application/json";
            case MULTIPART: return "multipart/form-data";
            case TEXT: return "text/plain";
            case XML: return "application/xml";
            case UNKNOWN:
            default: return JSON.getContentType();
        }
    }
}
