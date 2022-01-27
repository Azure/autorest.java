// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization.implementation.ls.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum FileChangeType {
    CREATED(1),
    CHANGED(2),
    DELETED(3);

    private static final Map<Integer, FileChangeType> INT_TO_KIND_MAP;

    static {
        INT_TO_KIND_MAP = new HashMap<>();

        for (FileChangeType kind : FileChangeType.values()) {
            INT_TO_KIND_MAP.putIfAbsent(kind.value, kind);
        }
    }

    private final int value;
    private final String valueString;

    FileChangeType(int value) {
        this.value = value;
        this.valueString = Integer.toString(value);
    }

    @JsonCreator
    public static FileChangeType fromInt(int value) {
        return INT_TO_KIND_MAP.get(value);
    }

    @JsonValue
    @Override
    public String toString() {
        return valueString;
    }
}
