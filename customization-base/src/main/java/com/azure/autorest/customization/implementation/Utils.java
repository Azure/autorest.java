package com.azure.autorest.customization.implementation;

import com.azure.autorest.customization.Editor;
import com.azure.autorest.customization.implementation.ls.EclipseLanguageClient;
import com.azure.autorest.customization.implementation.ls.models.FileChangeType;
import com.azure.autorest.customization.implementation.ls.models.FileEvent;
import com.azure.autorest.customization.implementation.ls.models.SymbolInformation;
import com.azure.autorest.customization.implementation.ls.models.TextEdit;
import com.azure.autorest.customization.implementation.ls.models.WorkspaceEdit;
import com.azure.autorest.customization.models.Position;
import com.azure.autorest.customization.models.Range;

import java.io.File;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

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

    public static void deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        directoryToBeDeleted.delete();
    }

    public static <T> boolean isNullOrEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    static void validateModifiers(int validTypeModifiers, int newModifiers) {
        if (newModifiers <= 0) {
            throw new IllegalArgumentException("Modifiers aren't allowed to be less than or equal to 0.");
        }

        if (validTypeModifiers != (validTypeModifiers | newModifiers)) {
            throw new IllegalArgumentException("Modifiers contain illegal modifiers for the type.");
        }
    }

    public static void replaceModifier(SymbolInformation symbol, Editor editor, EclipseLanguageClient languageClient,
        BiFunction<String, String, String> replacer, int validaTypeModifiers, int newModifiers) {
        validateModifiers(validaTypeModifiers, newModifiers);

        URI fileUri = symbol.getLocation().getUri();
        int i = fileUri.toString().indexOf("src/main/java/");
        String fileName = fileUri.toString().substring(i);

        int line = symbol.getLocation().getRange().getStart().getLine();
        Position start = new Position(line, 0);
        String oldLineContent = editor.getFileLine(fileName, line);
        Position end = new Position(line, oldLineContent.length());
        String newLineContent = replacer.apply(oldLineContent, Modifier.toString(newModifiers));
        TextEdit textEdit = new TextEdit();
        textEdit.setNewText(newLineContent);
        textEdit.setRange(new Range(start, end));
        WorkspaceEdit workspaceEdit = new WorkspaceEdit();
        workspaceEdit.setChanges(Collections.singletonMap(fileUri, Collections.singletonList(textEdit)));
        Utils.applyWorkspaceEdit(workspaceEdit, editor, languageClient);
    }

    private Utils() {}
}
