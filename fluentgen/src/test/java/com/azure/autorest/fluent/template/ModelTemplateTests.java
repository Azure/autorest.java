/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */


package com.azure.autorest.fluent.template;

import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.TestUtils;
import com.azure.autorest.mapper.ModelMapper;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelPropertyAccess;
import com.azure.autorest.model.clientmodel.ClientModelPropertyReference;
import com.azure.autorest.model.javamodel.JavaFileFactory;
import com.azure.core.util.CoreUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.util.List;

public class ModelTemplateTests {

    private final JavaSettings settings = JavaSettings.getInstance();
    private final JavaFileFactory javaFileFactory = new JavaFileFactory(settings);

    @BeforeAll
    public static void init(){
        TestUtils.MockFluentGen fluentgen = new TestUtils.MockFluentGen();
    }

    /**
     * Issue: https://github.com/Azure/autorest.java/issues/1320
     * Remove duplicate setter methods from child schema when parent schema contains same property
     * mainly to test {@link com.azure.autorest.template.ModelTemplate#getParentSettersToOverride(ClientModel, JavaSettings, List)}
     */
    @Test
    public void deduplicateTest(){
        //construct parent schema
        ObjectSchema siteSchemaParent = loadSiteSchemaParent();
        ClientModel modelParent = ModelMapper.getInstance().map(siteSchemaParent);
        //get child schema
        ClientModel model = modelParent.getDerivedModels().get(0);
        ModelTemplateAccessor templateAccessor = new ModelTemplateAccessor();
        List<ClientModelPropertyReference> propertyReferences = templateAccessor.getClientModelPropertyReferences0(modelParent.getDerivedModels().get(0));
        if (!CoreUtils.isNullOrEmpty(model.getPropertyReferences())) {
            propertyReferences.addAll(model.getPropertyReferences());
        }
        // real test here
        List<ClientModelPropertyAccess> toOverride = templateAccessor.getParentSettersToOverride(model, settings, propertyReferences);
        Assertions.assertEquals(toOverride.size(), 1);
    }

    private ObjectSchema loadSiteSchemaParent() {
        Representer representer = new Representer() {
            @Override
            protected NodeTuple representJavaBeanProperty(Object javaBean, Property property, Object propertyValue, Tag customTag) {
                // if value of property is null, ignore it.
                if (propertyValue == null) {
                    return null;
                }
                else {
                    return super.representJavaBeanProperty(javaBean, property, propertyValue, customTag);
                }
            }
        };
        LoaderOptions loaderOptions = new LoaderOptions();
        loaderOptions.setMaxAliasesForCollections(Integer.MAX_VALUE);
        Yaml yaml = new Yaml(new Constructor(loaderOptions), representer, new DumperOptions(), loaderOptions);
        return yaml.loadAs(getClass().getClassLoader().getResourceAsStream("site-schema.yaml"), ObjectSchema.class);
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
