// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.collectionformat;

import com.azure.core.util.ServiceVersion;

/** Service version of CollectionFormatClient. */
public enum CollectionFormatServiceVersion implements ServiceVersion {
    /** Enum value 2022-12-16-preview. */
    V2022_12_16_PREVIEW("2022-12-16-preview");

    private final String version;

    CollectionFormatServiceVersion(String version) {
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
     * @return The latest {@link CollectionFormatServiceVersion}.
     */
    public static CollectionFormatServiceVersion getLatest() {
        return V2022_12_16_PREVIEW;
    }
}
