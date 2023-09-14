// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.flatten.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Options for sendLong API. */
@Fluent
public final class SendLongOptions {
    /*
     * The id property.
     */
    @Generated
    @JsonProperty(value = "id")
    private String id;

    /*
     * The filter property.
     */
    @Generated
    @JsonProperty(value = "filter")
    private String filter;

    /*
     * The user property.
     */
    @Generated
    @JsonProperty(value = "user")
    private User user;

    /*
     * The input property.
     */
    @Generated
    @JsonProperty(value = "input")
    private String input;

    /*
     * The dataInt property.
     */
    @Generated
    @JsonProperty(value = "dataInt")
    private int dataInt;

    /*
     * The dataIntOptional property.
     */
    @Generated
    @JsonProperty(value = "dataIntOptional")
    private Integer dataIntOptional;

    /*
     * The dataLong property.
     */
    @Generated
    @JsonProperty(value = "dataLong")
    private Long dataLong;

    /*
     * The data_float property.
     */
    @Generated
    @JsonProperty(value = "data_float")
    private Double dataFloat;

    /**
     * Creates an instance of SendLongOptions class.
     *
     * @param id the id value to set.
     * @param input the input value to set.
     * @param dataInt the dataInt value to set.
     */
    @Generated
    @JsonCreator
    public SendLongOptions(
            @JsonProperty(value = "id") String id,
            @JsonProperty(value = "input") String input,
            @JsonProperty(value = "dataInt") int dataInt) {
        this.id = id;
        this.input = input;
        this.dataInt = dataInt;
    }

    /**
     * Get the id property: The id property.
     *
     * @return the id value.
     */
    @Generated
    public String getId() {
        return this.id;
    }

    /**
     * Get the filter property: The filter property.
     *
     * @return the filter value.
     */
    @Generated
    public String getFilter() {
        return this.filter;
    }

    /**
     * Set the filter property: The filter property.
     *
     * @param filter the filter value to set.
     * @return the SendLongOptions object itself.
     */
    @Generated
    public SendLongOptions setFilter(String filter) {
        this.filter = filter;
        return this;
    }

    /**
     * Get the user property: The user property.
     *
     * @return the user value.
     */
    @Generated
    public User getUser() {
        return this.user;
    }

    /**
     * Set the user property: The user property.
     *
     * @param user the user value to set.
     * @return the SendLongOptions object itself.
     */
    @Generated
    public SendLongOptions setUser(User user) {
        this.user = user;
        return this;
    }

    /**
     * Get the input property: The input property.
     *
     * @return the input value.
     */
    @Generated
    public String getInput() {
        return this.input;
    }

    /**
     * Get the dataInt property: The dataInt property.
     *
     * @return the dataInt value.
     */
    @Generated
    public int getDataInt() {
        return this.dataInt;
    }

    /**
     * Get the dataIntOptional property: The dataIntOptional property.
     *
     * @return the dataIntOptional value.
     */
    @Generated
    public Integer getDataIntOptional() {
        return this.dataIntOptional;
    }

    /**
     * Set the dataIntOptional property: The dataIntOptional property.
     *
     * @param dataIntOptional the dataIntOptional value to set.
     * @return the SendLongOptions object itself.
     */
    @Generated
    public SendLongOptions setDataIntOptional(Integer dataIntOptional) {
        this.dataIntOptional = dataIntOptional;
        return this;
    }

    /**
     * Get the dataLong property: The dataLong property.
     *
     * @return the dataLong value.
     */
    @Generated
    public Long getDataLong() {
        return this.dataLong;
    }

    /**
     * Set the dataLong property: The dataLong property.
     *
     * @param dataLong the dataLong value to set.
     * @return the SendLongOptions object itself.
     */
    @Generated
    public SendLongOptions setDataLong(Long dataLong) {
        this.dataLong = dataLong;
        return this;
    }

    /**
     * Get the dataFloat property: The data_float property.
     *
     * @return the dataFloat value.
     */
    @Generated
    public Double getDataFloat() {
        return this.dataFloat;
    }

    /**
     * Set the dataFloat property: The data_float property.
     *
     * @param dataFloat the dataFloat value to set.
     * @return the SendLongOptions object itself.
     */
    @Generated
    public SendLongOptions setDataFloat(Double dataFloat) {
        this.dataFloat = dataFloat;
        return this;
    }
}
