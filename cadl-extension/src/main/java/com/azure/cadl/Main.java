// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.cadl;

import com.azure.autorest.CadlPlugin;
import com.azure.autorest.extension.base.model.codemodel.AnnotatedPropertyUtils;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.CodeModelCustomConstructor;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.model.javamodel.JavaPackage;
import com.azure.autorest.preprocessor.tranformer.Transformer;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.cadl.model.EmitterOptions;
import com.azure.core.util.Configuration;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.googlejavaformat.java.Formatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    static {
        OBJECT_MAPPER
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    // java -jar target/azure-cadl-extension-jar-with-dependencies.jar

    public static void main(String[] args) throws IOException {
        // parameters
        String inputYamlFileName = "cadl-tests/cadl-output/code-model.yaml";
        if (args.length >= 1) {
            inputYamlFileName = args[0];
        }

        LOGGER.info("Code model file: {}", inputYamlFileName);

        // load code-model.yaml
        CodeModel codeModel = loadCodeModel(inputYamlFileName);

        EmitterOptions emitterOptions = loadEmitterOptions(codeModel);

        // initialize plugin
        CadlPlugin cadlPlugin = new CadlPlugin(emitterOptions);

        // transform code model
        codeModel = new Transformer().transform(codeModel);

        // map to client model
        Client client = Mappers.getClientMapper().map(codeModel);

        // template
        JavaPackage javaPackage = cadlPlugin.writeToTemplates(codeModel, client, JavaSettings.getInstance());

        LOGGER.info("Count of Java files: {}", javaPackage.getJavaFiles().size());
        LOGGER.info("Count of XML files: {}", javaPackage.getXmlFiles().size());
        LOGGER.info("Count of text files: {}", javaPackage.getTextFiles().size());

        // handle partial update
        Map<String, String> javaFiles = new ConcurrentHashMap<>();
        JavaSettings settings = JavaSettings.getInstance();
        javaPackage.getJavaFiles().parallelStream().forEach(javaFile -> {
            if (settings.isHandlePartialUpdate()) {
                javaFiles.put(javaFile.getFilePath(), cadlPlugin.handlePartialUpdate(javaFile.getFilePath(), javaFile.getContents().toString()));
            } else {
                javaFiles.put(javaFile.getFilePath(), javaFile.getContents().toString());
            }
        });

        // format
        Formatter formatter = new Formatter();

        Map<String, String> formattedFiles = new ConcurrentHashMap<>();
        javaFiles.entrySet().parallelStream().forEach(entry -> {
            String filePath = entry.getKey();
            String fileContent = entry.getValue();
            String formattedSource = fileContent;
            try {
                formattedSource = formatter.formatSourceAndFixImports(fileContent);
            } catch (Exception e) {
                LOGGER.error("Failed to format file: {}", emitterOptions.getOutputDir() + filePath, e);
                // but we continue so user can still check the file and see why format fails
            }
            formattedFiles.put(filePath, formattedSource);
        });

        // write output
        // java files
        formattedFiles.forEach((filePath, formattedSource) -> cadlPlugin.writeFile(filePath, formattedSource, null));

        // XML include POM
        javaPackage.getXmlFiles().forEach(xmlFile -> cadlPlugin.writeFile(xmlFile.getFilePath(), xmlFile.getContents().toString(), null));
        // Others
        javaPackage.getTextFiles().forEach(textFile -> cadlPlugin.writeFile(textFile.getFilePath(), textFile.getContents(), null));
        // resources
        String artifactId = ClientModelUtil.getArtifactId();
        if (!CoreUtils.isNullOrEmpty(artifactId)) {
            cadlPlugin.writeFile("src/main/resources/" + artifactId + ".properties",
                    "name=${project.artifactId}\nversion=${project" + ".version}\n", null);
        }
    }

    private static EmitterOptions loadEmitterOptions(CodeModel codeModel) {

        EmitterOptions options = null;
        String emitterOptionsJson = Configuration.getGlobalConfiguration().get("emitterOptions");

        if (emitterOptionsJson != null) {
            try {
                options = OBJECT_MAPPER.readValue(emitterOptionsJson, EmitterOptions.class);
                // namespace
                if (CoreUtils.isNullOrEmpty(options.getNamespace())) {
                    if (codeModel.getLanguage().getJava() != null && !CoreUtils.isNullOrEmpty(codeModel.getLanguage().getJava().getNamespace())) {
                        options.setNamespace(codeModel.getLanguage().getJava().getNamespace());
                    }
                }

                // output path
                if (CoreUtils.isNullOrEmpty(options.getOutputDir())) {
                    options.setOutputDir("cadl-tests/cadl-output/");
                } else if (!options.getOutputDir().endsWith("/")) {
                    options.setOutputDir(options.getOutputDir() + "/");
                }
            } catch (JsonProcessingException e) {
                LOGGER.info("Read emitter options failed, emitter options json: {}", emitterOptionsJson);
            }
        }

        if (options == null) {
            // default if emitterOptions fails
            options = new EmitterOptions();
            options.setOutputDir("cadl-tests/cadl-output/");
            if (codeModel.getLanguage().getJava() != null && !CoreUtils.isNullOrEmpty(codeModel.getLanguage().getJava().getNamespace())) {
                options.setNamespace(codeModel.getLanguage().getJava().getNamespace());
            }
        }
        return options;
    }

    private static CodeModel loadCodeModel(String filename) throws IOException {
        String file = readFile(filename);

        Representer representer = new Representer();
        representer.setPropertyUtils(new AnnotatedPropertyUtils());
        representer.getPropertyUtils().setSkipMissingProperties(true);
        LoaderOptions loaderOptions = new LoaderOptions();
        loaderOptions.setCodePointLimit(50 * 1024 * 1024);
        loaderOptions.setMaxAliasesForCollections(Integer.MAX_VALUE);
        loaderOptions.setNestingDepthLimit(Integer.MAX_VALUE);
        Constructor constructor = new CodeModelCustomConstructor(loaderOptions);
        Yaml yamlMapper = new Yaml(constructor, representer, new DumperOptions(), loaderOptions);
        CodeModel codeModel = yamlMapper.loadAs(file, CodeModel.class);
        return codeModel;
    }

    private static String readFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
