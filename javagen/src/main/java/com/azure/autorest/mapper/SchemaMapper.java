package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.AnySchema;
import com.azure.autorest.extension.base.model.codemodel.ArraySchema;
import com.azure.autorest.extension.base.model.codemodel.BinarySchema;
import com.azure.autorest.extension.base.model.codemodel.ChoiceSchema;
import com.azure.autorest.extension.base.model.codemodel.ConstantSchema;
import com.azure.autorest.extension.base.model.codemodel.DictionarySchema;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.PrimitiveSchema;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.model.codemodel.SealedChoiceSchema;
import com.azure.autorest.model.clientmodel.IType;
import java.util.HashMap;
import java.util.Map;

public class SchemaMapper implements IMapper<Schema, IType> {
    private static SchemaMapper instance = new SchemaMapper();
    Map<Schema, IType> parsed = new HashMap<>();

    private SchemaMapper() {
    }

    public static SchemaMapper getInstance() {
        return instance;
    }

    @Override
    public IType map(Schema value) {
        if (value == null) {
            return null;
        }

        if (value instanceof PrimitiveSchema) {
            return Mappers.getPrimitiveMapper().map((PrimitiveSchema) value);
        } else if (value instanceof ChoiceSchema) {
            return Mappers.getChoiceMapper().map((ChoiceSchema) value);
        } else if (value instanceof SealedChoiceSchema) {
            return Mappers.getSealedChoiceMapper().map((SealedChoiceSchema) value);
        } else if (value instanceof ArraySchema) {
            return Mappers.getArrayMapper().map((ArraySchema) value);
        } else if (value instanceof DictionarySchema) {
            return Mappers.getDictionaryMapper().map((DictionarySchema) value);
        } else if (value instanceof ObjectSchema) {
            return Mappers.getObjectMapper().map((ObjectSchema) value);
        } else if (value instanceof ConstantSchema) {
            return Mappers.getConstantMapper().map((ConstantSchema) value);
        } else if(value instanceof AnySchema) {
            return Mappers.getAnyMapper().map((AnySchema) value);
        } else if(value instanceof BinarySchema) {
            return Mappers.getBinaryMapper().map((BinarySchema) value);
        } else {
            throw new UnsupportedOperationException("Cannot find a mapper for schema type " + value.getClass() + ". Key: " + value.get$key());
        }
    }
}
