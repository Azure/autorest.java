package com.azure.autorest.preprocessor;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.azure.autorest.preprocessor.tranformer.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class Preprocessor extends NewPlugin {
  private static final Logger LOGGER = LoggerFactory.getLogger(Preprocessor.class);

  public Preprocessor(Connection connection, String plugin, String sessionId) {
    super(connection, plugin, sessionId);
  }

  @Override
  public boolean processInternal() {
    List<String> allFiles = listInputs();
    List<String> files = allFiles.stream().filter(s -> s.contains("no-tags")).collect(Collectors.toList());
    if (files.size() != 1) {
      throw new RuntimeException(String.format("Generator received incorrect number of inputs: %s : %s}", files.size(), String.join(", ", files)));
    }
    String file = readFile(files.get(0));
    try {
      File tempFile = new File("code-model.yaml");
      if (!tempFile.exists()) {
        tempFile.createNewFile();
      }
      new FileOutputStream(tempFile).write(file.getBytes(StandardCharsets.UTF_8));
    } catch (Exception e) {
      //
    }

    CodeModel codeModel;
    try {
      if (!file.startsWith("{")) {
        // YAML
        codeModel = yamlMapper.loadAs(file, CodeModel.class);
      } else {
        codeModel = jsonMapper.readValue(file, CodeModel.class);
      }
    } catch (Exception e) {
      System.err.println("Got an error " + e.getMessage());
      connection.sendError(1, 500, "Cannot parse input into code model: " + e.getMessage());
      return false;
    }

    codeModel = new Transformer().transform(codeModel);

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
    String output = newYaml.dump(codeModel);
    try {
      File tempFile = new File("code-model-processed-no-tags.yaml");
      if (!tempFile.exists()) {
        tempFile.createNewFile();
      }
      new FileOutputStream(tempFile).write(output.getBytes(StandardCharsets.UTF_8));
      writeFile(tempFile.getName(), output, null);
    } catch (Exception e) {
      LOGGER.info("Failed to complete preprocessing " + e);
    }
    return true;
  }

}
