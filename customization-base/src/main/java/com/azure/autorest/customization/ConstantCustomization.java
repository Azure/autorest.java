// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization;

import com.azure.autorest.customization.implementation.Utils;
import com.github.javaparser.ast.body.FieldDeclaration;

import java.lang.reflect.Modifier;
import java.util.Objects;

/**
 * Customization for an AutoRest generated constant property.
 * <p>
 * For instance property customizations use {@link PropertyCustomization}.
 */
public final class ConstantCustomization {
    private final FieldDeclaration constant;
    private final String packageName;
    private final String className;
    private final String constantName;

    ConstantCustomization(FieldDeclaration constant, String packageName, String className, String constantName) {
        this.constant = constant;
        this.packageName = packageName;
        this.className = className;
        this.constantName = constantName;
    }

    /**
     * Gets the name of the package that contains this constant.
     *
     * @return The name of the package that contains this constant.
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Gets the name of the class that contains this constant.
     *
     * @return The name of the class that contains this constant.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Gets the name of this constant.
     *
     * @return The name of this constant.
     */
    public String getConstantName() {
        return constantName;
    }

    /**
     * Gets the Javadoc customization for this constant.
     *
     * @return The Javadoc customization.
     */
    public JavadocCustomization getJavadoc() {
        return new JavadocCustomization(constant);
    }

    /**
     * Replace the modifier for this constant.
     * <p>
     * For compound modifiers such as {@code public abstract} use bitwise OR ({@code |}) of multiple Modifiers, {@code
     * Modifier.PUBLIC | Modifier.ABSTRACT}.
     * <p>
     * This operation doesn't allow for the constant to lose constant status, so
     * {@code Modifier.STATIC | Modifier.FINAL} will be added to the passed {@code modifiers}.
     * <p>
     * Pass {@code 0} for {@code modifiers} to indicate that the constant has no modifiers.
     *
     * @param modifiers The {@link Modifier Modifiers} for the constant.
     * @return The updated ConstantCustomization object.
     * @throws IllegalArgumentException If the {@code modifier} is less than to {@code 0} or any {@link Modifier}
     * included in the bitwise OR isn't a valid constant {@link Modifier}.
     */
    public ConstantCustomization setModifier(int modifiers) {
        Utils.setModifiers(constant, modifiers | Modifier.STATIC | Modifier.FINAL, Modifier.fieldModifiers());
        return this;
    }

    /**
     * Renames the constant.
     * <p>
     * This operation doesn't allow for the constant to lose naming conventions of capitalized and underscore delimited
     * words, so the {@code newName} will be capitalized.
     * <p>
     * This is a refactor operation, all references of the constant will be renamed and the getter method(s) for this
     * property will be renamed accordingly as well.
     *
     * @param newName The new name for the constant.
     * @return A new instance of {@link ConstantCustomization} for chaining.
     * @throws NullPointerException If {@code newName} is null.
     */
    public ConstantCustomization rename(String newName) {
        Objects.requireNonNull(newName, "'newName' cannot be null.");

        constant.getVariable(0).setName(newName);

        return this;
    }

    /**
     * Add an annotation to a property in the class.
     *
     * @param annotation the annotation to add. The leading @ can be omitted.
     * @return A new instance of {@link ConstantCustomization} for chaining.
     */
    public ConstantCustomization addAnnotation(String annotation) {
        Utils.addAnnotation(constant, annotation);
        return this;
    }

    /**
     * Remove an annotation from the constant.
     *
     * @param annotation the annotation to remove from the constant. The leading @ can be omitted.
     * @return A new instance of {@link ConstantCustomization} for chaining.
     */
    public ConstantCustomization removeAnnotation(String annotation) {
        Utils.removeAnnotation(constant, annotation);
        return this;
    }
}
