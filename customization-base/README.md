# Overview

To set up customizations, create a Maven project with dependency:

```xml
<dependency>
  <groupId>com.azure.tools</groupId>
  <artifactId>azure-autorest-customization</artifactId>
  <version>1.0.0-beta.4</version>
</dependency>
```

Create a customization class extending from `com.azure.autorest.customization.Customization` and override the 
`void customize(LibraryCustomization, Logger)` method. You will have access to a `LibraryCustomization` class where you 
will be able to customize the generated Java code before it's written to the disk. Currently, the following 
customizations are supported:

- [Change class modifier](#change-class-modifier)
- [Change method modifier](#change-method-modifier)
- [Change method return type](#change-method-return-type)
- [Add an annotation to a class](#add-an-annotation-to-a-class)
- [Add an annotation to a method](#add-an-annotation-to-a-method)
- [Remove an annotation from a class](#remove-an-annotation-from-a-class)
- [Refactor: Rename a class](#refactor-rename-a-class)
- [Refactor: Rename a method](#refactor-rename-a-method)
- [Refactor: Generate the getter and setter methods for a property](#refactor-generate-the-getter-and-setter-methods-for-a-property)
- [Refactor: Rename a property and its corresponding getter and setter methods](#refactor-rename-a-property-and-its-corresponding-getter-and-setter-methods)
- [Refactor: Rename an enum member name](#refactor-rename-an-enum-member-name)
- [Javadoc: Set the description for a class / method](#javadoc-set-the-description-for-a-class--method)
- [Javadoc: Set / remove a parameter's javadoc on a method](#javadoc-set--remove-a-parameters-javadoc-on-a-method)
- [Javadoc: Set the return javadoc on a method](#javadoc-set-the-return-javadoc-on-a-method)
- [Javadoc: Set / remove an exception's javadoc on a method ](#javadoc-add--remove-an-exceptions-javadoc-on-a-method)

## Navigate through the packages and classes

There are five primary customization classes currently available, `LibraryCustomization`, `PackageCustomization`, 
`ClassCustomization`, `MethodCustomization` and `JavadocCustomization`. From a given `LibraryCustomization`, you can 
navigate through the packages, classes, and methods intuitively with the following methods:

```java readme-sample-implementation-customization
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
```

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
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    foo.setModifier(0); // 0 is a special modifier that sets package private
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
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    MethodCustomization getBar = foo.getMethod("getBar");
    getBar.setModifier(Modifier.PRIVATE); // change to private
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
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    MethodCustomization getId = foo.getMethod("getId");
    getId.setReturnType("UUID", "UUID.fromString(%s)"); // change return type to UUID
}
```

will generate

```java readme-sample-change-method-return-type-result
public class Foo {
    private String id;

    public UUID getId() {
        String returnValue = this.id;
        return UUID.fromString(returnValue);
    }
}
```

The `UUID` class will be automatically imported.

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
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    foo.addAnnotation("JsonClassDescription(\"Foo class\")");
}
```

will generate

```java readme-sample-add-class-annotation-result
@JsonClassDescription("Foo class")
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
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    MethodCustomization getBar = foo.getMethod("getBar");
    getBar.addAnnotation("Deprecated");
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

## Remove an annotation from a class

A class `Foo`

```java readme-sample-remove-class-annotation-initial
@JsonClassDescription("Foo class")
public class Foo {
}
```

with customization

```java readme-sample-remove-class-annotation-customization
@Override
public void customize(LibraryCustomization customization, Logger logger) {
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    foo.removeAnnotation("JsonClassDescription");
}
```

will generate

```java readme-sample-remove-class-annotation-result
public class Foo {
}
```

## Refactor: Rename a class

A class `Foo`

```java readme-sample-rename-class-initial
public class Foo {
}
```

with customization

```java readme-sample-rename-class-customization
@Override
public void customize(LibraryCustomization customization, Logger logger) {
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    foo.rename("FooInfo");
}
```

will generate

```java readme-sample-rename-class-result
public class FooInfo {
}
```

All references of `Foo` will be modified to `FooInfo`. When a valid value is provided, this customization is guaranteed 
to not break the build.

## Refactor: Rename a method

A method `isSupportsUnicode` in the `Foo` class

```java readme-sample-rename-method-initial
public class Foo {
    private boolean supportsUnicode;

    public boolean isSupportsUnicode() {
        return this.supportsUnicode;
    }
}
```

with customization

```java readme-sample-rename-method-customization
@Override
public void customize(LibraryCustomization customization, Logger logger) {
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    MethodCustomization isSupportsUnicode = foo.getMethod("isSupportsUnicode");
    isSupportsUnicode.rename("isUnicodeSupported");
}
```

will generate

```java readme-sample-rename-method-result
public class Foo {
    private boolean supportsUnicode;

    public boolean isUnicodeSupported() {
        return this.supportsUnicode;
    }
}
```

All references of `isSupportsUnicode()` will be modified to `isUnicodeSupported()`. When a valid value is provided, this 
customization is guaranteed to not break the build.

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
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    PropertyCustomization active = foo.getProperty("active");
    active.generateGetterAndSetter();
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

If the class already contains a getter or a setter method, the current method will be kept. This customization is 
guaranteed to not break the build.

## Refactor: Rename a property and its corresponding getter and setter methods

A property `whitelist` in the `Foo` class

```java readme-sample-rename-property-initial
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
```

with customization

```java readme-sample-rename-property-customization
@Override
public void customize(LibraryCustomization customization, Logger logger) {
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    PropertyCustomization active = foo.getProperty("active");
    active.rename("allowList");
}
```

will generate

```java readme-sample-rename-property-result
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
```

This customization is guaranteed to not break the build.

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
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    foo.renameEnumMember("JPG", "JPEG");
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
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    JavadocCustomization fooJavadoc = foo.getJavadoc();
    fooJavadoc.setDescription("A Foo object stored in Azure.");
    JavadocCustomization setActiveJavadoc = foo.getMethod("setActive").getJavadoc();
    setActiveJavadoc.setDescription("Set the active value.");
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
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    JavadocCustomization setActiveJavadoc = foo.getMethod("setActive").getJavadoc();
    setActiveJavadoc.setParam("active", "if the foo object is in active state");
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
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    JavadocCustomization setActiveJavadoc = foo.getMethod("setActive").getJavadoc();
    setActiveJavadoc.setReturn("the current foo object");
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
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    JavadocCustomization setActiveJavadoc = foo.getMethod("setActive").getJavadoc();
    setActiveJavadoc.addThrows("RuntimeException", "An unsuccessful response is received");
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
