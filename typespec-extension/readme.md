# Prerequisite

Install [Node.js](https://nodejs.org/en/download/) 16 or above. (Verify by `node --version`)

Install [Java](https://docs.microsoft.com/java/openjdk/download) 11 or above. (Verify by `java --version`)

Install [TypeSpec](https://github.com/microsoft/typespec/) 0.42.

# Initialize TypeSpec Project

Follow [TypeSpec Getting Started](https://github.com/microsoft/typespec/#using-node--npm) to initialize your TypeSpec project.

Make sure `npx tsp compile .` runs correctly.

# Add TypeSpec-Java

Make sure the version of [TypeSpec-java release](https://github.com/Azure/autorest.java/releases) depends on same version of "@typespec/compiler" as in your TypeSpec project.

Modify `package.json`, add one line under `dependencies`:
```diff
    "dependencies": {
      "@typespec/compiler": "latest",
      "@typespec/rest": "latest",
      "@azure-tools/typespec-azure-core": "latest",
+      "@azure-tools/typespec-java": "latest"
    },
```

Run `npm install` again to install `@azure-tools/typespec-java`.

Modify (or create) `tspconfig.yaml`, specify emit as `@azure-tools/typespec-java`:
```diff
emit:
  - "@azure-tools/typespec-java"
```

# Generate Java

`npx tsp compile client.tsp --emit=@azure-tools/typespec-java` or `npx tsp compile client.tsp --emit=@azure-tools/typespec-java --options='@azure-tools/typespec-java.emitter-output-dir=<target=folder>`.

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
    namespace: "com.azure.ai.language.authoring"
    service-name: "Authoring"
    partial-update: false
    service-versions:
      - "2022-05-15-preview"
    namer: false
    generate-samples: true
    generate-tests: true
```

## Convenience API

By default, TypeSpec-Java generates all protocol APIs and convenience APIs.
A few exceptions are API of JSON Merge Patch, and API of long-running operation with ambiguous response type.

See "convenientAPI" decorator from [typespec-client-generator-core](https://github.com/Azure/typespec-azure/tree/main/packages/typespec-client-generator-core).

# Changelog

See [changelog](https://github.com/Azure/autorest.java/blob/main/typespec-extension/changelog.md).
