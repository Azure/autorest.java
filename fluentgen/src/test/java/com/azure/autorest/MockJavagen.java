/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.Message;
import com.azure.autorest.extension.base.plugin.JavaSettingsAccessor;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.util.FluentJavaSettings;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MockJavagen extends Javagen {

    private static final Map<String, Object> DEFAULT_SETTINGS = new HashMap<>();
    static {
        DEFAULT_SETTINGS.put("namespace", "com.azure.resourcemanager.mock");
        DEFAULT_SETTINGS.put("fluent", "lite");
        DEFAULT_SETTINGS.put("sync-methods", "all");
        DEFAULT_SETTINGS.put("add-context-parameter", true);
        DEFAULT_SETTINGS.put("context-client-method-parameter", true);
        DEFAULT_SETTINGS.put("client-side-validations", true);
        DEFAULT_SETTINGS.put("client-logger", true);
        DEFAULT_SETTINGS.put("generate-client-interfaces", true);
        DEFAULT_SETTINGS.put("required-parameter-client-methods", true);
    }

    public MockJavagen() {
        super(new Connection(System.out, System.in), "dummy", "dummy");
        instance = this;

        JavaSettingsAccessor.setHost(this);

        FluentStatic.setFluentJavaSettings(new FluentJavaSettings(this));
    }

    @Override
    public <T> T getValue(Type type, String key) {
        return (T) DEFAULT_SETTINGS.get(key);
    }

    @Override
    public void message(Message message) {
//            System.out.println(String.format("[%1$s] %2$s", message.getChannel(), message.getText()));
    }
}

