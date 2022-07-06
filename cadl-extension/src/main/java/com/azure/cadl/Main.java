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
import com.google.googlejavaformat.java.Formatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    // java -jar target/azure-cadl-extension-jar-with-dependencies.jar

    public static void main(String[] args) throws IOException {
        String inputYamlFileName = "cadl-project/cadl-output/code-model.yaml";
        String outputFolder = "cadl-sample/";
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
        if (args.length >= 3) {
            namespace = args[2];
        }

        LOGGER.info("Code model file: {}", inputYamlFileName);
        LOGGER.info("Output folder: {}", outputFolder);
        LOGGER.info("Namespace: {}", namespace);

        CadlPlugin cadlPlugin = new CadlPlugin(namespace);
        CodeModel codeModel = loadCodeModel(inputYamlFileName);

        codeModel = new Transformer().transform(codeModel);

        Client client = Mappers.getClientMapper().map(codeModel);

        JavaPackage javaPackage = cadlPlugin.writeToTemplates(JavaSettings.getInstance(), codeModel, client);

        LOGGER.info("Count of Java files: {}", javaPackage.getJavaFiles().size());
        LOGGER.info("Count of XML files: {}", javaPackage.getXmlFiles().size());
        LOGGER.info("Count of text files: {}", javaPackage.getTextFiles().size());

        Formatter formatter = new Formatter();
        for (JavaFile javaFile : javaPackage.getJavaFiles()) {
            String content = javaFile.getContents().toString();
            try {
                content = formatter.formatSourceAndFixImports(content);
            } catch (Exception e) {
                LOGGER.error("Failed to format file: {}", outputFolder + javaFile.getFilePath(), e);
                continue;
            }
            writeFile(outputFolder + javaFile.getFilePath(), content);
        }
        for (XmlFile xmlFile : javaPackage.getXmlFiles()) {
            String content = xmlFile.getContents().toString();
            writeFile(outputFolder + xmlFile.getFilePath(), content);
        }
        for (TextFile testFile : javaPackage.getTextFiles()) {
            String content = testFile.getContents();
            writeFile(outputFolder + testFile.getFilePath(), content);
        }
    }

    private static void writeFile(String path, String content) throws IOException {
        new File(path).getParentFile().mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(content);
        }
        LOGGER.info("Write file: {}", path);
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
