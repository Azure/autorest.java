package com.azure.autorest.postprocessor.ls.models;

import java.net.URI;

public class WorkspaceFolder {
    private URI uri;

    private String name;

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
