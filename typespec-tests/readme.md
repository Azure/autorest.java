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

## Troubleshooting

### New version of `@typespec/compiler` etc.

Force an installation of new version via deleting `package-lock.json` and `node_modules` in `./typespec-extension` folder.

```shell
rm -rf node_modules
rm package-lock.json
```
