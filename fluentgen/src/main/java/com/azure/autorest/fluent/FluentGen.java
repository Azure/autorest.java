// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent;

import com.azure.autorest.Javagen;
import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.checker.JavaFormatter;
import com.azure.autorest.fluent.mapper.ExampleParser;
import com.azure.autorest.fluent.mapper.FluentMapper;
import com.azure.autorest.fluent.mapper.FluentMapperFactory;
import com.azure.autorest.fluent.mapper.FluentPomMapper;
import com.azure.autorest.fluent.model.clientmodel.FluentClient;
import com.azure.autorest.fluent.model.clientmodel.FluentExample;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.model.javamodel.FluentJavaPackage;
import com.azure.autorest.fluent.model.projectmodel.FluentProject;
import com.azure.autorest.model.clientmodel.ClientBuilder;
import com.azure.autorest.model.projectmodel.TextFile;
import com.azure.autorest.fluent.namer.FluentNamerFactory;
import com.azure.autorest.fluent.template.FluentTemplateFactory;
import com.azure.autorest.fluent.util.FluentJavaSettings;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.model.clientmodel.AsyncSyncClient;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.model.clientmodel.ClientException;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientResponse;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.PackageInfo;
import com.azure.autorest.model.clientmodel.Pom;
import com.azure.autorest.model.clientmodel.XmlSequenceWrapper;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.xmlmodel.XmlFile;
import com.azure.autorest.template.Templates;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.autorest.util.CodeNamer;
import org.slf4j.Logger;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FluentGen extends Javagen {

    private final Logger logger = new PluginLogger(this, FluentGen.class);
    static FluentGen instance;

    private FluentJavaSettings fluentJavaSettings;
    private FluentMapper fluentMapper;

    private List<FluentExample> fluentPremiumExamples;

    public FluentGen(Connection connection, String plugin, String sessionId) {
        super(connection, plugin, sessionId);
        instance = this;
        Javagen.instance = this;
    }

    public static FluentGen getPluginInstance() {
        return instance;
    }

    @Override
    public boolean processInternal() {
        this.clear();

        try {
            JavaSettings settings = JavaSettings.getInstance();

            List<String> files = listInputs().stream().filter(s -> s.contains("no-tags")).collect(Collectors.toList());
            if (files.size() != 1) {
                throw new RuntimeException(String.format("Generator received incorrect number of inputs: %s : %s}", files.size(), String.join(", ", files)));
            }

            logger.info("Read YAML");
            String fileContent = readFile(files.get(0));
            createInputCodeModelFile(fileContent);

            // Parse yaml to code model
            CodeModel codeModel = this.handleYaml(fileContent);

            // Map code model to client model
            Client client = this.handleMap(codeModel);

            // Write to templates
            FluentJavaPackage javaPackage = this.handleTemplate(client);

            // Fluent Lite
            this.handleFluentLite(codeModel, client, javaPackage);

            // Print to files
            logger.info("Write Java");
            for (JavaFile javaFile : javaPackage.getJavaFiles()) {
                String content = javaFile.getContents().toString();
                String path = javaFile.getFilePath();

                if (!settings.isSkipFormatting()) {
                    // formatter
                    boolean isSampleJavaFile = path.contains("src/samples/java/");
                    content = new JavaFormatter(content, path).format(!isSampleJavaFile);
                }

                writeFile(path, content, null);
            }
            logger.info("Write Xml");
            for (XmlFile xmlFile : javaPackage.getXmlFiles()) {
                writeFile(xmlFile.getFilePath(), xmlFile.getContents().toString(), null);
            }
            logger.info("Write Text");
            for (TextFile textFile : javaPackage.getTextFiles()) {
                writeFile(textFile.getFilePath(), textFile.getContents(), null);
            }
            return true;
        } catch (Exception e) {
            logger.error("Failed to successfully run fluentgen plugin " + e, e);
            //connection.sendError(1, 500, "Error occurred while running fluentgen plugin: " + e.getMessage());
            return false;
        }
    }

    private void createInputCodeModelFile(String file) throws IOException {
        File tempFile = new File("code-model.yaml");
        if (!tempFile.exists()) {
            tempFile.createNewFile();
        }
        new FileOutputStream(tempFile).write(file.getBytes(StandardCharsets.UTF_8));
    }

    CodeModel handleYaml(String yamlContent) {
        Representer representer = new Representer() {
            @Override
            protected NodeTuple representJavaBeanProperty(Object javaBean, Property property, Object propertyValue, Tag customTag) {
                // if value of property is null, ignore it.
                if (propertyValue == null) {
                    return null;
                }
                else {
                    return super.representJavaBeanProperty(javaBean, property, propertyValue, customTag);
                }
            }
        };
        LoaderOptions loaderOptions = new LoaderOptions();
        loaderOptions.setMaxAliasesForCollections(Integer.MAX_VALUE);
        Yaml newYaml = new Yaml(new Constructor(loaderOptions), representer, new DumperOptions(), loaderOptions);
        CodeModel codeModel = newYaml.loadAs(yamlContent, CodeModel.class);
        return codeModel;
    }

    Client handleMap(CodeModel codeModel) {
        JavaSettings settings = JavaSettings.getInstance();

        FluentMapper fluentMapper = this.getFluentMapper();

        logger.info("Map code model to client model");
        fluentMapper.preModelMap(codeModel);

        Client client = Mappers.getClientMapper().map(codeModel);

        // samples for Fluent Premium
        if (fluentJavaSettings.isGenerateSamples() && settings.isFluentPremium()) {
            FluentStatic.setClient(client);
            FluentStatic.setFluentJavaSettings(fluentJavaSettings);
            ExampleParser exampleParser = new ExampleParser();
            fluentPremiumExamples = client.getServiceClient().getMethodGroupClients().stream()
                    .flatMap(mg -> exampleParser.parseMethodGroup(mg).stream())
                    .collect(Collectors.toList());
        }

        return client;
    }

    FluentJavaPackage handleTemplate(Client client) {
        JavaSettings javaSettings = JavaSettings.getInstance();

        logger.info("Java template for client model");
        FluentJavaPackage javaPackage = new FluentJavaPackage(this);

        // Service client
        String interfacePackage = ClientModelUtil.getServiceClientInterfacePackageName();
        javaPackage
                .addServiceClient(client.getServiceClient().getPackage(), client.getServiceClient().getClassName(),
                        client.getServiceClient());
        if (javaSettings.shouldGenerateClientInterfaces()) {
            javaPackage
                    .addServiceClientInterface(interfacePackage, client.getServiceClient().getInterfaceName(), client.getServiceClient());
        }

        // Service client builder
        String builderPackage = ClientModelUtil.getServiceClientBuilderPackageName(client.getServiceClient());
        String builderSuffix = ClientModelUtil.getBuilderSuffix();
        String builderName = client.getServiceClient().getInterfaceName() + builderSuffix;
        javaPackage.addServiceClientBuilder(builderPackage,
                builderName, new ClientBuilder(builderName, client.getServiceClient()));

        if (javaSettings.shouldGenerateSyncAsyncClients()) {
            List<AsyncSyncClient> asyncClients = new ArrayList<>();
            List<AsyncSyncClient> syncClients = new ArrayList<>();
            ClientModelUtil.getAsyncSyncClients(client.getServiceClient(), asyncClients, syncClients);

            if (!javaSettings.isFluentLite()) {
                // fluent lite only expose sync client
                for (AsyncSyncClient asyncClient : asyncClients) {
                    javaPackage.addAsyncServiceClient(asyncClient.getPackageName(), asyncClient);
                }
            }

            for (AsyncSyncClient syncClient : syncClients) {
                javaPackage.addSyncServiceClient(syncClient.getPackageName(), syncClient);
            }
        }

        // Method group
        for (MethodGroupClient methodGroupClient : client.getServiceClient().getMethodGroupClients()) {
            javaPackage.addMethodGroup(methodGroupClient.getPackage(), methodGroupClient.getClassName(), methodGroupClient);
            if (javaSettings.shouldGenerateClientInterfaces()) {
                javaPackage.addMethodGroupInterface(interfacePackage, methodGroupClient.getInterfaceName(), methodGroupClient);
            }
        }

        // Response
        for (ClientResponse response : client.getResponseModels()) {
            javaPackage.addClientResponse(response.getPackage(), response.getName(), response);
        }

        // Client model
        for (ClientModel model : client.getModels()) {
            javaPackage.addModel(model.getPackage(), model.getName(), model);
        }

        // Enum
        for (EnumType enumType : client.getEnums()) {
            javaPackage.addEnum(enumType.getPackage(), enumType.getName(), enumType);
        }

        // XML sequence wrapper
        for (XmlSequenceWrapper xmlSequenceWrapper : client.getXmlSequenceWrappers()) {
            javaPackage.addXmlSequenceWrapper(xmlSequenceWrapper.getPackage(),
                    xmlSequenceWrapper.getWrapperClassName(), xmlSequenceWrapper);
        }

        // Exception
        for (ClientException exception : client.getExceptions()) {
            javaPackage.addException(exception.getPackage(), exception.getName(), exception);
        }

        // Package-info
        for (PackageInfo packageInfo : client.getPackageInfos()) {
            javaPackage.addPackageInfo(packageInfo.getPackage(), "package-info", packageInfo);
        }

        // Samples
        if (fluentPremiumExamples != null) {
            for (FluentExample example : fluentPremiumExamples) {
                javaPackage.addSample(example);
            }
        }

        return javaPackage;
    }

    FluentClient handleFluentLite(CodeModel codeModel, Client client, FluentJavaPackage javaPackage) {
        FluentJavaSettings fluentJavaSettings = this.getFluentJavaSettings();
        JavaSettings javaSettings = JavaSettings.getInstance();

        FluentClient fluentClient = null;

        // Fluent Lite
        if (javaSettings.isFluentLite()) {
            final boolean isSdkIntegration = fluentJavaSettings.isSdkIntegration();
            FluentStatic.setFluentJavaSettings(fluentJavaSettings);
            FluentStatic.setClient(client);

            logger.info("Process for Fluent Lite, SDK integration {}", (isSdkIntegration ? "enabled" : "disabled"));

            fluentClient = this.getFluentMapper().map(codeModel, client);

            // project
            FluentProject project = new FluentProject(fluentClient);
            if (isSdkIntegration) {
                project.integrateWithSdk();
            }

            // Fluent manager
            javaPackage.addFluentManager(fluentClient.getManager(), project);

            // Fluent resource models
            for (FluentResourceModel model : fluentClient.getResourceModels()) {
                javaPackage.addFluentResourceModel(model);
            }

            // Fluent resource collections
            for (FluentResourceCollection collection : fluentClient.getResourceCollections()) {
                javaPackage.addFluentResourceCollection(collection);
            }

            // Utils
            javaPackage.addUtils();

            // module-info
            javaPackage.addModuleInfo(fluentClient.getModuleInfo());

            // POM
            if (javaSettings.shouldRegeneratePom()) {
                Pom pom = new FluentPomMapper().map(project);
                javaPackage.addPom(fluentJavaSettings.getPomFilename(), pom);
            }

            // Samples
            List<JavaFile> sampleJavaFiles = new ArrayList<>();
            for (FluentExample example : fluentClient.getExamples()) {
                sampleJavaFiles.add(javaPackage.addSample(example));
            }

            // Readme and Changelog
            if (isSdkIntegration) {
                javaPackage.addReadmeMarkdown(project);
                javaPackage.addChangelogMarkdown(project.getChangelog());
                if (fluentJavaSettings.isGenerateSamples() && project.getSdkRepositoryUri().isPresent()) {
                    javaPackage.addSampleMarkdown(fluentClient.getExamples(), sampleJavaFiles);
                }
            }
        }

        return fluentClient;
    }

    private void clear() {
        JavaSettings.clear();
        fluentJavaSettings = null;
        fluentMapper = null;
        fluentPremiumExamples = null;
    }

    private FluentJavaSettings getFluentJavaSettings() {
        if (fluentJavaSettings == null) {
            fluentJavaSettings = new FluentJavaSettings(this);
        }
        return fluentJavaSettings;
    }

    protected FluentMapper getFluentMapper() {
        if (fluentMapper == null) {
            // use fluent mapper and template
            Mappers.setFactory(new FluentMapperFactory());
            Templates.setFactory(new FluentTemplateFactory());

            FluentJavaSettings fluentJavaSettings = getFluentJavaSettings();
            CodeNamer.setFactory(new FluentNamerFactory(fluentJavaSettings));

            fluentMapper = new FluentMapper(fluentJavaSettings);
        }
        return fluentMapper;
    }
}
