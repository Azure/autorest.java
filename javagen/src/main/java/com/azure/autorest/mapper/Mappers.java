package com.azure.autorest.mapper;

public class Mappers {
    public static ChoiceMapper getChoiceMapper() {
        return ChoiceMapper.getInstance();
    }

    public static SealedChoiceMapper getSealedChoiceMapper() {
        return SealedChoiceMapper.getInstance();
    }

    public static PrimitiveMapper getPrimitiveMapper() {
        return PrimitiveMapper.getInstance();
    }

    public static SchemaMapper getSchemaMapper() {
        return SchemaMapper.getInstance();
    }

    public static ArrayMapper getArrayMapper() {
        return ArrayMapper.getInstance();
    }

    public static DictionaryMapper getDictionaryMapper() {
        return DictionaryMapper.getInstance();
    }

    public static ObjectMapper getObjectMapper() {
        return ObjectMapper.getInstance();
    }

    public static ConstantMapper getConstantMapper() {
        return ConstantMapper.getInstance();
    }

    public static ModelPropertyMapper getModelPropertyMapper() {
        return ModelPropertyMapper.getInstance();
    }

    public static ModelMapper getModelMapper() {
        return ModelMapper.getInstance();
    }

    public static ProxyParameterMapper getProxyParameterMapper() {
        return ProxyParameterMapper.getInstance();
    }

    public static ProxyMethodMapper getProxyMethodMapper() {
        return ProxyMethodMapper.getInstance();
    }

    public static MethodGroupMapper getMethodGroupMapper() {
        return MethodGroupMapper.getInstance();
    }
}
