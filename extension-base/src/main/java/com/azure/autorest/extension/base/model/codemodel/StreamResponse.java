// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;



/**
 * a response from a service.
 * 
 */
public class StreamResponse extends Response {
    private boolean stream = true;

    public boolean isStream() {
        return stream;
    }
}
