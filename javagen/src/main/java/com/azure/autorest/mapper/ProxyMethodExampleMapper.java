// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.mapper;

import com.azure.autorest.model.clientmodel.ProxyMethodExample;

import java.util.Map;

public class ProxyMethodExampleMapper implements IMapper<Object, ProxyMethodExample> {

    private static final ProxyMethodExampleMapper INSTANCE = new ProxyMethodExampleMapper();

    protected ProxyMethodExampleMapper() {
    }

    public static ProxyMethodExampleMapper getInstance() {
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ProxyMethodExample map(Object xmsExample) {
        ProxyMethodExample.Builder builder = new ProxyMethodExample.Builder();

        if (xmsExample instanceof Map) {
            Object parameters = ((Map<String, Object>) xmsExample).get("parameters");
            if (parameters instanceof Map) {
                for (Map.Entry<String, Object> entry : ((Map<String, Object>) parameters).entrySet()) {
                    builder.parameter(entry.getKey(), entry.getValue());
                }
            }
            String xmsOriginalFile = (String) ((Map<String, Object>) xmsExample).get("x-ms-original-file");
            builder.originalFile(xmsOriginalFile);
        }
        return builder.build();
    }
}
