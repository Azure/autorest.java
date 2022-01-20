/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.extension.base.model.codemodel;

import java.util.List;
import java.util.Map;

/**
 * API scenarios
 */
public class TestScenario {

    private String description;
    /**
     * Extra environment variables that required by API scenario
     */
    private List<String> requiredVariables;
    /**
     * Default values of the required variables
     */
    private Map<String, String> requiredVariablesDefault;
    private String scenario;
    /**
     * Whether to share the scope and prepareSteps with other scenarios
     */
    private Boolean shareScope;

    private List<ScenarioStep> resolvedSteps;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getRequiredVariables() {
        return requiredVariables;
    }

    public void setRequiredVariables(List<String> requiredVariables) {
        this.requiredVariables = requiredVariables;
    }

    public Map<String, String> getRequiredVariablesDefault() {
        return requiredVariablesDefault;
    }

    public void setRequiredVariablesDefault(Map<String, String> requiredVariablesDefault) {
        this.requiredVariablesDefault = requiredVariablesDefault;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public Boolean getShareScope() {
        return shareScope;
    }

    public void setShareScope(Boolean shareScope) {
        this.shareScope = shareScope;
    }

    @YamlProperty("_resolvedSteps")
    public List<ScenarioStep> getResolvedSteps() {
        return resolvedSteps;
    }

    public void setResolvedSteps(List<ScenarioStep> resolvedSteps) {
        this.resolvedSteps = resolvedSteps;
    }
}
