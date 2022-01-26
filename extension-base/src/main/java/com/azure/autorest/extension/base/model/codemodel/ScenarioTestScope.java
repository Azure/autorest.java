/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.extension.base.model.codemodel;

import java.util.HashMap;
import java.util.Map;

/**
 */
public enum ScenarioTestScope {

    /**
     * All of the following API scenario and steps should be under some resourceGroup. It means:
     * The consumer (API scenario runner or anything consumes API scenario) SHOULD maintain the resource group itself.
     * Usually it requires user to input the subscriptionId/location, then it creates the resource group before test running, and deletes the resource group after running
     * The consumer SHOULD set the following variables:
     *  - subscriptionId
     *  - resourceGroupName
     *  - location
     */
    RESOURCE_GROUP("ResourceGroup")
    ;

    private final String value;
    private static final Map<String, ScenarioTestScope> CONSTANTS = new HashMap<>();

    static {
        for (ScenarioTestScope stepType : ScenarioTestScope.values()) {
            CONSTANTS.put(stepType.value, stepType);
        }
    }

    ScenarioTestScope(String value) {
        this.value = value;
    }

    public static ScenarioTestScope fromValue(String value) {
        ScenarioTestScope stepType = CONSTANTS.get(value);
        if (stepType == null) {
            throw new IllegalArgumentException(value);
        }
        return stepType;
    }
}
