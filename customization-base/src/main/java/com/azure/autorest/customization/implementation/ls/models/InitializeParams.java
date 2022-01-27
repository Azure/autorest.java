// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization.implementation.ls.models;

import java.net.URI;
import java.util.List;

public class InitializeParams {
    private int processId;

    private URI rootUri;

    private ClientCapabilities capabilities;

    private String trace;

    private List<WorkspaceFolder> workspaceFolders;

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public URI getRootUri() {
        return rootUri;
    }

    public void setRootUri(URI rootUri) {
        this.rootUri = rootUri;
    }

    public ClientCapabilities getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(ClientCapabilities capabilities) {
        this.capabilities = capabilities;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public List<WorkspaceFolder> getWorkspaceFolders() {
        return workspaceFolders;
    }

    public void setWorkspaceFolders(List<WorkspaceFolder> workspaceFolders) {
        this.workspaceFolders = workspaceFolders;
    }
}
