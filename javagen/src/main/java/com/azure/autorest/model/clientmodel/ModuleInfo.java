/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.model.clientmodel;

import java.util.ArrayList;
import java.util.List;

public class ModuleInfo {
    private final String moduleName;
    private final List<RequireModule> requireModules = new ArrayList<>();
    private final List<ExportModule> exportModules = new ArrayList<>();
    private final List<OpenModule> openModules = new ArrayList<>();

    public static class RequireModule {
        private final String moduleName;
        private final boolean isTransitive;

        public RequireModule(String moduleName) {
            this.moduleName = moduleName;
            this.isTransitive = false;
        }

        public RequireModule(String moduleName, boolean isTransitive) {
            this.moduleName = moduleName;
            this.isTransitive = isTransitive;
        }

        public String getModuleName() {
            return moduleName;
        }

        public boolean isTransitive() {
            return isTransitive;
        }
    }

    public static class ExportModule {
        private final String moduleName;

        public ExportModule(String moduleName) {
            this.moduleName = moduleName;
        }

        public String getModuleName() {
            return moduleName;
        }
    }

    public static class OpenModule {
        private final String moduleName;
        private final List<String> openToModules;

        public OpenModule(String moduleName) {
            this.moduleName = moduleName;
            this.openToModules = null;
        }

        public OpenModule(String moduleName, List<String> openToModules) {
            this.moduleName = moduleName;
            this.openToModules = openToModules;
        }

        public String getModuleName() {
            return moduleName;
        }

        public List<String> getOpenToModules() {
            return openToModules;
        }

        public boolean isOpenTo() {
            return openToModules != null && !openToModules.isEmpty();
        }
    }

    public ModuleInfo(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public List<RequireModule> getRequireModules() {
        return requireModules;
    }

    public List<ExportModule> getExportModules() {
        return exportModules;
    }

    public List<OpenModule> getOpenModules() {
        return openModules;
    }
}
