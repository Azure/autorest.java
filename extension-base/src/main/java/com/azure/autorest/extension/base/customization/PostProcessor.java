package com.azure.autorest.extension.base.customization;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class PostProcessor {
    private final Map<String, String> files;

    public PostProcessor(Map<String, String> files) {
        this.files = files;
    }

    public abstract void process();

    protected void changeClassModifier(String filePath, String modifier) {
        if (files.containsKey(filePath)) {
            String content = files.get(filePath);
            String[] segments = filePath.split("/");
            String className = segments[segments.length - 1].replace(".java", "");
            content = content.replaceFirst("p[a-zA-Z0-9 ]+ class " + className, modifier + " class " + className);
            files.put(filePath, content);
        }
    }

    protected void rewriteMethod(String filePath, String methodSignature, String newMethodSignature, String methodContent) {
        if (files.containsKey(filePath)) {
            String content = files.get(filePath);
            content = content.replaceFirst(methodSignature + " \\{.+ {8}}",
                    newMethodSignature + " {\n" + methodContent + "\n        }");
            files.put(filePath, content);
        }
    }

    protected Map<String, String> getFiles() {
        return files;
    }
}
