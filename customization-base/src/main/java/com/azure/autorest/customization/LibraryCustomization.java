package com.azure.autorest.customization;

import com.azure.autorest.customization.ls.EclipseLanguageClient;

public class LibraryCustomization {
    private EclipseLanguageClient languageClient;
    private Editor editor;

    LibraryCustomization(Editor editor, EclipseLanguageClient languageClient) {
        this.editor = editor;
        this.languageClient = languageClient;
    }

    public PackageCustomization getPackage(String packageName) {
        return new PackageCustomization(editor, languageClient, packageName);
    }

    public ClassCustomization getClass(String packageName, String className) {
        return new ClassCustomization(editor, languageClient, packageName, className);
    }

    public Editor getRawEditor() {
        return editor;
    }
}
