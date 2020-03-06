/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 *
 */

package com.azure.autorest.fluent.checker;

import com.github.javaparser.ParseProblemException;
import com.github.javaparser.Problem;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

public class JavaChecker {

    private static final Logger logger = LoggerFactory.getLogger(JavaChecker.class);

    private final String content;
    private final String path;

    public JavaChecker(String content, String path) {
        this.content = content;
        this.path = path;
    }

    public boolean check() {
        try {
            CompilationUnit compilationUnit = StaticJavaParser.parse(content);
        } catch (ParseProblemException parseProblemException) {
            logger.error("Failed to parse Java file {}, message: {}", path,
                    parseProblemException.getProblems().stream().map(Problem::getMessage).collect(Collectors.joining("; ")));
            return false;
        }
        return true;
    }
}
