param (
    # re-build autorest.java
    [switch] $RebuildJar = $false
)

Set-Location $PSScriptRoot

if ($RebuildJar) {
    Set-Location ../
    ./Build-TypeSpec.ps1
} else {
    Set-Location ../typespec-extension/
    # re-build typespec-java
    npm ci
    npm run build
    npm run lint
    npm pack
    if ($LASTEXITCODE -ne 0) {
        exit $LASTEXITCODE
    }
}

# re-install
Set-Location ../typespec-tests/
if (Test-Path node_modules) {
    Remove-Item node_modules -Recurse -Force
}

if (Test-Path package-lock.json) {
    Remove-Item package-lock.json
}

# typespec-tests references typespec-java via a local file path.
# npm ci will fail when the hash of the package is different from the one in package-lock.json.
# To avoid this, we remove package-lock.json and run npm install instead.
npm install

# delete output
if (Test-Path tsp-output) {
    Remove-Item tsp-output -Recurse -Force
}
