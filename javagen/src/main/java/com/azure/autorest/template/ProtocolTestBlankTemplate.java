// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.extension.base.model.codemodel.Scheme;
import com.azure.autorest.model.clientmodel.AsyncSyncClient;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.clientmodel.ServiceClientProperty;
import com.azure.autorest.model.clientmodel.TestContext;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaIfBlock;
import com.azure.autorest.util.CodeNamer;
import com.azure.core.credential.AccessToken;
import com.azure.core.http.HttpClient;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.util.Configuration;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class ProtocolTestBlankTemplate implements IJavaTemplate<TestContext, JavaFile> {

    @Override
    public void write(TestContext testContext, JavaFile context) {

        final ServiceClient serviceClient = testContext.getServiceClient();
        final List<AsyncSyncClient> syncClients = testContext.getSyncClients();
        final boolean isTokenCredential = serviceClient.getSecurityInfo() != null && serviceClient.getSecurityInfo().getSecurityTypes() != null
                && serviceClient.getSecurityInfo().getSecurityTypes().contains(Scheme.SecuritySchemeType.AADTOKEN);

        final Set<String> imports = new HashSet<>(Arrays.asList(
                AccessToken.class.getName(),
                HttpClient.class.getName(),
                HttpLogDetailLevel.class.getName(),
                HttpLogOptions.class.getName(),
                Configuration.class.getName(),
                OffsetDateTime.class.getName(),
                Mono.class.getName(),
                "com.azure.identity.DefaultAzureCredentialBuilder",
                "com.azure.core.test.TestBase",
                "com.azure.core.test.TestMode",
                "com.azure.core.test.annotation.DoNotRecord",
                "org.junit.jupiter.api.BeforeEach",
                "org.junit.jupiter.api.Test"
        ));
        syncClients.forEach(c -> {
            c.addImportsTo(imports, false);
            c.getClientBuilder().addImportsTo(imports, false);
        });

        context.declareImport(imports);

        context.publicFinalClass("ClientTests extends TestBase", classBlock -> {

            syncClients.forEach(c -> {
                classBlock.privateMemberVariable(String.format("%1$s %2$s", c.getClassName(), CodeNamer.toCamelCase(c.getClassName())));
            });

            classBlock.annotation("BeforeEach");
            classBlock.publicMethod("void setup()", methodBlock -> {
                syncClients.forEach(c -> {
                    String clientVarName = CodeNamer.toCamelCase(c.getClassName());
                    String builderClassName = c.getClientBuilder().getClassName();
                    String builderVarName = CodeNamer.toCamelCase(c.getClassName()) + "builder";

                    methodBlock.line(String.format("%1$s %2$s = new %3$s()", builderClassName, builderVarName, builderClassName));
                    methodBlock.increaseIndent();
                    // required service client properties
                    serviceClient.getProperties().stream().filter(ServiceClientProperty::isRequired).forEach(serviceClientProperty -> {
                        String defaultValueExpression = serviceClientProperty.getDefaultValueExpression();
                        String expr;
                        if (defaultValueExpression == null) {
                            expr = String.format("Configuration.getGlobalConfiguration().get(\"%1$s\")",
                                    serviceClientProperty.getName().toUpperCase(Locale.ROOT));
                        } else {
                            expr = String.format("Configuration.getGlobalConfiguration().get(\"%1$s\", %2$s)",
                                    serviceClientProperty.getName().toUpperCase(Locale.ROOT), defaultValueExpression);
                        }
                        methodBlock.line(".%1$s(%2$s)", serviceClientProperty.getAccessorMethodSuffix(), expr);
                    });
                    methodBlock.line(".httpClient(HttpClient.createDefault())");
                    methodBlock.line(".httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS));");
                    methodBlock.decreaseIndent();

                    JavaIfBlock codeBlock = methodBlock.ifBlock("getTestMode() == TestMode.PLAYBACK", ifBlock -> {
                        if (isTokenCredential) {
                            ifBlock.line(String.format("%1$s.httpClient(interceptorManager.getPlaybackClient())", builderVarName));
                            ifBlock.line(".credential(request -> Mono.just(new AccessToken(\"this_is_a_token\", OffsetDateTime.MAX)));");
                        } else {
                            ifBlock.line(String.format("%1$s.httpClient(interceptorManager.getPlaybackClient());", builderVarName));
                        }
                    }).elseIfBlock("getTestMode() == TestMode.RECORD", ifBlock -> {
                        if (isTokenCredential) {
                            ifBlock.line(String.format("%1$s.addPolicy(interceptorManager.getRecordPolicy())", builderVarName));
                            ifBlock.line(".credential(new DefaultAzureCredentialBuilder().build());");
                        } else {
                            ifBlock.line(String.format("%1$s.addPolicy(interceptorManager.getRecordPolicy());", builderVarName));
                        }
                    });

                    if (isTokenCredential) {
                        codeBlock.elseIfBlock("getTestMode() == TestMode.LIVE", ifBlock -> {
                            ifBlock.line(String.format("%1$s.credential(new DefaultAzureCredentialBuilder().build());", builderVarName));
                        });
                    }

                    methodBlock.line(String.format("%1$s = %2$s.%3$s();", clientVarName, builderVarName, c.getClientBuilder().getBuilderMethodNameForSyncClient(c)));
                    methodBlock.line();
                });
            });

            classBlock.annotation("Test", "DoNotRecord(skipInPlayback = true)");
            classBlock.publicMethod("void testClient()", methodBlock -> {
                methodBlock.line("// use the builder to create client");
            });
        });
    }
}
