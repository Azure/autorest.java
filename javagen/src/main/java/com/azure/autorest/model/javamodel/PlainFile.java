package com.azure.autorest.model.javamodel;

import com.azure.autorest.Javagen;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

public class PlainFile {

    private Logger logger = new PluginLogger(Javagen.getPluginInstance(), PlainFile.class);

    private String filePath;

    private String content;

    public String getFilePath() {
        return filePath;
    }

    public String getContent() {
        return content;
    }

    public PlainFile(String filePath, String content) {
        this.filePath = filePath;
        this.content = content;
    }

    public PlainFile(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load from resource file and apply model.
     * @param filename filename
     * @param model if model is null, ignore it
     */
    public void loadFromResource(String filename, Map<String, String> model) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(filename);
        if (is == null) {
            logger.warn(String.format("%s is not found", filename));
            return;
        }
        String text = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
                .lines().collect(Collectors.joining(System.lineSeparator())) + System.lineSeparator();
        for (Map.Entry<String, String> e : model.entrySet()) {
            text = text.replace("${" + e.getKey() + "}", e.getValue());
        }
        content = text;
    }
}
