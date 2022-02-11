// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ProxyMethodExample;
import com.azure.autorest.util.ProxyMethodExampleWrapper;

import java.util.Locale;
import java.util.Map;

public class ProxyMethodExampleMapper implements IMapper<ProxyMethodExampleWrapper, ProxyMethodExample> {

    private static final ProxyMethodExampleMapper INSTANCE = new ProxyMethodExampleMapper();

    protected ProxyMethodExampleMapper() {
    }

    public static ProxyMethodExampleMapper getInstance() {
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ProxyMethodExample map(ProxyMethodExampleWrapper exampleWrapper) {
        ProxyMethodExample.Builder builder = new ProxyMethodExample.Builder();

        Object xmsExample = exampleWrapper.getXmsExample();
        if (xmsExample instanceof Map) {
            Object parameters = ((Map<String, Object>) xmsExample).get("parameters");
            if (parameters instanceof Map) {
                for (Map.Entry<String, Object> entry : ((Map<String, Object>) parameters).entrySet()) {
                    builder.parameter(entry.getKey(), entry.getValue());
                }
            }
            String xmsOriginalFile = (String) ((Map<String, Object>) xmsExample).get("x-ms-original-file");
            builder.originalFile(xmsOriginalFile);
            if (exampleWrapper.getOperationId() != null) {
                builder.codeSnippetIdentifier(buildCodeSnippetIdentifier(exampleWrapper.getOperationId()));
            }
        }
        return builder.build();
    }

    private String buildCodeSnippetIdentifier(String operationId) {
        String[] operationGroupAndId = operationId.split("_");
        if (operationGroupAndId.length != 2) {
            return null;
        }
        String operationGroup = operationGroupAndId[0];
        String oprId = operationGroupAndId[1];
        return String.format("%s.%s.%s", JavaSettings.getInstance().getPackage(), operationGroup.toLowerCase(Locale.ROOT), oprId.toLowerCase(Locale.ROOT));
    }
}
