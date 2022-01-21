/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */


package com.azure.autorest.fluent.template;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.FluentGenAccessor;
import com.azure.autorest.fluent.TestUtils;
import com.azure.autorest.mapper.ClientMapper;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelPropertyAccess;
import com.azure.autorest.model.clientmodel.ClientModelPropertyReference;
import com.azure.core.util.CoreUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ModelTemplateTests {

    private final JavaSettings settings = JavaSettings.getInstance();
    private static FluentGenAccessor fluentGenAccessor;

    @BeforeAll
    public static void init(){
        TestUtils.MockFluentGen fluentgen = new TestUtils.MockFluentGen();
        fluentGenAccessor = new FluentGenAccessor(fluentgen);
    }

    /**
     * Issue: https://github.com/Azure/autorest.java/issues/1320
     * Remove duplicate setter methods from child schema when parent schema contains same property
     * mainly to test {@link com.azure.autorest.template.ModelTemplate#getParentSettersToOverride(ClientModel, JavaSettings, List)}
     */
    @Test
    public void deduplicateTest(){
        CodeModel codeModel = TestUtils.loadCodeModel(fluentGenAccessor, "code-model-fluentnamer-botservice.yaml");
        Client client = ClientMapper.getInstance().map(codeModel);
        ClientModel model = client.getModels().stream().filter(clientModel -> clientModel.getName().equals("Site")).findAny().get();
        ModelTemplateAccessor templateAccessor = new ModelTemplateAccessor();
        List<ClientModelPropertyReference> propertyReferences = templateAccessor.getClientModelPropertyReferences0(model);
        if (!CoreUtils.isNullOrEmpty(model.getPropertyReferences())) {
            propertyReferences.addAll(model.getPropertyReferences());
        }
        // real test here
        List<ClientModelPropertyAccess> toOverride = templateAccessor.getParentSettersToOverride(model, settings, propertyReferences);
        Assertions.assertEquals(toOverride.size(), 1);
    }

    private static class ModelTemplateAccessor extends FluentModelTemplate {

        public List<ClientModelPropertyReference> getClientModelPropertyReferences0(ClientModel model) {
            return super.getClientModelPropertyReferences(model);
        }

        public List<ClientModelPropertyAccess> getParentSettersToOverride(ClientModel model, JavaSettings settings, List<ClientModelPropertyReference> propertyReferences) {
            return super.getParentSettersToOverride(model, settings, propertyReferences);
        }

    }


}
