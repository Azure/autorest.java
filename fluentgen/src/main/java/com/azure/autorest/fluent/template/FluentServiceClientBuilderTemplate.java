/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.template.ServiceClientBuilderTemplate;

public class FluentServiceClientBuilderTemplate extends ServiceClientBuilderTemplate {

    private static final FluentServiceClientBuilderTemplate instance = new FluentServiceClientBuilderTemplate();

    public static FluentServiceClientBuilderTemplate getInstance() {
        return instance;
    }
}
