package com.azure.autorest.customization;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassCustomizationTests {
    private static final Pattern WHITESPACE = Pattern.compile("\\s*");
    private static final Logger LOGGER = LoggerFactory.getLogger(AnnotationTests.class);

    @Test
    public void testStaticCodeBlock() throws URISyntaxException {

        final String fileName = "src/main/java/foo/Foo.java";
        final String fileContent = String.join(System.lineSeparator(),
                "public class Foo {",
                "}");

        final String expectedFileContent = String.join(System.lineSeparator(),
                "public class Foo {",
                "    static { String a = \"foo\"; }",
                "}");

        Customization customization = new Customization() {
            @Override
            public void customize(LibraryCustomization libraryCustomization, Logger logger) {
                ClassCustomization classCustomization = libraryCustomization.getPackage("foo")
                        .getClass("Foo");

                classCustomization.addStaticBlock("static { String a = \"foo\"; }");

                assertEquals(standardizeFileForComparison(expectedFileContent),
                        standardizeFileForComparison(libraryCustomization.getRawEditor()
                                .getFileContent(classCustomization.getFileName())));
            }
        };

        customization.run(null, Collections.singletonMap(fileName, fileContent), LOGGER);
    }

    @Test
    public void testStaticCodeBlockWithImports() throws URISyntaxException {

        final String fileName = "src/main/java/foo/Foo.java";
        final String fileContent = String.join(System.lineSeparator(),
                "public class Foo {",
                "    private String foo; ",
                "    public Foo(String foo) { ",
                "       this.foo = foo;",
                "    }",
                "}");

        final String expectedFileContent = String.join(System.lineSeparator(),
                "import com.azure.core.util.BinaryData;",
                "public class Foo {",
                "    private String foo;",
                "    static { String a = \"foo\"; }",
                "    public Foo(String foo) {",
                "      this.foo = foo;",
                "   }",
                "}");

        Customization customization = new Customization() {
            @Override
            public void customize(LibraryCustomization libraryCustomization, Logger logger) {
                ClassCustomization classCustomization = libraryCustomization.getPackage("foo")
                        .getClass("Foo");

                classCustomization.addStaticBlock("String a = \"foo\";", Arrays.asList("com.azure.core" +
                        ".util.BinaryData"));

                assertEquals(standardizeFileForComparison(expectedFileContent),
                        standardizeFileForComparison(libraryCustomization.getRawEditor()
                                .getFileContent(classCustomization.getFileName())));
            }
        };

        customization.run(null, Collections.singletonMap(fileName, fileContent), LOGGER);
    }

    private static String standardizeFileForComparison(String content) {
        return WHITESPACE.matcher(content).replaceAll("");
    }
}
