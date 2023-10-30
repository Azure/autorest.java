// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.postprocessor;

import com.azure.autorest.customization.Customization;
import com.azure.autorest.customization.Editor;
import com.azure.autorest.customization.implementation.Utils;
import com.azure.autorest.customization.implementation.ls.BuildWorkspaceStatus;
import com.azure.autorest.customization.implementation.ls.EclipseLanguageClient;
import com.azure.autorest.customization.implementation.ls.models.SymbolInformation;
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
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                readme = Files.readString(Paths.get(new URI(readmePath)));
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
                logger.error("Failed to complete postprocessing.", e);
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

    private void writeToFiles(Map<String, String> javaFiles) {
        JavaSettings settings = JavaSettings.getInstance();
        if (settings.isHandlePartialUpdate()) {
            handlePartialUpdate(javaFiles);
        }

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
                Files.copy(Postprocessor.class.getClassLoader().getResourceAsStream("readme/pom.xml"), pomPath);
                pomPath.toFile().deleteOnExit();
                Files.copy(Postprocessor.class.getClassLoader().getResourceAsStream("readme/eclipse-format-azure-sdk-for-java.xml"),
                        pomPath.resolveSibling("eclipse-format-azure-sdk-for-java.xml"));
                pomPath.resolveSibling("eclipse-format-azure-sdk-for-java.xml").toFile().deleteOnExit();

                attemptMavenSpotless(pomPath, logger);

                for (Map.Entry<String, String> javaFile : javaFiles.entrySet()) {
                    Path file = tmpDir.resolve(javaFile.getKey());
                    writeFile(javaFile.getKey(), Files.readString(file), null);
                }
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            }
        } else {
            javaFiles.forEach((fileName, content) -> writeFile(fileName, content, null));
        }
    }

    private static void attemptMavenSpotless(Path pomPath, Logger logger) {
        String[] command = Utils.isWindows()
                ? new String[] { "cmd", "/c", "mvn", "spotless:apply", "-P", "spotless", "-f", pomPath.toString() }
                : new String[] { "sh", "-c", "mvn", "spotless:apply", "-P", "spotless", "-f", pomPath.toString() };

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
            logger.warn("Failed to run Spotless on generated code.");
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
            String code = Files.readString(customizationFile);
            return loadCustomizationClass(customizationFile.getFileName().toString().replace(".java", ""), filePath, code, logger);
        } catch (IOException e) {
            logger.error("Cannot read customization from base directory " + baseDirectory + " and file " + customizationFile);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static Class<? extends Customization> loadCustomizationClass(String className, String fileName, String code, Logger logger) {
        Path tempDirWithPrefix;

        // Populate editor
        Editor editor;
        try {
            tempDirWithPrefix = Files.createTempDirectory("temp");
            editor = new Editor(new HashMap<>(), tempDirWithPrefix);
            byte[] buffer = Postprocessor.class.getClassLoader().getResourceAsStream("readme/pom.xml").readAllBytes();
            editor.addFile("pom.xml", new String(buffer, StandardCharsets.UTF_8));
            attemptMavenInstall(Paths.get(tempDirWithPrefix.toString(), "pom.xml"), logger);
            editor.addFile(fileName.substring(fileName.indexOf("src/")), code);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int javaVersion = Runtime.version().feature();
        if (javaVersion != -1 && javaVersion < 11) {
            throw new IllegalStateException("Java version was '" + javaVersion + "', code customizations require "
                + "Java 11+ to be used. Please update your environment to Java 11+, preferably Java 17, and run "
                + "Autorest again.");
        }

        // Start language client
        try (EclipseLanguageClient languageClient = new EclipseLanguageClient(tempDirWithPrefix.toString())) {
            languageClient.initialize();
            SymbolInformation classSymbol = languageClient.findWorkspaceSymbol(className)
                .stream().filter(si -> si.getLocation().getUri().toString().endsWith(className + ".java"))
                .findFirst().get();
            URI fileUri = classSymbol.getLocation().getUri();
            Utils.organizeImportsOnRange(languageClient, editor, fileUri, classSymbol.getLocation().getRange());
            BuildWorkspaceStatus status = languageClient.buildWorkspace(true);
            if (status == BuildWorkspaceStatus.SUCCEED) {
                URL fileUrl = new URI(Paths.get(tempDirWithPrefix.toString(), "target", "classes").toUri().toString()).toURL();
                URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{fileUrl}, ClassLoader.getSystemClassLoader());
                return (Class<? extends Customization>) Class.forName(className, true, classLoader);
            }
            throw new RuntimeException("Failed to build with status code " + status);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Utils.deleteDirectory(tempDirWithPrefix.toFile());
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

    private static void attemptMavenInstall(Path pomPath, Logger logger) {
        String[] command = Utils.isWindows()
                ? new String[] { "cmd", "/c", "mvn", "clean", "install", "-f", pomPath.toString() }
                : new String[] { "sh", "-c", "mvn", "clean", "install", "-f", pomPath.toString() };

        // Attempt to install the POM file. This will ensure that the Eclipse language server will have all
        // necessary dependencies to run.
        try {
            Runtime.getRuntime().exec(command).waitFor(30, TimeUnit.SECONDS);
        } catch (IOException | InterruptedException ex) {
            logger.warn("Failed to install customization POM file. Eclipse language server may fail with missing dependencies."
                + "If this happens 'mvn install -f" + pomPath + "' to install dependencies manually.");
        }
    }

    private void clear() {
        JavaSettings.clear();
    }
}
