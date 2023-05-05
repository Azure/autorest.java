# re-build java-client-emitter
Set-Location ../typespec-extension/
# Remove-Item node_modules -Recurse -Force
# Remove-Item package-lock.json
npm install --legacy-peer-deps
npm run build
npm run lint
npm pack

# re-install
Set-Location ../typespec-tests/
if (Test-Path node_modules) {
    Remove-Item node_modules -Recurse -Force
}

if (Test-Path package-lock.json) {
    Remove-Item package-lock.json
}

npm install --legacy-peer-deps

# snapshot of versions of dependencies
npm list --all

# delete output
if (Test-Path tsp-output) {
    Remove-Item tsp-output -Recurse -Force
}
