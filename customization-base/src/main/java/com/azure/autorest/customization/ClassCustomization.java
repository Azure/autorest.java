package com.azure.autorest.customization;

import com.azure.autorest.customization.implementation.Utils;
import com.azure.autorest.customization.implementation.ls.EclipseLanguageClient;
import com.azure.autorest.customization.implementation.ls.models.CodeActionKind;
import com.azure.autorest.customization.implementation.ls.models.FileChangeType;
import com.azure.autorest.customization.implementation.ls.models.FileEvent;
import com.azure.autorest.customization.implementation.ls.models.SymbolInformation;
import com.azure.autorest.customization.implementation.ls.models.SymbolKind;
import com.azure.autorest.customization.implementation.ls.models.TextEdit;
import com.azure.autorest.customization.implementation.ls.models.WorkspaceEdit;
import com.azure.autorest.customization.implementation.ls.models.WorkspaceEditCommand;
import com.azure.autorest.customization.models.Position;
import com.azure.autorest.customization.models.Range;

import java.lang.reflect.Modifier;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class level customization for an AutoRest generated class.
 */
public final class ClassCustomization {
    /*
     * This pattern attempts to find the first line of a method string that doesn't have a first non-space character of
     * '*' or '/'. From there it captures all word and space characters before and inside '( )' ignoring any trailing
     * spaces and an opening '{'.
     */
    private static final Pattern METHOD_SIGNATURE_PATTERN =
        Pattern.compile("^\\s*([^/*][\\w\\s]+\\([\\w\\s\\.]+\\))\\s*\\{?$", Pattern.MULTILINE);

    private final EclipseLanguageClient languageClient;
    private final Editor editor;
    private final String packageName;
    private final String className;
    private SymbolInformation classSymbol;

    ClassCustomization(Editor editor, EclipseLanguageClient languageClient, String packageName, String className, SymbolInformation classSymbol) {
        this.editor = editor;
        this.languageClient = languageClient;
        this.packageName = packageName;
        this.className = className;
        this.classSymbol = classSymbol;
    }

    /**
     * Gets the method level customization for a method in the class.
     *
     * @param methodNameOrSignature the method name or signature
     * @return the method level customization
     */
    public MethodCustomization getMethod(String methodNameOrSignature) {
        String methodName;
        String methodSignature = null;
        if (methodNameOrSignature.contains("(")) {
            // method signature
            methodSignature = methodNameOrSignature.replaceFirst("\\) *\\{", "").replaceFirst(" *public ", "").replaceFirst(" *private ", "");
            String returnTypeAndMethodName = methodNameOrSignature.split("\\(")[0];
            if (returnTypeAndMethodName.contains(" ")) {
                methodName = returnTypeAndMethodName.replaceAll(".* ", "");
            } else {
                methodName = returnTypeAndMethodName;
            }
        } else {
            methodName = methodNameOrSignature;
        }
        URI fileUri = classSymbol.getLocation().getUri();
        int i = fileUri.toString().indexOf("src/main/java/");
        String fileName = fileUri.toString().substring(i);
        Optional<SymbolInformation> methodSymbol = languageClient.listDocumentSymbols(fileUri)
            .stream().filter(si -> si.getName().replaceFirst("\\(.*\\)", "").equals(methodName) && si.getKind() == SymbolKind.METHOD)
            .filter(si -> editor.getFileLine(fileName, si.getLocation().getRange().getStart().getLine()).contains(methodNameOrSignature))
            .findFirst();
        if (!methodSymbol.isPresent()) {
            throw new IllegalArgumentException("Method " + methodNameOrSignature + " does not exist in class " + className);
        }
        if (methodSignature == null) {
            methodSignature = editor.getFileLine(fileName, methodSymbol.get().getLocation().getRange().getStart().getLine())
                .replaceFirst("\\) *\\{", "").replaceFirst(" *public ", "").replaceFirst(" *private ", "");
        }
        return new MethodCustomization(editor, languageClient, packageName, className, methodName, methodSignature, methodSymbol.get());
    }

    /**
     * Gets the property level customization for a property in the class.
     *
     * @param propertyName the property name
     * @return the property level customization
     */
    public PropertyCustomization getProperty(String propertyName) {
        return new PropertyCustomization(editor, languageClient, packageName, className, classSymbol, this, propertyName);
    }

