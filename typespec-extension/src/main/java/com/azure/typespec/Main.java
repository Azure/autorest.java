// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.typespec;

import com.azure.autorest.TypeSpecPlugin;
import com.azure.autorest.extension.base.model.codemodel.AnnotatedPropertyUtils;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.CodeModelCustomConstructor;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.model.javamodel.JavaPackage;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.core.util.Configuration;
import com.azure.core.util.CoreUtils;
import com.azure.typespec.model.EmitterOptions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.inspector.TrustedTagInspector;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    static {
        OBJECT_MAPPER
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    // java -jar target/azure-typespec-extension-jar-with-dependencies.jar
    public static void main(String[] args) throws IOException {
        // parameters
        String inputYamlFileName = "typespec-tests/tsp-output/code-model.yaml";
        if (args.length >= 1) {
            inputYamlFileName = args[0];
        }

        LOGGER.info("Code model file: {}", inputYamlFileName);

        // load code-model.yaml
        CodeModel codeModel = loadCodeModel(inputYamlFileName);

        EmitterOptions emitterOptions = loadEmitterOptions(codeModel);

        boolean sdkIntegration = true;
        String outputDir = emitterOptions.getOutputDir();
        Path outputDirPath = Paths.get(outputDir);
        if (Files.exists(outputDirPath)) {
            try (Stream<Path> filestream = Files.list(outputDirPath)) {
                Set<String> filenames = filestream
                        .map(p -> p.getFileName().toString())
                        .map(name -> name.toLowerCase(Locale.ROOT))
                        .collect(Collectors.toSet());

                // if there is already pom and source, do not overwrite them (includes README.md, CHANGELOG.md etc.)
                sdkIntegration = !filenames.containsAll(Arrays.asList("pom.xml", "src"));
            }
        }

        // initialize plugin
        TypeSpecPlugin typeSpecPlugin = new TypeSpecPlugin(emitterOptions, sdkIntegration);

        // client
        Client client = typeSpecPlugin.processClient(codeModel);

        // template
        JavaPackage javaPackage = typeSpecPlugin.processTemplates(codeModel, client, JavaSettings.getInstance());

        LOGGER.info("Count of Java files: {}", javaPackage.getJavaFiles().size());
        LOGGER.info("Count of XML files: {}", javaPackage.getXmlFiles().size());
        LOGGER.info("Count of text files: {}", javaPackage.getTextFiles().size());

        // handle partial update
        Map<String, String> javaFiles = new ConcurrentHashMap<>();
        JavaSettings settings = JavaSettings.getInstance();
        javaPackage.getJavaFiles().parallelStream().forEach(javaFile -> {
            if (settings.isHandlePartialUpdate()) {
                javaFiles.put(javaFile.getFilePath(), typeSpecPlugin.handlePartialUpdate(javaFile.getFilePath(), javaFile.getContents().toString()));
            } else {
                javaFiles.put(javaFile.getFilePath(), javaFile.getContents().toString());
            }
        });

        // handle customization
        javaFiles.putAll(typeSpecPlugin.customizeGeneratedCode(javaFiles, outputDir));

        // write output
        // java files
        formatAndWriteJavaFiles(typeSpecPlugin, javaFiles, settings);

        // XML include POM
        javaPackage.getXmlFiles().forEach(xmlFile -> typeSpecPlugin.writeFile(xmlFile.getFilePath(), xmlFile.getContents().toString(), null));
        // Others
        javaPackage.getTextFiles().forEach(textFile -> typeSpecPlugin.writeFile(textFile.getFilePath(), textFile.getContents(), null));
        // resources
        String artifactId = ClientModelUtil.getArtifactId();
        if (!CoreUtils.isNullOrEmpty(artifactId)) {
            typeSpecPlugin.writeFile("src/main/resources/" + artifactId + ".properties",
                    "name=${project.artifactId}\nversion=${project" + ".version}\n", null);
        }
        System.exit(0);
    }

    private static void formatAndWriteJavaFiles(TypeSpecPlugin typeSpecPlugin, Map<String, String> javaFiles,
                                                JavaSettings settings) {
        if (!settings.isSkipFormatting()) {
            try {
                Path tmpDir = Files.createTempDirectory("spotless");
                tmpDir.toFile().deleteOnExit();

                for (Map.Entry<String, String> javaFile : javaFiles.entrySet()) {
                    Path file = tmpDir.resolve(javaFile.getKey());
                    Files.createDirectories(file.getParent());
                    Files.writeString(file, javaFile.getValue()).toFile().deleteOnExit();
                }

                Path pomPath = tmpDir.resolve("pom.xml");
                Files.copy(Main.class.getClassLoader().getResourceAsStream("spotless-pom.xml"), pomPath);
                Files.copy(Main.class.getClassLoader().getResourceAsStream("eclipse-format-azure-sdk-for-java.xml"),
                        pomPath.resolveSibling("eclipse-format-azure-sdk-for-java.xml"));

                attemptMavenSpotless(pomPath);

                for (Map.Entry<String, String> javaFile : javaFiles.entrySet()) {
                    Path file = tmpDir.resolve(javaFile.getKey());
                    typeSpecPlugin.writeFile(javaFile.getKey(), Files.readString(file), null);
                }
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            }
        } else {
            for (Map.Entry<String, String> javaFile : javaFiles.entrySet()) {
                typeSpecPlugin.writeFile(javaFile.getValue(), javaFile.getKey(), null);
            }
        }
    }

    private static void attemptMavenSpotless(Path pomPath) {
        String[] command = isWindows()
            ? new String[] { "cmd", "/c", "mvn", "spotless:apply", "-f", pomPath.toString() }
            : new String[] { "sh", "-c", "mvn", "spotless:apply", "-f", pomPath.toString() };

        try {
            File outputFile = Files.createTempFile(pomPath.getParent(), "spotless", ".log").toFile();
            outputFile.deleteOnExit();
            Process process = new ProcessBuilder(command)
                    .redirectErrorStream(true)
                    .redirectOutput(ProcessBuilder.Redirect.to(outputFile))
                    .start();
            process.waitFor(60, TimeUnit.SECONDS);

            if (process.isAlive() || process.exitValue() != 0) {
                process.destroyForcibly();
                throw new RuntimeException("Spotless failed to complete within 60 seconds or failed with an error code. "
                    + Files.readString(outputFile.toPath())
                    + "\nThe command ran was: " + process.info().commandLine());
            }
        } catch (IOException | InterruptedException ex) {
            Main.LOGGER.warn("Failed to run Spotless on generated code.");
        }
    }

    private static boolean isWindows() {
        String osName = System.getProperty("os.name");
        return osName != null && osName.startsWith("Windows");
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
                    options.setOutputDir("typespec-tests/tsp-output/");
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
            options.setOutputDir("typespec-tests/tsp-output/");
            if (codeModel.getLanguage().getJava() != null && !CoreUtils.isNullOrEmpty(codeModel.getLanguage().getJava().getNamespace())) {
                options.setNamespace(codeModel.getLanguage().getJava().getNamespace());
            }
        }
        return options;
    }

    private static CodeModel loadCodeModel(String filename) throws IOException {
        String file = Files.readString(Paths.get(filename));

        Representer representer = new Representer(new DumperOptions());
        representer.setPropertyUtils(new AnnotatedPropertyUtils());
        representer.getPropertyUtils().setSkipMissingProperties(true);
        LoaderOptions loaderOptions = new LoaderOptions();
        loaderOptions.setCodePointLimit(50 * 1024 * 1024);
        loaderOptions.setMaxAliasesForCollections(Integer.MAX_VALUE);
        loaderOptions.setNestingDepthLimit(Integer.MAX_VALUE);
        loaderOptions.setTagInspector(new TrustedTagInspector());
        Constructor constructor = new CodeModelCustomConstructor(loaderOptions);
        Yaml yamlMapper = new Yaml(constructor, representer, new DumperOptions(), loaderOptions);
        return yamlMapper.loadAs(file, CodeModel.class);
    }
}
