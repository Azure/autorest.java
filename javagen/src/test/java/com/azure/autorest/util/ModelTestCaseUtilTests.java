// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.util;

import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientEnumValue;
import com.azure.autorest.model.clientmodel.EnumType;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Objects;

public class ModelTestCaseUtilTests {

    @Test
    public void testJsonFromEnumType() {
        EnumType type = new EnumType.Builder()
                .elementType(ClassType.STRING)
                .values(Arrays.asList(
                        new ClientEnumValue("200", "200"),
                        new ClientEnumValue("404", "404")))
                .build();

        Object jsonObject = ModelTestCaseUtil.jsonFromType(0, type);
        Assert.assertTrue(jsonObject instanceof String);
        Assert.assertTrue(Objects.equals("200", jsonObject) || Objects.equals("404", jsonObject));

        type = new EnumType.Builder()
                .elementType(ClassType.INTEGER)
                .values(Arrays.asList(
                        new ClientEnumValue("200", "200"),
                        new ClientEnumValue("404", "404")))
                .build();

        jsonObject = ModelTestCaseUtil.jsonFromType(0, type);
        Assert.assertTrue(jsonObject instanceof Integer);
        Assert.assertTrue(Objects.equals(200, jsonObject) || Objects.equals(404, jsonObject));
    }
}
