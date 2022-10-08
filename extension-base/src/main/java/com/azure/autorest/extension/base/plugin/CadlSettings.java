// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.plugin;

public class CadlSettings {
    private String outputFolder;

    public String getOutputFolder() {
        return outputFolder;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String outputFolder;

        public Builder outputFolder(String outputFolder) {
            this.outputFolder = outputFolder;
            return this;
        }

        public CadlSettings build() {
            CadlSettings cadlSettings = new CadlSettings();
            cadlSettings.outputFolder = this.outputFolder;
            return cadlSettings;
        }
    }
}
