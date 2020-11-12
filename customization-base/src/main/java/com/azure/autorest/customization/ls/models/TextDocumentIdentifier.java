package com.azure.autorest.customization.ls.models;

import java.net.URI;

public class TextDocumentIdentifier {
    private URI uri;

    public TextDocumentIdentifier(URI uri) {
        this.uri = uri;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
}
