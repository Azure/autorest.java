// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentMethodMockUnitTest;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.examplemodel.ExampleHelperFeature;
import com.azure.autorest.model.clientmodel.examplemodel.ExampleNode;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.template.IJavaTemplate;
import com.azure.autorest.template.example.ModelExampleWriter;
import com.azure.autorest.util.CodeNamer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FluentMethodMockTestTemplate
    implements IJavaTemplate<FluentMethodMockTestTemplate.ClientMethodInfo, JavaFile> {

    public static class ClientMethodInfo {
        private final String className;

        private final FluentMethodMockUnitTest fluentMethodMockUnitTest;

        public ClientMethodInfo(String className, FluentMethodMockUnitTest fluentMethodMockUnitTest) {
            this.className = className;
            this.fluentMethodMockUnitTest = fluentMethodMockUnitTest;
        }
    }

    private static final FluentMethodMockTestTemplate INSTANCE = new FluentMethodMockTestTemplate();

    private static final ObjectMapper SERIALIZER = new ObjectMapper();

    private FluentMethodMockTestTemplate() {
    }

    public static FluentMethodMockTestTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    public void write(ClientMethodInfo info, JavaFile javaFile) {
        Set<String> imports = new HashSet<>(Arrays.asList("com.azure.core.credential.AccessToken",
            ClassType.HTTP_CLIENT.getFullName(), ClassType.HTTP_HEADERS.getFullName(),
            ClassType.HTTP_REQUEST.getFullName(), "com.azure.core.http.HttpResponse",
            "com.azure.core.test.http.MockHttpResponse", ClassType.AZURE_ENVIRONMENT.getFullName(),
            "com.azure.core.management.profile.AzureProfile", "org.junit.jupiter.api.Test", ByteBuffer.class.getName(),
            "reactor.core.publisher.Mono", "reactor.core.publisher.Flux", StandardCharsets.class.getName(),
            OffsetDateTime.class.getName()));

        String className = info.className;
        FluentMethodMockUnitTest fluentMethodMockUnitTest = info.fluentMethodMockUnitTest;
        ClientMethod clientMethod = fluentMethodMockUnitTest.getCollectionMethod().getInnerClientMethod();
        IType fluentReturnType = fluentMethodMockUnitTest.getFluentReturnType();
        final boolean isResponseType = FluentUtils.isResponseType(fluentReturnType);
        if (isResponseType) {
            fluentReturnType = FluentUtils.getValueTypeFromResponseType(fluentReturnType);
        }
        final boolean hasReturnValue = fluentReturnType.asNullable() != ClassType.VOID;

        // method invocation
        String clientMethodInvocationWithResponse;
        FluentExampleTemplate.ExampleMethod exampleMethod;
        if (fluentMethodMockUnitTest.getFluentResourceCreateExample() != null) {
            exampleMethod = FluentExampleTemplate.getInstance()
                .generateExampleMethod(fluentMethodMockUnitTest.getFluentResourceCreateExample());
        } else if (fluentMethodMockUnitTest.getFluentMethodExample() != null) {
            exampleMethod = FluentExampleTemplate.getInstance()
                .generateExampleMethod(fluentMethodMockUnitTest.getFluentMethodExample());
        } else {
            throw new IllegalStateException();
        }
        String clientMethodInvocation = exampleMethod.getMethodContent();
        if (hasReturnValue) {
            // hack on replaceResponseForValue, as in "update" case, "exampleMethod.getMethodContent()" would be a code
            // block, not a single line of code invocation.
            clientMethodInvocationWithResponse = fluentReturnType + " response = " + (isResponseType
                ? replaceResponseForValue(clientMethodInvocation)
                : clientMethodInvocation);
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
            jsonStr = SERIALIZER.writeValueAsString(jsonObject);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to serialize Object to JSON string", e);
        }

        // prepare assertions
        ModelExampleWriter.ExampleNodeAssertionVisitor assertionVisitor
            = new ModelExampleWriter.ExampleNodeAssertionVisitor();
        if (hasReturnValue) {
            imports.add("org.junit.jupiter.api.Assertions");

            assertionVisitor.accept(verificationNode, verificationObjectName);
            imports.addAll(assertionVisitor.getImports());
        }

        javaFile.declareImport(imports);

        javaFile.publicFinalClass(className, classBlock -> {
            classBlock.annotation("Test");
            classBlock.publicMethod(
                "void test" + CodeNamer.toPascalCase(clientMethod.getName()) + "() throws Exception",
                methodBlock -> {
                    // response
                    methodBlock.line("String responseStr = " + ClassType.STRING.defaultValueExpression(jsonStr) + ";");
                    methodBlock.line();

                    // prepare mock class
                    methodBlock.line(
                        "HttpClient httpClient = response -> Mono.just(new MockHttpResponse(response, " + statusCode
                            + ", responseStr.getBytes(StandardCharsets.UTF_8)));");

                    // initialize manager
                    String exampleMethodName = exampleMethod.getExample().getEntryType().getName();
                    methodBlock.line(exampleMethodName + " manager = " + exampleMethodName + ".configure()"
                        + ".withHttpClient(httpClient).authenticate(tokenRequestContext -> "
                        + "Mono.just(new AccessToken(\"this_is_a_token\", OffsetDateTime.MAX)), "
                        + "new AzureProfile(\"\", \"\", AzureEnvironment.AZURE));");
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
            if (exampleMethod.getHelperFeatures().contains(ExampleHelperFeature.MapOfMethod)) {
                ModelExampleWriter.writeMapOfMethod(classBlock);
            }
        });
    }

    private static String replaceResponseForValue(String clientMethodInvocation) {
        if (clientMethodInvocation.endsWith(";")) {
            clientMethodInvocation = clientMethodInvocation.substring(0, clientMethodInvocation.length() - 1);
            clientMethodInvocation += ".getValue();";
        }
        return clientMethodInvocation;
    }
}
