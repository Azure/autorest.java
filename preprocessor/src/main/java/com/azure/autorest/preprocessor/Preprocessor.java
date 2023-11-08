// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.preprocessor;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.Message;
import com.azure.autorest.extension.base.model.MessageChannel;
import com.azure.autorest.extension.base.model.codemodel.ChoiceValue;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.ConstantSchema;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.model.codemodel.SealedChoiceSchema;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.preprocessor.tranformer.Transformer;
import org.slf4j.Logger;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.inspector.TrustedTagInspector;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Preprocessor extends NewPlugin {
    protected final NewPlugin wrappedPlugin;
    private final Logger logger;
    protected final Connection connection;

    public Preprocessor(NewPlugin wrappedPlugin, Connection connection, String pluginName, String sessionId) {
        super(connection, pluginName, sessionId);

        this.wrappedPlugin = wrappedPlugin;
        this.logger = new PluginLogger(this, Preprocessor.class);
        this.connection = connection;
    }

    public CodeModel processCodeModel() {
        List<String> allFiles = wrappedPlugin.listInputs();
        List<String> files = allFiles.stream().filter(s -> s.contains("no-tags")).collect(Collectors.toList());
        if (files.size() != 1) {
            throw new RuntimeException(
                String.format("Generator received incorrect number of inputs: %s : %s}", files.size(),
                    String.join(", ", files)));
        }
        String file = wrappedPlugin.readFile(files.get(0));
        try {
            Files.writeString(Paths.get("code-model.yaml"), file);
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
            logger.error("Got an error " + e.getMessage());
            connection.sendError(1, 500, "Cannot parse input into code model: " + e.getMessage());
            throw new RuntimeException("Cannot parse input into code model.", e);
        }

        performPreTransformUpdates(codeModel);
        codeModel = new Transformer().transform(codeModel);
        performPostTransformUpdates(codeModel);

        try {
            Path path = Paths.get("code-model-processed-no-tags.yaml");
            Files.writeString(path, dumpYaml(codeModel));
            return codeModel;
        } catch (Exception e) {
            logger.error("Failed to pre-process the code model.", e);
            throw new RuntimeException("Failed to pre-process the code model.", e);
        }
    }

    protected CodeModel loadCodeModel(String file) throws IOException {
        if (!file.startsWith("{")) {
            // YAML
            return yamlMapper.loadAs(file, CodeModel.class);
        } else {
            return jsonMapper.readValue(file, CodeModel.class);
        }
    }

    protected String dumpYaml(CodeModel codeModel) {
        Representer representer = new Representer(new DumperOptions()) {
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
        LoaderOptions loaderOptions = new LoaderOptions();
        loaderOptions.setCodePointLimit(50 * 1024 * 1024);
        loaderOptions.setMaxAliasesForCollections(Integer.MAX_VALUE);
        loaderOptions.setNestingDepthLimit(Integer.MAX_VALUE);
        loaderOptions.setTagInspector(new TrustedTagInspector());
        Yaml newYaml = new Yaml(new Constructor(loaderOptions), representer, new DumperOptions(), loaderOptions);
        return newYaml.dump(codeModel);
    }

    private static CodeModel performPostTransformUpdates(CodeModel codeModel) {
        if (JavaSettings.getInstance().isOptionalConstantAsEnum()) {
            return convertOptionalConstantsToEnum(codeModel);
        }
        return codeModel;
    }

    private static CodeModel performPreTransformUpdates(CodeModel codeModel) {
        if (JavaSettings.getInstance().isOptionalConstantAsEnum()) {
            return convertOptionalConstantsToEnum(codeModel);
        }
        return codeModel;
    }

    public static CodeModel convertOptionalConstantsToEnum(CodeModel codeModel) {
        Set<ConstantSchema> constantSchemas = new HashSet<>(codeModel.getSchemas().getConstants());
        if (!constantSchemas.isEmpty()) {
            Map<ConstantSchema, SealedChoiceSchema> convertedChoiceSchemas = new HashMap<>();

            codeModel.getOperationGroups().stream().flatMap(og -> og.getOperations().stream()).forEach(o -> {
                o.getParameters().stream().filter(p -> !p.isRequired() && p.getSchema() instanceof ConstantSchema)
                    .forEach(p -> {
                        ConstantSchema constantSchema = (ConstantSchema) p.getSchema();
                        SealedChoiceSchema sealedChoiceSchema = convertedChoiceSchemas.computeIfAbsent(constantSchema,
                            Preprocessor::convertToChoiceSchema);
                        p.setSchema(sealedChoiceSchema);

                        o.getSignatureParameters().add(p);
                    });

                o.getRequests().forEach(r -> r.getParameters().stream()
                    .filter(p -> !p.isRequired() && p.getSchema() instanceof ConstantSchema).forEach(p -> {
                        ConstantSchema constantSchema = (ConstantSchema) p.getSchema();
                        SealedChoiceSchema sealedChoiceSchema = convertedChoiceSchemas.computeIfAbsent(constantSchema,
                            Preprocessor::convertToChoiceSchema);
                        p.setSchema(sealedChoiceSchema);

                        r.getSignatureParameters().add(p);
                    }));
            });

            boolean noneFlatten = JavaSettings.getInstance().getClientFlattenAnnotationTarget() == JavaSettings.ClientFlattenAnnotationTarget.NONE;
            for (ObjectSchema s : codeModel.getSchemas().getObjects()) {
                for (com.azure.autorest.extension.base.model.codemodel.Property p : s.getProperties()) {
                    if (p.isRequired()) {
                        continue;
                    }

                    if (p.getSchema() instanceof ConstantSchema) {
                        ConstantSchema constantSchema = (ConstantSchema) p.getSchema();
                        SealedChoiceSchema sealedChoiceSchema = convertedChoiceSchemas.computeIfAbsent(constantSchema,
                            Preprocessor::convertToChoiceSchema);
                        p.setSchema(sealedChoiceSchema);
                    } else if (noneFlatten
                        && p.getExtensions() != null && p.getExtensions().isXmsClientFlatten()
                        && p.getSchema() instanceof ObjectSchema) {
                        ObjectSchema objectSchema = (ObjectSchema) p.getSchema();
                        for (com.azure.autorest.extension.base.model.codemodel.Property p1 : objectSchema.getProperties()) {
                            if (p1.getSchema() instanceof ConstantSchema) {
                                ConstantSchema constantSchema = (ConstantSchema) p1.getSchema();
                                SealedChoiceSchema sealedChoiceSchema = convertedChoiceSchemas.computeIfAbsent(
                                    constantSchema, Preprocessor::convertToChoiceSchema);
                                p1.setSchema(sealedChoiceSchema);
                            }
                        }
                    }
                }
            }

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
        sealedChoiceSchema.setSummary(constantSchema.getSummary());
        sealedChoiceSchema.setUsage(constantSchema.getUsage());

        ChoiceValue choice = new ChoiceValue();
        choice.setValue(constantSchema.getValue().getValue().toString());
        choice.setLanguage(constantSchema.getValue().getLanguage());
        sealedChoiceSchema.setChoices(Collections.singletonList(choice));
        return sealedChoiceSchema;
    }

    @Override
    public String readFile(String fileName) {
        return wrappedPlugin.readFile(fileName);
    }

    @Override
    public <T> T getValue(Type type, String key) {
        return wrappedPlugin.getValue(type, key);
    }

    @Override
    public String getStringValue(String key) {
        return wrappedPlugin.getStringValue(key);
    }

    @Override
    public String getStringValue(String key, String defaultValue) {
        return wrappedPlugin.getStringValue(key, defaultValue);
    }

    @Override
    public String getStringValue(String[] keys, String defaultValue) {
        return wrappedPlugin.getStringValue(keys, defaultValue);
    }

    @Override
    public Boolean getBooleanValue(String key) {
        return wrappedPlugin.getBooleanValue(key);
    }

    @Override
    public boolean getBooleanValue(String key, boolean defaultValue) {
        return wrappedPlugin.getBooleanValue(key, defaultValue);
    }

    @Override
    public List<String> listInputs() {
        return wrappedPlugin.listInputs();
    }

    @Override
    public List<String> listInputs(String artifactType) {
        return wrappedPlugin.listInputs(artifactType);
    }

    @Override
    public void message(Message message) {
        wrappedPlugin.message(message);
    }

    @Override
    public void message(MessageChannel channel, String text, Throwable error, List<String> keys) {
        wrappedPlugin.message(channel, text, error, keys);
    }

    @Override
    public void writeFile(String fileName, String content, List<Object> sourceMap) {
        wrappedPlugin.writeFile(fileName, content, sourceMap);
    }

    @Override
    public void writeFile(String fileName, String content, List<Object> sourceMap, String artifactType) {
        wrappedPlugin.writeFile(fileName, content, sourceMap, artifactType);
    }

    @Override
    public void protectFiles(String path) {
        wrappedPlugin.protectFiles(path);
    }

    @Override
    public String getConfigurationFile(String fileName) {
        return wrappedPlugin.getConfigurationFile(fileName);
    }

    @Override
    public void updateConfigurationFile(String filename, String content) {
        wrappedPlugin.updateConfigurationFile(filename, content);
    }

    @Override
    public boolean process() {
        throw new UnsupportedOperationException("Use processCodeModel instead.");
    }

    @Override
    public boolean processInternal() {
        throw new UnsupportedOperationException("Use processCodeModel instead.");
    }
}
