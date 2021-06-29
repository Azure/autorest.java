/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.clientmodel.FluentExample;
import com.azure.autorest.fluent.model.projectmodel.Project;
import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class SampleTemplate {

    private final StringBuilder builder = new StringBuilder();

    private static final String NEW_LINE = System.lineSeparator();

    public String write(Project project, List<FluentExample> examples) {
        if (examples.isEmpty()) {
            return "";
        }

        final String repoUrl = project.getSdkRepositoryUri().get();
        final String sampleUrl = repoUrl + "/src/samples/java/" + project.getNamespace().replace(".", "/") + "/";

        heading("Code snippets and samples", 1);

        String groupName = null;
        for (FluentExample example : examples) {
            if (!Objects.equals(groupName, example.getGroupName())) {
                newLine();
                heading(example.getGroupName(), 3);
            }

            groupName = example.getGroupName();

            try {
                URL exampleJavaUrl = new URL(sampleUrl + example.getClassName() + ".java");

                unorderedList(link(example.getMethodName(), exampleJavaUrl));
            } catch (MalformedURLException e) {
                throw new IllegalStateException(e);
            }
        }

        return builder.toString();
    }

    private static String link(String text, URL url) {
        return '[' + text + ']' + '(' + url.toString() + ')';
    }

    private void heading(String text, int level) {
        builder.append(StringUtils.repeat('#', level)).append(' ').append(text)
                .append(NEW_LINE).append(NEW_LINE);
    }

    private void unorderedList(String text) {
        builder.append("- ").append(text).append(NEW_LINE);
    }

    private void newLine() {
        builder.append(NEW_LINE);
    }
}
