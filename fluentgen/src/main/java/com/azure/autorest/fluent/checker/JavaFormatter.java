/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.checker;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaFormatter {

    private static final Logger logger = LoggerFactory.getLogger(JavaFormatter.class);

    private final String content;
    private final String path;

    public JavaFormatter(String content, String path) {
        this.content = content;
        this.path = path;
    }

    public String format() {
        try {
            return new Formatter().formatSource(content);
        } catch (FormatterException e) {
            logger.warn("Failed to parse Java file {}, message: {}", path, e.getMessage());
            return content;
        }
    }
}
