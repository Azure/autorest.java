package com.azure.autorest;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.azure.autorest.mapper.Mappers;
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
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

public class Javagen extends NewPlugin {

    private static final Logger LOGGER = LoggerFactory.getLogger(Javagen.class);
    public Javagen(Connection connection, String plugin, String sessionId) {
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

        // Step 1: Parse
        CodeModel codeModel;
        try {
            Yaml yaml = new Yaml();
            codeModel = yaml.loadAs(file, CodeModel.class);
        } catch (Exception e) {
            System.err.println("Got an error " + e.getMessage());
            connection.sendError(1, 500, "Cannot parse input into code model: " + e.getMessage());
            return false;
        }

        // Step 2: Map
        Client client = Mappers.getClientMapper().map(codeModel);

        // Step 3: Write to templates
        JavaPackage javaPackage = new JavaPackage();
        // Service client
        javaPackage.addServieClient(client.getServiceClient().getPackage(), client.getServiceClient().getClassName(), client.getServiceClient());
        if (JavaSettings.getInstance().shouldGenerateClientInterfaces()) {
            javaPackage.addServiceClientInterface(client.getServiceClient().getInterfaceName(), client.getServiceClient());
        }

        // Service client builder
        javaPackage.addServieClientBuilder(client.getServiceClient().getPackage(), client.getServiceClient().getInterfaceName() + "Builder", client.getServiceClient());

        // Method group
        for (MethodGroupClient methodGroupClient : client.getServiceClient().getMethodGroupClients()) {
            javaPackage.addMethodGroup(methodGroupClient.getPackage(), methodGroupClient.getClassName(), methodGroupClient);
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

        // Print to files
        for (JavaFile javaFile : javaPackage.getJavaFiles()) {
            writeFile(javaFile.getFilePath(), javaFile.getContents().toString(), null);
        }
        return true;
    }
}
