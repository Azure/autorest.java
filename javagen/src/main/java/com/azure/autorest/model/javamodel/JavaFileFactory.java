package com.azure.autorest.model.javamodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;

import java.io.File;
import java.nio.file.Paths;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


public class JavaFileFactory {
    private JavaSettings settings;

    public JavaFileFactory(JavaSettings settings) {
        this.settings = settings;
    }

    public final JavaFile createEmptySourceFile(String package_Keyword, String fileNameWithoutExtension) {
        String folderPath = Paths.get("src", "main", "java", package_Keyword.replace('.', File.separatorChar)).toString();
        String filePath = Paths.get(folderPath).resolve(String.format("%1$s.java", fileNameWithoutExtension)).toString().replace('\\', '/').replace("//", "/");
        return new JavaFile(filePath);
    }

    public final JavaFile createSourceFile(String package_Keyword, String fileNameWithoutExtension) {
        JavaFile javaFile = createEmptySourceFile(package_Keyword, fileNameWithoutExtension);

        String headerComment = settings.getFileHeaderText();
        if (headerComment != null && !headerComment.isEmpty()) {
            javaFile.lineComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
            {
                comment.line(headerComment);
            });
            javaFile.line();
        }

        javaFile.delcarePackage(package_Keyword);
        javaFile.line();

        return javaFile;
    }

    public final JavaFile createTestFile(String package_Keyword, String fileNameWithoutExtension) {
        String folderPath = Paths.get("src", "test", "java", package_Keyword.replace('.', File.separatorChar)).toString();
        String filePath = Paths.get(folderPath).resolve(String.format("%1$s.java", fileNameWithoutExtension)).toString().replace('\\', '/').replace("//", "/");
        JavaFile javaFile = new JavaFile(filePath);

        String headerComment = settings.getFileHeaderText();
        if (headerComment != null && !headerComment.isEmpty()) {
            javaFile.javadocComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
            {
                comment.description(headerComment);
            });
            javaFile.line();
        }

        javaFile.delcarePackage(package_Keyword);
        javaFile.line();

        return javaFile;
    }
}