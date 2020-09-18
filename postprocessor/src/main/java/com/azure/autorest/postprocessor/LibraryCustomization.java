package com.azure.autorest.postprocessor;

import com.azure.autorest.postprocessor.ls.JDTLanguageClient;
import com.azure.autorest.postprocessor.ls.models.FileChangeType;
import com.azure.autorest.postprocessor.ls.models.FileEvent;
import com.azure.autorest.postprocessor.ls.models.SymbolInformation;
import com.azure.autorest.postprocessor.ls.models.TextEdit;
import com.azure.autorest.postprocessor.ls.models.WorkspaceEdit;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LibraryCustomization {
    private Map<String, String> files;
    private JDTLanguageClient languageClient;
    private Editor editor;

    LibraryCustomization(Editor editor, JDTLanguageClient languageClient) {
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
