package com.azure.autorest.customization.implementation.ls.models;

public class DidOpenTextDocumentParams {
    private TextDocumentItem textDocument;

    public TextDocumentItem getTextDocument() {
        return textDocument;
    }

    public void setTextDocument(TextDocumentItem textDocument) {
        this.textDocument = textDocument;
    }
}
