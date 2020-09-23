package com.azure.autorest.postprocessor.ls.models;

public class ClientCapabilities {
    private WorkspaceCapabilities workspace;

    private TextDocumentClientCapabilities textDocument;

    private WindowCapabilities window;

    public WorkspaceCapabilities getWorkspace() {
        return workspace;
    }

    public void setWorkspace(WorkspaceCapabilities workspace) {
        this.workspace = workspace;
    }

    public TextDocumentClientCapabilities getTextDocument() {
        return textDocument;
    }

    public void setTextDocument(TextDocumentClientCapabilities textDocument) {
        this.textDocument = textDocument;
    }

    public WindowCapabilities getWindow() {
        return window;
    }

    public void setWindow(WindowCapabilities window) {
        this.window = window;
    }
}
