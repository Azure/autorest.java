/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.checker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class JavaFormatter {

    private static final Logger logger = LoggerFactory.getLogger(JavaFormatter.class);

    private final String content;
    private final String path;

    public JavaFormatter(String content, String path) {
        this.content = content;
        this.path = path;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public String format() {
        try {
            //return new Formatter().formatSource(content);

            Class formatterClass = this.getClass().getClassLoader().loadClass("com.google.googlejavaformat.java.Formatter");
            Object formatterInstance = formatterClass.getConstructor().newInstance();
            Method formatSourceMethod = formatterClass.getMethod("formatSource", String.class);
            return (String) formatSourceMethod.invoke(formatterInstance, content);
        } catch (Exception e) {
            logger.warn("Failed to parse Java file {}, message: {}", path, e.getMessage());
            return content;
        }
    }
}
