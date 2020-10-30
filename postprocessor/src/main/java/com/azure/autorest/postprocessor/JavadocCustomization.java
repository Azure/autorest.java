package com.azure.autorest.postprocessor;

import com.azure.autorest.postprocessor.ls.EclipseLanguageClient;
import com.azure.autorest.postprocessor.ls.models.Position;
import com.azure.autorest.postprocessor.ls.models.Range;
import com.azure.autorest.postprocessor.ls.models.SymbolInformation;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class JavadocCustomization {
    final EclipseLanguageClient languageClient;
    final Editor editor;
    final SymbolInformation classSymbol;
    final int symbolLine;
    Range description;
    final Map<String, Range> params;
    Range returnDoc;
    final Map<String, Range> throwsDocs;

    JavadocCustomization(Editor editor, EclipseLanguageClient languageClient, String packageName, SymbolInformation classSymbol, int symbolLine) {
        this.editor = editor;
        this.languageClient = languageClient;
        this.classSymbol = classSymbol;
        this.symbolLine = symbolLine;
        this.description = null;
        this.returnDoc = null;
        this.params = new HashMap<>();
        this.throwsDocs = new HashMap<>();

        URI fileUri = classSymbol.getLocation().getUri();
        int i = fileUri.toString().indexOf("src/main/java/");
        String fileName = fileUri.toString().substring(i);
        String lineContent = editor.getFileLine(fileName, --symbolLine);
        if (lineContent.endsWith("*/")) {
            int currentDocEndLine = symbolLine;
            while (!lineContent.contains("/*")) {
                if (lineContent.contains("@throws")) {
                    String type = lineContent.replaceFirst(".*@throws ", "".replaceFirst(" .*", ""));
                    Position docStart = new Position(symbolLine, lineContent.length() - lineContent.replaceFirst(".* @throws " + type, "").length() + 1);
                    Position docEnd = new Position(currentDocEndLine, editor.getFileLine(fileName, currentDocEndLine).length());
                    throwsDocs.put(type, new Range(docStart, docEnd));
                    currentDocEndLine = symbolLine - 1;
                } else if (lineContent.contains("@returns")) {
                    Position docStart = new Position(symbolLine, lineContent.length() - lineContent.replaceFirst(".* @throws " + name, "").length() + 1);
                    Position docEnd = new Position(currentDocEndLine, editor.getFileLine(fileName, currentDocEndLine).length());
                    returnDoc = new Range(docStart, docEnd);
                    currentDocEndLine = symbolLine - 1;
                } else if (lineContent.contains("@param")) {
                    String name = lineContent.replaceFirst(".*@param ", "".replaceFirst(" .*", ""));
                    Position docStart = new Position(symbolLine, lineContent.length() - lineContent.replaceFirst(".* @throws " + name, "").length() + 1);
                    Position docEnd = new Position(currentDocEndLine, editor.getFileLine(fileName, currentDocEndLine).length());
                    params.put(name, new Range(docStart, docEnd));
                    currentDocEndLine = symbolLine - 1;
                }
                lineContent = editor.getFileLine(fileName, --symbolLine);
            }
            Position descriptionStart = new Position(symbolLine + 1, editor.getFileLine(fileName, symbolLine + 1).indexOf("*") + 2);
            String descriptionEndLineContent = editor.getFileLine(fileName, currentDocEndLine);
            while (descriptionEndLineContent.trim().endsWith("*")) {
                descriptionEndLineContent = editor.getFileLine(fileName, --currentDocEndLine);
            }
            Position descriptionEnd = new Position(currentDocEndLine, descriptionEndLineContent.length());
            this.description = new Range(descriptionStart, descriptionEnd);
        } else {
            throw new IllegalStateException("No JavaDocs generated for this symbol at line " + this.symbolLine + " in class " + classSymbol.getName());
        }
    }

    public JavadocCustomization setDescription(String description) {

    }

    public JavadocCustomization setParam(String parameterName, String description) {

    }

    public JavadocCustomization removeParam(String parameterName) {

    }

    public JavadocCustomization setReturn(String description) {

    }

    public JavadocCustomization removeReturn() {

    }

    public JavadocCustomization addThrows(String exceptionType) {

    }
}
