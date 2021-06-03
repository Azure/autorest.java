// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization;

import com.azure.autorest.customization.implementation.CodeCustomization;
import com.azure.autorest.customization.implementation.Utils;
import com.azure.autorest.customization.implementation.ls.EclipseLanguageClient;
import com.azure.autorest.customization.implementation.ls.models.SymbolInformation;

import java.lang.reflect.Modifier;

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
        return Utils.addAnnotation(annotation, this, () -> refreshCustomization(constructorSignature));
    }

    /**
     * Remove an annotation from the constructor.
     *
     * @param annotation The annotation to remove from the constructor. The leading @ can be omitted.
     * @return A new ConstructorCustomization representing the updated constructor.
     */
    public ConstructorCustomization removeAnnotation(String annotation) {
        return Utils.removeAnnotation(annotation, this, () -> refreshCustomization(constructorSignature));
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

        return refreshCustomization(constructorSignature);
    }

    /**
     * Replace the parameters of the constructor.
     *
     * @param newParameters New constructor parameters.
     * @return A new ConstructorCustomization representing the updated constructor.
     */
    public ConstructorCustomization replaceParameters(String newParameters) {
        return Utils.replaceParameters(newParameters, this,
            () -> refreshCustomization(String.format("%s(%s)", className, newParameters)));
    }

    /**
     * Replace the body of the constructor.
     *
     * @param newBody New constructor body.
     * @return A new ConstructorCustomization representing the updated constructor.
     */
    public ConstructorCustomization replaceBody(String newBody) {
        return Utils.replaceBody(newBody, this, () -> refreshCustomization(constructorSignature));
    }

    private ConstructorCustomization refreshCustomization(String constructorSignature) {
        return new PackageCustomization(editor, languageClient, packageName)
            .getClass(className)
            .getConstructor(constructorSignature);
    }
}
