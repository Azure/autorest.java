/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.util;

import com.azure.autorest.Javagen;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ResourceUtil {

    private static final Logger logger = new PluginLogger(Javagen.getPluginInstance(), ResourceUtil.class);

    public static String loadTextFromResource(String filename, String... replacements) {
        String text = "";
        try (InputStream inputStream = ResourceUtil.class.getClassLoader().getResourceAsStream(filename)) {
            if (inputStream != null) {
                text = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                        .lines()
                        .collect(Collectors.joining(System.lineSeparator()));
                if (!text.isEmpty()) {
                    text += System.lineSeparator();
                }
            }

            if (replacements.length > 0) {
                if (replacements.length % 2 == 0) {
                    // replacement in template
                    for (int i = 0; i < replacements.length; i += 2) {
                        String key = replacements[i];
                        String value = replacements[i+1];
                        text = text.replace("{{" + key + "}}", value);
                    }
                } else {
                    logger.warn("Replacements skipped due to incorrect length: {}", Arrays.asList(replacements));
                }
            }
            return text;
        } catch (IOException e) {
            logger.error("Failed to read file '{}'", filename);
            throw new IllegalStateException(e);
        }
    }
}
