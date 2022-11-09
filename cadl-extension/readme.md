# Prerequisite

Install [Node.js](https://nodejs.org/en/download/) 16 or above. (Verify by `node --version`)

Install [Java](https://docs.microsoft.com/java/openjdk/download) 11 or above. (Verify by `java --version`)

Install [Cadl](https://github.com/microsoft/cadl/) 0.36. 

# Initialize Cadl Project

Follow [Cadl Getting Started](https://github.com/microsoft/cadl/#using-node--npm) to initialize your Cadl project.

Make sure `npx cadl compile .` runs correctly.

# Add Cadl-Java

Make sure the version of [Cadl-java release](https://github.com/Azure/autorest.java/releases) depends on same version of "@cadl-lang/compiler" as in your Cadl project.

Modify `package.json`, add one line under `dependencies`:
```diff
    "dependencies": {
      "@cadl-lang/compiler": "^0.36.0",
      "@cadl-lang/rest": "^0.18.0",
      "@azure-tools/cadl-azure-core": "^0.8.0",
+      "@azure-tools/cadl-java": "0.1.0-dev.11"
    },
```

Run `npm install` again to install `@azure-tools/cadl-java`.

Modify (or create) `cadl-project.yaml`, add one line under `emitters`:
```diff
emitters:
+  "@azure-tools/cadl-java": true
```

# Generate Java

Same `npx cadl compile .` or `npx cadl compile . --outputPath=<target-folder>`.

If `outputPath` option is not provided, generated Java code will be under `cadl-output` folder.

# Optional Configuration

## SDK

One can further configure the SDK generated, using the emitter options on `@azure-tools/cadl-java`.

```yaml
emitters:
  "@azure-tools/cadl-java":
    "output-dir": "./azure-ai-language-authoring"
    "namespace": "com.azure.ai.language.authoring"
    "service-name": "Authoring"
    "partial-update": true
    "service-versions":
      - "2022-05-15-preview"
```

## Convenience API

Use "convenienceAPI" decorator from [cadl-dpg](https://github.com/Azure/cadl-azure/tree/main/packages/cadl-dpg).
