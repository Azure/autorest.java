// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent;

import com.azure.autorest.TypeSpecPlugin;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.azure.autorest.fluentnamer.FluentNamer;

import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.Map;

public class TypeSpecFluentNamer extends FluentNamer {
    private final Map<String, Object> settingsMap;
    private final CodeModel codeModel;
    public TypeSpecFluentNamer(NewPlugin plugin, String pluginName, String sessionId, Map<String, Object> settingsMap, CodeModel codeModel) {
        super(plugin, new TypeSpecPlugin.MockConnection(), pluginName, sessionId);
        this.settingsMap = settingsMap;
        this.codeModel = codeModel;
    }

    @Override
    protected CodeModel getCodeModelAndWriteToTargetFolder(Path codeModelFolder) {
        return this.codeModel;
    }

    @Override
    public <T> T getValue(Type type, String key) {
        // in case parent class constructor calls this method, e.g. new PluginLogger()
        if (settingsMap == null) {
            return null;
        }
        return (T) settingsMap.get(key);
    }
}