    /**
     * Gets the Javadoc customization for this class.
     *
     * @return the Javadoc customization
     */
    public JavadocCustomization getJavadoc() {
        String packagePath = packageName.replace(".", "/");
        return new JavadocCustomization(editor, languageClient, packagePath, className, classSymbol.getLocation().getRange().getStart().getLine());
    }

    /**
     * Adds a method to this class.
     *
     * @param method the entire literal string of the method
     * @return the method level customization for the added method
     */
    public MethodCustomization addMethod(String method) {
        // Get the signature of the method.
        Matcher methodSignatureMatcher = METHOD_SIGNATURE_PATTERN.matcher(method);
        String methodSignature = null;
        if (methodSignatureMatcher.find()) {
            methodSignature = methodSignatureMatcher.group(1);
        }

        // find position
        URI fileUri = classSymbol.getLocation().getUri();
        int i = fileUri.toString().indexOf("src/main/java/");
        String fileName = fileUri.toString().substring(i);
        List<String> fileLines = editor.getFileLines(fileName);
        int lineNum = fileLines.size();
        String currentLine = fileLines.get(--lineNum);
        while (!currentLine.endsWith("}") || currentLine.startsWith("}")) {
            currentLine = fileLines.get(--lineNum);
        }
        editor.insertBlankLine(fileName, ++lineNum, false);
        Position newMethod = editor.insertBlankLine(fileName, ++lineNum, false);

        // replace
        editor.replace(fileName, newMethod, newMethod, method);
        FileEvent fileEvent = new FileEvent();
        fileEvent.setUri(fileUri);
        fileEvent.setType(FileChangeType.CHANGED);
        languageClient.notifyWatchedFilesChanged(Collections.singletonList(fileEvent));

        if (methodSignature == null) {
            methodSignature = editor.getFileLine(fileName, lineNum);
        }
        return getMethod(methodSignature);
    }

    /**
     * Renames a class in the package.
     *
     * @param newName the new simple name for this class
     */
    public ClassCustomization rename(String newName) {
        WorkspaceEdit workspaceEdit = languageClient.renameSymbol(classSymbol.getLocation().getUri(),
            classSymbol.getLocation().getRange().getStart(), newName);
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

        String packagePath = packageName.replace(".", "/");
        Optional<SymbolInformation> newClassSymbol = languageClient.findWorkspaceSymbol(newName)
            .stream().filter(si -> si.getLocation().getUri().toString().endsWith(packagePath + "/" + newName + ".java"))
            .findFirst();
        if (!newClassSymbol.isPresent()) {
            throw new IllegalArgumentException("Renamed failed with new class " + newName + " not found.");
        }
        return new ClassCustomization(editor, languageClient, packageName, newName, newClassSymbol.get());
    }

    /**
     * Replace the modifier for this class.
     * <p>
     * For compound modifiers such as {@code public abstract} use bitwise OR ({@code |}) of multiple Modifiers, {@code
     * Modifier.PUBLIC | Modifier.ABSTRACT}.
     *
     * @param modifiers The {@link Modifier Modifiers} for the class.
     * @return The updated ClassCustomization object.
     * @throws IllegalArgumentException If the {@code modifier} is less than or equal to {@code 0} or any {@link
     * Modifier} included in the bitwise OR isn't a valid class {@link Modifier}.
     */
    public ClassCustomization setModifier(int modifiers) {
        languageClient.listDocumentSymbols(classSymbol.getLocation().getUri())
            .stream().filter(si -> si.getName().equals(className) && si.getKind() == SymbolKind.CLASS)
            .findFirst()
            .ifPresent(symbolInformation ->
                Utils.replaceModifier(symbolInformation, editor, languageClient, (oldLine, newModifiers) ->
                        oldLine.replaceFirst("\\w.* class " + className, newModifiers + " class " + className),
                    Modifier.classModifiers(), modifiers));
        refreshSymbol();
        return this;
    }

