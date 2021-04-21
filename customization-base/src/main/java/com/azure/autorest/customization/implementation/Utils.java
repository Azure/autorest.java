package com.azure.autorest.customization.implementation;

import com.azure.autorest.customization.Editor;
import com.azure.autorest.customization.implementation.ls.EclipseLanguageClient;
import com.azure.autorest.customization.implementation.ls.models.CodeActionKind;
import com.azure.autorest.customization.implementation.ls.models.FileChangeType;
import com.azure.autorest.customization.implementation.ls.models.FileEvent;
import com.azure.autorest.customization.implementation.ls.models.SymbolInformation;
import com.azure.autorest.customization.implementation.ls.models.TextEdit;
import com.azure.autorest.customization.implementation.ls.models.WorkspaceEdit;
import com.azure.autorest.customization.implementation.ls.models.WorkspaceEditCommand;
import com.azure.autorest.customization.models.Position;
import com.azure.autorest.customization.models.Range;

import java.io.File;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

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

    public static boolean isNullOrEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static <T> boolean isNullOrEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    public static <T> boolean isNullOrEmpty(Iterable<T> iterable) {
        return (iterable == null || !iterable.iterator().hasNext());
    }

    static void validateModifiers(int validTypeModifiers, int newModifiers) {
        // 0 indicates no modifiers.
        if (newModifiers == 0) {
            return;
        }

        if (newModifiers < 0) {
            throw new IllegalArgumentException("Modifiers aren't allowed to be less than or equal to 0.");
        }

        if (validTypeModifiers != (validTypeModifiers | newModifiers)) {
            throw new IllegalArgumentException("Modifiers contain illegal modifiers for the type.");
        }
    }

    /**
     * Replaces the modifier for a given symbol.
     *
     * @param symbol The symbol having its modifier replaced.
     * @param editor The editor containing information about the symbol.
     * @param languageClient The language client handling replacement of the modifiers.
     * @param replaceTarget A string regex that determines how the modifiers are replaced.
     * @param modifierReplaceBase A string that determines the base modifier replacement.
     * @param validaTypeModifiers The modifier bit flag used to validate the new modifiers.
     * @param newModifiers The new modifiers for the symbol.
     */
    public static void replaceModifier(SymbolInformation symbol, Editor editor, EclipseLanguageClient languageClient,
        String replaceTarget, String modifierReplaceBase, int validaTypeModifiers, int newModifiers) {
        validateModifiers(validaTypeModifiers, newModifiers);

        URI fileUri = symbol.getLocation().getUri();
        int i = fileUri.toString().indexOf("src/main/java/");
        String fileName = fileUri.toString().substring(i);

        int line = symbol.getLocation().getRange().getStart().getLine();
        Position start = new Position(line, 0);
        String oldLineContent = editor.getFileLine(fileName, line);
        Position end = new Position(line, oldLineContent.length());

        String newModifiersString = Modifier.toString(newModifiers);
        String newLineContent = (isNullOrEmpty(newModifiersString))
            ? oldLineContent.replaceFirst(replaceTarget, modifierReplaceBase)
            : oldLineContent.replaceFirst(replaceTarget, newModifiersString + " " + modifierReplaceBase);

        TextEdit textEdit = new TextEdit();
        textEdit.setNewText(newLineContent);
        textEdit.setRange(new Range(start, end));
        WorkspaceEdit workspaceEdit = new WorkspaceEdit();
        workspaceEdit.setChanges(Collections.singletonMap(fileUri, Collections.singletonList(textEdit)));
        Utils.applyWorkspaceEdit(workspaceEdit, editor, languageClient);
    }

    public static <T, S> S returnIfPresentOrThrow(Optional<T> optional, Function<T, S> returnFormatter,
        Supplier<RuntimeException> orThrow) {
        if (optional.isPresent()) {
            return returnFormatter.apply(optional.get());
        }

        throw orThrow.get();
    }

    public static <T, S> S returnIfPresentOrElse(Optional<T> optional, Function<T, S> returnFormatter,
        Supplier<S> orElse) {
        return optional.isPresent()
            ? returnFormatter.apply(optional.get())
            : orElse.get();
    }

    public static void writeLine(StringBuilder stringBuilder, String text) {
        stringBuilder.append(text).append(System.lineSeparator());
    }

    public static void addAnnotation(String annotation, Editor editor, String fileName, SymbolInformation symbol,
        URI fileUri, EclipseLanguageClient languageClient) {
        if (!annotation.startsWith("@")) {
            annotation = "@" + annotation;
        }

        if (editor.getContents().containsKey(fileName)) {
            int line = symbol.getLocation().getRange().getStart().getLine();
            Position position = editor.insertBlankLine(fileName, line, true);
            editor.replace(fileName, position, position, annotation);

            FileEvent fileEvent = new FileEvent();
            fileEvent.setUri(fileUri);
            fileEvent.setType(FileChangeType.CHANGED);
            languageClient.notifyWatchedFilesChanged(Collections.singletonList(fileEvent));

            languageClient.listCodeActions(fileUri, symbol.getLocation().getRange())
                .stream().filter(ca -> ca.getKind().equals(CodeActionKind.SOURCE_ORGANIZEIMPORTS.toString()))
                .findFirst()
                .ifPresent(action -> {
                    if (action.getCommand() instanceof WorkspaceEditCommand) {
                        ((WorkspaceEditCommand) action.getCommand()).getArguments().forEach(workspaceEdit ->
                            Utils.applyWorkspaceEdit(workspaceEdit, editor, languageClient));
                    }
                });
        }
    }

    public static void removeAnnotation(String annotation, Editor editor, String fileName, SymbolInformation symbol,
        URI fileUri, EclipseLanguageClient languageClient) {
        if (!annotation.startsWith("@")) {
            annotation = "@" + annotation;
        }

        if (editor.getContents().containsKey(fileName)) {
            int line = symbol.getLocation().getRange().getStart().getLine();
            int annotationLine = -1;
            String lineContent = editor.getFileLine(fileName, line);
            while (!lineContent.trim().isEmpty()) {
                if (lineContent.trim().startsWith(annotation)) {
                    annotationLine = line;
                }
                lineContent = editor.getFileLine(fileName, --line);
            }
            if (annotationLine != -1) {
                Position start = new Position(annotationLine, 0);
                Position end = new Position(annotationLine + 1, 0);
                editor.replace(fileName, start, end, "");

                FileEvent fileEvent = new FileEvent();
                fileEvent.setUri(fileUri);
                fileEvent.setType(FileChangeType.CHANGED);
                languageClient.notifyWatchedFilesChanged(Collections.singletonList(fileEvent));

                languageClient.listCodeActions(fileUri, new Range(start, end))
                    .stream().filter(ca -> ca.getKind().equals(CodeActionKind.SOURCE_ORGANIZEIMPORTS.toString()))
                    .findFirst()
                    .ifPresent(action -> {
                        if (action.getCommand() instanceof WorkspaceEditCommand) {
                            ((WorkspaceEditCommand) action.getCommand()).getArguments().forEach(workspaceEdit ->
                                Utils.applyWorkspaceEdit(workspaceEdit, editor, languageClient));
                        }
                    });
            }
        }
    }

    private Utils() {
    }
}
