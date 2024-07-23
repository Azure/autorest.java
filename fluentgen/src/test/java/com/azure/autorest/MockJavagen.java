// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.Message;
import com.azure.json.JsonReader;
import com.azure.json.ReadValueCallback;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MockJavagen extends Javagen {

    private static final Map<String, Object> DEFAULT_SETTINGS = new HashMap<>();

    public MockJavagen(Connection connection) {
        super(connection, "dummy", "dummy");
        instance = this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getValue(String key, ReadValueCallback<String, T> converter) {
        return (T) DEFAULT_SETTINGS.get(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getValueWithJsonReader(String key, ReadValueCallback<JsonReader, T> converter) {
        return (T) DEFAULT_SETTINGS.get(key);
    }

    @Override
    public void message(Message message) {
    }
}

