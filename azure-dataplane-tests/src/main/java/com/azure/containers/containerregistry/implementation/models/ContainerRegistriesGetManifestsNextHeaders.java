// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.containers.containerregistry.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.HttpHeaders;

/**
 * The ContainerRegistriesGetManifestsNextHeaders model.
 */
@Fluent
public final class ContainerRegistriesGetManifestsNextHeaders {
    /*
     * The Link property.
     */
    @Generated
    private String link;

    // HttpHeaders containing the raw property values.
    /**
     * Creates an instance of ContainerRegistriesGetManifestsNextHeaders class.
     * 
     * @param rawHeaders The raw HttpHeaders that will be used to create the property values.
     */
    public ContainerRegistriesGetManifestsNextHeaders(HttpHeaders rawHeaders) {
        this.link = rawHeaders.getValue(HttpHeaderName.LINK);
    }

    /**
     * Get the link property: The Link property.
     * 
     * @return the link value.
     */
    @Generated
    public String getLink() {
        return this.link;
    }

    /**
     * Set the link property: The Link property.
     * 
     * @param link the link value to set.
     * @return the ContainerRegistriesGetManifestsNextHeaders object itself.
     */
    @Generated
    public ContainerRegistriesGetManifestsNextHeaders setLink(String link) {
        this.link = link;
        return this;
    }
}
