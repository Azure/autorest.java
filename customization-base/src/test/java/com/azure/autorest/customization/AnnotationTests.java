// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization;

import com.azure.autorest.customization.implementation.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.UUID;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for mutating annotations.
 */
public class AnnotationTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnnotationTests.class);
    private static final Pattern WHITESPACE = Pattern.compile("\\s*");


    Path workingDir;

    @BeforeEach
    public void setup() throws IOException {
        workingDir = Files.createTempDirectory("removeComplexAnnotation" + UUID.randomUUID());
    }

    @AfterEach
    public void teardown() {
        Utils.deleteDirectory(workingDir.toFile());
    }

    @Test
    public void addAnnotation() {

    }

    @Test
    public void removeSimpleAnnotation() {

    }

    @Test
    public void removeComplexAnnotation() throws URISyntaxException {
        final String fileName = "src/main/java/AnnotationTests/RemoveComplexAnnotation.java";
        final String fileContent = String.join(System.lineSeparator(),
            "public class RemoveComplexAnnotation {",
            "    @JsonTypeInfo(",
            "        use = JsonTypeInfo.Id.NAME,",
            "        include = JsonTypeInfo.As.PROPERTY,",
            "        property = \"@odata.type\",",
            "        defaultImpl = RemoveComplexAnnotation.class,",
            "        visible = true)",
            "    public void methodWithComplexAnnotation() {",
            "    }",
            "}");
        final String pathToLanguageServerPlugin = Paths.get(AnnotationTests.class.getResource("").toURI())
            .resolve("../../../../../../../postprocessor")
            .normalize()
            .toString();

        final String expectedFileContent = String.join(System.lineSeparator(),
            "public class RemoveComplexAnnotation {",
            "    public void methodWithComplexAnnotation() {",
            "    }",
            "}");

        Customization customization = new Customization() {
            @Override
            public void customize(LibraryCustomization libraryCustomization, Logger logger) {
                MethodCustomization methodCustomization = libraryCustomization.getPackage("AnnotationTests")
                    .getClass("RemoveComplexAnnotation")
                    .getMethod("methodWithComplexAnnotation");

                methodCustomization.removeAnnotation("@JsonTypeInfo");

                assertEquals(standardizeFileForComparison(expectedFileContent),
                    standardizeFileForComparison(libraryCustomization.getRawEditor()
                        .getFileContent(methodCustomization.getFileName())));
            }
        };

        customization.run(pathToLanguageServerPlugin, Collections.singletonMap(fileName, fileContent), LOGGER);
    }

    private static String standardizeFileForComparison(String content) {
        return WHITESPACE.matcher(content).replaceAll("");
    }
}
