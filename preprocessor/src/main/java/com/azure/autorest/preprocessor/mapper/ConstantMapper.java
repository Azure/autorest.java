package com.azure.autorest.preprocessor.mapper;

import com.azure.autorest.extension.base.model.codemodel.ConstantSchema;
import com.azure.autorest.preprocessor.model.clientmodel.IType;
import java.util.HashMap;
import java.util.Map;

public class ConstantMapper implements IMapper<ConstantSchema, IType> {
    private static ConstantMapper instance = new ConstantMapper();
    Map<ConstantSchema, IType> parsed = new HashMap<>();

    private ConstantMapper() {
    }

    public static ConstantMapper getInstance() {
        return instance;
    }

    @Override
    public IType map(ConstantSchema constantSchema) {
        if (constantSchema == null) {
            return null;
        }
        if (parsed.containsKey(constantSchema)) {
            return parsed.get(constantSchema);
        }

        IType backedType = Mappers.getSchemaMapper().map(constantSchema.getValueType());

        //TODO: constants
        IType iType = backedType;

        parsed.put(constantSchema, iType);
        return iType;
    }
}
