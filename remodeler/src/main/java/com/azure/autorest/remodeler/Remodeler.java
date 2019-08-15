package com.azure.autorest.remodeler;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.models.Message;
import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.List;

public class Remodeler extends NewPlugin {
    private final ObjectMapper yamlMapper;

    public Remodeler(Connection connection, String plugin, String sessionId) {
        super(connection, plugin, sessionId);
        yamlMapper = new ObjectMapper(new YAMLFactory());
        yamlMapper.findAndRegisterModules();
    }

    @Override
    public boolean processInternal() {
        List<String> files = listInputs();
        if (files.size() != 1)
        {
            throw new RuntimeException(String.format("Generator received incorrect number of inputs: %s : %s}", files.size(), String.join(", ", files)));
        }
        String file = readFile(files.get(0));
        if (!file.startsWith("{")) {
            // YAML
            try {
                file = MAPPER.writeValueAsString(yamlMapper.readTree(file));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Message info = new Message();
        info.setChannel("information");
        info.setText("generating file: data.json");
        message(info);
        writeFile("data.json", file, null);
        return true;
    }
}
