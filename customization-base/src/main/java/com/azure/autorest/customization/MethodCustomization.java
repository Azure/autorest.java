package com.azure.autorest.customization;

import com.azure.autorest.customization.implementation.Utils;
import com.azure.autorest.customization.implementation.ls.EclipseLanguageClient;
import com.azure.autorest.customization.implementation.ls.models.CodeAction;
import com.azure.autorest.customization.implementation.ls.models.CodeActionKind;
import com.azure.autorest.customization.implementation.ls.models.FileChangeType;
import com.azure.autorest.customization.implementation.ls.models.FileEvent;
import com.azure.autorest.customization.implementation.ls.models.SymbolInformation;
import com.azure.autorest.customization.implementation.ls.models.SymbolKind;
import com.azure.autorest.customization.implementation.ls.models.TextEdit;
import com.azure.autorest.customization.implementation.ls.models.WorkspaceEdit;
import com.azure.autorest.customization.implementation.ls.models.WorkspaceEditCommand;
import com.azure.autorest.customization.models.Modifier;
import com.azure.autorest.customization.models.Position;
import com.azure.autorest.customization.models.Range;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


/**
 * The Javadoc customization for an AutoRest generated classes and methods.
 */
public final class MethodCustomization {
    private final EclipseLanguageClient languageClient;
    private final Editor editor;
    private final String packageName;
    private final String className;
    private final String methodName;
    private String methodSignature;
    private SymbolInformation symbol;

    MethodCustomization(Editor editor, EclipseLanguageClient languageClient, String packageName, String className, String methodName, String methodSignature, SymbolInformation symbol) {
        this.editor = editor;
        this.languageClient = languageClient;
        this.packageName = packageName;
        this.className = className;
        this.methodName = methodName;
        this.methodSignature = methodSignature;
        this.symbol = symbol;
    }

    /**
     * Gets the Javadoc customization for this method.
     *
     * @return the Javadoc customization
     */
    public JavadocCustomization getJavadoc() {
        String packagePath = packageName.replace(".", "/");
        return new JavadocCustomization(editor, languageClient, packagePath, className, symbol.getLocation().getRange().getStart().getLine());
    }

    /**
     * Rename a method in the class. This is a refactor operation. All references to this method across the client
     * library will be automatically modified.
     *
     * @param newName the new name for the method
     * @return the current class customization for chaining
     */
    public MethodCustomization rename(String newName) {
        URI fileUri = symbol.getLocation().getUri();
        WorkspaceEdit edit = languageClient.renameSymbol(fileUri, symbol.getLocation().getRange().getStart(), newName);
        Utils.applyWorkspaceEdit(edit, editor, languageClient);
        Optional<SymbolInformation> newMethodSymbol = languageClient.listDocumentSymbols(fileUri)
                .stream().filter(si -> si.getName().replaceFirst("\\(.*\\)", "").equals(newName) && si.getKind() == SymbolKind.METHOD)
                .findFirst();
        if (!newMethodSymbol.isPresent()) {
            throw new IllegalArgumentException("Renamed failed with new method " + newName + " not found.");
        }
        return new MethodCustomization(editor, languageClient, packageName, className, newName, methodSignature.replace(methodName + "(", newName + "("), newMethodSymbol.get());
    }

    /**
     * Add an annotation to a method in the class.
     *
     * @param annotation the annotation to add. The leading @ can be omitted.
     * @return the current class customization for chaining
     */
    public MethodCustomization addAnnotation(String annotation) {
        if (!annotation.startsWith("@")) {
            annotation = "@" + annotation;
        }

        URI fileUri = symbol.getLocation().getUri();
        int i = fileUri.toString().indexOf("src/main/java/");
        String fileName = fileUri.toString().substring(i);
        if (editor.getContents().containsKey(fileName)) {
            int line = symbol.getLocation().getRange().getStart().getLine();
            Position position = editor.insertBlankLine(fileName, line, true);
            editor.replace(fileName, position, position, annotation);

            FileEvent fileEvent = new FileEvent();
            fileEvent.setUri(fileUri);
            fileEvent.setType(FileChangeType.CHANGED);
            languageClient.notifyWatchedFilesChanged(Collections.singletonList(fileEvent));

            Optional<CodeAction> generateAccessors = languageClient.listCodeActions(fileUri, symbol.getLocation().getRange())
                    .stream().filter(ca -> ca.getKind().equals(CodeActionKind.SOURCE_ORGANIZEIMPORTS.toString()))
                    .findFirst();
            if (generateAccessors.isPresent()) {
                WorkspaceEditCommand command;
                if (generateAccessors.get().getCommand() instanceof WorkspaceEditCommand) {
                    command = (WorkspaceEditCommand) generateAccessors.get().getCommand();
                    for(WorkspaceEdit workspaceEdit : command.getArguments()) {
                        Utils.applyWorkspaceEdit(workspaceEdit, editor, languageClient);
                    }
                }
            }
        }
        refreshSymbol();
        return this;
    }

