// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package azure.signalr.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.logging.ClientLogger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** Cross-Origin Resource Sharing (CORS) settings. */
@Fluent
public final class SignalRCorsSettings {
    @JsonIgnore private final ClientLogger logger = new ClientLogger(SignalRCorsSettings.class);

    /*
     * Gets or sets the list of origins that should be allowed to make
     * cross-origin calls (for example: http://example.com:12345). Use "*" to
     * allow all. If omitted, allow all by default.
     */
    @JsonProperty(value = "allowedOrigins")
    private List<String> allowedOrigins;

    /**
     * Get the allowedOrigins property: Gets or sets the list of origins that should be allowed to make cross-origin
     * calls (for example: http://example.com:12345). Use "*" to allow all. If omitted, allow all by default.
     *
     * @return the allowedOrigins value.
     */
    public List<String> allowedOrigins() {
        return this.allowedOrigins;
    }

    /**
     * Set the allowedOrigins property: Gets or sets the list of origins that should be allowed to make cross-origin
     * calls (for example: http://example.com:12345). Use "*" to allow all. If omitted, allow all by default.
     *
     * @param allowedOrigins the allowedOrigins value to set.
     * @return the SignalRCorsSettings object itself.
     */
    public SignalRCorsSettings withAllowedOrigins(List<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }
}
