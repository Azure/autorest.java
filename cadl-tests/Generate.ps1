function Generate($CadlFile) {
  Write-Host "cadl compile $CadlFile"
  Invoke-Expression "cadl compile $CadlFile"

  if ($LASTEXITCODE) {
      exit 1
  }

  if (Test-Path ./cadl-output/src/main/java/module-info.java) {
      Remove-Item ./cadl-output/src/main/java/module-info.java
  }

  if (Test-Path ./cadl-output/src/samples) {
      Remove-Item ./cadl-output/src/samples -Recurse -Force
  }

  Copy-Item -Path ./cadl-output/src -Destination ./ -Force

  Remove-Item ./cadl-output -Recurse -Force
}

./Setup.ps1

New-Item -Path ./existingcode/src/main/java/com/cadl/ -ItemType Directory -Force

if (Test-Path ./src/main/java/com/cadl/partialupdate) {
    Copy-Item -Path ./src/main/java/com/cadl/partialupdate -Destination ./existingcode/src/main/java/com/cadl/partialupdate -Force
}

if (Test-Path ./src/main) {
    Remove-Item ./src/main -Recurse -Force
}

if (Test-Path ./cadl-output) {
    Remove-Item ./cadl-output -Recurse -Force
}

# enable convenience methods for tests
$env:GENERATE_MODELS = "true"
$env:GENERATE_CONVENIENCE_METHODS = "true"
$env:PARTIAL_UPDATE = "true"

#run other local tests except partial update
foreach ($cadlFile in (Get-Item ./cadl/*.cadl -Exclude "*partialupdate*")) {
    generate $cadlFile
}

#partial update test
cadl compile ./cadl/partialupdate.cadl --outputPath=./existingcode/
Copy-Item -Path ./existingcode/src/main/java/com/cadl/partialupdate -Destination ./src/main/java/com/cadl/partialupdate -Force
Remove-Item ./existingcode -Recurse -Force

# run cadl ranch tests sources
Copy-Item -Path node_modules/@azure-tools/cadl-ranch-specs/http -Destination ./ -Force

$env:NAMESPACE = "com.cadl.testserver.servicedriven1"
generate ./http/resiliency/srv-driven-1/main.cadl

$env:NAMESPACE = "com.cadl.testserver.servicedriven2"
generate ./http/resiliency/srv-driven-2/main.cadl

$env:NAMESPACE = ''
foreach ($cadlFile in (Get-Item ./http/*.cadl -Exclude "*resiliency*")) {
    generate $cadlFile
}
Remove-Item ./http -Recurse -Force