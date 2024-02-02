// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.requiredoptionalbody;

/**
 * Service version of RequiredOptionalBodyClient.
 */
public enum RequiredOptionalBodyServiceVersion implements com.azure.core.util.ServiceVersion {
    /**
     * Enum value 1.0.0.
     */
    V1_0_0("1.0.0");

    private final String version;

    RequiredOptionalBodyServiceVersion(String version) {
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
     * @return The latest {@link RequiredOptionalBodyServiceVersion}.
     */
    public static RequiredOptionalBodyServiceVersion getLatest() {
        return V1_0_0;
    }
}
