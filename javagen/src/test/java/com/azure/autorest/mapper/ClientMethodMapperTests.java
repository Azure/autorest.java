// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.mapper;

import com.azure.autorest.MockUnitJavagen;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.MethodParameter;
import com.azure.autorest.model.clientmodel.Versioning;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClientMethodMapperTests {

    @BeforeClass
    public static void ensurePlugin() {
        MockUnitJavagen javagen = new MockUnitJavagen();
    }

    @Test
    public void testOverloadedSignatures() {
        List<ClientMethodParameter> parameters = new ArrayList<>();

        parameters.add(new ClientMethodParameter.Builder()
                .name("param1")
                .wireType(ClassType.String)
                .required(true)
                .build());

        parameters.add(new ClientMethodParameter.Builder()
                .name("param2")
                .wireType(ClassType.String)
                .versioning(new Versioning.Builder().added(Arrays.asList("v1", "v2", "v3", "v4", "v5")).build())
                .build());

        parameters.add(new ClientMethodParameter.Builder()
                .name("param3")
                .wireType(ClassType.String)
                .versioning(new Versioning.Builder().added(Arrays.asList("v2", "v3", "v4", "v5")).build())
                .build());

        parameters.add(new ClientMethodParameter.Builder()
                .name("param4")
                .wireType(ClassType.String)
                .versioning(new Versioning.Builder().added(Arrays.asList("v3", "v4", "v5")).build())
                .build());

        parameters.add(new ClientMethodParameter.Builder()
                .name("param5")
                .wireType(ClassType.String)
                .versioning(new Versioning.Builder().added(Arrays.asList("v5")).build())
                .build());

        List<List<ClientMethodParameter>> signatures = ClientMethodMapper.findOverloadedSignatures(parameters);
        Assert.assertEquals(3, signatures.size());
        // API for no version would be same as API for required parameters
        // API for v1
        Assert.assertEquals(Arrays.asList("param1", "param2"), signatures.get(0).stream().map(MethodParameter::getName).collect(Collectors.toList()));
        // API for v2
        Assert.assertEquals(Arrays.asList("param1", "param2", "param3"), signatures.get(1).stream().map(MethodParameter::getName).collect(Collectors.toList()));
        // API for v3
        Assert.assertEquals(Arrays.asList("param1", "param2", "param3", "param4"), signatures.get(2).stream().map(MethodParameter::getName).collect(Collectors.toList()));
        // API for v4 be same as v4
        // API for v5 would be same as full parameters
    }
}
