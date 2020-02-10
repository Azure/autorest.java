package com.azure.autorest.preprocessor.model.clientmodel;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

/**
 * An individual value within an enumerated type of a service.
 */
public class ClientEnumValue {
    private String name;
    private String value;

    /**
     * Create a new EnumValue with the provided name and value.
     * @param name The name of this EnumValue.
     * @param value The value of this EnumValue.
     */
    public ClientEnumValue(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * The name of this EnumValue.
     */
    public final String getName() {
        return name;
    }

    /**
     * The value of this EnumValue.
     */
    public final String getValue() {
        return value;
    }
}
