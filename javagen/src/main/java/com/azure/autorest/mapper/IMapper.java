package com.azure.autorest.mapper;

public interface IMapper<FromT, ToT> {
    ToT map(FromT fromT);
}
