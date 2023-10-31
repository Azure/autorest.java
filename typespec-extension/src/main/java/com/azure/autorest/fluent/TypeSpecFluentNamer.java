// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent;

import com.azure.autorest.TypeSpecPlugin;
import com.azure.autorest.fluentnamer.FluentNamer;

import java.lang.reflect.Type;

public class TypeSpecFluentNamer extends FluentNamer {
    public TypeSpecFluentNamer() {
        super(new TypeSpecPlugin.MockConnection(), "dummy", "dummy");
    }

    @Override
    public <T> T getValue(Type type, String key) {
        // Logger uses getValue to get log level, null will use default
        return null;
    }
}
