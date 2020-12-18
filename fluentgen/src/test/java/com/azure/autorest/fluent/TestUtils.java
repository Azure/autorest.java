/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.Message;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.JavaSettingsAccessor;
import com.azure.autorest.fluent.mapper.ResourceParserTests;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.util.FluentJavaSettings;
import com.azure.autorest.model.clientmodel.Client;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class TestUtils {

    public static class MockFluentGen extends FluentGen {

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

        public MockFluentGen() {
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

    public static CodeModel loadCodeModel(FluentGenAccessor fluentgenAccessor, String filename) {
        String searchYamlContent = loadYaml(filename);   // the YAML is produced by fluentnamer on locks.json

        CodeModel codeModel = fluentgenAccessor.handleYaml(searchYamlContent);
        Client client = fluentgenAccessor.handleMap(codeModel);

        FluentStatic.setClient(client);

        return codeModel;
    }

    public static String loadYaml(String filename) {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        try (InputStream inputStream = ResourceParserTests.class.getClassLoader().getResourceAsStream(filename);
             Reader in = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            int charsRead;
            while ((charsRead = in.read(buffer, 0, buffer.length)) > 0) {
                out.append(buffer, 0, charsRead);
            }
            return out.toString();
        } catch (IOException e) {
            Assertions.fail(e);
            return null;
        }
    }
}
