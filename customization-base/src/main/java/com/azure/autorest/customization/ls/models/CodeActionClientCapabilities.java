package com.azure.autorest.customization.ls.models;

public class CodeActionClientCapabilities {
    private CodeActionLiteralSupport codeActionLiteralSupport;

    public CodeActionLiteralSupport getCodeActionLiteralSupport() {
        return codeActionLiteralSupport;
    }

    public void setCodeActionLiteralSupport(CodeActionLiteralSupport codeActionLiteralSupport) {
        this.codeActionLiteralSupport = codeActionLiteralSupport;
    }
}
