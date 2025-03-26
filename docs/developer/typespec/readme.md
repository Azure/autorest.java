# Developer Guide for TypeSpec Emitter

## Publish `@azure-tools/typespec-java` to NPM

1. Update `version` in `typespec-extension/package.json`.
2. Update `typespec-java` `version` in `typespec-test/package.json`.
3. Update `typespec-extensions/changelog.md`, merge the PR.
4. Run "typespec-java - publish" in (internal) DevOps.
5. Update Release notes in [GitHub Releases](https://github.com/Azure/autorest.java/releases).
