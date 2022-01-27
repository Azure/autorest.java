// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.util;

public class DefaultNamerFactory implements NamerFactory {

    private static final ModelNamer modelNamer = new ModelNamer();

    @Override
    public ModelNamer getModelNamer() {
        return modelNamer;
    }
}
