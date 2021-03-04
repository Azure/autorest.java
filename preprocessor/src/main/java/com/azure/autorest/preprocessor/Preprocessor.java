package com.azure.autorest.preprocessor;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.codemodel.ChoiceValue;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.ConstantSchema;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.model.codemodel.SealedChoiceSchema;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.preprocessor.tranformer.Transformer;
import org.slf4j.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Preprocessor extends NewPlugin {
  private final Logger logger = new PluginLogger(this, Preprocessor.class);

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

    performPretransformUpdates(codeModel);
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
      logger.error("Failed to pre-process the code model.", e);
      return false;
    }
    return true;
  }


  private CodeModel performPretransformUpdates(CodeModel codeModel) {
    if (JavaSettings.getInstance().isOptionalConstantAsEnum()) {
      return convertOptionalConstantsToEnum(codeModel);
    }
    return codeModel;
  }

  private CodeModel convertOptionalConstantsToEnum(CodeModel codeModel) {
    Set<ConstantSchema> constantSchemas = new HashSet<>(codeModel.getSchemas().getConstants());
    if (!constantSchemas.isEmpty()) {
      Map<ConstantSchema, SealedChoiceSchema> convertedChoiceSchemas = new HashMap<>();

      codeModel.getOperationGroups().stream()
              .flatMap(og -> og.getOperations().stream())
              .forEach(o -> {
                o.getParameters().stream()
                        .filter(p -> !p.isRequired() && p.getSchema() instanceof ConstantSchema)
                        .forEach(p -> {
                          ConstantSchema constantSchema = (ConstantSchema) p.getSchema();
                          SealedChoiceSchema sealedChoiceSchema = convertedChoiceSchemas.computeIfAbsent(constantSchema,
                                  Preprocessor::convertToChoiceSchema);
                          p.setSchema(sealedChoiceSchema);

                          o.getSignatureParameters().add(p);
                        });

                o.getRequests().forEach(r -> {
                  r.getParameters().stream()
                          .filter(p -> !p.isRequired() && p.getSchema() instanceof ConstantSchema)
                          .forEach(p -> {
                            ConstantSchema constantSchema = (ConstantSchema) p.getSchema();
                            SealedChoiceSchema sealedChoiceSchema = convertedChoiceSchemas.computeIfAbsent(constantSchema,
                                    Preprocessor::convertToChoiceSchema);
                            p.setSchema(sealedChoiceSchema);

                            r.getSignatureParameters().add(p);
                          });
                });
              });

      codeModel.getSchemas().getObjects().stream()
              .flatMap(s -> s.getProperties().stream())
              .filter(p -> !p.isRequired() && p.getSchema() instanceof ConstantSchema)
              .forEach(p -> {
                ConstantSchema constantSchema = (ConstantSchema) p.getSchema();
                SealedChoiceSchema sealedChoiceSchema = convertedChoiceSchemas.computeIfAbsent(constantSchema,
                        Preprocessor::convertToChoiceSchema);
                p.setSchema(sealedChoiceSchema);
              });

      codeModel.getSchemas().getSealedChoices().addAll(convertedChoiceSchemas.values());
    }
    return codeModel;
  }

  private static SealedChoiceSchema convertToChoiceSchema(ConstantSchema constantSchema) {
    SealedChoiceSchema sealedChoiceSchema = new SealedChoiceSchema();
    sealedChoiceSchema.setType(Schema.AllSchemaTypes.SEALED_CHOICE);
    sealedChoiceSchema.setChoiceType(constantSchema.getValueType());
    sealedChoiceSchema.setDefaultValue(constantSchema.getDefaultValue());
    sealedChoiceSchema.setLanguage(constantSchema.getLanguage());

    ChoiceValue choice = new ChoiceValue();
    choice.setValue(constantSchema.getValue().getValue().toString());
    choice.setLanguage(constantSchema.getValue().getLanguage());
    sealedChoiceSchema.setChoices(Collections.singletonList(choice));
    return sealedChoiceSchema;
  }
}
