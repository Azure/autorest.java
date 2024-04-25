// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The package level customization for an AutoRest generated client library.
 */
public final class PackageCustomization {
    private final String packageName;

    private final Map<String, String> rawFiles;
    private final Map<String, CompilationUnit> parsedFiles;

    PackageCustomization(String packageName) {
        this.packageName = packageName;

        this.rawFiles = new HashMap<>();
        this.parsedFiles = new HashMap<>();
    }

    void addFile(String className, String content) {
        rawFiles.put(className, content);
    }

    Map<String, CompilationUnit> getParsedFiles() {
        return parsedFiles;
    }

    /**
     * Gets the name of the package.
     *
     * @return the name of the package
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Gets the class level customization for a Java class in the package.
     *
     * @param className the simple name of the class
     * @return the class level customization
     */
    public ClassCustomization getClass(String className) {
        CompilationUnit parsedClass = parsedFiles.get(className);

        if (parsedClass != null) {
            // Class has already been parsed.
            return new ClassCustomization(parsedClass, packageName, className);
        }

        String rawClass = rawFiles.get(className);
        if (rawClass == null) {
            throw new IllegalArgumentException(className + " does not exist in package " + packageName);
        }

        // TODO (alzimmer): If performance needs improving, CompilationUnit allows for an observer to be set. This could
        //  allow for determine whether or not a class has actually been customized.
        parsedClass = StaticJavaParser.parse(rawClass);
        parsedFiles.put(className, parsedClass);

        return new ClassCustomization(parsedClass, packageName, className);
    }

    /**
     * This method lists all the classes in this package.
     *
     * @return A list of classes that are in this package.
     */
    public List<ClassCustomization> listClasses() {
        // Calling this will force all raw files to be parsed.
        rawFiles.forEach((key, value) -> parsedFiles.computeIfAbsent(key, ignored -> StaticJavaParser.parse(value)));

        return parsedFiles.entrySet().stream()
            .map(entry -> new ClassCustomization(entry.getValue(), packageName, entry.getKey()))
            .collect(Collectors.toList());
    }
}
