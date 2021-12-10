/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest;

import com.azure.autorest.model.javamodel.JavaPackage;
import org.junit.Test;

public class JavagenUnitTest {

    @Test
    public void genTest() {

        // SOURCE SWAGGER URL: https://github.com/Azure/azure-rest-api-specs/blob/main/specification/containerregistry/data-plane/Azure.ContainerRegistry/stable/2021-07-01/containerregistry.json
        // This file is a product from the preprocessor. After calling autorest generate script, it can be found under preprocessor model by the name 'code-model-processed-no-tags.yaml'.
        String yaml = "containerregistry-code-model-processed-no-tags.yaml";

        MockJavagen4Unit javagen = new MockJavagen4Unit();
        JavaPackage javaPackage = javagen.convertFileToTemplates(yaml);

        System.out.println(javaPackage.getJavaFiles().size());

    }


}
