// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization.implementation.ls.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FileChangeType {
    CREATED(1),
    CHANGED(2),
    DELETED(3);

    private static final FileChangeType[] INT_TO_KIND_MAP;

    static {
        INT_TO_KIND_MAP = new FileChangeType[4];

        for (FileChangeType kind : FileChangeType.values()) {
            INT_TO_KIND_MAP[kind.value] = kind;
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
        return (value < 0 || value >= INT_TO_KIND_MAP.length) ? null : INT_TO_KIND_MAP[value];
    }

    @JsonValue
    @Override
    public String toString() {
        return valueString;
    }
}
