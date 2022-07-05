// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.cadl;

import com.azure.autorest.CadlPlugin;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaPackage;
import com.azure.autorest.preprocessor.tranformer.Transformer;
import com.google.googlejavaformat.java.Formatter;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
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

    // java -jar target/azure-cadl-extension-jar-with-dependencies.jar

    public static void main(String[] args) throws IOException {
        String inputYamlFileName = "cadl-project/cadl-output/code-model.yaml";
        if (args.length > 1) {
            inputYamlFileName = args[1];
        }

        CadlPlugin cadlPlugin = new CadlPlugin();
        CodeModel codeModel = loadCodeModel(inputYamlFileName);

        codeModel = new Transformer().transform(codeModel);

        Client client = Mappers.getClientMapper().map(codeModel);

        JavaPackage javaPackage = cadlPlugin.writeToTemplates(JavaSettings.getInstance(), codeModel, client);

        Formatter formatter = new Formatter();
        for (JavaFile javaFile : javaPackage.getJavaFiles()) {
            String content = javaFile.getContents().toString();
            try {
                content = formatter.formatSourceAndFixImports(content);
            } catch (Exception e) {
                continue;
            }
            new File("cadl-sample/" + javaFile.getFilePath()).getParentFile().mkdirs();
            writeFile("cadl-sample/" + javaFile.getFilePath(), content);
        }
    }

    private static void writeFile(String path, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(content);
        }
    }

    private static CodeModel loadCodeModel(String filename) throws IOException {
        String file = readFile(filename);

        Representer representer = new Representer() {
            @Override
            protected NodeTuple representJavaBeanProperty(Object javaBean, Property property, Object propertyValue,
                                                          Tag customTag) {
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
        Yaml newYaml = new Yaml(new Constructor(loaderOptions), representer, new DumperOptions(), loaderOptions);
        CodeModel codeModel = newYaml.loadAs(file, CodeModel.class);
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
