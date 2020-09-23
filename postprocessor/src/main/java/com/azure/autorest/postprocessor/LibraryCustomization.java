package com.azure.autorest.postprocessor;

import com.azure.autorest.postprocessor.ls.EclipseLanguageClient;

import java.util.Map;

public class LibraryCustomization {
    private Map<String, String> files;
    private EclipseLanguageClient languageClient;
    private Editor editor;

    LibraryCustomization(Editor editor, EclipseLanguageClient languageClient) {
        this.editor = editor;
        this.languageClient = languageClient;
        this.files = editor.getContents();
    }

    public PackageCustomization getPackage(String packageName) {
        return new PackageCustomization(editor, languageClient, packageName);
    }

    public ModelCustomization getModel(String packageName, String modelClassName) {
        return new ModelCustomization(editor, languageClient, packageName, modelClassName);
    }
}
