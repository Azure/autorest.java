Write-Host "Apply diff to core"
Push-Location ./core
try {
  git checkout .
  git apply ../core.diff
} finally {
  Pop-Location
}

Write-Host "Build JAR"
mvn clean install -P tsp

Write-Host "Copy JAR and TypeSpec code to './typespec-extension' directory"
# Copy JAR
New-Item -ItemType File -Path ./typespec-extension/generator/http-client-generator/target/emitter.jar -Force
Copy-Item ./core/packages/http-client-java/generator/http-client-generator/target/emitter.jar ./typespec-extension/generator/http-client-generator/target/emitter.jar -Force
# Copy TypeScript code
Copy-Item -Path ./core/packages/http-client-java/emitter/src -Destination ./typespec-extension/ -Recurse -Force

Write-Host "Build and Pack typespec-java"
Push-Location ./typespec-extension/
try {
  npm ci
  npm run build
  npm run lint
  # npm run check-format
  npm pack
}
finally {
  Pop-Location
}
