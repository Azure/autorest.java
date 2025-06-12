// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import org.slf4j.Logger;

import java.util.UUID;

/**
 * README samples for post-code generation.
 */
@SuppressWarnings({"unused", "InnerClassMayBeStatic"})
public class ReadmeSamples {
    static class ChangeClassModifier extends Customization {
        static class Initial {
            // BEGIN: readme-sample-change-class-modifier-initial
            public class Foo {
            }
            // END: readme-sample-change-class-modifier-initial
        }

        // BEGIN: readme-sample-change-class-modifier-customization
        @Override
        public void customize(LibraryCustomization customization, Logger logger) {
            // Calling setModifiers without passing anything is equivalent to package-private.
            customization.getClass("com.azure.myservice.models", "Foo").customizeAst(ast -> ast.getClassByName("Foo")
                .ifPresent(ClassOrInterfaceDeclaration::setModifiers));
        }
        // END: readme-sample-change-class-modifier-customization

        static class Result {
            // BEGIN: readme-sample-change-class-modifier-result
            class Foo {
            }
            // END: readme-sample-change-class-modifier-result
        }
    }

    static class ChangeMethodModifier extends Customization {
        static class Initial {
            // BEGIN: readme-sample-change-method-modifier-initial
            public class Foo {
                private Bar bar;

                public Bar getBar() {
                    return this.bar;
                }
            }
            // END: readme-sample-change-method-modifier-initial
        }

        // BEGIN: readme-sample-change-method-modifier-customization
        @Override
        public void customize(LibraryCustomization customization, Logger logger) {
            customization.getClass("com.azure.myservice.models", "Foo").customizeAst(ast -> ast.getClassByName("Foo")
                .ifPresent(clazz -> clazz.getMethodsByName("getBar")
                    .forEach(method -> method.setModifiers(Modifier.Keyword.PRIVATE))));
        }
        // END: readme-sample-change-method-modifier-customization

        static class Result {
            // BEGIN: readme-sample-change-method-modifier-result
            public class Foo {
                private Bar bar;

                private Bar getBar() {
                    return this.bar;
                }
            }
            // END: readme-sample-change-method-modifier-result
        }
    }

    static class ChangeMethodReturnType extends Customization {
        static class Initial {
            // BEGIN: readme-sample-change-method-return-type-initial
            public class Foo {
                private Bar bar;

                public Bar getBar() {
                    return this.bar;
                }
            }
            // END: readme-sample-change-method-return-type-initial
        }

        // BEGIN: readme-sample-change-method-return-type-customization
        @Override
        public void customize(LibraryCustomization customization, Logger logger) {
            customization.getClass("com.azure.myservice.models", "Foo").customizeAst(ast -> {
                ast.addImport(UUID.class);
                ast.getClassByName("Foo").ifPresent(clazz -> clazz.getMethodsByName("getId").forEach(method -> {
                    // change return type to UUID
                    method.setType("UUID");
                    method.setBody(StaticJavaParser.parseBlock("{ return UUID.fromString(this.id); }"));
                }));
            });
        }
        // END: readme-sample-change-method-return-type-customization

        static class Result {
            // BEGIN: readme-sample-change-method-return-type-result
            public class Foo {
                private String id;

                public UUID getId() {
                    return UUID.fromString(this.id);
                }
            }
            // END: readme-sample-change-method-return-type-result
        }
    }

    static class AddClassAnnotation extends Customization {
        static class Initial {
            // BEGIN: readme-sample-add-class-annotation-initial
            public class Foo {
            }
            // END: readme-sample-add-class-annotation-initial
        }

        // BEGIN: readme-sample-add-class-annotation-customization
        @Override
        public void customize(LibraryCustomization customization, Logger logger) {
            customization.getClass("com.azure.myservice.models", "Foo").customizeAst(ast -> ast.getClassByName("Foo")
                .ifPresent(clazz -> clazz.addMarkerAnnotation("Deprecated")));
        }
        // END: readme-sample-add-class-annotation-customization

        static class Result {
            // BEGIN: readme-sample-add-class-annotation-result
            @Deprecated
            public class Foo {
            }
            // END: readme-sample-add-class-annotation-result
        }
    }

    static class AddMethodAnnotation extends Customization {
        static class Initial {
            // BEGIN: readme-sample-add-method-annotation-initial
            public class Foo {
                private Bar bar;

                public Bar getBar() {
                    return this.bar;
                }
            }
            // END: readme-sample-add-method-annotation-initial
        }

        // BEGIN: readme-sample-add-method-annotation-customization
        @Override
        public void customize(LibraryCustomization customization, Logger logger) {
            customization.getClass("com.azure.myservice.models", "Foo").customizeAst(ast -> ast.getClassByName("Foo")
                .ifPresent(clazz -> clazz.getMethodsByName("getBar")
                    .forEach(method -> method.addMarkerAnnotation("Deprecated"))));
        }
        // END: readme-sample-add-method-annotation-customization

