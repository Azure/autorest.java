// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.typespec.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {
    /**
     * Write given content to the given file under given path.
     *
     * @param outputDir output directory, will create if not exist
     * @param fileName filename
     * @param content content to write to the file
     * @return the file
     */
    public static File writeToFile(String outputDir, String fileName, String content) {
        File outputFile = Paths.get(outputDir, fileName).toAbsolutePath().toFile();
        File parentFile = outputFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try (OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(outputFile.toPath()), StandardCharsets.UTF_8)) {
            writer.write(content);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return outputFile;
    }
}
