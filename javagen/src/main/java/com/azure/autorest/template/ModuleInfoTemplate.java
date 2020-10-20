/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.template;

import com.azure.autorest.model.clientmodel.ModuleInfo;
import com.azure.autorest.model.javamodel.JavaFile;

public class ModuleInfoTemplate implements IJavaTemplate<ModuleInfo, JavaFile> {

    private static ModuleInfoTemplate INSTANCE = new ModuleInfoTemplate();

    private ModuleInfoTemplate() {
    }

    public static ModuleInfoTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    public void write(ModuleInfo model, JavaFile context) {
        context.line(String.format("module %1$s {", model.getModuleName()));
        context.indent(() -> {
            for (ModuleInfo.RequireModule module : model.getRequireModules()) {
                context.line(String.format("requires %1$s%2$s;",
                        module.isTransitive() ? "transitive " : "",
                        module.getModuleName()));
            }
            for (ModuleInfo.ExportModule module : model.getExportModules()) {
                context.line(String.format("exports %1$s;",
                        module.getModuleName()));
            }
            for (ModuleInfo.OpenModule module : model.getOpenModules()) {
                context.line(String.format("opens %1$s%2$s;",
                        module.getModuleName(),
                        module.isOpenTo() ? (" to " + String.join(", ", module.getOpenToModules())) : ""));
            }
        });
        context.line("}");
    }
}
