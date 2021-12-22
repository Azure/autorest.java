/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * test entry for preprocessor
 */
public class PreprocessorTests {

    @Test
    public void processTest() {
        MockPreprocessor preprocessor = new MockPreprocessor(new Connection(System.out, System.in), "dummy", "dummy");
        // SOURCE SWAGGER URL: https://github.com/Azure/azure-rest-api-specs/blob/main/specification/containerregistry/data-plane/Azure.ContainerRegistry/stable/2021-07-01/containerregistry.json
        // It's a debug file from the output of modelerfour during the generate process where it converts swagger definition into code model.
        // This file can be obtained by calling generate commands from `generate` or `generate.bat` on the swagger json, it will appear under this project by the name 'code-model.yaml'.
        String codeModelFileName = "containerregistry-code-model.yaml";

        CodeModel codeModel = preprocessor.loadCodeModel(codeModelFileName);
        Assert.assertEquals(codeModel.getOperationGroups().size(), 3);
        Assert.assertEquals(codeModel.getOperationGroups().get(0).getOperations().size(), 15);
        Assert.assertEquals(codeModel.getOperationGroups().get(1).getOperations().size(), 11);
        Assert.assertEquals(codeModel.getOperationGroups().get(2).getOperations().size(), 3);
        codeModel = preprocessor.transform(codeModel);
        Assert.assertEquals(codeModel.getOperationGroups().get(0).getOperations().size(), 18); // additional 3 pagination operations

        String output = preprocessor.dump(codeModel);
    }

}
