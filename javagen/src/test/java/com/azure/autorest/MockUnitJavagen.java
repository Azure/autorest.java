// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import org.junit.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public class MockUnitJavagen extends Javagen{

    public MockUnitJavagen() {
        super(new Connection(System.out, System.in), "dummy", "dummy");
        JavaSettingsAccessor.setHost(this);
    }

    @Override
    public String readFile(String fileName) {
        InputStream fis = this.getClass().getClassLoader().getResourceAsStream(fileName);
        StringBuilder sb = new StringBuilder();
        char[] buffer = new char[1024];
        try (InputStreamReader reader = new InputStreamReader(fis, StandardCharsets.UTF_8)) {
            int charsRead;
            while ((charsRead = reader.read(buffer, 0, buffer.length)) > 0) {
                sb.append(buffer, 0, charsRead);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    @Override
    public <T> T getValue(Type type, String key) {
        return null;
    }
}
