// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

/**
 * Represents a stream response.
 */
public class StreamResponse extends Response {
    /**
     * Creates a new instance of the StreamResponse class.
     */
    public StreamResponse() {
        super();
    }

    /**
     * Whether the response is a stream.
     *
     * @return Whether the response is a stream.
     */
    public boolean isStream() {
        return true;
    }
}
