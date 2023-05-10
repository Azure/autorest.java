// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.server.path.multiple;

import com.azure.core.annotation.Generated;
import com.azure.core.util.ServiceVersion;

/** Service version of MultipleClient. */
public enum MultipleServiceVersion implements ServiceVersion {
    /** Enum value v1.0. */
    V1_0("v1.0");

    @Generated private final String version;

    @Generated
    MultipleServiceVersion(String version) {
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
     * @return The latest {@link MultipleServiceVersion}.
     */
    @Generated
    public static MultipleServiceVersion getLatest() {
        return V1_0;
    }
}
