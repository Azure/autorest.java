/*
 * // Copyright (c) Microsoft Corporation. All rights reserved.
 * // Licensed under the MIT License.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.fluent.template.FluentExampleTemplate;

public class FluentExampleLiveTestStep extends FluentLiveTestStep {

    private FluentExampleTemplate.ExampleMethod exampleMethod;

    public FluentExampleTemplate.ExampleMethod getExampleMethod() {
        return exampleMethod;
    }

    public void setExampleMethod(FluentExampleTemplate.ExampleMethod exampleMethod) {
        this.exampleMethod = exampleMethod;
    }
}
