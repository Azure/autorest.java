/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.azure.autorest.fluent.mapper.FluentMapper;
import com.azure.autorest.fluent.mapper.FluentMapperFactory;
import com.azure.autorest.fluent.template.FluentTemplateFactory;
import com.azure.autorest.fluent.transformer.FluentTransformer;
import com.azure.autorest.fluent.util.FluentJavaSettings;
import com.azure.autorest.fluent.namer.FluentNamerFactory;
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
import com.azure.autorest.template.Templates;
import com.azure.autorest.transformer.Transformer;
import com.azure.autorest.util.CodeNamer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class FluentGen extends NewPlugin {

    private static Logger logger = LoggerFactory.getLogger(FluentGen.class);

    public FluentGen(Connection connection, String plugin, String sessionId) {
        super(connection, plugin, sessionId);
    }

    @Override
    public boolean processInternal() {
        try {
            logger.info("Read YAML");
            List<String> files = listInputs().stream().filter(s -> s.contains("no-tags")).collect(Collectors.toList());
            if (files.size() != 1) {
                throw new RuntimeException(String.format("Generator received incorrect number of inputs: %s : %s}", files.size(), String.join(", ", files)));
            }
            String file = readFile(files.get(0));
            try {
                File tempFile = new File("codemodel.yaml");
                if (!tempFile.exists()) {
                    tempFile.createNewFile();
                }
                new FileOutputStream(tempFile).write(file.getBytes(StandardCharsets.UTF_8));
            } catch (Exception e) {
                //
            }
            // Step 1: Parse
            logger.info("Parse code model");
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

            logger.info("Load fluent settings");
            // use fluent mapper and template
            Mappers.setFactory(new FluentMapperFactory());
            Templates.setFactory(new FluentTemplateFactory());
            FluentJavaSettings fluentJavaSettings = new FluentJavaSettings(this);
            CodeNamer.setFactory(new FluentNamerFactory(fluentJavaSettings));

            // Step 2: Transform
            logger.info("Transform code model");
            FluentTransformer transformer = new FluentTransformer(fluentJavaSettings);
            codeModel = transformer.preTransform(codeModel);

            codeModel = new Transformer().transform(codeModel);

            codeModel = transformer.postTransform(codeModel);

            // Step 3: Map
            logger.info("Map code model to client model");
            new FluentMapper(fluentJavaSettings).preModelMap(codeModel);

            Client client = Mappers.getClientMapper().map(codeModel);

            // Step 4: Write to templates
            logger.info("Java template for client model");
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

            // TODO: POM, Manager

            // Print to files
            logger.info("Write Java");
            for (JavaFile javaFile : javaPackage.getJavaFiles()) {
                writeFile(javaFile.getFilePath(), javaFile.getContents().toString(), null);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
