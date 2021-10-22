package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.DictionarySchema;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.MapType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DictionaryMapper implements IMapper<DictionarySchema, IType> {
    private static final DictionaryMapper INSTANCE = new DictionaryMapper();
    Map<DictionarySchema, IType> parsed = new ConcurrentHashMap<>();

    private DictionaryMapper() {
    }

    public static DictionaryMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public IType map(DictionarySchema dictionaryType) {
        if (dictionaryType == null) {
            return null;
        }

        return parsed.computeIfAbsent(dictionaryType, dType ->
            new MapType(Mappers.getSchemaMapper().map(dType.getElementType())));
    }
}
