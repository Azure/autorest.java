// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.mapper;

import com.azure.autorest.Javagen;
import com.azure.autorest.extension.base.jsonrpc.Connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class MockJavagen extends Javagen {
    public MockJavagen(Connection connection, String plugin, String sessionId) {
        super(connection, plugin, sessionId);
    }

    @Override
    public List<String> listInputs() {
        return Arrays.asList("input.yaml");
    }

    @Override
    public String readFile(String fileName) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get("/Users/jianghlu/Downloads/example(no-tags).yaml"));
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
