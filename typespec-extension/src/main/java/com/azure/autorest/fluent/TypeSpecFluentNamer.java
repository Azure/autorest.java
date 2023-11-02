// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent;

import com.azure.autorest.TypeSpecPlugin;
import com.azure.autorest.fluentnamer.FluentNamer;

import java.lang.reflect.Type;
import java.util.Map;

public class TypeSpecFluentNamer extends FluentNamer {
    private final Map<String, Object> settingsMap;
    public TypeSpecFluentNamer(Map<String, Object> settingsMap) {
        super(new TypeSpecPlugin.MockConnection(), "dummy", "dummy");
        this.settingsMap = settingsMap;
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
