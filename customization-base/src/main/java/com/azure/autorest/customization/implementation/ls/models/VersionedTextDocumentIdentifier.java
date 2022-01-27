// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization.implementation.ls.models;

import java.net.URI;

public class VersionedTextDocumentIdentifier extends TextDocumentIdentifier {
    private int version;

    public VersionedTextDocumentIdentifier(URI uri) {
        super(uri);
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
