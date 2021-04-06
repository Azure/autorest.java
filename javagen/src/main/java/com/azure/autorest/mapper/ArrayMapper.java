package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ArraySchema;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.IterableType;
import com.azure.autorest.model.clientmodel.ListType;
import java.util.HashMap;
import java.util.Map;

public class ArrayMapper implements IMapper<ArraySchema, IType> {
    private static ArrayMapper instance = new ArrayMapper();
    Map<ArraySchema, IType> parsed = new HashMap<>();

    private ArrayMapper() {
    }

    public static ArrayMapper getInstance() {
        return instance;
    }

    @Override
    public IType map(ArraySchema sequenceType) {
        if (sequenceType == null) {
            return null;
        }
        if (parsed.containsKey(sequenceType)) {
            return parsed.get(sequenceType);
        }
        IType iType = new ListType(Mappers.getSchemaMapper().map(sequenceType.getElementType()));
        if (JavaSettings.getInstance().shouldUseIterable()) {
            iType = new IterableType(Mappers.getSchemaMapper().map(sequenceType.getElementType()));
        }
        parsed.put(sequenceType, iType);
        return iType;
    }
}
