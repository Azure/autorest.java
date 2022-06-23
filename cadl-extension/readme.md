# Experimental Java Client Emitter for CADL

Currently, the data flow for production would be,
1. CADL source
2. CADL compiler with `@cadl-lang/openapi3` emitter output OpenAPI 3.0
3. AutoRest convert OpenAPI 3.0 to ModelerFour
4. AutoRest.Java consumes ModelerFour data and generate Java files

In above setup, the source is CADL or OpenAPI 2.0 has no effect on AutoRest.Java, as it sees the same ModelerFour YAML.

---

This experiment connects AutoRest.Java (almost) directly to CADL compiler, skipping AutoRest and ModelerFour.

## Pipeline (manual)

At present, there is no automated pipeline like AutoRest CLI.
Therefore, we had to manually (or script) to connect following files.

1. CADL source, e.g. `main.cadl`
2. Output of Java client emitter and input of AutoRest.Java, `client-model.yaml`
3. Java files as output of AutoRest.Java

### Step 1, CADL and source

Read [CADL](https://github.com/microsoft/cadl/) readme first.

We uses `petstore` in [CADL samples](https://github.com/microsoft/cadl/tree/main/packages/samples).

Configure this in `cadl-project.yaml` to use Java client emitter,
```yaml
emitters:
  '@cadl-lang/java-client-emitter': true
```

### Step 2, Java client emitter

Currently this [@cadl-lang/java-client-emitter](package.json) is not published to npm.
Hence, one had to use dev build via `npm compile`.

Note that `npm link` does not work correctly for me on Windows.
In this case, one might had to copy the content of folder `cadl-extension` (excluding `node_modules`) to `node_modules/@cadl-lang/java-client-emitter` cadl source folder (the folder that one runs `cadl init`).

After `cadl compile .`, `client-model.yaml` will be at `cadl-output` folder.
Copy it to AutoRest.Java folder.

### Step 3, AutoRest.Java

After [`client-model.yaml`](../client-model.yaml) is ready, run [`CadlMain.java`](../javagen/src/test/java/com/azure/autorest/CadlMain.java).
Generated Java files will be at `cadl-sample` folder.

### BASH command lines

Overall, under `cadl-extension` folder,
```bash
npm install
npm run build
```

Under cadl source folder (first 2 lines are for the case of `npm link` not working),
```bash
mkdir node_modules/\@cadl-lang/java-client-emitter/
tar -c --exclude node_modules -C <autorest-java>/cadl-extension/ . | tar -x -C node_modules/\@cadl-lang/java-client-emitter/

cadl compile .
cp cadl-output/client-model.yaml <autorest-java>/client-model.yaml
```

Then run `CadlMain.java` in IDE to generate Java files.

## AutoRest.Java

[Design on AutoRest.Java](../docs/developer/design.md).
Quick recap, in AutoRest.Java, data flow is,

**preprocessor** module

1. YAML from ModelerFour
2. CodeModel of this YAML in Java
3. Transformation (rename according to Java guideline, add nextLink method for pagination, etc.)
4. Output YAML similar to ModelerFour

**javagen** module

5. YAML from preprocessor (similar to ModelerFour YAML)
6. CodeModel of this YAML in Java
7. Mapping to Client (client-model)
8. Generate Java files via Template

The experiment choose client-model (step 7) as the intermediate data between CADL emitter and javagen, as it is the last step of mostly Java-code-independent data in AutoRest.Java.

This likely not going to be the choice in production, as
1. client-model data itself got many duplications (e.g. 5 or more different ClientMethod for 1 REST API, on method overload for optional paramters, sync/async, Response or not, etc.).
2. Java code of client-model rely heavily on Builder pattern, which might not be compatible to YAML de-serialization (2-step construct of Java bean due to anchors).

In this experiment, there is very little change to AutoRest.Java.
Mostly on adding setter methods for client-model classes for YAML de-serialization (as mentioned, Builder pattern might not work).
Then a `CadlMain.java` to just run step 7 and 8 to read client-model YAML and generate Java files.

## Java client emitter

The [emitter](src/emitter.ts) in typescript is pretty straightforward.

CADL compiler calls `$onEmit` method, if proper configured.
Then the code requests necessary information from CADL compiler (notably, `getAllRoutes` method, for all REST APIs), compose the client-model YAML, write it to file.

One important technical trick is the registration of `ClassType` and `GenericType` schemas to `yaml.DEFAULT_SCHEMA`.
This will enable js-yaml generate proper tags for them, e.g. `!!com.azure.autorest.model.clientmodel.ClassType`, which is required for proper de-serialization of snakeyaml in Java interface or polymorphism cases.

## Appendix

[CADL](https://github.com/microsoft/cadl/) 0.30

Note that one might need a global install `npm install -g @cadl-lang/compiler` to run `cadl` without npx, as well as for CADL extension for VS code.
