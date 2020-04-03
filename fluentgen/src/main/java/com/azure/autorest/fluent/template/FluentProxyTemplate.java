/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 *
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.javamodel.JavaInterface;
import com.azure.autorest.template.ProxyTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FluentProxyTemplate extends ProxyTemplate {

    private static FluentProxyTemplate _instance = new FluentProxyTemplate();

    public static FluentProxyTemplate getInstance() {
        return _instance;
    }

    @Override
    protected void writeProxyMethodHeaders(ProxyMethod restAPIMethod, JavaInterface interfaceBlock) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", restAPIMethod.getRequestContentType());
        headers.put("Accept", String.join(",", restAPIMethod.getResponseContentTypes()));

        Set<String> headerParameterNames = restAPIMethod.getParameters().stream()
                .filter(p -> p.getRequestParameterLocation() == RequestParameterLocation.Header)
                .map(ProxyMethodParameter::getRequestParameterName)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        headers = headers.entrySet().stream()
                .filter(e -> !headerParameterNames.contains(e.getKey().toLowerCase()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (!headers.isEmpty()) {
            String headersString = headers.entrySet().stream()
                    .map(e -> String.format("\"%s: %s\"", e.getKey(), e.getValue()))
                    .collect(Collectors.joining(", "));
            interfaceBlock.annotation(String.format("Headers({ %s })",
                    headersString));
        }
    }

    @Override
    protected String breakUrlOnLengthLimit(String string) {
        final int lengthLimit = 120 - 12 - 4;

        if (string.length() <= lengthLimit) {
            return string;
        } else {
            StringBuilder builder = new StringBuilder();
            boolean first = true;
            while (!string.isEmpty()) {
                if (string.length() > lengthLimit) {
                    int index = string.indexOf("/");
                    int nextIndex = index;
                    while (nextIndex != -1 && nextIndex < lengthLimit) {
                        index = nextIndex;
                        nextIndex = string.indexOf("/", index + 1);
                    }
                    if (index == -1) {
                        index = string.length();
                    }
                    if (!first) {
                        builder.append("\" + \"");
                    } else {
                        first = false;
                    }
                    builder.append(string, 0, index);
                    string = string.substring(index);
                } else {
                    // certainly not first
                    builder.append("\" + \"");
                    builder.append(string);
                    string = string.substring(string.length());
                }
            }
            return builder.toString();
        }
    }
}
