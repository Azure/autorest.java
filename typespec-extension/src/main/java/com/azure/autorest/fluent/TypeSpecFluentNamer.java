// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent;

import com.azure.autorest.TypeSpecPlugin;
import com.azure.autorest.fluentnamer.FluentNamer;

import java.lang.reflect.Type;

public class TypeSpecFluentNamer extends FluentNamer {
    private final TypeSpecFluentPlugin fluentPlugin;
    public TypeSpecFluentNamer(TypeSpecFluentPlugin fluentPlugin) {
        super(new TypeSpecPlugin.MockConnection(), "dummy", "dummy");
        this.fluentPlugin = fluentPlugin;
    }

    @Override
    public <T> T getValue(Type type, String key) {
        // in case parent class constructor calls this method, e.g. new PluginLogger()
        if (fluentPlugin == null) {
            return null;
        }
        return fluentPlugin.getValue(type, key);
    }
}
