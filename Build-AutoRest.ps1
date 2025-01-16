$ErrorActionPreference = "Stop"

Write-Host "Apply diff to core"
Push-Location ./core
try {
  git checkout .
  git apply ../core.diff --ignore-whitespace
} finally {
  Pop-Location
}

Write-Host "Build JAR"
mvn clean install -P local --no-transfer-progress
if ($LASTEXITCODE -ne 0) {
  exit $LASTEXITCODE
}
