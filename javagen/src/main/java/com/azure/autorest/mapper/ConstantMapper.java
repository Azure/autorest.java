package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ConstantSchema;
import com.azure.autorest.model.clientmodel.IType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConstantMapper implements IMapper<ConstantSchema, IType> {
    private static final ConstantMapper INSTANCE = new ConstantMapper();
    Map<ConstantSchema, IType> parsed = new ConcurrentHashMap<>();

    private ConstantMapper() {
    }

    public static ConstantMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public IType map(ConstantSchema constantSchema) {
        if (constantSchema == null) {
            return null;
        }

        return parsed.computeIfAbsent(constantSchema, cSchema -> {
            //TODO: constants
            return Mappers.getSchemaMapper().map(cSchema.getValueType());
        });
    }
}
