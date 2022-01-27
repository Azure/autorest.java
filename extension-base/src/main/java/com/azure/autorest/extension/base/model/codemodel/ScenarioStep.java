// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import java.util.Map;

/**
 */
public class ScenarioStep {

    private TestScenarioStepType type;
    private String operationId;
    private String exampleFile;
    private String exampleName;
    /**
     * map of resolved parameter name and value.
     */
    private Map<String, Object> requestParameters;

    public TestScenarioStepType getType() {
        return type;
    }

    public void setType(TestScenarioStepType type) {
        this.type = type;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getExampleFile() {
        return exampleFile;
    }

    public void setExampleFile(String exampleFile) {
        this.exampleFile = exampleFile;
    }

    public String getExampleName() {
        return exampleName;
    }

    public void setExampleName(String exampleName) {
        this.exampleName = exampleName;
    }

    public Map<String, Object> getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(Map<String, Object> requestParameters) {
        this.requestParameters = requestParameters;
    }
}
