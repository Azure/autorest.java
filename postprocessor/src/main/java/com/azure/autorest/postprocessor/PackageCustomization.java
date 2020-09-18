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

public class PackageCustomization {
    private final Map<String, String> files;
    private final JDTLanguageClient languageClient;
    private final Editor editor;
    private final String packageName;

    PackageCustomization(Editor editor, JDTLanguageClient languageClient, String packageName) {
        this.editor = editor;
        this.languageClient = languageClient;
        this.files = editor.getContents();
        this.packageName = packageName;
    }

    public void renameClass(String className, String newName) {
        String packagePath = packageName.replace(".", "/");
        Optional<SymbolInformation> symbolInformation = languageClient.findWorkspaceSymbol(className)
                .stream().filter(si -> si.getLocation().getUri().toString().endsWith(packagePath + "/" + className + ".java"))
                .findFirst();

        if (symbolInformation.isPresent()) {
            WorkspaceEdit workspaceEdit = languageClient.renameSymbol(symbolInformation.get().getLocation().getUri(),
                    symbolInformation.get().getLocation().getRange().getStart(), newName);
            List<FileEvent> changes = new ArrayList<>();
            for (Map.Entry<URI, List<TextEdit>> edit : workspaceEdit.getChanges().entrySet()) {
                int i = edit.getKey().toString().indexOf("src/main/java/");
                String oldEntry = edit.getKey().toString().substring(i);
                if (files.containsKey(oldEntry)) {
                    for (TextEdit textEdit : edit.getValue()) {
                        editor.replace(oldEntry, textEdit.getRange().getStart(), textEdit.getRange().getEnd(), textEdit.getNewText());
                    }
                    FileEvent fileEvent = new FileEvent();
                    fileEvent.setUri(edit.getKey());
                    if (oldEntry.endsWith(className + ".java")) {
                        String newEntry = oldEntry.replace(className + ".java", newName + ".java");
                        editor.renameFile(oldEntry, newEntry);
                        URI newUri = URI.create(edit.getKey().toString().replace(className + ".java", newName + ".java"));
                        fileEvent.setType(FileChangeType.DELETED);
                        changes.add(fileEvent);
                        FileEvent newFile = new FileEvent();
                        newFile.setUri(newUri);
                        newFile.setType(FileChangeType.CREATED);
                        changes.add(newFile);
                    } else {
                        fileEvent.setType(FileChangeType.CHANGED);
                        changes.add(fileEvent);
                    }
                }
            }
            languageClient.notifyWatchedFilesChanged(changes);
        }
    }

    public ModelCustomization getModel(String modelClassName) {
        return new ModelCustomization(editor, languageClient, packageName, modelClassName);
    }
}
