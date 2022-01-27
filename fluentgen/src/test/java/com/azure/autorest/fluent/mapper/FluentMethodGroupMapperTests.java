// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.OperationGroup;
import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.FluentGenAccessor;
import com.azure.autorest.fluent.TestUtils;
import com.azure.autorest.fluent.model.FluentType;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class FluentMethodGroupMapperTests {

    private static FluentGenAccessor fluentgenAccessor;

    @BeforeAll
    public static void ensurePlugin() {
        FluentGen fluentgen = new TestUtils.MockFluentGen();
        fluentgenAccessor = new FluentGenAccessor(fluentgen);
    }

    @Test
    public void testSupportedInterfaces() {
        TestUtils.ContentLocks content = TestUtils.initContentLocks(fluentgenAccessor);
        CodeModel codeModel = content.getCodeModel();
        Client client = content.getClient();

        OperationGroup operationGroup = codeModel.getOperationGroups().stream()
                .filter(og -> Utils.getDefaultName(og).equals("ManagementLocks"))
                .findFirst().get();

        Optional<MethodGroupClient> clientOpt = client.getServiceClient().getMethodGroupClients().stream()
                .filter(mgc -> mgc.getClassBaseName().equals("ManagementLocks"))
                .findFirst();
        Assertions.assertTrue(clientOpt.isPresent());

        List<IType> supportedInterfaces = FluentMethodGroupMapper.getInstance().findSupportedInterfaces(operationGroup, clientOpt.get().getClientMethods());
        Assertions.assertEquals(3, supportedInterfaces.size());
        Assertions.assertTrue(supportedInterfaces.stream().anyMatch(t -> t.toString().contains(FluentType.InnerSupportsGet(ClassType.Object).getName())));
        Assertions.assertTrue(supportedInterfaces.stream().anyMatch(t -> t.toString().contains(FluentType.InnerSupportsList(ClassType.Object).getName())));
        Assertions.assertTrue(supportedInterfaces.stream().anyMatch(t -> t.toString().contains(FluentType.InnerSupportsDelete(ClassType.Object).getName())));
    }
}
