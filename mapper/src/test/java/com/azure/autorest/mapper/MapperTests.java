package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import org.junit.Test;

public class MapperTests {
    @Test
    public void canParseCodeModel() {
        Mapper mapper = new MockMapper(new Connection(System.out, System.in), "mapper", "session_1");
        mapper.processInternal();
    }
}
