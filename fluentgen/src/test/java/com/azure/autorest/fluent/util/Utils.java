/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.util;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.fluent.FluentGen;

import java.lang.reflect.Type;

public class Utils {

    public static class MockFluentGen extends FluentGen {

        public MockFluentGen() {
            super(new Connection(System.out, System.in), "dummy", "dummy");
            instance = this;
        }

        @Override
        public <T> T getValue(Type type, String key) {
            return null;
        }
    }
}
