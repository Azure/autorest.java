package com.azure.autorest.customization;

import com.azure.autorest.customization.implementation.ls.EclipseLanguageClient;

/**
 * The top level customization for an AutoRest generated client library.
 */
public final class LibraryCustomization {
    private EclipseLanguageClient languageClient;
    private Editor editor;

    LibraryCustomization(Editor editor, EclipseLanguageClient languageClient) {
        this.editor = editor;
        this.languageClient = languageClient;
    }

    /**
     * Gets the package level customization for a Java package in the client library.
     *
     * @param packageName the fully qualified name of the package
     * @return the package level customization.
     */
    public PackageCustomization getPackage(String packageName) {
        return new PackageCustomization(editor, languageClient, packageName);
    }

    /**
     * Gets the class level customization for a Java class in the client library.
     *
     * @param packageName the fully qualified name of the package
     * @param className the simple name of the class
     * @return the class level customization
     */
    public ClassCustomization getClass(String packageName, String className) {
        return new ClassCustomization(editor, languageClient, packageName, className);
    }

    /**
     * Gets the raw editor containing the current files being edited and eventually emitted to the disk.
     * @return the raw editor
     */
    public Editor getRawEditor() {
        return editor;
    }
}
