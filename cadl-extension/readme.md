# Prerequisite

Install [Node.js](https://nodejs.org/en/download/) 16 or above. (Verify by `node --version`)

Install [Java](https://docs.microsoft.com/en-us/java/openjdk/download) 11 or above. (Verify by `java --version`)

Install [Cadl](https://github.com/microsoft/cadl/).

# Initialize Cadl Project

Follow [Cadl Getting Started](https://github.com/microsoft/cadl/#using-node--npm) to initialize your Cadl project.

Make sure `cadl compile .` runs correctly.

# Add Cadl-Java

Make sure the version of [Cadl-java release](https://github.com/Azure/autorest.java/releases) depends on same version of "@cadl-lang/compiler" as in your Cadl project.

Modify `package.json`, add one line under `dependencies`:
```diff
    "dependencies": {
      "@cadl-lang/compiler": "^0.35.0",
      "@cadl-lang/rest": "^0.17.0",
      "@azure-tools/cadl-azure-core": "^0.7.0",
+      "@azure-tools/cadl-java": "https://github.com/Azure/autorest.java/releases/download/%40azure-tools%2Fcadl-java_0.1.0-dev.7/cadl-extension.azure-tools-cadl-java-0.1.0-dev.7.tgz"
    },
```

Run `npm install` again to install `@azure-tools/cadl-java`.

Modify (or create) `cadl-project.yaml`, add one line under `emitters`:
```diff
emitters:
+  '@azure-tools/cadl-java': true
```

# Generate Java

Same `cadl compile .` or `cadl compile . --outputPath=<target-folder>`.

If `outputPath` option is not provided, generated Java code will be under `cadl-output` folder.

# Optional Configuration (temporary solution)

[Side-car](https://github.com/Azure/autorest.java/wiki/Cadl-to-Java#side-car-design-changes-expected) for override namespace or service name.

[Convenience method](https://github.com/Azure/autorest.java/wiki/Cadl-to-Java#models-for-convenience-method-design-changes-expected) for generating models and methods for selected operations.
