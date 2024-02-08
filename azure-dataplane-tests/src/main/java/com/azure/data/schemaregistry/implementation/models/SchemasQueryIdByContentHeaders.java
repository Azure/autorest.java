// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package com.azure.data.schemaregistry.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.HttpHeaders;

/**
 * The SchemasQueryIdByContentHeaders model.
 */
@Fluent
public final class SchemasQueryIdByContentHeaders {

    /*
     * The Schema-Version property.
     */
    private Integer schemaVersion;

    /*
     * The Schema-Id property.
     */
    private String schemaId;

    /*
     * The Schema-Group-Name property.
     */
    private String schemaGroupName;

    /*
     * The Schema-Name property.
     */
    private String schemaName;

    /*
     * The Schema-Id-Location property.
     */
    private String schemaIdLocation;

    /*
     * The Location property.
     */
    private String location;

    private static final HttpHeaderName SCHEMA_VERSION = HttpHeaderName.fromString("Schema-Version");

    private static final HttpHeaderName SCHEMA_ID = HttpHeaderName.fromString("Schema-Id");

    private static final HttpHeaderName SCHEMA_GROUP_NAME = HttpHeaderName.fromString("Schema-Group-Name");

    private static final HttpHeaderName SCHEMA_NAME = HttpHeaderName.fromString("Schema-Name");

    private static final HttpHeaderName SCHEMA_ID_LOCATION = HttpHeaderName.fromString("Schema-Id-Location");

    // HttpHeaders containing the raw property values.
    /**
     * Creates an instance of SchemasQueryIdByContentHeaders class.
     *
     * @param rawHeaders The raw HttpHeaders that will be used to create the property values.
     */
    public SchemasQueryIdByContentHeaders(HttpHeaders rawHeaders) {
        String schemaVersion = rawHeaders.getValue(SCHEMA_VERSION);
        if (schemaVersion != null) {
            this.schemaVersion = Integer.parseInt(schemaVersion);
        }
        this.schemaId = rawHeaders.getValue(SCHEMA_ID);
        this.schemaGroupName = rawHeaders.getValue(SCHEMA_GROUP_NAME);
        this.schemaName = rawHeaders.getValue(SCHEMA_NAME);
        this.schemaIdLocation = rawHeaders.getValue(SCHEMA_ID_LOCATION);
        this.location = rawHeaders.getValue(HttpHeaderName.LOCATION);
    }

    /**
     * Get the schemaVersion property: The Schema-Version property.
     *
     * @return the schemaVersion value.
     */
    public Integer getSchemaVersion() {
        return this.schemaVersion;
    }

    /**
     * Set the schemaVersion property: The Schema-Version property.
     *
     * @param schemaVersion the schemaVersion value to set.
     * @return the SchemasQueryIdByContentHeaders object itself.
     */
    public SchemasQueryIdByContentHeaders setSchemaVersion(Integer schemaVersion) {
        this.schemaVersion = schemaVersion;
        return this;
    }

    /**
     * Get the schemaId property: The Schema-Id property.
     *
     * @return the schemaId value.
     */
    public String getSchemaId() {
        return this.schemaId;
    }

    /**
     * Set the schemaId property: The Schema-Id property.
     *
     * @param schemaId the schemaId value to set.
     * @return the SchemasQueryIdByContentHeaders object itself.
     */
    public SchemasQueryIdByContentHeaders setSchemaId(String schemaId) {
        this.schemaId = schemaId;
        return this;
    }

    /**
     * Get the schemaGroupName property: The Schema-Group-Name property.
     *
     * @return the schemaGroupName value.
     */
    public String getSchemaGroupName() {
        return this.schemaGroupName;
    }

    /**
     * Set the schemaGroupName property: The Schema-Group-Name property.
     *
     * @param schemaGroupName the schemaGroupName value to set.
     * @return the SchemasQueryIdByContentHeaders object itself.
     */
    public SchemasQueryIdByContentHeaders setSchemaGroupName(String schemaGroupName) {
        this.schemaGroupName = schemaGroupName;
        return this;
    }

    /**
     * Get the schemaName property: The Schema-Name property.
     *
     * @return the schemaName value.
     */
    public String getSchemaName() {
        return this.schemaName;
    }

    /**
     * Set the schemaName property: The Schema-Name property.
     *
     * @param schemaName the schemaName value to set.
     * @return the SchemasQueryIdByContentHeaders object itself.
     */
    public SchemasQueryIdByContentHeaders setSchemaName(String schemaName) {
        this.schemaName = schemaName;
        return this;
    }

    /**
     * Get the schemaIdLocation property: The Schema-Id-Location property.
     *
     * @return the schemaIdLocation value.
     */
    public String getSchemaIdLocation() {
        return this.schemaIdLocation;
    }

    /**
     * Set the schemaIdLocation property: The Schema-Id-Location property.
     *
     * @param schemaIdLocation the schemaIdLocation value to set.
     * @return the SchemasQueryIdByContentHeaders object itself.
     */
    public SchemasQueryIdByContentHeaders setSchemaIdLocation(String schemaIdLocation) {
        this.schemaIdLocation = schemaIdLocation;
        return this;
    }

    /**
     * Get the location property: The Location property.
     *
     * @return the location value.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Set the location property: The Location property.
     *
     * @param location the location value to set.
     * @return the SchemasQueryIdByContentHeaders object itself.
     */
    public SchemasQueryIdByContentHeaders setLocation(String location) {
        this.location = location;
        return this;
    }
}
