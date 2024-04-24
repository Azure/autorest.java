// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.ProjectRoot;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * The raw editor containing the current files being customized.
 */
public final class Editor {
    private final Path rootDir;
    private final ProjectRoot project;

    /**
     * Creates an editor instance with the file contents and the root directory path.
     *
     * @param contents the map from file relative paths (starting with "src/main/java") and file contents
     * @param rootDir the root directory path containing the files
     */
    public Editor(Map<String, String> contents, Path rootDir) {
        this.rootDir = rootDir;
    }

    /**
     * Adds a new file.
     *
     * @param name the relative path of the file, starting with "src/main/java"
     * @param content the file content
     */
    public void addFile(String name, String content) {
        addOrReplaceFile(name, content, false);
    }

    /**
     * Replaces an existing file with new content.
     *
     * @param name The relative path of the file, starting with "src/main/java".
     * @param content The content of the file.
     */
    public void replaceFile(String name, String content) {
        addOrReplaceFile(name, content, true);
    }

    private void addOrReplaceFile(String name, String content, boolean isReplace) {
        Path newFilePath = Paths.get(rootDir.toString(), name);

        try {
            if (Files.notExists(newFilePath.getParent())) {
                Files.createDirectories(newFilePath.getParent());
            }

            boolean exists = Files.exists(newFilePath);
            if (!exists) {
                Files.createFile(newFilePath);
            }

            Files.writeString(newFilePath, content);


            if (!exists || isReplace) {
                files.put(name, StaticJavaParser.parse(content));
                paths.put(name, newFilePath);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Removes a file.
     *
     * @param name the relative file path, starting with "src/main/java"
     */
    public void removeFile(String name) {
        try {
            files.remove(name);
            Files.deleteIfExists(paths.get(name));
            paths.remove(name);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Gets the content of a file.
     *
     * @param name the relative path of a file, starting with "src/main/java"
     * @return the file content
     */
    public String getFileContent(String name) {
        return files.get(name).toString();
    }

    /**
     * Renames a file. This simply renames the file, without renaming any class content.
     *
     * @param fileName the original relative path of a file, starting with "src/main/java"
     * @param newName the new relative path of the file, starting with "src/main/java"
     */
    public void renameFile(String fileName, String newName) {
        try {
            files.put(newName, files.remove(fileName));
            Path path = paths.remove(fileName);
            Path newPath = Paths.get(rootDir.toString(), newName);
            Files.move(path, newPath);
            paths.put(newName, newPath);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
