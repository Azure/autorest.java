// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import org.slf4j.Logger;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.UUID;

/**
 * README samples for post-code generation.
 */
@SuppressWarnings({"unused", "InnerClassMayBeStatic"})
public class ReadmeSamples {
    static class ImplementCustomization extends Customization {
        // BEGIN: readme-sample-implementation-customization
        @Override
        public void customize(LibraryCustomization customization, Logger logger) {
            PackageCustomization models = customization.getPackage("com.azure.myservice.models");
            /* code to customize on the package level */
            ClassCustomization foo = models.getClass("Foo");
            /* code to customize the Foo class */
            MethodCustomization getBar = foo.getMethod("getBar");
            /* code to customize the getBar method */
            JavadocCustomization getBarJavadoc = getBar.getJavadoc();
            /* code to customize javadoc for getBar() method */
        }
        // END: readme-sample-implementation-customization
    }

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
            PackageCustomization models = customization.getPackage("com.azure.myservice.models");
            ClassCustomization foo = models.getClass("Foo");
            foo.setModifier(0); // 0 is a special modifier that sets package private
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
            PackageCustomization models = customization.getPackage("com.azure.myservice.models");
            ClassCustomization foo = models.getClass("Foo");
            MethodCustomization getBar = foo.getMethod("getBar");
            getBar.setModifier(Modifier.PRIVATE); // change to private
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
            PackageCustomization models = customization.getPackage("com.azure.myservice.models");
            ClassCustomization foo = models.getClass("Foo");
            MethodCustomization getId = foo.getMethod("getId");
            getId.setReturnType("UUID", "UUID.fromString(%s)"); // change return type to UUID
        }
        // END: readme-sample-change-method-return-type-customization

        static class Result {
            // BEGIN: readme-sample-change-method-return-type-result
            public class Foo {
                private String id;

                public UUID getId() {
                    String returnValue = this.id;
                    return UUID.fromString(returnValue);
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
            PackageCustomization models = customization.getPackage("com.azure.myservice.models");
            ClassCustomization foo = models.getClass("Foo");
            foo.addAnnotation("JsonClassDescription(\"Foo class\")");
        }
        // END: readme-sample-add-class-annotation-customization

        static class Result {
            // BEGIN: readme-sample-add-class-annotation-result
            @JsonClassDescription("Foo class")
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
            PackageCustomization models = customization.getPackage("com.azure.myservice.models");
            ClassCustomization foo = models.getClass("Foo");
            MethodCustomization getBar = foo.getMethod("getBar");
            getBar.addAnnotation("Deprecated");
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
            @JsonClassDescription("Foo class")
            public class Foo {
            }
            // END: readme-sample-remove-class-annotation-initial
        }

        // BEGIN: readme-sample-remove-class-annotation-customization
        @Override
        public void customize(LibraryCustomization customization, Logger logger) {
            PackageCustomization models = customization.getPackage("com.azure.myservice.models");
            ClassCustomization foo = models.getClass("Foo");
            foo.removeAnnotation("JsonClassDescription");
        }
        // END: readme-sample-remove-class-annotation-customization

        static class Result {
            // BEGIN: readme-sample-remove-class-annotation-result
            public class Foo {
            }
            // END: readme-sample-remove-class-annotation-result
        }
    }

    static class RenameClass extends Customization {
        static class Initial {
            // BEGIN: readme-sample-rename-class-initial
            public class Foo {
            }
            // END: readme-sample-rename-class-initial
        }

        // BEGIN: readme-sample-rename-class-customization
        @Override
        public void customize(LibraryCustomization customization, Logger logger) {
            PackageCustomization models = customization.getPackage("com.azure.myservice.models");
            ClassCustomization foo = models.getClass("Foo");
            foo.rename("FooInfo");
        }
        // END: readme-sample-rename-class-customization

        static class Result {
            // BEGIN: readme-sample-rename-class-result
            public class FooInfo {
            }
            // END: readme-sample-rename-class-result
        }
    }

    static class RenameMethod extends Customization {
        static class Initial {
            // BEGIN: readme-sample-rename-method-initial
            public class Foo {
                private boolean supportsUnicode;

                public boolean isSupportsUnicode() {
                    return this.supportsUnicode;
                }
            }
            // END: readme-sample-rename-method-initial
        }

        // BEGIN: readme-sample-rename-method-customization
        @Override
        public void customize(LibraryCustomization customization, Logger logger) {
            PackageCustomization models = customization.getPackage("com.azure.myservice.models");
            ClassCustomization foo = models.getClass("Foo");
            MethodCustomization isSupportsUnicode = foo.getMethod("isSupportsUnicode");
            isSupportsUnicode.rename("isUnicodeSupported");
        }
        // END: readme-sample-rename-method-customization

        static class Result {
            // BEGIN: readme-sample-rename-method-result
            public class Foo {
                private boolean supportsUnicode;

                public boolean isUnicodeSupported() {
                    return this.supportsUnicode;
                }
            }
            // END: readme-sample-rename-method-result
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
            PackageCustomization models = customization.getPackage("com.azure.myservice.models");
            ClassCustomization foo = models.getClass("Foo");
            PropertyCustomization active = foo.getProperty("active");
            active.generateGetterAndSetter();
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

    static class RenameProperty extends Customization {
        static class Initial {
            // BEGIN: readme-sample-rename-property-initial
            public class Foo {
                private List<String> whiteList;

                public List<String> getWhiteList() {
                    return this.whiteList;
                }

                public Foo setWhiteList(List<String> whiteList) {
                    this.whiteList = whiteList;
                    return this;
                }
            }
            // END: readme-sample-rename-property-initial
        }

        // BEGIN: readme-sample-rename-property-customization
        @Override
        public void customize(LibraryCustomization customization, Logger logger) {
            PackageCustomization models = customization.getPackage("com.azure.myservice.models");
            ClassCustomization foo = models.getClass("Foo");
            PropertyCustomization active = foo.getProperty("active");
            active.rename("allowList");
        }
        // END: readme-sample-rename-property-customization

        static class Result {
            // BEGIN: readme-sample-rename-property-result
            public class Foo {
                private List<String> allowList;

                public List<String> getAllowList() {
                    return this.allowList;
                }

                public Foo setAllowList(List<String> allowList) {
                    this.allowList = allowList;
                    return this;
                }
            }
            // END: readme-sample-rename-property-result
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
            PackageCustomization models = customization.getPackage("com.azure.myservice.models");
            ClassCustomization foo = models.getClass("Foo");
            foo.renameEnumMember("JPG", "JPEG");
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
            PackageCustomization models = customization.getPackage("com.azure.myservice.models");
            ClassCustomization foo = models.getClass("Foo");
            JavadocCustomization fooJavadoc = foo.getJavadoc();
            fooJavadoc.setDescription("A Foo object stored in Azure.");
            JavadocCustomization setActiveJavadoc = foo.getMethod("setActive").getJavadoc();
            setActiveJavadoc.setDescription("Set the active value.");
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
            PackageCustomization models = customization.getPackage("com.azure.myservice.models");
            ClassCustomization foo = models.getClass("Foo");
            JavadocCustomization setActiveJavadoc = foo.getMethod("setActive").getJavadoc();
            setActiveJavadoc.setParam("active", "if the foo object is in active state");
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
            PackageCustomization models = customization.getPackage("com.azure.myservice.models");
            ClassCustomization foo = models.getClass("Foo");
            JavadocCustomization setActiveJavadoc = foo.getMethod("setActive").getJavadoc();
            setActiveJavadoc.setReturn("the current foo object");
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
            PackageCustomization models = customization.getPackage("com.azure.myservice.models");
            ClassCustomization foo = models.getClass("Foo");
            JavadocCustomization setActiveJavadoc = foo.getMethod("setActive").getJavadoc();
            setActiveJavadoc.addThrows("RuntimeException", "An unsuccessful response is received");
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
