// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.util;

/**
 * Return type of a method.
 */
public enum ReturnType {
    /**
     * Single value return type.
     */
    SINGLE,

    /**
     * Simple collection, enumeration, return type.
     */
    COLLECTION,

    /**
     * Long-running operation return type.
     */
    LONG_RUNNING_OPERATION
}
