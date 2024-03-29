// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.template;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.javamodel.JavaInterface;
import com.azure.autorest.template.ProxyTemplate;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FluentProxyTemplate extends ProxyTemplate {

    private static final FluentProxyTemplate INSTANCE = new FluentProxyTemplate();

    public static FluentProxyTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    protected void writeProxyMethodHeaders(ProxyMethod restAPIMethod, JavaInterface interfaceBlock) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", restAPIMethod.getRequestContentType());
        headers.put("Accept", String.join(",", restAPIMethod.getResponseContentTypes()));

        Set<String> headerParameterNames = restAPIMethod.getParameters().stream()
                .filter(p -> p.getRequestParameterLocation() == RequestParameterLocation.HEADER)
                .map(ProxyMethodParameter::getRequestParameterName)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        headers = headers.entrySet().stream()
                .filter(e -> !headerParameterNames.contains(e.getKey().toLowerCase(Locale.ROOT)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (!headers.isEmpty()) {
            String headersString = headers.entrySet().stream()
                    .map(e -> String.format("\"%s: %s\"", e.getKey(), e.getValue()))
                    .collect(Collectors.joining(", "));
            interfaceBlock.annotation(String.format("Headers({ %s })",
                    headersString));
        }
    }
}
