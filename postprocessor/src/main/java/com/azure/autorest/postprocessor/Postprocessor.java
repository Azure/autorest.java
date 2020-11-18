package com.azure.autorest.postprocessor;

import com.azure.autorest.customization.Customization;
import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.google.googlejavaformat.java.Formatter;
import org.slf4j.Logger;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Postprocessor extends NewPlugin {
  private final Logger logger = new PluginLogger(this, Postprocessor.class);

  public Postprocessor(Connection connection, String plugin, String sessionId) {
    super(connection, plugin, sessionId);
  }

  @Override
  public boolean processInternal() {
    List<String> files = listInputs();
    Map<String, String> fileContents = files.stream().collect(Collectors.toMap(f -> f, this::readFile));

    try {
      //Step 1: post process
      String jarPath = JavaSettings.getInstance().getCustomizationJarPath();
      String className = JavaSettings.getInstance().getCustomizationClass();
      if (jarPath != null && className != null) {
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
        URLClassLoader loader = URLClassLoader.newInstance(new URL[]{ jarUrl }, ClassLoader.getSystemClassLoader());
        Class<? extends Customization> customizationClass;
        try {
          customizationClass = (Class<? extends Customization>) Class.forName(className, true, loader);
        } catch (Exception e) {
          new PluginLogger(this, Postprocessor.class, "LoadCustomizationClass")
                  .warn("Customization class " + className + " not found in customization jar. Customization skipped.", e);
          return true;
        }
        try {
          Customization customization = customizationClass.getConstructor().newInstance();
          logger.info("Running customization, this may take a while...");
          fileContents = customization.run(fileContents);
        } catch (Exception e) {
          logger.error("Unable to complete customization", e);
          return false;
        }
      }

      //Step 2: Print to files
      Formatter formatter = new Formatter();
      for (Map.Entry<String, String> javaFile : fileContents.entrySet()) {
        String formattedSource = javaFile.getValue();
        if (javaFile.getKey().endsWith(".java")) {
          try {
            formattedSource = formatter.formatSourceAndFixImports(formattedSource);
          } catch (Exception e) {
            logger.error("Unable to format output file " + javaFile.getKey(), e);
            return false;
          }
        }
        writeFile(javaFile.getKey(), formattedSource, null);
      }
    } catch (Exception e) {
      logger.error("Failed to complete postprocessing.", e);
      return false;
    }
    return true;
  }

  private String getBaseDirectory() {
    LinkedHashMap<String, String> configurationFiles = getValue(LinkedHashMap.class, "configurationFiles");
    Optional<String> readme = configurationFiles.keySet().stream().filter(key -> !key.contains(".autorest")).findFirst();
    if (readme.isPresent()) {
      return new File(URI.create(readme.get()).getPath()).getParent();
    }

    // TODO: get autorest running directory
    return null;
  }
}
