// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.clientmodel.ServiceClientProperty;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaIfBlock;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.core.credential.AccessToken;
import com.azure.core.http.HttpClient;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.util.Configuration;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class ProtocolTestBlankTemplate implements IJavaTemplate<ServiceClient, JavaFile> {

    @Override
    public void write(ServiceClient serviceClient, JavaFile context) {

        final String serviceClientBuilderName = serviceClient.getInterfaceName() + ClientModelUtil.getBuilderSuffix();
        final boolean isTokenCredential = JavaSettings.getInstance().getCredentialTypes()
                .contains(JavaSettings.CredentialType.TOKEN_CREDENTIAL);

        Set<String> imports = new HashSet<>(Arrays.asList(
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

        context.declareImport(imports);

        context.publicFinalClass("ClientTests extends TestBase", classBlock -> {
            classBlock.privateMemberVariable(String.format("%s builder", serviceClientBuilderName));

            classBlock.annotation("BeforeEach");
            classBlock.publicMethod("void setup()", methodBlock -> {
                methodBlock.line(String.format("builder = new %s()", serviceClientBuilderName));
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
                        ifBlock.line("builder.httpClient(interceptorManager.getPlaybackClient())");
                        ifBlock.line(".credential(request -> Mono.just(new AccessToken(\"this_is_a_token\", OffsetDateTime.MAX)));");
                    } else {
                        ifBlock.line("builder.httpClient(interceptorManager.getPlaybackClient());");
                    }
                }).elseIfBlock("getTestMode() == TestMode.RECORD", ifBlock -> {
                    if (isTokenCredential) {
                        ifBlock.line("builder.addPolicy(interceptorManager.getRecordPolicy())");
                        ifBlock.line(".credential(new DefaultAzureCredentialBuilder().build());");
                    } else {
                        ifBlock.line("builder.addPolicy(interceptorManager.getRecordPolicy());");
                    }
                });

                if (isTokenCredential) {
                    codeBlock.elseIfBlock("getTestMode() == TestMode.LIVE", ifBlock -> {
                        ifBlock.line("builder.credential(new DefaultAzureCredentialBuilder().build());");
                    });
                }
            });

            classBlock.annotation("Test", "DoNotRecord(skipInPlayback = true)");
            classBlock.publicMethod("void testClient()", methodBlock -> {
                methodBlock.line("// use the builder to create client");
            });
        });
    }
}
