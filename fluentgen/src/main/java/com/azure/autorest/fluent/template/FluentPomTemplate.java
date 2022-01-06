/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.model.projectmodel.FluentProject;
import com.azure.autorest.model.clientmodel.Pom;
import com.azure.autorest.model.xmlmodel.XmlBlock;
import com.azure.autorest.template.PomTemplate;

public class FluentPomTemplate extends PomTemplate {

    private static final FluentPomTemplate INSTANCE = new FluentPomTemplate();

    private static FluentProject project;

    protected FluentPomTemplate() {
    }

    public static FluentPomTemplate getInstance() {
        return INSTANCE;
    }

    public static void setProject(FluentProject project) {
        FluentPomTemplate.project = project;
    }

    @Override
    protected void writeBuildBlock(XmlBlock projectBlock, Pom pom) {
        projectBlock.block("build", buildBlock -> buildBlock.block("plugins", pluginsBlock -> {
            if (!FluentStatic.getFluentJavaSettings().isSdkIntegration()) {
                writeStandAlonePlugins(pluginsBlock);
            }
        }));
    }
}
