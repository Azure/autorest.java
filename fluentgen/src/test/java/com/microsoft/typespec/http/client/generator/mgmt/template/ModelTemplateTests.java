// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.


package com.microsoft.typespec.http.client.generator.mgmt.template;

import com.microsoft.typespec.http.client.generator.core.extension.model.codemodel.CodeModel;
import com.microsoft.typespec.http.client.generator.core.extension.plugin.JavaSettings;
import com.microsoft.typespec.http.client.generator.core.model.clientmodel.Client;
import com.microsoft.typespec.http.client.generator.core.model.clientmodel.ClientModel;
import com.microsoft.typespec.http.client.generator.core.model.clientmodel.ClientModelPropertyAccess;
import com.microsoft.typespec.http.client.generator.core.model.clientmodel.ClientModelPropertyReference;
import com.microsoft.typespec.http.client.generator.mgmt.FluentGenAccessor;
import com.microsoft.typespec.http.client.generator.mgmt.TestUtils;
import com.microsoft.typespec.http.client.generator.core.mapper.ClientMapper;
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
     * mainly to test {@link com.microsoft.typespec.http.client.generator.core.template.ModelTemplate#getSuperSetters(ClientModel, JavaSettings, List)}
     */
    @Test
    public void deduplicateTest(){
        CodeModel codeModel = TestUtils.loadCodeModel(fluentGenAccessor, "code-model-fluentnamer-botservice.yaml");
        Client client = ClientMapper.getInstance().map(codeModel);
        ClientModel model = client.getModels().stream().filter(clientModel -> clientModel.getName().equals("Site")).findAny().get();
        ModelTemplateAccessor templateAccessor = new ModelTemplateAccessor();
        List<ClientModelPropertyReference> propertyReferences = templateAccessor.getClientModelPropertyReferences0(model);
        if (model.getPropertyReferences() != null && !model.getPropertyReferences().isEmpty()) {
            propertyReferences.addAll(model.getPropertyReferences());
        }
        // real test here
        List<ClientModelPropertyAccess> toOverride = templateAccessor.getSuperSetters(model, settings, propertyReferences);
        Assertions.assertEquals(toOverride.size(), 1);
    }

    private static class ModelTemplateAccessor extends FluentModelTemplate {

        public List<ClientModelPropertyReference> getClientModelPropertyReferences0(ClientModel model) {
            return super.getClientModelPropertyReferences(model);
        }

        public List<ClientModelPropertyAccess> getSuperSetters(ClientModel model, JavaSettings settings, List<ClientModelPropertyReference> propertyReferences) {
            return super.getSuperSetters(model, settings, propertyReferences);
        }

    }


}