        static class Result {
            // BEGIN: readme-sample-add-method-annotation-result
            public class Foo {
                private Bar bar;

                @Deprecated
                public Bar getBar() {
                    return this.bar;
                }
            }
            // END: readme-sample-add-method-annotation-result
        }
    }

    static class RemoveClassAnnotation extends Customization {
        static class Initial {
            // BEGIN: readme-sample-remove-class-annotation-initial
            @Deprecated
            public class Foo {
            }
            // END: readme-sample-remove-class-annotation-initial
        }

        // BEGIN: readme-sample-remove-class-annotation-customization
        @Override
        public void customize(LibraryCustomization customization, Logger logger) {
            customization.getClass("com.azure.myservice.models", "Foo").customizeAst(ast -> ast.getClassByName("Foo")
                .flatMap(clazz -> clazz.getAnnotationByName("Deprecated"))
                .ifPresent(Node::remove));
        }
        // END: readme-sample-remove-class-annotation-customization

        static class Result {
            // BEGIN: readme-sample-remove-class-annotation-result
            public class Foo {
            }
            // END: readme-sample-remove-class-annotation-result
        }
    }

    static class GenerateGetterAndSetter extends Customization {
        static class Initial {
            // BEGIN: readme-sample-generate-getter-and-setter-initial
            public class Foo {
                private boolean active;
            }
            // END: readme-sample-generate-getter-and-setter-initial
        }

        // BEGIN: readme-sample-generate-getter-and-setter-customization
        @Override
        public void customize(LibraryCustomization customization, Logger logger) {
            customization.getClass("com.azure.myservice.models", "Foo").customizeAst(ast -> ast.getClassByName("Foo")
                .ifPresent(clazz -> {
                    clazz.addMethod("isActive", Modifier.Keyword.PUBLIC)
                        .setType("boolean")
                        .setBody(StaticJavaParser.parseBlock("{ return this.active; }"));
                    clazz.addMethod("setActive", Modifier.Keyword.PUBLIC)
                        .setType("Foo")
                        .addParameter("boolean", "active")
                        .setBody(StaticJavaParser.parseBlock("{ this.active = active; return this; }"));
                }));
        }
        // END: readme-sample-generate-getter-and-setter-customization

        static class Result {
            // BEGIN: readme-sample-generate-getter-and-setter-result
            public class Foo {
                private boolean active;

                public boolean isActive() {
                    return this.active;
                }

                public Foo setActive(boolean active) {
                    this.active = active;
                    return this;
                }
            }
            // END: readme-sample-generate-getter-and-setter-result
        }
    }

    static class RenameEnumMember extends Customization {
        static class Initial {
            // BEGIN: readme-sample-rename-enum-member-initial
            public enum ImageFileType {
                GIF("gif"),
                JPG("jpg"),
                TIFF("tiff"),
                PNG("png");

                private final String value;

                ImageFileType(String value) {
                    this.value = value;
                }
            }
            // END: readme-sample-rename-enum-member-initial
        }

        // BEGIN: readme-sample-rename-enum-member-customization
        @Override
        public void customize(LibraryCustomization customization, Logger logger) {
            customization.getClass("com.azure.myservice.models", "Foo").customizeAst(ast -> ast.getEnumByName("Foo")
                .ifPresent(clazz -> clazz.getEntries().stream()
                    .filter(entry -> "JPG".equals(entry.getName().getIdentifier()))
                    .forEach(entry -> entry.setName("JPEG"))));
        }
        // END: readme-sample-rename-enum-member-customization

        static class Result {
            // BEGIN: readme-sample-rename-enum-member-result
            public enum ImageFileType {
                GIF("gif"),
                JPEG("jpg"),
                TIFF("tiff"),
                PNG("png");

                private final String value;

                ImageFileType(String value) {
                    this.value = value;
                }
            }
            // END: readme-sample-rename-enum-member-result
        }
    }

    static class ChangeJavadocDescription extends Customization {
        static class Initial {
            // BEGIN: readme-sample-change-javadoc-description-initial
            /** Class Foo. */
            public class Foo {
                private boolean active;

                public boolean isActive() {
                    return this.active;
                }

                public Foo setActive(boolean active) {
                    this.active = active;
                    return this;
                }
            }
            // END: readme-sample-change-javadoc-description-initial
        }

        // BEGIN: readme-sample-change-javadoc-description-customization
        @Override
        public void customize(LibraryCustomization customization, Logger logger) {
            customization.getClass("com.azure.myservice.models", "Foo").customizeAst(ast -> ast.getClassByName("Foo")
                .ifPresent(clazz -> {
                    clazz.setJavadocComment("A Foo object stored in Azure.");
                    clazz.getMethodsByName("setActive")
                        .forEach(method -> method.setJavadocComment("Set the active value."));
                }));
        }
        // END: readme-sample-change-javadoc-description-customization

        static class Result {
            // BEGIN: readme-sample-change-javadoc-description-result
            /**
             * A Foo object stored in Azure.
             */
            public class Foo {
                private boolean active;

