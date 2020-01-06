/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.mapper.ObjectMapper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class FluentObjectMapper extends ObjectMapper {

    private static FluentObjectMapper _instance = new FluentObjectMapper();

    public static FluentObjectMapper getInstance() {
        return _instance;
    }

    private Set<ObjectSchema> innerModels = ConcurrentHashMap.newKeySet();

    protected boolean isInnerModel(ObjectSchema compositeType) {
        return innerModels.contains(compositeType);
    }

    /**
     * Add types as Inner.
     * @param compositeTypes The types to add as Inner.
     * @return The types from compositeTypes that need to be added.
     */
    public Set<ObjectSchema> addInnerModels(Collection<ObjectSchema> compositeTypes) {
        Set<ObjectSchema> compositeTypesToAdd = new HashSet<>(compositeTypes);
        compositeTypesToAdd.removeAll(innerModels);
        innerModels.addAll(compositeTypesToAdd);
        return compositeTypesToAdd;
    }
}
