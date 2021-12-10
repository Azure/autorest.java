/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest;

import com.azure.autorest.extension.base.jsonrpc.Connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

public class MockUnitJavagen extends Javagen{

    public MockUnitJavagen() {
        super(new Connection(System.out, System.in), "dummy", "dummy");
        JavaSettingsAccessor.setHost(this);
    }

    @Override
    public String readFile(String fileName) {
        try {
            InputStream fis = this.getClass().getClassLoader().getResourceAsStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T getValue(Type type, String key) {
        return null;
    }
}
