/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent;

import com.azure.autorest.Javagen;
import com.azure.autorest.MockJavagen;
import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.Message;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.JavaSettingsAccessor;
import com.azure.autorest.fluent.mapper.FluentMapperAccessor;
import com.azure.autorest.fluent.mapper.ResourceParserTests;
import com.azure.autorest.fluent.model.clientmodel.FluentClient;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.util.FluentJavaSettings;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.template.prototype.MethodTemplate;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
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
            DEFAULT_SETTINGS.put("generate-samples", true);
            DEFAULT_SETTINGS.put("model-override-setter-from-superclass", true);
            //DEFAULT_SETTINGS.put("client-flattened-annotation-target", "NONE");
        }

        private Javagen javagen;

        public MockFluentGen() {
            super(new Connection(System.out, System.in), "dummy", "dummy");
            instance = this;

            JavaSettingsAccessor.setHost(this);

            FluentStatic.setFluentJavaSettings(new FluentJavaSettings(this));

            javagen = new MockJavagen();
        }

        @Override
        public <T> T getValue(Type type, String key) {
            return (T) DEFAULT_SETTINGS.get(key);
        }

        public void setValue(String key, Object value) {
            DEFAULT_SETTINGS.put(key, value);
        }

        @Override
        public void message(Message message) {
//            System.out.println(String.format("[%1$s] %2$s", message.getChannel(), message.getText()));
        }
    }

    // code-model-fluentnamer-locks.yaml is produced by fluentnamer on https://github.com/Azure/azure-rest-api-specs/blob/master/specification/resources/resource-manager/Microsoft.Authorization/stable/2016-09-01/locks.json
    public static CodeModel loadCodeModel(FluentGenAccessor fluentgenAccessor, String filename) {
        String searchYamlContent = loadYaml(filename);

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

    public static class ContentLocks {
        private final CodeModel codeModel;
        private final Client client;
        private final FluentClient fluentClient;

        private final List<FluentResourceModel> fluentModels;

        private final FluentResourceModel lockModel;
        private final FluentResourceCollection lockCollection;

        public ContentLocks(CodeModel codeModel, Client client, FluentClient fluentClient,
                            List<FluentResourceModel> fluentModels,
                            FluentResourceModel lockModel, FluentResourceCollection lockCollection) {
            this.codeModel = codeModel;
            this.client = client;
            this.fluentClient = fluentClient;
            this.fluentModels = fluentModels;
            this.lockModel = lockModel;
            this.lockCollection = lockCollection;
        }

        public CodeModel getCodeModel() {
            return codeModel;
        }

        public Client getClient() {
            return client;
        }

        public FluentClient getFluentClient() {
            return fluentClient;
        }

        public List<FluentResourceModel> getFluentModels() {
            return fluentModels;
        }

        public FluentResourceModel getLockModel() {
            return lockModel;
        }

        public FluentResourceCollection getLockCollection() {
            return lockCollection;
        }
    }

    public static ContentLocks initContentLocks(FluentGenAccessor fluentgenAccessor) {
        CodeModel codeModel = loadCodeModel(fluentgenAccessor, "code-model-fluentnamer-locks.yaml");
        Client client = FluentStatic.getClient();
        FluentClient fluentClient = new FluentMapperAccessor(fluentgenAccessor.getFluentMapper()).basicMap(codeModel, client);

        List<FluentResourceModel> fluentModels = fluentClient.getResourceModels();
        List<FluentResourceCollection> fluentCollections = fluentClient.getResourceCollections();

        FluentResourceModel lockModel = fluentModels.stream()
                .filter(m -> m.getName().equals("ManagementLockObject"))
                .findFirst().get();

        FluentResourceCollection lockCollection = fluentCollections.stream()
                .filter(c -> c.getInnerGroupClient().getClassBaseName().startsWith("ManagementLocks"))
                .findFirst().get();

        return new ContentLocks(codeModel, client, fluentClient, fluentModels, lockModel, lockCollection);
    }

    public static String getMethodTemplateContent(MethodTemplate methodTemplate) {
        JavaFile javaFile = new JavaFile("dummy");
        methodTemplate.writeMethod(new JavaClass(javaFile.getContents()));
        return javaFile.getContents().toString();
    }
}
