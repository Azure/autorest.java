// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.plugin;

import com.azure.autorest.extension.base.model.codemodel.AnnotatedPropertyUtils;
import com.azure.autorest.extension.base.model.codemodel.CodeModelCustomConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

public class YamlPropertyTest {

    @Test
    public void test() {

        Representer representer = new Representer();

        representer.setPropertyUtils(new AnnotatedPropertyUtils());
        representer.getPropertyUtils().setSkipMissingProperties(true);

        LoaderOptions loaderOptions = new LoaderOptions();
        loaderOptions.setMaxAliasesForCollections(Integer.MAX_VALUE);
        Constructor constructor = new CodeModelCustomConstructor(loaderOptions);
        Yaml yamlMapper = new Yaml(constructor, representer, new DumperOptions(), loaderOptions);

        TestYamlPropertyBean bean = yamlMapper.loadAs(getClass().getClassLoader().getResourceAsStream("yaml-property.yaml"), TestYamlPropertyBean.class);

        Assertions.assertTrue(bean.getPropertyToRemap());
        Assertions.assertNotNull(bean.getExtensions());
        Assertions.assertTrue(bean.getExtensions().getXmsExamples());
    }


}
