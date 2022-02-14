// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.projectmodel.Project;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SwaggerReadmeTemplate {

    private final StringBuilder builder = new StringBuilder();

    private static final String NEW_LINE = System.lineSeparator();

    private static final Map<String, Object> OVERRIDE_SETTINGS = new LinkedHashMap<>();
    static {
        OVERRIDE_SETTINGS.put("output-folder", "../");
        OVERRIDE_SETTINGS.put("java", true);
        OVERRIDE_SETTINGS.put("regenerate-pom", false);
        OVERRIDE_SETTINGS.put("partial-update", true);
        OVERRIDE_SETTINGS.put("sdk-integration", null);
    }

    public String write(Project project) {
        JavaSettings settings = JavaSettings.getInstance();

        // prepare OVERRIDE_SETTINGS
        settings.getAutorestSettings().getTitle()
                .ifPresent(value -> OVERRIDE_SETTINGS.putIfAbsent("title", value));
        if (!settings.getAutorestSettings().getSecurity().isEmpty()) {
            OVERRIDE_SETTINGS.putIfAbsent("security",
                    stringOrArray(settings.getAutorestSettings().getSecurity()));
        }
        if (!settings.getAutorestSettings().getSecurityScopes().isEmpty()) {
            OVERRIDE_SETTINGS.putIfAbsent("security-scopes",
                    stringOrArray(settings.getAutorestSettings().getSecurityScopes()));
        }
        settings.getAutorestSettings().getSecurityHeaderName()
                .ifPresent(value -> OVERRIDE_SETTINGS.putIfAbsent("security-header-name", value));

        // prepare YAML object
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(dumperOptions);

        Map<String, Object> objectNode = new LinkedHashMap<>();
        objectNode.put("input-files", settings.getAutorestSettings().getInputFiles());
        // settings from internal
        for (Map.Entry<String, Object> entry : OVERRIDE_SETTINGS.entrySet()) {
            if (entry.getValue() != null) {
                objectNode.put(entry.getKey(), entry.getValue());
            }
        }
        // settings from external
        for (Map.Entry<String, Object> entry : settings.getSimpleJavaSettings().entrySet()) {
            if (!OVERRIDE_SETTINGS.containsKey(entry.getKey()) && entry.getValue() != null) {
                objectNode.put(entry.getKey(), entry.getValue());
            }
        }
        // service-versions
        objectNode.put("service-versions", Collections.singletonList(project.getApiVersion()));

        // write README
        line("## Generate autorest code");
        newLine();
        line("```yaml");
        builder.append(yaml.dump(objectNode));
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

    private Object stringOrArray(List<String> array) {
        if (array.size() == 1) {
            return array.iterator().next();
        } else {
            return array;
        }
    }
}
