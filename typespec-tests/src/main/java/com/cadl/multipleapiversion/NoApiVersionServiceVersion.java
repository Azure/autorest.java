// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.multipleapiversion;

/**
 * Service version of NoApiVersionClient.
 */
public enum NoApiVersionServiceVersion implements com.azure.core.util.ServiceVersion {
    /**
     * Enum value 2022-10-01-preview.
     */
    V2022_10_01_PREVIEW("2022-10-01-preview"),

    /**
     * Enum value 2022-12-01-preview.
     */
    V2022_12_01_PREVIEW("2022-12-01-preview");

    private final String version;

    NoApiVersionServiceVersion(String version) {
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
     * @return The latest {@link NoApiVersionServiceVersion}.
     */
    public static NoApiVersionServiceVersion getLatest() {
        return V2022_12_01_PREVIEW;
    }
}
