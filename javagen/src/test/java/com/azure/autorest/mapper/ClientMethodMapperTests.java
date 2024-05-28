// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.mapper;

import com.azure.autorest.MockUnitJavagen;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.MethodParameter;
import com.azure.autorest.model.clientmodel.Versioning;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClientMethodMapperTests {

    @BeforeAll
    public static void ensurePlugin() {
        MockUnitJavagen javagen = new MockUnitJavagen();
    }

    @Test
    public void testOverloadedSignatures() {
        List<ClientMethodParameter> parameters = new ArrayList<>();

        parameters.add(new ClientMethodParameter.Builder()
                .name("param0")
                .wireType(ClassType.STRING)
                .required(true)
                .build());

        parameters.add(new ClientMethodParameter.Builder()
                .name("param1")
                .wireType(ClassType.STRING)
                .build());

        parameters.add(new ClientMethodParameter.Builder()
                .name("param2")
                .wireType(ClassType.STRING)
                .versioning(new Versioning.Builder().added(Arrays.asList("v1", "v2", "v3", "v4", "v5")).build())
                .build());

        parameters.add(new ClientMethodParameter.Builder()
                .name("param3")
                .wireType(ClassType.STRING)
                .versioning(new Versioning.Builder().added(Arrays.asList("v2", "v3", "v4", "v5")).build())
                .build());

        parameters.add(new ClientMethodParameter.Builder()
                .name("param4")
                .wireType(ClassType.STRING)
                .versioning(new Versioning.Builder().added(Arrays.asList("v3", "v4", "v5")).build())
                .build());

        parameters.add(new ClientMethodParameter.Builder()
                .name("param5")
                .wireType(ClassType.STRING)
                .versioning(new Versioning.Builder().added(Arrays.asList("v5")).build())
                .build());

        List<List<ClientMethodParameter>> signatures = ClientMethodMapper.findOverloadedSignatures(parameters);
        Assertions.assertEquals(4, signatures.size());
        // API for required-only would be signature of "param0"
        // API for no added (aka "v0")
        Assertions.assertEquals(Arrays.asList("param0", "param1"), signatures.get(0).stream().map(MethodParameter::getName).collect(Collectors.toList()));
        // API for v1
        Assertions.assertEquals(Arrays.asList("param0", "param1", "param2"), signatures.get(1).stream().map(MethodParameter::getName).collect(Collectors.toList()));
        // API for v2
        Assertions.assertEquals(Arrays.asList("param0", "param1", "param2", "param3"), signatures.get(2).stream().map(MethodParameter::getName).collect(Collectors.toList()));
        // API for v3
        Assertions.assertEquals(Arrays.asList("param0", "param1", "param2", "param3", "param4"), signatures.get(3).stream().map(MethodParameter::getName).collect(Collectors.toList()));
        // API for v4 be same as v3
        // API for v5 would be same as full parameters
    }
}
