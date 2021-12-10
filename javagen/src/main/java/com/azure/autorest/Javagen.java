// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.mapper.PomMapper;
import com.azure.autorest.model.clientmodel.AsyncSyncClient;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.model.clientmodel.ClientException;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientResponse;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.PackageInfo;
import com.azure.autorest.model.clientmodel.Pom;
import com.azure.autorest.model.clientmodel.ProtocolExample;
import com.azure.autorest.model.clientmodel.ServiceVersion;
import com.azure.autorest.model.clientmodel.XmlSequenceWrapper;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaPackage;
import com.azure.autorest.model.projectmodel.Project;
import com.azure.autorest.model.projectmodel.TextFile;
import com.azure.autorest.model.xmlmodel.XmlFile;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.autorest.util.CodeNamer;
import com.azure.core.util.CoreUtils;
import com.google.googlejavaformat.java.Formatter;
import org.slf4j.Logger;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class Javagen extends NewPlugin {
    private final Logger logger = new PluginLogger(this, Javagen.class);
    protected static Javagen instance;

    public Javagen(Connection connection, String plugin, String sessionId) {
        super(connection, plugin, sessionId);
        instance = this;
    }

    public static Javagen getPluginInstance() {
        return instance;
    }

    @Override
    public boolean processInternal() {
        JavaSettings settings = JavaSettings.getInstance();

        List<String> allFiles = listInputs();
        List<String> files = allFiles.stream().filter(s -> s.contains("no-tags")).collect(Collectors.toList());
        if (files.size() != 1) {
            throw new RuntimeException(String.format("Generator received incorrect number of inputs: %s : %s}", files.size(), String.join(", ", files)));
        }

        try {
            // Step 1: Parse input yaml as CodeModel
            String fileName = files.get(0);
            CodeModel codeModel = parseCodeModel(fileName);

            // Step 2: Map
            Client client = Mappers.getClientMapper().map(codeModel);

            // Step 3: Write to templates
            JavaPackage javaPackage = writeToTemplates(settings, codeModel, client);

            //Step 4: Print to files
            Formatter formatter = new Formatter();
            for (JavaFile javaFile : javaPackage.getJavaFiles()) {
                String content = javaFile.getContents().toString();
                if (!settings.isSkipFormatting()) {
                    try {
                        content = formatter.formatSourceAndFixImports(content);
                    } catch (Exception e) {
                        logger.error("Unable to format output file " + javaFile.getFilePath(), e);
                        return false;
                    }
                }
                writeFile(javaFile.getFilePath(), content, null);
            }
            for (XmlFile xmlFile : javaPackage.getXmlFiles()) {
                writeFile(xmlFile.getFilePath(), xmlFile.getContents().toString(), null);
            }
            for (TextFile textFile : javaPackage.getTextFiles()) {
                writeFile(textFile.getFilePath(), textFile.getContents(), null);
            }

            String artifactId = settings.getArtifactId();
            if (!CoreUtils.isNullOrEmpty(artifactId)) {
                writeFile("src/main/resources/" + artifactId + ".properties",
                        "name=${project.artifactId}\nversion=${project" + ".version}\n", null);
            }
        } catch (Exception ex) {
            logger.error("Failed to generate code.", ex);
            return false;
        }
        return true;
    }

    CodeModel parseCodeModel(String fileName) {
        String file = readFile(fileName);
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

        LoaderOptions loaderOptions = new LoaderOptions();
        loaderOptions.setMaxAliasesForCollections(Integer.MAX_VALUE);
        Yaml newYaml = new Yaml(new Constructor(loaderOptions), representer, new DumperOptions(), loaderOptions);
        CodeModel codeModel = newYaml.loadAs(file, CodeModel.class);
        return codeModel;
    }

    JavaPackage writeToTemplates(JavaSettings settings, CodeModel codeModel, Client client) {
        JavaPackage javaPackage = new JavaPackage(this);
        // Service client
        javaPackage
            .addServiceClient(client.getServiceClient().getPackage(), client.getServiceClient().getClassName(),
                client.getServiceClient());

        if (settings.shouldGenerateClientInterfaces()) {
            javaPackage
                .addServiceClientInterface(client.getServiceClient().getInterfaceName(), client.getServiceClient());
        }

        String builderSuffix = ClientModelUtil.getBuilderSuffix();
        String builderName = client.getServiceClient().getInterfaceName() + builderSuffix;
        if (!client.getServiceClient().builderDisabled()) {
            // Service client builder
            String builderPackage = ClientModelUtil.getServiceClientBuilderPackageName(client.getServiceClient());
            javaPackage.addServiceClientBuilder(builderPackage, builderName, client.getServiceClient());
        }

        List<AsyncSyncClient> syncClients = new ArrayList<>();
        if (settings.shouldGenerateSyncAsyncClients()) {
            List<AsyncSyncClient> asyncClients = new ArrayList<>();
            ClientModelUtil.getAsyncSyncClients(client.getServiceClient(), asyncClients, syncClients);

            for (AsyncSyncClient asyncClient : asyncClients) {
                javaPackage.addAsyncServiceClient(asyncClient.getPackageName(), asyncClient);
            }

            for (AsyncSyncClient syncClient : syncClients) {
                javaPackage.addSyncServiceClient(syncClient.getPackageName(), syncClient);
            }
        }

        // Method group
        for (MethodGroupClient methodGroupClient : client.getServiceClient().getMethodGroupClients()) {
            javaPackage.addMethodGroup(methodGroupClient.getPackage(), methodGroupClient.getClassName(), methodGroupClient);
            if (settings.shouldGenerateClientInterfaces()) {
                javaPackage.addMethodGroupInterface(methodGroupClient.getInterfaceName(), methodGroupClient);
            }
        }

        // Sample
        if (settings.isLowLevelClient() && settings.isGenerateSamples()) {
            Set<String> protocolExampleNameSet = new HashSet<>();

            syncClients.stream().filter(c -> c.getMethodGroupClient() != null)
                .forEach(c -> c.getMethodGroupClient().getClientMethods().stream()
                    .filter(m -> m.getType() == ClientMethodType.SimpleSyncRestResponse || m.getType() == ClientMethodType.PagingSync)
                    .forEach(m -> {
                        if (m.getProxyMethod().getExamples() != null) {
                            m.getProxyMethod().getExamples().forEach((name, example) -> {
                                String filename = CodeNamer.toPascalCase(CodeNamer.removeInvalidCharacters(name));
                                if (!protocolExampleNameSet.contains(filename)) {
                                    ProtocolExample protocolExample = new ProtocolExample(m, c, client.getServiceClient(), builderName, filename, example);
                                    javaPackage.addProtocolExamples(protocolExample);
                                    protocolExampleNameSet.add(filename);
                                }
                            });
                        }
                    }));
        }

        // Service version
        if (settings.isLowLevelClient()) {
            List<String> serviceVersions = settings.getServiceVersions();
            if (serviceVersions == null) {
                String apiVersion = ClientModelUtil.getFirstApiVersion(codeModel);
                if (apiVersion == null) {
                    throw new IllegalArgumentException("'api-version' not found. Please configure 'serviceVersions' option.");
                }
                serviceVersions = Collections.singletonList(apiVersion);
            }

            String packageName = settings.getPackage();
            String serviceName;
            if (settings.getServiceName() == null) {
                serviceName = client.getServiceClient().getInterfaceName();
            } else {
                serviceName = settings.getServiceName();
            }
            String className = ClientModelUtil.getServiceVersionClassName(client.getServiceClient().getInterfaceName());
            javaPackage.addServiceVersion(packageName, new ServiceVersion(className, serviceName, serviceVersions));
        }

        if (!settings.isLowLevelClient()) {
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

            // Exception
            for (ClientException exception : client.getExceptions()) {
                javaPackage.addException(exception.getPackage(), exception.getName(), exception);
            }

            // XML sequence wrapper
            for (XmlSequenceWrapper xmlSequenceWrapper : client.getXmlSequenceWrappers()) {
                javaPackage.addXmlSequenceWrapper(xmlSequenceWrapper.getPackage(),
                    xmlSequenceWrapper.getWrapperClassName(), xmlSequenceWrapper);
            }
        }

        // Package-info
        for (PackageInfo packageInfo : client.getPackageInfos()) {
            javaPackage.addPackageInfo(packageInfo.getPackage(), "package-info", packageInfo);
        }

        if (settings.isLowLevelClient()) {
            Project project = new Project(client, ClientModelUtil.getFirstApiVersion(codeModel));
            if (settings.isSdkIntegration()) {
                project.integrateWithSdk();
            }

            // Module-info
            javaPackage.addModuleInfo(client.getModuleInfo());

            // POM
            if (settings.shouldRegeneratePom()) {
                Pom pom = new PomMapper().map(project);
                javaPackage.addPom("pom.xml", pom);
            }

            // Readme, Changelog
            if (settings.isSdkIntegration()) {
                javaPackage.addReadmeMarkdown(project);
                javaPackage.addSwaggerReadmeMarkdown(project);
                javaPackage.addChangelogMarkdown(project);

                // Blank test case
                javaPackage.addProtocolTestBlank(client.getServiceClient());

                // Blank readme sample
                javaPackage.addProtocolExamplesBlank();
            }
        }
        return javaPackage;
    }
}
