package com.azure.autorest;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.Message;
import com.azure.autorest.extension.base.model.codemodel.ChoiceSchema;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.OperationGroup;
import com.azure.autorest.extension.base.model.codemodel.SealedChoiceSchema;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaFileFactory;
import com.azure.autorest.model.javamodel.JavaPackage;
import com.azure.autorest.transformer.Transformer;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class Javagen extends NewPlugin {
    public Javagen(Connection connection, String plugin, String sessionId) {
        super(connection, plugin, sessionId);
    }

    @Override
    public boolean processInternal() {
        List<String> files = listInputs().stream().filter(s -> s.contains("no-tags")).collect(Collectors.toList());
        if (files.size() != 1)
        {
            throw new RuntimeException(String.format("Generator received incorrect number of inputs: %s : %s}", files.size(), String.join(", ", files)));
        }
        String file = readFile(files.get(0));
        try {
            File tempFile = new File("tempfile.json");
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
        System.err.println("Parsed into code model " + codeModel);
        Message info = new Message();
        info.setChannel("information");
        info.setText("generating file: data.json");
        message(info);
        JavaFileFactory factory = new JavaFileFactory(JavaSettings.getInstance());
        JavaPackage javaPackage = new JavaPackage();
        codeModel = new Transformer().transform(codeModel);
        for (ChoiceSchema choiceSchema : codeModel.getSchemas().getChoices()) {
            IType iType = Mappers.getChoiceMapper().map(choiceSchema);
            if (iType != ClassType.String) {
                EnumType enumType = (EnumType) iType;
                javaPackage.addEnum(enumType.getPackage(), enumType.getName(), enumType);
            }
        }
        for (SealedChoiceSchema choiceSchema : codeModel.getSchemas().getSealedChoices()) {
            IType iType = Mappers.getSealedChoiceMapper().map(choiceSchema);
            if (iType != ClassType.String) {
                EnumType enumType = (EnumType) iType;
                javaPackage.addEnum(enumType.getPackage(), enumType.getName(), enumType);
            }
        }
        for (ObjectSchema objectSchema : codeModel.getSchemas().getObjects()) {
            ClientModel model = Mappers.getModelMapper().map(objectSchema);
            javaPackage.addModel(model.getPackage(), model.getName(), model);
        }
        for (OperationGroup operationGroup : codeModel.getOperationGroups()) {
            operationGroup.setCodeModel(codeModel);
            MethodGroupClient methodGroupClient = Mappers.getMethodGroupMapper().map(operationGroup);
            javaPackage.addMethodGroup(methodGroupClient.getPackage(), methodGroupClient.getClassName(), methodGroupClient);
            if (JavaSettings.getInstance().shouldGenerateClientInterfaces()) {
                javaPackage.addMethodGroupInterface(methodGroupClient.getInterfaceName(), methodGroupClient);
            }
        }
        for (JavaFile javaFile : javaPackage.getJavaFiles()) {
            writeFile(javaFile.getFilePath(), javaFile.getContents().toString(), null);
        }
        writeFile("data.json", "{\"output\": \"" + codeModel.getInfo().getTitle() + "\"}", null);
        return true;
    }
}
