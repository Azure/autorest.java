// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import java.util.HashMap;
import java.util.Map;

/**
 */
public enum TestScenarioStepType {

    /**
     * Step to run a swagger operation defined rest call. This may not be just one http call.
     *
     * If the operation is a long running operation (LRO), then follow the LRO polling strategy.
     * Response statusCode must be 200 if the LRO succeeded, no matter what code the initial response is.
     * If the LRO is PUT/PATCH, the runner should automatically insert a GET after the polling to verify the resource update result.
     * If the operation is DELETE, then after the operation, the runner should automatically insert a GET to verify resource cannot be found.
     * Rest call step could be defined either by an example file, or by resourceName tracking and update.
     */
    REST_CALL("restCall"),
    /**
     * Step to deploy ARM template to the scope.
     */
    STEP_ARM_TEMPLATE("stepArmTemplate"),
    /**
     * Step to deploy ARM deployment script to the scope.
     */
    STEP_ARM_DEPLOYMENT_SCRIPT("stepArmDeploymentScript")

    ;

    private final String value;
    private static final Map<String, TestScenarioStepType> CONSTANTS = new HashMap<>();

    static {
        for (TestScenarioStepType stepType : TestScenarioStepType.values()) {
            CONSTANTS.put(stepType.value, stepType);
        }
    }

    TestScenarioStepType(String value) {
        this.value = value;
    }

    public static TestScenarioStepType fromValue(String value) {
        TestScenarioStepType stepType = CONSTANTS.get(value);
        if (stepType == null) {
            throw new IllegalArgumentException(value);
        }
        return stepType;
    }


}
