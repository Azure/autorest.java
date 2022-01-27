// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import java.util.HashMap;
import java.util.Map;

public enum SerializationStyle {
    BINARY("binary"),
    DEEP_OBJECT("deepObject"),
    FORM("form"),
    JSON("json"),
    LABEL("label"),
    MATRIX("matrix"),
    PIPE_DELIMITED("pipeDelimited"),
    SIMPLE("simple"),
    SPACE_DELIMITED("spaceDelimited"),
    TAB_DELIMITED("tabDelimited"),
    XML("xml");

    private final String value;
    private final static Map<String, SerializationStyle> CONSTANTS = new HashMap<>();

    static {
        for (SerializationStyle c: values()) {
            CONSTANTS.put(c.value, c);
        }
    }
    public int getValue()
    {
        return this.ordinal();
    }


    private SerializationStyle(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if ("uri".equals(this.value)) {
            return "host";
        } else {
            return this.value;
        }
    }

    public String value() {
        return this.value;
    }

    public static SerializationStyle fromValue(String value) {
        SerializationStyle constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }
}
