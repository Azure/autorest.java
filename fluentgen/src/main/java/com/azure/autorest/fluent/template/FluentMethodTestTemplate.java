// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentMethodMockUnitTest;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.examplemodel.ExampleNode;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.template.IJavaTemplate;
import com.azure.autorest.template.example.ModelExampleWriter;
import com.azure.autorest.util.CodeNamer;
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FluentMethodTestTemplate implements IJavaTemplate<FluentMethodTestTemplate.ClientMethodInfo, JavaFile> {

    public static class ClientMethodInfo {
        private final String className;

        private final FluentMethodMockUnitTest fluentMethodMockUnitTest;

        public ClientMethodInfo(String className, FluentMethodMockUnitTest fluentMethodMockUnitTest) {
            this.className = className;
            this.fluentMethodMockUnitTest = fluentMethodMockUnitTest;
        }
    }

    private static final FluentMethodTestTemplate INSTANCE = new FluentMethodTestTemplate();

    private static final SerializerAdapter SERIALIZER = SerializerFactory.createDefaultManagementSerializerAdapter();

    private FluentMethodTestTemplate() {
    }

    public static FluentMethodTestTemplate getInstance() {
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

        String className = info.className;
        FluentMethodMockUnitTest fluentMethodMockUnitTest = info.fluentMethodMockUnitTest;
        ClientMethod clientMethod = fluentMethodMockUnitTest.getCollectionMethod().getInnerClientMethod();
        final boolean hasReturnValue = clientMethod.getReturnValue().getType() != PrimitiveType.Void;
        IType fluentReturnType = fluentMethodMockUnitTest.getCollectionMethod().getFluentReturnType();

        // method invocation
        String clientMethodInvocationWithResponse;
        FluentExampleTemplate.ExampleMethod exampleMethod;
        if (fluentMethodMockUnitTest.getFluentResourceCreateExample() != null) {
            exampleMethod = FluentExampleTemplate.getInstance().generateExampleMethod(fluentMethodMockUnitTest.getFluentResourceCreateExample());
        } else if (fluentMethodMockUnitTest.getFluentMethodExample() != null) {
            exampleMethod = FluentExampleTemplate.getInstance().generateExampleMethod(fluentMethodMockUnitTest.getFluentMethodExample());
        } else {
            throw new IllegalStateException();
        }
        String clientMethodInvocation = exampleMethod.getMethodContent();
        if (hasReturnValue) {
            clientMethodInvocationWithResponse = fluentReturnType.toString() + " response = " + clientMethodInvocation;
        } else {
            clientMethodInvocationWithResponse = clientMethodInvocation;
        }
        imports.addAll(exampleMethod.getImports());
        exampleMethod.getExample().getEntryType().addImportsTo(imports, false);
        fluentReturnType.addImportsTo(imports, false);

        // create response body with mocked data
        int statusCode = fluentMethodMockUnitTest.getResponse().getStatusCode();
        Object jsonObject = fluentMethodMockUnitTest.getResponse().getBody();
        ExampleNode verificationNode = fluentMethodMockUnitTest.getResponseVerificationNode();
        String verificationObjectName = fluentMethodMockUnitTest.getResponseVerificationVariableName();
        String jsonStr;
        try {
            jsonStr = SERIALIZER.serialize(jsonObject, SerializerEncoding.JSON);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to serialize Object to JSON string", e);
        }

        // prepare assertions
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
                // prepare mock class
                methodBlock.line("HttpClient httpClient = Mockito.mock(HttpClient.class);");
                methodBlock.line("HttpResponse httpResponse = Mockito.mock(HttpResponse.class);");
                methodBlock.line("ArgumentCaptor<HttpRequest> httpRequest = ArgumentCaptor.forClass(HttpRequest.class);");
                methodBlock.line();
                // response
                methodBlock.line("String responseStr = " + ClassType.String.defaultValueExpression(jsonStr) + ";");
                methodBlock.line();
                // mock class
                methodBlock.line("Mockito.when(httpResponse.getStatusCode()).thenReturn(" + statusCode + ");");
                methodBlock.line("Mockito.when(httpResponse.getHeaders()).thenReturn(new HttpHeaders());");
                methodBlock.line("Mockito.when(httpResponse.getBody()).thenReturn(Flux.just(ByteBuffer.wrap(responseStr.getBytes(StandardCharsets.UTF_8))));");
                methodBlock.line("Mockito.when(httpResponse.getBodyAsByteArray()).thenReturn(Mono.just(responseStr.getBytes(StandardCharsets.UTF_8)));");
                methodBlock.line("Mockito.when(httpClient.send(httpRequest.capture(), Mockito.any())).thenReturn(Mono.defer(() -> {");
                methodBlock.line("    Mockito.when(httpResponse.getRequest()).thenReturn(httpRequest.getValue());");
                methodBlock.line("    return Mono.just(httpResponse);");
                methodBlock.line("}));");
                methodBlock.line();
                // initialize manager
                methodBlock.line(String.format("%1$s manager = %1$s.configure().withHttpClient(httpClient).authenticate(tokenRequestContext -> Mono.just(new AccessToken(\"this_is_a_token\", OffsetDateTime.MAX)), new AzureProfile(\"\", \"\", AzureEnvironment.AZURE));", exampleMethod.getExample().getEntryType().getName()));
                methodBlock.line();
                // method invocation
                methodBlock.line(clientMethodInvocationWithResponse);
                methodBlock.line();
                // verification
                if (hasReturnValue) {
                    assertionVisitor.getAssertions().forEach(methodBlock::line);
                }
            });

            // helper method
            if (!exampleMethod.getHelperFeatures().isEmpty()) {
                ModelExampleWriter.writeMapOfMethod(classBlock);
            }
        });
    }
}
