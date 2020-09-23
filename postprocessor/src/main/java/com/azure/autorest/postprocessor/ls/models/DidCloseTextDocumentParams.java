package com.azure.autorest.postprocessor.ls.models;

public class DidCloseTextDocumentParams {
    private TextDocumentIdentifier textDocument;

    public TextDocumentIdentifier getTextDocument() {
        return textDocument;
    }

    public void setTextDocument(TextDocumentIdentifier textDocument) {
        this.textDocument = textDocument;
    }
}
