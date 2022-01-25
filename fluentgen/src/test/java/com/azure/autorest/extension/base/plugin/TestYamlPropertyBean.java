/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.extension.base.plugin;


import com.azure.autorest.extension.base.model.codemodel.YamlProperty;

public class TestYamlPropertyBean {

    private Extensions extensions;
    private Boolean propertyToRemap;

    public Extensions getExtensions() {
        return extensions;
    }

    public void setExtensions(Extensions extensions) {
        this.extensions = extensions;
    }

    @YamlProperty("property-to-remap")
    public Boolean getPropertyToRemap() {
        return propertyToRemap;
    }

    public void setPropertyToRemap(Boolean propertyToRemap) {
        this.propertyToRemap = propertyToRemap;
    }

    public static class Extensions {
        private Boolean xmsExamples;

        @YamlProperty("x-ms-examples")
        public Boolean getXmsExamples() {
            return xmsExamples;
        }

        public void setXmsExamples(Boolean xmsExamples) {
            this.xmsExamples = xmsExamples;
        }
    }
}
