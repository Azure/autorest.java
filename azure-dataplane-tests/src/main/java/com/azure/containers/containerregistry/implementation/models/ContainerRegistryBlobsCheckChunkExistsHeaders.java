// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.containers.containerregistry.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.HttpHeaders;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The ContainerRegistryBlobsCheckChunkExistsHeaders model. */
@Fluent
public final class ContainerRegistryBlobsCheckChunkExistsHeaders {
    /*
     * The Content-Range property.
     */
    @JsonProperty(value = "Content-Range")
    private String contentRange;

    /*
     * The Content-Length property.
     */
    @JsonProperty(value = "Content-Length")
    private Long contentLength;

    // HttpHeaders containing the raw property values.
    /**
     * Creates an instance of ContainerRegistryBlobsCheckChunkExistsHeaders class.
     *
     * @param rawHeaders The raw HttpHeaders that will be used to create the property values.
     */
    public ContainerRegistryBlobsCheckChunkExistsHeaders(HttpHeaders rawHeaders) {
        this.contentRange = rawHeaders.getValue(HttpHeaderName.CONTENT_RANGE);
        String contentLength = rawHeaders.getValue(HttpHeaderName.CONTENT_LENGTH);
        if (contentLength != null) {
            this.contentLength = Long.parseLong(contentLength);
        }
    }

    /**
     * Get the contentRange property: The Content-Range property.
     *
     * @return the contentRange value.
     */
    public String getContentRange() {
        return this.contentRange;
    }

    /**
     * Set the contentRange property: The Content-Range property.
     *
     * @param contentRange the contentRange value to set.
     * @return the ContainerRegistryBlobsCheckChunkExistsHeaders object itself.
     */
    public ContainerRegistryBlobsCheckChunkExistsHeaders setContentRange(String contentRange) {
        this.contentRange = contentRange;
        return this;
    }

    /**
     * Get the contentLength property: The Content-Length property.
     *
     * @return the contentLength value.
     */
    public Long getContentLength() {
        return this.contentLength;
    }

    /**
     * Set the contentLength property: The Content-Length property.
     *
     * @param contentLength the contentLength value to set.
     * @return the ContainerRegistryBlobsCheckChunkExistsHeaders object itself.
     */
    public ContainerRegistryBlobsCheckChunkExistsHeaders setContentLength(Long contentLength) {
        this.contentLength = contentLength;
        return this;
    }
}
