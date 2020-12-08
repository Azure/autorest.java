/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.Message;
import com.azure.autorest.extension.base.plugin.JavaSettingsAccessor;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.util.FluentJavaSettings;

import java.lang.reflect.Type;

public class TestUtils {

    public static class MockFluentGen extends FluentGen {

        public MockFluentGen() {
            super(new Connection(System.out, System.in), "dummy", "dummy");
            instance = this;

            JavaSettingsAccessor.setHost(this);

            FluentStatic.setFluentJavaSettings(new FluentJavaSettings(this));
        }

        @Override
        public <T> T getValue(Type type, String key) {
            if ("namespace".equals(key)) {
                return (T) "com.azure.resourcemanager.mock";
            } else if ("fluent".equals(key)) {
                return (T) "lite";
            }

            return null;
        }

        @Override
        public void message(Message message) {
//            System.out.println(String.format("[%1$s] %2$s", message.getChannel(), message.getText()));
        }
    }
}
