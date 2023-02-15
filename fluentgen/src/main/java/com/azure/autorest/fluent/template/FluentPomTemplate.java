// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.template;

import com.azure.autorest.model.xmlmodel.XmlBlock;
import com.azure.autorest.template.PomTemplate;

public class FluentPomTemplate extends PomTemplate {

    private static final FluentPomTemplate INSTANCE = new FluentPomTemplate();

//    private static FluentProject project;

    protected FluentPomTemplate() {
    }

    public static FluentPomTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    protected void writeJacoco(XmlBlock propertiesBlock) {
        super.writeJacoco(propertiesBlock);

        propertiesBlock.tag("jacoco.min.linecoverage", "0");
        propertiesBlock.tag("jacoco.min.branchcoverage", "0");
    }

//    public static void setProject(FluentProject project) {
//        FluentPomTemplate.project = project;
//    }
}
