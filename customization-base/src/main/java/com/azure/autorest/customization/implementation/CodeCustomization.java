// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization.implementation;

import com.azure.autorest.customization.Editor;
import com.azure.autorest.customization.implementation.ls.EclipseLanguageClient;
import com.azure.autorest.customization.implementation.ls.models.SymbolInformation;

import java.net.URI;

/**
 * Base class for all code based customizations.
 */
public abstract class CodeCustomization {
    protected final Editor editor;
    protected final EclipseLanguageClient languageClient;
    protected final SymbolInformation symbol;
    protected final URI fileUri;
    protected final String fileName;

    protected CodeCustomization(Editor editor, EclipseLanguageClient languageClient, SymbolInformation symbol) {
        this.editor = editor;
        this.languageClient = languageClient;
        this.symbol = symbol;
        this.fileUri = symbol.getLocation().getUri();
        int i = fileUri.toString().indexOf("src/main/java/");
        this.fileName = fileUri.toString().substring(i);
    }

    /**
     * The Editor managing the state of the CodeCustomization.
     *
     * @return The Editor.
     */
    Editor getEditor() {
        return editor;
    }

    /**
     * The EclipseLanguageClient managing validation of the CodeCustomization.
     *
     * @return The EclipseLanguageClient.
     */
    EclipseLanguageClient getLanguageClient() {
        return languageClient;
    }

    /**
     * The SymbolInformation managing information about the CodeCustomization.
     *
     * @return The SymbolInformation.
     */
    SymbolInformation getSymbol() {
        return symbol;
    }

    /**
     * The URI of the file containing where the code for the CodeCustomization exists.
     *
     * @return The URI of the file.
     */
    URI getFileUri() {
        return fileUri;
    }

    /**
     * The name of the file containing where the code for the CodeCustomization exists.
     *
     * @return The name of the file.
     */
    public String getFileName() {
        return fileName;
    }
}
