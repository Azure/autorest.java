package com.azure.autorest.preprocessor;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.CodeModelCustomConstructor;
import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.azure.autorest.preprocessor.tranformer.Transformer;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

public class Preprocessor extends NewPlugin {
  private static final Logger LOGGER = LoggerFactory.getLogger(Preprocessor.class);

  public Preprocessor(Connection connection, String plugin, String sessionId) {
    super(connection, plugin, sessionId);
    LOGGER.error("Constructor in Preprocessor");
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
      File tempFile = new File("code-model-processed-no-tags.yaml");
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
    Yaml newYaml  = new Yaml();
    String output = newYaml.dump(codeModel);
    try {
      File tempFile = new File("code-model-processed-no-tags-new.yaml");
      if (!tempFile.exists()) {
        tempFile.createNewFile();
      }
      new FileOutputStream(tempFile).write(output.getBytes());

      writeFile(tempFile.getName(), output, null, "code-model-v4-no-tags");
      writeFile(tempFile.getName(), output, null);
    } catch (Exception e) {
      LOGGER.info("Failed to complete preprocessing " + e);
    }
    return true;
  }

}