    /**
     * Add an annotation on the class. The annotation class will be automatically imported.
     *
     * @param annotation the annotation to add to the class. The leading @ can be omitted.
     * @return the current class customization for chaining
     */
    public ClassCustomization addAnnotation(String annotation) {
        if (!annotation.startsWith("@")) {
            annotation = "@" + annotation;
        }

        URI fileUri = classSymbol.getLocation().getUri();
        Optional<SymbolInformation> symbol = languageClient.listDocumentSymbols(fileUri)
            .stream().filter(si -> si.getKind() == SymbolKind.CLASS)
            .findFirst();
        if (symbol.isPresent()) {
            int i = fileUri.toString().indexOf("src/main/java/");
            String fileName = fileUri.toString().substring(i);
            if (editor.getContents().containsKey(fileName)) {
                int line = symbol.get().getLocation().getRange().getStart().getLine();
                Position position = editor.insertBlankLine(fileName, line, true);
                editor.replace(fileName, position, position, annotation);

                FileEvent fileEvent = new FileEvent();
                fileEvent.setUri(fileUri);
                fileEvent.setType(FileChangeType.CHANGED);
                languageClient.notifyWatchedFilesChanged(Collections.singletonList(fileEvent));

                languageClient.listCodeActions(fileUri, symbol.get().getLocation().getRange()).stream()
                    .filter(ca -> ca.getKind().equals(CodeActionKind.SOURCE_ORGANIZEIMPORTS.toString()))
                    .findFirst()
                    .ifPresent(action -> {
                        if (action.getCommand() instanceof WorkspaceEditCommand) {
                            ((WorkspaceEditCommand) action.getCommand()).getArguments().forEach(workspaceEdit ->
                                Utils.applyWorkspaceEdit(workspaceEdit, editor, languageClient));
                        }
                    });
            }
        }
        refreshSymbol();
        return this;
    }

    /**
     * Remove an annotation from the class.
     *
     * @param annotation the annotation to remove from the class. The leading @ can be omitted.
     * @return the current class customization for chaining
     */
    public ClassCustomization removeAnnotation(String annotation) {
        if (!annotation.startsWith("@")) {
            annotation = "@" + annotation;
        }

        URI fileUri = classSymbol.getLocation().getUri();
        int i = fileUri.toString().indexOf("src/main/java/");
        String fileName = fileUri.toString().substring(i);
        if (editor.getContents().containsKey(fileName)) {
            Range range = editor.searchTextFirstOccurrence(fileName, annotation);
            if (range != null) {
                Position start = new Position(range.getStart().getLine(), 0);
                Position end = new Position(range.getStart().getLine() + 1, 0);
                editor.replace(fileName, start, end, "");

                FileEvent fileEvent = new FileEvent();
                fileEvent.setUri(fileUri);
                fileEvent.setType(FileChangeType.CHANGED);
                languageClient.notifyWatchedFilesChanged(Collections.singletonList(fileEvent));

                languageClient.listCodeActions(fileUri, range).stream()
                    .filter(ca -> ca.getKind().equals(CodeActionKind.SOURCE_ORGANIZEIMPORTS.toString()))
                    .findFirst()
                    .ifPresent(action -> {
                        if (action.getCommand() instanceof WorkspaceEditCommand) {
                            ((WorkspaceEditCommand) action.getCommand()).getArguments().forEach(workspaceEdit ->
                                Utils.applyWorkspaceEdit(workspaceEdit, editor, languageClient));
                        }
                    });
            }
        }
        refreshSymbol();
        return this;
    }

    /**
     * Rename an enum member if the current class is an enum class.
     *
     * @param enumMemberName the current enum member name
     * @param newName the new enum member name
     * @return the current class customization for chaining
     */
    public ClassCustomization renameEnumMember(String enumMemberName, String newName) {
        URI fileUri = classSymbol.getLocation().getUri();
        languageClient.listDocumentSymbols(fileUri).stream()
            .filter(si -> si.getName().toLowerCase().contains(enumMemberName.toLowerCase()))
            .forEach(symbol -> {
                WorkspaceEdit edit = languageClient.renameSymbol(fileUri, symbol.getLocation().getRange().getStart(),
                    newName);
                Utils.applyWorkspaceEdit(edit, editor, languageClient);
            });
        return this;
    }

    private void refreshSymbol() {
        this.classSymbol = new PackageCustomization(editor, languageClient, packageName).getClass(className).classSymbol;
    }
}
