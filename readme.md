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
| `--pom-file` | String. Name for Maven POM file. Default is `pom.xml`. |
| `--package-version` | String. Version number for Maven artifact. Default is `1.0.0-beta.1`. |
| `--service-name` | String. Service name used in Manager class and other documentations. If not provided, service name is deduced from `title` configure (from swagger or readme). |
| `--sdk-integration` | Boolean. Integrate to [azure-sdk-for-java](https://github.com/Azure/azure-sdk-for-java/). Default is `false`. |
| `--track1-naming` | Boolean. Use track1 naming style (`withFoo` / `foo` as setter / getter). Default is `true`. |
| `--add-inner` | CSV. Treat as inner class (move to `fluent.models` namespace, append `Inner` to class name). |
| `--remove-inner` | CSV. Exclude from inner classes. |
| `--rename-model` | CSV. Rename classes. Each item is of pattern `from:to`. |
| `--name-for-ungrouped-operations` | String. Name for ungrouped operation group. |
| `--resource-property-as-subresource` | Boolean, experimental. Automatically correct input-only resource type as `SubResource`. Default is `false`. |

Also `fluent` option will change the default value for some vanilla options.
For example, `generate-client-interfaces`, `context-client-method-parameter`, `required-parameter-client-methods`, `model-override-setter-from-superclass` option is by default `true`.

The code formatter would require Java 11+ runtime.

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
