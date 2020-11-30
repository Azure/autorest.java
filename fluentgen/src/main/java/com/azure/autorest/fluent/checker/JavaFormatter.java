/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.checker;

import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.FluentGen;
import com.azure.core.util.CoreUtils;
import org.slf4j.Logger;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class JavaFormatter {

    private static final Logger logger = new PluginLogger(FluentGen.getPluginInstance(), JavaFormatter.class);

    private static final boolean ENABLED;
    static {
        boolean enabled = false;
        String version = System.getProperty("java.version");
        if (!CoreUtils.isNullOrEmpty(version)) {
            logger.info("Java version: {}", version);
            String[] segments = version.split(Pattern.quote("."));
            if (segments.length >= 2 && !segments[0].equals("1")) {
                try {
                    int majorVersion = Integer.parseInt(segments[0]);
                    if (majorVersion >= 11) {
                        enabled = true;
                    }
                } catch (NumberFormatException e) {
                    //
                }
            }
        }
        ENABLED = enabled;
        logger.info("Java formatter {}", enabled ? "enabled" : "disabled");
    }

    private final String content;
    private final String path;

    public JavaFormatter(String content, String path) {
        this.content = content;
        this.path = path;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public String format() {
        if (!ENABLED) {
            return content;
        }

        try {
            //return new Formatter().formatSourceAndFixImports(content);

            Class formatterClass = this.getClass().getClassLoader().loadClass("com.google.googlejavaformat.java.Formatter");
            Object formatterInstance = formatterClass.getConstructor().newInstance();
            Method formatSourceMethod = formatterClass.getMethod("formatSourceAndFixImports", String.class);
            String formattedCode = (String) formatSourceMethod.invoke(formatterInstance, content);
            return fixOverlongStringLiteral(formattedCode);
        } catch (Exception e) {
            logger.warn("Failed to parse Java file {}, message: {}", path, e.getMessage());
            return content;
        }
    }

    private static String fixOverlongStringLiteral(String content) {
        final int limit = 120;
        final String quote = "\"";

        List<String> formattedLines = new ArrayList<>();
        String[] lines = content.split("\r?\n", -1);
        for (String line : lines) {
            if (line.length() > limit) {
                int firstQuote = line.indexOf(quote);
                int lastQuote = line.lastIndexOf(quote);
                if (firstQuote < lastQuote) {
                    int lineIndent = 0;
                    for (int j = 0; j < line.length(); ++j) {
                        if (line.charAt(j) != ' ') {
                            lineIndent = j;
                            break;
                        }
                    }
                    lineIndent += 4;
                    String lineIndentStr = String.join("", Collections.nCopies(lineIndent, " "));

                    String stringLiteral = line.substring(firstQuote + 1, lastQuote);
                    char breakChar = 0;
                    if (stringLiteral.contains("/")) {
                        breakChar = '/';
                    } else if (stringLiteral.contains(".")) {
                        breakChar = '.';
                    } else if (stringLiteral.contains(",")) {
                        breakChar = ',';
                    } else if (stringLiteral.contains(" ")) {
                        breakChar = ' ';
                    }

                    for (int i = Math.min(lastQuote, limit) - 2; i >= 0; --i) {
                        char c = line.charAt(i + 1);
                        if (breakChar == 0 || breakChar == c) {
                            String firstLine = line.substring(0, i + 1) + quote;
                            formattedLines.add(firstLine);

                            String breakedLine = lineIndentStr + "+ " + quote + line.substring(i + 1);
                            while (breakedLine.length() > limit) {
                                lastQuote = breakedLine.lastIndexOf(quote);

                                for (int j = Math.min(lastQuote, limit) - 2; j >= 0; --j) {
                                    c = breakedLine.charAt(j + 1);
                                    if (breakChar == 0 || breakChar == c) {
                                        String nextBreakedLine = lineIndentStr + "+ " + quote + breakedLine.substring(j + 1);
                                        breakedLine = breakedLine.substring(0, j + 1) + quote;

                                        formattedLines.add(breakedLine);
                                        breakedLine = nextBreakedLine;
                                        break;
                                    }
                                }
                            }
                            formattedLines.add(breakedLine);
                            break;
                        }
                    }
                } else {
                    formattedLines.add(line);
                }
            } else {
                formattedLines.add(line);
            }
        }
        return String.join(System.lineSeparator(), formattedLines);
    }
}
