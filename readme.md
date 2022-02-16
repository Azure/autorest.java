# Overview
This is the next gen (v4) of AutoRest Java generator. It's built on AutoRest v3, written in Java, and supports OpenAPI3. It generates clients that work with `com.azure:azure-core`.

# Prerequisites
You need to have the following installed on your machine:

- Node.JS v10.x - v13.x
- Java 8+
- Maven 3.x

You need to have [autorest](https://www.npmjs.com/package/autorest) installed through NPM:

```bash
npm install -g autorest
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

To build from source code, clone this repo and checkout to main branch. Make sure all prerequisites are met, and run

```bash
mvn package -P local
```

This will build a file `javagen-jar-with-dependencies.jar` under `javagen` module, a `preprocess-jar-with-dependencies.jar` under `preprocessor` module, a `fluentgen-jar-with-dependencies.jar` under `fluentgen` module, and a `fluentnamer-jar-with-dependencies.jar` under `fluentnamer` module.

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
Settings can be provided on the command line through `--name:value` or in a README file through `name: value`. The list of settings for AutoRest in general can be found at https://github.com/Azure/autorest/blob/main/docs/user/command-line-interface.md. The list of settings for AutoRest.Java specifically are listed below:

|Option                                                                &nbsp;| Description |
|------------------|-------------|
|`--enable-xml`|Generates models and clients that can be sent in XML over the wire. Default is false|
|`--client-side-validations`|Generate validations for required parameters and required model properties. Default is false.|
|`--generate-client-as-impl`|Append "Impl" to the names of service clients and method groups and place them in the `implementation` sub-package. Default is false.|
|`--generate-client-interfaces`|Implies `--generate-client-as-impl` and generates interfaces for all the "Impl"s. Default is false.|
|`--generate-sync-async-clients`|Implies `--generate-client-as-impl` and generates sync and async convenience layer clients for all the "Impl"s. Default is false.|
|`--generate-builder-per-client`| Requires `--generate-sync-async-clients`, and generates one ClientBuilder for each Client. Default is false.|
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
|`--service-interface-as-public`|Indicates whether to generate service interfaces as public. This resolves `SecurityManager` issues to prevent reflectively access non-public APIs. Default is false.|
|`--require-x-ms-flattened-to-flatten`|Indicates whether `x-ms-flattened` is required to annotated a class with `@JsonFlatten` if the discriminator has `.` in its name. Default is false.|
|`--client-flattened-annotation-target=TYPE,FIELD,NONE`|Indicates the target of `@JsonFlatten` annotation for `x-ms-client-flatten`. Default is `TYPE`. If value is `FIELD`, it implies `require-x-ms-flattened-to-flatten=true`.|
|`--disable-client-builder`|Indicates whether to disable generating the `ClientBuilder` class. Default is false.|
|`--skip-formatting`|Indicates whether to skip formatting Java file. This is for SDK that already contains a hand-written `ClientBuilder` class. Default is false.|
|`--polling`|Configures how to generate long running operations. See [Polling Configuration](#polling-configuration) to see more details on how to use this flag.|
|`--service-name`|Service name used in Client class and other documentations. If not provided, service name is deduced from `title` configure (from swagger or readme).|
|`--partial-update`|Indicates whether to support partial update for `Client`/`AsyncClient` classes and `ClientBuilder` class.|
|`--pass-discriminator-to-child-deserialization`|Indicates whether the discriminator property is passed to subclass deserialization. Default is false.|
|`--default-http-exception-type`|Indicates the fully-qualified class name that should be used as the default HTTP exception type. The class must extend from `HttpResponseException`.|
|`--use-default-http-status-code-to-exception-type-mapping`|Indicates whether a default HTTP status code to exception mapping should be used if one isn't provided.|
|`--http-status-code-to-exception-type-mapping`|Indicates the HTTP status code to exception mapping that should be used. All exception types must be fully-qualified and extend from `HttpResponseException`.|

## Settings for minimal clients (low-level clients)

`low-level-client` option enables the generator to generate code for minimal clients.

`low-level-client` option will change the default value for some vanilla options.
For example, `generate-client-interfaces`, `generate-client-as-impl`, `generate-sync-async-clients`, `generate-builder-per-client`, `add-context-parameter`, `context-client-method-parameter` option is by default `true`.
`polling` is by default enabled as default settings globally (`polling={}`).

`sdk-integration` option can be used for integrating to [azure-sdk-for-java](https://github.com/Azure/azure-sdk-for-java/).

`generate-samples` option can be used for generating samples for client.
`generate-tests` option can be used for generating tests for client.

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
| `--generate-samples` | Boolean. Generate samples from `x-ms-examples` in swagger. Default is `false`. |
| `--add-inner` | CSV. Treat as inner class (move to `fluent.models` namespace, append `Inner` to class name). |
| `--remove-inner` | CSV. Exclude from inner classes. |
| `--rename-model` | CSV. Rename classes. Each item is of pattern `from:to`. |
| `--remove-model` | CSV. Remove classes. |
| `--preserve-model` | CSV. Preserve classes from clean-up. |
| `--name-for-ungrouped-operations` | String. Name for ungrouped operation group. Default to `ResourceProviders` for Lite. |

`fluent` option will change the default value for some vanilla options.
For example, `generate-client-interfaces`, `context-client-method-parameter`, `required-parameter-client-methods`, `model-override-setter-from-superclass` option is by default `true`.

The code formatter would require Java 11+ runtime.

## Polling configuration
Polling configurations can be set through `--polling` setting globally or for each operation. The format is a key value map specified below:

```
polling:
  {operationId}:
    strategy: {strategy}
    intermediate-type: {intermediate-type}
    final-type: {final-type}
    poll-interval: {poll-interval}
```

With the fields specified below:

|Field|Type|Required|Description|Example|
|-----|----|--------|-----------|-------|
|operationId|String|true|The `operationId` of the operation. For global polling configuration, use `default`. Case insensitive.|`Pets_put`|
|strategy|String|false|The invocation to construct a polling strategy. Use fully qualified class name if outside the implementation subpackage specified in `namespace` & `implementation-subpackage`. Use dynamic literals `{httpPipeline}`, `{context}`, `{serializerAdapter}`, `{intermediate-type}`, `{final-type}` if these components are required to construct the polling strategy. Default is `com.azure.core.util.polling.ChainedPollingStrategy.createDefault({httpPipeline}, {context})`.|`new com.azure.core.util.polling.OperationResourcePollingStrategy<>({httpPipeline}, {context})`|
|intermediate-type|String|false|The type of the polling intermediate type. Use fully qualified class name if outside the base package specified in `namespace`. Default is the return type specified on the operation in Swagger, or `BinaryData` if the operation returns `void`.|`PollResult`,`com.azure.core.util.BinaryData`|
|final-type|String|false|The type of the final result type. Use fully qualified class name if outside the base package specified in `namespace`. Default is the return type specified on the operation in Swagger, or `BinaryData` if the operation returns `void`.|`Pet`,`com.azure.core.util.BinaryData`|
|poll-interval|integer|false|The default interval in seconds to poll with (can be modified by users in `PollerFlux` and `SyncPoller`. Default is 1.|30|

To use default settings globally, use `--polling={}`.

## HTTP Status Code to Exception Type Handling

By default, Swagger definitions will contain an exception type to be thrown when any error HTTP status code is seen. Swaggers
may also define the error HTTP status code to exception type mapping, but isn't always the case. Or, it may be needed to change
the default exception type to a custom type. In these cases it is possible to configure the error HTTP status code to exception
type handling by using the configurations `--default-http-exception-type`, `--use-default-http-status-code-to-exception-type-mapping`, 
and `--http-status-code-to-exception-type-mapping`. 

NOTE: Using the configurations on an already shipped library will likely result in runtime breaking changes as most 
libraries are already using a sub-class type of `HttpResponseException` and changing sub-types is a breaking change.

### Default Http Exception Type

The default exception type is used for all error HTTP response status codes that do not have an explicit exception type
configured. Generally, there is no error HTTP response status code to exception type mapping, so this will become your
general exception type. The following is an example of configuring `com.azure.core.exception.HttpResponseException` as
the default exception type:

`autorest --default-http-exception-type=com.azure.core.exception.HttpResponseException`

or in the Swagger README configuration:

```
default-http-exception-type: com.azure.core.exception.HttpResponseException
```

If this configuration isn't used the default exception type will be based on the Swagger definition.

### Use Default HTTP Status Code to Exception Type Mapping

The default error HTTP status code to exception type map is the following:

|Status Code|Exception Type|
|-----------|--------------|
|401|com.azure.core.exception.ClientAuthenticationException|
|404|com.azure.core.exception.ResourceNotFoundException|
|409|com.azure.core.exception.ResourceModifiedException|

By default, this mapping isn't used, to use it pass the `--use-default-http-status-code-to-exception-type-mapping` to 
the generator or in the Swagger README configuration:

```
use-default-http-status-code-to-exception-type-mapping: true
```

### Custom HTTP Status Code to Exception Type Mapping

If you need to have a custom mapping it is possible to do so by using `--http-status-code-to-exception-type-mapping`. 
This requires additional configuration in the Swagger README definition as it is a mapping of HTTP status code to 
exception. The following is an example:

```
http-status-code-to-exception-type-mapping:
  404: com.azure.core.exception.ResourceNotFoundException
  412: com.azure.core.exception.ResourceExistsException
```

### Example

The HTTP status code to exception type handling configurations can be used together to offer a nice baseline default
with additional customizations as need. For example, if you wanted to configure the default exception type, use most of
the default error HTTP status code to exception type, and customize one error HTTP status code exception type the following
configurations can be used.

```
default-http-exception-type: com.azure.core.exception.HttpResponseException
use-default-http-status-code-to-exception-type-mapping: true
http-status-code-to-exception-type-mapping:
  404: com.azure.core.exception.ResourceExistsException
```

This results in the following error HTTP response to exception type mapping:

|Status Code|Exception Type|
|-----------|--------------|
|401|com.azure.core.exception.ClientAuthenticationException|
|404|com.azure.core.exception.ResourceExistsException|
|409|com.azure.core.exception.ResourceModifiedException|
|*|com.azure.core.exception.HttpResponseException|

Notices how 404 changes from the default `com.azure.core.exception.ResourceNotFoundException` to
`com.azure.core.exception.ResourceExistsException`.

# Minimal clients (low-level clients)

You can generate the output as minimal clients, a.k.a., low-level clients with `--low-level-client` flag. The models will not be generated and the methods in the clients will be generated as [protocol methods](https://github.com/Azure/azure-sdk-for-java/wiki/Protocol-Methods).

The generated code has the following structure:

```
- pom.xml
- /src/main/java
  |
  - module-info.java
  - /com/azure/<group>/<service>
    |
    - <Service>Builder.java
    - <Service>Client.java
    - <Service>AsyncClient.java
    - /implementation
      |
      - <Service>ClientImpl.java
```

and requires `azure-core` 1.19.0-beta.1 as a dependency.

# Customizations

For guidance on using the post-code generation customization framework see its [documentation](https://github.com/Azure/autorest.java/tree/main/customization-base/README.md).

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

``` yaml $(java) && !$(fluent) && !$(android)
use: $(this-folder)/javagen
```

``` yaml $(java) && $(fluent) && !$(android)
use: $(this-folder)/fluentgen
```

```  yaml $(java) && $(android)
use: $(this-folder)/androidgen
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
      - key: generate-builder-per-client
        type: bool
        description: Requires `--generate-sync-async-clients`, and generates one ClientBuilder for each Client. Default is false.
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
      - key: low-level-client
        type: bool
        description: Indicates whether to generate clients and methods as protocol(low level) clients and methods. Default is false.
      - key: client-flattened-annotation-target
        type: string
        description: \[TYPE,FIELD] Indicates the target of `@JsonFlatten` annotation for `x-ms-client-flatten`. Default is `TYPE`. If value is `FIELD`, it implies `require-x-ms-flattened-to-flatten=true`.
      - key: disable-client-builder
        type: bool
        description: Indicates whether to disable generating the `ClientBuilder` class. This is for SDK that already contains a hand-written `ClientBuilder` class. Default is false.
      - key: skip-formatting
        type: bool
        description: Indicates whether to skip formatting Java file. Default is false.
      - key: polling
        type: string
        description: Configures how to generate long running operations. See [Polling Configuration](https://github.com/Azure/autorest.java#polling-configuration) to see more details on how to use this flag.
      - key: service-name
        type: string
        description: Service name used in Client class and other documentations. If not provided, service name is deduced from `title` configure (from swagger or readme).
      - key: partial-update
        type: bool
        description: Indicates whether to support partial update for `Client`/`AsyncClient` classes and `ClientBuilder` class.
      - key: low-level-client
        type: bool
        description:  Indicates whether to generate code for minimal clients. Default is false.

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
      - key: generate-samples
        type: bool
        description: Boolean. Generate samples from `x-ms-examples` in swagger. Default is `false`.
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
      - key: preserve-model
        type: string
        description: CSV. Preserve classes from clean-up.
      - key: name-for-ungrouped-operations
        type: string
        description: String. Name for ungrouped operation group. Default to `ResourceProviders` for Lite.
```
