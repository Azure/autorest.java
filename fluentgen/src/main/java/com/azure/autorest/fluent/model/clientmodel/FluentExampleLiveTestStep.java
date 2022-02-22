/*
 * // Copyright (c) Microsoft Corporation. All rights reserved.
 * // Licensed under the MIT License.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.fluent.template.FluentExampleTemplate;

public class FluentExampleLiveTestStep extends FluentLiveTestStep {

    private final FluentExampleTemplate.ExampleMethod exampleMethod;

    public FluentExampleLiveTestStep(FluentExampleTemplate.ExampleMethod exampleMethod) {
        this.exampleMethod = exampleMethod;
    }

    public FluentExampleTemplate.ExampleMethod getExampleMethod() {
        return exampleMethod;
    }

}
