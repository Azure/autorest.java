# WARNING: the script would "checkout" unstaged code in "./core" submodule. Please stage/commit your code there.

$ErrorActionPreference = "Stop"

Write-Host "Apply diff to core"
Push-Location ./core
try {
  git checkout .
  git apply ../core.patch --ignore-whitespace
} finally {
  Pop-Location
}

Write-Host "Build JAR"
mvn clean install -P local --no-transfer-progress
if ($LASTEXITCODE -ne 0) {
  exit $LASTEXITCODE
}
