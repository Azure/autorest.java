package com.azure.autorest.postprocessor;

import com.azure.autorest.postprocessor.ls.JDTLanguageClient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public abstract class Customization {
    public Map<String, String> run(Map<String, String> files) {
        Path tempDirWithPrefix;

        // Populate editor
        Editor editor;
        try {
            tempDirWithPrefix = Files.createTempDirectory("temp");
            editor = new Editor(files, tempDirWithPrefix);
            InputStream pomStream = Customization.class.getResourceAsStream("/pom.xml");
            byte[] buffer = new byte[pomStream.available()];
            pomStream.read(buffer);
            editor.addFile("pom.xml", new String(buffer, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Start language client
        JDTLanguageClient languageClient = null;
        try {
            languageClient = new JDTLanguageClient(tempDirWithPrefix.toString());
            languageClient.initialize();
            customize(new LibraryCustomization(editor, languageClient));
            editor.removeFile("pom.xml");
            return editor.getContents();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            deleteDirectory(tempDirWithPrefix.toFile());
            if (languageClient != null) {
                languageClient.exit();
            }
        }
    }

    public abstract void customize(LibraryCustomization libraryCustomization);

    private void deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        directoryToBeDeleted.delete();
    }

}
