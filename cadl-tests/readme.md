# Install and Test on TypeSpec-Java

## Install TypeSpec

Install Node.js and [TypeSpec](https://github.com/microsoft/typespec/).

```shell
npm install -g @typespec/compiler
```

## Build JAR

`mvn package -P local,cadl` in project root.

This will build `./cadl-extension`, which is basically `preprocessor` and `javagen` combined.

## Build and Install TypeSpec-Java

`pwsh Setup.ps1` in `./cadl-tests` folder.

It makes the npm package in `./cadl-extension`, then install it to `./cadl-tests` folder.

## Generate Code

`tsp compile <target.tsp>` in `./cadl-tests` folder.

Generated code will be at `./cadl-tests/cadl-output/` folder.

## Troubleshooting

### New version of `@typespec/compiler` etc.

Force an installation of new version via deleting `package-lock.json` and `node_modules` in `./cadl-extension` folder.

```shell
rm -rf node_modules
rm package-lock.json
```
