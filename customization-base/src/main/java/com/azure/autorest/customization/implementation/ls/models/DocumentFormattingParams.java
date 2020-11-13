package com.azure.autorest.customization.implementation.ls.models;

public class DocumentFormattingParams {
    private TextDocumentIdentifier textDocument;
    private FormattingOptions options;

    public TextDocumentIdentifier getTextDocument() {
        return textDocument;
    }

    public void setTextDocument(TextDocumentIdentifier textDocument) {
        this.textDocument = textDocument;
    }

    public FormattingOptions getOptions() {
        return options;
    }

    public void setOptions(FormattingOptions options) {
        this.options = options;
    }
}
