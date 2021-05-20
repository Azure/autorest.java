package com.azure.autorest.postprocessor;

import com.azure.autorest.customization.Customization;
import com.azure.autorest.customization.Editor;
import com.azure.autorest.customization.implementation.Utils;
import com.azure.autorest.customization.implementation.ls.EclipseLanguageClient;
import com.azure.autorest.customization.implementation.ls.models.CodeAction;
import com.azure.autorest.customization.implementation.ls.models.CodeActionKind;
import com.azure.autorest.customization.implementation.ls.models.SymbolInformation;
import com.azure.autorest.customization.implementation.ls.models.WorkspaceEdit;
import com.azure.autorest.customization.implementation.ls.models.WorkspaceEditCommand;
import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Postprocessor extends NewPlugin {
  private final Logger logger = new PluginLogger(this, Postprocessor.class);

  public Postprocessor(Connection connection, String plugin, String sessionId) {
    super(connection, plugin, sessionId);
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean processInternal() {
    List<String> files = listInputs();
    Map<String, String> fileContents = files.stream().collect(Collectors.toMap(f -> f, this::readFile));

    String jarPath = JavaSettings.getInstance().getCustomizationJarPath();
    String className = JavaSettings.getInstance().getCustomizationClass();
    String readme = null;
    try {
      readme = new String(Files.readAllBytes(Paths.get(new URI(getReadme()))));
    } catch (IOException | URISyntaxException e) {
      return false;
    }

    if (className == null) {
      try {
        writeToFiles(fileContents);
      } catch (FormatterException e) {
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
                  .warn("Customization class " + className + " not found in customization jar. Customization skipped.", e);
          return true;
        }
      } else if (className.startsWith("src") && className.endsWith(".java")) {
        customizationClass = loadCustomizationClassFromJavaCode(className);
      } else {
        customizationClass = loadCustomizationClassFromReadme(className, readme);
      }

      try {
        Customization customization = customizationClass.getConstructor().newInstance();
        logger.info("Running customization, this may take a while...");
        fileContents = customization.run(fileContents);
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

  private void writeToFiles(Map<String, String> fileContents) throws FormatterException {
    Formatter formatter = new Formatter();
    for (Map.Entry<String, String> javaFile : fileContents.entrySet()) {
      String formattedSource = javaFile.getValue();
      if (javaFile.getKey().endsWith(".java")) {
        try {
//          formattedSource = formatter.formatSourceAndFixImports(formattedSource);
        } catch (Exception e) {
          logger.error("Unable to format output file " + javaFile.getKey(), e);
          throw e;
        }
      }
      writeFile(javaFile.getKey(), formattedSource, null);
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

    return loadCustomizationClass(className, customizationFile, code);
  }

  private Class<? extends Customization> loadCustomizationClassFromJavaCode(String filePath) {
    Path customizationFile = Paths.get(filePath);
    if (!customizationFile.isAbsolute()) {
      String baseDirectory = getBaseDirectory();
      if (baseDirectory != null) {
        customizationFile = Paths.get(baseDirectory, filePath);
      }
    }
    try {
      String code = new String(Files.readAllBytes(customizationFile), StandardCharsets.UTF_8);
      return loadCustomizationClass(customizationFile.getFileName().toString().replace(".java", ""), filePath, code);
    } catch (IOException e) {
      logger.error("Cannot read customization from " + filePath);
      return null;
    }
  }

  @SuppressWarnings("unchecked")
  private Class<? extends Customization> loadCustomizationClass(String className, String fileName, String code) {
    Path tempDirWithPrefix;

    // Populate editor
    Editor editor;
    try {
      tempDirWithPrefix = Files.createTempDirectory("temp");
      editor = new Editor(new HashMap<>(), tempDirWithPrefix);
      InputStream pomStream = Postprocessor.class.getResourceAsStream("/readme/pom.xml");
      byte[] buffer = new byte[pomStream.available()];
      pomStream.read(buffer);
      editor.addFile("pom.xml", new String(buffer, StandardCharsets.UTF_8));
      editor.addFile(fileName, code);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    // Start language client
    EclipseLanguageClient languageClient = null;
    try {
      languageClient = new EclipseLanguageClient(tempDirWithPrefix.toString());
      languageClient.initialize();
      SymbolInformation classSymbol = languageClient.findWorkspaceSymbol(className)
              .stream().filter(si -> si.getLocation().getUri().toString().endsWith(className + ".java"))
              .findFirst().get();
      URI fileUri = classSymbol.getLocation().getUri();
      Optional<CodeAction> organizeImports = languageClient.listCodeActions(fileUri, classSymbol.getLocation().getRange())
              .stream().filter(ca -> ca.getKind().equals(CodeActionKind.SOURCE_ORGANIZEIMPORTS.toString()))
              .findFirst();
      if (organizeImports.isPresent()) {
        WorkspaceEditCommand command;
        if (organizeImports.get().getCommand() instanceof WorkspaceEditCommand) {
          command = (WorkspaceEditCommand) organizeImports.get().getCommand();
          for(WorkspaceEdit workspaceEdit : command.getArguments()) {
            Utils.applyWorkspaceEdit(workspaceEdit, editor, languageClient);
          }
        }
      }
      languageClient.buildWorkspace(true);
      URL fileUrl = new URI(Paths.get(tempDirWithPrefix.toString(), "target", "classes").toUri().toString()).toURL();
      URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{fileUrl}, ClassLoader.getSystemClassLoader());
      return (Class<? extends Customization>) Class.forName(className, true, classLoader);
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      Utils.deleteDirectory(tempDirWithPrefix.toFile());
      if (languageClient != null) {
        languageClient.exit();
      }
    }
  }
}
