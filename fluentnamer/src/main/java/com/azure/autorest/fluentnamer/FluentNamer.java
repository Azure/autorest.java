// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluentnamer;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.namer.FluentNamerFactory;
import com.azure.autorest.fluent.transformer.FluentTransformer;
import com.azure.autorest.fluent.util.FluentJavaSettings;
import com.azure.autorest.preprocessor.Preprocessor;
import com.azure.autorest.preprocessor.tranformer.Transformer;
import com.azure.autorest.util.CodeNamer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FluentNamer extends Preprocessor {
    private final Logger logger;
    private static NewPlugin plugin;

    public FluentNamer(NewPlugin plugin, Connection connection, Yaml yamlMapper, ObjectMapper jsonMapper) {
        super(plugin, connection, yamlMapper, jsonMapper);
        this.logger = new PluginLogger(plugin, FluentNamer.class);
        FluentNamer.plugin = plugin;
    }

    public static NewPlugin getPluginInstance() {
        return plugin;
    }

    public CodeModel processCodeModel() {
        try {
            List<String> files = plugin.listInputs().stream().filter(s -> s.contains("no-tags")).collect(Collectors.toList());
            if (files.size() != 1) {
                throw new RuntimeException(String
                        .format("Generator received incorrect number of inputs: %s : %s}", files.size(), String.join(", ", files)));
            }
            // Read input file
            String file = plugin.readFile(files.get(0));
            // Write the input code model file to a local code model file to help debugging
            createInputCodeModelFile(file);
            // Deserialize the input code model string to CodeModel object
            CodeModel codeModel = loadCodeModel(file);
            // Do necessary transformation
            codeModel = transform(codeModel);
            // Write to local file (for debugging)
            Files.writeString(Paths.get("code-model-fluentnamer-no-tags.yaml"), dumpYaml(codeModel));

            return codeModel;
        } catch (Exception e) {
            logger.error("Failed to successfully run fluentnamer plugin.", e);
            throw new RuntimeException("Failed to successfully run fluentnamer plugin.", e);
        }
    }

    private void createInputCodeModelFile(String file) throws IOException {
        Files.writeString(Paths.get("code-model.yaml"), file);
    }

    private CodeModel transform(CodeModel codeModel) {
        logger.info("Load fluent settings");
        FluentJavaSettings fluentJavaSettings = new FluentJavaSettings(plugin);
        CodeNamer.setFactory(new FluentNamerFactory(fluentJavaSettings));

        // Step 2: Transform
        logger.info("Transform code model");
        FluentTransformer transformer = new FluentTransformer(fluentJavaSettings);
        codeModel = transformer.preTransform(codeModel);

        codeModel = new Transformer().transform(codeModel);

        codeModel = transformer.postTransform(codeModel);

        return codeModel;
    }
}
