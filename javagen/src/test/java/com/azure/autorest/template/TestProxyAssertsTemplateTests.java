// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.MockUnitJavagen;
import com.azure.autorest.model.projectmodel.Project;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Assert;
import org.junit.Test;

public class TestProxyAssertsTemplateTests {

    private static class MockProject extends Project {
        MockProject() {
            serviceName = "OpenAI";
            serviceDescription = "OpenAI Service";
            namespace = "com.azure.ai.openai";
            artifactId = "azure-ai-openai";
            sdkRepositoryUri = "https://github.com/Azure/azure-sdk-for-java/tree/main/sdk/openai/azure-ai-openai";
        }
    }

    @Test
    public void testAssertsTemplateWrite() throws JsonProcessingException {
        MockUnitJavagen javagen = new MockUnitJavagen();

        Project project = new MockProject();

        String output = new TestProxyAssetsTemplate().write(project);

        JsonNode jsonNode = TestProxyAssetsTemplate.OBJECT_MAPPER.readTree(output);
        Assert.assertEquals("Azure/azure-sdk-assets", jsonNode.get("AssetsRepo").asText());
        Assert.assertEquals("java", jsonNode.get("AssetsRepoPrefixPath").asText());
        Assert.assertEquals("java/openai/azure-ai-openai", jsonNode.get("TagPrefix").asText());
        Assert.assertNotNull(jsonNode.get("Tag").asText());
    }
}
