/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 *
 */

package com.azure.autorest.util;

public class DefaultNamerFactory implements NamerFactory {

    protected static final ModelNamer modelNamer = new ModelNamer();

    @Override
    public ModelNamer getModelNamer() {
        return modelNamer;
    }
}
