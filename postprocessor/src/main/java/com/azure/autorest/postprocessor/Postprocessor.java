package com.azure.autorest.postprocessor;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.azure.autorest.postprocessor.customization.Customization;
import com.google.googlejavaformat.java.Formatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Map;
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
        URL jarUrl;
        if (!jarPath.startsWith("http")) {
          jarUrl = new File(jarPath).toURI().toURL();
        } else {
          jarUrl = new URI(jarPath).toURL();
        }
        URLClassLoader loader = URLClassLoader.newInstance(new URL[]{ jarUrl }, ClassLoader.getSystemClassLoader());
        Class<? extends Customization> customizationClass = (Class<? extends Customization>) Class.forName(className, true, loader);
        Customization customization = customizationClass.getConstructor(Map.class).newInstance(fileContents);
        fileContents = customization.run();
      }

      //Step 2: Print to files
      Formatter formatter = new Formatter();
      for (Map.Entry<String, String> javaFile : fileContents.entrySet()) {
        String formattedSource = formatter.formatSourceAndFixImports(javaFile.getValue());
        writeFile(javaFile.getKey(), formattedSource, null);
      }
    } catch (Exception e) {
      LOGGER.info("Failed to complete postprocessing " + e);
      throw new RuntimeException(e);
    }
    return true;
  }

}
