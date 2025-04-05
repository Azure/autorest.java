# Prerequisite

Install [Node.js](https://nodejs.org/en/download/) 20 or above. (Verify by running `node --version`)

Install [Java](https://docs.microsoft.com/java/openjdk/download) 11 or above. (Verify by running `java --version`)

Install [Maven](https://maven.apache.org/download.cgi). (Verify by running `mvn --version`)

Install [TypeSpec](https://typespec.io/) 1.0.0.

# Initialize TypeSpec Project

Follow [TypeSpec Getting Started](https://typespec.io/docs/) to initialize your TypeSpec project.

Make sure `npx tsp compile .` runs correctly.

# Add TypeSpec-Java

Run the command `npm install @azure-tools/typespec-java`.

# Generate Java

`npx tsp compile client.tsp --emit=@azure-tools/typespec-java` or `npx tsp compile client.tsp --emit=@azure-tools/typespec-java --options='@azure-tools/typespec-java.emitter-output-dir=<target-folder>'`.

If `emitter-output-dir` option is not provided, generated Java code will be under `tsp-output/@azure-tools/typespec-java` folder.

# Optional Configuration

## SDK

One can further configure the SDK generated, using the emitter options on `@azure-tools/typespec-java`.

```yaml
emit:
  - "@azure-tools/typespec-java"
options:
  "@azure-tools/typespec-java":
    emitter-output-dir: "{project-root}/azure-ai-language-authoring"
    service-name: "Authoring"
    generate-samples: true
    generate-tests: true
    partial-update: false
    api-version: "2023-11-01"
```

### SDK Related Configuration in Emitter options `tspconfig.yaml`

#### `api-version`

By default, the emitter generates code from the latest api-version in TypeSpec.

In cases where a service needs to generate code from a previous api-version, set the value to that specific api-version.

#### `service-name`

This emitter option is for management-plane SDK.

It is advised for the service to set an appropriate service name. E.g. `service-name: Standby Pool`.

The name will appear in documentation (e.g. "README.md") that describes the service and the SDK. It would also affect the name of the entry class of the SDK.

#### `partial-update`

Default value is `false`.

This emitter option is for data-plane SDK.

In the case that the generated code is not good enough for the SDK, the developer can choose to customize the generated SDK via [Partial Update](https://github.com/Azure/azure-sdk-for-java/wiki/TypeSpec-Java-QuickStart#partial-update). Set the value to `true` to enable this feature.

#### `generate-samples`

Default value is `true`. The emitter generates code samples under the `generated` package.

For data-plane SDK, the generated samples are for reference. The motivation is to show how samples can be written. It is expected that developers write correct and concise samples outside of the `generated` package.

If there is customization of the generated SDK, the generated samples may not compile after customization. One can delete the `generated` package, and set the value to `false` to disable the generated samples.

#### `generate-tests`

Default value is `true`. The emitter generates tests under the `generated` package.

For data-plane SDK, the generated tests are (disabled) live tests. The motivation is to show how test cases can be written in Java with JUnit. It is expected that developers write runnable tests outside of the `generated` package.

For management-plane SDK, the generated tests are mock tests, for JSON serialization and API requests.

If there is customization of the generated SDK, the generated tests may not compile after customization. One can delete the `generated` package, and set the value to `false` to disable the generated tests.

### SDK Related Configuration in Client Customization `client.tsp`

For Java SDK, it is required to provide a Java package name via `@clientNamespace`.

Here is an example:

```ts
@@clientNamespace(Client, "com.azure.ai.openai", "java");
@@clientNamespace(Azure.OpenAI, "com.azure.ai.openai", "java");
```

For management-plane SDK, one can provide a client name via `@clientName`.

Here is an example:

```ts
@@clientNamespace(Microsoft.StandbyPool,
  "com.azure.resourcemanager.standbypool",
  "java"
);
@@clientName(Microsoft.StandbyPool, "StandbyPoolManagementClient", "java");
```

## Convenience API

By default, TypeSpec-Java generates all protocol APIs and convenience APIs.
A few exceptions are API of JSON Merge Patch, and API of long-running operation with ambiguous response type.

See "convenientAPI" decorator from [typespec-client-generator-core](https://github.com/Azure/typespec-azure/tree/main/packages/typespec-client-generator-core).

# Customization

All post-code customizations listed in this [documentation](https://github.com/Azure/autorest.java/tree/main/customization-base/README.md) are supported for code generated from TypeSpec.

To configure customization with TypeSpec, Java's emitter options should include a `customization-class`. The `customization-class` option should specify the path to the file containing the customization code relative to `emitter-output-dir`. Note that the path should end with `src/main/java/<YourCustomizationClassName>.java`. The recommended practice is to place the customization class in `<output-dir>/customization/src/main/java/<YourCustomizationClassName>.java` and the `customization-class` option will have the value of `customization-class: customization/src/main/java/<YourCustomizationClassName>.java`. See example `tspconfig.yaml` below:

```yaml
emit:
  - "@azure-tools/typespec-java"
options:
  "@azure-tools/typespec-java":
    emitter-output-dir: "{project-root}/azure-ai-language-authoring"
    namespace: "com.azure.ai.language.authoring"
    customization-class: customization/src/main/java/MyCustomization.java
```

# Changelog

See [changelog](https://github.com/Azure/autorest.java/blob/main/typespec-extension/changelog.md).

# Troubleshooting

### Enable logging in Java code

To enable logging, use `tspconfig.yaml` to add the `loglevel: ` option. Typically, `tspconfig.yaml` file will be
located in the same directory as the `<target.tsp>` file. The `loglevel` setting is a developer option and should be set under `options->dev-options`. The acceptable values for `loglevel` are
`off`, `debug`, `info`, `warn` and `error`. A sample `tspconfig.yaml` is shown below that enables logging at `info` level. By default,
logging is enabled at `error` level.

```yaml
emit:
  - "@azure-tools/typespec-java"
options:
  "@azure-tools/typespec-java":
    emitter-output-dir: "{project-root}/tsp-output"
    namespace: "com.azure.ai.language.authoring"
    dev-options:
      loglevel: info
```

### Debugging Java code

In order to set breakpoints and debug Java code locally on your development workspace, use the `tspconfig.yaml` file to
set the `debug` option to `true` under `options->dev-options` as shown in the example below. If the `debug` option is set
to `true`, then `tsp compile <target.tsp>` command will start the emitter which then invokes the Java process but the process
will be suspended until a debugger is attached to the process. The process listens on port 5005. Run the remote debugger
with this port on your IntelliJ or VS Code to connect to the Java process. This should now run the Java code generator
and breaks at all applicable breakpoints.

The remote debugger configuration is shown below for reference.

![img.png](https://raw.githubusercontent.com/Azure/autorest.java/main/docs/images/remote-debugger-config.png)

```yaml
emit:
  - "@azure-tools/typespec-java"
options:
  "@azure-tools/typespec-java":
    emitter-output-dir: "{project-root}/tsp-output"
    namespace: "com.azure.ai.language.authoring"
    dev-options:
      debug: true
```

### New version of `@typespec/compiler` etc.

Force an installation of new version via deleting `package-lock.json` and `node_modules` in `./typespec-extension` folder.

```shell
rm -rf node_modules
rm package-lock.json
```
