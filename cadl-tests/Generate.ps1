function Generate($CadlFile) {
  Write-Host "npx cadl compile $CadlFile --trace import-resolution --trace projection --trace cadl-java"
  Invoke-Expression "npx cadl compile $CadlFile --trace import-resolution --trace projection --trace cadl-java"

  if ($LASTEXITCODE) {
    exit $LASTEXITCODE
  }

  if (Test-Path ./cadl-output/src/main/java/module-info.java) {
    Remove-Item ./cadl-output/src/main/java/module-info.java
  }

  if (Test-Path ./cadl-output/src/samples) {
    Remove-Item ./cadl-output/src/samples -Recurse -Force
  }

  Copy-Item -Path ./cadl-output/src -Destination ./ -Recurse -Force

  Remove-Item ./cadl-output -Recurse -Force
}

./Setup.ps1

New-Item -Path ./existingcode/src/main/java/com/cadl/ -ItemType Directory -Force

if (Test-Path ./src/main/java/com/cadl/partialupdate) {
    Copy-Item -Path ./src/main/java/com/cadl/partialupdate -Destination ./existingcode/src/main/java/com/cadl/partialupdate -Recurse -Force
}

if (Test-Path ./src/main) {
    Remove-Item ./src/main -Recurse -Force
}
if (Test-Path ./cadl-output) {
    Remove-Item ./cadl-output -Recurse -Force
}

# generate without convenience APIs
Copy-Item ./cadl-project.yaml ./cadl-project-backup.yaml
Get-Content -path "cadl-project-backup.yaml" | % { $_ -Replace "      generate-convenience-apis: true", "      generate-convenience-apis: false" } |  Out-File "cadl-project.yaml"
foreach ($cadlFile in (Get-Item ./cadl/* -Filter "*.cadl" -Exclude "*partialupdate*")) {
    generate $cadlFile
}
Copy-Item ./cadl-project-backup.yaml ./cadl-project.yaml
Remove-Item ./cadl-project-backup.yaml
Invoke-Expression "mvn package ""-Dmaven.test.skip"""
if ($LASTEXITCODE) {
    exit $LASTEXITCODE
}
if (Test-Path ./src/main) {
    Remove-Item ./src/main -Recurse -Force
}
if (Test-Path ./cadl-output) {
    Remove-Item ./cadl-output -Recurse -Force
}

# run other local tests except partial update
foreach ($cadlFile in (Get-Item ./cadl/* -Filter "*.cadl" -Exclude "*partialupdate*")) {
    generate $cadlFile
}

# partial update test
npx cadl compile ./cadl/partialupdate.cadl --outputPath=./existingcode/
Copy-Item -Path ./existingcode/src/main/java/com/cadl/partialupdate -Destination ./src/main/java/com/cadl/partialupdate -Recurse -Force
Remove-Item ./existingcode -Recurse -Force

# run cadl ranch tests sources
Copy-Item -Path node_modules/@azure-tools/cadl-ranch-specs/http -Destination ./ -Recurse -Force

foreach ($cadlFile in (Get-ChildItem ./http -Filter "*.cadl" -File -Name -Recurse)) {
    generate "./http/$cadlFile"
}
Remove-Item ./http -Recurse -Force
