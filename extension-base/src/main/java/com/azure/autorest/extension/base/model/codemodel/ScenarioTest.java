package com.azure.autorest.extension.base.model.codemodel;

import java.util.List;
import java.util.Map;

/**
 * @see <a href="https://github.com/Azure/azure-rest-api-specs/blob/main/documentation/api-scenario/references/ApiScenarioDefinition.md">
 *     Api Scenario Definition Reference
 *     </a>
 */
public class ScenarioTest {

    private String _filePath;
    private List<String> requiredVariables;
    private Map<String, String> requiredVariablesDefault;
    /**
     * It defines one API scenario that could go through on its own.
     */
    private List<TestScenario> scenarios;
    private ScenarioTestScope scope;
    private Boolean useArmTemplate;

    public String get_filePath() {
        return _filePath;
    }

    public void set_filePath(String _filePath) {
        this._filePath = _filePath;
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

    public List<TestScenario> getScenarios() {
        return scenarios;
    }

    public void setScenarios(List<TestScenario> scenarios) {
        this.scenarios = scenarios;
    }

    public ScenarioTestScope getScope() {
        return scope;
    }

    public void setScope(ScenarioTestScope scope) {
        this.scope = scope;
    }

    public Boolean getUseArmTemplate() {
        return useArmTemplate;
    }

    public void setUseArmTemplate(Boolean useArmTemplate) {
        this.useArmTemplate = useArmTemplate;
    }
}
