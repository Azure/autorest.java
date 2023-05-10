// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.mediatypes;

import com.azure.core.annotation.Generated;
import com.azure.core.util.ServiceVersion;

/** Service version of MediaTypesClient. */
public enum MediaTypesServiceVersion implements ServiceVersion {
    /** Enum value 2.0-preview. */
    V2_0_PREVIEW("2.0-preview");

    @Generated private final String version;

    @Generated
    MediaTypesServiceVersion(String version) {
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
     * @return The latest {@link MediaTypesServiceVersion}.
     */
    @Generated
    public static MediaTypesServiceVersion getLatest() {
        return V2_0_PREVIEW;
    }
}
