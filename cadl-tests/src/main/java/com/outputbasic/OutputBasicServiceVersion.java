// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.outputbasic;

import com.azure.core.util.ServiceVersion;

/** Service version of OutputBasicClient. */
public enum OutputBasicServiceVersion implements ServiceVersion {
    /** Enum value 1.0.0. */
    V1_0_0("1.0.0");

    private final String version;

    OutputBasicServiceVersion(String version) {
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
     * @return The latest {@link OutputBasicServiceVersion}.
     */
    public static OutputBasicServiceVersion getLatest() {
        return V1_0_0;
    }
}
