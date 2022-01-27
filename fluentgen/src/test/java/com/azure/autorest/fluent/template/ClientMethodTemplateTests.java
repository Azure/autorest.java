// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.template;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.FluentGenAccessor;
import com.azure.autorest.fluent.TestUtils;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.template.ClientMethodTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ClientMethodTemplateTests {

    private static FluentGenAccessor fluentgenAccessor;

    @BeforeAll
    public static void ensurePlugin() {
        FluentGen fluentgen = new TestUtils.MockFluentGen();
        fluentgenAccessor = new FluentGenAccessor(fluentgen);
    }

    private static class VerificationCriterion {
        private final String methodName;
        private final String includeString;

        public static final String IGNORE = null;

        public VerificationCriterion(String methodName, String includeString) {
            this.methodName = methodName;
            this.includeString = includeString;
        }
    }

    @Test
    public void testClientMethodTemplate() {
        CodeModel codeModel = TestUtils.loadCodeModel(fluentgenAccessor, "code-model-fluentnamer-locks.yaml");
        Client client = FluentStatic.getClient();

        ClientMethodTemplate template = FluentClientMethodTemplate.getInstance();

        MethodGroupClient lockMethodGroupClient = client.getServiceClient().getMethodGroupClients().stream()
                .filter(c -> c.getClassBaseName().startsWith("ManagementLocks"))
                .findFirst().get();

        List<VerificationCriterion> criteria = Arrays.asList(
                new VerificationCriterion("listByScopeAsync", "private PagedFlux<ManagementLockObjectInner> listByScopeAsync(String scope"),
                new VerificationCriterion("listByScope", "public PagedIterable<ManagementLockObjectInner> listByScope(String scope"),
                new VerificationCriterion("listByScopeSinglePageAsync", VerificationCriterion.IGNORE),
                new VerificationCriterion("listByScopeNextSinglePageAsync", VerificationCriterion.IGNORE),
                new VerificationCriterion("createOrUpdateByScopeWithResponseAsync", "private Mono<Response<ManagementLockObjectInner>> createOrUpdateByScopeWithResponseAsync(String scope"),
                new VerificationCriterion("createOrUpdateByScopeWithResponse", "public Response<ManagementLockObjectInner> createOrUpdateByScopeWithResponse(String scope"),
                new VerificationCriterion("createOrUpdateByScopeAsync", "private Mono<ManagementLockObjectInner> createOrUpdateByScopeAsync(String scope"),
                new VerificationCriterion("createOrUpdateByScope", "public ManagementLockObjectInner createOrUpdateByScope(String scope")
        );

        for (VerificationCriterion criterion : criteria) {
            Optional<ClientMethod> clientMethod = lockMethodGroupClient.getClientMethods().stream()
                    .filter(m -> m.getName().equals(criterion.methodName))
                    .findFirst();
            Assertions.assertTrue(clientMethod.isPresent());
            JavaFile javaFile = new JavaFile("dummy");
            template.write(clientMethod.get(), new JavaClass(javaFile.getContents()));
            String content = javaFile.getContents().toString();
            if (criterion.includeString != VerificationCriterion.IGNORE) {
                Assertions.assertTrue(content.contains(criterion.includeString),
                        String.format("Expected content not found: '%s'\nMethod content:\n%s", criterion.includeString, content));
            }
        }
    }
}
