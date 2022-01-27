// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization.implementation.ls.models;

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
