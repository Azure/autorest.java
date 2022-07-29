// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.paged;

import com.azure.core.util.ServiceVersion;

/** Service version of PagedClient. */
public enum PagedServiceVersion implements ServiceVersion {
    /** Enum value 1.0.0. */
    V1_0_0("1.0.0");

    private final String version;

    PagedServiceVersion(String version) {
        this.version = version;
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    /**
     * Gets the latest service version supported by this client library.
     *
     * @return The latest {@link PagedServiceVersion}.
     */
    public static PagedServiceVersion getLatest() {
        return V1_0_0;
    }
}
