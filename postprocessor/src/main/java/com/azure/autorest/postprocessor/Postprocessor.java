// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.postprocessor;

import com.azure.autorest.customization.Customization;
import com.azure.autorest.customization.implementation.Utils;
import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.partialupdate.util.PartialUpdateHandler;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Postprocessor extends NewPlugin {
    private final Logger logger = new PluginLogger(this, Postprocessor.class);

    public Postprocessor(Connection connection, String plugin, String sessionId) {
        super(connection, plugin, sessionId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean processInternal() {
        this.clear();

        List<String> files = listInputs();
        Map<String, String> fileContents = files.stream().collect(Collectors.toMap(f -> f, this::readFile));

        String jarPath = JavaSettings.getInstance().getCustomizationJarPath();
        String className = JavaSettings.getInstance().getCustomizationClass();

        if (className == null) {
            try {
                writeToFiles(fileContents);
            } catch (Exception e) {
                logger.error("Failed to complete postprocessing.", e);
                return false;
            }
            return true;
        }

        if (jarPath == null && (!className.startsWith("src") || !className.endsWith(".java"))) {
            logger.warn("Must provide a JAR path or a source file path containing the customization class {}", className);
            return false;
        }

        try {
            //Step 1: post process
            Class<? extends Customization> customizationClass;
            if (jarPath != null) {
                URL jarUrl = null;
                if (!jarPath.startsWith("http")) {
                    if (Paths.get(jarPath).isAbsolute()) {
                        jarUrl = new File(jarPath).toURI().toURL();
                    } else {
                        String baseDirectory = getBaseDirectory();
                        if (baseDirectory != null) {
                            jarUrl = Paths.get(baseDirectory, jarPath).toUri().toURL();
                        }
                    }
                } else {
                    jarUrl = new URI(jarPath).toURL();
                }
                if (jarUrl == null || Files.notExists(Paths.get(jarUrl.toURI()))) {
                    new PluginLogger(this, Postprocessor.class, "LoadCustomizationJar")
                        .warn("Customization JAR {} not found. Customization skipped.", jarPath);
                    return true;
                }
                URLClassLoader loader = URLClassLoader.newInstance(new URL[]{jarUrl}, ClassLoader.getSystemClassLoader());
                try {
                    customizationClass = (Class<? extends Customization>) Class.forName(className, true, loader);
                } catch (Exception e) {
                    new PluginLogger(this, Postprocessor.class, "LoadCustomizationClass")
                        .warn("Customization class " + className +
                            " not found in customization jar. Customization skipped.", e);
                    return true;
                }
            } else if (className.startsWith("src") && className.endsWith(".java")) {
                customizationClass = loadCustomizationClassFromJavaCode(className, getBaseDirectory(), logger);
            } else {
                throw new RuntimeException("Invalid customization class " + className);
            }

            try {
                Customization customization = customizationClass.getConstructor().newInstance();
                logger.info("Running customization, this may take a while...");
                fileContents = customization.run(fileContents, logger);
            } catch (Exception e) {
                logger.error("Unable to complete customization", e);
                return false;
            }

            //Step 2: Print to files
            writeToFiles(fileContents);
        } catch (Exception e) {
            logger.error("Failed to complete postprocessing.", e);
            return false;
        }
        return true;
    }

    private void writeToFiles(Map<String, String> javaFiles) {
        JavaSettings settings = JavaSettings.getInstance();
        if (settings.isHandlePartialUpdate()) {
            handlePartialUpdate(javaFiles);
        }

        if (!settings.isSkipFormatting()) {
            Path tmpDir = null;
            try {
                tmpDir = Files.createTempDirectory("spotless" + UUID.randomUUID());

                for (Map.Entry<String, String> javaFile : javaFiles.entrySet()) {
                    Path file = tmpDir.resolve(javaFile.getKey());
                    Files.createDirectories(file.getParent());
                    Files.writeString(file, javaFile.getValue());
                }

                Path pomPath = tmpDir.resolve("spotless-pom.xml");
                Files.copy(Postprocessor.class.getClassLoader().getResourceAsStream("readme/pom.xml"), pomPath);
                Files.copy(Postprocessor.class.getClassLoader().getResourceAsStream("readme/eclipse-format-azure-sdk-for-java.xml"),
                    pomPath.resolveSibling("eclipse-format-azure-sdk-for-java.xml"));

                attemptMavenSpotless(pomPath);

                for (Map.Entry<String, String> javaFile : javaFiles.entrySet()) {
                    Path file = tmpDir.resolve(javaFile.getKey());
                    writeFile(javaFile.getKey(), Files.readString(file), null);
                }
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            } finally {
                if (tmpDir != null) {
                    Utils.deleteDirectory(tmpDir.toFile());
                }
            }
        } else {
            javaFiles.forEach((fileName, content) -> writeFile(fileName, content, null));
        }
    }

    private static void attemptMavenSpotless(Path pomPath) {
        String[] command = Utils.isWindows()
            ? new String[] { "cmd", "/c", "mvn", "spotless:apply", "-P", "spotless", "-f", pomPath.toString() }
            : new String[] { "mvn", "spotless:apply", "-P", "spotless", "-f", pomPath.toString() };

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
                    + Files.readString(outputFile.toPath()));
            }
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException("Failed to run Spotless on generated code.", ex);
        }
    }

    private String getReadme() {
        List<String> configurationFiles = getValue(List.class, "configurationFiles");
        return configurationFiles.stream().filter(key -> !key.contains(".autorest")).findFirst().orElse(null);
    }

    private String getBaseDirectory() {
        String readme = getReadme();
        if (readme != null) {
            return new File(URI.create(readme).getPath()).getParent();
        }

        // TODO: get autorest running directory
        return null;
    }

    public static Class<? extends Customization> loadCustomizationClassFromJavaCode(String filePath, String baseDirectory, Logger logger) {
        Path customizationFile = Paths.get(filePath);
        if (!customizationFile.isAbsolute()) {
            if (baseDirectory != null) {
                customizationFile = Paths.get(baseDirectory, filePath);
            }
        }

        try {
            String code = Files.readString(customizationFile);
            return loadCustomizationClass(customizationFile.getFileName().toString().replace(".java", ""), code);
        } catch (IOException e) {
            logger.error("Cannot read customization from base directory " + baseDirectory + " and file " + customizationFile);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static Class<? extends Customization> loadCustomizationClass(String className, String code) {
        Path customizationCompile = null;
        try {
            customizationCompile = Files.createTempDirectory("customizationCompile" + UUID.randomUUID());

            Path pomPath = customizationCompile.resolve("compile-pom.xml");
            Files.copy(Postprocessor.class.getClassLoader().getResourceAsStream("readme/pom.xml"), pomPath);

            Path sourcePath = customizationCompile.resolve("src/main/java/" + className + ".java");
            Files.createDirectories(sourcePath.getParent());

            Files.writeString(sourcePath, code);

            attemptMavenInstall(pomPath);

            URL fileUrl = customizationCompile.resolve("target/classes").toUri().toURL();
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{fileUrl},
                ClassLoader.getSystemClassLoader());
            return (Class<? extends Customization>) Class.forName(className, true, classLoader);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if (customizationCompile != null) {
                Utils.deleteDirectory(customizationCompile.toFile());
            }
        }
    }

    private void handlePartialUpdate(Map<String, String> fileContents) {
        logger.info("Begin handle partial update...");
        // handle partial update
        // currently only support add additional interface or overload a generated method in sync and async client
        fileContents.replaceAll((path, generatedFileContent) -> {
            if (path.endsWith(".java")) { // only handle for .java file
                // get existing file path
                // use output-folder from autorest, if exists and is absolute path
                String projectBaseDirectoryPath = null;
                String outputFolderPath = JavaSettings.getInstance().getAutorestSettings().getOutputFolder();
                if (Paths.get(outputFolderPath).isAbsolute()) {
                    projectBaseDirectoryPath = outputFolderPath;
                }
                if (projectBaseDirectoryPath == null || !(new File(projectBaseDirectoryPath).isDirectory())) {
                    // use parent directory of swagger/readme.md
                    projectBaseDirectoryPath = new File(getBaseDirectory()).getParent();
                }
                Path existingFilePath = Paths.get(projectBaseDirectoryPath, path);
                // check if existingFile exists, if not, no need to handle partial update
                if (Files.exists(existingFilePath)) {
                    try {
                        String existingFileContent = Files.readString(existingFilePath);
                        return PartialUpdateHandler.handlePartialUpdateForFile(generatedFileContent, existingFileContent);
                    } catch (Exception e) {
                        logger.error("Unable to get content from file path", e);
                        throw new RuntimeException(e);
                    }
                }
            }
            return generatedFileContent;
        });
        logger.info("Finish handle partial update.");
    }

    private static void attemptMavenInstall(Path pomPath) {
        String[] command = Utils.isWindows()
            ? new String[] { "cmd", "/c", "mvn", "compiler:compile", "-f", pomPath.toString() }
            : new String[] { "mvn", "compiler:compile", "-f", pomPath.toString() };

        try {
            File outputFile = Files.createTempFile(pomPath.getParent(), "compile", ".log").toFile();
            Process process = new ProcessBuilder(command)
                .redirectErrorStream(true)
                .redirectOutput(ProcessBuilder.Redirect.to(outputFile))
                .start();
            process.waitFor(60, TimeUnit.SECONDS);

            if (process.isAlive() || process.exitValue() != 0) {
                process.destroyForcibly();
                throw new RuntimeException("Compile failed to complete within 60 seconds or failed with an error code. "
                    + Files.readString(outputFile.toPath())
                    + "If this happens 'mvn compile -f " + pomPath + "' to install dependencies manually.");
            }
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException("Failed to run compile on generated code.", ex);
        }
    }

    private void clear() {
        JavaSettings.clear();
    }
}
