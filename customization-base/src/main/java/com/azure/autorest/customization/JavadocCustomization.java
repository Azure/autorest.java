package com.azure.autorest.customization;

import com.azure.autorest.customization.implementation.ls.EclipseLanguageClient;
import com.azure.autorest.customization.implementation.ls.models.FileChangeType;
import com.azure.autorest.customization.implementation.ls.models.FileEvent;
import com.azure.autorest.customization.implementation.ls.models.SymbolInformation;
import com.azure.autorest.customization.models.Position;
import com.azure.autorest.customization.models.Range;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * The Javadoc customization for an AutoRest generated classes and methods.
 */
public final class JavadocCustomization {
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

    /**
     * Sets the description in the Javadoc.
     * @param description the description for the current class/method.
     * @return the Javadoc customization object for chaining
     */
    public JavadocCustomization setDescription(String description) {
        descriptionDoc = description;
        commit();
        return this;
    }

    /**
     * Sets the param Javadoc for a parameter on the method.
     *
     * @param parameterName the parameter name on the method
     * @param description the description for this parameter
     * @return the Javadoc customization object for chaining
     */
    public JavadocCustomization setParam(String parameterName, String description) {
        paramDocs.put(parameterName, description);
        commit();
        return this;
    }

    /**
     * Removes a parameter Javadoc on the method.
     *
     * @param parameterName the name of the parameter on the method
     * @return the Javadoc customization object for chaining
     */
    public JavadocCustomization removeParam(String parameterName) {
        paramDocs.remove(parameterName);
        commit();
        return this;
    }

    /**
     * Sets the return Javadoc on the method.
     *
     * @param description the description for the return value
     * @return the Javadoc customization object for chaining
     */
    public JavadocCustomization setReturn(String description) {
        returnDoc = description;
        commit();
        return this;
    }

    /**
     * Removes the return Javadoc for a method.
     *
     * @return the Javadoc customization object for chaining
     */
    public JavadocCustomization removeReturn() {
        returnDoc = null;
        commit();
        return this;
    }

    /**
     * Adds a throws Javadoc for a method.
     * @param exceptionType the type of the exception the method will throw
     * @param description the description for the exception
     * @return the Javadoc customization object for chaining
     */
    public JavadocCustomization addThrows(String exceptionType, String description) {
        throwsDocs.put(exceptionType, description);
        commit();
        return this;
    }

    /**
     * Removes a throw Javadoc for a method.
     * @param exceptionType the type of the exception the method will throw
     * @return the Javadoc customization object for chaining
     */
    public JavadocCustomization removeThrows(String exceptionType) {
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
