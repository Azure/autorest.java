// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization.implementation.ls.models;

import java.util.List;

public class DidChangeWatchedFilesParams {
    private List<FileEvent> changes;

    public List<FileEvent> getChanges() {
        return changes;
    }

    public void setChanges(List<FileEvent> changes) {
        this.changes = changes;
    }
}
