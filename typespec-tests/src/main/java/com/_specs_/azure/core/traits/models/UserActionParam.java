// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com._specs_.azure.core.traits.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.regex.Pattern;

/**
 * User action param.
 */
@Immutable
public final class UserActionParam {
    /*
     * User action value.
     */
    @Generated
    @JsonProperty(value = "userActionValue")
    private String userActionValue;

    /**
     * Creates an instance of UserActionParam class.
     * 
     * @param userActionValue the userActionValue value to set.
     */
    @Generated
    @JsonCreator
    public UserActionParam(@JsonProperty(value = "userActionValue") String userActionValue) {
        this.userActionValue = userActionValue;
    }

    /**
     * Get the userActionValue property: User action value.
     * 
     * @return the userActionValue value.
     */
    @Generated
    public String getUserActionValue() {
        return this.userActionValue;
    }
}
