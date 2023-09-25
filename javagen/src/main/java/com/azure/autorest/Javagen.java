// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.codemodel.ApiVersion;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.mapper.PomMapper;
import com.azure.autorest.model.clientmodel.AsyncSyncClient;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.model.clientmodel.ClientBuilder;
import com.azure.autorest.model.clientmodel.ClientException;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModels;
import com.azure.autorest.model.clientmodel.ClientResponse;
import com.azure.autorest.model.clientmodel.ClientMethodExample;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.PackageInfo;
import com.azure.autorest.model.clientmodel.Pom;
import com.azure.autorest.model.clientmodel.ProtocolExample;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.clientmodel.ServiceVersion;
import com.azure.autorest.model.clientmodel.TestContext;
import com.azure.autorest.model.clientmodel.UnionModels;
import com.azure.autorest.model.clientmodel.XmlSequenceWrapper;
import com.azure.autorest.model.javamodel.JavaPackage;
import com.azure.autorest.model.projectmodel.Project;
import com.azure.autorest.model.projectmodel.TextFile;
import com.azure.autorest.model.xmlmodel.XmlFile;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.autorest.util.SchemaUtil;
import com.azure.core.util.CoreUtils;
import com.google.googlejavaformat.java.Formatter;
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

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
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
        this.clear();

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
            JavaPackage javaPackage = writeToTemplates(codeModel, client, settings, true);

            //Step 4: Print to files
            Map<String, String> formattedFiles = new ConcurrentHashMap<>();
            Formatter formatter = new Formatter();

            // Formatting Java source files can be expensive but can be run in parallel.
            // Submit each file for formatting as a task on the common ForkJoinPool and then wait until all tasks
            // complete.
            AtomicBoolean failedFormatting = new AtomicBoolean();
            javaPackage.getJavaFiles().parallelStream().forEach(javaFile -> {
                String formattedSource = javaFile.getContents().toString();
                if (!settings.isSkipFormatting()) {
                    try {
                        formattedSource = formatter.formatSourceAndFixImports(formattedSource);
                    } catch (Exception e) {
                        logger.error("Unable to format output file " + javaFile.getFilePath(), e);
                        failedFormatting.set(true);
                    }
                }

                formattedFiles.put(javaFile.getFilePath(), formattedSource);
            });

            if (failedFormatting.get()) {
                throw new RuntimeException("Failed to format Java files.");
            }

            // Then for each formatted file write the file. This is done synchronously as there is potential race
            // conditions that can lead to deadlocking.
            formattedFiles.forEach((filePath, formattedSource) -> writeFile(filePath, formattedSource, null));

            for (XmlFile xmlFile : javaPackage.getXmlFiles()) {
                writeFile(xmlFile.getFilePath(), xmlFile.getContents().toString(), null);
            }
            for (TextFile textFile : javaPackage.getTextFiles()) {
                writeFile(textFile.getFilePath(), textFile.getContents(), null);
            }

            String artifactId = ClientModelUtil.getArtifactId();
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
        return newYaml.loadAs(file, CodeModel.class);
    }

    protected JavaPackage writeToTemplates(CodeModel codeModel, Client client, JavaSettings settings,
                                           boolean generateSwaggerMarkdown) {
        JavaPackage javaPackage = new JavaPackage(this);
        // Service client
        if (CoreUtils.isNullOrEmpty(client.getServiceClients())) {
            javaPackage.addServiceClient(client.getServiceClient().getPackage(),
                client.getServiceClient().getClassName(), client.getServiceClient());
        } else {
            // multi-client from TypeSpec
            for (ServiceClient serviceClient : client.getServiceClients()) {
                javaPackage.addServiceClient(serviceClient.getPackage(), serviceClient.getClassName(), serviceClient);
            }
        }

        if (settings.isGenerateClientInterfaces()) {
            javaPackage.addServiceClientInterface(client.getServiceClient().getInterfaceName(),
                client.getServiceClient());
        }

        // Async/sync service clients
        for (AsyncSyncClient asyncClient : client.getAsyncClients()) {
            javaPackage.addAsyncServiceClient(asyncClient.getPackageName(), asyncClient);
        }
        for (AsyncSyncClient syncClient : client.getSyncClients()) {
            boolean syncClientWrapAsync = settings.isSyncClientWrapAsyncClient()
                // HLC could have sync method that is harder to convert, e.g. Flux<ByteBuffer> -> InputStream
                && settings.isDataPlaneClient()
                // 1-1 match of SyncClient and AsyncClient
                && client.getAsyncClients().size() == client.getSyncClients().size();
            javaPackage.addSyncServiceClient(syncClient.getPackageName(), syncClient, syncClientWrapAsync);
        }

        // Service client builder
        for (ClientBuilder clientBuilder : client.getClientBuilders()) {
            javaPackage.addServiceClientBuilder(clientBuilder);
        }

        // Method group
        for (MethodGroupClient methodGroupClient : client.getServiceClient().getMethodGroupClients()) {
            javaPackage.addMethodGroup(methodGroupClient.getPackage(), methodGroupClient.getClassName(), methodGroupClient);
            if (settings.isGenerateClientInterfaces()) {
                javaPackage.addMethodGroupInterface(methodGroupClient.getInterfaceName(), methodGroupClient);
            }
        }

        // Sample
        if (settings.isDataPlaneClient() && settings.isGenerateSamples()) {
            for (ProtocolExample protocolExample : client.getProtocolExamples()) {
                javaPackage.addProtocolExamples(protocolExample);
            }
            for (ClientMethodExample clientMethodExample : client.getClientMethodExamples()) {
                javaPackage.addClientMethodExamples(clientMethodExample);
            }
        }

        // Test
        if (settings.isDataPlaneClient() && settings.isGenerateTests()) {
            if (!client.getSyncClients().isEmpty() && client.getSyncClients().iterator().next().getClientBuilder() != null) {
                List<ServiceClient> serviceClients = client.getServiceClients();
                if (CoreUtils.isNullOrEmpty(serviceClients)) {
                    serviceClients = Collections.singletonList(client.getServiceClient());
                }
                TestContext testContext = new TestContext(serviceClients, client.getSyncClients());

                // base test class
                javaPackage.addProtocolTestBase(testContext);

                // test cases as Disabled
                if (!client.getProtocolExamples().isEmpty()) {
                    client.getProtocolExamples().forEach(protocolExample -> javaPackage.addProtocolTest(new TestContext<>(testContext, protocolExample)));
                }
                if (!client.getClientMethodExamples().isEmpty()) {
                    client.getClientMethodExamples().forEach(clientMethodExample -> javaPackage.addClientMethodTest(new TestContext<>(testContext, clientMethodExample)));
                }
            }
        }

        // GraalVM config
        if (settings.isGenerateGraalVmConfig()) {
            javaPackage.addGraalVmConfig(Project.AZURE_GROUP_ID, ClientModelUtil.getArtifactId(), client.getGraalVmConfig());
        }

        // Service version
        if (settings.isDataPlaneClient()) {
            String packageName = settings.getPackage();
            if (CoreUtils.isNullOrEmpty(client.getServiceClients())) {
                List<String> serviceVersions = settings.getServiceVersions();
                if (CoreUtils.isNullOrEmpty(serviceVersions)) {
                    List<String> apiVersions = ClientModelUtil.getApiVersions(codeModel);
                    if (!CoreUtils.isNullOrEmpty(apiVersions)) {
                        serviceVersions = apiVersions;
                    } else {
                        throw new IllegalArgumentException("'api-version' not found. Please configure 'serviceVersions' option.");
                    }
                }

                String serviceName;
                if (settings.getServiceName() == null) {
                    serviceName = client.getServiceClient().getInterfaceName();
                } else {
                    serviceName = settings.getServiceName();
                }
                String className = ClientModelUtil.getServiceVersionClassName(ClientModelUtil.getClientInterfaceName(codeModel));
                javaPackage.addServiceVersion(packageName, new ServiceVersion(className, serviceName, serviceVersions));
            } else {
                // multi-client from TypeSpec
                for (com.azure.autorest.extension.base.model.codemodel.Client client1 : codeModel.getClients()) {
                    if (client1.getServiceVersion() != null) {
                        javaPackage.addServiceVersion(packageName,
                                new ServiceVersion(
                                        SchemaUtil.getJavaName(client1.getServiceVersion()),
                                        client1.getServiceVersion().getLanguage().getDefault().getDescription(),
                                        client1.getApiVersions().stream().map(ApiVersion::getVersion).collect(Collectors.toList())));
                    }
                }
            }
        }

        writeClientModels(client, javaPackage, settings);

        // Unit tests on client model
        if (settings.isGenerateTests() && (!settings.isDataPlaneClient() || settings.isGenerateModels())) {
            for (ClientModel model : client.getModels()) {
                if (!model.isStronglyTypedHeader()) {
                    javaPackage.addModelUnitTest(model);
                }
            }
        }

        // Package-info
        for (PackageInfo packageInfo : client.getPackageInfos()) {
            javaPackage.addPackageInfo(packageInfo.getPackage(), "package-info", packageInfo);
        }

        if (settings.isDataPlaneClient()) {
            Project project = new Project(client, ClientModelUtil.getApiVersions(codeModel));
            if (settings.isSdkIntegration()) {
                project.integrateWithSdk();
            }

            client.getModuleInfo().checkForAdditionalDependencies(client.getModels());
            project.checkForAdditionalDependencies(client.getModels());

            // Module-info
            javaPackage.addModuleInfo(client.getModuleInfo());

            // POM
            if (settings.isRegeneratePom()) {
                Pom pom = new PomMapper().map(project);
                javaPackage.addPom("pom.xml", pom);
            }

            // Readme, Changelog
            if (settings.isSdkIntegration()) {
                javaPackage.addReadmeMarkdown(project);
                if (generateSwaggerMarkdown) {
                    javaPackage.addSwaggerReadmeMarkdown(project);
                }
                javaPackage.addChangelogMarkdown(project);

                // test proxy asserts.json
                javaPackage.addTestProxyAssetsJson(project);

                // Blank readme sample
                javaPackage.addProtocolExamplesBlank();
            }
        }
        return javaPackage;
    }

    protected void writeClientModels(Client client, JavaPackage javaPackage, JavaSettings settings) {
        if (!settings.isDataPlaneClient() || settings.isGenerateModels()) {
            // Client model
            for (ClientModel model : client.getModels()) {
                javaPackage.addModel(model.getPackage(), model.getName(), model);
            }

            // Enum
            for (EnumType enumType : client.getEnums()) {
                javaPackage.addEnum(enumType.getPackage(), enumType.getName(), enumType);
            }

            // Response
            for (ClientResponse response : client.getResponseModels()) {
                javaPackage.addClientResponse(response.getPackage(), response.getName(), response);
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
    }

    private void clear() {
        ClientModels.getInstance().clear();
        UnionModels.getInstance().clear();
        JavaSettings.clear();
    }

    public Logger getLogger() {
        return this.logger;
    }
}
