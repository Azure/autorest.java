/*
 * // Copyright (c) Microsoft Corporation. All rights reserved.
 * // Licensed under the MIT License.
 */

package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ScenarioStep;
import com.azure.autorest.extension.base.model.codemodel.TestModel;
import com.azure.autorest.extension.base.model.codemodel.TestScenarioStepType;
import com.azure.autorest.model.clientmodel.ExampleLiveTestStep;
import com.azure.autorest.model.clientmodel.LiveTestCase;
import com.azure.autorest.model.clientmodel.LiveTestStep;
import com.azure.autorest.model.clientmodel.LiveTests;
import com.azure.autorest.model.clientmodel.ProxyMethodExample;
import com.azure.autorest.util.CodeNamer;
import com.azure.autorest.util.XmsExampleWrapper;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LiveTestsMapper implements IMapper<TestModel, List<LiveTests>>{

    private static final LiveTestsMapper INSTANCE = new LiveTestsMapper();

    public static LiveTestsMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public List<LiveTests> map(TestModel testModel) {
        if (testModel.getScenarioTests() == null) {
            return Lists.newArrayList();
        }
        return testModel.getScenarioTests().stream().map(scenarioTest -> {
            LiveTests liveTests = new LiveTests(getFilename(scenarioTest.getFilePath()));
            liveTests.addTestCases(scenarioTest.getScenarios().stream().map(testScenario -> {
                LiveTestCase liveTestCase = new LiveTestCase(CodeNamer.toCamelCase(testScenario.getScenario()));
                liveTestCase.addTestSteps(testScenario.getResolvedSteps().stream().map((Function<ScenarioStep, LiveTestStep>) scenarioStep -> {
                    // future work: support other step types, for now only support example file
                    if (scenarioStep.getType() != TestScenarioStepType.REST_CALL ||
                        scenarioStep.getExampleFile() == null) {
                        throw new UnsupportedOperationException(String.format("Scenario test step: %s is not supported", scenarioStep.getType()));
                    }
                    Map<String, Object> example = new HashMap<>();
                    example.put("parameters", scenarioStep.getRequestParameters());
                    XmsExampleWrapper exampleWrapper = new XmsExampleWrapper(example, scenarioStep.getOperationId(), scenarioStep.getExampleName());
                    ProxyMethodExample proxyMethodExample = Mappers.getProxyMethodExampleMapper().map(exampleWrapper);
                    return new ExampleLiveTestStep(scenarioStep.getOperationId(), proxyMethodExample);
                }).collect(Collectors.toList()));
                return liveTestCase;
            }).collect(Collectors.toList()));
            return liveTests;
        }).collect(Collectors.toList());
    }

    private static String getFilename(String filePath) {
        String[] split = filePath.replaceAll("\\\\", "/").split("/");
        String filename = split[split.length - 1];
        filename = filename.split("\\.")[0];
        return CodeNamer.toCamelCase(filename);
    }
}
