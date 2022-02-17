/*
 * // Copyright (c) Microsoft Corporation. All rights reserved.
 * // Licensed under the MIT License.
 */

package com.azure.autorest.model.clientmodel;

import java.util.List;

public class LiveTestCase {

    private String name;
    private List<LiveTestStep> testSteps;

    public List<LiveTestStep> getTestSteps() {
        return testSteps;
    }

    public void setTestSteps(List<LiveTestStep> testSteps) {
        this.testSteps = testSteps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
