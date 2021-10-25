/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.projectmodel;

import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.util.TemplateUtil;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoField.*;

public class Changelog {
    private static final Logger logger = new PluginLogger(FluentGen.getPluginInstance(), Changelog.class);

    private static final Pattern UNRELEASED_VERSION_PATTERN =
        Pattern.compile("^## ([0-9][-.a-z|0-9]+) \\(Unreleased\\)");

    private final List<String> lines;

    public Changelog(FluentProject project) {
        this(FluentUtils.loadTextFromResource("Changelog.txt",
                TemplateUtil.SERVICE_NAME, project.getServiceName(),
                TemplateUtil.SERVICE_DESCRIPTION, project.getServiceDescriptionForMarkdown(),
                TemplateUtil.ARTIFACT_VERSION, project.getVersion(),
                TemplateUtil.DATE_UTC, getDateUtc()
        ));
    }

    public Changelog(String content) {
        this.lines = Arrays.stream(content.split("\r?\n")).collect(Collectors.toList());
    }

    public Changelog(BufferedReader reader) {
        this.lines = reader.lines().collect(Collectors.toList());
    }

    public void updateForVersion(FluentProject project) {
        List<String> sectionBefore = new ArrayList<>();
        List<String> sectionAfter = new ArrayList<>();
        String previousUnreleasedVersion = null;
        List<String> previousChangelog = new ArrayList<>();

        Pattern currentVersionPattern = Pattern.compile("^## " + Pattern.quote(project.getVersion())+ " \\(.*\\)");

        boolean beforeUnreleasedSection = true;
        boolean afterUnreleasedSection = false;
        for (String line : this.lines) {
            if (line.trim().startsWith("## ")) {
                if (beforeUnreleasedSection) {
                    beforeUnreleasedSection = false;

                    if (line.trim().endsWith("(Unreleased)")) {
                        Matcher m = UNRELEASED_VERSION_PATTERN.matcher(line.trim());
                        if (m.find()) {
                            previousUnreleasedVersion = m.group(1);
                            logger.info("Found last unreleased version '{}'", previousUnreleasedVersion);
                        }
                    } else if (currentVersionPattern.matcher(line.trim()).find()) {
                        previousUnreleasedVersion = project.getVersion();
                        logger.info("Found last version '{}', which is same as current version", previousUnreleasedVersion);
                    } else {
                        afterUnreleasedSection = true;
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
        this.lines.add(String.format("## %1$s (%2$s)", project.getVersion(), getDateUtc()));
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
        return String.join("\n", lines) + "\n";
    }

    List<String> getLines() {
        return lines;
    }

    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .appendValue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
            .appendLiteral('-')
            .appendValue(MONTH_OF_YEAR, 2)
            .appendLiteral('-')
            .appendValue(DAY_OF_MONTH, 2)
            .toFormatter(Locale.ROOT);

    static String getDateUtc() {
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        return now.format(FORMATTER);
    }
}
