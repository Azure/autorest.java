package com.azure.autorest.customization;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.body.InitializerDeclaration;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
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
                ClassCustomization classCustomization = libraryCustomization.getClass("foo", "Foo")
                    .customizeAst(ast -> ast.getClassByName("Foo").ifPresent(clazz -> clazz.addStaticInitializer()
                        .addStatement(StaticJavaParser.parseStatement("String a = \"foo\";"))));

                assertEquals(standardizeFileForComparison(expectedFileContent),
                    standardizeFileForComparison(libraryCustomization.getRawEditor()
                        .getFileContent(classCustomization.getFileName())));
            }
        };

        customization.run(Collections.singletonMap(fileName, fileContent), LOGGER);
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
            "    static { String a = \"foo\"; }",
            "    private String foo;",
            "    public Foo(String foo) {",
            "      this.foo = foo;",
            "   }",
            "}");

        Customization customization = new Customization() {
            @Override
            public void customize(LibraryCustomization libraryCustomization, Logger logger) {
                ClassCustomization classCustomization = libraryCustomization.getClass("foo", "Foo")
                    .customizeAst(ast -> {
                        ast.addImport("com.azure.core.util.BinaryData");
                        ast.getClassByName("Foo").ifPresent(clazz -> clazz.getMembers()
                            .add(0, new InitializerDeclaration(true,
                                StaticJavaParser.parseBlock("{ String a = \"foo\"; }"))));
                    });

                assertEquals(standardizeFileForComparison(expectedFileContent),
                        standardizeFileForComparison(libraryCustomization.getRawEditor()
                                .getFileContent(classCustomization.getFileName())));
            }
        };

        customization.run(Collections.singletonMap(fileName, fileContent), LOGGER);
    }

    private static String standardizeFileForComparison(String content) {
        return WHITESPACE.matcher(content).replaceAll("");
    }
}
