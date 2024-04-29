// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization;

import com.azure.autorest.customization.implementation.Utils;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;

import java.lang.reflect.Modifier;
import java.util.List;

/**
 * The method level customization for an AutoRest generated method.
 */
public final class MethodCustomization {
    private final CompilationUnit compilationUnit;
    final MethodDeclaration method;
    private final String packageName;
    private final String className;
    private final String methodName;

    MethodCustomization(CompilationUnit compilationUnit, MethodDeclaration method, String packageName,
        String className) {
        this.compilationUnit = compilationUnit;
        this.method = method;
        this.packageName = packageName;
        this.className = className;
        this.methodName = method.getNameAsString();
    }

    /**
     * Gets the name of the package containing the method.
     *
     * @return The name of the package containing the method.
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Gets the name of the class containing the method.
     *
     * @return The name of the class containing the method.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Gets the name of the method this customization is using.
     *
     * @return The name of the method.
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Gets the Javadoc customization for this method.
     *
     * @return the Javadoc customization
     */
    public JavadocCustomization getJavadoc() {
        return new JavadocCustomization(method);
    }

    /**
     * Rename a method in the class. This is a refactor operation. All references to this method across the client
     * library will be automatically modified.
     *
     * @param newName the new name for the method
     * @return the current method customization for chaining
     */
    public MethodCustomization rename(String newName) {
        method.setName(newName);
        return this;
    }

    /**
     * Add an annotation to a method in the class.
     *
     * @param annotation the annotation to add. The leading @ can be omitted.
     * @return the current method customization for chaining
     */
    public MethodCustomization addAnnotation(String annotation) {
        Utils.addAnnotation(method, annotation);
        return this;
    }

    /**
     * Remove an annotation from the method.
     *
     * @param annotation the annotation to remove from the method. The leading @ can be omitted.
     * @return the current method customization for chaining
     */
    public MethodCustomization removeAnnotation(String annotation) {
        Utils.removeAnnotation(method, annotation);
        return this;
    }

    /**
     * Replace the modifier for this method.
     * <p>
     * For compound modifiers such as {@code public abstract} use bitwise OR ({@code |}) of multiple Modifiers, {@code
     * Modifier.PUBLIC | Modifier.ABSTRACT}.
     * <p>
     * Pass {@code 0} for {@code modifiers} to indicate that the method has no modifiers.
     *
     * @param modifiers The {@link Modifier Modifiers} for the method.
     * @return The updated MethodCustomization object.
     * @throws IllegalArgumentException If the {@code modifier} is less than to {@code 0} or any {@link Modifier}
     * included in the bitwise OR isn't a valid method {@link Modifier}.
     */
    public MethodCustomization setModifier(int modifiers) {
        Utils.setModifiers(method, modifiers, Modifier.methodModifiers());
        return this;
    }

    /**
     * Replace the parameters of the method.
     *
     * @param newParameters New method parameters.
     * @return The updated MethodCustomization object.
     */
    public MethodCustomization replaceParameters(String newParameters) {
        return replaceParameters(newParameters, null);
    }

    /**
     * Replaces the parameters of the method and adds any additional imports required by the new parameters.
     *
     * @param newParameters New method parameters.
     * @param importsToAdd Any additional imports required by the method. These will be custom types or types that
     * are ambiguous on which to use such as {@code List} or the utility class {@code Arrays}.
     * @return A new MethodCustomization representing the updated method.
     */
    public MethodCustomization replaceParameters(String newParameters, List<String> importsToAdd) {
        Utils.addImports(compilationUnit, importsToAdd);
        Utils.replaceParameters(method, newParameters);
        return this;
    }

    /**
     * Replace the body of the method.
     *
     * @param newBody New method body.
     * @return The updated MethodCustomization object.
     */
    public MethodCustomization replaceBody(String newBody) {
        return replaceBody(newBody, null);
    }

    /**
     * Replaces the body of the method and adds any additional imports required by the new body.
     *
     * @param newBody New method body.
     * @param importsToAdd Any additional imports required by the method. These will be custom types or types that
     * are ambiguous on which to use such as {@code List} or the utility class {@code Arrays}.
     * @return A new MethodCustomization representing the updated method.
     */
    public MethodCustomization replaceBody(String newBody, List<String> importsToAdd) {
        Utils.addImports(compilationUnit, importsToAdd);
        method.setBody(new BlockStmt(Utils.parseCodeBlockOrStatement(newBody)));
        return this;
    }

    /**
     * Change the return type of the method. The new return type will be automatically imported.
     *
     * <p>
     * The {@code returnValueFormatter} can be used to transform the return value. If the original return type is {@code
     * void}, simply pass the new return expression to {@code returnValueFormatter}; if the new return type is {@code
     * void}, pass {@code null} to {@code returnValueFormatter}; if either the original return type nor the new return
     * type is {@code void}, the {@code returnValueFormatter} should be a String formatter that contains exactly 1
     * instance of {@code %s}.
     *
     * @param newReturnType the simple name of the new return type
     * @param returnValueFormatter the return value String formatter as described above
     * @return the current method customization for chaining
     */
    public MethodCustomization setReturnType(String newReturnType, String returnValueFormatter) {
        return setReturnType(newReturnType, returnValueFormatter, false);
    }

    /**
     * Change the return type of the method. The new return type will be automatically imported.
     * <p>
     * The {@code returnValueFormatter} can be used to transform the return value. If the original return type is {@code
     * void}, simply pass the new return expression to {@code returnValueFormatter}; if the new return type is {@code
     * void}, pass {@code null} to {@code returnValueFormatter}; if either the original return type nor the new return
     * type is {@code void}, the {@code returnValueFormatter} should be a String formatter that contains exactly 1
     * instance of {@code %s}.
     *
     * @param newReturnType the simple name of the new return type
     * @param returnValueFormatter the return value String formatter as described above
     * @param replaceReturnStatement if set to {@code true}, the return statement will be replaced by the provided
     * returnValueFormatter text with exactly one instance of {@code %s}. If set to true, appropriate semicolons,
     * parentheses, opening and closing of code blocks have to be taken care of in the {@code returnValueFormatter}.
     * @return the current method customization for chaining
     */
    public MethodCustomization setReturnType(String newReturnType, String returnValueFormatter,
        boolean replaceReturnStatement) {
        method.setType(newReturnType);

        // Get the existing return statement, if it exists.
        ReturnStmt returnStmt = method.getBody().flatMap(body -> body.getStatements().stream()
            .filter(Statement::isReturnStmt).map(Statement::asReturnStmt).findFirst())
            .orElse(null);

        if (newReturnType.equalsIgnoreCase("void") && returnStmt == null) {
            // New return type is void and the method already doesn't have a return statement, early out.
            return this;
        }

        if (returnStmt == null) {
            // No existing return statement, just add the new return statement.
            Statement newReturnStatement = new ReturnStmt(StaticJavaParser.parseExpression(returnValueFormatter));
            method.getBody().get().addStatement(newReturnStatement);
        } else if (newReturnType.equals("void")) {
            // New return type is void, remove the existing return statement.
            returnStmt.remove();
        } else {
            // Return statement exists and new return type is not void, update the return statement.
            if (replaceReturnStatement) {
                // Replace the return statement.
                returnStmt.setExpression(StaticJavaParser.parseExpression(returnValueFormatter));
            } else {
                // Update the return statement by formatting the existing return value.
                returnStmt.setExpression(StaticJavaParser.parseExpression(
                    String.format(returnValueFormatter, returnStmt.getExpression().get())));
            }
        }

        return this;
    }
}
