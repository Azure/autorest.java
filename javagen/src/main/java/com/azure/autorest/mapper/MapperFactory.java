package com.azure.autorest.mapper;

public interface MapperFactory {

    ChoiceMapper getChoiceMapper();

    SealedChoiceMapper getSealedChoiceMapper();

    PrimitiveMapper getPrimitiveMapper();

    SchemaMapper getSchemaMapper();

    ArrayMapper getArrayMapper();

    DictionaryMapper getDictionaryMapper();

    ObjectMapper getObjectMapper();

    ConstantMapper getConstantMapper();

    ModelPropertyMapper getModelPropertyMapper();

    ModelMapper getModelMapper();

    ProxyParameterMapper getProxyParameterMapper();

    ProxyMethodMapper getProxyMethodMapper();

    MethodGroupMapper getMethodGroupMapper();

    ClientParameterMapper getClientParameterMapper();

    ClientMethodMapper getClientMethodMapper();

    ExceptionMapper getExceptionMapper();

    ServiceClientMapper getServiceClientMapper();

    ClientMapper getClientMapper();

    AnyMapper getAnyMapper();

    BinaryMapper getBinaryMapper();
}
