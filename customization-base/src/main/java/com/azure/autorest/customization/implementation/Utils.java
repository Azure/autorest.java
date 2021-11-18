package com.azure.autorest.customization.implementation;

import com.azure.autorest.customization.ClassCustomization;
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
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Utils {
    /**
     * This pattern matches a single line Javadoc and captures its content in group 1.
     */
    public static final Pattern SINGLE_LINE_JAVADOC_PATTERN = Pattern.compile("^\\s*\\/\\*\\*(.*?)\\*\\/\\s*$");

    /**
     * This pattern matches a Javadoc start line.
     */
    public static final Pattern JAVADOC_START_PATTERN = Pattern.compile("^\\s*\\/\\*\\*\\s*$");

    /**
     * This pattern matches a Javadoc end line.
     */
    public static final Pattern JAVADOC_END_PATTERN = Pattern.compile("^\\s*\\*\\/\\s*$");

    /**
     * This pattern determines the indentation of the passed string. Effectively it creates a group containing all
     * spaces before the first word character.
     */
    public static final Pattern INDENT_DETERMINATION_PATTERN = Pattern.compile("^(\\s*).*$");

    /**
     * This pattern matches a Java package declaration.
     */
    private static final Pattern PACKAGE_PATTERN = Pattern.compile("package\\s[\\w\\.]+;");

    /**
     * This pattern matches a Java import.
     */
    private static final Pattern IMPORT_PATTERN = Pattern.compile("import\\s(?:static\\s)?[\\w\\.]+;");

    /**
     * This pattern matches anything then the space.
     */
    public static final Pattern ANYTHING_THEN_SPACE_PATTERN = Pattern.compile(".* ");

    /*
     * This pattern determines if a line is a beginning of constructor or method. The following is an explanation of
     * the pattern:
     *
     * 1. Capture all leading space characters.
     * 2. Capture all modifiers for the constructor or method.
     * 3. If a method, capture the return type.
     * 4. Capture the name of the constructor or method.
     *
     * The following are the groups return:
     *
     * 1. The entire matching declaration from the beginning of the string used to determine the beginning offset of the
     * parameters in the constructor or method.
     * 2. Any modifiers for the constructor or method. This may be empty/null.
     * 3. If a method, the return type. If a constructor, empty/null.
     * 4. The name of the constructor or method.
     */
    private static final Pattern BEGINNING_OF_PARAMETERS_PATTERN =
        Pattern.compile("^(\\s*(?:([\\w\\s]*?)\\s)?(?:([a-zA-Z$_][\\w]*?)\\s+)?([a-zA-Z$_][\\w]*?)\\s*)\\(.*$");

    private static final Pattern ENDING_OF_PARAMETERS_PATTERN = Pattern.compile("^(.*)\\)\\s*\\{.*$");

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

    /**
     * Walks down the lines of a file until the line matches a predicate.
     *
     * @param editor The editor containing the file's information.
     * @param fileName The name of the file.
     * @param startLine The line to start walking.
     * @param linePredicate The predicate that determines when a matching line is found.
     * @return The first line that matches the predicate. If no line in the file matches the predicate {@code -1} is
     * returned.
     */
    public static int walkDownFileUntilLineMatches(Editor editor, String fileName, int startLine,
        Predicate<String> linePredicate) {
        return walkFileUntilLineMatches(editor, fileName, startLine, linePredicate, true);
    }

    /**
     * Walks up the lines of a file until the line matches a predicate.
     *
     * @param editor The editor containing the file's information.
     * @param fileName The name of the file.
     * @param startLine The line to start walking.
     * @param linePredicate The predicate that determines when a matching line is found.
     * @return The first line that matches the predicate. If no line in the file matches the predicate {@code -1} is
     * returned.
     */
    public static int walkUpFileUntilLineMatches(Editor editor, String fileName, int startLine,
        Predicate<String> linePredicate) {
        return walkFileUntilLineMatches(editor, fileName, startLine, linePredicate, false);
    }

    private static int walkFileUntilLineMatches(Editor editor, String fileName, int startLine,
        Predicate<String> linePredicate, boolean isWalkingDown) {
        int matchingLine = -1;

        List<String> fileLines = editor.getFileLines(fileName);
        if (isWalkingDown) {
            for (int line = startLine; line < fileLines.size(); line++) {
                if (linePredicate.test(fileLines.get(line))) {
                    matchingLine = line;
                    break;
                }
            }
        } else {
            for (int line = startLine; line >= 0; line--) {
                if (linePredicate.test(fileLines.get(line))) {
                    matchingLine = line;
                    break;
                }
            }
        }

        return matchingLine;
    }

    /**
     * Utility method to add an annotation to a code block.
     *
     * @param annotation The annotation to add.
     * @param customization The customization having an annotation added.
     * @param refreshedCustomizationSupplier A supplier that returns a refreshed customization after the annotation is
     * added.
     * @param <T> The type of the customization.
     * @return A refreshed customization after the annotation was added.
     */
    public static <T extends CodeCustomization> T addAnnotation(String annotation, CodeCustomization customization,
        Supplier<T> refreshedCustomizationSupplier) {
        SymbolInformation symbol = customization.getSymbol();
        Editor editor = customization.getEditor();
        String fileName = customization.getFileName();
        URI fileUri = customization.getFileUri();
        EclipseLanguageClient languageClient = customization.getLanguageClient();

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

            organizeImportsOnRange(languageClient, editor, fileUri, symbol.getLocation().getRange());
        }

        return refreshedCustomizationSupplier.get();
    }

    /**
     * Utility method to remove an annotation from a code block.
     *
     * @param annotation The annotation to remove.
     * @param customization The customization having an annotation removed.
     * @param refreshedCustomizationSupplier A supplier that returns a refreshed customization after the annotation is
     * removed.
     * @param <T> The type of the customization.
     * @return A refreshed customization after the annotation was removed.
     */
    public static <T extends CodeCustomization> T removeAnnotation(String annotation, CodeCustomization customization,
        Supplier<T> refreshedCustomizationSupplier) {
        SymbolInformation symbol = customization.getSymbol();
        Editor editor = customization.getEditor();
        String fileName = customization.getFileName();
        URI fileUri = customization.getFileUri();
        EclipseLanguageClient languageClient = customization.getLanguageClient();

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

                organizeImportsOnRange(languageClient, editor, fileUri, new Range(start, end));
            }
        }

        return refreshedCustomizationSupplier.get();
    }

    /**
     * Utility method to replace a body of a code block.
     *
     * @param newBody The new body.
     * @param customization The customization having its body replaced.
     * @param refreshedCustomizationSupplier A supplier that returns a refreshed customization after the body is
     * replaced.
     * @param <T> The type of the customization.
     * @return A refreshed customization after the body was replaced.
     */
    public static <T extends CodeCustomization> T replaceBody(String newBody, CodeCustomization customization,
        Supplier<T> refreshedCustomizationSupplier) {
        SymbolInformation symbol = customization.getSymbol();
        Editor editor = customization.getEditor();
        String fileName = customization.getFileName();

        int line = symbol.getLocation().getRange().getStart().getLine();
        String methodBlockIndent = getIndent(editor.getFileLine(fileName, line));

        // Loop until the line containing the body start is found.
        Pattern startPattern = Pattern.compile(".*\\{\\s*");
        int startLine = walkDownFileUntilLineMatches(editor, fileName, line, lineContent ->
            startPattern.matcher(lineContent).matches()) + 1; // Plus one since the start is after the opening '{'

        // Then determine the base indentation level for the body.
        String methodContentIndent = getIndent(editor.getFileLine(fileName, startLine));
        Position oldBodyStart = new Position(startLine, methodContentIndent.length());

        // Then continue iterating over lines until the body close line is found.
        Pattern closePattern = Pattern.compile(methodBlockIndent + "}\\s*");
        int lastLine = walkDownFileUntilLineMatches(editor, fileName, startLine, lineContent ->
            closePattern.matcher(lineContent).matches()) - 1; // Minus one since the end is before the closing '}'
        Position oldBodyEnd = new Position(lastLine, editor.getFileLine(fileName, lastLine).length());

        editor.replaceWithIndentedContent(fileName, oldBodyStart, oldBodyEnd, newBody, methodContentIndent.length());
        FileEvent fileEvent = new FileEvent();
        fileEvent.setUri(customization.getFileUri());
        fileEvent.setType(FileChangeType.CHANGED);
        customization.getLanguageClient().notifyWatchedFilesChanged(Collections.singletonList(fileEvent));

        // Return the refreshed customization.
        return refreshedCustomizationSupplier.get();
    }

    public static <T extends CodeCustomization> T replaceParameters(String newParameters,
        CodeCustomization customization, Supplier<T> refreshCustomizationSupplier) {
        SymbolInformation symbol = customization.getSymbol();
        Editor editor = customization.getEditor();
        String fileName = customization.getFileName();
        URI fileUri = customization.getFileUri();
        EclipseLanguageClient languageClient = customization.getLanguageClient();

        // Beginning line of the symbol.
        int line = symbol.getLocation().getRange().getStart().getLine();

        // First find the starting location of the parameters.
        // The beginning of the parameters may not be on the same line as the start of the signature.
        Matcher matcher = BEGINNING_OF_PARAMETERS_PATTERN.matcher(editor.getFileLine(fileName, line));
        while (!matcher.matches()) {
            matcher = BEGINNING_OF_PARAMETERS_PATTERN.matcher(editor.getFileLine(fileName, ++line));
        }

        // Now that the line where the parameters begin is found create its position.
        // Starting character is inclusive of the character offset, so add one as ')' isn't included in the capture.
        Position parametersStart = new Position(line, matcher.group(1).length() + 1);

        // Then find where the parameters end.
        // The ending of the parameters may not be on the same line as the start of the parameters.
        matcher = ENDING_OF_PARAMETERS_PATTERN.matcher(editor.getFileLine(fileName, line));
        while (!matcher.matches()) {
            matcher = ENDING_OF_PARAMETERS_PATTERN.matcher(editor.getFileLine(fileName, ++line));
        }

        // Now that the line where the parameters end is found gets create its position.
        // Ending character is exclusive of the character offset.
        Position parametersEnd = new Position(line, matcher.group(1).length());

        editor.replace(fileName, parametersStart, parametersEnd, newParameters);

        FileEvent fileEvent = new FileEvent();
        fileEvent.setUri(fileUri);
        fileEvent.setType(FileChangeType.CHANGED);
        languageClient.notifyWatchedFilesChanged(Collections.singletonList(fileEvent));

        return refreshCustomizationSupplier.get();
    }

    public static String getIndent(String content) {
        Matcher matcher = INDENT_DETERMINATION_PATTERN.matcher(content);
        return matcher.matches() ? matcher.group(1) : "";
    }

    /**
     * Adds imports to the customization.
     *
     * @param importsToAdd Imports to add.
     * @param customization Code customization to add imports.
     * @param refreshCustomizationSupplier A supplier that returns a refreshed customization after the imports are
     * added.
     * @param <T> Type of the customization.
     * @return A refreshed customization.
     */
    public static <T extends CodeCustomization> T addImports(List<String> importsToAdd,
        ClassCustomization customization, Supplier<T> refreshCustomizationSupplier) {
        CodeCustomization codeCustomization = customization;
        EclipseLanguageClient languageClient = codeCustomization.getLanguageClient();
        Editor editor = codeCustomization.getEditor();
        URI fileUri = codeCustomization.getFileUri();
        String fileName = codeCustomization.getFileName();

        // Only add imports if they exist.
        if (!isNullOrEmpty(importsToAdd)) {
            // Always place imports after the package.
            // The language server will format the imports once added, so location doesn't matter.
            int importLine = Utils.walkDownFileUntilLineMatches(editor, fileName, 0,
                line -> PACKAGE_PATTERN.matcher(line).matches()) + 1;

            Position importPosition = new Position(importLine, 0);
            String imports = importsToAdd.stream()
                .map(importToAdd -> "import " + importToAdd + ";")
                .collect(Collectors.joining("\n"));

            editor.insertBlankLine(fileName, importLine, false);
            editor.replace(fileName, importPosition, importPosition, imports);
        }

        FileEvent fileEvent = new FileEvent();
        fileEvent.setUri(fileUri);
        fileEvent.setType(FileChangeType.CHANGED);
        languageClient.notifyWatchedFilesChanged(Collections.singletonList(fileEvent));

        return refreshCustomizationSupplier.get();
    }

    public static void organizeImportsOnRange(EclipseLanguageClient languageClient, Editor editor, URI fileUri,
        Range range) {
        languageClient.listCodeActions(fileUri, range).stream()
            .filter(ca -> ca.getKind().equals(CodeActionKind.SOURCE_ORGANIZEIMPORTS.toString()))
            .findFirst()
            .ifPresent(action -> {
                if (action.getCommand() instanceof WorkspaceEditCommand) {
                    ((WorkspaceEditCommand) action.getCommand()).getArguments().forEach(importEdit ->
                        Utils.applyWorkspaceEdit(importEdit, editor, languageClient));
                }
            });
    }

    private Utils() {
    }
}
