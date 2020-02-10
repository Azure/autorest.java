package com.azure.autorest.preprocessor.mapper;

public interface IMapper<FromT, ToT> {
    ToT map(FromT fromT);
}
