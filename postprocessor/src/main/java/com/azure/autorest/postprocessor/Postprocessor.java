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
import com.google.googlejavaformat.java.Formatter;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ConcurrentHashMap;
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
        String readme = null;
        try {
            String readmePath = getReadme();
            if (readmePath != null) {
                logger.info("README found at: {}", readmePath);
                readme = new String(Files.readAllBytes(Paths.get(new URI(readmePath))));
            }
        } catch (IOException | URISyntaxException | IllegalArgumentException e) {
            logger.warn("Location of README is not valid", e);
            return false;
        } catch (FileSystemNotFoundException e) {
            // likely cause is the URI of the README is "https" scheme
            logger.warn("File system of README not reachable, skip README", e);
            // continue
        }

        if (className == null) {
            try {
                writeToFiles(fileContents);
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        if (jarPath == null && readme == null) {
            logger.warn("Must provide a JAR path or a README.md config containing the customization class {}", className);
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
                if (jarUrl == null || !new File(jarUrl.getFile()).exists()) {
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
                customizationClass = loadCustomizationClassFromReadme(className, readme);
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

    private void writeToFiles(Map<String, String> fileContents) {
        JavaSettings settings = JavaSettings.getInstance();
        if (settings.isHandlePartialUpdate()) {
            handlePartialUpdate(fileContents);
        }

        //Step 4: Print to files
        Map<String, String> formattedFiles = new ConcurrentHashMap<>();
        Formatter formatter = new Formatter();

        // Formatting Java source files can be expensive but can be run in parallel.
        // Submit each file for formatting as a task on the common ForkJoinPool and then wait until all tasks
        // complete.
        fileContents.entrySet().parallelStream().forEach(javaFile -> {
            String formattedSource = javaFile.getValue();
            if (javaFile.getKey().endsWith(".java")) {
                if (!settings.isSkipFormatting()) {
                    try {
                        formattedSource = formatter.formatSourceAndFixImports(formattedSource);
                    } catch (Exception e) {
                        logger.error("Unable to format output file " + javaFile.getKey(), e);
                        throw new CompletionException(e);
                    }
                }
            }

            formattedFiles.put(javaFile.getKey(), formattedSource);
        });

        // Then for each formatted file write the file. This is done synchronously as there is potential race
        // conditions that can lead to deadlocking.
        formattedFiles.forEach((filePath, formattedSource) -> writeFile(filePath, formattedSource, null));
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

    private Class<? extends Customization> loadCustomizationClassFromReadme(String className, String readmeContent) {
        String customizationFile = String.format("src/main/java/%s.java", className);
        String code;
        if (readmeContent.contains("public class " + className)) {
            int classIndex = readmeContent.indexOf("public class " + className);
            int startIndex = readmeContent.substring(0, classIndex).lastIndexOf("```java");
            code = readmeContent.substring(startIndex).replaceFirst("```java.*([\r]?\n)", "");
            int endIndex = code.indexOf("```");
            code = code.substring(0, endIndex);
        } else {
            logger.warn("No customization class defined in README.");
            return null;
        }

        return loadCustomizationClass(className, customizationFile, code, this.logger);
    }

    public static Class<? extends Customization> loadCustomizationClassFromJavaCode(String filePath, String baseDirectory, Logger logger) {
        Path customizationFile = Paths.get(filePath);
        if (!customizationFile.isAbsolute()) {
            if (baseDirectory != null) {
                customizationFile = Paths.get(baseDirectory, filePath);
            }
        }
        try {
            String code = new String(Files.readAllBytes(customizationFile), StandardCharsets.UTF_8);
            return loadCustomizationClass(customizationFile.getFileName().toString().replace(".java", ""), filePath, code, logger);
        } catch (IOException e) {
            logger.error("Cannot read customization from base directory " + baseDirectory + " and file " + customizationFile);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static Class<? extends Customization> loadCustomizationClass(String className, String fileName, String code,
        Logger logger) {
        // Compile the customization code using Maven's compiler plugin. This will ensure that all dependencies
        // are available to the customization code.

        Path customizationCompile = null;
        try {
            customizationCompile = Files.createTempDirectory("customizationCompile");

            Path pomPath = customizationCompile.resolve("pom.xml");
            Files.copy(Postprocessor.class.getResourceAsStream("/readme/pom.xml"), pomPath);

            Path sourcePath = customizationCompile.resolve(fileName);
            Files.createDirectories(sourcePath.getParent());

            Files.writeString(sourcePath, code);

            attemptMavenCompile(pomPath, logger);

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
                        String existingFileContent = new String(Files.readAllBytes(existingFilePath), StandardCharsets.UTF_8);
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

    private static void attemptMavenCompile(Path pomPath, Logger logger) {
        String[] command;
        if (Utils.isWindows()) {
            command = new String[] { "cmd", "/c", "mvn", "clean", "compile", "-f", pomPath.toString() };
        } else {
            command = new String[] { "sh", "-c", "mvn", "clean", "compile", "-f", pomPath.toString() };
        }

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
            logger.warn("Failed to run Spotless on generated code.");
        }
    }

    private void clear() {
        JavaSettings.clear();
    }

    private static int getJavaVersion(Logger logger) {
        // java.version format:
        // 8 and lower: 1.7, 1.8.0
        // 9 and above: 12, 14.1.1
        String version = System.getProperty("java.version");
        if (version == null || version.isEmpty()) {
            logger.info("Unable to determine Java version to verify if Java 11+ is being used, which is the "
                + "requirement to run Autorest code customizations.");
            return -1;
        }

        if (version.startsWith("1.")) {
            if (version.length() < 3) {
                logger.info("Unable to parse Java version to verify if Java 11+ is being used, which is the "
                    + "requirement to run Autorest code customizations. Version was: " + version);
                return -1;
            }

            try {
                return Integer.parseInt(version.substring(2, 3));
            } catch (NumberFormatException t) {
                logger.info("Unable to parse Java version to verify if Java 11+ is being used, which is the "
                    + "requirement to run Autorest code customizations. Version was: " + version);
                return -1;
            }
        } else {
            int idx = version.indexOf(".");

            if (idx == -1) {
                try {
                    return Integer.parseInt(version);
                } catch (NumberFormatException ex) {
                    logger.info("Unable to parse Java version to verify if Java 11+ is being used, which is the "
                        + "requirement to run Autorest code customizations. Version was: " + version);
                    return -1;
                }
            }
            try {
                return Integer.parseInt(version.substring(0, idx));
            } catch (NumberFormatException t) {
                logger.info("Unable to parse Java version to verify if Java 11+ is being used, which is the "
                    + "requirement to run Autorest code customizations. Version was: " + version);
                return -1;
            }
        }
    }
}
