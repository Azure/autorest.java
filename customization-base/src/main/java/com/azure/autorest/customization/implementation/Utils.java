package com.azure.autorest.customization.implementation;

import com.azure.autorest.customization.Editor;
import com.azure.autorest.customization.implementation.ls.EclipseLanguageClient;
import com.azure.autorest.customization.implementation.ls.models.FileChangeType;
import com.azure.autorest.customization.implementation.ls.models.FileEvent;
import com.azure.autorest.customization.implementation.ls.models.TextEdit;
import com.azure.autorest.customization.implementation.ls.models.WorkspaceEdit;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Utils {
    public static void applyWorkspaceEdit(WorkspaceEdit workspaceEdit, Editor editor, EclipseLanguageClient languageClient) {
        List<FileEvent> changes = new ArrayList<>();
        for (Map.Entry<URI, List<TextEdit>> edit : workspaceEdit.getChanges().entrySet()) {
            int i = edit.getKey().toString().indexOf("src/main/java/");
            String fileName = edit.getKey().toString().substring(i);
            if (editor.getContents().containsKey(fileName)) {
                for (TextEdit textEdit : edit.getValue()) {
                    editor.replace(fileName, textEdit.getRange().getStart(), textEdit.getRange().getEnd(), textEdit.getNewText());
                }
                FileEvent fileEvent = new FileEvent();
                fileEvent.setUri(edit.getKey());
                fileEvent.setType(FileChangeType.CHANGED);
                changes.add(fileEvent);
            }
        }
        languageClient.notifyWatchedFilesChanged(changes);
    }

    public static void applyTextEdits(URI fileUri, List<TextEdit> textEdits, Editor editor, EclipseLanguageClient languageClient) {
        List<FileEvent> changes = new ArrayList<>();
        int i = fileUri.toString().indexOf("src/main/java/");
        String fileName = fileUri.toString().substring(i);
        if (editor.getContents().containsKey(fileName)) {
            for (int j = textEdits.size() - 1; j >= 0; j--) {
                TextEdit textEdit = textEdits.get(j);
                editor.replace(fileName, textEdit.getRange().getStart(), textEdit.getRange().getEnd(), textEdit.getNewText());
            }
            FileEvent fileEvent = new FileEvent();
            fileEvent.setUri(fileUri);
            fileEvent.setType(FileChangeType.CHANGED);
            changes.add(fileEvent);
        }
        languageClient.notifyWatchedFilesChanged(changes);
    }

    private Utils() {}
}
