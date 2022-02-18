/*
 * // Copyright (c) Microsoft Corporation. All rights reserved.
 * // Licensed under the MIT License.
 */

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.ScenarioTest;
import com.azure.autorest.extension.base.model.codemodel.TestScenario;
import com.azure.autorest.model.clientmodel.LiveTestCase;
import com.azure.autorest.model.clientmodel.LiveTests;
import com.azure.autorest.mapper.IMapper;

import java.util.ArrayList;
import java.util.List;

public class LiveTestsMapper implements IMapper<ScenarioTest, LiveTests> {
    @Override
    public LiveTests map(ScenarioTest scenarioTest) {
        LiveTests result = new LiveTests();
        List<LiveTestCase> testcases = new ArrayList<>();
        for (TestScenario testScenario : scenarioTest.getScenarios()) {

        }
        return result;
    }
}
