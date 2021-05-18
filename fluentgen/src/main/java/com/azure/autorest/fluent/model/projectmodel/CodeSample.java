/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.projectmodel;

import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.FluentGen;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CodeSample {

    private static final Logger logger = new PluginLogger(FluentGen.getPluginInstance(), Project.class);

    private static final String TEST_ANNOTATION = "@Test";

    private String code;

    protected CodeSample() {
    }

    public static CodeSample fromTestFile(Path testFilePath) {
        // the assumption is there is a try block in a @Test method

        CodeSample codeSample = new CodeSample();

        try (BufferedReader reader = Files.newBufferedReader(testFilePath, StandardCharsets.UTF_8)) {
            List<String> codeLines = new ArrayList<>();

            boolean testMethodBegin = false;
            boolean tryMethodBegin = false;
            String testMethodIndent = "";
            String tryMethodIndent = "";
            for (String line : reader.lines().collect(Collectors.toList())) {
                if (!testMethodBegin) {
                    if (line.trim().equals(TEST_ANNOTATION)) {
                        // first get inside @Test method

                        testMethodBegin = true;
                        int indent = line.indexOf(TEST_ANNOTATION);
                        char[] chars = new char[indent];
                        Arrays.fill(chars, ' ');
                        testMethodIndent = String.valueOf(chars);
                    }
                } else if (!tryMethodBegin) {
                    if (line.startsWith(testMethodIndent + "}")) {
                        // method ends without try block

                        testMethodBegin = false;
                        break;
                    } else if (line.trim().equals("try {")) {
                        // next get inside try block

                        tryMethodBegin = true;
                        int indent = line.indexOf("try {");
                        char[] chars = new char[indent];
                        Arrays.fill(chars, ' ');
                        tryMethodIndent = String.valueOf(chars);
                    }
                } else  {
                    if (line.startsWith(tryMethodIndent + "} finally {") || line.startsWith(tryMethodIndent + "} catch (")) {
                        // try block ends

                        tryMethodBegin = false;
                        break;
                    } else {
                        // extract the code line (except Assertions)

                        if (!line.trim().startsWith("Assertions.")) {
                            codeLines.add(line);
                        }
                    }
                }
            }

            if (!codeLines.isEmpty() && !tryMethodBegin) {
                codeLines = removeIndent(codeLines);
                codeSample.code = String.join("\n", codeLines) + "\n";

                logger.info("Read {} lines of code sample from test file '{}'", codeLines.size(), testFilePath);
            }
        } catch (IOException e) {
            logger.warn("Failed to read '" + testFilePath + "'", e);
        }

        return codeSample;
    }

    public String getCode() {
        return code;
    }

    private static List<String> removeIndent(List<String> codeLines) {
        int minIndent = Integer.MAX_VALUE;
        for (String line : codeLines) {
            String trimmedLine = line.trim();
            if (!trimmedLine.isEmpty()) {
                int indent = line.indexOf(trimmedLine);
                if (indent < minIndent) {
                    minIndent = indent;
                }
            }
        }

        List<String> lines = codeLines;
        if (minIndent > 0) {
            lines = new ArrayList<>();
            for (String line : codeLines) {
                if (line.length() > minIndent) {
                    lines.add(line.substring(minIndent));
                } else {
                    lines.add("");
                }
            }
        }
        return lines;
    }
}
