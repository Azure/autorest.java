# Developer Guide

## AutoRest Java

AutoRest Java is the Java language generator for AutoRest.

As [NPM package](https://github.com/Azure/autorest/blob/main/docs/developer/writing-an-extension.md), it is defined by the [`package.json`](https://github.com/Azure/autorest.java/blob/main/package.json) located in project root, and the `package.json` in `javagen` and `fluentgen`.

As AutoRest plugin, it is defined by the [YAML section in `readme.md`](https://github.com/Azure/autorest.java/blob/main/javagen/readme.md), located in the same folders of `package.json`.

## Build and Test

### Build

Build is configured via Maven.

There is several build profiles:
- **`local` profile**: It is required to be enabled. It uses `maven-shade-plugin` to combine project output to a single jar.
- **`testVanilla` profile**: It enables the integrated vanilla tests, which is the common ground for all modules.
- **testAzure profile**: It enables the integrated Azure tests, which tests handling of some advanced [AutoRest Extensions for OpenAPI 2.0](https://github.com/Azure/autorest/blob/main/docs/extensions/readme.md).
- **`testFluent` profile**: It enables the integrated Fluent tests, which tests generation of Fluent management SDKs for [ARM](https://docs.microsoft.com/azure/azure-resource-manager/management/overview).

### Unit Test

Because the input of AutoRest Java is a complicated code model of the OpenAPI, it is relatively difficult to add unit tests.

There is unit tests under fluentgen. It depends on [recorded YAML output of fluentnamer](https://github.com/Azure/autorest.java/blob/v4.0.34/fluentgen/src/test/resources/code-model-fluentnamer-locks.yaml), and a [mocked `FluentGen`](https://github.com/Azure/autorest.java/blob/v4.0.34/fluentgen/src/test/java/com/azure/autorest/fluent/TestUtils.java#L39-L79).

### Integrated Test

Vanilla tests and Azure tests both depends on [AutoRest Test Server](https://github.com/Azure/autorest.testserver), which provides OpenAPI 2.0 swaggers and NodeJS test server which implements the swaggers.

The generated code is checked-in to the repository. [`generate` or `generate.bat` script](https://github.com/Azure/autorest.java/blob/main/generate) in project root will re-generate the code.
CI will start the NodeJS test server and run the tests, which communicate with the test server.

Test server can be installed and ran locally.
```
npm install
npm run testserver-run
```

Fluent tests is different. It depends on the real OpenAPI 2.0 swaggers for ARM services.

The generated code is not checked-in. Instead, code is generated on-the-fly during the test, via [`Initialize-Tests.ps1` script](https://github.com/Azure/autorest.java/blob/main/fluent-tests/Initialize-Tests.ps1).

`CompilationTests` relies on Java compiler to pin-down certain features of the generated code, while `RuntimeTests` executes the generated code and verify the result.

## Use local AutoRest Java

Use `--use` option of the AutoRest to load the local AutoRest Java build (instead of the one downloaded from pre-release).

The `name` of `@autorest/java` in `package.json` makes sure that it is loaded when `--java` option is provided.

## Publish `@autorest/java` to NPM

1. Update `version` in root `package.json`, merge the PR.
2. Run "autorest.java - publish" in (internal) DevOps.
3. Update Release notes in [GitHub Releases](https://github.com/Azure/autorest.java/releases).

## Publish customization libraries to Maven

Modules in following folders are published to Maven
- **`extension-base` module**: [azure-autorest-extension](https://central.sonatype.com/artifact/com.azure.tools/azure-autorest-extension)
- **`customization-base` module**: [azure-autorest-customization](https://central.sonatype.com/artifact/com.azure.tools/azure-autorest-customization)

The publish task involves local build and manual operation via Partner release pipeline.

## Logging

**DO NOT** log to stdout. AutoRest uses stdin and stdout as [RPC channel for plugins](https://github.com/Azure/autorest/blob/main/docs/developer/writing-an-extension.md#rpc-channel).

For formal logging, use `PluginLogger` in pattern `Logger logger = new PluginLogger(<PluginEntry>.getPluginInstance(), <Class>.class)`.

## AutoRest

- [AutoRest in the narrow sense](https://www.npmjs.com/package/autorest) is the command line tool (CLI) for the pipeline.
- [AutoRest Core](https://www.npmjs.com/package/@autorest/core) is the core functionality of the AutoRest pipeline. Its code is at [core](https://github.com/Azure/autorest/tree/main/packages/extensions/core). 
- [AutoRest Modeler Four](https://www.npmjs.com/package/@autorest/modelerfour) is the modeler extension. Its code is at [modelerfour](https://github.com/Azure/autorest/tree/main/packages/extensions/modelerfour). AutoRest Java connects to the output of the modeler.

It is easy to get confused about AutoRest and AutoRest Core.
If one run `autorest --info`, one might see below:
```
AutoRest code generation utility [cli version: 3.3.2; node: v12.16.1]
(C) 2018 Microsoft Corporation.
https://aka.ms/autorest

Showing All Installed Extensions

 Type       Extension Name                           Version      Location
 core       @autorest/core                           3.4.5        C:\Users\<user>\.autorest\@autorest_core@3.4.5
 core       @autorest/core                           3.5.1        C:\Users\<user>\.autorest\@autorest_core@3.5.1
 extension  @autorest/java                           4.0.30       C:\Users\<user>\.autorest\@autorest_java@4.0.30
 extension  @autorest/modelerfour                    4.18.1       C:\Users\<user>\.autorest\@autorest_modelerfour@4.18.1
 extension  @autorest/modelerfour                    4.19.3       C:\Users\<user>\.autorest\@autorest_modelerfour@4.19.3
```

The current installed AutoRest version is 3.3.2, while downloaded AutoRest Core version is 3.4.5 and 3.5.1.
And downloaded Modeler Four version is 4.18.1 and 4.19.3, AutoRest Java version is 4.0.30.

## Design on AutoRest Java

See [Design](./design.md).

## Debug AutoRest Java

To enable debugging, first configure a remote JVM debugger. In IntelliJ, you can do this by going to
Run -> Edit Configuration. On the configuration window, click on Add New Configuration -> Remote JVM Debug and 
configure the remote debugger. Set the debugger mode to "Attach to remote JVM", Transport to "Socket", Host to 
"localhost" and Port to "5005". Set the command line arguments to "-agentlib:jdwp=transport=dt_socket,server=y,
suspend=n,address=*:5005".

![img.png](https://raw.githubusercontent.com/Azure/autorest.java/main/docs/images/remote-debugger-config.png)

After the remote debugger is configured, run autorest with this additional argument `--java.debugger`. The AutoRest 
process will block until the JVM debugger is attached, so, after the AutoRest process pauses, start the debugger 
which will connect to the remote debugger and this will hit the breakpoints set in the "Javagen" plugin.
