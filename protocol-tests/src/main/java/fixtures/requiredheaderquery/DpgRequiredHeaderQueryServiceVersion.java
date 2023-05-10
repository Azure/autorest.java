// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.requiredheaderquery;

import com.azure.core.annotation.Generated;
import com.azure.core.util.ServiceVersion;

/** Service version of DpgRequiredHeaderQueryClient. */
public enum DpgRequiredHeaderQueryServiceVersion implements ServiceVersion {
    /** Enum value 1.0.0. */
    V1_0_0("1.0.0");

    @Generated private final String version;

    @Generated
    DpgRequiredHeaderQueryServiceVersion(String version) {
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
     * @return The latest {@link DpgRequiredHeaderQueryServiceVersion}.
     */
    @Generated
    public static DpgRequiredHeaderQueryServiceVersion getLatest() {
        return V1_0_0;
    }
}
