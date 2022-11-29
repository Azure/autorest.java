// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.naming.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * summary of Response
 *
 * <p>description of Response.
 */
@Immutable
public final class DataResponse {
    /*
     * summary of name property
     *
     * description of name property
     */
    @JsonProperty(value = "name", required = true)
    private String name;

    /*
     * summary of data property
     *
     * description of data property
     */
    @JsonProperty(value = "data", required = true)
    private BinaryData data;

    /*
     * summary of type property
     *
     * description of type property
     */
    @JsonProperty(value = "type", required = true)
    private TypesModel dataType;

    /*
     * summary of status property
     *
     * description of status property
     */
    @JsonProperty(value = "status", required = true)
    private DataStatus status;

    /**
     * Creates an instance of DataResponse class.
     *
     * @param name the name value to set.
     * @param data the data value to set.
     * @param dataType the dataType value to set.
     * @param status the status value to set.
     */
    @JsonCreator
    private DataResponse(
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "data", required = true) BinaryData data,
            @JsonProperty(value = "type", required = true) TypesModel dataType,
            @JsonProperty(value = "status", required = true) DataStatus status) {
        this.name = name;
        this.data = data;
        this.dataType = dataType;
        this.status = status;
    }

    /**
     * Get the name property: summary of name property
     *
     * <p>description of name property.
     *
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the data property: summary of data property
     *
     * <p>description of data property.
     *
     * @return the data value.
     */
    public BinaryData getData() {
        return this.data;
    }

    /**
     * Get the dataType property: summary of type property
     *
     * <p>description of type property.
     *
     * @return the dataType value.
     */
    public TypesModel getDataType() {
        return this.dataType;
    }

    /**
     * Get the status property: summary of status property
     *
     * <p>description of status property.
     *
     * @return the status value.
     */
    public DataStatus getStatus() {
        return this.status;
    }
}
