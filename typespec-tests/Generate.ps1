function Generate($tspFile) {
  Write-Host "npx tsp compile $tspFile --trace import-resolution --trace projection --trace typespec-java"
  Invoke-Expression "npx tsp compile $tspFile --trace import-resolution --trace projection --trace typespec-java"

  if ($LASTEXITCODE) {
    exit $LASTEXITCODE
  }

  if (Test-Path ./tsp-output/src/main/java/module-info.java) {
    Remove-Item ./tsp-output/src/main/java/module-info.java
  }

  if (Test-Path ./tsp-output/src/samples) {
    Remove-Item ./tsp-output/src/samples -Recurse -Force
  }

  Copy-Item -Path ./tsp-output/src -Destination ./ -Recurse -Force

  Remove-Item ./tsp-output -Recurse -Force
}

./Setup.ps1

New-Item -Path ./existingcode/src/main/java/com/tsp/ -ItemType Directory -Force

if (Test-Path ./src/main/java/com/tsp/partialupdate) {
    Copy-Item -Path ./src/main/java/com/tsp/partialupdate -Destination ./existingcode/src/main/java/com/tsp/partialupdate -Recurse -Force
}

if (Test-Path ./src/main) {
    Remove-Item ./src/main -Recurse -Force
}
if (Test-Path ./tsp-output) {
    Remove-Item ./tsp-output -Recurse -Force
}

# run other local tests except partial update
foreach ($tspFile in (Get-Item ./tsp/* -Filter "*.tsp" -Exclude "*partialupdate*")) {
    generate $tspFile
}

# partial update test
npx tsp compile ./tsp/partialupdate.tsp --options="@azure-tools/typespec-java.emitter-output-dir={project-root}/existingcode"
Copy-Item -Path ./existingcode/src/main/java/com/tsp/partialupdate -Destination ./src/main/java/com/tsp/ -Recurse -Force
Remove-Item ./existingcode -Recurse -Force

# run cadl ranch tests sources
Copy-Item -Path node_modules/@azure-tools/cadl-ranch-specs/http -Destination ./ -Recurse -Force

foreach ($tspFile in (Get-ChildItem ./http -Filter "*.tsp" -File -Name -Recurse)) {
    generate "./http/$tspFile"
}
Remove-Item ./http -Recurse -Force
