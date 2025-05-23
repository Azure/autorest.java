// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.containers.containerregistry.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.HttpHeaders;

/**
 * The ContainerRegistryBlobsCheckBlobExistsHeaders model.
 */
@Fluent
public final class ContainerRegistryBlobsCheckBlobExistsHeaders {
    /*
     * The Content-Length property.
     */
    @Generated
    private Long contentLength;

    /*
     * The Docker-Content-Digest property.
     */
    @Generated
    private String dockerContentDigest;

    /*
     * The Location property.
     */
    @Generated
    private String location;

    private static final HttpHeaderName DOCKER_CONTENT_DIGEST = HttpHeaderName.fromString("Docker-Content-Digest");

    // HttpHeaders containing the raw property values.
    /**
     * Creates an instance of ContainerRegistryBlobsCheckBlobExistsHeaders class.
     * 
     * @param rawHeaders The raw HttpHeaders that will be used to create the property values.
     */
    public ContainerRegistryBlobsCheckBlobExistsHeaders(HttpHeaders rawHeaders) {
        String contentLength = rawHeaders.getValue(HttpHeaderName.CONTENT_LENGTH);
        if (contentLength != null) {
            this.contentLength = Long.parseLong(contentLength);
        } else {
            this.contentLength = null;
        }
        this.dockerContentDigest = rawHeaders.getValue(DOCKER_CONTENT_DIGEST);
        this.location = rawHeaders.getValue(HttpHeaderName.LOCATION);
    }

    /**
     * Get the contentLength property: The Content-Length property.
     * 
     * @return the contentLength value.
     */
    @Generated
    public Long getContentLength() {
        return this.contentLength;
    }

    /**
     * Set the contentLength property: The Content-Length property.
     * 
     * @param contentLength the contentLength value to set.
     * @return the ContainerRegistryBlobsCheckBlobExistsHeaders object itself.
     */
    @Generated
    public ContainerRegistryBlobsCheckBlobExistsHeaders setContentLength(Long contentLength) {
        this.contentLength = contentLength;
        return this;
    }

    /**
     * Get the dockerContentDigest property: The Docker-Content-Digest property.
     * 
     * @return the dockerContentDigest value.
     */
    @Generated
    public String getDockerContentDigest() {
        return this.dockerContentDigest;
    }

    /**
     * Set the dockerContentDigest property: The Docker-Content-Digest property.
     * 
     * @param dockerContentDigest the dockerContentDigest value to set.
     * @return the ContainerRegistryBlobsCheckBlobExistsHeaders object itself.
     */
    @Generated
    public ContainerRegistryBlobsCheckBlobExistsHeaders setDockerContentDigest(String dockerContentDigest) {
        this.dockerContentDigest = dockerContentDigest;
        return this;
    }

    /**
     * Get the location property: The Location property.
     * 
     * @return the location value.
     */
    @Generated
    public String getLocation() {
        return this.location;
    }

    /**
     * Set the location property: The Location property.
     * 
     * @param location the location value to set.
     * @return the ContainerRegistryBlobsCheckBlobExistsHeaders object itself.
     */
    @Generated
    public ContainerRegistryBlobsCheckBlobExistsHeaders setLocation(String location) {
        this.location = location;
        return this;
    }
}
