/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.fluent.model.FluentType;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.mapper.ObjectMapper;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.core.util.FluxUtil;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class FluentObjectMapper extends ObjectMapper {

    private static final FluentObjectMapper INSTANCE = new FluentObjectMapper();

    public static FluentObjectMapper getInstance() {
        return INSTANCE;
    }

    private final Set<ObjectSchema> innerModels = ConcurrentHashMap.newKeySet();

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
        } else if (compositeType.getLanguage().getJava().getName().equals(FluentType.ManagementError.getName())) {
            result = FluentType.ManagementError;
        }
        return result;
    }

    /**
     * Add types as Inner.
     *
     * @param compositeTypes The types to add as Inner.
     * @return The types from compositeTypes that need to be added.
     */
    public Set<ObjectSchema> addInnerModels(Collection<ObjectSchema> compositeTypes) {
        Set<ObjectSchema> compositeTypesToAdd = new HashSet<>(compositeTypes);
        compositeTypesToAdd.removeAll(innerModels);
        innerModels.addAll(compositeTypesToAdd);
        return compositeTypesToAdd;
    }

    /**
     * Remove types as Inner.
     *
     * @param javaNames The Java class names to remove as Inner.
     */
    public void removeInnerModels(Set<String> javaNames) {
        Set<ObjectSchema> compositeTypesToRemove = innerModels.stream()
                .filter(type -> javaNames.contains(Utils.getJavaName(type)))
                .collect(Collectors.toSet());
        innerModels.removeAll(compositeTypesToRemove);
    }
}
