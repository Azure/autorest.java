# Install and Test on TypeSpec-Java

## Install TypeSpec

Install Node.js and [TypeSpec](https://github.com/microsoft/typespec/).

```shell
npm install -g @typespec/compiler
```

## Build JAR, Build and Install TypeSpec-Java

`pwsh Setup.ps1 -RebuildJar` in `./typespec-tests` folder.

It builds JAR and makes the npm package in `./typespec-extension`, then install it to `./typespec-tests` folder.

## Generate Code

`pwsh Generate.ps1` in `./typespec-tests` folder.

It takes time.

The existing code is already in sync. Run this if you have changed the code of TypeSpec-Java.

## Run Test

Start Cadl-Ranch

`npm run testserver-run`

Then, run the tests in `typespec-tests` module as usual Java Tests.

## Generate Code for TypeSpec Source

`tsp compile <target.tsp>` in `./typespec-tests` folder.

This is usually to do a quick check whether the modified TypeSpec-Java works as expected.

Generated code will be at `./typespec-tests/tsp-output/` folder for inspect.

## Debugging Java code

`tspconfig.yaml` already configured
```yaml
    dev-options:
      generate-code-model: true
```

With this option, a `code-model.yaml` file is kept in `./typespec-tests/tsp-output/` folder after `tsp compile <target.tsp>`.

`Main.java` under `./typespec-extension` would load this `code-model.yaml` file as default, and run the Java code to generate the code.
At this stage, one can modify or debug the Java code (`./typespec-extension/src/main/java/`) in IDE. The code generated in `./typespec-tests/tsp-output/` would reflect the modified Java code.

Notice: there maybe some difference of other option between `tspconfig.yaml` and `EmitterOptions.java`. Remember to temporary modify `EmitterOptions.java` to reflect the option in `tspconfig.yaml` when running `Main.java` this way. (e.g. set `streamStyleSerialization` to `true`)

## Troubleshooting

### New version of `@typespec/compiler` etc.

Force an installation of new version via deleting `package-lock.json` and `node_modules` in `./typespec-extension` folder.

```shell
rm -rf node_modules
rm package-lock.json
```
