# Developer Guide

## AutoRest Java

AutoRest Java is the Java language generator for AutoRest.

As npm package, it is defined by the [`package.json`](https://github.com/Azure/autorest.java/blob/v4/package.json) located in project root, and the `package.json` in `preprocessor`, `javagen`, `postprocessor`, `fluentnamer`, `fluentgen`, `androidgen`.

As AutoRest plugin, it is defined by the [YAML section in `readme.md`](https://github.com/Azure/autorest.java/blob/v4/javagen/readme.md), located in the same folders of `package.json`.

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

The generated code is checked-in to the repository. [`generate` or `generate.bat` script](https://github.com/Azure/autorest.java/blob/v4/generate) in project root will re-generate the code.
Maven will start the NodeJS test server and run the tests, which communicate with the test server.

Fluent tests is different. It depends on the real OpenAPI 2.0 swaggers for ARM services.

The generated code is not checked-in. Instead, code is generated on-the-fly during the test, via [`prepare-tests.bat` script](https://github.com/Azure/autorest.java/blob/v4/fluent-tests/prepare-tests.bat).

`CompilationTests` relies on Java compiler to pin-down certain features of the generated code, while `RuntimeTests` executes the generated code and verify the result.

## Pre-release

1. Update `version` in root `package.json`.
2. Run "Pre-release autorest.java" in [GitHub Actions](https://github.com/Azure/autorest.java/actions). Provide the same `release version`.
3. Update Release notes in [GitHub Releases](https://github.com/Azure/autorest.java/releases).

## Logging

**DO NOT** log to stdout. AutoRest uses stdin and stdout as pipeline for plugins.
