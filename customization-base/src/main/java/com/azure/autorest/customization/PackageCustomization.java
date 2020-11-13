package com.azure.autorest.customization;

import com.azure.autorest.customization.implementation.ls.EclipseLanguageClient;
import com.azure.autorest.customization.implementation.ls.models.FileChangeType;
import com.azure.autorest.customization.implementation.ls.models.FileEvent;
import com.azure.autorest.customization.implementation.ls.models.SymbolInformation;
import com.azure.autorest.customization.implementation.ls.models.TextEdit;
import com.azure.autorest.customization.implementation.ls.models.WorkspaceEdit;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The package level customization for an AutoRest generated client library.
 */
public final class PackageCustomization {
    private final EclipseLanguageClient languageClient;
    private final Editor editor;
    private final String packageName;

    PackageCustomization(Editor editor, EclipseLanguageClient languageClient, String packageName) {
        this.editor = editor;
        this.languageClient = languageClient;
        this.packageName = packageName;
    }

    /**
     * Renames a class in the package.
     *
     * @param className the simple name of an existing class in the package
     * @param newName the new simple name for this class
     */
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
                if (editor.getContents().containsKey(oldEntry)) {
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

    /**
     * Gets the class level customization for a Java class in the package.
     *
     * @param className the simple name of the class
     * @return the class level customization
     */
    public ClassCustomization getClass(String className) {
        return new ClassCustomization(editor, languageClient, packageName, className);
    }
}
