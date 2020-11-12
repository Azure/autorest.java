package com.azure.autorest.customization.implementation.ls.models;

public class DidSaveTextDocumentParams {
    private TextDocumentIdentifier textDocument;
    private String text;

    public TextDocumentIdentifier getTextDocument() {
        return textDocument;
    }

    public void setTextDocument(TextDocumentIdentifier textDocument) {
        this.textDocument = textDocument;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
