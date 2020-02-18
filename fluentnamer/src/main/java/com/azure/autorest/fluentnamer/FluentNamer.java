package com.azure.autorest.fluentnamer;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.NewPlugin;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

public class FluentNamer extends NewPlugin {

  private static final Logger LOGGER = LoggerFactory.getLogger(FluentNamer.class);

  public FluentNamer(Connection connection, String plugin,
      String sessionId) {
    super(connection, plugin, sessionId);
  }

  @Override
  public boolean processInternal() {
    List<String> allFiles = listInputs();
    List<String> files = allFiles.stream().filter(s -> s.contains("no-tags")).collect(Collectors.toList());
    if (files.size() != 1) {
      throw new RuntimeException(String
          .format("Generator received incorrect number of inputs: %s : %s}", files.size(), String.join(", ", files)));
    }

    String file = readFile(files.get(0));
    CodeModel codeModel;
    try {
      // YAML
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
      Yaml newYaml  = new Yaml(representer);

      codeModel = newYaml.loadAs(file, CodeModel.class);
      renameFluentTypes(codeModel);
      String output = newYaml.dump(codeModel);
      File tempFile = new File("code-model-fluentnamer-no-tags.yaml");
      if (!tempFile.exists()) {
        tempFile.createNewFile();
      }
      new FileOutputStream(tempFile).write(file.getBytes(StandardCharsets.UTF_8));
      writeFile(tempFile.getName(), file, null);
    } catch (Exception e) {
      LOGGER.error("Failed to complete fluentnamer plugin operation ", e);
      System.err.println("Got an error " + e.getMessage());
      connection.sendError(1, 500, "Cannot parse input into code model: " + e.getMessage());
      return false;
    }
    return true;
  }

  private void renameFluentTypes(CodeModel codeModel) {
    // TODO: This is the place to update names for fluent types
  }
}
