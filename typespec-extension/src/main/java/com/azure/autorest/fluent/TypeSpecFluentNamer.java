// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent;

import com.azure.autorest.TypeSpecPlugin;
import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.azure.autorest.fluentnamer.FluentNamer;

import java.lang.reflect.Type;
import java.util.Map;

public class TypeSpecFluentNamer extends FluentNamer {
    private final Map<String, Object> settingsMap;
    public TypeSpecFluentNamer(NewPlugin plugin, String pluginName, String sessionId, Map<String, Object> settingsMap) {
        super(plugin, new TypeSpecPlugin.MockConnection(), pluginName, sessionId);
        this.settingsMap = settingsMap;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getValue(Type type, String key) {
        // in case parent class constructor calls this method, e.g. new PluginLogger()
        if (settingsMap == null) {
            return null;
        }
        return (T) settingsMap.get(key);
    }
}
