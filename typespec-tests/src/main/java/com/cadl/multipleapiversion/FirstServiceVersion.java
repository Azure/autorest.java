// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.cadl.multipleapiversion;

import com.azure.core.util.ServiceVersion;

/**
 * Service version of FirstClient.
 */
public enum FirstServiceVersion implements ServiceVersion {

    /**
     * Enum value 2022-06-01-preview.
     */
    V2022_06_01_PREVIEW("2022-06-01-preview"),
    /**
     * Enum value 2022-12-01-preview.
     */
    V2022_12_01_PREVIEW("2022-12-01-preview");

    private final String version;

    FirstServiceVersion(String version) {
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
     * @return The latest {@link FirstServiceVersion}.
     */
    public static FirstServiceVersion getLatest() {
        return V2022_12_01_PREVIEW;
    }
}
