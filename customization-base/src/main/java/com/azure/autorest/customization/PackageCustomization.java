// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization;

import com.azure.autorest.customization.implementation.Utils;
import com.github.javaparser.utils.SourceRoot;
import org.eclipse.lsp4j.SymbolInformation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The package level customization for an AutoRest generated client library.
 */
public final class PackageCustomization {
    private final SourceRoot source;
    private final String packageName;

    PackageCustomization(SourceRoot source, String packageName) {
        this.source = source;
        this.packageName = packageName;
    }

    /**
     * Gets the class level customization for a Java class in the package.
     *
     * @param className the simple name of the class
     * @return the class level customization
     */
    public ClassCustomization getClass(String className) {
        String packagePath = packageName.replace(".", "/");
        Optional<SymbolInformation> classSymbol = languageClient.findWorkspaceSymbol(className).stream()
             // findWorkspace symbol finds all classes that contain the classname term
             // The filter that checks the filename only works if there are no nested classes
             // So, when customizing client classes that contain service interface, this can incorrectly return
             // the service interface instead of the client class. So, we should add another check for exact name match
            .filter(si -> si.getName().equals(className))
            .filter(si -> si.getLocation().getUri().endsWith(packagePath + "/" + className + ".java"))
            .findFirst();

        return Utils.returnIfPresentOrThrow(classSymbol,
            symbol -> new ClassCustomization(editor, languageClient, packageName, className, symbol),
            () -> new IllegalArgumentException(className + " does not exist in package " + packageName));
    }

    /**
     * This method lists all the classes in this package.
     * @return A list of classes that are in this package.
     */
    public List<ClassCustomization> listClasses() {
        source.getCompilationUnits()
        return languageClient.findWorkspaceSymbol("*")
                .stream()
                .filter(si -> si.getContainerName().equals(packageName))
                .map(classSymbol -> new ClassCustomization(editor, languageClient, packageName,
                        classSymbol.getName(), classSymbol))
                .collect(Collectors.toList());
    }
}
