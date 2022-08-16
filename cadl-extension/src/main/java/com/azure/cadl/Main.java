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
import com.azure.core.util.Configuration;
import com.azure.core.util.CoreUtils;
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

    // java -jar target/azure-cadl-extension-jar-with-dependencies.jar

    public static void main(String[] args) throws IOException {
        // parameters
        String inputYamlFileName = "cadl-tests/cadl-output/code-model.yaml";
        String outputFolder = "cadl-tests/cadl-output/java/";
        String namespace = "com.azure.cadl";
        if (args.length >= 1) {
            inputYamlFileName = args[0];
        }
        if (args.length >= 2) {
            outputFolder = args[1];
            if (!outputFolder.endsWith("/")) {
                outputFolder += "/";
            }
        }
        final String outputFolderFinal = outputFolder;

        LOGGER.info("Code model file: {}", inputYamlFileName);
        LOGGER.info("Output folder: {}", outputFolderFinal);

        // load code-model.yaml
        CodeModel codeModel = loadCodeModel(inputYamlFileName);

        if (codeModel.getLanguage().getJava() != null && !CoreUtils.isNullOrEmpty(codeModel.getLanguage().getJava().getNamespace())) {
            namespace = codeModel.getLanguage().getJava().getNamespace();
        }
        namespace = Configuration.getGlobalConfiguration().get("NAMESPACE", namespace);
        LOGGER.info("Namespace: {}", namespace);

        // initialize plugin
        CadlPlugin cadlPlugin = new CadlPlugin(namespace);

        // transform code model
        codeModel = new Transformer().transform(codeModel);

        // map to client model
        Client client = Mappers.getClientMapper().map(codeModel);

        // template
        JavaPackage javaPackage = cadlPlugin.writeToTemplates(codeModel, client, JavaSettings.getInstance());

        LOGGER.info("Count of Java files: {}", javaPackage.getJavaFiles().size());
        LOGGER.info("Count of XML files: {}", javaPackage.getXmlFiles().size());
        LOGGER.info("Count of text files: {}", javaPackage.getTextFiles().size());

        // write output
        Formatter formatter = new Formatter();

        // Java
        Map<String, String> formattedFiles = new ConcurrentHashMap<>();
        javaPackage.getJavaFiles().parallelStream().forEach(javaFile -> {
            String formattedSource = javaFile.getContents().toString();
            try {
                formattedSource = formatter.formatSourceAndFixImports(formattedSource);
            } catch (Exception e) {
                LOGGER.error("Failed to format file: {}", outputFolderFinal + javaFile.getFilePath(), e);
                // but we continue so user can still check the file and see why format fails
            }
            formattedFiles.put(javaFile.getFilePath(), formattedSource);
        });
        formattedFiles.forEach((filePath, formattedSource) -> cadlPlugin.writeFile(outputFolderFinal + filePath, formattedSource, null));

        // XML include POM
        javaPackage.getXmlFiles().forEach(xmlFile -> cadlPlugin.writeFile(outputFolderFinal + xmlFile.getFilePath(), xmlFile.getContents().toString(), null));
        // Others
        javaPackage.getTextFiles().forEach(textFile -> cadlPlugin.writeFile(outputFolderFinal + textFile.getFilePath(), textFile.getContents(), null));
        // resources
        String artifactId = ClientModelUtil.getArtifactId();
        if (!CoreUtils.isNullOrEmpty(artifactId)) {
            cadlPlugin.writeFile(outputFolderFinal + "src/main/resources/" + artifactId + ".properties",
                    "name=${project.artifactId}\nversion=${project" + ".version}\n", null);
        }
    }

    private static CodeModel loadCodeModel(String filename) throws IOException {
        String file = readFile(filename);

        Representer representer = new Representer();
        representer.setPropertyUtils(new AnnotatedPropertyUtils());
        representer.getPropertyUtils().setSkipMissingProperties(true);
        LoaderOptions loaderOptions = new LoaderOptions();
        loaderOptions.setMaxAliasesForCollections(Integer.MAX_VALUE);
        Constructor constructor = new CodeModelCustomConstructor(loaderOptions);
        Yaml yamlMapper = new Yaml(constructor, representer, new DumperOptions(), loaderOptions);
        CodeModel codeModel = yamlMapper.loadAs(file, CodeModel.class);
        return codeModel;
    }

    private static String readFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
