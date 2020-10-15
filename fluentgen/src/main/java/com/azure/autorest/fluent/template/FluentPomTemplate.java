/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.model.clientmodel.Pom;
import com.azure.autorest.model.xmlmodel.XmlBlock;
import com.azure.autorest.template.PomTemplate;

public class FluentPomTemplate extends PomTemplate {

    private static final FluentPomTemplate INSTANCE = new FluentPomTemplate();

    protected FluentPomTemplate() {
    }

    public static FluentPomTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    protected void writeBuildBlock(XmlBlock projectBlock, Pom pom) {
        projectBlock.block("build", buildBlock -> {
            buildBlock.block("plugins", pluginsBlock -> {
                pluginsBlock.block("plugin", pluginBlock -> {
                    pluginBlock.tag("groupId", "org.apache.maven.plugins");
                    pluginBlock.tag("artifactId", "maven-compiler-plugin");
                    pluginBlock.tag("version", "3.8.1");
                    pluginBlock.block("configuration", configurationBlock -> {
                        configurationBlock.tag("release", "11");
                    });
                });
            });
        });
    }
}
