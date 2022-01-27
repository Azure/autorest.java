// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.checker.JavaFormatter;
import com.azure.autorest.fluent.model.clientmodel.FluentExample;
import com.azure.autorest.model.javamodel.JavaFile;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class SampleTemplate {

    private final StringBuilder builder = new StringBuilder();

    private static final String NEW_LINE = System.lineSeparator();

    public String write(List<FluentExample> examples, List<JavaFile> sampleJavaFiles) {
        assert examples.size() == sampleJavaFiles.size();

        heading("Code snippets and samples", 1);

        List<String> sectionNames = new ArrayList<>();
        String groupName = null;
        for (FluentExample example : examples) {
            if (!Objects.equals(groupName, example.getGroupName())) {
                newLine();
                heading(example.getGroupName(), 2);
            }

            groupName = example.getGroupName();
            String sectionName = example.getGroupName() + "_" + example.getMethodName();
            sectionNames.add(sectionName);

            unorderedList(linkSection(example.getMethodName(), sectionName));
        }

        int index = 0;
        for (JavaFile sampleJavaFile : sampleJavaFiles) {
            String sectionName = sectionNames.get(index);
            heading(sectionName, 3);

            builder.append("```java");
            newLine();
            builder.append(formatJavaFile(sampleJavaFile));
            builder.append("```");
            newLine();
            newLine();

            ++index;
        }

        return builder.toString();
    }

    private static String formatJavaFile(JavaFile javaFile) {
        String content = javaFile.getContents().toString();
        String path = javaFile.getFilePath();

        // remove copyright and package statement
        List<String> formattedLines = new ArrayList<>();
        String[] lines = content.split("\r?\n", -1);
        boolean skipCopyright = true;
        for (String line : lines) {
            if (skipCopyright) {
                if (!line.trim().isEmpty() && !line.trim().startsWith("//") && !line.trim().startsWith("package")) {
                    skipCopyright = false;
                }
            }

            if (!skipCopyright) {
                formattedLines.add(line);
            }
        }
        content = String.join(System.lineSeparator(), formattedLines);

        if (!JavaSettings.getInstance().isSkipFormatting()) {
            content = new JavaFormatter(content, path).format(false);
        }
        return content;
    }

    private static String link(String text, URL url) {
        return '[' + text + ']' + '(' + url.toString() + ')';
    }

    private static String linkSection(String text, String section) {
        return '[' + text + ']' + "(#" + section.toLowerCase(Locale.ROOT) + ')';
    }

    private void heading(String text, int level) {
        for (int i = 0; i < level; i++) {
            builder.append('#');
        }
        builder.append(' ').append(text).append(NEW_LINE).append(NEW_LINE);
    }

    private void unorderedList(String text) {
        builder.append("- ").append(text).append(NEW_LINE);
    }

    private void newLine() {
        builder.append(NEW_LINE);
    }
}
