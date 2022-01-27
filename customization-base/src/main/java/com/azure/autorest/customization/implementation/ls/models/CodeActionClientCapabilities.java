// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization.implementation.ls.models;

public class CodeActionClientCapabilities {
    private CodeActionLiteralSupport codeActionLiteralSupport;

    public CodeActionLiteralSupport getCodeActionLiteralSupport() {
        return codeActionLiteralSupport;
    }

    public void setCodeActionLiteralSupport(CodeActionLiteralSupport codeActionLiteralSupport) {
        this.codeActionLiteralSupport = codeActionLiteralSupport;
    }
}
