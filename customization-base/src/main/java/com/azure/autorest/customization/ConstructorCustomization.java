// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization;

import com.azure.autorest.customization.implementation.Utils;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ConstructorDeclaration;

import java.lang.reflect.Modifier;
import java.util.List;

/**
 * The constructor level customization for an AutoRest generated constructor.
 */
public final class ConstructorCustomization {
    private final CompilationUnit compilationUnit;
    final ConstructorDeclaration constructor;
    private final String packageName;
    private final String className;

    ConstructorCustomization(CompilationUnit compilationUnit, ConstructorDeclaration constructor, String packageName,
        String className) {
        this.compilationUnit = compilationUnit;
        this.constructor = constructor;
        this.packageName = packageName;
        this.className = className;
    }

    /**
     * Gets the name of the package containing the constructor.
     *
     * @return The name of the package containing the constructor.
     */
    public String getPackageName() {
        return packageName;
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
        return new JavadocCustomization(constructor);
    }

    /**
     * Add an annotation to the constructor.
     *
     * @param annotation The annotation to add to the constructor. The leading @ can be omitted.
     * @return A new ConstructorCustomization representing the updated constructor.
     */
    public ConstructorCustomization addAnnotation(String annotation) {
        Utils.addAnnotation(constructor, annotation);
        return this;
    }

    /**
     * Remove an annotation from the constructor.
     *
     * @param annotation The annotation to remove from the constructor. The leading @ can be omitted.
     * @return A new ConstructorCustomization representing the updated constructor.
     */
    public ConstructorCustomization removeAnnotation(String annotation) {
        Utils.removeAnnotation(constructor, annotation);
        return this;
    }

    /**
     * Replace the modifier for this constructor.
     * <p>
     * For compound modifiers such as {@code public abstract} use bitwise OR ({@code |}) of multiple Modifiers,
     * {@code Modifier.PUBLIC | Modifier.ABSTRACT}.
     * <p>
     * Pass {@code 0} for {@code modifiers} to indicate that the constructor has no modifiers.
     *
     * @param modifiers The {@link Modifier Modifiers} for the constructor.
     * @return A new ConstructorCustomization representing the updated constructor.
     * @throws IllegalArgumentException If the {@code modifier} is less than to {@code 0} or any {@link Modifier}
     * included in the bitwise OR isn't a valid constructor {@link Modifier}.
     */
    public ConstructorCustomization setModifier(int modifiers) {
        Utils.setModifiers(constructor, modifiers, Modifier.constructorModifiers());
        return this;
    }

    /**
     * Replace the parameters of the constructor.
     *
     * @param newParameters New constructor parameters.
     * @return A new ConstructorCustomization representing the updated constructor.
     */
    public ConstructorCustomization replaceParameters(String newParameters) {
        return replaceParameters(newParameters, null);
    }

    /**
     * Replaces the parameters of the constructor and adds any additional imports required by the new parameters.
     *
     * @param newParameters New constructor parameters.
     * @param importsToAdd Any additional imports required by the constructor. These will be custom types or types that
     * are ambiguous on which to use such as {@code List} or the utility class {@code Arrays}.
     * @return A new ConstructorCustomization representing the updated constructor.
     */
    public ConstructorCustomization replaceParameters(String newParameters, List<String> importsToAdd) {
        Utils.addImports(compilationUnit, importsToAdd);
        Utils.replaceParameters(constructor, newParameters);
        return this;
    }

    /**
     * Replace the body of the constructor.
     *
     * @param newBody New constructor body.
     * @return A new ConstructorCustomization representing the updated constructor.
     */
    public ConstructorCustomization replaceBody(String newBody) {
        return replaceBody(newBody, null);
    }

    /**
     * Replaces the body of the constructor and adds any additional imports required by the new body.
     *
     * @param newBody New constructor body.
     * @param importsToAdd Any additional imports required by the constructor. These will be custom types or types that
     * are ambiguous on which to use such as {@code List} or the utility class {@code Arrays}.
     * @return A new ConstructorCustomization representing the updated constructor.
     */
    public ConstructorCustomization replaceBody(String newBody, List<String> importsToAdd) {
        Utils.addImports(compilationUnit, importsToAdd);
        constructor.setBody(StaticJavaParser.parseBlock(newBody));
        return this;
    }
}
