/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.template.ServiceClientTemplate;
import com.azure.autorest.template.prototype.MethodTemplate;

public class FluentServiceClientTemplate extends ServiceClientTemplate {

    private static FluentServiceClientTemplate _instance = new FluentServiceClientTemplate();
    static {
//        _instance.additionalMethods.add(MethodTemplate.builder()
//                .methodSignature("abc")
//                .build());
    }

    public static FluentServiceClientTemplate getInstance() {
        return _instance;
    }
}
