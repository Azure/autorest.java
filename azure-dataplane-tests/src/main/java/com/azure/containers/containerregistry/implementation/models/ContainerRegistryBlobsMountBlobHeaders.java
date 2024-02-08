// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package com.azure.containers.containerregistry.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.HttpHeaders;

/**
 * The ContainerRegistryBlobsMountBlobHeaders model.
 */
@Fluent
public final class ContainerRegistryBlobsMountBlobHeaders {

    /*
     * The Docker-Upload-UUID property.
     */
    private String dockerUploadUUID;

    /*
     * The Location property.
     */
    private String location;

    /*
     * The Docker-Content-Digest property.
     */
    private String dockerContentDigest;

    private static final HttpHeaderName DOCKER_UPLOAD_UUID = HttpHeaderName.fromString("Docker-Upload-UUID");

    private static final HttpHeaderName DOCKER_CONTENT_DIGEST = HttpHeaderName.fromString("Docker-Content-Digest");

    // HttpHeaders containing the raw property values.
    /**
     * Creates an instance of ContainerRegistryBlobsMountBlobHeaders class.
     *
     * @param rawHeaders The raw HttpHeaders that will be used to create the property values.
     */
    public ContainerRegistryBlobsMountBlobHeaders(HttpHeaders rawHeaders) {
        this.dockerUploadUUID = rawHeaders.getValue(DOCKER_UPLOAD_UUID);
        this.location = rawHeaders.getValue(HttpHeaderName.LOCATION);
        this.dockerContentDigest = rawHeaders.getValue(DOCKER_CONTENT_DIGEST);
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
     * @return the ContainerRegistryBlobsMountBlobHeaders object itself.
     */
    public ContainerRegistryBlobsMountBlobHeaders setDockerUploadUUID(String dockerUploadUUID) {
        this.dockerUploadUUID = dockerUploadUUID;
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
     * @return the ContainerRegistryBlobsMountBlobHeaders object itself.
     */
    public ContainerRegistryBlobsMountBlobHeaders setLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * Get the dockerContentDigest property: The Docker-Content-Digest property.
     *
     * @return the dockerContentDigest value.
     */
    public String getDockerContentDigest() {
        return this.dockerContentDigest;
    }

    /**
     * Set the dockerContentDigest property: The Docker-Content-Digest property.
     *
     * @param dockerContentDigest the dockerContentDigest value to set.
     * @return the ContainerRegistryBlobsMountBlobHeaders object itself.
     */
    public ContainerRegistryBlobsMountBlobHeaders setDockerContentDigest(String dockerContentDigest) {
        this.dockerContentDigest = dockerContentDigest;
        return this;
    }
}
