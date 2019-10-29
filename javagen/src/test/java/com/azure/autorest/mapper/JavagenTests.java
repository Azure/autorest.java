package com.azure.autorest.mapper;

import com.azure.autorest.Javagen;
import com.azure.autorest.extension.base.jsonrpc.Connection;
import org.junit.Test;

public class JavagenTests {
    @Test
    public void canParseCodeModel() {
        Javagen javagen = new MockJavagen(new Connection(System.out, System.in), "javagen", "session_1");
        javagen.processInternal();
    }
}
