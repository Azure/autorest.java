// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.testserver.readonlyproperties;

import com.azure.core.util.ServiceVersion;

/** Service version of ReadonlyPropertiesClient. */
public enum ReadonlyPropertiesServiceVersion implements ServiceVersion {
    /** Enum value 1.0.0. */
    V1_0_0("1.0.0");

    private final String version;

    ReadonlyPropertiesServiceVersion(String version) {
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
     * @return The latest {@link ReadonlyPropertiesServiceVersion}.
     */
    public static ReadonlyPropertiesServiceVersion getLatest() {
        return V1_0_0;
    }
}
