# Developer Guide for TypeSpec Emitter

## TypeSpec Java

As [NPM package](https://www.npmjs.com/package/@azure-tools/typespec-java), it is the Java client [emitter](https://typespec.io/docs/extending-typespec/emitters-basics/) for Azure SDKs.

## Build and Test

At present, almost all the code of the emitter is in [microsoft/typespec repository](https://github.com/microsoft/typespec/tree/main/packages/http-client-java).

### Build

NPM package is in `typespec-extension` folder.

The [`Build-TypeSpec.ps1` script](../../../Build-TypeSpec.ps1) builds the emitter.
It first build the JAR using Maven, copy it into `typespec-extension` folder, then build and pack the NPM package to a `tgz` file.

### Integrated Test

End-to-end test resides in `typespec-tests` folder.

At present, the tests is copied from 

The [`Generate.ps1` script](../../../typespec-tests/Generate.ps1) generates the test code from [http-specs](https://github.com/microsoft/typespec/tree/main/packages/http-specs) and [azure-http-specs](https://github.com/Azure/typespec-azure/tree/main/packages/azure-http-specs).

`npm run spector-serve` starts the mock server.

Then standard Maven Surefire would run the tests.

## Publish `@azure-tools/typespec-java` to NPM

1. Update `version` in `typespec-extension/package.json`.
2. Update `typespec-java` `version` in `typespec-test/package.json`.
3. Update `typespec-extensions/changelog.md`, merge the PR.
4. Run "typespec-java - publish" in (internal) DevOps.
5. Update Release notes in [GitHub Releases](https://github.com/Azure/autorest.java/releases).

## Debugging

### Debugging TypeScript Code

In project that runs TypeSpec compiler (e.g. `typespec-tests` folder), run
```shell
node --inspect-brk "node_modules/@typespec/compiler/dist/src/core/cli/cli.js" compile <tsp-file>
```
to run `tsp compile` in debug mode (as Node.js process).

Then, have the debugging client attach to port 9229 of the Node.js process.
Here is a [Visual Studio Code configuration](../../../typespec-tests/.vscode/launch.json).

Add break point to the `emitter.js` or `code-model-builder.js` under `node_modules/@azure-tools/typespec-java/dist/src` to debug the emitter.

### Debugging Java Code

See [Debugging Java Code](../../../typespec-extension/readme.md#debugging-java-code).

Alternatively, since the communication from TypeScript to Java is via the `code-model.yaml` file (plus the `EmitterOptions`), one can modify the `DEFAULT_OUTPUT_DIR` in `Main.java` under `core/packages/http-client-java/generator/http-client-generator` and debug `Main.main()`.

Notice:
- Add `--add-exports jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED --add-exports jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED --add-exports jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED --add-exports jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED --add-exports jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED` to VM options.
- There maybe some difference of other option between `tspconfig.yaml` and `EmitterOptions.java`. Remember to temporary modify `EmitterOptions.java` to reflect the option in `tspconfig.yaml` when running `Main.java` this way. For example, set `flavor` to `azure`.
