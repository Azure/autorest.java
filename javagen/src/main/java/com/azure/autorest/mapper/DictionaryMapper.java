package com.azure.autorest.mapper;

import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.extension.base.model.codemodel.DictionarySchema;

import java.util.HashMap;
import java.util.Map;

public class DictionaryMapper implements IMapper<DictionarySchema, IType> {
    private DictionaryMapper() {
    }

    private static DictionaryMapper instance = new DictionaryMapper();

    public static DictionaryMapper getInstance() {
        return instance;
    }

    Map<DictionarySchema, IType> parsed = new HashMap<>();

    @Override
    public IType map(DictionarySchema dictionaryType) {
        if (dictionaryType == null)
        {
            return null;
        }
        if (parsed.containsKey(dictionaryType))
        {
            return parsed.get(dictionaryType);
        }
        IType itype = new MapType(Mappers.getSchemaMapper().map(dictionaryType.getElementType()));
        parsed.put(dictionaryType, itype);
        return itype;
    }
}