                public boolean isActive() {
                    return this.active;
                }

                /**
                 * Set the active value.
                 */
                public Foo setActive(boolean active) {
                    this.active = active;
                    return this;
                }
            }
            // END: readme-sample-change-javadoc-description-result
        }
    }

    static class ChangeJavadocParam extends Customization {
        static class Initial {
            // BEGIN: readme-sample-change-javadoc-param-initial
            public class Foo {
                private boolean active;

                public boolean isActive() {
                    return this.active;
                }

                /**
                 * Set the active value.
                 */
                public Foo setActive(boolean active) {
                    this.active = active;
                    return this;
                }
            }
            // END: readme-sample-change-javadoc-param-initial
        }

        // BEGIN: readme-sample-change-javadoc-param-customization
        @Override
        public void customize(LibraryCustomization customization, Logger logger) {
            customization.getClass("com.azure.myservice.models", "Foo").customizeAst(ast -> ast.getClassByName("Foo")
                .ifPresent(clazz -> clazz.getMethodsByName("setActive").forEach(method -> method.getJavadoc()
                    .ifPresent(javadoc -> method.setJavadocComment(javadoc.addBlockTag("param", "active",
                        "if the foo object is in active state"))))));
        }
        // END: readme-sample-change-javadoc-param-customization

        static class Result {
            // BEGIN: readme-sample-change-javadoc-param-result
            public class Foo {
                private boolean active;

                public boolean isActive() {
                    return this.active;
                }

                /**
                 * Set the active value.
                 *
                 * @param active if the foo object is in active state
                 */
                public Foo setActive(boolean active) {
                    this.active = active;
                    return this;
                }
            }
            // END: readme-sample-change-javadoc-param-result
        }
    }

    static class ChangeJavadocReturn extends Customization {
        static class Initial {
            // BEGIN: readme-sample-change-javadoc-return-initial
            public class Foo {
                private boolean active;

                public boolean isActive() {
                    return this.active;
                }

                /**
                 * Set the active value.
                 *
                 * @param active if the foo object is in active state
                 */
                public Foo setActive(boolean active) {
                    this.active = active;
                    return this;
                }
            }
            // END: readme-sample-change-javadoc-return-initial
        }

        // BEGIN: readme-sample-change-javadoc-return-customization
        @Override
        public void customize(LibraryCustomization customization, Logger logger) {
            customization.getClass("com.azure.myservice.models", "Foo").customizeAst(ast -> ast.getClassByName("Foo")
                .ifPresent(clazz -> clazz.getMethodsByName("setActive").forEach(method -> method.getJavadoc()
                    .ifPresent(javadoc -> method.setJavadocComment(javadoc.addBlockTag("return",
                        "the current foo object"))))));
        }
        // END: readme-sample-change-javadoc-return-customization

        static class Result {
            // BEGIN: readme-sample-change-javadoc-return-result
            public class Foo {
                private boolean active;

                public boolean isActive() {
                    return this.active;
                }

                /**
                 * Set the active value.
                 *
                 * @param active if the foo object is in active state
                 * @return the current foo object
                 */
                public Foo setActive(boolean active) {
                    this.active = active;
                    return this;
                }
            }
            // END: readme-sample-change-javadoc-return-result
        }
    }

    static class ChangeJavadocThrows extends Customization {
        static class Initial {
            // BEGIN: readme-sample-change-javadoc-throws-initial
            public class FooClient {
                /**
                 * Create a Foo object.
                 *
                 * @param foo the foo object to create in Azure
                 * @return the response for creating the foo object
                 */
                public CreateFooResponse createFoo(Foo foo) {
                    /* REST call to create foo */
                    return null;
                }
            }
            // END: readme-sample-change-javadoc-throws-initial
        }

        // BEGIN: readme-sample-change-javadoc-throws-customization
        @Override
        public void customize(LibraryCustomization customization, Logger logger) {
            customization.getClass("com.azure.myservice.models", "Foo").customizeAst(ast -> ast.getClassByName("Foo")
                .ifPresent(clazz -> clazz.getMethodsByName("setActive").forEach(method -> method.getJavadoc()
                    .ifPresent(javadoc -> method.setJavadocComment(javadoc.addBlockTag("throws", "RuntimeException",
                        "An unsuccessful response is received"))))));
        }
        // END: readme-sample-change-javadoc-throws-customization

        static class Result {
            // BEGIN: readme-sample-change-javadoc-throws-result
            /**
             * Create a Foo object.
             *
             * @param foo the foo object to create in Azure
             * @return the response for creating the foo object
             * @throws RuntimeException An unsuccessful response is received
             */
            public CreateFooResponse createFoo(Foo foo) {
                /* REST call to create foo */
                return null;
            }
            // END: readme-sample-change-javadoc-throws-result
        }
    }

    private static final class Bar {
    }

    private static final class Foo {
    }

    private static final class CreateFooResponse {
    }
}
