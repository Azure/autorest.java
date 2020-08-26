package com.azure.autorest.extension.base.postprocessor;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public abstract class PostProcessor {
    private final Map<String, String> files;

    public PostProcessor(Map<String, String> files) {
        this.files = files;
    }

    public abstract void process();

    protected void moveFile(String originalPath, String newPath) {
        if (files.containsKey(originalPath)) {
            String content = files.remove(originalPath);
            files.put(newPath, content);
        }
    }

    protected void moveFiles(List<String> fileNames, String originalPackage, String destinationPackage) {
        for (String file : fileNames) {
            moveFile(Paths.get(originalPackage, file).toString(), Paths.get(destinationPackage, file).toString());
        }
    }

    protected void changeClassModifier(String filePath, String modifier) {
        if (files.containsKey(filePath)) {
            String content = files.get(filePath);
            String[] segments = filePath.split("/");
            String className = segments[segments.length - 1].replace(".java", "");
            content = content.replaceFirst("p[a-zA-Z0-9 ]+ class " + className, modifier + " class " + className);
            files.put(filePath, content);
        }
    }

    protected Map<String, String> getFiles() {
        return files;
    }
}
