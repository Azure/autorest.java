/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 *
 */

package com.azure.autorest.fluent.util;

import com.azure.autorest.util.ModelNamer;
import com.azure.autorest.util.NamerFactory;

public class FluentNamerFactory implements NamerFactory {

    private final ModelNamer modelNamer;

    public FluentNamerFactory(FluentJavaSettings settings) {
        modelNamer = settings.isTrack2Naming() ? new ModelNamer() : new FluentModelNamer();
    }

    @Override
    public ModelNamer getModelNamer() {
        return modelNamer;
    }
}
