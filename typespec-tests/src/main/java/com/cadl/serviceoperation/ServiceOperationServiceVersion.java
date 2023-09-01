// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.serviceoperation;

import com.azure.core.util.ServiceVersion;

/** Service version of ServiceOperationClient. */
public enum ServiceOperationServiceVersion implements ServiceVersion {
    /** Enum value 1.0.0. */
    V1_0_0("1.0.0");

    private final String version;

    ServiceOperationServiceVersion(String version) {
        this.version = version;
    }

    /** {@inheritDoc} */
    @Override
    public String getVersion() {
        return this.version;
    }

    /**
     * Gets the latest service version supported by this client library.
     *
     * @return The latest {@link ServiceOperationServiceVersion}.
     */
    public static ServiceOperationServiceVersion getLatest() {
        return V1_0_0;
    }
}
