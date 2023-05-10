// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.resiliency.servicedriven.v1;

import com.azure.core.annotation.Generated;
import com.azure.core.util.ServiceVersion;

/** Service version of ServiceDrivenClient. */
public enum ServiceDrivenServiceVersion implements ServiceVersion {
    /** Enum value v1. */
    V1("v1");

    @Generated private final String version;

    @Generated
    ServiceDrivenServiceVersion(String version) {
        this.version = version;
    }

    /** {@inheritDoc} */
    @Override
    @Generated
    public String getVersion() {
        return this.version;
    }

    /**
     * Gets the latest service version supported by this client library.
     *
     * @return The latest {@link ServiceDrivenServiceVersion}.
     */
    @Generated
    public static ServiceDrivenServiceVersion getLatest() {
        return V1;
    }
}
