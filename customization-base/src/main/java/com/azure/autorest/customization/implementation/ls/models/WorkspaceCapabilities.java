package com.azure.autorest.customization.implementation.ls.models;

public class WorkspaceCapabilities {
    private boolean applyEdit;

    private WorkspaceEditClientCapabilities workspaceEdit;

    private WorkspaceSymbolClientCapabilities symbol;

    private boolean workspaceFolders;

    private boolean configuration;

    public boolean isApplyEdit() {
        return applyEdit;
    }

    public void setApplyEdit(boolean applyEdit) {
        this.applyEdit = applyEdit;
    }

    public WorkspaceEditClientCapabilities getWorkspaceEdit() {
        return workspaceEdit;
    }

    public void setWorkspaceEdit(WorkspaceEditClientCapabilities workspaceEdit) {
        this.workspaceEdit = workspaceEdit;
    }

    public WorkspaceSymbolClientCapabilities getSymbol() {
        return symbol;
    }

    public void setSymbol(WorkspaceSymbolClientCapabilities symbol) {
        this.symbol = symbol;
    }

    public boolean isWorkspaceFolders() {
        return workspaceFolders;
    }

    public void setWorkspaceFolders(boolean workspaceFolders) {
        this.workspaceFolders = workspaceFolders;
    }

    public boolean isConfiguration() {
        return configuration;
    }

    public void setConfiguration(boolean configuration) {
        this.configuration = configuration;
    }
}
