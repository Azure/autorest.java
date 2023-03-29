# Install and Test on TypeSpec-Java

## Install TypeSpec

Install Node.js and [TypeSpec](https://github.com/microsoft/typespec/).

```shell
npm install -g @typespec/compiler
```

## Build JAR

`mvn package -P local,cadl` in project root.

This will build `./typespec-extension`, which is basically `preprocessor` and `javagen` combined.

## Build and Install TypeSpec-Java

`pwsh Setup.ps1` in `./typespec-tests` folder.

It makes the npm package in `./typespec-extension`, then install it to `./typespec-tests` folder.

## Generate Code

`tsp compile <target.tsp>` in `./typespec-tests` folder.

Generated code will be at `./typespec-tests/tsp-output/` folder.

## Troubleshooting

### New version of `@typespec/compiler` etc.

Force an installation of new version via deleting `package-lock.json` and `node_modules` in `./typespec-extension` folder.

```shell
rm -rf node_modules
rm package-lock.json
```
