/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.Message;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MockJavagen extends Javagen {

    private static final Map<String, Object> DEFAULT_SETTINGS = new HashMap<>();

    public MockJavagen() {
        super(new Connection(System.out, System.in), "dummy", "dummy");
        instance = this;
    }

    @Override
    public <T> T getValue(Type type, String key) {
        return (T) DEFAULT_SETTINGS.get(key);
    }

    @Override
    public void message(Message message) {
    }
}

