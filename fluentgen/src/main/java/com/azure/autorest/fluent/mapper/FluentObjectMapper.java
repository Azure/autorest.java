/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.fluent.model.FluentType;
import com.azure.autorest.mapper.ObjectMapper;
import com.azure.autorest.model.clientmodel.ClassType;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class FluentObjectMapper extends ObjectMapper {

    private static final FluentObjectMapper instance = new FluentObjectMapper();

    public static FluentObjectMapper getInstance() {
        return instance;
    }

    private Set<ObjectSchema> innerModels = ConcurrentHashMap.newKeySet();

    @Override
    protected boolean isInnerModel(ObjectSchema compositeType) {
        return innerModels.contains(compositeType);
    }

    @Override
    protected boolean isImplementedModel(ClassType modelType) {
        return !FluentType.nonResourceType(modelType) || !FluentType.nonManagementError(modelType);
    }

    @Override
    protected ClassType mapImplementedModel(ObjectSchema compositeType) {
        ClassType result = null;
        if (compositeType.getLanguage().getJava().getName().equals(FluentType.Resource.getName())) {
            result = FluentType.Resource;
        } else if (compositeType.getLanguage().getJava().getName().equals(FluentType.ProxyResource.getName())) {
            result = FluentType.ProxyResource;
        } else if (compositeType.getLanguage().getJava().getName().equals(FluentType.SubResource.getName())) {
            result = FluentType.SubResource;
        }
        return result;
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
