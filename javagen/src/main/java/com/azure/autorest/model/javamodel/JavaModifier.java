package com.azure.autorest.model.javamodel;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

/**
 * Modifiers that can be applied to Java types or members.
 */
public enum JavaModifier {
    Final,

    Static;

    public static final int SIZE = java.lang.Integer.SIZE;

    public static JavaModifier forValue(int value) {
        return values()[value];
    }

    public int getValue() {
        return this.ordinal();
    }
}