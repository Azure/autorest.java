# Development

The emitter is the [TypeSpec](https://typespec.io/) library for emitting Java client from the TypeSpec REST protocol binding.

See [Emitter](https://typespec.io/docs/extending-typespec/emitters-basics/) for the basics on the TypeSpec emitter library.

See [Developer Guide for TypeSpec Emitter](docs/developer/typespec/readme.md) for basics on this repository.

Guideline:

- Most of the development work should happen in "core/packages/http-client-java" folder.
- Before starting the development in "core" folder, always checkout latest code from main branch, and create a dev branch upon it.
- When checking in code in "core" folder, do not check-in the files in "core.patch".
- When preparing the PR, create 2 PRs, one for the code changes in submodule ("typespec" repository, if there is change), one for the code changes in this repository (which would include the update on "core" submodule).

## TypeScript Part for the Emitter

TypeScript code for the Emitter is in "core/packages/http-client-java/emitter" folder. Follow the package.json for the build and unit test.

The code there, in runtime, produces a "code-model.yaml" file, and passes it to Java Part to generate Java client.

## Java Part for the Emitter

Java code for the Emitter is in "core/packages/http-client-java/generator/http-client-generator" (which depends on "http-client-generator-core" and "http-client-generator-mgmt" module). Follow the pom.xml for the build and unit test.

The code there, in runtime, consumes the "code-model.yaml" file produced by the TypeSpec Part, and generate the Java client.

## End-to-end test with TypeSpec

The end-to-end takes a TypeSpec file, and produces a Java client.
We'd like to see whether the TypeSpec file (with a certain feature) can produce expected Java code.

The test happens in "core/packages/http-client-java/generator/http-client-generator-test" folder.

If there is code change in "core", before running any end-to-end test, first run the "Setup.ps1" script there, to update the test environment.

When calling "tsp compile", always set `--config .`.

The Java client would be generated in its "tsp-output" folder.
When running a new test, delete this folder if exists.

# Update and Release

## Update Node.js Package for Latest Dependencies

Instructions:
- Always use absolute path when change directory.

Steps:

1. In "core" folder, run `git pull upstream main` to fetch latest commit from upstream or origin.
2. Go back to project root. Commit the change in "core" folder.
3. Run `ncu -u` on "package.json" in both "package.json" from "typespec-extension" and "typespec-tests" folder.
4. Update package versions in `peerDependencies` (keep the semver range) in "package.json" from "typespec-extension" folder, according to the corresponding package versions in `devDependencies`.
5. Update package versions in `override` (keep the semver range) in "package.json" from "typespec-tests" folder, according to the corresponding package versions in "package.json" from "typespec-extension" folder.
6. Save the files, and run `npm install` in "typespec-extension" folder, so that "package-lock.json" would be updated.
7. Commit the changes in "typespec-extension" and "typespec-tests" folders.
8. Run `pwsh SyncTests.ps1` in "typespec-tests" folder.
9. Commit the changes in "typespec-tests" folder, include new files.

## Prepare for Minor/Patch Release

Steps:

1. Bump minor/patch version of `@typespec/typespec-java` in both "package.json" from "typespec-extension" and "typespec-tests" folder.
2. Save the file, and run `npm install` in "typespec-extension" folder, so that "package-lock.json" would be updated.
3. Add a new item in release history in "typespec-extension/changelog.md". The version would be the minor/patch version. The date would be today.

The [publish to NPM pipeline](https://dev.azure.com/azure-sdk/internal/_build?definitionId=5618) needs to be triggered, after the PR is merged.

After release, the [sync SDK pipeline](https://dev.azure.com/azure-sdk/internal/_build?definitionId=6270) needs to be triggered, to sync the released typespec-java into the SDK repository.
This pipeline also regenerates all SDK based on TypeSpec.
