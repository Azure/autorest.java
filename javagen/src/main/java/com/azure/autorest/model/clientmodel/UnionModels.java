// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

import java.util.HashMap;
import java.util.Map;

public class UnionModels {

    private static final UnionModels INSTANCE = new UnionModels();
    private final Map<String, UnionModel> nameMap = new HashMap<>();

    private UnionModels() {
    }

    public final void clear() {
        nameMap.clear();
    }

    public static UnionModels getInstance() {
        return INSTANCE;
    }

    /**
     * Gets the UnionModel instance from the name of the model.
     *
     * @param modelName the name of the model.
     * @return the UnionModel instance.
     */
    public final UnionModel getModel(String modelName) {
        return nameMap.get(modelName);
    }

    public final void addModel(UnionModel model) {
        nameMap.put(model.getName(), model);
    }
}
