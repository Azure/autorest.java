/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.model.javamodel.JavaPackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MockJavagen4Unit extends Javagen{

    private static final Map<String, Object> SETTINGS = new HashMap<>();

    public MockJavagen4Unit() {
        super(new Connection(System.out, System.in), "dummy", "dummy");
        JavaSettingsAccessor.setHost(this);
    }

    public JavaPackage convertFileToTemplates(String filename) {
        CodeModel codeModel = loadCodeModel(filename);
        Client client = Mappers.getClientMapper().map(codeModel);
        return writeToTemplates(JavaSettings.getInstance(), codeModel, client);
    }

    @Override
    public String readFile(String fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);
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
        return (T) SETTINGS.get(key);
    }
}
