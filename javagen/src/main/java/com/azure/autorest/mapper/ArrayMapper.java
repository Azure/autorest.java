package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ArraySchema;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.IterableType;
import com.azure.autorest.model.clientmodel.ListType;
import java.util.HashMap;
import java.util.Map;

public class ArrayMapper implements IMapper<ArraySchema, IType> {
    private static final ArrayMapper INSTANCE = new ArrayMapper();
    Map<ArraySchema, IType> parsed = new HashMap<>();

    private ArrayMapper() {
    }

    public static ArrayMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public IType map(ArraySchema sequenceType) {
        if (sequenceType == null) {
            return null;
        }

        return parsed.computeIfAbsent(sequenceType, sType -> {
            IType mappedType = Mappers.getSchemaMapper().map(sequenceType.getElementType());

            // Choose IterableType or ListType depending on whether arrays should use Iterable.
            return JavaSettings.getInstance().shouldUseIterable()
                ? new IterableType(mappedType)
                : new ListType(mappedType);
        });
    }
}
