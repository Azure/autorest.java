# Developer Guide

## AutoRest Java

AutoRest Java is the Java language generator for AutoRest.

As [npm package](https://github.com/Azure/autorest/blob/main/docs/developer/writing-an-extension.md), it is defined by the [`package.json`](https://github.com/Azure/autorest.java/blob/main/package.json) located in project root, and the `package.json` in `preprocessor`, `javagen`, `postprocessor`, `fluentnamer`, `fluentgen`, `androidgen`.

As AutoRest plugin, it is defined by the [YAML section in `readme.md`](https://github.com/Azure/autorest.java/blob/main/javagen/readme.md), located in the same folders of `package.json`.

## Build and Unit Test

### Build

Build is configured via Maven.

There is several build profiles:
- `local` profile. It is required to be enabled. It uses `maven-assembly-plugin` to combine project output to a single jar.
- `testVanilla` profile. It enables the integrated vanilla tests, which is the common ground for all modules.
- `testAzure` profile. It enables the integrated Azure tests, which tests handling of some advanced [AutoRest Extensions for OpenAPI 2.0](https://github.com/Azure/autorest/blob/main/docs/extensions/readme.md).
- `testFluent` profile. It enables the integrated Fluent tests, which tests generation of Fluent management SDKs for [ARM](https://docs.microsoft.com/azure/azure-resource-manager/management/overview).

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

The generated code is not checked-in. Instead, code is generated on-the-fly during the test, via [`prepare-tests.bat` script](https://github.com/Azure/autorest.java/blob/main/fluent-tests/prepare-tests.bat).

`CompilationTests` relies on Java compiler to pin-down certain features of the generated code, while `RuntimeTests` executes the generated code and verify the result.

## Use local AutoRest Java

Use `--use` option of the AutoRest to load the local AutoRest Java build (instead of the one downloaded from pre-release).

The `name` of `@autorest/java` in `package.json` makes sure that it is loaded when `--java` option is provided.

## Pre-release

1. Update `version` in root `package.json`.
2. Run "Pre-release autorest.java" in [GitHub Actions](https://github.com/Azure/autorest.java/actions). Provide the same `release version`.
3. Update Release notes in [GitHub Releases](https://github.com/Azure/autorest.java/releases).

## Logging

**DO NOT** log to stdout. AutoRest uses stdin and stdout as [PRC channel for plugins](https://github.com/Azure/autorest/blob/main/docs/developer/writing-an-extension.md#rpc-channel).

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
