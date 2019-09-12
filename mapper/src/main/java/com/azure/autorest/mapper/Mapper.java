package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.models.Message;
import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.azure.autorest.model.codemodel.CodeModel;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.BeanAccess;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Mapper extends NewPlugin {
    private final Yaml yamlMapper;

    public Mapper(Connection connection, String plugin, String sessionId) {
        super(connection, plugin, sessionId);
        Representer representer = new Representer();
        representer.getPropertyUtils().setSkipMissingProperties(true);
        representer.getPropertyUtils().setBeanAccess(BeanAccess.FIELD);
        yamlMapper = new Yaml(representer);
    }

    @Override
    public boolean processInternal() {
        List<String> files = listInputs();
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
        writeFile("data.json", "{\"output\": \"" + codeModel.info().title() + "\"}", null);
        return true;
    }
}
