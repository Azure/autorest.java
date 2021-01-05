/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.util;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.TestUtils;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MapType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TypeConversionUtilsTests {

    @BeforeAll
    public static void ensurePlugin() {
        new TestUtils.MockFluentGen();
    }

    @Test
    public void testConversionExpression() {
        JavaSettings settings = JavaSettings.getInstance();
        IType innerType = new ClassType.Builder()
                .packageName(settings.getPackage(settings.getFluentModelsSubpackage()))
                .name("MockResourceInner")
                .build();

        IType mapType = new MapType(innerType);
        String convertedExpression = TypeConversionUtils.conversionExpression(mapType, TypeConversionUtils.tempPropertyName());
        Assertions.assertEquals("inner.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, inner1 -> new MockResourceImpl(inner1.getValue(), this.manager())))", convertedExpression);

        IType listType = new ListType(innerType);
        convertedExpression = TypeConversionUtils.conversionExpression(listType, TypeConversionUtils.tempPropertyName());
        Assertions.assertEquals("inner.stream().map(inner1 -> new MockResourceImpl(inner1, this.manager())).collect(Collectors.toList())", convertedExpression);

        IType pagedIterableType = GenericType.PagedIterable(innerType);
        convertedExpression = TypeConversionUtils.conversionExpression(pagedIterableType, TypeConversionUtils.tempPropertyName());
        Assertions.assertEquals("inner.mapPage(inner1 -> new MockResourceImpl(inner1, this.manager()))", convertedExpression);

        IType responseType = GenericType.Response(innerType);
        convertedExpression = TypeConversionUtils.conversionExpression(responseType, TypeConversionUtils.tempPropertyName());
        Assertions.assertEquals("new SimpleResponse<>(inner.getRequest(), inner.getStatusCode(), inner.getHeaders(), new MockResourceImpl(inner.getValue(), this.manager()))", convertedExpression);

        IType nestedMapType = new MapType(mapType);
        convertedExpression = TypeConversionUtils.conversionExpression(nestedMapType, TypeConversionUtils.tempPropertyName());
        Assertions.assertEquals("inner.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, inner1 -> inner1.getValue().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, inner2 -> new MockResourceImpl(inner2.getValue(), this.manager())))))", convertedExpression);

        IType nestedListType = new ListType(listType);
        convertedExpression = TypeConversionUtils.conversionExpression(nestedListType, TypeConversionUtils.tempPropertyName());
        Assertions.assertEquals("inner.stream().map(inner1 -> inner1.stream().map(inner2 -> new MockResourceImpl(inner2, this.manager())).collect(Collectors.toList())).collect(Collectors.toList())", convertedExpression);
    }

    private void expressionsWorkbench() {
        {
            // map
            Map<String, MockInner> inner = new HashMap();
            Map<String, MockResourceImpl> impl =
                    inner.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, inner1 -> new MockResourceImpl(inner1.getValue(), this.manager())));
        }

        {
            // nested map
            Map<String, Map<String, MockInner>> inner = new HashMap();
            Map<String, Map<String, MockResourceImpl>> impl =
                    inner.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, inner1 -> inner1.getValue().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, inner2 -> new MockResourceImpl(inner2.getValue(), this.manager())))));
        }

        {
            // nested list
            List<List<MockInner>> inner = new ArrayList<>();
            List<List<MockResourceImpl>> impl =
                    inner.stream().map(inner1 -> inner1.stream().map(inner2 -> new MockResourceImpl(inner2, this.manager())).collect(Collectors.toList())).collect(Collectors.toList());
        }
    }

    private String manager() {
        return null;
    }

    private static class MockInner {
    }

    private static class MockResourceImpl {
        public MockResourceImpl(MockInner inner, String manager) {
            // do nothing
        }
    }
}
