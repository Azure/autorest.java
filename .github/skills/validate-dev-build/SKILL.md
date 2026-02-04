---
name: validate-dev-build
description: Build the emitter with dev dependencies, and validate end-to-end tests
argument-hint: [test-case]
---

## Update Node.js dependencies to dev

Read "typespec-extension/package.json", if it does not depends on a dev version of `@typespec/compiler` (e.g. `X-dev.Y`), run following command on repository root:

```
npx -y @azure-tools/typespec-bump-deps typespec-extension/package.json typespec-tests/package.json --add-npm-overrides
```

## Prepare end-to-end test environment

Under "typespec-tests" folder, run `Setup.ps1`.

## Generate end-to-end test code

Under "typespec-tests" folder.

`[test-case]` usually in the form of an URL, e.g. "https://github.com/Azure/typespec-azure/tree/main/packages/azure-http-specs/specs/azure/client-generator-core/override"

Map this URL to the location of the test case in "node_modules".
E.g. above would map to "node_modules/@azure-tools/azure-http-specs/specs/azure/client-generator-core/override/client.tsp"

URL "https://github.com/microsoft/typespec/tree/main/packages/http-specs/specs/encode/array" would map to "node_modules/@typespec/http-specs/specs/encode/array/main.tsp"

- Use "client.tsp" when available. Otherwise, use "main.tsp".
- Escape the `@`, if it is necessary to do so in terminal.

Run
```
tsp compile --config . [location-of-test-case]
```
to generate Java code.
