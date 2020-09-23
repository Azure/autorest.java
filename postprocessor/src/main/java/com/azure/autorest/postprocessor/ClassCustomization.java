package com.azure.autorest.postprocessor;

import com.azure.autorest.postprocessor.ls.EclipseLanguageClient;
import com.azure.autorest.postprocessor.ls.models.FileChangeType;
import com.azure.autorest.postprocessor.ls.models.FileEvent;
import com.azure.autorest.postprocessor.ls.models.Position;
import com.azure.autorest.postprocessor.ls.models.Range;
import com.azure.autorest.postprocessor.ls.models.SymbolInformation;
import com.azure.autorest.postprocessor.ls.models.SymbolKind;
import com.azure.autorest.postprocessor.ls.models.TextEdit;
import com.azure.autorest.postprocessor.ls.models.WorkspaceEdit;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ClassCustomization {
    final Map<String, String> files;
    final EclipseLanguageClient languageClient;
    final Editor editor;
    final String packageName;
    final String className;

    ClassCustomization(Editor editor, EclipseLanguageClient languageClient, String packageName, String className) {
        this.editor = editor;
        this.languageClient = languageClient;
        this.files = editor.getContents();
        this.packageName = packageName;
        this.className = className;
    }

    public void changeClassModifier(String modifier) {
        String packagePath = packageName.replace(".", "/");
        Optional<SymbolInformation> classSymbol = languageClient.findWorkspaceSymbol(className)
                .stream().filter(si -> si.getLocation().getUri().toString().endsWith(packagePath + "/" + className + ".java"))
                .findFirst();

        if (classSymbol.isPresent()) {
            URI fileUri = classSymbol.get().getLocation().getUri();
            Optional<SymbolInformation> symbol = languageClient.listDocumentSymbols(fileUri)
                    .stream().filter(si -> si.getName().equals(className) && si.getKind() == SymbolKind.CLASS)
                    .findFirst();
            int i = fileUri.toString().indexOf("src/main/java/");
            String fileName = fileUri.toString().substring(i);
            if (symbol.isPresent()) {
                int line = symbol.get().getLocation().getRange().getStart().getLine();
                Position start = new Position(line, 0);
                String oldLineContent = editor.getFileLine(fileName, line);
                Position end = new Position(line, oldLineContent.length());
                String newLineContent = oldLineContent.replaceFirst("\\w.* " + className, modifier + " " + className);
                TextEdit textEdit = new TextEdit();
                textEdit.setNewText(newLineContent);
                textEdit.setRange(new Range(start, end));
                WorkspaceEdit workspaceEdit = new WorkspaceEdit();
                workspaceEdit.setChanges(Collections.singletonMap(fileUri, Collections.singletonList(textEdit)));
                applyWorkspaceEdit(workspaceEdit);
            }
        }
    }

    public void renameMethod(String methodName, String newName) {
        String packagePath = packageName.replace(".", "/");
        Optional<SymbolInformation> classSymbol = languageClient.findWorkspaceSymbol(className)
                .stream().filter(si -> si.getLocation().getUri().toString().endsWith(packagePath + "/" + className + ".java"))
                .findFirst();

        if (classSymbol.isPresent()) {
            URI fileUri = classSymbol.get().getLocation().getUri();
            Optional<SymbolInformation> symbol = languageClient.listDocumentSymbols(fileUri)
                    .stream().filter(si -> si.getName().equals(methodName) && si.getKind() == SymbolKind.METHOD)
                    .findFirst();
            if (symbol.isPresent()) {
                WorkspaceEdit edit = languageClient.renameSymbol(fileUri, symbol.get().getLocation().getRange().getStart(), newName);
                applyWorkspaceEdit(edit);
            }
        }
    }

    void applyWorkspaceEdit(WorkspaceEdit workspaceEdit) {
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
                fileEvent.setType(FileChangeType.CHANGED);
                changes.add(fileEvent);
            }
        }
        languageClient.notifyWatchedFilesChanged(changes);
    }
}
