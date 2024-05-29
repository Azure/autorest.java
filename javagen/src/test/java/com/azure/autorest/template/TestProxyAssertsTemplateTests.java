// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.MockUnitJavagen;
import com.azure.autorest.model.projectmodel.Project;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestProxyAssertsTemplateTests {

    private static class MockProject extends Project {
        MockProject() {
            serviceName = "OpenAI";
            serviceDescription = "OpenAI Service";
            namespace = "com.azure.ai.openai";
            artifactId = "azure-ai-openai";
            sdkRepositoryPath = "sdk/openai/azure-ai-openai";
        }
    }

    @Test
    public void testAssertsTemplateWrite() throws JsonProcessingException {
        MockUnitJavagen javagen = new MockUnitJavagen();

        Project project = new MockProject();

        String output = new TestProxyAssetsTemplate().write(project);

        JsonNode jsonNode = new ObjectMapper().readTree(output);
        Assertions.assertEquals("Azure/azure-sdk-assets", jsonNode.get("AssetsRepo").asText());
        Assertions.assertEquals("java", jsonNode.get("AssetsRepoPrefixPath").asText());
        Assertions.assertEquals("java/openai/azure-ai-openai", jsonNode.get("TagPrefix").asText());
        Assertions.assertNotNull(jsonNode.get("Tag").asText());
    }
}
