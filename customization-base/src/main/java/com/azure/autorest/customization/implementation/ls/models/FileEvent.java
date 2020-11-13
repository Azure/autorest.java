package com.azure.autorest.customization.implementation.ls.models;

import java.net.URI;

public class FileEvent {
    URI uri;
    FileChangeType type;

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public FileChangeType getType() {
        return type;
    }

    public void setType(FileChangeType type) {
        this.type = type;
    }
}
