package com.azure.autorest.customization.implementation.ls.models;

import java.util.List;

public class DidChangeTextDocumentParams {
    private VersionedTextDocumentIdentifier textDocument;
    private List<TextDocumentContentChangeEvent> contentChanges;

    public VersionedTextDocumentIdentifier getTextDocument() {
        return textDocument;
    }

    public void setTextDocument(VersionedTextDocumentIdentifier textDocument) {
        this.textDocument = textDocument;
    }

    public List<TextDocumentContentChangeEvent> getContentChanges() {
        return contentChanges;
    }

    public void setContentChanges(List<TextDocumentContentChangeEvent> contentChanges) {
        this.contentChanges = contentChanges;
    }
}
