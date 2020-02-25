package com.azure.autorest.fluentnamer;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.azure.autorest.preprocessor.tranformer.Transformer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
    try {
      List<String> files = listInputs().stream().filter(s -> s.contains("no-tags")).collect(Collectors.toList());
      if (files.size() != 1) {
        throw new RuntimeException(String
            .format("Generator received incorrect number of inputs: %s : %s}", files.size(), String.join(", ", files)));
      }
      // Read input file
      String file = readFile(files.get(0));
      // Write the input code model file to a local code model file to help debugging
      createInputCodeModelFile(file);
      // Deserialize the input code model string to CodeModel object
      CodeModel codeModel = loadCodeModel(file);
      // Do necessary transformation
      codeModel = new Transformer().transform(codeModel);
      // Rename fluent types here
      renameFluentTypes(codeModel);
      // Write to local file (for debugging)
      Yaml newYaml = createYaml();
      String output = newYaml.dump(codeModel);
      File fluentNamerFile = new File("code-model-fluentnamer-no-tags.yaml");
      if (!fluentNamerFile.exists()) {
        fluentNamerFile.createNewFile();
      }
      new FileOutputStream(fluentNamerFile).write(output.getBytes(StandardCharsets.UTF_8));

      // Output updated code model
      writeFile(fluentNamerFile.getName(), output, null);
    } catch (Exception e) {
      LOGGER.info("Failed to successfully run fluentnamer plugin " + e);
      connection.sendError(1, 500, "Error occured while running fluentnamer plugin: " + e.getMessage());
      return false;
    }
    return true;
  }

  private void createInputCodeModelFile(String file) throws IOException {
    File tempFile = new File("code-model.yaml");
    if (!tempFile.exists()) {
      tempFile.createNewFile();
    }
    new FileOutputStream(tempFile).write(file.getBytes(StandardCharsets.UTF_8));
  }

  private CodeModel loadCodeModel(String file) throws com.fasterxml.jackson.core.JsonProcessingException {
    CodeModel codeModel;
    if (!file.startsWith("{")) {
      codeModel = yamlMapper.loadAs(file, CodeModel.class);
    } else {
      codeModel = jsonMapper.readValue(file, CodeModel.class);
    }
    return codeModel;
  }

  private Yaml createYaml() {
    Representer representer = new Representer() {
      @Override
      protected NodeTuple representJavaBeanProperty(Object javaBean, Property property, Object propertyValue,
          Tag customTag) {
        // if value of property is null, ignore it.
        if (propertyValue == null) {
          return null;
        } else {
          return super.representJavaBeanProperty(javaBean, property, propertyValue, customTag);
        }
      }
    };
    return new Yaml(representer);
  }

  private void renameFluentTypes(CodeModel codeModel) {
    // TODO: This is the place to update names for fluent types
  }
}
