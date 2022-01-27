// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import java.util.List;

/**
 */
public class TestModel {

    /**
     * API scenario definitions
     */
    private List<ScenarioTest> scenarioTests;

    public List<ScenarioTest> getScenarioTests() {
        return scenarioTests;
    }

    public void setScenarioTests(List<ScenarioTest> scenarioTests) {
        this.scenarioTests = scenarioTests;
    }
}
