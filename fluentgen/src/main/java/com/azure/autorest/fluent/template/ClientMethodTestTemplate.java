// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.template;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.mapper.ExampleParser;
import com.azure.autorest.fluent.model.clientmodel.FluentManager;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.model.clientmodel.MethodParameter;
import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentMethodExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.ParameterExample;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.ProxyMethodExample;
import com.azure.autorest.model.clientmodel.examplemodel.ExampleNode;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.template.IJavaTemplate;
import com.azure.autorest.template.example.ModelExampleWriter;
import com.azure.autorest.util.CodeNamer;
import com.azure.autorest.util.ModelExampleUtil;
import com.azure.autorest.util.ModelTestCaseUtil;
import com.azure.core.credential.AccessToken;
import com.azure.core.http.HttpResponse;
import com.azure.core.management.profile.AzureProfile;
import com.azure.core.management.serializer.SerializerFactory;
import com.azure.core.util.serializer.SerializerAdapter;
import com.azure.core.util.serializer.SerializerEncoding;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientMethodTestTemplate implements IJavaTemplate<ClientMethodTestTemplate.ClientMethodInfo, JavaFile> {

    public static class ClientMethodInfo {
        private final String className;

        private final MethodGroupClient methodGroup;
        private final ClientMethod clientMethod;

        public ClientMethodInfo(String className, MethodGroupClient methodGroup, ClientMethod clientMethod) {
            this.className = className;
            this.methodGroup = methodGroup;
            this.clientMethod = clientMethod;
        }
    }

    private static final ClientMethodTestTemplate INSTANCE = new ClientMethodTestTemplate();

    private static final SerializerAdapter SERIALIZER = SerializerFactory.createDefaultManagementSerializerAdapter();

    private ClientMethodTestTemplate() {
    }

    public static ClientMethodTestTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    public void write(ClientMethodInfo info, JavaFile javaFile) {
        Set<String> imports = new HashSet<>(Arrays.asList(
                AccessToken.class.getName(),
                ClassType.HttpClient.getFullName(),
                ClassType.HttpHeaders.getFullName(),
                ClassType.HttpRequest.getFullName(),
                HttpResponse.class.getName(),
                ClassType.AzureEnvironment.getFullName(),
                AzureProfile.class.getName(),
                "org.junit.jupiter.api.Test",
                "org.mockito.ArgumentCaptor",
                "org.mockito.Mockito",
                ByteBuffer.class.getName(),
                Mono.class.getName(),
                Flux.class.getName(),
                StandardCharsets.class.getName(),
                OffsetDateTime.class.getName()
        ));

        // create method with mocked data on parameters
        String className = info.className;
        MethodGroupClient methodGroup = info.methodGroup;
        ClientMethod clientMethod = info.clientMethod;
        FluentMethodExample methodExample = TestExampleParser.parseMethod(FluentStatic.getFluentManager(), methodGroup, clientMethod);

        final boolean hasReturnValue = clientMethod.getReturnValue().getType() != PrimitiveType.Void;

        // method invocation
        String managerName = methodExample.getEntryName();
        ExampleNodeVisitor visitor = new ExampleNodeVisitor();
        String parameterInvocations = methodExample.getParameters().stream()
                .map(p -> visitor.accept(p.getExampleNode()))
                .collect(Collectors.joining(", "));
        String clientMethodInvocation = String.format("%1$s.%2$s.%3$s(%4$s);",
                managerName,
                methodExample.getMethodReference(),
                methodExample.getMethodName(),
                parameterInvocations);
        String clientMethodInvocationWithResponse;
        if (hasReturnValue) {
            clientMethodInvocationWithResponse = clientMethod.getReturnValue().getType().toString() + " response = " + clientMethodInvocation;
        } else {
            clientMethodInvocationWithResponse = clientMethodInvocation;
        }
        clientMethod.addImportsTo(imports, false, JavaSettings.getInstance());
        methodExample.getEntryType().addImportsTo(imports, false);
        imports.addAll(visitor.getImports());

        // create response body with mocked data
        int statusCode = clientMethod.getProxyMethod().getResponseExpectedStatusCodes().iterator().next();
        Object jsonObject;
        ExampleNode verificationNode;
        String verificationObjectName;
        if (clientMethod.getType() == ClientMethodType.PagingSync) {
            // pageable
            if (clientMethod.getReturnValue().getType() instanceof GenericType) {
                IType elementType = ((GenericType) clientMethod.getReturnValue().getType()).getTypeArguments()[0];

                Object firstJsonObjectInPageable = ModelTestCaseUtil.jsonFromType(0, elementType);

                Map<String, Object> jsonMap = new HashMap<>();
                jsonMap.put(clientMethod.getMethodPageDetails().getRawItemName(), Collections.singletonList(firstJsonObjectInPageable));

                jsonObject = jsonMap;

                verificationObjectName = "response.iterator().next()";
                verificationNode = ModelExampleUtil.parseNode(elementType, firstJsonObjectInPageable);
            } else {
                throw new IllegalStateException("Response of pageable operation must be PagedIterable<>");
            }
        } else {
            // simple or LRO
            jsonObject = ModelTestCaseUtil.jsonFromType(0, clientMethod.getReturnValue().getType());

            if (jsonObject == null) {
                jsonObject = new Object();
            }
            if (clientMethod.getType() == ClientMethodType.LongRunningSync) {
                // LRO, hack to set properties.provisioningState == Succeeded, so that LRO can stop at activation operation
                setProvisioningState(jsonObject);
            }

            verificationObjectName = "response";
            verificationNode = ModelExampleUtil.parseNode(clientMethod.getReturnValue().getType(), jsonObject);
        }
        String jsonStr;
        try {
            jsonStr = SERIALIZER.serialize(jsonObject, SerializerEncoding.JSON);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to serialize Object to JSON string", e);
        }

        ModelExampleWriter.ExampleNodeAssertionVisitor assertionVisitor = new ModelExampleWriter.ExampleNodeAssertionVisitor();
        if (hasReturnValue) {
            imports.add("org.junit.jupiter.api.Assertions");

            assertionVisitor.accept(verificationNode, verificationObjectName);
            imports.addAll(assertionVisitor.getImports());
        }

        javaFile.declareImport(imports);

        javaFile.publicFinalClass(className, classBlock -> {
            classBlock.annotation("Test");
            classBlock.publicMethod(String.format("void test%1$s() throws Exception", CodeNamer.toPascalCase(clientMethod.getName())), methodBlock -> {
                methodBlock.line("HttpClient httpClient = Mockito.mock(HttpClient.class);");
                methodBlock.line("HttpResponse httpResponse = Mockito.mock(HttpResponse.class);");
                methodBlock.line("ArgumentCaptor<HttpRequest> httpRequest = ArgumentCaptor.forClass(HttpRequest.class);");
                methodBlock.line();
                methodBlock.line("String responseStr = " + ClassType.String.defaultValueExpression(jsonStr) + ";");
                methodBlock.line();
                methodBlock.line("Mockito.when(httpResponse.getStatusCode()).thenReturn(" + statusCode + ");");
                methodBlock.line("Mockito.when(httpResponse.getHeaders()).thenReturn(new HttpHeaders());");
                methodBlock.line("Mockito.when(httpResponse.getBody()).thenReturn(Flux.just(ByteBuffer.wrap(responseStr.getBytes(StandardCharsets.UTF_8))));");
                methodBlock.line("Mockito.when(httpResponse.getBodyAsByteArray()).thenReturn(Mono.just(responseStr.getBytes(StandardCharsets.UTF_8)));");
                methodBlock.line("Mockito.when(httpClient.send(httpRequest.capture(), Mockito.any())).thenReturn(Mono.defer(() -> {");
                methodBlock.line("    Mockito.when(httpResponse.getRequest()).thenReturn(httpRequest.getValue());");
                methodBlock.line("    return Mono.just(httpResponse);");
                methodBlock.line("}));");
                methodBlock.line();
                methodBlock.line(String.format("%1$s manager = %1$s.configure().withHttpClient(httpClient).authenticate(tokenRequestContext -> Mono.just(new AccessToken(\"this_is_a_token\", OffsetDateTime.MAX)), new AzureProfile(\"\", \"\", AzureEnvironment.AZURE));", methodExample.getEntryType().getName()));
                methodBlock.line();
                methodBlock.line(clientMethodInvocationWithResponse);
                methodBlock.line();
                if (hasReturnValue) {
                    assertionVisitor.getAssertions().forEach(methodBlock::line);
                }
            });

            if (!visitor.getHelperFeatures().isEmpty()) {
                ModelExampleWriter.writeMapOfMethod(classBlock);
            }
        });
    }

    @SuppressWarnings("unchecked")
    private static void setProvisioningState(Object jsonObject) {
        if ((jsonObject instanceof Map) && ((Map<String, Object>) jsonObject).containsKey("properties")) {
            Object propertiesObject = ((Map<String, Object>) jsonObject).get("properties");
            if ((propertiesObject instanceof Map) && ((Map<String, Object>) propertiesObject).containsKey("provisioningState")) {
                Object provisioningStateObject = ((Map<String, Object>) propertiesObject).get("provisioningState");
                if (provisioningStateObject instanceof String) {
                    ((Map<String, Object>) propertiesObject).put("provisioningState", "Succeeded");
                }
            }
        }
    }

    private static class ClientMethodExample implements FluentMethodExample {

        private final String name;
        private final String originalFileName;
        private final FluentManager manager;
        private final MethodGroupClient methodGroup;
        private final ClientMethod clientMethod;
        private final List<ParameterExample> parameters = new ArrayList<>();

        public ClientMethodExample(String name, String originalFileName, FluentManager manager,
                                   MethodGroupClient methodGroup, ClientMethod clientMethod) {
            this.name = name;
            this.originalFileName = originalFileName;
            this.manager = manager;
            this.methodGroup = methodGroup;
            this.clientMethod = clientMethod;
        }

        @Override
        public String getMethodReference() {
            String methodGroupReference =  "get" + CodeNamer.toPascalCase(methodGroup.getVariableName()) + "()";
            return ModelNaming.METHOD_SERVICE_CLIENT + "()." + methodGroupReference;
        }

        @Override
        public String getMethodName() {
            return clientMethod.getName();
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getOriginalFileName() {
            return originalFileName;
        }

        @Override
        public ClassType getEntryType() {
            return manager.getType();
        }

        @Override
        public String getEntryName() {
            return "manager";
        }

        @Override
        public String getEntryDescription() {
            return manager.getDescription();
        }

        @Override
        public List<ParameterExample> getParameters() {
            return parameters;
        }
    }

    private static class TestExampleParser extends ExampleParser {
        public static FluentMethodExample parseMethod(FluentManager manager, MethodGroupClient methodGroup, ClientMethod clientMethod) {
            List<MethodParameter> methodParameters = getParameters(clientMethod);
            ProxyMethodExample proxyMethodExample = createProxyMethodExample(clientMethod, methodParameters);

            ClientMethodExample clientMethodExample = new ClientMethodExample(
                    clientMethod.getName(), null, manager, methodGroup, clientMethod);
            addMethodParametersToMethodExample(methodParameters, proxyMethodExample, clientMethodExample);
            return clientMethodExample;
        }

        private static ProxyMethodExample createProxyMethodExample(ClientMethod clientMethod, List<MethodParameter> methodParameters) {
            ProxyMethodExample.Builder example = new ProxyMethodExample.Builder()
                    .name(clientMethod.getName());

            for (MethodParameter methodParameter : methodParameters) {
                String serializedName = methodParameter.getSerializedName();
                if (serializedName == null && methodParameter.getProxyMethodParameter().getRequestParameterLocation() == RequestParameterLocation.BODY) {
                    serializedName = methodParameter.getProxyMethodParameter().getName();
                }

                Object jsonParam = ModelTestCaseUtil.jsonFromType(0, methodParameter.getProxyMethodParameter().getWireType());

                example.parameter(serializedName, jsonParam);
            }

//            int statusCode = clientMethod.getProxyMethod().getResponseExpectedStatusCodes().iterator().next();
//            Object jsonResponse = ModelTestCaseUtil.jsonFromType(0, clientMethod.getReturnValue().getType());
//            example.response(statusCode, new ProxyMethodExample.Response(statusCode, jsonResponse));

            return example.build();
        }
    }

    private static class ExampleNodeVisitor extends ModelExampleWriter.ExampleNodeModelInitializationVisitor {

        @Override
        protected String codeDeserializeJsonString(String jsonStr) {
            imports.add(com.azure.core.management.serializer.SerializerFactory.class.getName());
            imports.add(com.azure.core.util.serializer.SerializerEncoding.class.getName());
            imports.add(java.io.IOException.class.getName());

            return String.format("SerializerFactory.createDefaultManagementSerializerAdapter().deserialize(%s, Object.class, SerializerEncoding.JSON)",
                    ClassType.String.defaultValueExpression(jsonStr));
        }
    }
}