    /**
     * Remove an annotation from the method.
     *
     * @param annotation the annotation to remove from the method. The leading @ can be omitted.
     * @return the current method customization for chaining
     */
    public MethodCustomization removeAnnotation(String annotation) {
        if (!annotation.startsWith("@")) {
            annotation = "@" + annotation;
        }

        URI fileUri = symbol.getLocation().getUri();
        int i = fileUri.toString().indexOf("src/main/java/");
        String fileName = fileUri.toString().substring(i);
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

                Optional<CodeAction> generateAccessors = languageClient.listCodeActions(fileUri, new Range(start, end))
                        .stream().filter(ca -> ca.getKind().equals(CodeActionKind.SOURCE_ORGANIZEIMPORTS.toString()))
                        .findFirst();
                if (generateAccessors.isPresent()) {
                    WorkspaceEditCommand command;
                    if (generateAccessors.get().getCommand() instanceof WorkspaceEditCommand) {
                        command = (WorkspaceEditCommand) generateAccessors.get().getCommand();
                        for (WorkspaceEdit workspaceEdit : command.getArguments()) {
                            Utils.applyWorkspaceEdit(workspaceEdit, editor, languageClient);
                        }
                    }
                }
            }
        }
        refreshSymbol();
        return this;
    }

    /**
     * Replace the modifiers for this method.
     * <p>
     * If {@code modifier} is null the method modifiers are left unchanged.
     *
     * @param modifier The {@link Modifier} for this method.
     * @return The updated MethodCustomization object.
     */
    public MethodCustomization setModifier(Modifier modifier) {
        return setModifiers(modifier);
    }

    /**
     * Replace the modifiers for this method.
     * <p>
     * If {@code modifiers} is null or empty the method modifiers are left unchanged.
     *
     * @param modifiers The {@link Modifier Modifiers} for this method.
     * @return The updated MethodCustomization object.
     */
    public MethodCustomization setModifiers(Modifier... modifiers) {
        if (Utils.isNullOrEmpty(modifiers)) {
            return this;
        }

        Utils.replaceModifier(symbol, editor, languageClient, (oldLine, newModifiers) ->
            oldLine.replaceFirst("(\\w.* )?(\\w+) " + methodName + "\\(", newModifiers + "$2 " + methodName + "("),
            modifiers);
        refreshSymbol();
        return this;
    }

    /**
     * Change the return type of the method. The new return type will be automatically imported.
     *
     * <p>
     * The {@code returnValueFormatter} can be used to transform the return value. If the original return type is
     * {@code void}, simply pass the new return expression to {@code returnValueFormatter}; if the new return type is
     * {@code void}, pass {@code null} to {@code returnValueFormatter}; if either the original return type nor the new
     * return type is {@code void}, the {@code returnValueFormatter} should be a String formatter that contains
     * exactly 1 instance of {@code %s}.
     *
     * @param newReturnType the simple name of the new return type
     * @param returnValueFormatter the return value String formatter as described above
     * @param replaceReturnStatement if set to {@code true}, the return statement will be replaced by the provided
     * returnValueFormatter text with exactly one instance of {@code %s}. If set to true, appropriate semi-colons,
     * parentheses, opening and closing of code blocks have to be taken care of in the {@code returnValueFormatter}.
     * @return the current class customization for chaining
     */
    public MethodCustomization setReturnType(String newReturnType, String returnValueFormatter, boolean replaceReturnStatement) {
        URI fileUri = symbol.getLocation().getUri();
        int i = fileUri.toString().indexOf("src/main/java/");
        String fileName = fileUri.toString().substring(i);

        List<TextEdit> edits = new ArrayList<>();

        int line = symbol.getLocation().getRange().getStart().getLine();
        Position start = new Position(line, 0);
        String oldLineContent = editor.getFileLine(fileName, line);
        Position end = new Position(line, oldLineContent.length());
        String newLineContent = oldLineContent.replaceFirst("(\\w.* )?(\\w+) " + methodName + "\\(", "$1" + newReturnType + " " + methodName + "(");
        TextEdit signatureEdit = new TextEdit();
        signatureEdit.setNewText(newLineContent);
        signatureEdit.setRange(new Range(start, end));
        edits.add(signatureEdit);

        String methodIndent = editor.getFileLine(fileName, line).replaceAll("\\w.*$", "");
        String methodContentIndent = editor.getFileLine(fileName, line + 1).replaceAll("\\w.*$", "");
        String oldReturnType = oldLineContent.replaceAll(" " + methodName + "\\(.*", "").replaceFirst(methodIndent + "(\\w.* )?", "").trim();
        int returnLine = -1;
        while (!oldLineContent.startsWith(methodIndent + "}")) {
            if (oldLineContent.contains("return ")) {
                returnLine = line;
            }
            oldLineContent = editor.getFileLine(fileName, ++ line);
        }
        if (returnLine == -1) {
            // no return statement, originally void return type
            editor.insertBlankLine(fileName, line, false);
            FileEvent blankLineEvent = new FileEvent();
            blankLineEvent.setUri(fileUri);
            blankLineEvent.setType(FileChangeType.CHANGED);
            languageClient.notifyWatchedFilesChanged(Collections.singletonList(blankLineEvent));

            TextEdit returnEdit = new TextEdit();
            returnEdit.setRange(new Range(new Position(line, 0), new Position(line, 0)));
            returnEdit.setNewText(methodContentIndent + "return " + returnValueFormatter + ";");
            edits.add(returnEdit);
        } else if (newReturnType.equals("void")) {
            // remove return statement
            TextEdit returnEdit = new TextEdit();
            returnEdit.setNewText("");
            returnEdit.setRange(new Range(new Position(returnLine, 0), new Position(line, 0)));
            edits.add(returnEdit);
        } else {
            // replace return statement
            TextEdit returnValueEdit = new TextEdit();
            String returnLineText = editor.getFileLine(fileName, returnLine);
            returnValueEdit.setRange(new Range(new Position(returnLine, 0), new Position(returnLine, returnLineText.length())));
            returnValueEdit.setNewText(returnLineText.replace("return ", oldReturnType + " returnValue = "));
            edits.add(returnValueEdit);

            editor.insertBlankLine(fileName, line, false);
            FileEvent blankLineEvent = new FileEvent();
            blankLineEvent.setUri(fileUri);
            blankLineEvent.setType(FileChangeType.CHANGED);
            languageClient.notifyWatchedFilesChanged(Collections.singletonList(blankLineEvent));

            TextEdit returnEdit = new TextEdit();
            returnEdit.setRange(new Range(new Position(line, 0), new Position(line, 0)));

            if (replaceReturnStatement) {
                returnEdit.setNewText(String.format(returnValueFormatter, "returnValue"));
            } else {
                returnEdit.setNewText(methodContentIndent + "return " + String.format(returnValueFormatter, "returnValue") + ";");
            }

            edits.add(returnEdit);
        }

        WorkspaceEdit workspaceEdit = new WorkspaceEdit();
        workspaceEdit.setChanges(Collections.singletonMap(fileUri, edits));
        Utils.applyWorkspaceEdit(workspaceEdit, editor, languageClient);

        Optional<CodeAction> organizeImports = languageClient.listCodeActions(fileUri, new Range(start, end))
                .stream().filter(ca -> ca.getKind().equals(CodeActionKind.SOURCE_ORGANIZEIMPORTS.toString()))
                .findFirst();
        if (organizeImports.isPresent()) {
            WorkspaceEditCommand command;
            if (organizeImports.get().getCommand() instanceof WorkspaceEditCommand) {
                command = (WorkspaceEditCommand) organizeImports.get().getCommand();
                for(WorkspaceEdit importEdit : command.getArguments()) {
                    Utils.applyWorkspaceEdit(importEdit, editor, languageClient);
                }
            }
        }
        methodSignature = methodSignature.replace(oldReturnType + " " + methodName, newReturnType + " " + methodName);
        refreshSymbol();
        return this;
    }

    /**
     * Change the return type of a method. The new return type will be automatically imported.
     *
     * <p>
     * The {@code returnValueFormatter} can be used to transform the return value. If the original return type is
     * {@code void}, simply pass the new return expression to {@code returnValueFormatter}; if the new return type is
     * {@code void}, pass {@code null} to {@code returnValueFormatter}; if either the original return type nor the new
     * return type is {@code void}, the {@code returnValueFormatter} should be a String formatter that contains
     * exactly 1 instance of {@code %s}.
     *
     * @param newReturnType the simple name of the new return type
     * @param returnValueFormatter the return value String formatter as described above
     * @return the current class customization for chaining
     */
    public MethodCustomization setReturnType(String newReturnType, String returnValueFormatter) {
        return setReturnType(newReturnType, returnValueFormatter, false);
    }

    private void refreshSymbol() {
        URI fileUri = symbol.getLocation().getUri();
        int i = fileUri.toString().indexOf("src/main/java/");
        String fileName = fileUri.toString().substring(i);
        Optional<SymbolInformation> methodSymbol = languageClient.listDocumentSymbols(fileUri)
                .stream().filter(si -> si.getName().replaceFirst("\\(.*\\)", "").equals(methodName) && si.getKind() == SymbolKind.METHOD)
                .filter(si -> editor.getFileLine(fileName, si.getLocation().getRange().getStart().getLine()).contains(methodSignature))
                .findFirst();
        if (!methodSymbol.isPresent()) {
            throw new IllegalArgumentException("Method " + methodSignature + " does not exist in class " + className);
        }
        symbol = methodSymbol.get();
    }
}
