package com.azure.autorest.mapper;

import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.extension.base.model.codemodel.ArraySchema;

import java.util.HashMap;
import java.util.Map;

public class ArrayMapper implements IMapper<ArraySchema, IType> {
    private ArrayMapper() {
    }

    private static ArrayMapper instance = new ArrayMapper();

    public static ArrayMapper getInstance() {
        return instance;
    }

    Map<ArraySchema, IType> parsed = new HashMap<>();

    @Override
    public IType map(ArraySchema sequenceType) {
        if (sequenceType == null)
        {
            return null;
        }
        if (parsed.containsKey(sequenceType))
        {
            return parsed.get(sequenceType);
        }
        IType iType = new ListType(Mappers.getSchemaMapper().map(sequenceType.getElementType()));
        parsed.put(sequenceType, iType);
        return iType;
    }
}
