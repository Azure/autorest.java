package com.azure.autorest.postprocessor.ls.models;

public class VersionedTextDocumentIdentifier extends TextDocumentIdentifier {
    private int version;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
