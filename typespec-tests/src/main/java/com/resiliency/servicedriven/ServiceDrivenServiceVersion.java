// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.resiliency.servicedriven;

/**
 * Service version of ServiceDrivenClient.
 */
public enum ServiceDrivenServiceVersion implements com.azure.core.util.ServiceVersion {
    /**
     * Enum value v1.
     */
    V1("v1"),

    /**
     * Enum value v2.
     */
    V2("v2");

    private final String version;

    ServiceDrivenServiceVersion(String version) {
        this.version = version;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getVersion() {
        return this.version;
    }

    /**
     * Gets the latest service version supported by this client library.
     * 
     * @return The latest {@link ServiceDrivenServiceVersion}.
     */
    public static ServiceDrivenServiceVersion getLatest() {
        return V2;
    }
}
