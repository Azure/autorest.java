// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;

public class Package {

    public static final String GENERIC_CORE_PACKAGE_NAME = "com.generic.core";
    public static final String GENERIC_JSON_PACKAGE_NAME = "com.generic.json";

    public static final String AZURE_CORE_PACKAGE_NAME = "com.azure.core";
    public static final String AZURE_JSON_PACKAGE_NAME = "com.azure.json";


    public static final Package CORE = new Builder()
            .packageName(GENERIC_CORE_PACKAGE_NAME)
            .groupId("com.generic")
            .artifactId("generic-core")
            .build();

    public static final Package JSON = new Builder()
            .packageName(GENERIC_JSON_PACKAGE_NAME)
            .groupId("com.generic")
            .artifactId("generic-json")
            .build();

    private final String packageName;
    private final String groupId;
    private final String artifactId;

    private Package(String packageName, String groupId, String artifactId) {
        this.packageName = packageName;
        this.groupId = groupId;
        this.artifactId = artifactId;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public static final class Builder {
        private String packageName;
        private String groupId;
        private String artifactId;

        public Builder() {
        }

        public Builder packageName(String packageName) {
            this.packageName = packageName;
            return this;
        }

        public Builder groupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public Builder artifactId(String artifactId) {
            this.artifactId = artifactId;
            return this;
        }

        public Package build() {
            if (JavaSettings.getInstance().isBranding()) {
                switch (packageName) {
                    case GENERIC_CORE_PACKAGE_NAME:
                        packageName = AZURE_CORE_PACKAGE_NAME;
                        groupId = "com.azure";
                        artifactId = "azure-core";
                        break;

                    case GENERIC_JSON_PACKAGE_NAME:
                        packageName = AZURE_JSON_PACKAGE_NAME;
                        groupId = "com.azure";
                        artifactId = "azure-json";
                        break;
                }
            }
            return new Package(packageName, groupId, artifactId);
        }
    }
}
