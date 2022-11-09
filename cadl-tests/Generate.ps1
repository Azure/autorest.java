function Generate($CadlFile) {
  Write-Host "npx cadl compile $CadlFile --trace import-resolution --trace projection --trace cadl-java"
  Invoke-Expression "npx cadl compile $CadlFile --trace import-resolution --trace projection --trace cadl-java"

  if ($LASTEXITCODE) {
      exit 1
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

#run other local tests except partial update
foreach ($cadlFile in (Get-Item ./cadl/* -Filter "*.cadl" -Exclude "*partialupdate*")) {
    generate $cadlFile
}

#partial update test
npx cadl compile ./cadl/partialupdate.cadl --outputPath=./existingcode/
Copy-Item -Path ./existingcode/src/main/java/com/cadl/partialupdate -Destination ./src/main/java/com/cadl/partialupdate -Recurse -Force
Remove-Item ./existingcode -Recurse -Force

# run cadl ranch tests sources
Copy-Item -Path node_modules/@azure-tools/cadl-ranch-specs/http -Destination ./ -Recurse -Force

foreach ($cadlFile in (Get-ChildItem ./http -Filter "*.cadl" -File -Name -Recurse)) {
    generate "./http/$cadlFile"
}
Remove-Item ./http -Recurse -Force
