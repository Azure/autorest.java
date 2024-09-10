// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.typespec.http.client.generator.mgmt.mapper;

import com.microsoft.typespec.http.client.generator.core.extension.model.codemodel.CodeModel;
import com.microsoft.typespec.http.client.generator.core.extension.model.codemodel.OperationGroup;
import com.microsoft.typespec.http.client.generator.mgmt.FluentGen;
import com.microsoft.typespec.http.client.generator.mgmt.FluentGenAccessor;
import com.microsoft.typespec.http.client.generator.mgmt.TestUtils;
import com.microsoft.typespec.http.client.generator.mgmt.model.FluentType;
import com.microsoft.typespec.http.client.generator.mgmt.util.Utils;
import com.microsoft.typespec.http.client.generator.core.model.clientmodel.ClassType;
import com.microsoft.typespec.http.client.generator.core.model.clientmodel.Client;
import com.microsoft.typespec.http.client.generator.core.model.clientmodel.IType;
import com.microsoft.typespec.http.client.generator.core.model.clientmodel.MethodGroupClient;
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
        Assertions.assertTrue(supportedInterfaces.stream().anyMatch(t -> t.toString().contains(FluentType.InnerSupportsGet(ClassType.OBJECT).getName())));
        Assertions.assertTrue(supportedInterfaces.stream().anyMatch(t -> t.toString().contains(FluentType.InnerSupportsList(ClassType.OBJECT).getName())));
        Assertions.assertTrue(supportedInterfaces.stream().anyMatch(t -> t.toString().contains(FluentType.InnerSupportsDelete(ClassType.OBJECT).getName())));
    }
}
