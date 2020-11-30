package com.azure.autorest.customization.implementation.ls.models;

import java.util.List;

public class CodeAction {
    private String title;
    private String kind;
    private List<Object> diagnostics;
    private Command command;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<Object> getDiagnostics() {
        return diagnostics;
    }

    public void setDiagnostics(List<Object> diagnostics) {
        this.diagnostics = diagnostics;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
