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
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaPackage;
import com.azure.autorest.model.projectmodel.TextFile;
import com.azure.autorest.model.xmlmodel.XmlFile;
import com.azure.autorest.preprocessor.tranformer.Transformer;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.core.util.CoreUtils;
import com.google.googlejavaformat.java.Formatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    // java -jar target/azure-cadl-extension-jar-with-dependencies.jar

    public static void main(String[] args) throws IOException {
        // parameters
        String inputYamlFileName = "cadl-project/cadl-output/code-model.yaml";
        String outputFolder = "cadl-project/cadl-output/java/";
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

        LOGGER.info("Code model file: {}", inputYamlFileName);
        LOGGER.info("Output folder: {}", outputFolder);

        // load code-model.yaml
        CodeModel codeModel = loadCodeModel(inputYamlFileName);

        if (codeModel.getLanguage().getJava() != null && !CoreUtils.isNullOrEmpty(codeModel.getLanguage().getJava().getNamespace())) {
            namespace = codeModel.getLanguage().getJava().getNamespace();
        }
        LOGGER.info("Namespace: {}", namespace);

        // initialize plugin
        CadlPlugin cadlPlugin = new CadlPlugin(namespace);

        // transform cod emodel
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
        for (JavaFile javaFile : javaPackage.getJavaFiles()) {
            String content = javaFile.getContents().toString();
            try {
                content = formatter.formatSourceAndFixImports(content);
            } catch (Exception e) {
                LOGGER.error("Failed to format file: {}", outputFolder + javaFile.getFilePath(), e);
//                continue;
            }
            cadlPlugin.writeFile(outputFolder + javaFile.getFilePath(), content, null);
        }
        for (XmlFile xmlFile : javaPackage.getXmlFiles()) {
            String content = xmlFile.getContents().toString();
            cadlPlugin.writeFile(outputFolder + xmlFile.getFilePath(), content, null);
        }
        for (TextFile testFile : javaPackage.getTextFiles()) {
            String content = testFile.getContents();
            cadlPlugin.writeFile(outputFolder + testFile.getFilePath(), content, null);
        }
        // resources
        String artifactId = ClientModelUtil.getArtifactId();
        if (!CoreUtils.isNullOrEmpty(artifactId)) {
            cadlPlugin.writeFile(outputFolder + "src/main/resources/" + artifactId + ".properties",
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
        try (InputStream fis = new FileInputStream(path)) {
            StringBuilder sb = new StringBuilder();
            char[] buffer = new char[1024];
            try (InputStreamReader reader = new InputStreamReader(fis, StandardCharsets.UTF_8)) {
                int charsRead;
                while ((charsRead = reader.read(buffer, 0, buffer.length)) > 0) {
                    sb.append(buffer, 0, charsRead);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return sb.toString();
        }
    }
}
