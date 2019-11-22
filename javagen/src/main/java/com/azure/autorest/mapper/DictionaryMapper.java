package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.DictionarySchema;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.MapType;

import java.util.HashMap;
import java.util.Map;

public class DictionaryMapper implements IMapper<DictionarySchema, IType> {
    private static DictionaryMapper instance = new DictionaryMapper();
    Map<DictionarySchema, IType> parsed = new HashMap<>();

    private DictionaryMapper() {
    }

    public static DictionaryMapper getInstance() {
        return instance;
    }

    @Override
    public IType map(DictionarySchema dictionaryType) {
        if (dictionaryType == null) {
            return null;
        }
        if (parsed.containsKey(dictionaryType)) {
            return parsed.get(dictionaryType);
        }
        IType itype = new MapType(Mappers.getSchemaMapper().map(dictionaryType.getElementType()));
        parsed.put(dictionaryType, itype);
        return itype;
    }
}
