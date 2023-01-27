# Prerequisite

Install [Node.js](https://nodejs.org/en/download/) 16 or above. (Verify by `node --version`)

Install [Java](https://docs.microsoft.com/java/openjdk/download) 11 or above. (Verify by `java --version`)

Install [Cadl](https://github.com/microsoft/cadl/) 0.39. 

# Initialize Cadl Project

Follow [Cadl Getting Started](https://github.com/microsoft/cadl/#using-node--npm) to initialize your Cadl project.

Make sure `npx cadl compile .` runs correctly.

# Add Cadl-Java

Make sure the version of [Cadl-java release](https://github.com/Azure/autorest.java/releases) depends on same version of "@cadl-lang/compiler" as in your Cadl project.

Modify `package.json`, add one line under `dependencies`:
```diff
    "dependencies": {
      "@cadl-lang/compiler": "latest",
      "@cadl-lang/rest": "latest",
      "@azure-tools/cadl-azure-core": "latest",
+      "@azure-tools/cadl-java": "latest"
    },
```

Run `npm install` again to install `@azure-tools/cadl-java`.

Modify (or create) `cadl-project.yaml`, specify emit as `@azure-tools/cadl-java`:
```diff
emit:
  - "@azure-tools/cadl-java"
```

# Generate Java

`npx cadl compile client.cadl --emit=@azure-tools/cadl-java` or `npx cadl compile client.cadl --emit=@azure-tools/cadl-java --options='@azure-tools/cadl-java.emitter-output-dir=<target=folder>`.

If `emitter-output-dir` option is not provided, generated Java code will be under `cadl-output/@azure-tools` folder.

# Optional Configuration

## SDK

One can further configure the SDK generated, using the emitter options on `@azure-tools/cadl-java`.

```yaml
emit:
  - "@azure-tools/cadl-java"
options:
  "@azure-tools/cadl-java":
    emitter-output-dir: "{project-root}/azure-ai-language-authoring"
    namespace: "com.azure.ai.language.authoring"
    service-name: "Authoring"
    partial-update: true
    service-versions:
      - "2022-05-15-preview"
    namer: true
```

## Convenience API

Use "convenienceAPI" decorator from [cadl-dpg](https://github.com/Azure/cadl-azure/tree/main/packages/cadl-dpg).
