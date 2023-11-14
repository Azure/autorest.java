# Use case:
#
# The purpose of this script is to compact the steps required to regenerate TypeSpec into a single script.
#
# If 'com.azure.autorest.customization' tests fails, re-install 'customization-base'.
#
# Before running this script the 'tsp' profile must be built, 'mvn install -P local,tsp'.

$tspPattern2NamespaceMapping = @{
  # override namespace for reserved keyword "enum"
  "type[\\/]enum[\\/]extensible[\\/]*" = "com.type.enums.extensible";
  "type[\\/]enum[\\/]fixed[\\/]*" = "com.type.enums.fixed";

  # override namespace for "resiliency/srv-driven/old.tsp" (make it different to that from "main.tsp")
  "resiliency[\\/]srv-driven[\\/]old.tsp" = "com.resiliency.servicedriven.v1"
}

function Generate($tspFile) {
  $overridedNamespace = $null
  foreach ($tspPattern in $tspPattern2NamespaceMapping.Keys) {
    if ($tspFile -match $tspPattern) {
      $overridedNamespace = $tspPattern2NamespaceMapping[$tspPattern]
      break
    }
  }

  $tspClientFile = $tspFile -replace 'main.tsp', 'client.tsp'
  if (($tspClientFile -match 'client.tsp$') -and (Test-Path $tspClientFile)) {
    $tspFile = $tspClientFile
  }

  $tspOptions = ""
  if ($overridedNamespace) {
    $tspOptions = "--options=""@azure-tools/typespec-java.namespace=$overridedNamespace"""
  }

  # Test customization for one of the TypeSpec definitions - naming.tsp
  if ($tspFile -match "tsp[\\/]naming.tsp$") {
#     # since tsp-output directory will be cleaned up after each test tsp, we copy the customization
#     # code into output directory from customization directory in tsp-output/customization directory just before
#     # generating the code
#     Copy-Item -Path ./customization -Destination ./tsp-output/customization -Recurse -Force

    # Add the customization-class option for Java emitter
    $tspOptions = "--options=""@azure-tools/typespec-java.customization-class=../customization/src/main/java/CustomizationTest.java"""
  } elseif ($tspFile -match "encode[\\/]bytes[\\/]main.tsp") {
    $tspOptions = "--options=""@azure-tools/typespec-java.customization-class=../customization/src/main/java/CustomizationEncodeBytes.java"""
  } elseif ($tspFile -match "type[\\/]union[\\/]main.tsp") {
    $tspOptions = "--options=""@azure-tools/typespec-java.stream-style-serialization=true"""
  } elseif ($tspFile -match "tsp[\\/]union.tsp") {
    $tspOptions = "--options=""@azure-tools/typespec-java.stream-style-serialization=true"""
  }

  $tspTrace = "--trace import-resolution --trace projection --trace typespec-java"
  $tspCommand = "npx tsp compile $tspFile $tspOptions $tspTrace"
  Write-Host $tspCommand
  Invoke-Expression $tspCommand

  if ($LASTEXITCODE) {
    exit $LASTEXITCODE
  }

  if (Test-Path ./tsp-output/src/main/java/module-info.java) {
    Remove-Item ./tsp-output/src/main/java/module-info.java
  }

  Copy-Item -Path ./tsp-output/src -Destination ./ -Recurse -Force -Exclude ReadmeSamples.java

  Remove-Item ./tsp-output -Recurse -Force
}

./Setup.ps1

New-Item -Path ./existingcode/src/main/java/com/cadl/ -ItemType Directory -Force

if (Test-Path ./src/main/java/com/cadl/partialupdate) {
  Copy-Item -Path ./src/main/java/com/cadl/partialupdate -Destination ./existingcode/src/main/java/com/cadl/partialupdate -Recurse -Force
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
Copy-Item -Path ./existingcode/src/main/java/com/cadl/partialupdate -Destination ./src/main/java/com/cadl/ -Recurse -Force
Remove-Item ./existingcode -Recurse -Force

# run cadl ranch tests sources
Copy-Item -Path node_modules/@azure-tools/cadl-ranch-specs/http -Destination ./ -Recurse -Force

foreach ($tspFile in (Get-ChildItem ./http -Include "main.tsp","old.tsp" -File -Name -Recurse)) {
  Write-Host $tspFile
  generate "./http/$tspFile"
}
Remove-Item ./http -Recurse -Force
