// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import java.util.HashMap;
import java.util.Map;

public enum SchemaContext {

    INPUT("input"),

    OUTPUT("output"),

    EXCEPTION("exception"),

    PUBLIC("public"),

    PAGED("paged"),

    ANONYMOUS("anonymous"),

    INTERNAL("internal"),

    JSON_MERGE_PATCH("json-merge-patch");

    private final String value;
    private final static Map<String, SchemaContext> CONSTANTS = new HashMap<>();

    static {
        for (SchemaContext c : values()) {
            CONSTANTS.put(c.value, c);
        }
    }

    SchemaContext(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public String value() {
        return this.value;
    }

    public static SchemaContext fromValue(String value) {
        SchemaContext constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }
}
