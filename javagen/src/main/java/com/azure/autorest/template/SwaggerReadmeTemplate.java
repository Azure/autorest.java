/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.projectmodel.Project;

import java.util.HashMap;
import java.util.Map;

public class SwaggerReadmeTemplate {

    private final StringBuilder builder = new StringBuilder();

    private static final String NEW_LINE = System.lineSeparator();

    private static final Map<String, String> OVERRIDE_SETTINGS = new HashMap<>();
    static {
        OVERRIDE_SETTINGS.put("java", "true");
        OVERRIDE_SETTINGS.put("output-folder", "../");
        OVERRIDE_SETTINGS.put("sdk-integration", null);
    }

    public String write(Project project) {
        JavaSettings settings = JavaSettings.getInstance();

        line("## Generate autorest code");
        newLine();

        line("```yaml");
        for (String jsonPath : settings.getAutorestSettings().getInputFiles()) {
            line(String.format("%s: %s", "input-file", jsonPath));
        }
        for (Map.Entry<String, String> entry : OVERRIDE_SETTINGS.entrySet()) {
            if (entry.getValue() != null) {
                line(String.format("%s: %s", entry.getKey(), entry.getValue()));
            }
        }
        for (Map.Entry<String, String> entry : settings.getSimpleJavaSettings().entrySet()) {
            if (!OVERRIDE_SETTINGS.containsKey(entry.getKey()) && entry.getValue() != null) {
                line(String.format("%s: %s", entry.getKey(), entry.getValue()));
            }
        }
        line("```");

        return builder.toString();
    }

    private void line(String text) {
        builder.append(text);
        newLine();
    }

    private void newLine() {
        builder.append(NEW_LINE);
    }
}
