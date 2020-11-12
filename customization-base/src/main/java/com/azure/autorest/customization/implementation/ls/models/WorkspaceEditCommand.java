package com.azure.autorest.customization.implementation.ls.models;

import java.util.List;

public class WorkspaceEditCommand extends Command {
    private List<WorkspaceEdit> arguments;

    public List<WorkspaceEdit> getArguments() {
        return arguments;
    }

    public void setArguments(List<WorkspaceEdit> arguments) {
        this.arguments = arguments;
    }
}
