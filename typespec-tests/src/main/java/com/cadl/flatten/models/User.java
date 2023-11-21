// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.flatten.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The User model.
 */
@Immutable
public final class User {
    /*
     * The user property.
     */
    @Generated
    @JsonProperty(value = "user")
    private String user;

    /**
     * Creates an instance of User class.
     * 
     * @param user the user value to set.
     */
    @Generated
    @JsonCreator
    public User(@JsonProperty(value = "user") String user) {
        this.user = user;
    }

    /**
     * Get the user property: The user property.
     * 
     * @return the user value.
     */
    @Generated
    public String getUser() {
        return this.user;
    }
}
