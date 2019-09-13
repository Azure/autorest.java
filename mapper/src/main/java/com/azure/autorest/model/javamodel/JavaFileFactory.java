package com.azure.autorest.model.javamodel;

import AutoRest.Core.*;
import AutoRest.Core.Utilities.*;
import AutoRest.Extensions.*;
import AutoRest.Core.Model.*;
import Newtonsoft.Json.*;
import AutoRest.Core.Utilities.Collections.*;
import Newtonsoft.Json.Linq.*;
import AutoRest.Java.Model.*;
import java.io.*;
import java.nio.file.*;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


public class JavaFileFactory
{
    private JavaSettings settings;

    public JavaFileFactory(JavaSettings settings)
    {
        this.settings = settings;
    }

    public final JavaFile CreateEmptySourceFile(String package_Keyword, String fileNameWithoutExtension)
    {
        String folderPath = Path.Combine("src", "main", "java", package_Keyword.replace('.', File.separatorChar));
        String filePath = Paths.get(folderPath).resolve(String.format("%1$s.java", fileNameWithoutExtension)).toString().Replace('\\', '/').replace("//", "/");
        return new JavaFile(filePath);
    }

    public final JavaFile CreateSourceFile(String package_Keyword, String fileNameWithoutExtension)
    {
        JavaFile javaFile = CreateEmptySourceFile(package_Keyword, fileNameWithoutExtension);

        String headerComment = settings.getFileHeaderText();
        if (headerComment != null && !headerComment.isEmpty())
        {
            javaFile.LineComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
            {
                    comment.Line(headerComment);
            });
            javaFile.Line();
        }

        javaFile.Package(package_Keyword);
        javaFile.Line();

        return javaFile;
    }

    public final JavaFile CreateTestFile(String package_Keyword, String fileNameWithoutExtension)
    {
        String folderPath = Path.Combine("src", "test", "java", package_Keyword.replace('.', File.separatorChar));
        String filePath = Paths.get(folderPath).resolve(String.format("%1$s.java", fileNameWithoutExtension)).toString().Replace('\\', '/').replace("//", "/");
        JavaFile javaFile = new JavaFile(filePath);

        String headerComment = settings.getFileHeaderText();
        if (headerComment != null && !headerComment.isEmpty())
        {
            javaFile.JavadocComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
            {
                    comment.Description(headerComment);
            });
            javaFile.Line();
        }

        javaFile.Package(package_Keyword);
        javaFile.Line();

        return javaFile;
    }
}