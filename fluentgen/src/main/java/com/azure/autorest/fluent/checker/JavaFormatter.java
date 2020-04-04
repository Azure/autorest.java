/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.checker;

import com.azure.core.util.CoreUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class JavaFormatter {

    private static final Logger logger = LoggerFactory.getLogger(JavaFormatter.class);

    private static boolean ENABLED;
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
