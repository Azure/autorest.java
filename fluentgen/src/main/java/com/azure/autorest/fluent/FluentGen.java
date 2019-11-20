package com.azure.autorest.fluent;

import com.azure.autorest.extension.base.jsonrpc.Connection;
import com.azure.autorest.extension.base.model.Message;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.NewPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class FluentGen extends NewPlugin {

    public FluentGen(Connection connection, String plugin, String sessionId) {
        super(connection, plugin, sessionId);
    }

    @Override
    public boolean processInternal() {
        try {
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

            // TODO

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
