# Update Package for Latest Dependencies

Steps:

1. In "core" folder, run `git pull upstream main` to fetch latest commit from upstream or origin.
2. Go back to project root (`cd ..`). Commit the change in "core" folder.
3. Run `ncu -u` on "package.json" in both package.json from "typespec-extension" and "typespec-tests" folder.
4. Update package versions in `peerDependencies` (keep the semver range) in "package.json" from "typespec-extension" folder, according to the corresponding package versions in `devDependencies`.
5. Update package versions in `override` (keep the semver range) in "package.json" from "typespec-tests" folder, according to the corresponding package versions in "package.json" from "typespec-extension" folder.
6. Save the files, and run `npm install` in "typespec-extension" folder, so that "package-lock.json" would be updated.
7. Commit the changes in "typespec-extension" and "typespec-tests" folders.
8. Run `pwsh SyncTests.ps1` in "typespec-tests" folder.
9. Commit the changes in "typespec-tests" folder, include new files.
