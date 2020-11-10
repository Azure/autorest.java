package com.azure.autorest.postprocessor;

import com.azure.autorest.customization.Customization;
import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.Message;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.google.googlejavaformat.java.Formatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Postprocessor extends NewPlugin {
  private static final Logger LOGGER = LoggerFactory.getLogger(Postprocessor.class);

  public Postprocessor(Connection connection, String plugin, String sessionId) {
    super(connection, plugin, sessionId);
  }

  @Override
  public boolean processInternal() {
    List<String> files = listInputs();
    Map<String, String> fileContents = files.stream().collect(Collectors.toMap(f -> f, this::readFile));

    try {
      //Step 1: post process
      String jarPath = JavaSettings.getInstance().getPostProcessorJarPath();
      String className = JavaSettings.getInstance().getPostProcessorClass();
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
        if (jarUrl == null) {
          Message message = new Message();
          message.setChannel("error");
          message.setText("Customization JAR " + jarPath + " not found. Customization skipped.");
          message(message);
          return false;
        }
        URLClassLoader loader = URLClassLoader.newInstance(new URL[]{ jarUrl }, ClassLoader.getSystemClassLoader());
        Class<? extends Customization> customizationClass = (Class<? extends Customization>) Class.forName(className, true, loader);
        Customization customization = customizationClass.getConstructor().newInstance();
        fileContents = customization.run(fileContents);
      }

      //Step 2: Print to files
      Formatter formatter = new Formatter();
      for (Map.Entry<String, String> javaFile : fileContents.entrySet()) {
        String formattedSource = javaFile.getValue();
        if (javaFile.getKey().endsWith(".java")) {
          formattedSource = formatter.formatSourceAndFixImports(formattedSource);
        }
        writeFile(javaFile.getKey(), formattedSource, null);
      }
    } catch (Exception e) {
      Message message = new Message();
      message.setChannel("error");
      message.setText("Failed to complete postprocessing. Postprocessing skipped. Error: " + e.getMessage());
      message.setDetails(e.getStackTrace());
      message(message);
      return false;
    }
    return true;
  }

  private String getBaseDirectory() {
    LinkedHashMap<String, String> configurationFiles = getValue(LinkedHashMap.class, "configurationFiles");
    Optional<String> readme = configurationFiles.keySet().stream().filter(key -> !key.contains(".autorest")).findFirst();
    if (readme.isPresent()) {
      return new File(readme.get().replace("file:///", "")).getParent();
    }

    // TODO: get autorest running directory
    return null;
  }
}
