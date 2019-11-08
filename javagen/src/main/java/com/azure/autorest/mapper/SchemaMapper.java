package com.azure.autorest.mapper;

import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.codemodel.ArraySchema;
import com.azure.autorest.model.codemodel.ChoiceSchema;
import com.azure.autorest.model.codemodel.DictionarySchema;
import com.azure.autorest.model.codemodel.ObjectSchema;
import com.azure.autorest.model.codemodel.PrimitiveSchema;
import com.azure.autorest.model.codemodel.Schema;
import com.azure.autorest.model.codemodel.SealedChoiceSchema;

import java.util.HashMap;
import java.util.Map;

public class SchemaMapper implements IMapper<Schema, IType> {
    private SchemaMapper() {
    }

    private static SchemaMapper instance = new SchemaMapper();

    public static SchemaMapper getInstance() {
        return instance;
    }

    Map<Schema, IType> parsed = new HashMap<>();

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
        } else {
            throw new UnsupportedOperationException("Cannot find a mapper for schema type " + value.getClass() + ". Key: " + value.get$key());
        }
    }
}
