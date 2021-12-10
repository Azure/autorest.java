/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.preprocessor.tranformer.Transformer;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * test entry for preprocessor
 */
public class PreprocessorTest {

    @Test
    @Ignore("no validation")
    public void processTest() throws Exception {
        MockPreprocessor preprocessor = new MockPreprocessor(new Connection(System.out, System.in), "dummy", "dummy");
        // This file can be obtained by calling generate commands from `generate` or `generate.bat`, it will appear under this project
        // It's a debug file from the output of modelerfour during the generate process
        String codeModelFileName = "code-model.yaml";

        CodeModel codeModel = preprocessor.loadCodeModel(codeModelFileName);
        codeModel = preprocessor.transform(codeModel);
        String output = preprocessor.dump(codeModel);

        System.out.println(output);
    }

}
