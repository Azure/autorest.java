package com.azure.autorest.postprocessor.customization;

import com.azure.autorest.postprocessor.ls.models.Location;
import com.azure.autorest.postprocessor.ls.models.SymbolInformation;
import com.azure.autorest.postprocessor.ls.models.SymbolKind;
import com.azure.autorest.postprocessor.ls.models.TextEdit;
import com.azure.autorest.postprocessor.ls.models.WorkspaceEdit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class Customization {
    private final Path tempDirWithPrefix;
    private final Map<String, String> files;
    private JDTLanguageClient languageClient;

    public Customization(Map<String, String> files) {
        this.files = files;
        try {
            tempDirWithPrefix = Files.createTempDirectory("temp");

            for (Map.Entry<String, String> file : files.entrySet()) {
                Path filePath = Paths.get(tempDirWithPrefix.toString(), file.getKey());
                if (!filePath.toFile().getParentFile().exists()) {
                    filePath.toFile().getParentFile().mkdirs();
                }
                filePath.toFile().createNewFile();
                FileOutputStream stream = new FileOutputStream(filePath.toFile());
                stream.write(file.getValue().getBytes(StandardCharsets.UTF_8));
                stream.close();
            }

            InputStream pomStream = Customization.class.getResourceAsStream("/pom.xml");
            byte[] buffer = new byte[pomStream.available()];
            pomStream.read(buffer);
            File pomFile = Paths.get(tempDirWithPrefix.toString(), "pom.xml").toFile();
            pomFile.createNewFile();
            FileOutputStream stream = new FileOutputStream(pomFile);
            stream.write(buffer);
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> run() {
        try {
            languageClient = new JDTLanguageClient(tempDirWithPrefix.toString());
            languageClient.initialize();
            customize();
            Files.deleteIfExists(tempDirWithPrefix);
            return files;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            languageClient.exit();
        }
    }

    public abstract void customize();

    public void renameClass(String packageName, String className, String newName) {
        String packagePath = packageName.replace(".", "/");
        Optional<SymbolInformation> symbolInformation = languageClient.findWorkspaceSymbol(className)
                .stream().filter(si -> si.getLocation().getUri().toString().endsWith(packagePath + "/" + className + ".java"))
                .findFirst();

        if (symbolInformation.isPresent()) {
            WorkspaceEdit workspaceEdit = languageClient.renameSymbol(symbolInformation.get().getLocation().getUri(),
                    symbolInformation.get().getLocation().getRange().getStart(), newName);
            for (Map.Entry<URI, List<TextEdit>> edit : workspaceEdit.getChanges().entrySet()) {
                int i = edit.getKey().toString().indexOf("src/main/java/");
                String oldEntry = edit.getKey().toString().substring(i);
                if (files.containsKey(oldEntry)) {
                    String content = files.get(oldEntry);
                    StringBuilder newContent = new StringBuilder();
                    for (TextEdit textEdit : edit.getValue()) {
                        newContent.append(content.substring(0, textEdit.getRange().getStart()))
                        content = content.substring(0, edit.getValue().)
                    }
                }
            }
        }
    }
}
