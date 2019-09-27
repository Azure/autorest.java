package com.azure.autorest.template;


// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.PackageInfo;
import com.azure.autorest.model.javamodel.JavaFile;

/**
 Writes a PackageInfo to a JavaFile.
*/
public class PackageInfoTemplate implements IJavaTemplate<PackageInfo, JavaFile>
{
    private static PackageInfoTemplate _instance = new PackageInfoTemplate();
    public static PackageInfoTemplate getInstance()
    {
        return _instance;
    }

    private PackageInfoTemplate()
    {
    }

    public final void write(PackageInfo packageInfo, JavaFile javaFile)
    {
        JavaSettings settings = JavaSettings.getInstance();
        if (settings.getFileHeaderText() != null && !settings.getFileHeaderText().isEmpty())
        {
            javaFile.lineComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
            {
                    comment.line(settings.getFileHeaderText());
            });
            javaFile.line();
        }

        javaFile.javadocComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
        {
                for (String desc : packageInfo.getDescription().split(java.util.regex.Pattern.quote(String.valueOf(new char[] {'\r', '\n'})), -1))
                {
                    comment.description(desc);
                }
        });

        javaFile.delcarePackage(packageInfo.Package);
    }
}