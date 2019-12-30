package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.mapper.ObjectMapper;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class FluentObjectMapper extends ObjectMapper {

    private static FluentObjectMapper _instance = new FluentObjectMapper();

    public static FluentObjectMapper getInstance() {
        return _instance;
    }

    private Set<String> innerModelJavaNames = ConcurrentHashMap.newKeySet();

    protected boolean isInnerModel(ObjectSchema compositeType) {
        return compositeType.getLanguage() != null && compositeType.getLanguage().getJava() != null
                && innerModelJavaNames.contains(compositeType.getLanguage().getJava().getName());
    }

    /**
     * Add types as Inner.
     * @param compositeTypes The types to add as Inner.
     * @return The types from compositeTypes that need to be added.
     */
    public Set<ObjectSchema> addInnerModels(Collection<ObjectSchema> compositeTypes) {
        final Set<String> compositeTypeNames = compositeTypes.stream()
                .map(t -> t.getLanguage().getJava().getName())
                .collect(Collectors.toSet());
        compositeTypeNames.removeAll(innerModelJavaNames);
        innerModelJavaNames.addAll(compositeTypeNames);
        return compositeTypes.stream()
                .filter(t -> compositeTypeNames.contains(t.getLanguage().getJava().getName()))
                .collect(Collectors.toSet());
    }
}
