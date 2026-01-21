# Overview

The `azure-autorest-customization` package provides APIs for customizing Autorest code generation safely and
programmatically to support special cases not supported by Autorest code generation directly using Eclipse language
server to ensure valid Java code.

## Before You Customize

Before customizing generated code, consider whether your change should be made in TypeSpec (`client.tsp`) instead. TypeSpec customizations are cleaner and survive regeneration. See the [TypeSpec Client Customizations Reference](https://github.com/Azure/azure-sdk-for-java/blob/main/eng/common/knowledge/customizing-client-tsp.md) for available decorators like `@@clientName`, `@@access`, etc.

Use Java code customizations only when TypeSpec cannot express the behavior you need.

To set up customizations, create a Maven project with dependency:

```xml
<dependency>
  <groupId>com.azure.tools</groupId>
  <artifactId>azure-autorest-customization</artifactId>
  <version>1.0.0-beta.11</version>
</dependency>
```

Create a customization class extending from `com.azure.autorest.customization.Customization` and override the 
`void customize(LibraryCustomization, Logger)` method. You will have access to a `LibraryCustomization` class where you 
will be able to customize the generated Java code before it's written to the disk. Currently, the following 
customizations are supported:

- [Change class modifier](#change-class-modifier)
- [Change method modifier](#change-method-modifier)
- [Change method return type](#change-method-return-type)
- [Change class super type](#change-class-super-type)
- [Add an annotation to a class](#add-an-annotation-to-a-class)
- [Add an annotation to a method](#add-an-annotation-to-a-method)
- [Add a field default value](#add-a-field-default-value)
- [Remove an annotation from a class](#remove-an-annotation-from-a-class)
- [Refactor: Generate the getter and setter methods for a property](#refactor-generate-the-getter-and-setter-methods-for-a-property)
- [Refactor: Rename an enum member name](#refactor-rename-an-enum-member-name)
- [Javadoc: Set the description for a class / method](#javadoc-set-the-description-for-a-class--method)
- [Javadoc: Set / remove a parameter's javadoc on a method](#javadoc-set--remove-a-parameters-javadoc-on-a-method)
- [Javadoc: Set the return javadoc on a method](#javadoc-set-the-return-javadoc-on-a-method)
- [Javadoc: Set / remove an exception's javadoc on a method ](#javadoc-add--remove-an-exceptions-javadoc-on-a-method)

## Navigate through the packages and classes

There are three primary customization classes currently available, `LibraryCustomization`, `PackageCustomization`, 
and `ClassCustomization`. From a given `LibraryCustomization`, you can navigate through the packages and classes intuitively.

## Change class modifier

A class `Foo`

```java readme-sample-change-class-modifier-initial
public class Foo {
}
```

with customization

```java readme-sample-change-class-modifier-customization
@Override
public void customize(LibraryCustomization customization, Logger logger) {
    // Calling setModifiers without passing anything is equivalent to package-private.
    customization.getClass("com.azure.myservice.models", "Foo").customizeAst(ast -> ast.getClassByName("Foo")
        .ifPresent(ClassOrInterfaceDeclaration::setModifiers));
}
```

will generate

```java readme-sample-change-class-modifier-result
class Foo {
}
```

## Change method modifier

A method `getBar` in the `Foo` class

```java readme-sample-change-method-modifier-initial
public class Foo {
    private Bar bar;

    public Bar getBar() {
        return this.bar;
    }
}
```

with customization

```java readme-sample-change-method-modifier-customization
@Override
public void customize(LibraryCustomization customization, Logger logger) {
    customization.getClass("com.azure.myservice.models", "Foo").customizeAst(ast -> ast.getClassByName("Foo")
        .ifPresent(clazz -> clazz.getMethodsByName("getBar")
            .forEach(method -> method.setModifiers(Modifier.Keyword.PRIVATE))));
}
```

will generate

```java readme-sample-change-method-modifier-result
public class Foo {
    private Bar bar;

    private Bar getBar() {
        return this.bar;
    }
}
```

## Change method return type

You can change a method's return type, and pass a String formatter to transform the original return value statement. 
If the original return type is `void`, simply pass the full return value String expression in place of the String 
formatter; if the new return type is `void`, simply pass `null`.

A method `getId` in the `Foo` class

```java readme-sample-change-method-return-type-initial
public class Foo {
    private Bar bar;

    public Bar getBar() {
        return this.bar;
    }
}
```

with customization

```java readme-sample-change-method-return-type-customization
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
```

will generate

```java readme-sample-change-method-return-type-result
public class Foo {
    private String id;

    public UUID getId() {
        return UUID.fromString(this.id);
    }
}
```

The `UUID` class will be automatically imported.

## Change class super type

A class `Foo` extends `Bar`
```java readme-sample-change-class-base-type-initial
public class Bar {
}
public class Foo extends Bar {
}
```

with customization
```java readme-sample-change-class-base-type-customization
@Override
public void customize(LibraryCustomization customization, Logger logger) {
    customization.getClass("com.azure.myservice.models", "Foo")
        .customizeAst(ast -> ast.getClassByName("foo").ifPresent(clazz -> {
            String newTypeFullName = "com.azure.myservice.models.Bar1";
            ast.addImport(newTypeFullName);
            clazz.getExtendedTypes().clear();
            clazz.addExtendedType(new ClassOrInterfaceType(null, "Bar1"));
        }));
}
```

will generate
```java readme-sample-change-class-base-type-result
public class Foo extends Bar1 {
}
```

## Add an annotation to a class

A class `Foo`

```java readme-sample-add-class-annotation-initial
public class Foo {
}
```

with customization

```java readme-sample-add-class-annotation-customization
@Override
public void customize(LibraryCustomization customization, Logger logger) {
    customization.getClass("com.azure.myservice.models", "Foo").customizeAst(ast -> ast.getClassByName("Foo")
        .ifPresent(clazz -> clazz.addMarkerAnnotation("Deprecated")));
}
```

will generate

```java readme-sample-add-class-annotation-result
@Deprecated
public class Foo {
}
```

The `JsonClassDescription` class will be automatically imported.

## Add an annotation to a method

A method `getBar` in the `Foo` class

```java readme-sample-add-method-annotation-initial
public class Foo {
    private Bar bar;

    public Bar getBar() {
        return this.bar;
    }
}
```

with customization

```java readme-sample-add-method-annotation-customization
@Override
public void customize(LibraryCustomization customization, Logger logger) {
    customization.getClass("com.azure.myservice.models", "Foo").customizeAst(ast -> ast.getClassByName("Foo")
        .ifPresent(clazz -> clazz.getMethodsByName("getBar")
            .forEach(method -> method.addMarkerAnnotation("Deprecated"))));
}
```

will generate

```java readme-sample-add-method-annotation-result
public class Foo {
    private Bar bar;

    @Deprecated
    public Bar getBar() {
        return this.bar;
    }
}
```

The `Deprecated` class will be automatically imported.

## Add a field default value

A class `Foo`

```java readme-sample-add-a-field-default-value-initial
public class Foo {
    private String bar;
}
```

with customization

```java readme-sample-add-a-field-default-value-customization
@Override
public void customize(LibraryCustomization customization, Logger logger) {
    customization.getClass("com.azure.myservice.models", "Foo")
        .customizeAst(ast -> ast.getClassByName("Foo")
            .flatMap(clazz -> clazz.getFieldByName("bar"))
            .ifPresent(barField ->
                barField.getVariables().forEach(var -> {
                    if (var.getNameAsString().equals("bar")) {
                        var.setInitializer("\"bar\"");
                    }
                })));
}
```

will generate
```java readme-sample-add-a-field-default-value-result
public class Foo {
    private String bar = "bar";
}
```

## Remove an annotation from a class

A class `Foo`

```java readme-sample-remove-class-annotation-initial
@Deprecated
public class Foo {
}
```

with customization

```java readme-sample-remove-class-annotation-customization
@Override
public void customize(LibraryCustomization customization, Logger logger) {
    customization.getClass("com.azure.myservice.models", "Foo").customizeAst(ast -> ast.getClassByName("Foo")
        .flatMap(clazz -> clazz.getAnnotationByName("Deprecated"))
        .ifPresent(Node::remove));
}
```

will generate

```java readme-sample-remove-class-annotation-result
public class Foo {
}
```

## Refactor: Generate the getter and setter methods for a property

A property `active` in the `Foo` class

```java readme-sample-generate-getter-and-setter-initial
public class Foo {
    private boolean active;
}
```

with customization

```java readme-sample-generate-getter-and-setter-customization
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
```

will generate

```java readme-sample-generate-getter-and-setter-result
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
```

## Refactor: Rename an enum member name

An enum member `JPG` in an enum class `ImageFileType`:

```java readme-sample-rename-enum-member-initial
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
```

with customization

```java readme-sample-rename-enum-member-customization
@Override
public void customize(LibraryCustomization customization, Logger logger) {
    customization.getClass("com.azure.myservice.models", "Foo").customizeAst(ast -> ast.getEnumByName("Foo")
        .ifPresent(clazz -> clazz.getEntries().stream()
            .filter(entry -> "JPG".equals(entry.getName().getIdentifier()))
            .forEach(entry -> entry.setName("JPEG"))));
}
```

will generate

```java readme-sample-rename-enum-member-result
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
```

This customization is guaranteed to not break the build.

## Javadoc: Set the description for a class / method

A class `Foo`

```java readme-sample-change-javadoc-description-initial
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
```

with customization

```java readme-sample-change-javadoc-description-customization
@Override
public void customize(LibraryCustomization customization, Logger logger) {
    customization.getClass("com.azure.myservice.models", "Foo").customizeAst(ast -> ast.getClassByName("Foo")
        .ifPresent(clazz -> {
            clazz.setJavadocComment("A Foo object stored in Azure.");
            clazz.getMethodsByName("setActive")
                .forEach(method -> method.setJavadocComment("Set the active value."));
        }));
}
```

will generate

```java readme-sample-change-javadoc-description-result
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
```

## Javadoc: Set / remove a parameter's javadoc on a method

A class `Foo`

```java readme-sample-change-javadoc-param-initial
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
```

with customization

```java readme-sample-change-javadoc-param-customization
@Override
public void customize(LibraryCustomization customization, Logger logger) {
    customization.getClass("com.azure.myservice.models", "Foo").customizeAst(ast -> ast.getClassByName("Foo")
        .ifPresent(clazz -> clazz.getMethodsByName("setActive").forEach(method -> method.getJavadoc()
            .ifPresent(javadoc -> method.setJavadocComment(javadoc.addBlockTag("param", "active",
                "if the foo object is in active state"))))));
}
```

will generate

```java readme-sample-change-javadoc-param-result
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
```

## Javadoc: Set the return javadoc on a method

A `Foo` class

```java readme-sample-change-javadoc-return-initial
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
```

with customization

```java readme-sample-change-javadoc-return-customization
@Override
public void customize(LibraryCustomization customization, Logger logger) {
    customization.getClass("com.azure.myservice.models", "Foo").customizeAst(ast -> ast.getClassByName("Foo")
        .ifPresent(clazz -> clazz.getMethodsByName("setActive").forEach(method -> method.getJavadoc()
            .ifPresent(javadoc -> method.setJavadocComment(javadoc.addBlockTag("return",
                "the current foo object"))))));
}
```

will generate

```java readme-sample-change-javadoc-return-result
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
```

## Javadoc: Add / remove an exception's javadoc on a method

A `FooClient` class

```java readme-sample-change-javadoc-throws-initial
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
```

with customization

```java readme-sample-change-javadoc-throws-customization
@Override
public void customize(LibraryCustomization customization, Logger logger) {
    customization.getClass("com.azure.myservice.models", "Foo").customizeAst(ast -> ast.getClassByName("Foo")
        .ifPresent(clazz -> clazz.getMethodsByName("setActive").forEach(method -> method.getJavadoc()
            .ifPresent(javadoc -> method.setJavadocComment(javadoc.addBlockTag("throws", "RuntimeException",
                "An unsuccessful response is received"))))));
}
```

will generate

```java readme-sample-change-javadoc-throws-result
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
```

# Troubleshooting

## AutoRest fails with "Unable to format output file"

The cause is that the customized Java code has syntax error.

Possible root cause:
* Bug in customization code.
* Bug in the `customization-base` package.

Steps to diagnose and fix:
1. Add `skip-formatting` flag to skip Java code formatting, and hence not checking the syntax error.
2. Compile or inspect the generated code, find the error.
3. Determine the root cause. If it is caused by bug in customization code, fix it. If it is caused by bug in `customization-base` package, report it in GitHub issues.
4. Remove `skip-formatting` flag.

# Developer note

`azure-autorest-customization` passes all generated code files to an Eclipse language server process to enable richer,
IDE-like functionality. For this to work correctly all required dependencies must be passed to the language server using
a dummy `pom.xml`, which can be found in `src/main/resources`. If you make changes to code generation and begin seeing
code customizations failing it may be that the `pom.xml` is missing required dependencies and is causing the language
server to change processing from IDE-like to a regex, or find and replace, style.
