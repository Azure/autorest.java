param (
    # re-build autorest.java
    [switch] $RebuildJar = $false
)

if ($RebuildJar) {
    mvn clean install -f ../pom.xml -P local,tsp -DskipTests "-Djacoco.skip"
    if ($LASTEXITCODE -ne 0) {
        exit $LASTEXITCODE
    }
}

# re-build typespec-java
Set-Location ../typespec-extension/
# Remove-Item node_modules -Recurse -Force
# Remove-Item package-lock.json
npm ci
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

npm install

# delete output
if (Test-Path tsp-output) {
    Remove-Item tsp-output -Recurse -Force
}
