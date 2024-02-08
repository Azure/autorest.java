// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package com.azure.containers.containerregistry.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.HttpHeaders;

/**
 * The ContainerRegistryBlobsStartUploadHeaders model.
 */
@Fluent
public final class ContainerRegistryBlobsStartUploadHeaders {

    /*
     * The Docker-Upload-UUID property.
     */
    private String dockerUploadUUID;

    /*
     * The Range property.
     */
    private String range;

    /*
     * The Location property.
     */
    private String location;

    private static final HttpHeaderName DOCKER_UPLOAD_UUID = HttpHeaderName.fromString("Docker-Upload-UUID");

    // HttpHeaders containing the raw property values.
    /**
     * Creates an instance of ContainerRegistryBlobsStartUploadHeaders class.
     *
     * @param rawHeaders The raw HttpHeaders that will be used to create the property values.
     */
    public ContainerRegistryBlobsStartUploadHeaders(HttpHeaders rawHeaders) {
        this.dockerUploadUUID = rawHeaders.getValue(DOCKER_UPLOAD_UUID);
        this.range = rawHeaders.getValue(HttpHeaderName.RANGE);
        this.location = rawHeaders.getValue(HttpHeaderName.LOCATION);
    }

    /**
     * Get the dockerUploadUUID property: The Docker-Upload-UUID property.
     *
     * @return the dockerUploadUUID value.
     */
    public String getDockerUploadUUID() {
        return this.dockerUploadUUID;
    }

    /**
     * Set the dockerUploadUUID property: The Docker-Upload-UUID property.
     *
     * @param dockerUploadUUID the dockerUploadUUID value to set.
     * @return the ContainerRegistryBlobsStartUploadHeaders object itself.
     */
    public ContainerRegistryBlobsStartUploadHeaders setDockerUploadUUID(String dockerUploadUUID) {
        this.dockerUploadUUID = dockerUploadUUID;
        return this;
    }

    /**
     * Get the range property: The Range property.
     *
     * @return the range value.
     */
    public String getRange() {
        return this.range;
    }

    /**
     * Set the range property: The Range property.
     *
     * @param range the range value to set.
     * @return the ContainerRegistryBlobsStartUploadHeaders object itself.
     */
    public ContainerRegistryBlobsStartUploadHeaders setRange(String range) {
        this.range = range;
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
     * @return the ContainerRegistryBlobsStartUploadHeaders object itself.
     */
    public ContainerRegistryBlobsStartUploadHeaders setLocation(String location) {
        this.location = location;
        return this;
    }
}
