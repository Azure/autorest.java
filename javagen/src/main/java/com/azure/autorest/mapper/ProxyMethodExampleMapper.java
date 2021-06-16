/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.mapper;

import com.azure.autorest.model.clientmodel.ProxyMethodExample;

import java.util.Map;

public class ProxyMethodExampleMapper implements IMapper<Object, ProxyMethodExample> {

    @Override
    public ProxyMethodExample map(Object xmsExample) {
        ProxyMethodExample.Builder builder = new ProxyMethodExample.Builder();

        if (xmsExample instanceof Map) {
            Object parameters = ((Map<String, Object>) xmsExample).get("parameters");
            if (parameters != null && parameters instanceof Map) {
                for (Map.Entry<String, Object> entry : ((Map<String, Object>) parameters).entrySet()) {
                    builder.parameter(entry.getKey(), entry.getValue());
                }
            }
        }
        return builder.build();
    }
}
