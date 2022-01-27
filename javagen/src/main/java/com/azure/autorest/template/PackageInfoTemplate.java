// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;


import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.PackageInfo;
import com.azure.autorest.model.javamodel.JavaFile;

import java.util.regex.Pattern;

/**
 * Writes a PackageInfo to a JavaFile.
 */
public class PackageInfoTemplate implements IJavaTemplate<PackageInfo, JavaFile> {
    private static final PackageInfoTemplate INSTANCE = new PackageInfoTemplate();

    private static final Pattern NEW_LINE = Pattern.compile(Pattern.quote("\r\n"));

    private PackageInfoTemplate() {
    }

    public static PackageInfoTemplate getInstance() {
        return INSTANCE;
    }

    public final void write(PackageInfo packageInfo, JavaFile javaFile) {
        JavaSettings settings = JavaSettings.getInstance();
        if (settings.getFileHeaderText() != null && !settings.getFileHeaderText().isEmpty()) {
            javaFile.lineComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
                comment.line(settings.getFileHeaderText()));
            javaFile.line();
        }

        javaFile.javadocComment(settings.getMaximumJavadocCommentWidth(), (comment) -> {
            for (String desc : NEW_LINE.split(packageInfo.getDescription(), -1)) {
                comment.description(desc);
            }
        });

        javaFile.declarePackage(packageInfo.getPackage());
    }
}
