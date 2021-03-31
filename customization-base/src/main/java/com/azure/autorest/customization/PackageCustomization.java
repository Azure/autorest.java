package com.azure.autorest.customization;

import com.azure.autorest.customization.implementation.Utils;
import com.azure.autorest.customization.implementation.ls.EclipseLanguageClient;
import com.azure.autorest.customization.implementation.ls.models.SymbolInformation;

import java.util.Optional;

/**
 * The package level customization for an AutoRest generated client library.
 */
public final class PackageCustomization {
    private final EclipseLanguageClient languageClient;
    private final Editor editor;
    private final String packageName;

    PackageCustomization(Editor editor, EclipseLanguageClient languageClient, String packageName) {
        this.editor = editor;
        this.languageClient = languageClient;
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
            .filter(si -> si.getLocation().getUri().toString().endsWith(packagePath + "/" + className + ".java"))
            .findFirst();

        return Utils.returnIfPresentOrThrow(classSymbol,
            symbol -> new ClassCustomization(editor, languageClient, packageName, className, symbol),
            () -> new IllegalArgumentException(className + " does not exist in package " + packageName));
    }
}
