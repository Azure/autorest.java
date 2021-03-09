package com.azure.autorest.android;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.model.clientmodel.AsyncSyncClient;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.model.clientmodel.ClientException;
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
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Androidgen extends NewPlugin {
    private final Logger LOGGER = new PluginLogger(this, Androidgen.class);
    private static Androidgen instance;

    public Androidgen(Connection connection, String plugin, String sessionId) {
        super(connection, plugin, sessionId);
        instance = this;
    }

    public static Androidgen getPluginInstance() {
        return instance;
    }

    @Override
    public boolean processInternal() {
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

            Yaml newYaml  = new Yaml(representer);
            CodeModel codeModel = newYaml.loadAs(file, CodeModel.class);

            // Step 2: Map
            Client client = Mappers.getClientMapper().map(codeModel);

            // Step 3: Write to templates
            JavaPackage javaPackage = new JavaPackage(this);
            // Service client
            javaPackage
                    .addServiceClient(client.getServiceClient().getPackage(), client.getServiceClient().getClassName(),
                            client.getServiceClient());

            if (JavaSettings.getInstance().shouldGenerateClientInterfaces()) {
                javaPackage
                        .addServiceClientInterface(client.getServiceClient().getInterfaceName(), client.getServiceClient());
            }

            // Service client builder
            String builderPackage = ClientModelUtil.getServiceClientBuilderPackageName(client.getServiceClient());
            String builderSuffix = ClientModelUtil.getBuilderSuffix();
            javaPackage.addServiceClientBuilder(builderPackage,
                    client.getServiceClient().getInterfaceName() + builderSuffix, client.getServiceClient());

            if (JavaSettings.getInstance().shouldGenerateSyncAsyncClients()) {
                List<AsyncSyncClient> asyncClients = new ArrayList<>();
                List<AsyncSyncClient> syncClients = new ArrayList<>();
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
                javaPackage.addMethodGroup(methodGroupClient.getPackage(), methodGroupClient.getClassName(),
                        methodGroupClient);
                if (JavaSettings.getInstance().shouldGenerateClientInterfaces()) {
                    javaPackage.addMethodGroupInterface(methodGroupClient.getInterfaceName(), methodGroupClient);
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

            // Exception
            for (ClientException exception : client.getExceptions()) {
                javaPackage.addException(exception.getPackage(), exception.getName(), exception);
            }

            // XML sequence wrapper
            for (XmlSequenceWrapper xmlSequenceWrapper : client.getXmlSequenceWrappers()) {
                javaPackage.addXmlSequenceWrapper(xmlSequenceWrapper.getPackage(),
                        xmlSequenceWrapper.getWrapperClassName(), xmlSequenceWrapper);
            }

            // Package-info
            for (PackageInfo packageInfo : client.getPackageInfos()) {
                javaPackage.addPackageInfo(packageInfo.getPackage(), "package-info", packageInfo);
            }

            // TODO: POM, Manager
            //Step 4: Print to files
            Formatter formatter = new Formatter();
            for (JavaFile javaFile : javaPackage.getJavaFiles()) {
                try {
                    String formattedSource = formatter.formatSourceAndFixImports(javaFile.getContents().toString());
                    writeFile(javaFile.getFilePath(), formattedSource, null);
                } catch (Exception e) {
                    LOGGER.error("Unable to format output file " + javaFile.getFilePath(), e);
                    return false;
                }
            }
            String artifactId = JavaSettings.getInstance().getArtifactId();
            if (!(artifactId == null || artifactId.isEmpty())) {
                writeFile("src/main/resources/" + artifactId + ".properties",
                        "name=${project.artifactId}\nversion=${project" + ".version}\n", null);
            }
        } catch (Exception ex) {
            LOGGER.error("Failed to generate code.", ex);
            return false;
        }
        return true;
    }
}
