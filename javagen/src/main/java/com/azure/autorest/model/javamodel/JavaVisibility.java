package com.azure.autorest.model.javamodel;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

/**
 * The visibility of a Java type or member.
 */
public enum JavaVisibility {
    Public,

    Protected,

    Private,

    PackagePrivate;

    public static final int SIZE = java.lang.Integer.SIZE;

    public static JavaVisibility forValue(int value) {
        return values()[value];
    }

    public int getValue() {
        return this.ordinal();
    }
}