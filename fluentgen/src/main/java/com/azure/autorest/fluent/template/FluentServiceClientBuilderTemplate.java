// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.template;

import com.azure.autorest.model.javamodel.JavaContext;
import com.azure.autorest.template.ServiceClientBuilderTemplate;

import java.util.Set;

public class FluentServiceClientBuilderTemplate extends ServiceClientBuilderTemplate {

    private static final FluentServiceClientBuilderTemplate INSTANCE = new FluentServiceClientBuilderTemplate();

    public static FluentServiceClientBuilderTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    protected void addGeneratedImport(Set<String> imports) {
    }

    @Override
    protected void addGeneratedAnnotation(JavaContext classBlock) {
    }
}
