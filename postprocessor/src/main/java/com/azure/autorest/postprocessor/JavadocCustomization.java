package com.azure.autorest.postprocessor;

import com.azure.autorest.postprocessor.ls.EclipseLanguageClient;
import com.azure.autorest.postprocessor.ls.models.FileChangeType;
import com.azure.autorest.postprocessor.ls.models.FileEvent;
import com.azure.autorest.postprocessor.ls.models.Position;
import com.azure.autorest.postprocessor.ls.models.Range;
import com.azure.autorest.postprocessor.ls.models.SymbolInformation;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JavadocCustomization {
    private final EclipseLanguageClient languageClient;
    private final Editor editor;
    private final URI fileUri;
    private final String fileName;
    private final String indent;

    private String descriptionDoc;
    private final Map<String, String> paramDocs;
    private String returnDoc;
    private final Map<String, String> throwsDocs;
    private Range javadocRange;

    JavadocCustomization(Editor editor, EclipseLanguageClient languageClient, String packagePath, String className, int symbolLine) {
        this.editor = editor;
        this.languageClient = languageClient;

        this.paramDocs = new HashMap<>();
        this.throwsDocs = new HashMap<>();

        Optional<SymbolInformation> classSymbol = languageClient.findWorkspaceSymbol(className)
                .stream().filter(si -> si.getLocation().getUri().toString().endsWith(packagePath + "/" + className + ".java"))
                .findFirst();

        fileUri = classSymbol.get().getLocation().getUri();
        int i = fileUri.toString().indexOf("src/main/java/");
        fileName = fileUri.toString().substring(i);
        this.indent = editor.getFileLine(fileName, symbolLine).replaceAll("[^ ].*$", "");
        parseJavadoc(symbolLine);
    }

    public JavadocCustomization setDescription(String description) {
//        if (descriptionRange == null) {
//            int descriptionLine = symbolLine;
//            String lineContent = editor.getFileLine(fileName, descriptionLine);
//            while (!lineContent.startsWith(indent + "/*")) {
//                lineContent = editor.getFileLine(fileName, --descriptionLine);
//            }
//            descriptionLine ++;
//            editor.insertBlankLine(fileName, descriptionLine, false);
//            FileEvent blankLineEvent = new FileEvent();
//            blankLineEvent.setUri(fileUri);
//            blankLineEvent.setType(FileChangeType.CHANGED);
//            languageClient.notifyWatchedFilesChanged(Collections.singletonList(blankLineEvent));
//
//            Position start = new Position(descriptionLine, 0);
//            TextEdit textEdit = new TextEdit();
//            textEdit.setNewText(indent + " * " + description);
//            textEdit.setRange(new Range(start, start));
//            WorkspaceEdit workspaceEdit = new WorkspaceEdit();
//            workspaceEdit.setChanges(Collections.singletonMap(fileUri, Collections.singletonList(textEdit)));
//            Utils.applyWorkspaceEdit(workspaceEdit, editor, languageClient);
//            descriptionRange = new Range(start, new Position(start.getLine(), editor.getFileLine(fileName, descriptionLine).length()));
//        } else {
//            TextEdit textEdit = new TextEdit();
//            textEdit.setNewText(description);
//            textEdit.setRange(descriptionRange);
//            WorkspaceEdit workspaceEdit = new WorkspaceEdit();
//            workspaceEdit.setChanges(Collections.singletonMap(fileUri, Collections.singletonList(textEdit)));
//            Utils.applyWorkspaceEdit(workspaceEdit, editor, languageClient);
//            descriptionRange = new Range(descriptionRange.getStart(),
//                    new Position(descriptionRange.getStart().getLine(), descriptionRange.getStart().getCharacter() + description.length()));
//        }
        descriptionDoc = description;
        commit();
        return this;
    }

    public JavadocCustomization setParam(String parameterName, String description) {
//        TextEdit textEdit = new TextEdit();
//        if (paramRanges.containsKey(parameterName)) {
//            textEdit.setNewText(description);
//            textEdit.setRange(paramRanges.get(parameterName));
//        } else {
//            int newParamLine = -1;
//            if (!paramRanges.isEmpty()) {
//                for (Range paramRange : paramRanges.values()) {
//                    if (paramRange.getEnd().getLine() + 1 > newParamLine) {
//                        newParamLine = paramRange.getEnd().getLine() + 1;
//                    }
//                }
//            } else if (returnRange != null) {
//                newParamLine = returnRange.getStart().getLine();
//            } else if (!throwsRanges.isEmpty()) {
//                newParamLine = symbolLine;
//                for (Range throwsRange : throwsRanges.values()) {
//                    if (throwsRange.getEnd().getLine() < newParamLine) {
//                        newParamLine = throwsRange.getStart().getLine();
//                    }
//                }
//            } else if (descriptionRange != null) {
//                newParamLine = descriptionRange.getEnd().getLine() + 1;
//                if (!editor.getFileLine(fileName, newParamLine).endsWith("*/")) {
//                    editor.insertBlankLine(fileName, newParamLine, false);
//                    String indent = editor.getFileLine(fileName, newParamLine);
//                    Position insert = new Position(newParamLine, indent.length());
//                    editor.replace(fileName, insert, insert, indent + " * ");
//                    newParamLine++;
//                }
//            }
//            editor.insertBlankLine(fileName, newParamLine, false);
//            FileEvent blankLineEvent = new FileEvent();
//            blankLineEvent.setUri(fileUri);
//            blankLineEvent.setType(FileChangeType.CHANGED);
//            languageClient.notifyWatchedFilesChanged(Collections.singletonList(blankLineEvent));
//
//            textEdit.setNewText(indent + " * @param " + parameterName + " " + description);
//            Position newParamStart = new Position(newParamLine, editor.getFileLine(fileName, newParamLine).length());
//            textEdit.setRange(new Range(newParamStart, newParamStart));
//            Position newParamEnd = new Position(newParamLine, newParamStart.getCharacter() + textEdit.getNewText().length());
//            paramRanges.put(parameterName, new Range(newParamStart, newParamEnd));
//        }
//        WorkspaceEdit workspaceEdit = new WorkspaceEdit();
//        workspaceEdit.setChanges(Collections.singletonMap(fileUri, Collections.singletonList(textEdit)));
//        Utils.applyWorkspaceEdit(workspaceEdit, editor, languageClient);
        paramDocs.put(parameterName, description);
        commit();
        return this;
    }

    public JavadocCustomization removeParam(String parameterName) {
        paramDocs.remove(parameterName);
        commit();
        return this;
    }

    public JavadocCustomization setReturn(String description) {
//        TextEdit textEdit = new TextEdit();
//        if (returnRange != null) {
//            textEdit.setNewText(description);
//            textEdit.setRange(returnRange);
//        } else {
//            int newReturnLine = -1;
//            if (!paramRanges.isEmpty()) {
//                for (Range paramRange : paramRanges.values()) {
//                    if (paramRange.getEnd().getLine() > newReturnLine) {
//                        newReturnLine = paramRange.getEnd().getLine() + 1;
//                    }
//                }
//            } else if (!throwsRanges.isEmpty()) {
//                newReturnLine = symbolLine;
//                for (Range throwsRange : throwsRanges.values()) {
//                    if (throwsRange.getEnd().getLine() < newReturnLine) {
//                        newReturnLine = throwsRange.getStart().getLine();
//                    }
//                }
//            } else if (descriptionRange != null) {
//                newReturnLine = descriptionRange.getEnd().getLine() + 1;
//                if (!editor.getFileLine(fileName, newReturnLine).endsWith("*/")) {
//                    editor.insertBlankLine(fileName, newReturnLine, false);
//                    String indent = editor.getFileLine(fileName, newReturnLine);
//                    Position insert = new Position(newReturnLine, indent.length());
//                    editor.replace(fileName, insert, insert, indent + " * ");
//                    newReturnLine++;
//                }
//            }
//            editor.insertBlankLine(fileName, newReturnLine, false);
//            FileEvent blankLineEvent = new FileEvent();
//            blankLineEvent.setUri(fileUri);
//            blankLineEvent.setType(FileChangeType.CHANGED);
//            languageClient.notifyWatchedFilesChanged(Collections.singletonList(blankLineEvent));
//
//            textEdit.setNewText(indent + " * @return " + description);
//            Position newReturnStart = new Position(newReturnLine, editor.getFileLine(fileName, newReturnLine).length());
//            textEdit.setRange(new Range(newReturnStart, newReturnStart));
//            Position newReturnEnd = new Position(newReturnLine, newReturnStart.getCharacter() + textEdit.getNewText().length());
//            returnRange = new Range(newReturnStart, newReturnEnd);
//        }
//        WorkspaceEdit workspaceEdit = new WorkspaceEdit();
//        workspaceEdit.setChanges(Collections.singletonMap(fileUri, Collections.singletonList(textEdit)));
//        Utils.applyWorkspaceEdit(workspaceEdit, editor, languageClient);
        returnDoc = description;
        commit();
        return this;
    }

    public JavadocCustomization removeReturn() {
        returnDoc = null;
        commit();
        return this;
    }

    public JavadocCustomization addThrows(String exceptionType, String description) {
        throwsDocs.put(exceptionType, description);
        commit();
        return this;
    }

    public JavadocCustomization removeThrows(String exceptionType, String description) {
        throwsDocs.remove(exceptionType);
        commit();
        return this;
    }

    private void initialize(int symbolLine) {
        editor.insertBlankLine(fileName, symbolLine, false);
        editor.replace(fileName, new Position(symbolLine, 0), new Position(symbolLine, 0), indent);
        Position javadocCursor = new Position(symbolLine, indent.length());
        javadocRange = new Range(javadocCursor, javadocCursor);
        ++symbolLine;
        FileEvent blankLineEvent = new FileEvent();
        blankLineEvent.setUri(fileUri);
        blankLineEvent.setType(FileChangeType.CHANGED);
        languageClient.notifyWatchedFilesChanged(Collections.singletonList(blankLineEvent));
    }

    private void parseJavadoc(int symbolLine) {
        String lineContent = editor.getFileLine(fileName, --symbolLine);
        while (lineContent.startsWith(indent + "@")) {
            lineContent = editor.getFileLine(fileName, --symbolLine);
        }
        if (lineContent.endsWith("*/")) {
            Position javadocEnd = new Position(symbolLine, lineContent.length());
            int currentDocEndLine = symbolLine;
            while (!lineContent.contains("/*")) {
                if (lineContent.contains("@throws")) {
                    String type = lineContent.replaceFirst(".*@throws ", "").replaceFirst(" .*", "");
                    Position docStart = new Position(symbolLine, lineContent.indexOf("@throws") + 8);
                    Position docEnd = new Position(currentDocEndLine, editor.getFileLine(fileName, currentDocEndLine).length());
                    throwsDocs.put(type, editor.getTextInRange(fileName, new Range(docStart, docEnd), " ").replaceAll(" +\\* ", " ").trim());
                    currentDocEndLine = symbolLine - 1;
                } else if (lineContent.contains("@return")) {
                    Position docStart = new Position(symbolLine, lineContent.indexOf("@return") + 8);
                    Position docEnd = new Position(currentDocEndLine, editor.getFileLine(fileName, currentDocEndLine).length());
                    returnDoc = editor.getTextInRange(fileName, new Range(docStart, docEnd), " ").replaceAll(" +\\* ", " ").trim();
                    currentDocEndLine = symbolLine - 1;
                } else if (lineContent.contains("@param")) {
                    String name = lineContent.replaceFirst(".*@param ", "").replaceFirst(" .*", "");
                    Position docStart = new Position(symbolLine, lineContent.indexOf("@param") + 8 + name.length());
                    Position docEnd = new Position(currentDocEndLine, editor.getFileLine(fileName, currentDocEndLine).length());
                    paramDocs.put(name, editor.getTextInRange(fileName, new Range(docStart, docEnd), " ").replaceAll(" +\\* ", " ").trim());
                    currentDocEndLine = symbolLine - 1;
                } else if (lineContent.startsWith(indent + " */") || lineContent.endsWith(" *")) {
                    // empty line
                    currentDocEndLine--;
                }
                lineContent = editor.getFileLine(fileName, --symbolLine);
            }
            Position javadocStart = new Position(symbolLine, indent.length());
            javadocRange = new Range(javadocStart, javadocEnd);
            if (lineContent.endsWith("/*") || lineContent.endsWith("/**")) {
                symbolLine++;
            }
            Position descriptionStart = new Position(symbolLine, editor.getFileLine(fileName, symbolLine).replaceFirst("\\* .*$", "").length() + 2);
            String descriptionEndLineContent = editor.getFileLine(fileName, currentDocEndLine);
            while (descriptionEndLineContent.trim().endsWith("*")) {
                descriptionEndLineContent = editor.getFileLine(fileName, --currentDocEndLine);
            }
            Position descriptionEnd = new Position(currentDocEndLine, descriptionEndLineContent.replaceFirst(" \\*/$", "").length());
            this.descriptionDoc = editor.getTextInRange(fileName, new Range(descriptionStart, descriptionEnd), " ").replaceAll(" +\\* ", " ").trim();
        } else {
            initialize(symbolLine);
        }
    }

    private void commit() {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        printWriter.println("/**");
        if (descriptionDoc != null) {
            printWriter.println(indent + " * " + descriptionDoc);
        }
        if (!paramDocs.isEmpty() || !throwsDocs.isEmpty() || returnDoc != null) {
            printWriter.println(indent + " * ");

            for (Map.Entry<String, String> paramDoc : paramDocs.entrySet()) {
                printWriter.println(indent + " * @param " + paramDoc.getKey() + " " + paramDoc.getValue());
            }

            if (returnDoc != null) {
                printWriter.println(indent + " * @return " + returnDoc);
            }

            for (Map.Entry<String, String> throwsDoc : throwsDocs.entrySet()) {
                printWriter.println(indent + " * @throws " + throwsDoc.getKey() + " " + throwsDoc.getValue());
            }
        }
        printWriter.print(indent + " */");

        editor.replace(fileName, javadocRange.getStart(), javadocRange.getEnd(), stringWriter.toString());
        FileEvent blankLineEvent = new FileEvent();
        blankLineEvent.setUri(fileUri);
        blankLineEvent.setType(FileChangeType.CHANGED);
        languageClient.notifyWatchedFilesChanged(Collections.singletonList(blankLineEvent));

        int javadocStartLine = javadocRange.getStart().getLine();
        String lineContent = editor.getFileLine(fileName, javadocStartLine);
        while (!lineContent.endsWith("*/")) {
            lineContent = editor.getFileLine(fileName, ++javadocStartLine);
        }
        parseJavadoc(javadocStartLine + 1);
    }
}
