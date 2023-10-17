package com.azure.autorest.fluent;

import com.azure.autorest.TypeSpecPlugin;
import com.azure.typespec.model.EmitterOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TypeSpecFluentPlugin extends FluentGen {
    private static final Logger LOGGER = LoggerFactory.getLogger(TypeSpecFluentPlugin.class);

    public TypeSpecFluentPlugin(EmitterOptions options, boolean sdkIntegration) {
        super(new TypeSpecPlugin.MockConnection(), "dummy", "dummy");

    }
}
