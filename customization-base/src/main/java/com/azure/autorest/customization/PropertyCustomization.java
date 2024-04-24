// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization;

import com.azure.autorest.customization.implementation.Utils;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;

import java.lang.reflect.Modifier;


/**
 * Customization for an AutoRest generated instance property.
 * <p>
 * For constant property customizations use {@link ConstantCustomization}.
 */
public final class PropertyCustomization {
    private final FieldDeclaration property;
    private final String packageName;
    private final String className;
    private final String propertyName;

    PropertyCustomization(FieldDeclaration property, String packageName, String className, String propertyName) {
        this.property = property;
        this.packageName = packageName;
        this.className = className;
        this.propertyName = propertyName;
    }

    /**
     * Gets the name of the package that contains this property.
     *
     * @return The name of the package that contains this property.
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Gets the name of the class that contains this property.
     *
     * @return The name of the class that contains this property.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Gets the name of this property.
     *
     * @return The name of this property.
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * Rename a property in the class. This is a refactor operation. All references of the property will be renamed and
     * the getter and setter method(s) for this property will be renamed accordingly as well.
     *
     * @param newName the new name for the property
     * @return the current class customization for chaining
     */
    public PropertyCustomization rename(String newName) {
        property.getVariable(0).setName(newName);

        return this;
    }

    /**
     * Add an annotation to a property in the class.
     *
     * @param annotation the annotation to add. The leading @ can be omitted.
     * @return the current property customization for chaining
     */
    public PropertyCustomization addAnnotation(String annotation) {
        property.addAnnotation(annotation);
        return this;
    }

    /**
     * Remove an annotation from the property.
     *
     * @param annotation the annotation to remove from the property. The leading @ can be omitted.
     * @return the current property customization for chaining
     */
    public PropertyCustomization removeAnnotation(String annotation) {
        NodeList<AnnotationExpr> annotations = property.getAnnotations();
        annotations.removeIf(a -> a.getNameAsString().equals(annotation));
        property.setAnnotations(annotations);

        return this;
    }

    /**
     * Generates a getter and a setter method(s) for a property in the class. This is a refactor operation. If a getter
     * or a setter is already available on the class, the current getter or setter will be kept.
     *
     * @return the current class customization for chaining
     */
    public PropertyCustomization generateGetterAndSetter() {
        property.createGetter();
        property.createSetter();

        return this;
    }

    /**
     * Replace the modifier for this property.
     * <p>
     * For compound modifiers such as {@code public final} use bitwise OR ({@code |}) of multiple Modifiers, {@code
     * Modifier.PUBLIC | Modifier.FINAL}.
     * <p>
     * Pass {@code 0} for {@code modifiers} to indicate that the property has no modifiers.
     *
     * @param modifiers The {@link Modifier Modifiers} for the property.
     * @return The updated PropertyCustomization object.
     * @throws IllegalArgumentException If the {@code modifier} is less than {@code 0} or any {@link Modifier} included
     * in the bitwise OR isn't a valid property {@link Modifier}.
     */
    public PropertyCustomization setModifier(int modifiers) {
        property.setModifiers(Utils.toAstKeywords(modifiers, Modifier.fieldModifiers()));

        return this;
    }
}
