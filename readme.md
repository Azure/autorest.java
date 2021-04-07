# Overview
This is the next gen (v4) of AutoRest Java generator. It's built on AutoRest v3, written in Java, and supports OpenAPI3. It generates clients that work with `com.azure:azure-core`.

# Prerequisites
You need to have the following installed on your machine:

- Node.JS v10.x - v13.x
- Java 8+
- Maven 3.x

You need to have [autorest-beta](https://www.npmjs.com/package/@autorest/autorest) installed through NPM:

```bash
npm i -g autorest
```

# Usage
To use the latest released preview(https://github.com/Azure/autorest.java/releases), run
```bash
autorest --java
    --use:@autorest/java@4.0.x
    --input-file:path/to/specs.json
    --output-folder:where/to/generate/java/files
    --namespace:specified.java.package
```
The first time running it will take a little longer to download and install all the components.

To build from source code, clone this repo and checkout to v4 branch. Make sure all prerequisites are met, and run

```bash
mvn package -Dlocal
```

This will build a file `javagen-jar-with-dependencies.jar` under `javagen` module, a `preprocess-jar-with-dependencies.jar` under `preprocessor` module, a `fluentgen-jar-with-dependencies.jar` under `fluentgen` module, and a `fluentnamer--jar-with-dependencies.jar` under `fluentnamer` module.

And then run AutoRest

```bash
autorest --java
    --use:where/this/repo/is/cloned/autorest.java
    --input-file:path/to/specs.json
    --output-folder:where/to/generate/java/files
    --namespace:specified.java.package
```

Java files will be generated under `where/to/generate/java/files/src/main/java/specified/java/package`.

To debug, add `--java.debugger` to the argument list. The JVM will suspend at the beginning of the execution. Then attach a remote debugger in your IDE to `localhost:5005`. **Make sure you detach the debugger before killing the AutoRest process. Otherwise it will fail to shutdown the JVM and leave it orphaned. (which can be killed in the Task Manager)**

# Settings
Settings can be provided on the command line through `--name:value` or in a README file through `name: value`. The list of settings for AutoRest in general can be found at https://github.com/Azure/autorest/blob/master/docs/user/command-line-interface.md. The list of settings for AutoRest.Java specifically are listed below:

|Option                                                                &nbsp;| Description |
|------------------|-------------|
|`--enable-xml`|Generates models and clients that can be sent in XML over the wire. Default is false|
|`--client-side-validations`|Generate validations for required parameters and required model properties. Default is false.|
|`--generate-client-as-impl`|Append "Impl" to the names of service clients and method groups and place them in the `implementation` sub-package. Default is false.|
|`--generate-client-interfaces`|Implies `--generate-client-as-impl` and generates interfaces for all the "Impl"s. Default is false.|
|`--generate-sync-async-clients`|Implies `--generate-client-as-impl` and generates sync and async convenience layer clients for all the "Impl"s. Default is false.|
|`--implementation-subpackage=STRING`|The sub-package that the Service client and Method Group client implementation classes will be put into. Default is `implementation`.|
|`--models-subpackage=STRING`|The sub-package that Enums, Exceptions, and Model types will be put into. Default is `models`.|
|`--add-context-parameter`|Indicates whether the `com.azure.core.util.Context` parameter should be included in generated proxy methods. Default is false.|
|`--context-client-method-parameter`|Implies `--add-context-parameter` and indicates whether the `com.azure.core.util.Context` parameter should also be included in generated client methods. Default is false.|
|`--sync-methods=all\|essential\|none`|Specifies mode for generating sync wrappers. Supported value are <br>&nbsp;&nbsp;`essential` - generates only one sync returning body or header (default) <br>&nbsp;&nbsp;`all` - generates one sync method for each async method<br>&nbsp;&nbsp;`none` - does not generate any sync methods|
|`--required-parameter-client-methods`|Indicates whether client method overloads with only required parameters should be generated. Default is false.|
|`--custom-types=COMMA,SEPARATED,STRINGS`|Specifies a list of files to put in the package specified in `--custom-types-subpackage`.|
|`--custom-types-subpackage=STRING`|The sub-package that the custom types should be generated in. The types that custom types reference, or inherit from will also be automatically moved to this sub-package. **Recommended usage**: You can set this value to `models` and set `--models-subpackage=implementation.models`to generate models to `implementation.models` by default and pick specific models to be public through `--custom-types=`.|
|`--client-type-prefix=STRING`|The prefix that will be added to each generated client type.|
|`--model-override-setter-from-superclass`|Indicates whether to override the superclass setter method in model. Default is false.|

## Additional settings for Fluent

`fluent` option enables the generator extension for [Azure Management Libraries for Java](https://aka.ms/azsdk/java/mgmt).

Following settings only works when `fluent` option is specified.

| Option      | Description |
| ----------- | ----------- |
| `--fluent` | Enum. `LITE` for Fluent Lite; `PREMIUM` for Fluent Premium. Case insensitive. Default is `PREMIUM` if provided as other values. |
| `--fluent-subpackage` | String. The sub-package that vanilla client and builder will be put into. Default is `fluent`. |
| `--pom-file` | String. Name for Maven POM file. Default is `pom.xml`. |
| `--package-version` | String. Version number for Maven artifact. Default is `1.0.0-beta.1`. |
| `--service-name` | String. Service name used in Manager class and other documentations. If not provided, service name is deduced from `title` configure (from swagger or readme). |
| `--sdk-integration` | Boolean. Integrate to [azure-sdk-for-java](https://github.com/Azure/azure-sdk-for-java/). Default is `false`. Provide `output-folder` as absolute path for best performance. |
| `--add-inner` | CSV. Treat as inner class (move to `fluent.models` namespace, append `Inner` to class name). |
| `--remove-inner` | CSV. Exclude from inner classes. |
| `--rename-model` | CSV. Rename classes. Each item is of pattern `from:to`. |
| `--remove-model` | CSV. Remove classes. |
| `--name-for-ungrouped-operations` | String. Name for ungrouped operation group. Default to `ResourceProviders` for Lite. |

`fluent` option will change the default value for some vanilla options.
For example, `generate-client-interfaces`, `context-client-method-parameter`, `required-parameter-client-methods`, `model-override-setter-from-superclass` option is by default `true`.

The code formatter would require Java 11+ runtime.

# Customizations

To set up customizations, create a Maven project with dependency:

```xml
<dependency>
  <groupId>com.azure.tools</groupId>
  <artifactId>azure-autorest-customization</artifactId>
  <version>1.0.0-beta.3</version>
</dependency>
```

Create a customization class extending from `com.azure.autorest.customization.Customization` and override the `void customize(LibraryCustomization)` method. You will have access to a `LibraryCustomization` class where you will be able to customize the generated Java code before it's written to the disk. Currently, the following customizations are supported:

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
There are 4 customization classes currently available, `LibraryCustomization`, `PackageCustomization`, `ClassCustomization` and `JavadocCustomization`. From a given `LibraryCustomization`, you can navigate through the packages and classes intuitively with the following methods:

```java
@Override
public void customize(LibraryCustomization customization) {
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    /* code to customize on the package level */
    ClassCustomization foo = models.getClass("Foo");
    /* code to customize the Foo class */
    JavadocCustomization getBarJavadoc = foo.methodJavadoc("getBar");
    /* code to customize javadoc for getBar() method */
}
```

## Change class modifier
A class `Foo`
```java
public class Foo {
}
```

with customization

```java
@Override
public void customize(LibraryCustomization customization) {
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    foo.changeClassModifier(""); // change to package private
}
```

will generate

```java
class Foo {
}
```

## Change method modifier
A method `getBar` in the `Foo` class
```java
public class Foo {
    private Bar bar;

    public Bar getBar() {
        return this.bar;
    }
}
```

with customization

```java
@Override
public void customize(LibraryCustomization customization) {
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    foo.changeMethodModifier("getBar", "private"); // change to private
}
```

will generate
```java
public class Foo {
    private Bar bar;

    private Bar getBar() {
        return this.bar;
    }
}
```

## Change method return type
You can change a method's return type, and pass a String formatter to transform the original return value statement. If the original return type is `void`, simply pass the full return value String expression in place of the String formatter; if the new return type is `void`, simply pass `null`.

A method `getId` in the `Foo` class
```java
public class Foo {
    private String id;

    public String getId() {
        return this.id;
    }
}
```

with customization

```java
@Override
public void customize(LibraryCustomization customization) {
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    foo.changeMethodReturnType("getId", "UUID", "UUID.fromString(%s)"); // change to private
}
```

will generate
```java
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
```java
public class Foo {
}
```

with customization

```java
@Override
public void customize(LibraryCustomization customization) {
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    foo.addClassAnnotation("JsonFlatten");
}
```

will generate

```java
@JsonFlatten
public class Foo {
}
```

The `JsonFlatten` class will be automatically imported.

## Add an annotation to a method
A method `getBar` in the `Foo` class
```java
public class Foo {
    private Bar bar;

    public Bar getBar() {
        return this.bar;
    }
}
```

with customization

```java
@Override
public void customize(LibraryCustomization customization) {
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    foo.addMethodAnnotation("getBar", "Deprecated");
}
```

will generate
```java
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
```java
@Fluent
public class Foo {
}
```

with customization

```java
@Override
public void customize(LibraryCustomization customization) {
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    foo.removeClassAnnotation("Fluent");
}
```

will generate

```java
public class Foo {
}
```

## Refactor: Rename a class
A class `Foo`
```java
public class Foo {
}
```

with customization

```java
@Override
public void customize(LibraryCustomization customization) {
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    models.renameClass("Foo", "FooInfo");
}
```

will generate

```java
class FooInfo {
}
```

All references of `Foo` will be modified to `FooInfo`. When a valid value is provided, this customization is guaranteed to not break the build.

## Refactor: Rename a method
A method `isSupportsUnicode` in the `Foo` class
```java
public class Foo {
    private boolean supportsUnicode;

    public boolean isSupportsUnicode() {
        return this.supportsUnicode;
    }
}
```

with customization

```java
@Override
public void customize(LibraryCustomization customization) {
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    foo.renameMethod("isSupportsUnicode", "supportsUnicode");
}
```

will generate
```java
public class Foo {
    private boolean supportsUnicode;

    public boolean supportsUnicode() {
        return this.supportsUnicode;
    }
}
```

All references of `isSupportsUnicode()` will be modified to `supportsUnicode()`. When a valid value is provided, this customization is guaranteed to not break the build.

## Refactor: Generate the getter and setter methods for a property
A property `active` in the `Foo` class
```java
public class Foo {
    private boolean active;
}
```

with customization

```java
@Override
public void customize(LibraryCustomization customization) {
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    foo.generateGetterAndSetter("active");
}
```

will generate
```java
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

If the class already contains a getter or a setter method, the current method will be kept. This customization is guaranteed to not break the build.

## Refactor: Rename a property and its corresponding getter and setter methods
A property `whitelist` in the `Foo` class
```java
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

```java
@Override
public void customize(LibraryCustomization customization) {
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    foo.renameProperty("whiteList", "allowList");
}
```

will generate
```java
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
```java
public enum ImageFileType {
    GIF("gif"),
    JPG("jpg"),
    TIFF("tiff"),
    PNG("png");
}
```

with customization

```java
@Override
public void customize(LibraryCustomization customization) {
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization imageFileType = models.getClass("ImageFileType");
    foo.renameEnumMember("JPG", "JPEG");
}
```

will generate
```java
public enum ImageFileType {
    GIF("gif"),
    JPEG("jpg"),
    TIFF("tiff"),
    PNG("png");
}
```

This customization is guaranteed to not break the build.

## Javadoc: Set the description for a class / method
A class `Foo`
```java
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

```java
@Override
public void customize(LibraryCustomization customization) {
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    foo.classJavadoc().setDescription("A Foo object stored in Azure.")
    foo.methodJavadoc("setActive").setDescription("Set the active value.");
}
```

will generate

```java
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
```java
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
```java
@Override
public void customize(LibraryCustomization customization) {
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    foo.methodJavadoc("setActive").setParam("active", "if the foo object is in active state");
}
```

will generate
```java
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
```java
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
```java
@Override
public void customize(LibraryCustomization customization) {
    PackageCustomization models = customization.getPackage("com.azure.myservice.models");
    ClassCustomization foo = models.getClass("Foo");
    foo.methodJavadoc("setActive").setReturn("the current foo object");
}
```

will generate
```java
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
```java
public class FooClient {
    /**
     * Create a Foo object.
     *
     * @param foo the foo object to create in Azure
     * @return the response for creating the foo object
     */
    public CreateFooResponse createFoo(Foo foo) {
        /* REST call to create foo */
    }
}
```

with customization
```java
@Override
public void customize(LibraryCustomization customization) {
    PackageCustomization root = customization.getPackage("com.azure.myservice");
    ClassCustomization fooClient = root.getClass("FooClient");
    fooClient.methodJavadoc("createFoo").addThrows("HttpResponseException", "An unsuccessful response is received");
}
```

will generate
```java
public class FooClient {
    /**
     * Create a Foo object.
     *
     * @param foo the foo object to create in Azure
     * @return the response for creating the foo object
     * @throws HttpResponseException An unsuccessful response is received
     */
    public CreateFooResponse createFoo(Foo foo) {
        /* REST call to create foo */
    }
}
```

# Project structure
## extension-base
This contains the base classes and utilities for creating an AutoRest extension in Java. It handles the JSON RPC communications with AutoRest core, provides JSON and YAML parsers, and provides the POJO models for the code model output from [modelerfour](https://github.com/Azure/autorest.modelerfour/).

Extend from `NewPlugin.java` class if you are writing a new extension in Java.

## javagen
This contains the actual generator extension, including mappers that maps a code model to a Java client model, and templates that writes the Java client models into .java files.

## fluentgen
This contains the [generator extension for Azure Management Libraries](#additional-settings-for-fluent).

## tests
This contains the generated classes from the [test swaggers](https://github.com/Azure/autorest.testserver/tree/master/swagger) in `src/main`. The code here should always be kept up-to-date with the output of the generator in `javagen`.

This also contains test code for these generated code under `src/test`. Running the tests will hit the test server running locally (see https://github.com/Azure/autorest.testserver for instructions) and verify the correctness of the generated code.

# Contributing

This project welcomes contributions and suggestions.  Most contributions require you to agree to a
Contributor License Agreement (CLA) declaring that you have the right to, and actually do, grant us
the rights to use your contribution. For details, visit https://cla.microsoft.com.

When you submit a pull request, a CLA-bot will automatically determine whether you need to provide
a CLA and decorate the PR appropriately (e.g., label, comment). Simply follow the instructions
provided by the bot. You will only need to do this once across all repos using our CLA.

This project has adopted the [Microsoft Open Source Code of Conduct](https://opensource.microsoft.com/codeofconduct/).
For more information see the [Code of Conduct FAQ](https://opensource.microsoft.com/codeofconduct/faq/) or
contact [opencode@microsoft.com](mailto:opencode@microsoft.com) with any additional questions or comments.

### Autorest plugin configuration
- Please don't edit this section unless you're re-configuring how the Java extension plugs in to AutoRest
AutoRest needs the below config to pick this up as a plug-in - see https://github.com/Azure/autorest/blob/master/docs/developer/architecture/AutoRest-extension.md

#### Javagen

``` yaml $(java) && !$(fluent)
use: $(this-folder)/javagen
```

``` yaml $(java) && $(fluent)
use: $(this-folder)/fluentgen
```

#### Help

```yaml
help-content:
  java:
    activationScope: java
    categoryFriendlyName: Java Generator
    settings:
      - key: enable-xml
        type: bool
        description: Generates models and clients that can be sent in XML over the wire. Default is false
      - key: client-side-validations
        type: bool
        description: Generate validations for required parameters and required model properties. Default is false.
      - key: generate-client-as-impl
        type: bool
        description: Append "Impl" to the names of service clients and method groups and place them in the `implementation` sub-package. Default is false.
      - key: generate-client-interfaces
        type: bool
        description: Implies `--generate-client-as-impl` and generates interfaces for all the "Impl"s. Default is false.
      - key: generate-sync-async-clients
        type: bool
        description: Implies `--generate-client-as-impl` and generates sync and async convenience layer clients for all the "Impl"s. Default is false.
      - key: implementation-subpackage=STRING
        type: string
        description: The sub-package that the Service client and Method Group client implementation classes will be put into. Default is `implementation`.
      - key: models-subpackage=STRING
        type: string
        description: The sub-package that Enums, Exceptions, and Model types will be put into. Default is `models`.
      - key: add-context-parameter
        type: bool
        description: Indicates whether the `com.azure.core.util.Context` parameter should be included in generated proxy methods. Default is false.
      - key: context-client-method-parameter
        type: bool
        description: Implies `--add-context-parameter` and indicates whether the `com.azure.core.util.Context` parameter should also be included in generated client methods. Default is false.
      - key: sync-methods
        type: string
        description: \[all|essential|none] Specifies mode for generating sync wrappers. Supported value are <br>&nbsp;&nbsp;`essential` - generates only one sync returning body or header (default) <br>&nbsp;&nbsp;`all` - generates one sync method for each async method<br>&nbsp;&nbsp;`none` - does not generate any sync methods
      - key: required-parameter-client-methods
        type: bool
        description: Indicates whether client method overloads with only required parameters should be generated. Default is false.
      - key: custom-types
        type: string
        description: \[COMMA,SEPARATED,STRINGS] Specifies a list of files to put in the package specified in `--custom-types-subpackage`.
      - key: custom-types-subpackage
        type: string
        description: The sub-package that the custom types should be generated in. The types that custom types reference, or inherit from will also be automatically moved to this sub-package. **Recommended usage** You can set this lue to `models` and set `--models-subpackage=implementation.models`to generate models to `implementation.models` by default and pick specific models to be public through `--custom-types=`.
      - key: client-type-prefix
        type: string
        description: The prefix that will be added to each generated client type.
      - key: model-override-setter-from-superclass
        type: bool
        description: Indicates whether to override the superclass setter method in model. Default is false.

  javafluent:
    activationScope: fluent
    categoryFriendlyName: Java fluent Generator
    settings:
      - key: fluent
        type: string
        description: Enum. `LITE` for Fluent Lite; `PREMIUM` for Fluent Premium. Case insensitive. Default is `PREMIUM` if provided as other values.
      - key: fluent-subpackage
        type: string
        description: String. The sub-package that vanilla client and builder will be put into. Default is `fluent`.
      - key: pom-file
        type: string
        description: String. Name for Maven POM file. Default is `pom.xml`.
      - key: package-version
        type: string
        description: String. Version number for Maven artifact. Default is `1.0.0-beta.1`.
      - key: service-name
        type: string
        description: String. Service name used in Manager class and other documentations. If not provided, service name is deduced from `title` configure (from swagger or readme).
      - key: sdk-integration
        type: bool
        description: Boolean. Integrate to [azure-sdk-for-java](https://github.com/Azure/azure-sdk-for-java/). Default is `false`. Provide `output-folder` as absolute path for best performance.
      - key: add-inner
        type: string
        description: CSV. Treat as inner class (move to `fluent.models` namespace, append `Inner` to class name).
      - key: remove-inner
        type: string
        description: CSV. Exclude from inner classes.
      - key: rename-model
        type: string
        description: CSV. Rename classes. Each item is of pattern `from:to`.
      - key: remove-model
        type: string
        description: CSV. Remove classes.
      - key: name-for-ungrouped-operations
        type: string
        description: String. Name for ungrouped operation group. Default to `ResourceProviders` for Lite.
```