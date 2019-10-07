package com.azure.autorest.mapper;

import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.codemodel.Schema;

public class TypeMapper implements IMapper<Schema, IType> {
    private TypeMapper() {
    }

    @Override
    public IType map(Schema schema) {
        if (schema == null) {
            return null;
        }

        return null;
    }
}
