// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.naming.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * summary of Response
 * 
 * description of Response.
 */
@Immutable
public final class DataResponse {
    /*
     * summary of name property
     * 
     * description of name property
     */
    @Generated
    @JsonProperty(value = "name")
    private String name;

    /*
     * summary of data property
     * 
     * description of data property
     */
    @Generated
    @JsonProperty(value = "data")
    private BinaryData data;

    /*
     * summary of type property
     * 
     * description of type property
     */
    @Generated
    @JsonProperty(value = "type")
    private TypesModel dataType;

    /*
     * summary of status property
     * 
     * description of status property
     */
    @Generated
    @JsonProperty(value = "status")
    private DataStatus status;

    /**
     * Creates an instance of DataResponse class.
     * 
     * @param name the name value to set.
     * @param data the data value to set.
     * @param dataType the dataType value to set.
     * @param status the status value to set.
     */
    @Generated
    @JsonCreator
    private DataResponse(@JsonProperty(value = "name") String name, @JsonProperty(value = "data") BinaryData data, @JsonProperty(value = "type") TypesModel dataType, @JsonProperty(value = "status") DataStatus status) {
        this.name = name;
        this.data = data;
        this.dataType = dataType;
        this.status = status;
    }

    /**
     * Get the name property: summary of name property
     * 
     * description of name property.
     * 
     * @return the name value.
     */
    @Generated
    public String getName() {
        return this.name;
    }

    /**
     * Get the data property: summary of data property
     * 
     * description of data property.
     * 
     * @return the data value.
     */
    @Generated
    public BinaryData getData() {
        return this.data;
    }

    /**
     * Get the dataType property: summary of type property
     * 
     * description of type property.
     * 
     * @return the dataType value.
     */
    @Generated
    public TypesModel getDataType() {
        return this.dataType;
    }

    /**
     * Get the status property: summary of status property
     * 
     * description of status property.
     * 
     * @return the status value.
     */
    @Generated
    public DataStatus getStatus() {
        return this.status;
    }
}
