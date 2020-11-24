/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.projectmodel;

import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.template.TextTemplate;
import com.azure.autorest.fluent.util.FluentUtils;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Changelog {

    private static final Logger logger = new PluginLogger(FluentGen.getPluginInstance(), Changelog.class);

    private final List<String> lines;

    public Changelog(Project project) {
        this(FluentUtils.loadTextFromResource("Changelog.txt",
                TextTemplate.SERVICE_NAME, project.getServiceName(),
                TextTemplate.SERVICE_DESCRIPTION, project.getServiceDescriptionForMarkdown(),
                TextTemplate.ARTIFACT_VERSION, project.getVersion()
        ));
    }

    public Changelog(String content) {
        this.lines = Arrays.stream(content.split("\n")).collect(Collectors.toList());
    }

    public Changelog(BufferedReader reader) {
        this.lines = reader.lines().collect(Collectors.toList());
    }

    public void updateForVersion(Project project) {
        List<String> sectionBefore = new ArrayList<>();
        List<String> sectionAfter = new ArrayList<>();
        String previousUnreleasedVersion = null;
        List<String> previousChangelog = new ArrayList<>();

        Pattern unreleasedVersionPattern = Pattern.compile("^## ([0-9][-.a-z|0-9]+) \\(Unreleased\\)");

        boolean beforeUnreleasedSection = true;
        boolean afterUnreleasedSection = false;
        for (String line : this.lines) {
            if (line.trim().startsWith("## ")) {
                beforeUnreleasedSection = false;

                if (line.trim().endsWith("(Unreleased)")) {
                    Matcher m = unreleasedVersionPattern.matcher(line.trim());
                    if (m.find()) {
                        previousUnreleasedVersion = m.group(1);
                        logger.info("Found last unreleased version {}", previousUnreleasedVersion);
                    }
                } else {
                    afterUnreleasedSection = true;
                }
            } else if (!beforeUnreleasedSection && !afterUnreleasedSection) {
                if (!previousChangelog.isEmpty() || !line.isEmpty()) {
                    previousChangelog.add(line);
                }
            }

            if (beforeUnreleasedSection) {
                sectionBefore.add(line);
            }
            if (afterUnreleasedSection) {
                sectionAfter.add(line);
            }
        }

        String currentChangelog = String.format("- Azure Resource Manager %1$s client library for Java. %2$s", project.getServiceName(), project.getServiceDescriptionForMarkdown());

        this.lines.clear();

        this.lines.addAll(sectionBefore);
        this.lines.add(String.format("## %s (Unreleased)", project.getVersion()));
        this.lines.add("");
        this.lines.add(currentChangelog);
        if (!previousChangelog.isEmpty() && !previousChangelog.iterator().next().startsWith("- ")) {
            // blank line when first line is not unordered list
            this.lines.add("");
        }
        for (String line : previousChangelog) {
            if (!line.trim().equals(currentChangelog)) {
                this.lines.add(line);
            }
        }
        if (previousChangelog.isEmpty() || !previousChangelog.get(previousChangelog.size() - 1).trim().isEmpty()) {
            // blank line when last line is not blank line (or no line at all)
            this.lines.add("");
        }
        this.lines.addAll(sectionAfter);
    }

    public String getContent() {
        return String.join("\n", lines);
    }
}
