# re-build java-client-emitter
Set-Location ../cadl-extension/
# Remove-Item node_modules -Recurse -Force
# Remove-Item package-lock.json
npm install
npm run build
npm run lint
npm pack

# re-install
Set-Location ../cadl-tests/
if (Test-Path node_modules) {
    Remove-Item node_modules -Recurse -Force
}

if (Test-Path package-lock.json) {
    Remove-Item package-lock.json
}

npm install

# delete output
if (Test-Path cadl-output) {
    Remove-Item cadl-output -Recurse -Force
}