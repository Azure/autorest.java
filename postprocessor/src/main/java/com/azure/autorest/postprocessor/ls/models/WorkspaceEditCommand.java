package com.azure.autorest.postprocessor.ls.models;

import com.fasterxml.jackson.annotation.JsonSetter;

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
