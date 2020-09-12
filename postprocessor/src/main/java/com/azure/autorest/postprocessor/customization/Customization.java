package com.azure.autorest.postprocessor.customization;

import org.openrewrite.java.JavaParser;
import org.openrewrite.java.tree.J;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Customization {
    private List<J.CompilationUnit> compilationUnits;

    public Customization(Map<String, String> files) {
        List<Path> sourcePaths = new ArrayList<>();
        Path tempDirWithPrefix = null;
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
                sourcePaths.add(filePath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        compilationUnits = JavaParser.fromJavaVersion().classpath(System.getProperty("java.class.path")).build().parse(sourcePaths, null);
    }

    public Map<String, String> getUpdatedFiles() {
        Map<String, String> updatedFiles = new HashMap<>();
        for (J.CompilationUnit compilationUnit : compilationUnits) {
            updatedFiles.put(compilationUnit.getSourcePath().replaceFirst(".*[\\\\/]src[\\\\/]main[\\\\/]java", "src/main/java"), compilationUnit.print());
        }
        return updatedFiles;
    }

    public abstract void customize();

    public Refactor refactor() {
        return new Refactor(compilationUnits);
    }

}
