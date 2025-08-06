# Update Node.JS Package for Latest Dependencies

Instructions:
* Always use absolute path when change directory.

Steps:

1. In "core" folder, run `git pull upstream main` to fetch latest commit from upstream or origin.
2. Go back to project root. Commit the change in "core" folder.
3. Run `ncu -u -x @typescript-eslint/eslint-plugin -x typescript` on "package.json" in both "package.json" from "typespec-extension" and "typespec-tests" folder.
4. Update package versions in `peerDependencies` (keep the semver range) in "package.json" from "typespec-extension" folder, according to the corresponding package versions in `devDependencies`.
5. Update package versions in `override` (keep the semver range) in "package.json" from "typespec-tests" folder, according to the corresponding package versions in "package.json" from "typespec-extension" folder.
6. Save the files, and run `npm install` in "typespec-extension" folder, so that "package-lock.json" would be updated.
7. Commit the changes in "typespec-extension" and "typespec-tests" folders.
8. Run `pwsh SyncTests.ps1` in "typespec-tests" folder.
9. Commit the changes in "typespec-tests" folder, include new files.

# Prepare for Minor/Patch Release

Steps:

1. Bump minor/patch version of `@typespec/typespec-java` in both "package.json" from "typespec-extension" and "typespec-tests" folder.
2. Save the file, and run `npm install` in "typespec-extension" folder, so that "package-lock.json" would be updated.
3. Add a new item in release history in "typespec-extension/changelog.md". The version would be the minor/patch version. The date would be today.

The [publish to NPM pipeline](https://dev.azure.com/azure-sdk/internal/_build?definitionId=5618) need to be triggered, after the PR is merged.

After release, the [sync SDK pipeline](https://dev.azure.com/azure-sdk/internal/_build?definitionId=6270) need to be triggered, to sync the released typespec-java into the SDK repository.
This pipeline also regenerates all SDK based on TypeSpec.
