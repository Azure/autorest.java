package com.azure.autorest;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.model.clientmodel.AsyncSyncClient;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.model.clientmodel.ClientException;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientResponse;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.PackageInfo;
import com.azure.autorest.model.clientmodel.XmlSequenceWrapper;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaPackage;
import com.azure.autorest.util.ClientModelUtil;
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
import java.util.List;
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
            String file = readFile(files.get(0));
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

            // Step 2: Map
            Client client = Mappers.getClientMapper().map(codeModel);

            // Step 3: Write to templates
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
                String hostName0 = "host";
                if (client.getServiceClient().getProperties().stream().anyMatch(p -> p.getName().equals("host"))) {
                    hostName0 = "host";
                } else if (client.getServiceClient().getProperties().stream().anyMatch(p -> p.getName().equals("endpoint"))) {
                    hostName0 = "endpoint";
                }
                String hostName = hostName0;
                syncClients.stream().filter(c -> c.getMethodGroupClient() != null)
                        .forEach(c -> c.getMethodGroupClient().getClientMethods().stream()
                        .filter(m -> m.getType() == ClientMethodType.SimpleSyncRestResponse || m.getType() == ClientMethodType.PagingSync)
                        .forEach(m -> javaPackage.addProtocolExamples(m, c, builderName, hostName)));
            }

            // Service version
            if (settings.isLowLevelClient() && settings.getServiceVersions() != null) {
                String packageName = settings.getPackage();
                String serviceName;
                if (settings.getServiceName() == null) {
                    serviceName = client.getServiceClient().getInterfaceName();
                } else {
                    serviceName = settings.getServiceName().replaceAll("\\s", "");
                }
                String className = serviceName + (serviceName.endsWith("Service") ? "Version" : "ServiceVersion");
                List<String> serviceVersions = settings.getServiceVersions();
                javaPackage.addServiceVersion(packageName, serviceName, className, serviceVersions, client.getServiceClient());
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

            // Module-info
            if (settings.isLowLevelClient()) {
                javaPackage.addModuleInfo(client.getModuleInfo());
            }

            // TODO: POM, Manager
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
            String artifactId = settings.getArtifactId();
            if (!(artifactId == null || artifactId.isEmpty())) {
                writeFile("src/main/resources/" + artifactId + ".properties",
                        "name=${project.artifactId}\nversion=${project" + ".version}\n", null);
            }
        } catch (Exception ex) {
            logger.error("Failed to generate code.", ex);
            return false;
        }
        return true;
    }
}
