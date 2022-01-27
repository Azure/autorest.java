// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization.implementation.ls.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum SymbolKind {
    FILE(1),
    MODULE(2),
    NAMESPACE(3),
    PACKAGE(4),
    CLASS(5),
    METHOD(6),
    PROPERTY(7),
    FIELD(8),
    CONSTRUCTOR(9),
    ENUM(10),
    INTERFACE(11),
    FUNCTION(12),
    VARIABLE(13),
    CONSTANT(14),
    STRING(15),
    NUMBER(16),
    BOOLEAN(17),
    ARRAY(18),
    OBJECT(19),
    KEY(20),
    NULL(21),
    ENUM_MEMBER(22),
    STRUCT(23),
    EVENT(24),
    OPERATOR(25),
    TYPE_PARAMETER(26);

    private static final Map<Integer, SymbolKind> INT_TO_KIND_MAP;

    static {
        INT_TO_KIND_MAP = new HashMap<>();

        for (SymbolKind kind : SymbolKind.values()) {
            INT_TO_KIND_MAP.putIfAbsent(kind.value, kind);
        }
    }

    private final int value;
    private final String valueString;

    SymbolKind(int value) {
        this.value = value;
        this.valueString = Integer.toString(value);
    }

    @JsonCreator
    public static SymbolKind fromInt(int value) {
        return INT_TO_KIND_MAP.get(value);
    }

    @JsonValue
    @Override
    public String toString() {
        return valueString;
    }
}
