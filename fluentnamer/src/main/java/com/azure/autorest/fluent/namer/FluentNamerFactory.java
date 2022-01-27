// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.namer;

import com.azure.autorest.fluent.util.FluentJavaSettings;
import com.azure.autorest.util.ModelNamer;
import com.azure.autorest.util.NamerFactory;

public class FluentNamerFactory implements NamerFactory {

    private final ModelNamer modelNamer;

    public FluentNamerFactory(FluentJavaSettings settings) {
        modelNamer = settings.isTrack1Naming() ? new FluentModelNamer() : new ModelNamer();
    }

    @Override
    public ModelNamer getModelNamer() {
        return modelNamer;
    }
}
