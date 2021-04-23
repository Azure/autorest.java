// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization;

import com.azure.autorest.customization.implementation.CodeCustomization;
import com.azure.autorest.customization.implementation.Utils;
import com.azure.autorest.customization.implementation.ls.EclipseLanguageClient;
import com.azure.autorest.customization.implementation.ls.models.FileChangeType;
import com.azure.autorest.customization.implementation.ls.models.FileEvent;
import com.azure.autorest.customization.implementation.ls.models.SymbolInformation;
import com.azure.autorest.customization.implementation.ls.models.SymbolKind;
import com.azure.autorest.customization.models.Position;

import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.Optional;

/**
 * The constructor level customization for an AutoRest generated constructor.
 */
public final class ConstructorCustomization extends CodeCustomization {
    private final String packageName;
    private final String className;
    private final String constructorSignature;

    ConstructorCustomization(Editor editor, EclipseLanguageClient languageClient, String packageName, String className,
        String constructorSignature, SymbolInformation symbol) {
        super(editor, languageClient, symbol);
        this.packageName = packageName;
        this.className = className;
        this.constructorSignature = constructorSignature;
    }

    /**
     * Gets the name of the class containing the constructor.
     *
     * @return The name of the class containing the constructor.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Gets the Javadoc customization for this constructor.
     *
     * @return The Javadoc customization for this constructor.
     */
    public JavadocCustomization getJavadoc() {
        return new JavadocCustomization(editor, languageClient, fileUri, fileName,
            symbol.getLocation().getRange().getStart().getLine());
    }

    /**
     * Add an annotation to the constructor.
     *
     * @param annotation The annotation to add to the constructor. The leading @ can be omitted.
     * @return A new ConstructorCustomization representing the updated constructor.
     */
    public ConstructorCustomization addAnnotation(String annotation) {
        return Utils.addAnnotation(annotation, this, () -> new ConstructorCustomization(editor, languageClient,
            packageName, className, constructorSignature, refreshSymbol()));
    }

    /**
     * Remove an annotation from the constructor.
     *
     * @param annotation The annotation to remove from the constructor. The leading @ can be omitted.
     * @return A new ConstructorCustomization representing the updated constructor.
     */
    public ConstructorCustomization removeAnnotation(String annotation) {
        return Utils.removeAnnotation(annotation, this, () -> new ConstructorCustomization(editor, languageClient,
            packageName, className, constructorSignature, refreshSymbol()));
    }

    /**
     * Replace the modifier for this constructor.
     * <p>
     * For compound modifiers such as {@code public abstract} use bitwise OR ({@code |}) of multiple Modifiers, {@code
     * Modifier.PUBLIC | Modifier.ABSTRACT}.
     * <p>
     * Pass {@code 0} for {@code modifiers} to indicate that the constructor has no modifiers.
     *
     * @param modifiers The {@link Modifier Modifiers} for the constructor.
     * @return A new ConstructorCustomization representing the updated constructor.
     * @throws IllegalArgumentException If the {@code modifier} is less than to {@code 0} or any {@link Modifier}
     * included in the bitwise OR isn't a valid constructor {@link Modifier}.
     */
    public ConstructorCustomization setModifier(int modifiers) {
        Utils.replaceModifier(symbol, editor, languageClient, "(?:.+ )?" + className + "\\(", className + "(",
            Modifier.constructorModifiers(), modifiers);

        return new ConstructorCustomization(editor, languageClient, packageName, className, constructorSignature,
            refreshSymbol());
    }

    /**
     * Replace the parameters of the constructor.
     *
     * @param newParameters New constructor parameters.
     * @return A new ConstructorCustomization representing the updated constructor.
     */
    public ConstructorCustomization replaceParameters(String newParameters) {
        // Beginning line of the symbol.
        int line = symbol.getLocation().getRange().getStart().getLine();
        String parametersPositionFinder = editor.getFileLine(fileName, line);

        // First find the starting location of the parameters.
        // The beginning of the parameters may not be on the same line as the start of the signature.
        while (!parametersPositionFinder.contains("(")) {
            parametersPositionFinder = editor.getFileLine(fileName, ++line);
        }

        // Now that the line where the parameters begin is found create its position.
        int parametersStartCharacter = parametersPositionFinder.indexOf("(");

        // Starting character is inclusive of the character offset, so increment the index one.
        Position parametersStart = new Position(line, parametersStartCharacter + 1);

        // Then find where the parameters end.
        // The ending of the parameters may not be on the same line as the start of the parameters.
        while (!parametersPositionFinder.contains(")")) {
            parametersPositionFinder = editor.getFileLine(fileName, ++line);
        }

        // Now that the line where the parameters end is found gets create its position.
        int parametersEndCharacter = parametersPositionFinder.indexOf(")");

        // Ending character is exclusive of the character offset, so use the index as is.
        Position parametersEnd = new Position(line, parametersEndCharacter);

        editor.replace(fileName, parametersStart, parametersEnd, newParameters);
        FileEvent fileEvent = new FileEvent();
        fileEvent.setUri(fileUri);
        fileEvent.setType(FileChangeType.CHANGED);
        languageClient.notifyWatchedFilesChanged(Collections.singletonList(fileEvent));

        return new PackageCustomization(editor, languageClient, packageName)
            .getClass(className)
            .getConstructor(String.format("%s(%s)", className, newParameters));
    }

    /**
     * Replace the body of the constructor.
     *
     * @param newBody New constructor body.
     * @return A new ConstructorCustomization representing the updated constructor.
     */
    public ConstructorCustomization replaceBody(String newBody) {
        return Utils.replaceBody(newBody, this, () -> new ConstructorCustomization(editor, languageClient, packageName,
            className, constructorSignature, refreshSymbol()));
    }

    private SymbolInformation refreshSymbol() {
        Optional<SymbolInformation> methodSymbol = languageClient.listDocumentSymbols(fileUri)
            .stream().filter(si -> si.getName().replaceFirst("\\(.*\\)", "").equals(className)
                && si.getKind() == SymbolKind.CONSTRUCTOR)
            .filter(si -> editor.getFileLine(fileName, si.getLocation().getRange().getStart().getLine())
                .contains(constructorSignature))
            .findFirst();
        if (!methodSymbol.isPresent()) {
            throw new IllegalArgumentException("Constructor " + constructorSignature + " does not exist in class "
                + className);
        }
        return methodSymbol.get();
    }
}
