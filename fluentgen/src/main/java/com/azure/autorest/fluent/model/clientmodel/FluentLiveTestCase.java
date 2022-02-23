// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.fluent.template.FluentExampleTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FluentLiveTestCase {

    private final Set<FluentExampleTemplate.HelperFeature> helperFeatures = new HashSet<>();
    private final List<FluentLiveTestStep> steps = new ArrayList<>();
    private final String methodName;

    public FluentLiveTestCase(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public Set<FluentExampleTemplate.HelperFeature> getHelperFeatures() {
        return helperFeatures;
    }

    public List<FluentLiveTestStep> getSteps() {
        return steps;
    }
}
