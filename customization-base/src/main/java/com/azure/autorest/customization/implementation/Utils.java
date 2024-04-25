// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization.implementation;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import com.github.javaparser.ast.nodeTypes.NodeWithModifiers;
import com.github.javaparser.ast.nodeTypes.NodeWithParameters;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Utils {
    /**
     * Converts the Java {@link Modifier modifiers} to JavaParser
     * {@link com.github.javaparser.ast.Modifier.Keyword keywords}.
     *
     * @param newModifiers The new modifiers to convert.
     * @param validModifiers The valid modifiers for the type.
     * @return The JavaParser {@link com.github.javaparser.ast.Modifier.Keyword keywords}.
     */
    static com.github.javaparser.ast.Modifier.Keyword[] toAstKeywords(int newModifiers, int validModifiers) {
        validateModifiers(newModifiers, validModifiers);

        List<com.github.javaparser.ast.Modifier.Keyword> keywords = new ArrayList<>();
        if (Modifier.isPublic(newModifiers)) {
            keywords.add(com.github.javaparser.ast.Modifier.Keyword.PUBLIC);
        }
        if (Modifier.isProtected(newModifiers)) {
            keywords.add(com.github.javaparser.ast.Modifier.Keyword.PROTECTED);
        }
        if (Modifier.isPrivate(newModifiers)) {
            keywords.add(com.github.javaparser.ast.Modifier.Keyword.PRIVATE);
        }
        if (Modifier.isAbstract(newModifiers)) {
            keywords.add(com.github.javaparser.ast.Modifier.Keyword.ABSTRACT);
        }
        if (Modifier.isStatic(newModifiers)) {
            keywords.add(com.github.javaparser.ast.Modifier.Keyword.STATIC);
        }
        if (Modifier.isFinal(newModifiers)) {
            keywords.add(com.github.javaparser.ast.Modifier.Keyword.FINAL);
        }
        if (Modifier.isTransient(newModifiers)) {
            keywords.add(com.github.javaparser.ast.Modifier.Keyword.TRANSIENT);
        }
        if (Modifier.isVolatile(newModifiers)) {
            keywords.add(com.github.javaparser.ast.Modifier.Keyword.VOLATILE);
        }
        if (Modifier.isSynchronized(newModifiers)) {
            keywords.add(com.github.javaparser.ast.Modifier.Keyword.SYNCHRONIZED);
        }
        if (Modifier.isNative(newModifiers)) {
            keywords.add(com.github.javaparser.ast.Modifier.Keyword.NATIVE);
        }
        if (Modifier.isStrict(newModifiers)) {
            keywords.add(com.github.javaparser.ast.Modifier.Keyword.STRICTFP);
        }
        return keywords.toArray(new com.github.javaparser.ast.Modifier.Keyword[0]);
    }

    /**
     * Sets the modifiers on the node.
     *
     * @param node The node to set the modifiers on.
     * @param newModifiers The new modifiers to set.
     * @param validModifiers The valid modifiers for the type.
     */
    public static void setModifiers(NodeWithModifiers<?> node, int newModifiers, int validModifiers) {
        node.setModifiers(toAstKeywords(newModifiers, validModifiers));
    }

    /**
     * Converts the comma separated string of parameters to a NodeList of JavaParser Parameters.
     * <p>
     * If the parameters string is null or empty, an empty NodeList will be returned.
     *
     * @param parameters The comma separated string of parameters.
     * @return The NodeList of JavaParser Parameters.
     */
    static NodeList<Parameter> toAstParameters(String parameters) {
        if (parameters == null || parameters.isEmpty()) {
            return new NodeList<>();
        }

        return Arrays.stream(parameters.split(","))
            .map(param -> StaticJavaParser.parseParameter(param.trim()))
            .collect(Collectors.toCollection(NodeList::new));
    }

    /**
     * Replaces the parameters on the node.
     * <p>
     * If parameters is null or empty, the parameters will be removed.
     *
     * @param node The node to replace the parameters on.
     * @param parameters The new parameters to set.
     */
    public static void replaceParameters(NodeWithParameters<?> node, String parameters) {
        node.setParameters(toAstParameters(parameters));
    }

    /**
     * Adds the imports to the compilation unit.
     * <p>
     * If imports is null, no imports will be added.
     *
     * @param compilationUnit The compilation unit to add the imports to.
     * @param imports The imports to add.
     */
    public static void addImports(CompilationUnit compilationUnit, List<String> imports) {
        if (imports != null) {
            imports.forEach(compilationUnit::addImport);
        }
    }

    /**
     * Adds an annotation to the node.
     *
     * @param node The node to add the annotation to.
     * @param annotation The annotation to add.
     */
    public static void addAnnotation(NodeWithAnnotations<?> node, String annotation) {
        node.addAnnotation(StaticJavaParser.parseAnnotation(annotation));
    }

    /**
     * Removes an annotation from the node.
     *
     * @param node The node to remove the annotation from.
     * @param annotation The annotation to remove.
     */
    public static void removeAnnotation(NodeWithAnnotations<?> node, String annotation) {
        AnnotationExpr annotationExpr = StaticJavaParser.parseAnnotation(annotation);
        node.getAnnotationByName(annotationExpr.getNameAsString()).ifPresent(AnnotationExpr::remove);
    }

    public static void deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        directoryToBeDeleted.delete();
    }

    static void validateModifiers(int validTypeModifiers, int newModifiers) {
        // 0 indicates no modifiers.
        if (newModifiers == 0) {
            return;
        }

        if (newModifiers < 0) {
            throw new IllegalArgumentException("Modifiers aren't allowed to be less than or equal to 0.");
        }

        if (validTypeModifiers != (validTypeModifiers | newModifiers)) {
            throw new IllegalArgumentException("Modifiers contain illegal modifiers for the type.");
        }
    }

    /**
     * Gets the index in the node list where the predicate first matches.
     *
     * @param bodyDeclarations The body declarations to search.
     * @param predicate The predicate to match.
     * @return The index where the predicate first matches, or -1 if no match is found.
     */
    public static int indexOfTypeDeclaration(NodeList<BodyDeclaration<?>> bodyDeclarations,
        Predicate<BodyDeclaration<?>> predicate) {
        for (int i = 0; i < bodyDeclarations.size(); i++) {
            if (predicate.test(bodyDeclarations.get(i))) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Determines if the OS is Windows.
     *
     * @return Whether the OS is Windows.
     */
    public static boolean isWindows() {
        String osName = System.getProperty("os.name");
        return osName != null && osName.startsWith("Windows");
    }

    /**
     * Determines if the OS is Mac.
     *
     * @return Whether the OS is Mac.
     */
    public static boolean isMac() {
        String osName = System.getProperty("os.name");
        return osName != null && (osName.startsWith("Mac") || osName.startsWith("Darwin"));
    }

    private Utils() {
    }
}
