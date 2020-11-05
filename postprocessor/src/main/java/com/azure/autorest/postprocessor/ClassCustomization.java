package com.azure.autorest.postprocessor;

import com.azure.autorest.postprocessor.ls.EclipseLanguageClient;
import com.azure.autorest.postprocessor.ls.models.CodeAction;
import com.azure.autorest.postprocessor.ls.models.CodeActionKind;
import com.azure.autorest.postprocessor.ls.models.FileChangeType;
import com.azure.autorest.postprocessor.ls.models.FileEvent;
import com.azure.autorest.postprocessor.ls.models.JavaCodeActionKind;
import com.azure.autorest.postprocessor.ls.models.Position;
import com.azure.autorest.postprocessor.ls.models.Range;
import com.azure.autorest.postprocessor.ls.models.SymbolInformation;
import com.azure.autorest.postprocessor.ls.models.SymbolKind;
import com.azure.autorest.postprocessor.ls.models.TextEdit;
import com.azure.autorest.postprocessor.ls.models.WorkspaceEdit;
import com.azure.autorest.postprocessor.ls.models.WorkspaceEditCommand;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClassCustomization {
    final EclipseLanguageClient languageClient;
    final Editor editor;
    final String packageName;
    final String className;

    ClassCustomization(Editor editor, EclipseLanguageClient languageClient, String packageName, String className) {
        this.editor = editor;
        this.languageClient = languageClient;
        this.packageName = packageName;
        this.className = className;
    }

    public JavadocCustomization classJavadoc() {
        String packagePath = packageName.replace(".", "/");
        Optional<SymbolInformation> classSymbol = languageClient.findWorkspaceSymbol(className)
                .stream().filter(si -> si.getLocation().getUri().toString().endsWith(packagePath + "/" + className + ".java"))
                .findFirst();

        if (classSymbol.isPresent()) {
            return new JavadocCustomization(editor, languageClient, packagePath, className, classSymbol.get().getLocation().getRange().getStart().getLine());
        }
        return null;
    }

    public JavadocCustomization methodJavadoc(String methodName) {
        String packagePath = packageName.replace(".", "/");
        Optional<SymbolInformation> classSymbol = languageClient.findWorkspaceSymbol(className)
                .stream().filter(si -> si.getLocation().getUri().toString().endsWith(packagePath + "/" + className + ".java"))
                .findFirst();

        if (classSymbol.isPresent()) {
            URI fileUri = classSymbol.get().getLocation().getUri();
            Optional<SymbolInformation> symbol = languageClient.listDocumentSymbols(fileUri)
                    .stream().filter(si -> si.getName().replaceFirst("\\(.*\\)", "").equals(methodName) && si.getKind() == SymbolKind.METHOD)
                    .findFirst();
            if (symbol.isPresent()) {
                return new JavadocCustomization(editor, languageClient, packagePath, className, symbol.get().getLocation().getRange().getStart().getLine());
            }
        }
        return null;
    }

    public ClassCustomization changeClassModifier(String modifier) {
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
                String newLineContent = oldLineContent.replaceFirst("\\w.* class " + className, modifier + " class " + className);
                TextEdit textEdit = new TextEdit();
                textEdit.setNewText(newLineContent);
                textEdit.setRange(new Range(start, end));
                WorkspaceEdit workspaceEdit = new WorkspaceEdit();
                workspaceEdit.setChanges(Collections.singletonMap(fileUri, Collections.singletonList(textEdit)));
                Utils.applyWorkspaceEdit(workspaceEdit, editor, languageClient);
            }
        }
        return this;
    }

    public ClassCustomization addClassAnnotation(String annotation) {
        if (!annotation.startsWith("@")) {
            annotation = "@" + annotation;
        }

        String packagePath = packageName.replace(".", "/");
        Optional<SymbolInformation> classSymbol = languageClient.findWorkspaceSymbol(className)
                .stream().filter(si -> si.getLocation().getUri().toString().endsWith(packagePath + "/" + className + ".java"))
                .findFirst();

        if (classSymbol.isPresent()) {
            URI fileUri = classSymbol.get().getLocation().getUri();
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

                    Optional<CodeAction> organizeImports = languageClient.listCodeActions(fileUri, symbol.get().getLocation().getRange())
                            .stream().filter(ca -> ca.getKind().equals(CodeActionKind.SOURCE_ORGANIZEIMPORTS.toString()))
                            .findFirst();
                    if (organizeImports.isPresent()) {
                        WorkspaceEditCommand command;
                        if (organizeImports.get().getCommand() instanceof WorkspaceEditCommand) {
                            command = (WorkspaceEditCommand) organizeImports.get().getCommand();
                            for(WorkspaceEdit workspaceEdit : command.getArguments()) {
                                Utils.applyWorkspaceEdit(workspaceEdit, editor, languageClient);
                            }
                        }
                    }
                }
            }
        }
        return this;
    }

    public ClassCustomization removeClassAnnotation(String annotation) {
        if (!annotation.startsWith("@")) {
            annotation = "@" + annotation;
        }

        String packagePath = packageName.replace(".", "/");
        Optional<SymbolInformation> classSymbol = languageClient.findWorkspaceSymbol(className)
                .stream().filter(si -> si.getLocation().getUri().toString().endsWith(packagePath + "/" + className + ".java"))
                .findFirst();

        if (classSymbol.isPresent()) {
            URI fileUri = classSymbol.get().getLocation().getUri();
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

                    Optional<CodeAction> generateAccessors = languageClient.listCodeActions(fileUri, range)
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
        }
        return this;
    }

    public ClassCustomization renameMethod(String methodName, String newName) {
        String packagePath = packageName.replace(".", "/");
        Optional<SymbolInformation> classSymbol = languageClient.findWorkspaceSymbol(className)
                .stream().filter(si -> si.getLocation().getUri().toString().endsWith(packagePath + "/" + className + ".java"))
                .findFirst();

        if (classSymbol.isPresent()) {
            URI fileUri = classSymbol.get().getLocation().getUri();
            Optional<SymbolInformation> symbol = languageClient.listDocumentSymbols(fileUri)
                    .stream().filter(si -> si.getName().replaceFirst("\\(.*\\)", "").equals(methodName) && si.getKind() == SymbolKind.METHOD)
                    .findFirst();
            if (symbol.isPresent()) {
                WorkspaceEdit edit = languageClient.renameSymbol(fileUri, symbol.get().getLocation().getRange().getStart(), newName);
                Utils.applyWorkspaceEdit(edit, editor, languageClient);
            }
        }
        return this;
    }

    public ClassCustomization addMethodAnnotation(String methodName, String annotation) {
        if (!annotation.startsWith("@")) {
            annotation = "@" + annotation;
        }

        String packagePath = packageName.replace(".", "/");
        Optional<SymbolInformation> classSymbol = languageClient.findWorkspaceSymbol(className)
                .stream().filter(si -> si.getLocation().getUri().toString().endsWith(packagePath + "/" + className + ".java"))
                .findFirst();

        if (classSymbol.isPresent()) {
            URI fileUri = classSymbol.get().getLocation().getUri();
            Optional<SymbolInformation> symbol = languageClient.listDocumentSymbols(fileUri)
                    .stream().filter(si -> si.getName().replaceFirst("\\(.*\\)", "").equals(methodName) && si.getKind() == SymbolKind.METHOD)
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

                    Optional<CodeAction> generateAccessors = languageClient.listCodeActions(fileUri, symbol.get().getLocation().getRange())
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
            }
        }
        return this;
    }

    public ClassCustomization generateGetterAndSetter(String propertyName) {
        String packagePath = packageName.replace(".", "/");
        Optional<SymbolInformation> classSymbol = languageClient.findWorkspaceSymbol(className)
                .stream().filter(si -> si.getLocation().getUri().toString().endsWith(packagePath + "/" + className + ".java"))
                .findFirst();

        if (classSymbol.isPresent()) {
            URI fileUri = classSymbol.get().getLocation().getUri();
            Optional<SymbolInformation> symbol = languageClient.listDocumentSymbols(fileUri)
                    .stream().filter(si -> si.getName().equals(propertyName) && si.getKind() == SymbolKind.FIELD)
                    .findFirst();
            if (symbol.isPresent()) {
                Optional<CodeAction> generateAccessors = languageClient.listCodeActions(fileUri, symbol.get().getLocation().getRange())
                        .stream().filter(ca -> ca.getKind().equals(JavaCodeActionKind.SOURCE_GENERATE_ACCESSORS.toString()))
                        .findFirst();
                if (generateAccessors.isPresent()) {
                    WorkspaceEditCommand command;
                    if (generateAccessors.get().getCommand() instanceof WorkspaceEditCommand) {
                        command = (WorkspaceEditCommand) generateAccessors.get().getCommand();
                        for (WorkspaceEdit workspaceEdit : command.getArguments()) {
                            Utils.applyWorkspaceEdit(workspaceEdit, editor, languageClient);
                        }
                        List<TextEdit> formats = languageClient.format(fileUri);
                        Utils.applyTextEdits(fileUri, formats, editor, languageClient);

                        String setterMethod = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
                        changeMethodReturnType(setterMethod, className, "this");
                    }
                }
            }
        }
        return this;
    }

    public ClassCustomization renameProperty(String propertyName, String newName) {
        String packagePath = packageName.replace(".", "/");
        Optional<SymbolInformation> classSymbol = languageClient.findWorkspaceSymbol(className)
                .stream().filter(si -> si.getLocation().getUri().toString().endsWith(packagePath + "/" + className + ".java"))
                .findFirst();

        if (classSymbol.isPresent()) {
            URI fileUri = classSymbol.get().getLocation().getUri();
            List<SymbolInformation> symbols = languageClient.listDocumentSymbols(fileUri)
                    .stream().filter(si -> si.getName().toLowerCase().contains(propertyName.toLowerCase()))
                    .collect(Collectors.toList());
            String propertyPascalName = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
            String newPascalName = newName.substring(0, 1).toUpperCase() + newName.substring(1);
            for (SymbolInformation symbol : symbols) {
                if (symbol.getKind() == SymbolKind.FIELD) {
                    WorkspaceEdit edit = languageClient.renameSymbol(fileUri, symbol.getLocation().getRange().getStart(), newName);
                    Utils.applyWorkspaceEdit(edit, editor, languageClient);
                } else if (symbol.getKind() == SymbolKind.METHOD) {
                    String methodName = symbol.getName().replace(propertyPascalName, newPascalName)
                            .replace(propertyName, newName).replaceFirst("\\(.*\\)", "");
                    WorkspaceEdit edit = languageClient.renameSymbol(fileUri, symbol.getLocation().getRange().getStart(), methodName);
                    Utils.applyWorkspaceEdit(edit, editor, languageClient);
                }
            }
        }
        return this;
    }

    public ClassCustomization renameEnumMember(String enumMemberName, String newName) {
        String packagePath = packageName.replace(".", "/");
        Optional<SymbolInformation> classSymbol = languageClient.findWorkspaceSymbol(className)
                .stream().filter(si -> si.getLocation().getUri().toString().endsWith(packagePath + "/" + className + ".java"))
                .findFirst();

        if (classSymbol.isPresent()) {
            URI fileUri = classSymbol.get().getLocation().getUri();
            List<SymbolInformation> symbols = languageClient.listDocumentSymbols(fileUri)
                    .stream().filter(si -> si.getName().toLowerCase().contains(enumMemberName.toLowerCase()))
                    .collect(Collectors.toList());
            for (SymbolInformation symbol : symbols) {
                WorkspaceEdit edit = languageClient.renameSymbol(fileUri, symbol.getLocation().getRange().getStart(), newName);
                Utils.applyWorkspaceEdit(edit, editor, languageClient);
            }
        }
        return this;
    }

    public ClassCustomization changeMethodModifier(String methodName, String modifier) {
        String packagePath = packageName.replace(".", "/");
        Optional<SymbolInformation> classSymbol = languageClient.findWorkspaceSymbol(className)
                .stream().filter(si -> si.getLocation().getUri().toString().endsWith(packagePath + "/" + className + ".java"))
                .findFirst();

        if (classSymbol.isPresent()) {
            URI fileUri = classSymbol.get().getLocation().getUri();
            int i = fileUri.toString().indexOf("src/main/java/");
            String fileName = fileUri.toString().substring(i);
            Optional<SymbolInformation> symbol = languageClient.listDocumentSymbols(fileUri)
                    .stream().filter(si -> si.getName().replaceFirst("\\(.*\\)", "").equals(methodName) && si.getKind() == SymbolKind.METHOD)
                    .findFirst();
            if (symbol.isPresent()) {
                int line = symbol.get().getLocation().getRange().getStart().getLine();
                Position start = new Position(line, 0);
                String oldLineContent = editor.getFileLine(fileName, line);
                Position end = new Position(line, oldLineContent.length());
                String modifierPrefix = modifier == null || modifier.isEmpty() ? "" : modifier + " ";
                String newLineContent = oldLineContent.replaceFirst("(\\w.* )?(\\w+) " + methodName + "\\(", modifierPrefix + "$2 " + methodName + "(");
                TextEdit textEdit = new TextEdit();
                textEdit.setNewText(newLineContent);
                textEdit.setRange(new Range(start, end));
                WorkspaceEdit workspaceEdit = new WorkspaceEdit();
                workspaceEdit.setChanges(Collections.singletonMap(fileUri, Collections.singletonList(textEdit)));
                Utils.applyWorkspaceEdit(workspaceEdit, editor, languageClient);
            }
        }
        return this;
    }

    public ClassCustomization changeMethodReturnType(String methodName, String newReturnType, String returnValueFormatter) {
        String packagePath = packageName.replace(".", "/");
        Optional<SymbolInformation> classSymbol = languageClient.findWorkspaceSymbol(className)
                .stream().filter(si -> si.getLocation().getUri().toString().endsWith(packagePath + "/" + className + ".java"))
                .findFirst();

        if (classSymbol.isPresent()) {
            URI fileUri = classSymbol.get().getLocation().getUri();
            int i = fileUri.toString().indexOf("src/main/java/");
            String fileName = fileUri.toString().substring(i);
            Optional<SymbolInformation> symbol = languageClient.listDocumentSymbols(fileUri)
                    .stream().filter(si -> si.getName().replaceFirst("\\(.*\\)", "").equals(methodName) && si.getKind() == SymbolKind.METHOD)
                    .findFirst();
            if (symbol.isPresent()) {
                List<TextEdit> edits = new ArrayList<>();

                int line = symbol.get().getLocation().getRange().getStart().getLine();
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
                    returnEdit.setNewText(methodContentIndent + "return " + String.format(returnValueFormatter, "returnValue") + ";");
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
            }
        }
        return this;
    }
}
