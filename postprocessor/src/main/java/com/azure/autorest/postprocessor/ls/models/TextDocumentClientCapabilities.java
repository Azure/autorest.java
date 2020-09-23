package com.azure.autorest.postprocessor.ls.models;

public class TextDocumentClientCapabilities {
    private RenameClientCapabilities rename;
    private CodeActionClientCapabilities codeAction;

    public RenameClientCapabilities getRename() {
        return rename;
    }

    public void setRename(RenameClientCapabilities rename) {
        this.rename = rename;
    }

    public CodeActionClientCapabilities getCodeAction() {
        return codeAction;
    }

    public void setCodeAction(CodeActionClientCapabilities codeAction) {
        this.codeAction = codeAction;
    }
}
